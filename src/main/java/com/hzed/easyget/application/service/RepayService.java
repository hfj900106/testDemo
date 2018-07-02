package com.hzed.easyget.application.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.hzed.easyget.application.enums.*;
import com.hzed.easyget.application.service.product.ProductEnum;
import com.hzed.easyget.application.service.product.ProductFactory;
import com.hzed.easyget.application.service.product.ProductService;
import com.hzed.easyget.application.service.product.model.AbstractProduct;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.consts.ComConsts;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.exception.WarnException;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.model.PayResponse;
import com.hzed.easyget.infrastructure.repository.*;
import com.hzed.easyget.infrastructure.utils.Arith;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import com.hzed.easyget.infrastructure.utils.ValidatorUtil;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wuchengwu
 * @date 2018/6/4
 */
@Service
@Slf4j
public class RepayService {

    @Autowired
    private ComService comService;
    @Autowired
    private BidRepository bidRepository;
    @Autowired
    private BidProgressRepository bidProgressRepository;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BillLedgerRepository billLedgerRepository;
    @Autowired
    private UserTransactionRepository userTransactionRepository;
    @Autowired
    private UserRepaymentRepository userRepaymentRepository;
    @Autowired
    private RepayInfoFlowJobRepository repayInfoFlowJobRepository;
    @Autowired
    private RepayRepository repayRepository;
    @Autowired
    private FileService fileService;
    @Autowired
    private UserTransactionRepayRepository userTransactionRepayRepository;
    @Autowired
    private UserTransactionPicRepository userTransactionPicRepository;
    @Autowired
    private BluePayService bluePayService;
    @Autowired
    private TempTableRepository tempTableRepository;
    @Autowired
    private TransactionService transactionService;

    public RepayListResponse repaidList(RepayListRequest request) {
        RepayListResponse repayListResponse = new RepayListResponse();

        Integer pageNo = request.getPageNo();
        Integer pageSize = request.getPageSize();

        GlobalUser globalUser = RequestUtil.getGlobalUser();
        Long userId = globalUser.getUserId();
        List<Bid> bidList = bidRepository.findPageByUserIdAndStatus(userId, Lists.newArrayList(BidStatusEnum.REPAYMENT.getCode().byteValue(), BidStatusEnum.CLEARED.getCode().byteValue()), pageNo, pageSize);
        // 没有借款记录
        if (bidList == null || bidList.isEmpty()) {
            return repayListResponse;
        }
        List<RepaymentResponse> repaymentResponseList = Lists.newArrayList();
        for (Bid bid : bidList) {
            RepaymentResponse repaymentResponse = new RepaymentResponse();
            Long bidId = bid.getId();
            BidProgress bidProgress = bidProgressRepository.findByBidIdAndType(bidId, BidProgressTypeEnum.CLEAR.getCode().byteValue());
            Bill bill = billRepository.findByBid(bidId);

            // 已结清
            if (BidStatusEnum.CLEARED.getCode().byteValue() == bid.getStatus().byteValue()) {
                repaymentResponse.setRepayTime(DateUtil.localDateTimeToTimestamp(bidProgress.getHandleTime()));
                repaymentResponse.setStatus(RepayStatusEnum.CLEAR_REPAY.getCode().intValue());
            }
            // 未结清
            else {
                // 获取待还款标的的待还总额
                repayListResponse.setTotalAmount(comService.getBidNoRepayFee(bidId, LocalDateTime.now()));

                // 查询应还时间与当前时间对比，大于当前时间表示逾期，小于等于表示没到期
                int days = DateUtil.getBetweenDays(bill.getRepaymentTime(), LocalDateTime.now());

                // 逾期未结清
                if (days > 0) {
                    repaymentResponse.setStatus(RepayStatusEnum.OVDUE_UN_REPAY.getCode().intValue());
                    repaymentResponse.setDays(days);
                }
                // 正常未结清
                else {
                    repaymentResponse.setStatus(RepayStatusEnum.UN_REPAY.getCode().intValue());
                    int days1 = DateUtil.getBetweenDays(LocalDateTime.now(), bill.getRepaymentTime());
                    repaymentResponse.setDays(days1);
                }
            }

            repaymentResponse.setLoanAmount(bid.getLoanAmount());
            repaymentResponse.setBid(bidId);
            repaymentResponseList.add(repaymentResponse);
        }

        repayListResponse.setRepaymentInfo(repaymentResponseList);
        return repayListResponse;
    }

