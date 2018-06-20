package com.hzed.easyget.application.service;

import com.google.common.collect.Lists;
import com.hzed.easyget.application.enums.JobStatusEnum;
import com.hzed.easyget.application.enums.TransactionTypeEnum;
import com.hzed.easyget.controller.model.LoanTransactionRequest;
import com.hzed.easyget.infrastructure.consts.ComConsts;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.model.PayResponse;
import com.hzed.easyget.infrastructure.repository.BidRepository;
import com.hzed.easyget.infrastructure.repository.RepayInfoFlowJobRepository;
import com.hzed.easyget.infrastructure.repository.TempTableRepository;
import com.hzed.easyget.infrastructure.utils.MdcUtil;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.RepayInfoFlowJob;
import com.hzed.easyget.persistence.auto.entity.TempTable;
import com.hzed.easyget.persistence.ext.entity.BidExt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hfj
 * @date 2018/6/7
 */

@Slf4j
@Service
public class JobService {
    @Autowired
    private BidRepository bidRepository;
    @Autowired
    private TempTableRepository tempTableRepository;
    @Autowired
    private RepayInfoFlowJobRepository repayInfoFlowJobRepository;
    @Autowired
    private RepayService repayService;
    @Autowired
    private TransactionService transactionService;

    /**
     * 推风控
     */
    public void pushBid() {
        //关联中间表，拿到所有推送资产的ids
        List<BidExt> bids = bidRepository.gitBidsToPush();
        if (bids.size() <= 0 || bids.isEmpty()) {
            return;
        }
        for (BidExt bidExt : bids) {
            Long tempId = IdentifierGenerator.nextId();
            //推送bid写入中间表
            tempTableRepository.insertJob(TempTable.builder().id(tempId).relaseId(bidExt.getBidId()).jobName(ComConsts.PUSH_RISK_TASK).remark("推送资产").reRunTimes((byte) 1).build());
            try {
                // TODO 推送-调风控接口

            } catch (Exception ex) {
                ex.printStackTrace();
                tempTableRepository.upDateTemp(TempTable.builder().id(tempId).createTime(LocalDateTime.now()).remark("推送失败：" + ex.getMessage()).build());
            }
        }
    }

    /**
     * 还款走信息流
     */
    public void repayInfoFlow() {
        List<RepayInfoFlowJob> jobList = repayInfoFlowJobRepository.findJobList(Lists.newArrayList((byte) 1, (byte) 2), 2);
        if (ObjectUtils.isEmpty(jobList)) {
            log.info("没有需要走还款信息流的数据");
            return;
        }

        jobList.forEach(repayjob -> {
            MdcUtil.putTrace();
            try {
                // 走信息流
                Long bidId = repayjob.getBidId();
                BigDecimal repaymentAmount = repayjob.getRepaymentAmount();
                LocalDateTime realRepaymentTime = repayjob.getRealRepaymentTime();
                repayService.repayInformationFlow(bidId, repaymentAmount, realRepaymentTime, repayjob.getTransactionId(), repayjob);
            } catch (Exception e) {
                log.error("标的{}走还款信息流失败", repayjob.getBidId());

                RepayInfoFlowJob jobUpdate = new RepayInfoFlowJob();
                jobUpdate.setId(repayjob.getId());
                jobUpdate.setStatus(JobStatusEnum.FALI.getCode().byteValue());
                jobUpdate.setTimes((byte) (repayjob.getTimes().intValue() + 1));
                jobUpdate.setUpdateTime(LocalDateTime.now());
                jobUpdate.setRemark(e.getMessage());
                repayInfoFlowJobRepository.update(jobUpdate);
            }
        });
    }

    /**
     * 银行放款
     */
    public void bankLoan() {
        //关联中间表查出要放款的标的
        List<BidExt> bidList = bidRepository.findBankLoanBids();
        if (bidList == null || bidList.isEmpty()) {
            return;
        }
        bidList.forEach(bid -> {
            log.info("放款任务开始，标的id{}", bid.getBidId());
            //插入中间表
            Long tempId = IdentifierGenerator.nextId();
            tempTableRepository.insertJob(TempTable.builder().id(tempId).jobName(ComConsts.PUSH_BANK_TASK).relaseId(bid.getBidId()).remark("放款").createTime(LocalDateTime.now()).reRunTimes((byte) 1).build());
            LoanTransactionRequest loan = bidRepository.findLoanTransaction(bid.getBidId());
            if (!ObjectUtils.isEmpty(loan)) {
                //交易编号
                loan.setTransactionId(IdentifierGenerator.nextSeqNo());
                //交易流水
                loan.setRequestNo(tempId.toString());
                //收款人所在国
                loan.setPayeeCountry("ID");
                //TODO 调放款接口
                PayResponse response = transactionService.loanTransaction(loan);
                if (response.getCode().equals(BizCodeEnum.SUCCESS.getCode())) {
                    //TODO 如果交易放款成功 直接继续
                    transactionService.lendingCallback(bid.getBidId(), tempId, loan.getTransactionId(), TransactionTypeEnum.SUCCESS_RANSACTION.getCode().byteValue(), LocalDateTime.now());
                } else {
                    //TODO 若状态是放款中 还款中在交易表中插入一条 未完成记录
                    transactionService.insertUsrTransactionInfo(bid.getBidId(), loan.getTransactionId(), TransactionTypeEnum.IN_RANSACTION.getCode().byteValue(), null);
                }
            }
        });


    }

}
