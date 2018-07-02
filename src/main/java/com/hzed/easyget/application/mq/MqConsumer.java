package com.hzed.easyget.application.mq;

import com.alibaba.fastjson.JSON;
import com.hzed.easyget.application.service.RepayService;
import com.hzed.easyget.infrastructure.utils.MdcUtil;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    private RepayService repayService;

    @Override
    public void onMessage(Message messageByte, Channel channel) {
        MdcUtil.putModuleName("MQ回调");
        // 记录trace，方便日志追踪
        MdcUtil.putTrace();
        log.info("============================= MQ交易回调开始 =============================");
        try {
            log.info("原始报文，message：{}，channel：{}", JSON.toJSONString(messageByte), JSON.toJSONString(channel));
            repayService.mqCallback(new String(messageByte.getBody(), "UTF-8"));
        } catch (Exception ex) {
            log.error("============================= MQ交易回调自动处理失败，请执行人工处理程序 =============================");
        } finally {
            log.info("============================= MQ交易回调开始 =============================");
        }

    }
}
