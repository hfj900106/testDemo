package com.hzed.easyget.application.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.hzed.easyget.application.enums.*;
import com.hzed.easyget.application.service.product.ProductFactory;
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
import com.hzed.easyget.infrastructure.utils.id.IDGenerator;
import com.hzed.easyget.persistence.auto.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
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
    @Autowired
    private SmsService smsService;
    @Autowired
    private UserRepository userRepository;

    public RepayListResponse repaidList(RepayListRequest request) {
        RepayListResponse repayListResponse = new RepayListResponse();


        GlobalUser globalUser = RequestUtil.getGlobalUser();
        Long userId = globalUser.getUserId();
        List<Bid> bidList = bidRepository.findPageByUserIdAndStatus(userId, Lists.newArrayList(BidStatusEnum.REPAYMENT.getCode().byteValue(), BidStatusEnum.CLEARED.getCode().byteValue()), request.getPageModel());
        // 没有借款记录
        if (bidList == null || bidList.isEmpty()) {
            return repayListResponse;
        }
        List<RepaymentResponse> repaymentResponseList = Lists.newArrayList();
        repayListResponse.setLoanAmount(BigDecimal.ZERO);
        for (Bid bid : bidList) {
            RepaymentResponse repaymentResponse = new RepaymentResponse();
            Long bidId = bid.getId();
            BidProgress bidProgress = bidProgressRepository.findByBidIdAndType(bidId, BidProgressTypeEnum.CLEAR.getCode().byteValue());
            Bill bill = billRepository.findByBid(bidId);


            // 已结清
            if (BidStatusEnum.CLEARED.getCode().byteValue() == bid.getStatus().byteValue()) {
                repaymentResponse.setRepayTime(DateUtil.localDateTimeToTimestamp(bidProgress.getHandleTime()));
                repaymentResponse.setStatus(RepayStatusEnum.CLEAR_REPAY.getCode().intValue());
                //实还总额
                repaymentResponse.setRepayAmount(bill.getRealRepaymentAmount());
            }
            // 未结清
            else {
                // 放款金额
                repayListResponse.setLoanAmount(bid.getLoanAmount());

                // 查询应还时间与当前时间对比，大于当前时间表示逾期，小于等于表示没到期
                int days = DateUtil.daysBetweenNoHMS(bill.getRepaymentTime(), LocalDateTime.now());

                // 逾期未结清
                if (days > 0) {
                    repaymentResponse.setStatus(RepayStatusEnum.OVDUE_UN_REPAY.getCode().intValue());
                    repaymentResponse.setDays(days);
                }
                // 正常未结清
                else {
                    repaymentResponse.setStatus(RepayStatusEnum.UN_REPAY.getCode().intValue());
                    int days1 = DateUtil.daysBetweenNoHMS(LocalDateTime.now(), bill.getRepaymentTime());
                    repaymentResponse.setDays(days1);
                }

                //应该总额
                repaymentResponse.setRepayAmount(comService.getBidNoRepayFee(bidId, LocalDateTime.now()));
            }

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
        BigDecimal totalRepayAmount;
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
            repayDetailResponse.setRepayTime(DateUtil.localDateTimeToStr2(bill.getRepaymentTime()));
        }

        // 还款状态
        if ((BidStatusEnum.CLEARED.getCode().toString()).equals(bid.getStatus().toString())) {
            repayDetailResponse.setStatus(RepayStatusEnum.CLEAR_REPAY.getCode());
            totalRepayAmount = bill.getRealRepaymentAmount();
            repayDetailResponse.setPeriod(bid.getPeriod());
        } else {
            int days = DateUtil.daysBetweenNoHMS(bill.getRepaymentTime(), LocalDateTime.now());
            repayDetailResponse.setStatus(days > 0 ? RepayStatusEnum.OVDUE_UN_REPAY.getCode() : RepayStatusEnum.UN_REPAY.getCode());
            repayDetailResponse.setPeriod(days);
            // 标的待还总费用
            totalRepayAmount = comService.getBidNoRepayFee(bidId, LocalDateTime.now());

        }

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
        long progressId = IDGenerator.nextId();
        progressInsert.setId(progressId);
        progressInsert.setBidId(bidId);
        progressInsert.setType(BidProgressTypeEnum.REPAY.getCode().byteValue());
        progressInsert.setHandleTime(realRepaymentTime);
        progressInsert.setHandleResult("用户还款：" + repayAmount);
        bidProgressRepository.insert(progressInsert);
        // 待还账单
        Bill billBefore = billRepository.findAllBillByBidIdWithExp(bidId).get(0);
        // 还账单操作
        repayBill(billBefore, repayAmount, transactionId, realRepaymentTime);
        // 查询用户手机
        Bid bid = bidRepository.findByIdWithExp(bidId);
        User user = userRepository.findById(bid.getUserId());
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

            // 更新上一步的进度为结清
            BidProgress progressUpdate = new BidProgress();
            progressUpdate.setId(progressId);
            progressUpdate.setType(BidProgressTypeEnum.CLEAR.getCode().byteValue());
            progressUpdate.setHandleResult("用户结清：" + repayAmount);
            progressUpdate.setRemark("结清");
            bidProgressRepository.update(progressUpdate);
            // 全部结清 短信通知
            smsService.repaymentNotice(repayAmount, user.getMobileAccount(), null);
        } else {
            // 部分结清 短信通知
            smsService.repaymentNotice(repayAmount, user.getMobileAccount(), bidId);
        }

        // 如果从定时任务进来则更新定时任务为处理成功
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
            billUpdate.setSettlementTime(realRepaymentTime);
            // 逾期结清还是正常结清
            billUpdate.setStatus(DateUtil.daysBetweenNoHMS(bill.getRepaymentTime(), realRepaymentTime) > 0 ?
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
        userRepayment.setId(IDGenerator.nextId());
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
                ledgerInsert.setId(IDGenerator.nextId());
                ledgerInsert.setBillId(billId);
                ledgerInsert.setRepaymentTime(realRepaymentTime);
                ledgerInsert.setRepaymentAmount(billItemNoRepay);
                ledgerInsert.setRepaymentItem(item.byteValue());
                ledgerInsert.setRealRepaymentTime(realRepaymentTime);
                ledgerInsert.setRealRepaymentAmount(repayAmountNow);
                billLedgerRepository.insert(ledgerInsert);
            }
            // 有逾期费记录则修改
            else {
                BillLedger ledgerUpdate = new BillLedger();
                ledgerUpdate.setId(ledger.getId());
                ledgerUpdate.setRepaymentTime(realRepaymentTime);
                ledgerUpdate.setRepaymentAmount(billItemNoRepay);
                ledgerUpdate.setRealRepaymentTime(realRepaymentTime);
                ledgerUpdate.setRealRepaymentAmount(repayAmountNow);
                ledgerUpdate.setUpdateTime(LocalDateTime.now());
                billLedgerRepository.update(ledgerUpdate);
            }
        }
        // 其他台账做更新操作(本金+尾款)
        else {
            if (ledger == null) {
                throw new ComBizException(BizCodeEnum.ILLEGAL_LEDGER_TYPE, new Object[]{String.valueOf(billId), item});
            }

            BillLedger ledgerUpdate = new BillLedger();
            ledgerUpdate.setId(ledger.getId());
            ledgerUpdate.setRealRepaymentTime(realRepaymentTime);
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
        // 相关账单
        Bid bid = bidRepository.findByIdWithExp(bidId);
        //获取产品最小还款值
        BigDecimal minRepayAmount = ProductFactory.getProduct().createProduct(bid.getLoanAmount(), bid.getPeriod()).getMinRepayAmount();
        if (ObjectUtils.isEmpty(minRepayAmount)) {
            log.error("找不到产品的最小还款金额");
            throw new WarnException(BizCodeEnum.UNKNOWN_EXCEPTION);
        }
        return RepayPartDetailResponse.builder().totalAmount(String.valueOf(bidNoRepay)).minRepayAmount(String.valueOf(minRepayAmount)).inAccount(bid.getInAccount()).build();
    }

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
        // 部分还款
        if (!flag) {
            // 获取产品最小还款值
            BigDecimal minRepayAmount = ProductFactory.getProduct().createProduct(bid.getLoanAmount(), bid.getPeriod()).getMinRepayAmount();
            if (ObjectUtils.isEmpty(minRepayAmount)) {
                log.error("找不到产品的最小还款金额");
                throw new WarnException(BizCodeEnum.UNKNOWN_EXCEPTION);
            }
            // 获取应还金额
            BigDecimal repayFee = comService.getBidNoRepayFee(bid.getId(), LocalDateTime.now());
            // 剩余应还金额小于最小还款额
            log.debug("还款金额{}", repayFee);
            BigDecimal retain = repayFee.subtract(amount);
            log.debug("剩余金额金额{}", retain);
            if (Arith.greaterThenZero(retain) && retain.compareTo(minRepayAmount) < 0) {
                throw new WarnException(BizCodeEnum.CLEAR_ONCE);
            }
        }
        PaymentIdResponse response = new PaymentIdResponse();
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
        // 先查询数据库 是否存在没过期的还款码
        List<Byte> bytes = Arrays.asList(TransactionTypeEnum.SUCCESS_RANSACTION.getCode().byteValue(), TransactionTypeEnum.FAIL_RANSACTION.getCode().byteValue());
        UserTransactionRepay repayQuery = repayRepository.getVaCodeByParmers(request.getBidId(), request.getAmount(), bytes, request.getMode());
        TransactionVaResponse vaResponse = new TransactionVaResponse();
        if (!ObjectUtils.isEmpty(repayQuery)) {
            vaResponse.setExpireTime(DateUtil.localDateTimeToTimestamp(repayQuery.getVaExpireTime()));
            vaResponse.setVaCodel(repayQuery.getVa());
            vaResponse.setMode(repayQuery.getMode());
            return vaResponse;
        }
        // 交易单号
        String paymentId = IDGenerator.nextSeqNo();
        //bluepay接口调用
        PayResponse response = bluePayService.bluePaymentCode(request.getMode(), request.getAmount(), paymentId);
        // 解析返回信息
        String paymentCode = JSON.parseObject(response.getData()).getString("paymentCode");
        log.info("获取还款码，bluepay返回还款码：{}", paymentCode);
        LocalDateTime createTime = LocalDateTime.now();
        UserTransactionRepay repayInsert = UserTransactionRepay.builder()
                .id(IDGenerator.nextId())
                .bidId(request.getBidId())
                .paymentId(paymentId)
                .amount(request.getAmount())
                .repaymentTime(createTime)
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
                .id(IDGenerator.nextId())
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
                e.printStackTrace();
                throw new ComBizException(BizCodeEnum.UPLOAD_PIC_FAIL);
            }
            UserTransactionPic repayPicInsert = UserTransactionPic.builder()
                    .evidencePicUrl(picUrl)
                    .va(request.getVa().replace(" ", ""))
                    .bidId(request.getBidId())
                    .mode(request.getMode())
                    .build();
            userTransactionPicList.add(repayPicInsert);
        }
        userTransactionPicRepository.batchInsert(userTransactionPicList);

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
     * mq处理放款/还款回调业务
     *
     * @param bluePayRequest mq回调报文
     */
    @Transactional(rollbackFor = Exception.class)
    public void mqCallback(BluePayRequest bluePayRequest) {
        String requestStr = JSON.toJSONString(bluePayRequest);
        log.info("详细返回信息：{}", requestStr);
        // 参数校验
        ValidatorUtil.validateWithNull(bluePayRequest);
        // 返回的状态
        String status = bluePayRequest.getStatus();
        // 交易ID
        String paymentId = bluePayRequest.getT_id().trim();
        // 放还款类型
        String interfacetype = bluePayRequest.getInterfacetype();

        if (CASHOUT.equals(interfacetype)) {
            log.info("当前交易类型：放款");
            loanMQ(paymentId, status, requestStr);
        } else if (BANK.equals(interfacetype)) {
            log.info("当前交易类型：还款");
            repayMQ(paymentId, bluePayRequest.getPrice(), status, requestStr);
        } else {
            log.error("未知的交易类型：{}，处理终止", interfacetype);
        }
    }

    /**
     * MQ放款回调处理
     *
     * @param paymentId  交易ID
     * @param status     mq处理状态
     * @param requestStr 请求json字符串
     */
    private void loanMQ(String paymentId, String status, String requestStr) {
        // 处理中状态不处理
        if (status.equals(BluePayStatusEnum.OK.getKey())) {
            log.info("MQ交易正在处理中，处理终止");
            return;
        }
        // 交易失败处理
        if (!status.equals(BluePayStatusEnum.BLUE_PAY_COMPLETE.getKey())) {
            // 修改交易记录，交易失败记录原因，存code
            transactionService.updateUserTranState(paymentId, TransactionTypeEnum.FAIL_RANSACTION.getCode().byteValue(), status);
            // 放款失败 更新标状态为：7 放款失败
            UserTransaction transaction = userTransactionRepository.findUserTranByPaymentId(paymentId, (byte) 1);
            Long bidId = transaction.getBidId();
            Bid bidUpdate = new Bid();
            bidUpdate.setId(bidId);
            bidUpdate.setStatus(BidStatusEnum.LOAN_FAIL.getCode().byteValue());
            bidUpdate.setUpdateTime(LocalDateTime.now());
            bidRepository.update(bidUpdate);
            log.error("标的：{} 放款失败。MQ交易处理失败，处理终止。回调报文：{}，错误信息：{}", bidId, requestStr, BluePayStatusEnum.getValueDesc(status));
            return;
        }
        log.info("MQ交易处理成功，下面进行本地交易处理");
        // 查询是否有交易记录
        UserTransaction transactionQuery = transactionService.findUserTranByPaymentId(paymentId, TransactionTypeEnum.IN.getCode().byteValue());
        // 获取交易id 判断是否合法
        if (ObjectUtils.isEmpty(transactionQuery)) {
            log.error("本地无此交易信息，paymentId：{}，处理终止", paymentId);
            return;
        }
        // 判断这个交易是否是 交易中
        if (transactionQuery.getStatus().intValue() != TransactionTypeEnum.IN_RANSACTION.getCode().intValue()) {
            log.error("本地当前交易状态：{}，不是交易中状态，处理终止", transactionQuery.getStatus());
            return;
        }
        // 本地处理放款
        // 查询相应的推送任务信息
        Long tempId = tempTableRepository.findTempTableByBidNoAndNameWithExp(transactionQuery.getBidId(), ComConsts.PUSH_BANK_TASK);
        // 修改交易信息
        transactionService.loanSuccess(transactionQuery, tempId);
        log.info("本地放款交易处理成功");
    }

    /**
     * MQ还款回调处理
     *
     * @param paymentId  交易ID
     * @param price      交易金额
     * @param status     mq处理状态
     * @param requestStr 请求json字符串
     */
    private void repayMQ(String paymentId, Integer price, String status, String requestStr) {
        // 处理中状态不处理
        if (status.equals(BluePayStatusEnum.OK.getKey())) {
            log.info("MQ交易正在处理中，处理终止");
            return;
        }
        // 查询是否有对应的va码记录
        UserTransactionRepay repayQuery = repayRepository.findRepayInfoByPaymentId(paymentId);
        Long bidId = repayQuery.getBidId();
        if (ObjectUtils.isEmpty(repayQuery)) {
            log.error("还款交易没有对应的va码记录，处理终止");
            return;
        }
        // 查询本地是否有还款交易记录，没有交易就要先插入一条
        UserTransaction repayTransacQuery = userTransactionRepository.findUserTranByPaymentId(paymentId, TransactionTypeEnum.OUT.getCode().byteValue());
        if (ObjectUtils.isEmpty(repayTransacQuery)) {
            log.info("发现还款码，初始化处理中的还款记录");
            //查询标的信息
            Bid bid = bidRepository.findByIdWithExp(bidId);
            UserTransaction transactionInsert = UserTransaction.builder()
                    .id(IDGenerator.nextId())
                    .status(TransactionTypeEnum.IN_RANSACTION.getCode().byteValue())
                    .paymentId(paymentId)
                    .account(bid.getInAccount())
                    .bank(bid.getInBank())
                    .amount(BigDecimal.valueOf(price))
                    .type(TransactionTypeEnum.OUT.getCode().byteValue())
                    .repaymentType(repayQuery.getRepaymentType())
                    .bidId(bidId)
                    .userId(bid.getUserId())
                    .build();
            userTransactionRepository.insertSelective(transactionInsert);
        }
        // 交易失败处理
        if (!status.equals(BluePayStatusEnum.BLUE_PAY_COMPLETE.getKey())) {
            // 修改交易记录，交易失败记录原因，存code
            transactionService.updateUserTranState(paymentId, TransactionTypeEnum.FAIL_RANSACTION.getCode().byteValue(), status);
            // 还款失败 修改va码对应状态
            UserTransactionRepay repayUpdate = UserTransactionRepay.builder().paymentId(paymentId).status(TransactionTypeEnum.FAIL_RANSACTION.getCode().byteValue()).build();
            repayRepository.updateUserTransactionRepay(repayUpdate);
            log.error("标的：{} 还款失败。MQ交易处理失败，处理终止。回调报文：{}，错误信息：{}", bidId, requestStr, BluePayStatusEnum.getValueDesc(status));
            return;
        }
        log.info("MQ交易处理成功，下面进行本地交易处理");
        // 交易成功处理
        // 查询是否有交易记录
        UserTransaction transactionQuery = transactionService.findUserTranByPaymentId(paymentId, TransactionTypeEnum.OUT.getCode().byteValue());
        // 获取交易id 判断是否合法
        if (ObjectUtils.isEmpty(transactionQuery)) {
            log.error("本地无此交易信息，paymentId：{}，处理终止", paymentId);
            return;
        }
        // 判断这个交易是否是 交易中
        if (transactionQuery.getStatus().intValue() != TransactionTypeEnum.IN_RANSACTION.getCode().intValue()) {
            log.error("本地当前交易状态：{}，不是交易中状态，处理终止", transactionQuery.getStatus());
            return;
        }
        // 走信息流
        this.repaymentSuccess(transactionQuery, paymentId);
        log.info("本地还款交易处理成功");
    }

    public RepayProgressResponse getRepayProgress(RepayDetailRequest request) {
        RepayProgressResponse repayProgressResponse = new RepayProgressResponse();

        Long bidId = request.getBidId();
        Bid bid = bidRepository.findById(bidId);
        repayProgressResponse.setLoanAmount(bid.getLoanAmount());
        repayProgressResponse.setPeriod(bid.getPeriod());
        AbstractProduct productInfo = ProductFactory.getProduct().createProduct(bid.getLoanAmount(), bid.getPeriod());
        repayProgressResponse.setTailFree(productInfo.getTailFee());
        repayProgressResponse.setLoanTime(DateUtil.localDateTimeToTimestamp(bid.getCreateTime()));
        BigDecimal totalRepayAmount = productInfo.getTotalRepaymentAmount();
        repayProgressResponse.setTotalRepayAmount(totalRepayAmount);
        repayProgressResponse.setBidStatus(bid.getStatus());
        Bill bill = billRepository.findByBid(bidId);
        // 逾期天数计算 大于当前时间表示逾期，小于等于表示没到期
        int days = DateUtil.daysBetweenNoHMS(bill.getRepaymentTime(), LocalDateTime.now());
        repayProgressResponse.setOverdueDay(days > 0 ? days : 0);
        repayProgressResponse.setOverdueFree(days > 0 ? productInfo.getOverFee(days) : new BigDecimal(0));
        repayProgressResponse.setRepayTime(DateUtil.localDateTimeToTimestamp(bill.getRepaymentTime()));
        repayProgressResponse.setRealRepayAmount(bill.getRealRepaymentAmount());
        repayProgressResponse.setRemainderAmount(totalRepayAmount.subtract(bill.getRealRepaymentAmount().setScale(2, BigDecimal.ROUND_HALF_UP)));
        return repayProgressResponse;
    }
}