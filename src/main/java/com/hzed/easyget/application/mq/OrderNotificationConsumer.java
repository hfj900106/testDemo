package com.hzed.easyget.application.mq;

import com.alibaba.fastjson.JSONObject;
import com.hzed.easyget.application.service.TransactionService;
import com.hzed.easyget.controller.model.BlueNotificationRequest;
import com.hzed.easyget.infrastructure.consts.ComConsts;
import com.hzed.easyget.infrastructure.enums.BluePayStatusEnum;
import com.hzed.easyget.infrastructure.repository.TempTableRepository;
import com.hzed.easyget.persistence.auto.entity.TempTable;
import com.hzed.easyget.persistence.auto.entity.UserTransaction;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 功能概要：放款、收款通知消费接收
 * <p>Title: OrderNotificationConsumer </p>
 *
 * @author madaijun
 * @date 2018年6月13日 下午6:10:40
 */
@Slf4j
@Component
public class OrderNotificationConsumer implements ChannelAwareMessageListener {

    private FastJsonMessageConverter messageConverter;
    @Autowired
    private TempTableRepository tempTableRepository;
    @Autowired
    private TransactionService transactionService;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        BlueNotificationRequest bluePayRq = messageConverter.fromMessage(message, BlueNotificationRequest.class);
        log.info("放款回调，bluepay端返回信息{}", JSONObject.toJSONString(bluePayRq));
        //判断是否交易完成
        Long tempId = 0L;
        if (bluePayRq.getStatus().equals(BluePayStatusEnum.BLUE_PAY_COMPLETE.getKey()) && bluePayRq.getInterfacetype().equals(ComConsts.CASHOUT)) {
            //获取交易id 判断是否合法
            List<UserTransaction> transactionList = transactionService.findUserTranBypayMenid(bluePayRq.getT_id());
            if (!ObjectUtils.isEmpty(transactionList)) {
                //判断是否交易成功 防止重复交易
                UserTransaction userTr = transactionList.get(0);
                if (!userTr.getIsOver()) {
                    //查询相应的推送任务信息
                    tempId = tempTableRepository.findTempTableByBidNoAndName(userTr.getBidId(), ComConsts.PUSH_BANK_TASK);
                    //修改交易信息
                    transactionService.callBackupdateUserTrance(userTr, tempId);
                }
            }
        } else if (bluePayRq.getStatus().equals(BluePayStatusEnum.BLUE_PAY_COMPLETE.getKey()) && bluePayRq.getInterfacetype().equals(ComConsts.BANK)) {

        } else {
            log.info("放款回调，bluepay处理失败{}", BluePayStatusEnum.getPayStatusEnum(bluePayRq.getStatus()));
            tempTableRepository.upDateTemp(TempTable.builder().id(tempId).createTime(LocalDateTime.now()).remark("放款失败：" + BluePayStatusEnum.getPayStatusEnum(bluePayRq.getStatus())).build());
        }
    }

    public void setMessageConverter(FastJsonMessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }
}
