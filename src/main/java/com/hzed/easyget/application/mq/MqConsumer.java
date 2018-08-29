package com.hzed.easyget.application.mq;

import com.hzed.easyget.application.service.RepayService;
import com.hzed.easyget.controller.model.BluePayRequest;
import com.hzed.easyget.infrastructure.utils.MdcUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * 功能概要：放款、收款通知消费接收
 *
 * @author zhangruilin
 * @date 2018年6月13日 下午6:10:40
 */
@Slf4j
@Profile({"test","prod"})
public class MqConsumer {

    @Autowired
    private RepayService repayService;

    @RabbitListener(queues = "BLUEPAY_ORDER_NOTIFICATION_QUEUE", containerFactory = "rabbitListenerContainerFactory")
    public void onMessage(@Payload BluePayRequest request) {
        MdcUtil.putModuleName("MQ回调");
        // 记录trace，方便日志追踪
        MdcUtil.putTrace();
        log.info("============================= MQ交易回调开始 =============================");
        try {
            repayService.mqCallback(request);
        } catch (Exception ex) {
            log.error("============================= 自动处理失败，请执行人工处理程序 =============================", ex);
        } finally {
            log.info("============================= MQ交易回调结束 =============================");
        }

    }
}
