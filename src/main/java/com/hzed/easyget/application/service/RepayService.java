package com.hzed.easyget.application.service;

import com.google.common.collect.Lists;
import com.hzed.easyget.application.enums.*;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.repository.*;
import com.hzed.easyget.infrastructure.utils.Arith;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import com.hzed.easyget.infrastructure.utils.ThreadLocalUtil;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wuchengwu
 * @date 2018/6/4
 */
@Service
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

    private static final String USER_TRANCATIONID = "userTrancationId";

    private static final String TWENTY_PERCENT = "0.2";

    public RepayListResponse repaidList() {

        GlobalUser globalUser = RequestUtil.getGlobalUser();
        Long userId = globalUser.getUserId();
        List<Bid> bidList = bidRepository.findBStatusByUserId(userId, Lists.newArrayList(BidStatusEnum.REPAYMENT.getCode().byteValue(), BidStatusEnum.CLEARED.getCode().byteValue()));
        //没有借款记录
        if (bidList.isEmpty() || bidList.size() <= 0) {
            return RepayListResponse.builder().build();
        }
        List<RepaymentResponse> repaymentResponseList = Lists.newArrayList();
        RepaymentResponse repaymentResponse = null;
        //应还总金额
        BigDecimal totalRepayAmount = new BigDecimal(0);
        for (Bid bid : bidList) {
            repaymentResponse = new RepaymentResponse();
            Long bidId = bid.getId();
            BidProgress bidProgress = bidProgressRepository.findByBidId(bidId, BidProgressTypeEnum.CLEAR.getCode().byteValue());
            Bill loanBill = billRepository.findByBid(bidId);
            Long billId = loanBill.getId();


            //借款金额
            repaymentResponse.setLoanMount(bid.getLoanAmount().toString());
            //已结清
            if ((BidStatusEnum.CLEARED.getCode().toString()).equals(bid.getStatus().toString())) {
                repaymentResponse.setRepayTime(bidProgress.getHandleTime().toString());
                repaymentResponse.setStatus(String.valueOf(RepayStatusEnum.CLEAR_REPAY.getCode()));
                repaymentResponseList.add(repaymentResponse);
            } else {//未结清

                //查询应还时间与当前时间对比，大于当前时间:逾期，小于:没到期
                int days = DateUtil.daysBetween(loanBill.getRepaymentTime(), LocalDateTime.now());
                //计算总的未还总额
                BigDecimal unRepaymentAmount = getRepaymentAmount(billId);

                //逾期未结清
                if (days > 0) {

                    //计算总逾期费
                    BigDecimal totalOverdue = getTotalOverdee(billId, days);
                    //逾期部分已还
                    if (loanBill.getIsPartialRepayment()) {

                        //已还逾期费
                        BillLedger loanBillLedgerOver = billLedgerRepository.findBillLedgerItemByBillId(billId, BillLedgerItemEnum.OVERDUE_FEE.getCode().byteValue());
                        BigDecimal realRepayOver = loanBillLedgerOver.getRealRepaymentAmount();

                        //最终逾期费
                        BigDecimal overdue = Arith.sub(totalOverdue, realRepayOver);

                        totalRepayAmount = Arith.add(unRepaymentAmount, overdue);

                    } else {//逾期未还

                        totalRepayAmount = Arith.add(unRepaymentAmount, totalOverdue);
                    }
                    repaymentResponse.setStatus(RepayStatusEnum.OVDUE_UN_REPAY.getCode().toString());
                    repaymentResponse.setDays(String.valueOf(days));
                } else {//正常未还
                    repaymentResponse.setStatus(RepayStatusEnum.UN_REPAY.getCode().toString());
                    days = DateUtil.daysBetween(LocalDateTime.now(), loanBill.getRepaymentTime());
                    repaymentResponse.setDays(String.valueOf(days));
                    totalRepayAmount = unRepaymentAmount;

                }
                repaymentResponse.setBid(String.valueOf(bidId));
                repaymentResponseList.add(repaymentResponse);
            }

        }

        return RepayListResponse.builder().repaymentInfo(repaymentResponseList).TotalAmount(String.valueOf(totalRepayAmount)).build();

    }

    /**
     * 计算应还总额
     *
     * @param billId
     * @return
     */

    private BigDecimal getRepaymentAmount(Long billId) {
        List<BillLedger> loanBillLedgerList = billLedgerRepository.findAllBillLedgerByBillId(billId);

        BigDecimal totalRepaymentAmount = new BigDecimal(1);
        BigDecimal totalRealRepaymentAmount = new BigDecimal(1);

        for (BillLedger loanBillLedger : loanBillLedgerList) {
            BigDecimal repaymentAmount = loanBillLedger.getRepaymentAmount();
            BigDecimal realRepaymentAmount = loanBillLedger.getRealRepaymentAmount();

            if (!BillLedgerItemEnum.OVERDUE_FEE.getCode().equals(loanBillLedger.getRepaymentItem())) {

                //总的应还金额
                totalRepaymentAmount = Arith.add(totalRepaymentAmount, repaymentAmount);
                //总的已还金额
                totalRealRepaymentAmount = Arith.add(totalRealRepaymentAmount, realRepaymentAmount);
            }
        }

        //未还金额
        BigDecimal unRepaymentAmount = Arith.sub(totalRepaymentAmount, totalRealRepaymentAmount);
        return unRepaymentAmount;
    }

    /**
     * 计算总逾期费
     *
     * @param billId
     * @return
     */
    private BigDecimal getTotalOverdee(Long billId, int days) {
        //逾期费率
        BigDecimal twentyPercent = new BigDecimal(TWENTY_PERCENT);
        //本金
        BillLedger loanBillLedgerCorpus = billLedgerRepository.findBillLedgerItemByBillId(billId, BillLedgerItemEnum.CORPUS.getCode().byteValue());
        BigDecimal repaymentCorpus = loanBillLedgerCorpus.getRepaymentAmount();
        BigDecimal realRepaymentCopus = loanBillLedgerCorpus.getRealRepaymentAmount();
        BigDecimal corpus = Arith.sub(repaymentCorpus, realRepaymentCopus);
        BigDecimal day = new BigDecimal(days);
        //总逾期费，本金X利息X逾期天数
        BigDecimal totalOverdueAmount = Arith.mul(corpus, twentyPercent, day);
        return totalOverdueAmount;
    }

    /**
     * 还款详情
     *
     * @param request
     * @return
     */
    public RepayDetailResponse repayDetail(RepayDetailRequest request) {
        RepayDetailResponse repayDetailResponse = new RepayDetailResponse();
        String bidStr = request.getBid();
        long bidId = Long.valueOf(bidStr);
        //获取标的待还总费用
        BigDecimal bidNoRepay = comService.getBidNoRepay(bidId, LocalDateTime.now());
        //获取账单待还逾期费
        Bill bill = billRepository.findByBid(bidId);
        BigDecimal billOverFeeNoRepay = comService.getBillOverFeeNoRepay(bill.getId(), LocalDateTime.now());
        //待应还总额
        BigDecimal totalRepayAmount = bidNoRepay.add(billOverFeeNoRepay);
        //借款期限
        Bid bid = bidRepository.findById(bidId);
        Integer period = bid.getPeriod();
        //贷款时间
        BidProgress bidProgress = bidProgressRepository.findByBidId(bidId, BidProgressTypeEnum.LOAN.getCode().byteValue());
        LocalDateTime loanTime = bidProgress.getHandleTime();
        //还款时间或实际已还时间
        int status = bill.getStatus();
        if (BillStatusEnum.WAIT_CLEAR.getCode().equals(status)) {
            repayDetailResponse.setRepayTime(DateUtil.dateToStr(bill.getRepaymentTime()));
        } else {
            repayDetailResponse.setRepayTime(DateUtil.dateToStr(bill.getSettlementTime()));
        }
        repayDetailResponse.setPeriod(period);
        repayDetailResponse.setTotalRepayAmount(totalRepayAmount.toString());
        repayDetailResponse.setLoanTime(DateUtil.dateToStr(loanTime));
        return repayDetailResponse;

    }

    /**
     * 结清全部
     */
    public void repayAll(RepayAllRequest request) {
        Long bidId = request.getBidId();
        comService.getBidNoRepay(bidId, LocalDateTime.now());


    }

    /**
     * 部分还款
     * 保存 t_loan_user_repayment 还款记录表
     * 保存 t_loan_bill_ledger 台账表
     * 保存 t_loan_bill 账单表
     * 保存 t_loan_bid_progress 标的进度表（主要更新还款信息）
     * 保存 t_loan_bid 标的表（主要是更新结清操作，部分还款不用更新）
     * 保存 t_loan_user_transaction 用户交易记录表
     */
    public void repayPart(RepayPartRequest request) {
        Long bidId = request.getBidId();
        BigDecimal repayAmount = request.getRepayAmount();
        Bid bid = bidRepository.findByIdWithExp(bidId);
        // TODO 调用转账交易后的流水号 可以提前生成，可放入threadLocal中
        String requestSeq = String.valueOf(IdentifierGenerator.nextId());

        // 保存 t_loan_user_transaction 用户交易记录表
        long userTrancationId = IdentifierGenerator.nextId();
        // 放入threadLocal后面要用
        ThreadLocalUtil.set(USER_TRANCATIONID, userTrancationId);
        UserTransaction userTransaction = new UserTransaction();
        userTransaction.setId(userTrancationId);
        userTransaction.setUserId(RequestUtil.getGlobalUser().getUserId());
        userTransaction.setBidId(bidId);
        userTransaction.setType(TransactionTypeEnum.OUT.getCode().byteValue());
        userTransaction.setAmount(repayAmount);
        userTransaction.setRequestSeq(requestSeq);
        userTransaction.setBank(bid.getInBank());
        userTransaction.setAccount(bid.getInAccount());
        userTransaction.setMode((byte) 1);
        userTransaction.setIsDisplay(true);
        userTransaction.setCreateTime(LocalDateTime.now());
        userTransactionRepository.insert(userTransaction);

        // 保存 t_loan_bid_progress 标的进度表
        BidProgress progressInsert = new BidProgress();
        progressInsert.setId(IdentifierGenerator.nextId());
        progressInsert.setBidId(bidId);
        progressInsert.setType(BidProgressTypeEnum.REPAY.getCode().byteValue());
        progressInsert.setHandleTime(LocalDateTime.now());
        progressInsert.setHandleResult("用户还款：" + repayAmount);
        progressInsert.setCreateTime(LocalDateTime.now());
        bidProgressRepository.insert(progressInsert);

        // 待还账单
        Bill billBefore = billRepository.findAllBillByBidIdWithExp(bidId).get(0);
        // 还账单操作
        repayBill(billBefore, repayAmount, LocalDateTime.now());

        // 保存 t_loan_bid 标的表（主要是更新结清操作，部分还款不用更新）
        Bill billAfter = billRepository.findAllBillByBidIdWithExp(bidId).get(0);
        if(BillStatusEnum.NORMAL_CLEAR.getCode().equals(billAfter.getStatus())
                || BillStatusEnum.OVERDUE_CLEAR.getCode().equals(billAfter.getStatus())) {
            // 账单已结清 修改标的状态为结清
            Bid bidUpdate = new Bid();
            bidUpdate.setId(bidId);
            bidUpdate.setStatus(BidStatusEnum.CLEARED.getCode().byteValue());
            bidUpdate.setUpdateTime(LocalDateTime.now());
            bidRepository.update(bidUpdate);
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
    private BigDecimal repayBill(Bill bill, BigDecimal repayAmount, LocalDateTime realRepaymentTime) {
        Long billId = bill.getId();
        // 还逾期费
        BigDecimal restAmount1 = repayBillLedger(billId, BillLedgerItemEnum.OVERDUE_FEE.getCode().intValue(), repayAmount, realRepaymentTime);
        // 还尾款
        BigDecimal restAmount2 = repayBillLedger(billId, BillLedgerItemEnum.TAIL_FEE.getCode().intValue(), restAmount1, realRepaymentTime);
        // 还本金
        BigDecimal restAmount3 = repayBillLedger(billId, BillLedgerItemEnum.CORPUS.getCode().intValue(), restAmount2, realRepaymentTime);
        // 本次还款金额
        BigDecimal repayAmountNow = restAmount3.compareTo(BigDecimal.ZERO) >= 0 ? repayAmount.subtract(restAmount3) : repayAmount;
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
    private BigDecimal repayBillLedger(Long billId, Integer item, BigDecimal repayAmount, LocalDateTime realRepaymentTime) {
        // 还款金额为0
        if (repayAmount == null || repayAmount.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }
        // 待还总额
        BigDecimal billItemNoRepay = comService.getBillItemNoRepay(billId, item, realRepaymentTime);
        // 已结清则直接返回还款金额
        if (billItemNoRepay.compareTo(BigDecimal.ZERO) <= 0) {
            return repayAmount;
        }
        // 剩余金额
        BigDecimal restAmount = repayAmount.subtract(billItemNoRepay);
        // 本次还款金额
        BigDecimal repayAmountNow = restAmount.compareTo(BigDecimal.ZERO) <= 0 ? repayAmount : billItemNoRepay;

        // 保存 t_loan_user_repayment 还款记录表
        UserRepayment userRepayment = new UserRepayment();
        userRepayment.setId(IdentifierGenerator.nextId());
        // 从threadLocal里面拿
        userRepayment.setTransactionId(ThreadLocalUtil.get(USER_TRANCATIONID));
        userRepayment.setBillId(billId);
        userRepayment.setRepaymentItem(item.byteValue());
        userRepayment.setRepaymentAmount(billItemNoRepay);
        userRepayment.setRealRepaymentAmount(repayAmountNow);
        userRepayment.setCreateTime(LocalDateTime.now());
        // TODO save

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
                throw new ComBizException(BizCodeEnum.ILLEGAL_PARAM, "账单ID：" + billId + "的台账类型：" + item + "不存在");
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

}