    /**
     * 还款详情
     *
     * @param request
     * @return
     */
    public RepayDetailResponse repayDetail(RepayDetailRequest request) {
        RepayDetailResponse repayDetailResponse = new RepayDetailResponse();

        Long bidId = request.getBidId();
        Bid bid = bidRepository.findById(bidId);
        // 账单
        Bill bill = billRepository.findByBid(bidId);
        // 放款时间
        BidProgress bidProgress = bidProgressRepository.findByBidIdAndType(bidId, BidProgressTypeEnum.LOAN.getCode().byteValue());

        // 还款时间或实际已还时间
        int status = bill.getStatus();
        if (BillStatusEnum.WAIT_CLEAR.getCode().equals(status)) {
            repayDetailResponse.setRepayTime(DateUtil.localDateTimeToStr2(bill.getRepaymentTime()));
        } else {
            repayDetailResponse.setRepayTime(DateUtil.localDateTimeToStr2(bill.getSettlementTime()));
        }

        // 还款状态
        if ((BidStatusEnum.CLEARED.getCode().toString()).equals(bid.getStatus().toString())) {
            repayDetailResponse.setStatus(RepayStatusEnum.CLEAR_REPAY.getCode());
        } else {
            int days = DateUtil.getBetweenDays(bill.getRepaymentTime(), LocalDateTime.now());
            repayDetailResponse.setStatus(days > 0 ? RepayStatusEnum.OVDUE_UN_REPAY.getCode() : RepayStatusEnum.UN_REPAY.getCode());

        }

        // 标的待还总费用
        BigDecimal totalRepayAmount = comService.getBidNoRepayFee(bidId, LocalDateTime.now());

        repayDetailResponse.setTotalRepayAmount(totalRepayAmount);
        repayDetailResponse.setPeriod(bid.getPeriod());
        repayDetailResponse.setLoanTime(DateUtil.localDateTimeToStr2(bidProgress.getHandleTime()));
        return repayDetailResponse;
    }

    /**
     * 还款走信息流，包括全部结清和部分还款
     * 保存 t_loan_user_repayment 还款记录表
     * 保存 t_loan_bill_ledger 台账表
     * 保存 t_loan_bill 账单表
     * 保存 t_loan_bid_progress 标的进度表（主要更新还款信息）
     * 保存 t_loan_bid 标的表（主要是更新结清操作，部分还款不用更新）
     * 保存 t_loan_user_transaction 用户交易记录表
     *
     * @param bidId             标id
     * @param repayAmount       还款金额
     * @param realRepaymentTime 实际还款时间
     * @param job               定时任务标志，不是定时任务传null
     */
    @Transactional(rollbackFor = Exception.class)
    public void repayInformationFlow(Long bidId, BigDecimal repayAmount, LocalDateTime realRepaymentTime, Long transactionId, RepayInfoFlowJob job) {
        // 判断还款金额是否大于项目待还总额
        BigDecimal bidNoRepay = comService.getBidNoRepayFee(bidId, realRepaymentTime);
        if (repayAmount.compareTo(bidNoRepay) > 0) {
            throw new ComBizException(BizCodeEnum.OVER_REPAYMENT_MONEY);
        }

        // 保存 t_loan_bid_progress 标的进度表
        BidProgress progressInsert = new BidProgress();
        progressInsert.setId(IdentifierGenerator.nextId());
        progressInsert.setBidId(bidId);
        progressInsert.setType(BidProgressTypeEnum.REPAY.getCode().byteValue());
        progressInsert.setHandleTime(LocalDateTime.now());
        progressInsert.setHandleResult("用户还款：" + repayAmount);
        bidProgressRepository.insert(progressInsert);

        // 待还账单
        Bill billBefore = billRepository.findAllBillByBidIdWithExp(bidId).get(0);
        // 还账单操作
        repayBill(billBefore, repayAmount, transactionId, LocalDateTime.now());

        // 保存 t_loan_bid 标的表（主要是更新结清操作，部分还款不用更新）
        Bill billAfter = billRepository.findAllBillByBidIdWithExp(bidId).get(0);
        if (BillStatusEnum.NORMAL_CLEAR.getCode().intValue() == billAfter.getStatus().intValue()
                || BillStatusEnum.OVERDUE_CLEAR.getCode().intValue() == billAfter.getStatus().intValue()) {
            // 账单已结清 修改标的状态为结清
            Bid bidUpdate = new Bid();
            bidUpdate.setId(bidId);
            bidUpdate.setStatus(BidStatusEnum.CLEARED.getCode().byteValue());
            bidUpdate.setUpdateTime(LocalDateTime.now());
            bidRepository.update(bidUpdate);
        }

        if (job != null) {
            RepayInfoFlowJob jobUpdate = new RepayInfoFlowJob();
            jobUpdate.setId(job.getId());
            jobUpdate.setStatus(JobStatusEnum.SUCCESS.getCode().byteValue());
            jobUpdate.setTimes((byte) (job.getTimes().intValue() + 1));
            jobUpdate.setUpdateTime(LocalDateTime.now());
            repayInfoFlowJobRepository.update(jobUpdate);
        }

    }

