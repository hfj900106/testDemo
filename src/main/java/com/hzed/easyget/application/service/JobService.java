package com.hzed.easyget.application.service;

import com.google.common.collect.Lists;
import com.hzed.easyget.application.enums.*;
import com.hzed.easyget.application.service.product.ProductEnum;
import com.hzed.easyget.application.service.product.ProductFactory;
import com.hzed.easyget.application.service.product.ProductService;
import com.hzed.easyget.application.service.product.model.EasyGetPruduct;
import com.hzed.easyget.infrastructure.repository.BidRepository;
import com.hzed.easyget.infrastructure.repository.RepayInfoFlowJobRepository;
import com.hzed.easyget.infrastructure.repository.TempTableRepository;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.*;
import com.hzed.easyget.persistence.ext.entity.BidExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hfj
 * @date 2018/6/7
 */

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
            tempTableRepository.insertJob(TempTable.builder().id(tempId).relaseId(bidExt.getBidId()).jobName("pushBid").remark("推送资产").createTime(LocalDateTime.now()).reRunTimes((byte) 1).build());
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
     * TODO 日志打印
     */
    public void repayInfoFlow() {
        List<RepayInfoFlowJob> jobList = repayInfoFlowJobRepository.findJobList(Lists.newArrayList((byte) 1, (byte) 2), 2);
        if (jobList == null || jobList.isEmpty()) {
            return;
        }

        jobList.forEach(repayjob -> {
            try {
                // 走信息流
                Long bidId = repayjob.getBidId();
                BigDecimal repaymentAmount = repayjob.getRepaymentAmount();
                LocalDateTime realRepaymentTime = repayjob.getRealRepaymentTime();
                repayService.repayInformationFlow(bidId, repaymentAmount, realRepaymentTime, repayjob.getRequestseq(), repayjob);
            } catch (Exception e) {
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
            //插入中间表
            Long tempId = IdentifierGenerator.nextId();
            tempTableRepository.insertJob(TempTable.builder().id(tempId).jobName("bankLoan").relaseId(bid.getBidId()).remark("放款").createTime(LocalDateTime.now()).reRunTimes((byte) 1).build());
            try {
                //TODO 调放款接口
                boolean isSuccess = false;
                //根据返回结果
                if (isSuccess) {
                    Bid bidInfo = bidRepository.findById(bid.getBidId());
                    //改标的状态,砍头息、插入账单、台账、标进度、交易记录表，删除中间表数据
                    //工厂类获取bill和billLedgers
                    ProductService product = ProductFactory.getProduct(ProductEnum.EasyGet);
                    List<Bill> bills = product.createBills(bidInfo);
                    List<BillLedger> billLedgers = product.createBillLedger(bidInfo);

                    //TODO 交易流水号
                    String requestSeq = "";
                    String bank = "";
                    String account = "";
                    UserTransaction transaction = buildUserTransaction(bidInfo.getUserId(), bid.getBidId(), TransactionTypeEnum.IN.getCode().byteValue(), bidInfo.getLoanAmount(), requestSeq, bank, account);

                    tempTableRepository.afterBankLoan(
                            Bid.builder().id(bid.getBidId()).status(BidStatusEnum.REPAYMENT.getCode().byteValue()).auditFee(new EasyGetPruduct(bidInfo.getLoanAmount()).getHeadFee()).updateTime(LocalDateTime.now()).build(),
                            BidProgress.builder().bidId(bid.getBidId()).id(IdentifierGenerator.nextId()).type(BidProgressTypeEnum.LOAN.getCode().byteValue()).handleResult("放款成功").createTime(LocalDateTime.now()).remark("放款").build(),
                            bills.get(0),
                            billLedgers,
                            tempId,
                            transaction
                    );
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                tempTableRepository.upDateTemp(TempTable.builder().id(tempId).createTime(LocalDateTime.now()).remark("放款失败：" + ex.getMessage()).build());
            }


        });


    }

    private UserTransaction buildUserTransaction(Long userId, Long bidId, Byte type, BigDecimal amount, String requestSeq, String bank, String account) {
        UserTransaction transaction = new UserTransaction();
        transaction.setId(IdentifierGenerator.nextId());
        transaction.setUserId(userId);
        transaction.setBidId(bidId);
        transaction.setType(type);
        transaction.setAmount(amount);
        transaction.setRequestSeq(requestSeq);
        transaction.setBank(bank);
        transaction.setAccount(account);
        transaction.setMode((byte) 1);
        transaction.setIsDisplay(true);
        transaction.setCreateTime(LocalDateTime.now());
        transaction.setRemark("放款");
        return transaction;
    }


}
