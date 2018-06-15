package com.hzed.easyget.application.mq;

import com.alibaba.fastjson.JSONObject;
import com.hzed.easyget.application.enums.TransactionTypeEnum;
import com.hzed.easyget.application.service.TransactionService;
import com.hzed.easyget.controller.model.BlueNotificationRequest;
import com.hzed.easyget.infrastructure.consts.ComConsts;
import com.hzed.easyget.infrastructure.enums.BluePayStatusEnum;
import com.hzed.easyget.infrastructure.repository.TempTableRepository;
import com.hzed.easyget.persistence.auto.entity.UserTransaction;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

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
    /**
     * 放款标识
     */
    private static  final String CASHOUT="cashout";
    /**
     * 还款标识
     */
    private static  final String BANK="bank";

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        BlueNotificationRequest bluePayRq = messageConverter.fromMessage(message, BlueNotificationRequest.class);
        log.info("放款/还款回调，bluepay端返回信息{}", JSONObject.toJSONString(bluePayRq));
        //判断是否交易完成
        Long tempId = 0L;
        try {
            //过滤处理中
            if(bluePayRq.getStatus().equals(BluePayStatusEnum.OK.getKey())){
                log.info("放款/还款bluepay端正在处理中！");
                return;
            }
            //过滤失败直接修改交易记录
            if(!bluePayRq.getStatus().equals(BluePayStatusEnum.BLUE_PAY_COMPLETE.getKey())){
                transactionService.updateUserTranState(bluePayRq.getT_id(),TransactionTypeEnum.FAIL_RANSACTION.getCode().byteValue());
                log.info("放款bluepay端返回处理失败{}",BluePayStatusEnum.getPayStatusEnum(bluePayRq.getStatus()));
                return;
            }
            //放款
            if (bluePayRq.getInterfacetype().equals(CASHOUT)) {
                //获取交易id 判断是否合法
                List<UserTransaction> transactionList = transactionService.findUserTranBypayMenid(bluePayRq.getT_id());
                if (!ObjectUtils.isEmpty(transactionList)) {
                    //判断是否交易成功 防止重复交易
                    UserTransaction userTr = transactionList.get(0);
                    //判断这个交易是否是 交易中
                    if (userTr.getStatus().intValue() == TransactionTypeEnum.IN_RANSACTION.getCode()) {
                        //查询相应的推送任务信息
                        tempId = tempTableRepository.findTempTableByBidNoAndName(userTr.getBidId(), ComConsts.PUSH_BANK_TASK);
                        //修改交易信息
                        transactionService.callBackupdateUserTrance(userTr, tempId);
                        log.info("放款bluepay端返回处理成功，本地处理成功");
                    }
                }
            //还款
            } else if ( bluePayRq.getInterfacetype().equals(BANK)) {
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("放款/还款回调处理失败{}",e.getMessage());
        }
    }

    public void setMessageConverter(FastJsonMessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }
}