    /**
     * 账单还款
     * 逾期费->尾款->本金
     *
     * @param bill              账单
     * @param repayAmount       总还款金额
     * @param realRepaymentTime 实际还款时间
     * @return 剩余金额
     */
    private BigDecimal repayBill(Bill bill, BigDecimal repayAmount, Long transactionId, LocalDateTime realRepaymentTime) {
        Long billId = bill.getId();
        // 还逾期费
        BigDecimal restAmount1 = repayBillLedger(billId, BillLedgerItemEnum.OVERDUE_FEE.getCode().intValue(), repayAmount, transactionId, realRepaymentTime);
        // 还尾款
        BigDecimal restAmount2 = repayBillLedger(billId, BillLedgerItemEnum.TAIL_FEE.getCode().intValue(), restAmount1, transactionId, realRepaymentTime);
        // 还本金
        BigDecimal restAmount3 = repayBillLedger(billId, BillLedgerItemEnum.CORPUS.getCode().intValue(), restAmount2, transactionId, realRepaymentTime);
        // 本次还款金额
        BigDecimal repayAmountNow = restAmount3.compareTo(Arith.ZERO) >= 0 ? repayAmount.subtract(restAmount3) : repayAmount;
        // 更新账单表
        Bill billUpdate = new Bill();
        billUpdate.setId(billId);
        billUpdate.setRealRepaymentAmount(bill.getRealRepaymentAmount().add(repayAmountNow));
        billUpdate.setUpdateTime(LocalDateTime.now());

        // 判断此笔账单是否结清
        BillLedger corpusLedger = billLedgerRepository.findBillLedgerItemByBillIdWithExp(billId, BillLedgerItemEnum.CORPUS.getCode().byteValue());
        // 本金没有待还表示已结清
        if (corpusLedger.getRepaymentAmount().compareTo(corpusLedger.getRealRepaymentAmount()) == 0) {
            billUpdate.setSettlementTime(LocalDateTime.now());
            // 逾期结清还是正常结清
            billUpdate.setStatus(DateUtil.compare(realRepaymentTime, LocalDateTime.now()) ?
                    BillStatusEnum.OVERDUE_CLEAR.getCode().byteValue() : BillStatusEnum.NORMAL_CLEAR.getCode().byteValue());
        } else {
            // 部分还款
            billUpdate.setIsPartialRepayment(true);
        }
        billRepository.update(billUpdate);
        return restAmount3;
    }

