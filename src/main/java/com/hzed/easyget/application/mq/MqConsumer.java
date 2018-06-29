package com.hzed.easyget.application.mq;

import com.alibaba.fastjson.JSONObject;
import com.hzed.easyget.application.enums.BluePayStatusEnum;
import com.hzed.easyget.application.enums.TransactionTypeEnum;
import com.hzed.easyget.application.service.RepayService;
import com.hzed.easyget.application.service.TransactionService;
import com.hzed.easyget.controller.model.BluePayRequest;
import com.hzed.easyget.infrastructure.consts.ComConsts;
import com.hzed.easyget.infrastructure.repository.BidRepository;
import com.hzed.easyget.infrastructure.repository.TempTableRepository;
import com.hzed.easyget.infrastructure.utils.MdcUtil;
import com.hzed.easyget.infrastructure.utils.ValidatorUtil;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.Bid;
import com.hzed.easyget.persistence.auto.entity.UserTransaction;
import com.hzed.easyget.persistence.auto.entity.UserTransactionRepay;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;

/**
 * 功能概要：放款、收款通知消费接收
 * <p>Title: OrderNotificationConsumer </p>
 *
 * @author madaijun
 * @date 2018年6月13日 下午6:10:40
 */
@Slf4j
@Component
public class MqConsumer implements ChannelAwareMessageListener {

    @Autowired
    private TempTableRepository tempTableRepository;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private RepayService repayService;
    @Autowired
    private BidRepository bidRepository;
    /**
     * 放款标识
     */
    private static final String CASHOUT = "cashout";
    /**
     * 还款标识
     */
    private static final String BANK = "bank";

    @Override
    public void onMessage(Message messageByte, Channel channel) {
        // 记录trace，方便日志追踪
        MdcUtil.putTrace();
        try {
            log.info("============================= MQ交易回调开始 =============================");
            String message = new String(messageByte.getBody(), "UTF-8");
            log.info("MQ交易 放款/还款 回调，详细返回信息{}", message);

            BluePayRequest bluePayRequest = JSONObject.parseObject(message, BluePayRequest.class);

            // 参数校验
            ValidatorUtil.validateWithNull(bluePayRequest);

            // 返回的状态
            String status = bluePayRequest.getStatus();
            // 交易ID
            String paymentId = bluePayRequest.getT_id();
            // 放还款类型
            String interfacetype = bluePayRequest.getInterfacetype();
            log.info("当前交易类型：{}", CASHOUT.equals(interfacetype) ? "放款" : (BANK.equals(interfacetype) ? "还款" : "其他"));

            // 先判断是不是还款
            if (BANK.equals(interfacetype)) {
                //查询是否有对应的va码记录
                UserTransactionRepay repayQuery = repayService.findReayInfoByPayMentId(paymentId);
                if (ObjectUtils.isEmpty(repayQuery)) {
                    log.info("还款交易没有对应的va码记录，处理终止");
                    return;
                }
                // 查询本地是否有还款交易记录
                UserTransaction repayTransacQuery = transactionService.findUserTranByPaymentId(paymentId, TransactionTypeEnum.OUT.getCode().byteValue());
                // 没有交易就要先插入一条
                if (ObjectUtils.isEmpty(repayTransacQuery)) {
                    //查询标的信息
                    Bid bid = bidRepository.findByIdWithExp(repayQuery.getBidId());
                    UserTransaction transactionInsert = UserTransaction.builder()
                            .id(IdentifierGenerator.nextId())
                            .status(TransactionTypeEnum.IN_RANSACTION.getCode().byteValue())
                            .paymentId(paymentId)
                            .account(bid.getInAccount())
                            .bank(bid.getInBank())
                            .amount(BigDecimal.valueOf(bluePayRequest.getPrice()))
                            .type(TransactionTypeEnum.OUT.getCode().byteValue())
                            .repaymentType(repayQuery.getRepaymentType())
                            .bidId(repayQuery.getBidId())
                            .userId(bid.getUserId()).build();
                    //交易表插入交易中记录
                    repayService.insertUserTransaction(transactionInsert);
                }
            }
            // 过滤处理中
            if (status.equals(BluePayStatusEnum.OK.getKey())) {
                log.info("MQ交易正在处理中，处理终止");
                return;
            }
            // 过滤失败直接修改交易记录
            if (!status.equals(BluePayStatusEnum.BLUE_PAY_COMPLETE.getKey())) {
                transactionService.updateUserTranState(paymentId, TransactionTypeEnum.FAIL_RANSACTION.getCode().byteValue());
                if(BANK.equals(interfacetype)){
                    repayService.updateUserTransactionRepay(UserTransactionRepay.builder().paymentId(paymentId).status(TransactionTypeEnum.FAIL_RANSACTION.getCode().byteValue()).build());
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
            if (loanTransacQuery.getStatus().intValue() != TransactionTypeEnum.IN_RANSACTION.getCode().intValue()) {
                log.info("本地当前交易状态：{}，不是交易中状态，处理终止", loanTransacQuery.getStatus());
                return;
            }
            // 本地处理放款
            if (CASHOUT.equals(interfacetype)) {
                // 查询相应的推送任务信息
                Long tempId = tempTableRepository.findTempTableByBidNoAndName(loanTransacQuery.getBidId(), ComConsts.PUSH_BANK_TASK);
                // 修改交易信息
                transactionService.loanSuccess(loanTransacQuery, tempId);
                log.info("本地交易放款处理成功");
            }
            // 本地处理还款
            if (BANK.equals(interfacetype)) {
                // 走信息流
                UserTransactionRepay repay = UserTransactionRepay.builder().paymentId(paymentId).status(TransactionTypeEnum.SUCCESS_RANSACTION.getCode().byteValue()).build();
                repayService.repaymentSuccess(loanTransacQuery, repay);
                log.info("本地交易处理成功");
            }

        } catch (Exception ex) {
            log.error("处理MQ回调交易信息过程出现异常，请及时人工处理", ex);
        } finally {
            log.info("============================= MQ交易回调结束 =============================");
        }
    }
}
