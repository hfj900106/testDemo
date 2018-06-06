package com.hzed.easyget.application.service;

import com.google.common.collect.Lists;
import com.hzed.easyget.application.enums.BidStatusEnum;
import com.hzed.easyget.application.enums.LoanBillLedgerEnum;
import com.hzed.easyget.application.enums.ProgressTypeEnum;
import com.hzed.easyget.application.enums.RepayStatusEnum;
import com.hzed.easyget.controller.model.RepayResponse;
import com.hzed.easyget.controller.model.RepaymentResponse;
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
    private BidRepository bidRepository;
    @Autowired
    private BidProgressRepository bidProgressRepository;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BillLedgerRepository billLedgerRepository;

    private static final String TWENTY_PERCENT = "0.2";

    public RepayResponse doRepay() {

        GlobalUser globalUser = RequestUtil.getGlobalUser();
        Long userId = globalUser.getUserId();
        List<Bid> bidList = bidRepository.findBStatusByUserId(userId, Lists.newArrayList((byte) 3,(byte) 4));
        //没有借款记录
        if (bidList.isEmpty() || bidList.size() <= 0) {
            return RepayResponse.builder().build();
        }
        List<RepaymentResponse> repaymentResponseList = Lists.newArrayList();
        RepaymentResponse repaymentResponse = new RepaymentResponse();
        //应还总金额
        BigDecimal totalRepayAmount = new BigDecimal(1);
        for (Bid bid : bidList) {
            Long bidId = bid.getId();
            BidProgress bidProgress = bidProgressRepository.findHandleTimeByBidAndType(bidId, ProgressTypeEnum.CLEAR.getCode());
            Bill loanBill = billRepository.findRepayTimeByBid(bidId);
            Long billId = loanBill.getId();


            //借款金额
            repaymentResponse.setLoanMount(bid.getLoanAmount().toString());
            //已结清
            if (BidStatusEnum.CLEARED.getCode().equals(bid.getStatus())) {
                repaymentResponse.setLoanTime(bidProgress.getHandleTime().toString());
                repaymentResponse.setStatus(String.valueOf(RepayStatusEnum.CLEAR_REPAY.getCode()));

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
                        BillLedger loanBillLedgerOver = billLedgerRepository.findLoanBillLedger(billId, LoanBillLedgerEnum.OVERDUE.getType());
                        BigDecimal realRepayOver = loanBillLedgerOver.getRealRepaymentAmount();

                        //最终逾期费
                        BigDecimal overdue = Arith.sub(totalOverdue, realRepayOver);

                        totalRepayAmount = Arith.add(unRepaymentAmount, overdue);

                    } else {//逾期未还

                        totalRepayAmount = Arith.add(unRepaymentAmount,totalOverdue);
                    }
                    repaymentResponse.setStatus(RepayStatusEnum.OVDUE_UN_REPAY.getCode());
                    repaymentResponse.setDays(String.valueOf(days));
                } else {//正常未还
                    repaymentResponse.setStatus(RepayStatusEnum.UN_REPAY.getCode());
                    days = DateUtil.daysBetween(LocalDateTime.now(),loanBill.getRepaymentTime());
                    repaymentResponse.setDays(String.valueOf(days));
                    totalRepayAmount = unRepaymentAmount;

                }

            }

        }
        repaymentResponseList.add(repaymentResponse);
        return RepayResponse.builder().repaymentInfo(repaymentResponseList).TotalAmount(String.valueOf(totalRepayAmount)).build();

    }

    /**
     * 计算应还总额
     * @param billId
     * @return
     */

    private BigDecimal getRepaymentAmount(Long billId) {
        List<BillLedger> loanBillLedgerList = billLedgerRepository.findTotalAmount(billId);

        BigDecimal totalRepaymentAmount = new BigDecimal(1);
        BigDecimal totalRealRepaymentAmount = new BigDecimal(1);

        for (BillLedger loanBillLedger : loanBillLedgerList) {
            BigDecimal repaymentAmount = loanBillLedger.getRepaymentAmount();
            BigDecimal realRepaymentAmount = loanBillLedger.getRealRepaymentAmount();

            if(!LoanBillLedgerEnum.OVERDUE.getType().equals(loanBillLedger.getRepaymentItem())){

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
        BillLedger loanBillLedgerCorpus = billLedgerRepository.findLoanBillLedger(billId, LoanBillLedgerEnum.CORPUS.getType());
        BigDecimal repaymentCorpus = loanBillLedgerCorpus.getRepaymentAmount();
        BigDecimal realRepaymentCopus = loanBillLedgerCorpus.getRealRepaymentAmount();
        BigDecimal corpus = Arith.sub(repaymentCorpus, realRepaymentCopus);
        BigDecimal day = new BigDecimal(days);
        //总逾期费，本金X利息X逾期天数
        BigDecimal totalOverdueAmount = Arith.mul(corpus, twentyPercent, day);
        return totalOverdueAmount;
    }
}