    /**
     * 台账还款
     *
     * @param billId            账单id
     * @param item              台账类型 本金、尾款、逾期费
     * @param repayAmount       总还款金额
     * @param realRepaymentTime 实际还款时间
     * @return 剩余金额
     */
    private BigDecimal repayBillLedger(Long billId, Integer item, BigDecimal repayAmount, Long transactionId, LocalDateTime realRepaymentTime) {
        // 还款金额为0
        if (repayAmount == null || repayAmount.compareTo(Arith.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }
        // 待还总额
        BigDecimal billItemNoRepay = comService.getBillItemNoRepayFee(billId, item, realRepaymentTime);
        // 已结清则直接返回还款金额
        if (billItemNoRepay.compareTo(Arith.ZERO) <= 0) {
            return repayAmount;
        }
        // 剩余金额
        BigDecimal restAmount = repayAmount.subtract(billItemNoRepay);
        // 本次还款金额
        BigDecimal repayAmountNow = restAmount.compareTo(Arith.ZERO) <= 0 ? repayAmount : billItemNoRepay;

        // 保存 t_loan_user_repayment 还款记录表
        UserRepayment userRepayment = new UserRepayment();
        userRepayment.setId(IdentifierGenerator.nextId());
        userRepayment.setTransactionId(transactionId);
        userRepayment.setBillId(billId);
        userRepayment.setRepaymentItem(item.byteValue());
        userRepayment.setRepaymentAmount(billItemNoRepay);
        userRepayment.setRealRepaymentAmount(repayAmountNow);
        userRepaymentRepository.insert(userRepayment);

        // 新增或更新t_loan_bill_ledger 台账表
        BillLedger ledger = billLedgerRepository.findBillLedgerItemByBillId(billId, item.byteValue());
        // 逾期费单独处理
        if (BillLedgerItemEnum.OVERDUE_FEE.getCode().equals(item)) {
            // 无逾期费记录则新增
            if (ledger == null) {
                BillLedger ledgerInsert = new BillLedger();
                ledgerInsert.setId(IdentifierGenerator.nextId());
                ledgerInsert.setBillId(billId);
                ledgerInsert.setRepaymentTime(LocalDateTime.now());
                ledgerInsert.setRepaymentAmount(billItemNoRepay);
                ledgerInsert.setRepaymentItem(item.byteValue());
                ledgerInsert.setRealRepaymentTime(LocalDateTime.now());
                ledgerInsert.setRealRepaymentAmount(repayAmountNow);
                ledgerInsert.setCreateTime(LocalDateTime.now());
                billLedgerRepository.insert(ledgerInsert);
            }
            // 有逾期费记录则修改
            else {
                BillLedger ledgerUpdate = new BillLedger();
                ledgerUpdate.setId(ledger.getId());
                ledgerUpdate.setRepaymentTime(LocalDateTime.now());
                ledgerUpdate.setRepaymentAmount(billItemNoRepay);
                ledgerUpdate.setRealRepaymentTime(LocalDateTime.now());
                ledgerUpdate.setRealRepaymentAmount(repayAmountNow);
                ledgerUpdate.setUpdateTime(LocalDateTime.now());
                billLedgerRepository.update(ledgerUpdate);
            }
        }
        // 其他台账做更新操作(本金+尾款)
        else {
            if (ledger == null) {
                throw new ComBizException(BizCodeEnum.ILLEGAL_LEDGER_TYPE, new Object[]{billId, item});
            }

            BillLedger ledgerUpdate = new BillLedger();
            ledgerUpdate.setId(ledger.getId());
            ledgerUpdate.setRealRepaymentTime(LocalDateTime.now());
            // 实还金额累加
            ledgerUpdate.setRealRepaymentAmount(repayAmountNow.add(ledger.getRealRepaymentAmount()));
            ledgerUpdate.setUpdateTime(LocalDateTime.now());
            billLedgerRepository.update(ledgerUpdate);

        }

        return restAmount.compareTo(BigDecimal.ZERO) <= 0 ? BigDecimal.ZERO : restAmount;
    }

    public RepayPartDetailResponse repayPartDetail(RepayPartDetailRequest request) {
        Long bidId = request.getBidId();
        //获取标的待还总费用
        BigDecimal bidNoRepay = comService.getBidNoRepayFee(bidId, LocalDateTime.now());
        //获取产品最小还款值
        Bid bid = bidRepository.findByIdWithExp(bidId);
        // 相关账单
        ProductService productService = ProductFactory.getProduct(ProductEnum.EasyGet);
        AbstractProduct product = productService.createProduct(bid.getLoanAmount(), bid.getPeriod());
        BigDecimal minRepayAmount = productService.getMinRepayAmount(product);
        if (ObjectUtils.isEmpty(minRepayAmount)) {
            log.error("找不到产品的最小还款金额");
            throw new WarnException(BizCodeEnum.UNKNOWN_EXCEPTION);
        }

        //待还总费用大于0 且小于最小还款金额
        if (1 == bidNoRepay.compareTo(BigDecimal.ZERO) && -1 == bidNoRepay.compareTo(minRepayAmount)) {
            minRepayAmount = bidNoRepay;
        }
        return RepayPartDetailResponse.builder().totalAmount(String.valueOf(bidNoRepay)).minRepayAmount(String.valueOf(minRepayAmount)).inAccount(bid.getInAccount()).build();
    }

    /**
     * 放款标识
     */
    private static final String CASHOUT = "cashout";
    /**
     * 还款标识
     */
    private static final String BANK = "bank";

    /**
     * 全部还款/部分还款
     *
     * @param amount 还款金额
     * @param bidId  标id
     * @param flag   是否全部还清
     * @return
     */
    public PaymentIdResponse findloanManagResponse(BigDecimal amount, Long bidId, boolean flag) {
        // 查询标的信息
        Bid bid = bidRepository.findByIdWithExp(bidId);
        //查询应还时间
        LocalDateTime repaymentTime = repayRepository.findRepaymentTime(bid.getId());
        if (ObjectUtils.isEmpty(repaymentTime)) {
            throw new ComBizException(BizCodeEnum.USERTRANSACTION_ERROR);
        }
        // 查询是否有没过期的还款码
        UserTransactionRepay vaCode = repayRepository.getVaCode(bidId, amount, flag ? TransactionTypeEnum.ALL_CLEAR.getCode().byteValue() : TransactionTypeEnum.PARTIAL_CLEARANCE.getCode().byteValue());
        PaymentIdResponse response = new PaymentIdResponse();
        if (!ObjectUtils.isEmpty(vaCode)) {
            response.setVaCode(vaCode.getVa());
            response.setExpireTime(DateUtil.localDateTimeToTimestamp(vaCode.getVaCreateTime()));
            response.setMode(vaCode.getMode());
        }
        response.setBidId(bidId);
        response.setAmount(amount);
        response.setRepaymentTime(DateUtil.localDateTimeToTimestamp(repaymentTime));
        response.setFlag(flag);
        return response;
    }

    /**
     * 获取va码接口
     *
     * @param request 前端请求对象
     * @return va码构建对象
     */
    public TransactionVaResponse findVaTranc(TransactionVaRequest request) {
        // 查询标的信息
        Bid bid = bidRepository.findByIdWithExp(request.getBidId());
        // 先查询数据库 是否存在没过期的还款码
        UserTransactionRepay repayQuery = repayRepository.getVaCodeByParmers(request.getBidId(), request.getAmount(), request.isFlag() ? TransactionTypeEnum.ALL_CLEAR.getCode().byteValue() : TransactionTypeEnum.PARTIAL_CLEARANCE.getCode().byteValue(), request.getMode());
        TransactionVaResponse vaResponse = new TransactionVaResponse();
        if (!ObjectUtils.isEmpty(repayQuery)) {
            vaResponse.setExpireTime(DateUtil.localDateTimeToTimestamp(repayQuery.getVaExpireTime()));
            vaResponse.setVaCodel(repayQuery.getVa());
            vaResponse.setMode(repayQuery.getMode());
            return vaResponse;
        }
        // 交易单号
        String paymentId = IdentifierGenerator.nextSeqNo();
        //bluepay接口调用
        PayResponse response = bluePayService.bluePaymentCode(bid, request.getMode(), request.getAmount(), paymentId);
        // 解析返回信息
        String paymentCode = JSON.parseObject(response.getData()).getString("paymentCode");
        log.info("获取还款码，bluepay返回还款码：{}", paymentCode);
        LocalDateTime createTime = LocalDateTime.now();
        UserTransactionRepay repayInsert = UserTransactionRepay.builder()
                .id(IdentifierGenerator.nextId())
                .bidId(request.getBidId())
                .paymentId(paymentId)
                .amount(request.getAmount())
                .repaymentTime(LocalDateTime.now())
                .mode(request.getMode())
                .va(paymentCode)
                .vaCreateTime(createTime)
                .vaExpireTime(createTime.plusHours(6))
                .repaymentType(request.isFlag() ? TransactionTypeEnum.ALL_CLEAR.getCode().byteValue() : TransactionTypeEnum.PARTIAL_CLEARANCE.getCode().byteValue())
                .status(TransactionTypeEnum.INIT_RANSACTION.getCode().byteValue())
                .build();
        //插入va码到数据库
        repayRepository.insertSelective(repayInsert);
        //组装返回信息
        vaResponse.setExpireTime(DateUtil.localDateTimeToTimestamp(repayInsert.getVaExpireTime()));
        vaResponse.setMode(request.getMode());
        vaResponse.setVaCodel(paymentCode);
        return vaResponse;
    }

    /**
     * 还款成功信息流
     *
     * @param userTransaction 交易对象
     * @param paymentId       交易id
     */
    public void repaymentSuccess(UserTransaction userTransaction, String paymentId) {
        // 修改交易记录
        UserTransaction userTransactionUpdate = UserTransaction.builder()
                .id(userTransaction.getId())
                .status(TransactionTypeEnum.SUCCESS_RANSACTION.getCode().byteValue())
                .updateTime(LocalDateTime.now()).build();
        // 插入还款定时任务
        RepayInfoFlowJob repayInfoFlowJobInsert = RepayInfoFlowJob.builder()
                .id(IdentifierGenerator.nextId())
                .transactionId(userTransaction.getId())
                .bidId(userTransaction.getBidId())
                .repaymentAmount(userTransaction.getAmount())
                .realRepaymentTime(LocalDateTime.now())
                .repaymentMode(RepayFlowJobEnum.UNDER_LINE.getCode().byteValue())
                .repaymentType(userTransaction.getRepaymentType())
                .build();
        UserTransactionRepay repayUpdate = UserTransactionRepay.builder().paymentId(paymentId).status(TransactionTypeEnum.SUCCESS_RANSACTION.getCode().byteValue()).build();
        repayRepository.afterRepayment(userTransactionUpdate, repayInfoFlowJobInsert, repayUpdate);
    }

    /**
     * 根据交易订单号查询va码记录
     *
     * @param paymentId 订单号
     * @return va码信息
     */
    public UserTransactionRepay findRepayInfoByPaymentId(String paymentId) {
        return repayRepository.findRepayInfoByPaymentId(paymentId);
    }

    /**
     * 插入交易记录
     *
     * @param bidId          标的id
     * @param paymentId      订单号
     * @param price          交易金额
     * @param reapaymentType 交易方式
     */
    public void insertUserTransaction(long bidId, String paymentId, Integer price, Byte reapaymentType) {
        //查询标的信息
        Bid bid = bidRepository.findByIdWithExp(bidId);
        UserTransaction transactionInsert = UserTransaction.builder()
                .id(IdentifierGenerator.nextId())
                .status(TransactionTypeEnum.IN_RANSACTION.getCode().byteValue())
                .paymentId(paymentId)
                .account(bid.getInAccount())
                .bank(bid.getInBank())
                .amount(BigDecimal.valueOf(price))
                .type(TransactionTypeEnum.OUT.getCode().byteValue())
                .repaymentType(reapaymentType)
                .bidId(bidId)
                .userId(bid.getUserId()).build();
        userTransactionRepository.insertSelective(transactionInsert);
    }

    /**
     * 修改va码信息
     *
     * @param repayUpdate va码对象
     */
    public void updateUserTransactionRepay(UserTransactionRepay repayUpdate) {
        repayRepository.updateUserTransactionRepay(repayUpdate);
    }

    public List<VaHistoryResponse> getVaHistory(VaHistoryRequest request) {
        List<UserTransactionRepay> repays = userTransactionRepayRepository.findByBidId(request);
        List<VaHistoryResponse> results = Lists.newArrayList();
        repays.forEach(repay -> results.add(VaHistoryResponse.builder().mode(repay.getMode()).va(repay.getVa()).build()));
        return results;
    }

    public void uploadPicEvidence(UploadPicEvidenceRequest request) {
        String[] base64Imgs = request.getBase64Imgs();
        String[] picSuffixs = request.getPicSuffixs();
        if (base64Imgs.length != picSuffixs.length) {
            throw new ComBizException(BizCodeEnum.PIC_SIZE_ERROR);
        }
        List<UserTransactionPic> userTransactionPicList = Lists.newArrayList();
        for (int i = 0; i < base64Imgs.length; i++) {
            String picUrl;
            try {
                picUrl = fileService.uploadBase64Img(base64Imgs[i], picSuffixs[i]);
            } catch (Exception e) {
                throw new ComBizException(BizCodeEnum.UPLOAD_PIC_FAIL);
            }
            UserTransactionPic repayPicInsert = UserTransactionPic.builder()
                    .evidencePicUrl(picUrl)
                    .va(request.getVa())
                    .bidId(request.getBidId())
                    .mode(request.getMode())
                    .build();
            userTransactionPicList.add(repayPicInsert);
        }
        userTransactionPicRepository.batchInsert(userTransactionPicList);

    }

    /**
     * mq处理放款/还款回调业务
     *
     * @param message mq回调报文
     */
    @Transactional(rollbackFor = Exception.class)
    public void mqCallback(String message) {
        log.info("详细返回信息{}", message);
        BluePayRequest bluePayRequest = JSONObject.parseObject(message, BluePayRequest.class);
        // 参数校验
        ValidatorUtil.validateWithNull(bluePayRequest);
        // 返回的状态
        String status = bluePayRequest.getStatus();
        // 交易ID
        String paymentId = bluePayRequest.getT_id().trim();
        // 放还款类型
        String interfacetype = bluePayRequest.getInterfacetype();
        log.info("当前交易类型：{}", CASHOUT.equals(interfacetype) ? "放款" : (BANK.equals(interfacetype) ? "还款" : "其他"));
        // 过滤处理中
        if (status.equals(BluePayStatusEnum.OK.getKey())) {
            log.info("MQ交易正在处理中，处理终止");
            return;
        }

        // 先判断是不是还款
        if (BANK.equals(interfacetype)) {
            // 查询是否有对应的va码记录
            UserTransactionRepay repayQuery = this.findRepayInfoByPaymentId(paymentId);
            if (ObjectUtils.isEmpty(repayQuery)) {
                log.info("还款交易没有对应的va码记录，处理终止");
                return;
            }
            // 查询本地是否有还款交易记录
            UserTransaction repayTransacQuery = userTransactionRepository.findUserTranByPaymentId(paymentId, TransactionTypeEnum.OUT.getCode().byteValue());
            // 没有交易就要先插入一条
            if (ObjectUtils.isEmpty(repayTransacQuery)) {
                //交易表插入交易中记录
                log.info("发现还款码，初始化处理中的还款记录");
                this.insertUserTransaction(repayQuery.getBidId(), paymentId, bluePayRequest.getPrice(), repayQuery.getRepaymentType());
            }
        }
        // 过滤失败直接修改交易记录
        if (!status.equals(BluePayStatusEnum.BLUE_PAY_COMPLETE.getKey())) {
            transactionService.updateUserTranState(paymentId, TransactionTypeEnum.FAIL_RANSACTION.getCode().byteValue());
            if (BANK.equals(interfacetype)) {
                // 还款失败还需要修改va码对应状态
                this.updateUserTransactionRepay(UserTransactionRepay.builder().paymentId(paymentId).status(TransactionTypeEnum.FAIL_RANSACTION.getCode().byteValue()).build());
            }
            log.info("MQ交易处理失败：{}，处理终止", BluePayStatusEnum.getValueDesc(status));
            return;
        }
        log.info("MQ交易处理成功，下面进行本地交易处理");
        // 查询是否有交易记录
        UserTransaction loanTransacQuery = transactionService.findUserTranByPaymentId(paymentId, interfacetype.equals(BANK) ? TransactionTypeEnum.OUT.getCode().byteValue() : TransactionTypeEnum.IN.getCode().byteValue());
        // 获取交易id 判断是否合法
        if (ObjectUtils.isEmpty(loanTransacQuery)) {
            log.info("本地无此交易信息，paymentId：{}，处理终止", paymentId);
            return;
        }
        // 判断这个交易是否是 交易中
        if (loanTransacQuery.getStatus().intValue() != TransactionTypeEnum.IN_RANSACTION.getCode()) {
            log.info("本地当前交易状态：{}，不是交易中状态，处理终止", loanTransacQuery.getStatus());
            return;
        }
        // 本地处理放款
        if (CASHOUT.equals(interfacetype)) {
            // 查询相应的推送任务信息
            Long tempId = tempTableRepository.findTempTableByBidNoAndName(loanTransacQuery.getBidId(), ComConsts.PUSH_BANK_TASK);
            // 修改交易信息
            transactionService.loanSuccess(loanTransacQuery, tempId);
            log.info("本地放款交易处理成功");
        }
        // 本地处理还款
        if (BANK.equals(interfacetype)) {
            // 走信息流
            this.repaymentSuccess(loanTransacQuery, paymentId);
            log.info("本地还款交易处理成功");
        }
    }
}