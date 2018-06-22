package com.hzed.easyget.application.mq;

import com.alibaba.fastjson.JSONObject;
import com.hzed.easyget.application.enums.BluePayStatusEnum;
import com.hzed.easyget.application.enums.TransactionTypeEnum;
import com.hzed.easyget.application.service.RepayService;
import com.hzed.easyget.application.service.TransactionService;
import com.hzed.easyget.controller.model.BluePayRequest;
import com.hzed.easyget.infrastructure.consts.ComConsts;
import com.hzed.easyget.infrastructure.repository.TempTableRepository;
import com.hzed.easyget.infrastructure.utils.MdcUtil;
import com.hzed.easyget.infrastructure.utils.ValidatorUtil;
import com.hzed.easyget.persistence.auto.entity.UserTransaction;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

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
            String message = new String(messageByte.getBody(), "UTF-8");
            log.info("放款/还款回调，bluepay端返回信息{}", message);

            BluePayRequest bluePayRequest = JSONObject.parseObject(message, BluePayRequest.class);

            // 参数校验
            ValidatorUtil.validateWithNull(bluePayRequest);

            // 过滤处理中
            if (bluePayRequest.getStatus().equals(BluePayStatusEnum.OK.getKey())) {
                log.info("放款/还款bluepay端正在处理中");
                return;
            }

            // 过滤失败直接修改交易记录
            if (!bluePayRequest.getStatus().equals(BluePayStatusEnum.BLUE_PAY_COMPLETE.getKey())) {
                transactionService.updateUserTranState(bluePayRequest.getT_id(), TransactionTypeEnum.FAIL_RANSACTION.getCode().byteValue());
                log.info("放款/还款bluepay端返回处理失败{}", BluePayStatusEnum.getValueDesc(bluePayRequest.getStatus()));
                return;
            }

            // 获取交易id 判断是否合法
            UserTransaction userTr = transactionService.findUserTranByPaymentId(bluePayRequest.getT_id());
            if (ObjectUtils.isEmpty(userTr)) {
                return;
            }

            // 判断这个交易是否是 交易中
            if (userTr.getStatus().intValue() != TransactionTypeEnum.IN_RANSACTION.getCode()) {
                return;
            }

            // 放款
            if (CASHOUT.equals(bluePayRequest.getInterfacetype())) {
                // 查询相应的推送任务信息
                Long tempId = tempTableRepository.findTempTableByBidNoAndName(userTr.getBidId(), ComConsts.PUSH_BANK_TASK);
                // 修改交易信息
                transactionService.callBackupdateUserTrance(userTr, tempId);
                log.info("放款bluepay端返回处理成功，本地处理成功");
            }

            // 还款
            if (BANK.equals(bluePayRequest.getInterfacetype())) {
                // 走资金流
                repayService.afterRepayment(userTr);
                log.info("还款bluepay端返回处理成功，本地处理成功");
            }
        } catch (Exception ex) {
            // TODO
        }
    }
}
