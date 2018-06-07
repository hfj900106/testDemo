package com.hzed.easyget.application.service;

import com.google.common.collect.Lists;
import com.hzed.easyget.application.enums.*;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.repository.BidRepository;
import com.hzed.easyget.infrastructure.repository.BidProgressRepository;
import com.hzed.easyget.infrastructure.repository.BillLedgerRepository;
import com.hzed.easyget.infrastructure.repository.BillRepository;
import com.hzed.easyget.infrastructure.utils.Arith;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import com.hzed.easyget.persistence.auto.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
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

    private static final String TWENTY_PERCENT = "0.2";

    public RepayListResponse repaidList() {

        GlobalUser globalUser = RequestUtil.getGlobalUser();
        Long userId = globalUser.getUserId();
        List<Bid> bidList = bidRepository.findBStatusByUserId(userId, Lists.newArrayList(BidStatusEnum.REPAYMENT.getCode().byteValue(),BidStatusEnum.CLEARED.getCode().byteValue()));
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
            BidProgress bidProgress = bidProgressRepository.findHandleTimeByBidAndType(bidId, BidProgressTypeEnum.CLEAR.getCode().toString());
            Bill loanBill = billRepository.findByBid(bidId);
            Long billId = loanBill.getId();


            //借款金额
            repaymentResponse.setLoanMount(bid.getLoanAmount().toString());
            //已结清
            if ((BidStatusEnum.CLEARED.getCode().toString()).equals(bid.getStatus().toString())) {
                repaymentResponse.setRepayTime(bidProgress.getHandleTime().toString());
                repaymentResponse.setStatus(String.valueOf(RepayStatusEnum.CLEAR_REPAY.getCode()));
                repaymentResponse.setBid(String.valueOf(bidId));
                repaymentResponseList.add(repaymentResponse);
            } else {//未结清

                //查询应还时间与当前时间对比，大于当前时间:逾期，小于:没到期
                int days =  DateUtil.daysBetween(loanBill.getRepaymentTime(), LocalDateTime.now());
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

                        totalRepayAmount = Arith.add(unRepaymentAmount,totalOverdue);
                    }
                    repaymentResponse.setStatus(RepayStatusEnum.OVDUE_UN_REPAY.getCode().toString());
                    repaymentResponse.setDays(String.valueOf(days));
                } else {//正常未还
                    repaymentResponse.setStatus(RepayStatusEnum.UN_REPAY.getCode().toString());
                    days = DateUtil.daysBetween(LocalDateTime.now(),loanBill.getRepaymentTime());
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

            if(!BillLedgerItemEnum.OVERDUE_FEE.getCode().equals(loanBillLedger.getRepaymentItem())){

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
     * @param billId
     * @return
     */
    private BigDecimal getTotalOverdee(Long billId,int days) {
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
     * 结清全部
     */
    public void repayAll(RepayAllRequest request) {
        Long bidId = request.getBidId();
        comService.getBidNoRepay(bidId);


    }

    /**
     * 部分还款
     */
    public void repayPart(RepayPartRequest request) {
        // TODO 保存 t_loan_transaction_record 用户交易记录表
    }

    private BigDecimal repayOverdueFee() {
        return null;
    }

    /**
     * 还款详情
     * @param request
     * @return
     */
    public RepayDetailResponse repayDetail(RepayDetailRequest request) {
        RepayDetailResponse  repayDetailResponse= new RepayDetailResponse();
        String bidStr = request.getBid();
        long bidId = Long.valueOf(bidStr);
        //获取标的待还总费用
        BigDecimal bidNoRepay = comService.getBidNoRepay(bidId);
        //获取账单待还逾期费
        Bill bill = billRepository.findByBid(bidId);
        BigDecimal billOverFeeNoRepay = comService.getBillOverFeeNoRepay(bill.getId());
        //待应还总额
        BigDecimal totalRepayAmount = bidNoRepay.add(billOverFeeNoRepay);
        //借款期限
        Bid bid = bidRepository.findById(bidId);
        Integer period = bid.getPeriod();
        //贷款时间
        BidProgress bidProgress = bidProgressRepository.findHandleTimeByBidAndType(bidId, BidProgressTypeEnum.LOAN.getCode().toString());
        LocalDateTime loanTime = bidProgress.getHandleTime();
        //还款时间或实际已还时间
        int status = bill.getStatus();
        if(BillStatusEnum.WAIT_CLEAR.getCode().equals(status)){
            repayDetailResponse.setRepayTime(DateUtil.dateToStr(bill.getRepaymentTime()));
        }else{
            repayDetailResponse.setRepayTime(DateUtil.dateToStr(bill.getSettlementTime()));
        }
        //还款状态
        if ((BidStatusEnum.CLEARED.getCode().toString()).equals(bid.getStatus().toString())) {
            repayDetailResponse.setStatus(String.valueOf(RepayStatusEnum.CLEAR_REPAY.getCode()));
        } else {
            int days =  DateUtil.daysBetween(bill.getRepaymentTime(), LocalDateTime.now());
            if (days > 0) {
                repayDetailResponse.setStatus(String.valueOf(RepayStatusEnum.OVDUE_UN_REPAY.getCode()));
            } else {
                repayDetailResponse.setStatus(String.valueOf(RepayStatusEnum.UN_REPAY.getCode()));
            }
        }
        repayDetailResponse.setPeriod(period);
        repayDetailResponse.setTotalRepayAmount(totalRepayAmount.toString());
        repayDetailResponse.setLoanTime(DateUtil.dateToStr(loanTime));
        return repayDetailResponse;

    }
}