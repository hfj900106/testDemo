package com.hzed.easyget.application.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.hzed.easyget.application.enums.BidStatusEnum;
import com.hzed.easyget.application.enums.JobStatusEnum;
import com.hzed.easyget.application.enums.TransactionTypeEnum;
import com.hzed.easyget.controller.model.LoanTransactionRequest;
import com.hzed.easyget.infrastructure.config.RiskProp;
import com.hzed.easyget.infrastructure.config.SystemProp;
import com.hzed.easyget.infrastructure.config.rest.RestService;
import com.hzed.easyget.infrastructure.consts.ComConsts;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.exception.WarnException;
import com.hzed.easyget.infrastructure.model.PayResponse;
import com.hzed.easyget.infrastructure.model.RiskResponse;
import com.hzed.easyget.infrastructure.repository.*;
import com.hzed.easyget.infrastructure.utils.AesUtil;
import com.hzed.easyget.infrastructure.utils.Arith;
import com.hzed.easyget.infrastructure.utils.MdcUtil;
import com.hzed.easyget.infrastructure.utils.id.IDGenerator;
import com.hzed.easyget.persistence.auto.entity.Bid;
import com.hzed.easyget.persistence.auto.entity.RepayInfoFlowJob;
import com.hzed.easyget.persistence.auto.entity.TempTable;
import com.hzed.easyget.persistence.auto.entity.User;
import com.hzed.easyget.persistence.ext.entity.BidExt;
import com.hzed.easyget.persistence.ext.entity.BillExt;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
    private SaService saService;
    @Autowired
    private RiskProp riskProp;
    @Autowired
    private SystemProp systemProp;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private DictRepository dictRepository;
    @Autowired
    private SmsService smsService;
    @Autowired
    private BluePayService bluePayService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMessageRepository messageRepository;

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
            long tempId = IDGenerator.nextId();
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
                String url = riskProp.getPushBidUrl();
                log.info("请求风控接口，URL：{}, 请求参数：{}", url, JSON.toJSONString(map));
                RiskResponse riskResponse = restService.postJson(url, map, RiskResponse.class);
                log.info("风控返回数据：{}", JSON.toJSONString(riskResponse));

                // 推送风控失败
                if (null == riskResponse || !riskResponse.getHead().getStatus().equals(ComConsts.RISK_OK)) {
                    throw new ComBizException(BizCodeEnum.FAIL_PUSH_RISK);
                }
            } catch (Exception ex) {
                log.info("标ID：{}，推送失败：{}", bidId, ex);
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
                repayService.repayInformationFlow(repayjob.getBidId(), repayjob.getRepaymentAmount(), repayjob.getRealRepaymentTime(), repayjob.getTransactionId(), repayjob);
            } catch (Exception ex) {
                log.error("标的：{} 走还款信息流失败", repayjob.getBidId(), ex);

                RepayInfoFlowJob jobUpdate = new RepayInfoFlowJob();
                jobUpdate.setId(repayjob.getId());
                jobUpdate.setStatus(JobStatusEnum.FALI.getCode().byteValue());
                jobUpdate.setTimes((byte) (repayjob.getTimes().intValue() + 1));
                jobUpdate.setUpdateTime(LocalDateTime.now());
                jobUpdate.setRemark(ex.getMessage());
                repayInfoFlowJobRepository.update(jobUpdate);
            }
        });
    }

    /**
     * 银行放款
     */
    public void bankLoan() {
        // 关联中间表查出要放款的标的
        List<BidExt> bidList = bidRepository.selectBidsToPushOrBankLoan(BidStatusEnum.AUDIT_PASS.getCode().byteValue(), (byte) 1, ComConsts.PUSH_BANK_TASK);
        if (ObjectUtils.isEmpty(bidList)) {
            log.info("没有需要放款的记录");
            return;
        }

        bidList.forEach(bid -> {
            MdcUtil.putTrace();
            Long bidId = bid.getBidId();
            log.info("开始处理标ID：{}", bidId);
            Long tempId = IDGenerator.nextId();
            try {
                // 插入中间表
                TempTable tempTable = TempTable.builder()
                        .id(tempId)
                        .jobName(ComConsts.PUSH_BANK_TASK)
                        .relaseId(bidId)
                        .remark("放款")
                        .reRunTimes((byte) 1)
                        .build();
                tempTableRepository.insertJob(tempTable);

                // 获取bid
                Bid bidQuery = bidRepository.findById(bidId);
                // 获取用户
                User user = userRepository.findById(bid.getUserId());

                LoanTransactionRequest loan = new LoanTransactionRequest();
                // 收款人
                loan.setPayeeName(user.getRealName());
                // 收款人手机号
                loan.setPayeeMsisdn(user.getMobileAccount());
                // 收款人账号
                loan.setPayeeAccount(bidQuery.getInAccount());
                // 收款银行
                loan.setPayeeBankName(bidQuery.getInBank());
                // 放款金额
                loan.setAmount(Arith.sub(bidQuery.getLoanAmount(), bidQuery.getAuditFee()));
                // 交易id
                String transactionId = IDGenerator.nextSeqNo();

                loan.setTransactionId(transactionId);
                // 交易流水号
                loan.setRequestNo(tempId.toString());

                PayResponse response = bluePayService.loanTransaction(loan);

                if (response.getCode().equals(BizCodeEnum.SUCCESS.getCode())) {
                    transactionService.lendingCallback(bidId, tempId, transactionId, TransactionTypeEnum.SUCCESS_RANSACTION.getCode().byteValue(), LocalDateTime.now());
                } else {
                    transactionService.insertUsrTransactionInfo(bidId, transactionId, TransactionTypeEnum.IN_RANSACTION.getCode().byteValue(), null);
                }

            } catch (Exception e) {
                log.error("标ID：{}，放款失败", bidId, e);
                //更新中间表
                TempTable build = TempTable.builder()
                        .id(Long.valueOf(tempId))
                        .updateTime(LocalDateTime.now())
                        .remark("放款失败：" + e.getMessage())
                        .build();
                tempTableRepository.updateTemp(build);
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
    public void saInData() {
        saService.saInData();
    }

    /**
     * 神策借款成功
     */
    public void saLoanSuccess() {
        saService.saLoanSuccess();
    }

    /**
     * 神策还款成功
     */
    public void saRepaymentSuccess() {
        saService.saRepaymentSuccess();
    }

    /**
     * D-2：每天1条，12点半发
     * D-1：每天2条，早上7点半，中午12点半
     */
    public void checkBillD1AndD2(int day) {
        String template = dictRepository.findByCodeAndLanguage(ComConsts.SMS_CONTENT_4, systemProp.getLocal()).getDicValue();
        List<BillExt> bills = billRepository.findUnRepayBillExt(day);
        if (ObjectUtils.isEmpty(bills)) {
            return;
        }
        bills.forEach(billExt -> {
            smsNX(day, billExt.getMobile(), template, 0);
        });

    }

    /**
     * D-0:每天5条，4点、10点、12点半、4点、8点
     */
    public void checkBillD0(int day) {
        String template = dictRepository.findByCodeAndLanguage(ComConsts.SMS_CONTENT_7, systemProp.getLocal()).getDicValue();
        List<BillExt> bills = billRepository.findUnRepayBillExt(day);
        if (ObjectUtils.isEmpty(bills)) {
            return;
        }
        bills.forEach(billExt -> {
            smsNX(day, billExt.getMobile(), template, 1);
        });

    }

    private void smsNX(int day, String mobile, String template, int channel) {
        String title = dictRepository.findByCodeAndLanguage(ComConsts.MESSAGE_TITLE_3, systemProp.getLocal()).getDicValue();
        if (StringUtils.isBlank(template)) {
            log.error("没有配置短信模板");
            throw new WarnException(BizCodeEnum.DICT_NOTEXISTS);
        }
        if (StringUtils.isBlank(title)) {
            log.error("没有配置信息title");
            throw new WarnException(BizCodeEnum.DICT_NOTEXISTS);
        }
        String content = StringUtils.replace(template, "{0}", String.valueOf(day));
        try {
            log.info("发送催账短信-成功，手机号码{}", mobile);

            // 发送及保存短信
            //smsService.sendAndSaveSms(mobile, msg, "短信催账");
            //smsService.sendNX(mobile, content, "短信催账", channel);

            // 通过手机号获取用户id
            Long userId = userRepository.findByMobile(mobile).getId();
            messageRepository.addUserMessage(userId, title, content, "短信催账");
        } catch (Exception ex) {
            log.info("发送催账短信-失败，手机号码{}", mobile);
        }
    }


}
