package com.hzed.easyget.application.mq;

import com.hzed.easyget.application.service.RepayService;
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
    public void onMessage(Message messageByte, Channel channel){
        repayService.mqCallBackConsumer(null,messageByte);
    }
}
