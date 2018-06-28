package com.hzed.easyget.application.service;

import com.google.common.collect.Lists;
import com.hzed.easyget.application.enums.*;
import com.hzed.easyget.application.service.product.ProductEnum;
import com.hzed.easyget.application.service.product.ProductFactory;
import com.hzed.easyget.application.service.product.ProductService;
import com.hzed.easyget.application.service.product.model.AbstractProduct;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.exception.WarnException;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.model.PayResponse;
import com.hzed.easyget.infrastructure.repository.*;
import com.hzed.easyget.infrastructure.utils.Arith;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
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

    private static final String TIMEOUT = "TIMEOUT";
    private static final List<String> LISTCODE = Arrays.asList(BizCodeEnum.PROCESS_LENDING.getCode(), BizCodeEnum.SUCCESS.getCode(), BizCodeEnum.REPAYMENTS.getCode());

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
     * 还款页面标的详情
     *
     * @param amount 还款金额
     * @param bidId  标id
     * @param flag   是否全部还清
     * @return
     */
    public PaymentIdResponse findloanManagResponse(BigDecimal amount, Long bidId, boolean flag) {
        //查询标的信息
        Bid bid = bidRepository.findByIdWithExp(bidId);
        //先查询当天是否有交易记录
        UserTransaction tranceQuery = userTransactionRepository.findOldTrance(bidId, TransactionTypeEnum.OUT.getCode().byteValue(), TransactionTypeEnum.INIT_RANSACTION.getCode().byteValue(), flag ? TransactionTypeEnum.ALL_CLEAR.getCode().byteValue() : TransactionTypeEnum.PARTIAL_CLEARANCE.getCode().byteValue());

        if (!ObjectUtils.isEmpty(tranceQuery)) {
            return new PaymentIdResponse(tranceQuery.getId());
        }

        // 首次进入 生成交易记录
        UserTransaction tranceInsert = UserTransaction.builder()
                .id(IdentifierGenerator.nextId())
                .userId(bid.getUserId())
                .paymentId(IdentifierGenerator.nextSeqNo())
                .bank(bid.getInBank())
                .account(bid.getInAccount())
                .type(TransactionTypeEnum.OUT.getCode().byteValue())
                .amount(amount)
                .bidId(bidId)
                .status(TransactionTypeEnum.IN_RANSACTION.getCode().byteValue())
                .repaymentType(flag ? TransactionTypeEnum.ALL_CLEAR.getCode().byteValue() : TransactionTypeEnum.PARTIAL_CLEARANCE.getCode().byteValue())
                .remark(flag ? "全部还款" : "部分还款").build();
        userTransactionRepository.insertSelective(tranceInsert);

        return new PaymentIdResponse(tranceInsert.getId());

    }

    /**
     * 确认转账 修改确认时间 上传凭证
     *
     * @param request 请求对象
     * @return 响应对象
     */
    public PayResponse repayment(RepaymentRequest request) throws Exception {
        //先查询交易信息比对数据
        UserTransaction transactionQuery = userTransactionRepository.findUserTranById(request.getPayId());
        if (ObjectUtils.isEmpty(transactionQuery)) {
            return PayResponse.getFailResponse();
        }
        if (0 != transactionQuery.getAmount().compareTo(request.getAmount())) {
            return PayResponse.getFailResponse();
        }
        //凭证图片与后缀数量必须相等
        if (request.getBase64Imgs().length != request.getPicSuffixs().length) {
            return PayResponse.getFailResponse();
        }
        //保存凭证pic
        for (int i = 0; i < request.getBase64Imgs().length; i++) {
            String picUrl = fileService.uploadBase64Img(request.getBase64Imgs()[i], request.getPicSuffixs()[i]);
            //向凭证表添加
            UserTransactionPic repayPicInsert = UserTransactionPic.builder()
                    .id(IdentifierGenerator.nextId())
                    .evidencePicUrl(picUrl)
//                    .transactionId(transactionQuery.getId())
                    .createTime(LocalDateTime.now())
                    .build();
            repayRepository.picinsertSelective(repayPicInsert);
        }
        //修改交易的确认时间
        PayResponse response = new PayResponse();
        LocalDateTime time = LocalDateTime.now();
//        repayRepository.updateByPrimaryKey(UserTransaction.builder().id(transactionQuery.getId()).confirmTime(time).build());
        response.setConfirmTime(DateUtil.localDateTimeToTimestamp(time));
        response.setCode(BizCodeEnum.SUCCESS.getCode());
        response.setPayId(transactionQuery.getId());
        return response;
    }

    /**
     * 刷新还款结果
     *
     * @param payId    交易流水id
     * @param isExpire 是否计时完成
     * @return 响应对象
     */
    public PayMentResponse refreshResult(Long payId, boolean isExpire) {
        // 先查询交易信息比对数据
        UserTransaction transactionQuery = userTransactionRepository.findUserTranById(payId);
        if (ObjectUtils.isEmpty(transactionQuery)) {
            throw new ComBizException(BizCodeEnum.USERTRANSACTION_ERROR);
        }
        // 如果倒计时到期 直接处理失败
        PayMentResponse response = new PayMentResponse();
        response.setPayId(payId);
        if (isExpire) {
            UserTransaction transactionUpdate = UserTransaction.builder()
                    .id(payId)
                    .status(TransactionTypeEnum.FAIL_RANSACTION.getCode().byteValue()).build();
            userTransactionRepository.transactionUpdateByKey(transactionUpdate);
            response.setStatus(TransactionTypeEnum.FAIL_RANSACTION.getCode().toString());
            return response;
        }
        response.setBidId(transactionQuery.getBidId());
        response.setStatus(transactionQuery.getStatus().toString());
        return response;
    }

    /**
     * 查看还款信息
     * TODO 待改造
     *
     * @param payId 交易流水id
     * @return 还款信息
     */
    public LoanManagResponse loanManagInfo(Long payId) {
        //先查询是否有交易记录
        UserTransaction tranceQuery = userTransactionRepository.findOldTrance(payId, TransactionTypeEnum.OUT.getCode().byteValue(), TransactionTypeEnum.INIT_RANSACTION.getCode().byteValue());
        if (ObjectUtils.isEmpty(tranceQuery)) {
            throw new ComBizException(BizCodeEnum.USERTRANSACTION_ERROR);
        }
        LocalDateTime repaymentTime = repayRepository.findRepaymentTime(tranceQuery.getBidId());
        LoanManagResponse managResponse = new LoanManagResponse();
        UserTransactionRepay vaCode = repayRepository.getVaCode(tranceQuery.getId());
        if (!ObjectUtils.isEmpty(vaCode)) {
            managResponse.setVaCodel(vaCode.getVa());
            managResponse.setCreateTime(DateUtil.localDateTimeToTimestamp(vaCode.getVaCreateTime()));
            managResponse.setMode(vaCode.getMode());
        }
        managResponse.setAmount(tranceQuery.getAmount());
        managResponse.setPayId(tranceQuery.getId());
        managResponse.setRepaymentTime(DateUtil.localDateTimeToTimestamp(repaymentTime));
        return managResponse;
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
            throw new ComBizException(BizCodeEnum.UPLOAD_PIC_FAIL);
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
}