package com.hzed.easyget.application.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.hzed.easyget.application.enums.JobStatusEnum;
import com.hzed.easyget.application.enums.TransactionTypeEnum;
import com.hzed.easyget.controller.model.LoanTransactionRequest;
import com.hzed.easyget.infrastructure.config.rest.RestService;
import com.hzed.easyget.infrastructure.consts.ComConsts;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.model.PayResponse;
import com.hzed.easyget.infrastructure.model.RiskResponse;
import com.hzed.easyget.infrastructure.repository.*;
import com.hzed.easyget.infrastructure.utils.AesUtil;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.MdcUtil;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.RepayInfoFlowJob;
import com.hzed.easyget.persistence.auto.entity.TempTable;
import com.hzed.easyget.persistence.auto.entity.UserTransaction;
import com.hzed.easyget.persistence.ext.entity.BidExt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private RestService restService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTransactionRepository transactionRepository;
    @Autowired
    private SaService saService;


    /**
     * 风控审核
     */
    public void pushBid() {
        // 关联中间表，拿到所有推送标ids
        List<BidExt> bids = bidRepository.selectBidsToPushOrBankLoan((byte) 1, (byte) 1, ComConsts.PUSH_RISK_TASK);
        if (ObjectUtils.isEmpty(bids)) {
            log.info("暂无待风控审核的标的");
            return;
        }
        bids.forEach(bidExt -> {
            MdcUtil.putTrace();
            Long bidId = bidExt.getBidId();
            log.info("开始处理标ID：{}", bidId);
            long tempId = IdentifierGenerator.nextId();
            int times = 1;
            try {
                TempTable tempTable = tempTableRepository.findTempTableByBidNoAndJobName(bidId, ComConsts.PUSH_RISK_TASK);
                if (ObjectUtils.isEmpty(tempTable)) {
                    //插入中间表
                    TempTable tempInsert = buildTempTableToInsert(tempId, ComConsts.PUSH_RISK_TASK, bidId, "推送资产");
                    tempTableRepository.insertJob(tempInsert);
                } else {
                    tempId = tempTable.getId();
                    times = tempTable.getReRunTimes().intValue() + 1;
                    //更新中间表
                    TempTable tempUpdate = buildTempTableToUpdate(tempId, (byte) times, "推送资产");
                    tempTableRepository.updateTemp(tempUpdate);
                }
                // 推送-调风控接口
                Long timeStamp = System.currentTimeMillis();
                Map<String, Object> map = new HashMap<>(16);
                map.put("sign", AesUtil.aesEncode(bidExt.getUserId(), timeStamp));
                map.put("userId", bidExt.getUserId());
                map.put("timeStamp", timeStamp);
                map.put("bid", bidId);
                //TODO 补充地址
                log.info("请求风控接口，URL：{}, 请求参数：{}", "", JSON.toJSONString(map));

                RiskResponse riskResponse = restService.postJson("http://10.10.20.203:9611/api/risk/auto/antifraud", map, RiskResponse.class);
                log.info("风控返回数据：{}", JSON.toJSONString(riskResponse));

                // 推送风控失败
                if (null == riskResponse || !riskResponse.getHead().getStatus().equals(ComConsts.RISK_OK)) {
                    throw new ComBizException(BizCodeEnum.FAIL_PUSH_RISK);
                }
            } catch (Exception ex) {
                log.info("标ID：{}，推送失败", bidId);
                //更新中间表
                TempTable tempUpdate = buildTempTableToUpdate(tempId, (byte) times, "推送失败");
                tempTableRepository.updateTemp(tempUpdate);
            }
        });
    }

    /**
     * 还款走信息流
     */
    public void repayInfoFlow() {
        List<RepayInfoFlowJob> jobList = repayInfoFlowJobRepository.findJobList(Lists.newArrayList((byte) 1, (byte) 2), 2);
        if (ObjectUtils.isEmpty(jobList)) {
            log.info("没有需要走还款信息流的记录");
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
        List<BidExt> bidList = bidRepository.selectBidsToPushOrBankLoan((byte) 4, (byte) 1, ComConsts.PUSH_BANK_TASK);
        if (ObjectUtils.isEmpty(bidList)) {
            log.info("没有需要放款的记录");
            return;
        }
        bidList.forEach(bid -> {
            MdcUtil.putTrace();
            Long bidId = bid.getBidId();
            log.info("开始处理标ID：{}", bidId);
            Long tempId = IdentifierGenerator.nextId();
            try {
                //插入中间表
                tempTableRepository.insertJob(TempTable.builder().id(tempId).jobName(ComConsts.PUSH_BANK_TASK).relaseId(bidId).remark("放款").reRunTimes((byte) 1).build());
                LoanTransactionRequest loan = bidRepository.findLoanTransaction(bidId);
                if (!ObjectUtils.isEmpty(loan)) {
                    //交易编号
                    loan.setTransactionId(IdentifierGenerator.nextSeqNo());
                    //交易流水
                    loan.setRequestNo(tempId.toString());
                    PayResponse response = transactionService.loanTransaction(loan);
                    if (response.getCode().equals(BizCodeEnum.SUCCESS.getCode())) {
                        transactionService.lendingCallback(bidId, tempId, loan.getTransactionId(), TransactionTypeEnum.SUCCESS_RANSACTION.getCode().byteValue(), LocalDateTime.now());
                    } else {
                        transactionService.insertUsrTransactionInfo(bidId, loan.getTransactionId(), TransactionTypeEnum.IN_RANSACTION.getCode().byteValue(), null);
                    }
                }
            } catch (Exception e) {
                log.error("标ID：{}，放款失败", bidId, e);
                //更新中间表
                tempTableRepository.updateTemp(TempTable.builder().id(Long.valueOf(tempId)).updateTime(LocalDateTime.now()).remark("放款失败：" + e.getMessage()).build());
            }
        });


    }


    /**
     * 处理还款失败
     */
    public void repayFail() {
        //找到要处理的数据
        List<UserTransaction> transactionList = userRepository.findUserTransToUpdateRepayFail(DateUtil.addHour(LocalDateTime.now(), -2));
        if (ObjectUtils.isEmpty(transactionList)) {
            return;
        }
        transactionList.forEach(userTransaction -> {
            MdcUtil.putTrace();
            log.info("标的：{}还款失败处理", userTransaction.getBidId());
            long bidId = userTransaction.getBidId();
            long tempId = IdentifierGenerator.nextId();
            int times = 1;
            try {
                TempTable tempTable = tempTableRepository.findTempTableByBidNoAndJobName(bidId, ComConsts.PUSH_RISK_TASK);
                if (ObjectUtils.isEmpty(tempTable)) {
                    //插入中间表
                    TempTable tempInsert = buildTempTableToInsert(tempId, ComConsts.REPAY_DAIL_TASK, bidId, "还款失败处理-待处理");
                    tempTableRepository.insertJob(tempInsert);
                } else {
                    tempId = tempTable.getId();
                    times = tempTable.getReRunTimes().intValue() + 1;
                    //更新中间表
                    TempTable tempUpdate = buildTempTableToUpdate(tempId, (byte) times, "还款失败处理-待处理");
                    tempTableRepository.updateTemp(tempUpdate);
                }
                userTransaction.setStatus((byte) 3);
                userTransaction.setUpdateTime(LocalDateTime.now());
                transactionRepository.transactionUpdateByKey(userTransaction);

                //处理成功后删除temp表
                tempTableRepository.deleteById(tempId);
            } catch (Exception ex) {
                log.info("标的：{}还款失败处理-失败", userTransaction.getBidId());
                //更新中间表
                TempTable tempUpdate = buildTempTableToUpdate(tempId, (byte) times, "还款失败处理-失败");
                tempTableRepository.updateTemp(tempUpdate);
            }


        });
    }

    private TempTable buildTempTableToInsert(Long tempId, String jobName, Long bidId, String remark) {
        return TempTable.builder().id(tempId).jobName(jobName).relaseId(bidId).remark(remark).reRunTimes((byte) 1).createTime(LocalDateTime.now()).build();
    }

    private TempTable buildTempTableToUpdate(Long tempId, Byte times, String remark) {
        return TempTable.builder().id(tempId).reRunTimes(times).remark(remark).updateTime(LocalDateTime.now()).build();
    }

    /**
     * 神策进件
     */
    public void inData() {
        saService.inData();
    }

    /**
     * 神策借款成功
     */
    public void loanSuccess() {
        saService.loanSuccess();
    }

    /**
     * 神策还款成功
     */
    public void repaymentSuccess() {
        saService.repaymentSuccess();
    }
}
