package com.hzed.easyget.application.service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.hzed.easyget.application.enums.EnvEnum;
import com.hzed.easyget.application.enums.SmsStatusEnum;
import com.hzed.easyget.infrastructure.config.SystemProp;
import com.hzed.easyget.infrastructure.consts.ComConsts;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.WarnException;
import com.hzed.easyget.infrastructure.repository.DictRepository;
import com.hzed.easyget.infrastructure.repository.SmsLogRepository;
import com.hzed.easyget.infrastructure.repository.UserMessageRepository;
import com.hzed.easyget.infrastructure.repository.UserRepository;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.MdcUtil;
import com.hzed.easyget.infrastructure.utils.id.IDGenerator;
import com.hzed.easyget.persistence.auto.entity.SmsLog;
import com.hzed.indonesia.sms.constants.SmsCodeEnum;
import com.hzed.indonesia.sms.model.request.BulkSmsDownRequest;
import com.hzed.indonesia.sms.model.request.NxSmsSendRequest;
import com.hzed.indonesia.sms.model.response.BulkSmsDownResponse;
import com.hzed.indonesia.sms.model.response.NxSmsSendResponse;
import com.hzed.indonesia.sms.utils.BulkSmsUtil;
import com.hzed.indonesia.sms.utils.NxSmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * @author zhangrl
 * @date 2018/6/7
 */

@Slf4j
@Service
public class SmsService {
    @Autowired
    private SystemProp systemProp;
    @Autowired
    private DictRepository dictRepository;
    @Autowired
    private ComService comService;
    @Autowired
    private SmsLogRepository smsLogRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMessageRepository messageRepository;
    @Autowired
    private DictService dictService;

    /**
     * 部分结清/全部结清 短信通知
     *
     * @param repaymentAmount 本次还款金额
     * @param mobile          手机号
     * @param bidId           标的id 结清不传
     */
    @Async
    public void repaymentNotice(BigDecimal repaymentAmount, String mobile, Long bidId) {
        MdcUtil.putTrace();
        MdcUtil.putModuleName("还款短信通知");
        String local = systemProp.getLocal();
        // 默认全部结清
        String smsCode = ComConsts.SMS_CONTENT_5;
        String title = dictRepository.findByCodeAndLanguage(ComConsts.MESSAGE_TITLE_4, systemProp.getLocal()).getDicValue();
        // 默认代还总额0
        BigDecimal balance = BigDecimal.valueOf(0);
        // 部分结清
        if (!ObjectUtils.isEmpty(bidId)) {
            smsCode = ComConsts.SMS_CONTENT_6;
            title = dictRepository.findByCodeAndLanguage(ComConsts.MESSAGE_TITLE_5, systemProp.getLocal()).getDicValue();
            // 获取剩余代还总额
            balance = comService.getBidNoRepayFee(bidId, LocalDateTime.now());
        }
        String content = dictRepository.findByCodeAndLoacl(local, smsCode).getDicValue();
        if (StringUtils.isBlank(content)) {
            log.error("没有配置短信模板");
            throw new WarnException(BizCodeEnum.UNKNOWN_EXCEPTION);
        }
        content = MessageFormat.format(content, DateUtil.localDateTimeToStr(LocalDateTime.now(), DateUtil.FORMAT6), repaymentAmount.toString(), balance.toString());
        // 发送及保存短信
        sendDefaultSms(mobile, content, "用户还款短信通知");
        // 通过手机号获取用户id
        Long userId = userRepository.findByMobile(mobile).getId();
        messageRepository.addUserMessage(userId, title, content, "用户还款短信通知");
        log.info("用户还款短信通知，手机号码：{},短信类容：{}", mobile, content);
    }

    /**
     * 使用默认配置发短信并保存
     *
     * @param mobile  手机号
     * @param content 短信内容
     * @param remark  短信描述
     */
    public void sendDefaultSms(String mobile, String content, String remark) {
        // 短信渠道
        String smsChannel = dictService.getDictByCode(ComConsts.SMS_DICT_CODE).getDicValue();
        log.info("发送短信渠道：{}", smsChannel);
        // 短信唯一标识
        Long smsId = IDGenerator.nextId();
        if (!EnvEnum.isTestEnv(systemProp.getEnv())) {
            // 非测试环境发送短信且去掉首位0
            sendSms(smsId, mobile, content, smsChannel);
        }

        // 保存短信记录
        SmsLog smsLogInsert = SmsLog.builder()
                .id(smsId)
                .content(content)
                .mobile(mobile)
                .status(SmsStatusEnum.SUCCESS.getCode().byteValue())
                .sendBy(smsChannel)
                .remark(remark)
                .build();
        smsLogRepository.insertSelective(smsLogInsert);
    }

    public void sendSms(Long smsId, String mobile, String content, String smsChannel) {
        if (ComConsts.NX.equalsIgnoreCase(smsChannel)) {
            // 使用牛信发送短信 默认通道 0-验证通道
            nxSend(mobile, content);
        } else {
            // 使用国际发短信
            cnSend(mobile, content, smsId);
        }
    }

    /**
     * 牛信发短信
     *
     * @param channel 通道 0-验证通道 1-营销账号
     */
    public void nxSend(String mobile, String content, Integer channel) {
        NxSmsSendRequest smsSendRequest = new NxSmsSendRequest();
        smsSendRequest.setPhone("62" + mobile);
        smsSendRequest.setTaskTime(DateUtil.localDateTimeToStr1(LocalDateTime.now()));
        smsSendRequest.setContent(content);
        smsSendRequest.setSmsChannelType(channel);
        log.info("请求参数：{}", JSONObject.toJSONString(smsSendRequest));
        NxSmsSendResponse smsSendResponse = NxSmsUtil.smsSend(smsSendRequest);
        if (ObjectUtils.isEmpty(smsSendResponse)) {
            log.error("发送牛信短信返回空");
            throw new WarnException(BizCodeEnum.SMS_CODE_SEND_FAIL);
        }
        log.info("发送短信返回数据：{}", JSONObject.toJSONString(smsSendResponse));
        // 发送失败
        if (!SmsCodeEnum.OK.getKey().equals(smsSendResponse.getCode())) {
            log.error("NX发送失败：{}", smsSendResponse.getResult());
            throw new WarnException(BizCodeEnum.SMS_CODE_SEND_FAIL);
        }
    }

    /**
     * 牛信发短信
     * 默认通道 0-验证通道
     */
    public void nxSend(String mobile, String content) {
        nxSend(mobile, content, 0);
    }

    /**
     * 国际发短信
     */
    public void cnSend(String mobile, String content, Long smsId) {
//        mobile = mobile.indexOf("0")
        // bulk短信下发请求bean
        BulkSmsDownRequest smsDownRequest = new BulkSmsDownRequest();
        // 短信请求body
        BulkSmsDownRequest.MsgBody msgBody = new BulkSmsDownRequest.MsgBody();
        // 短信来源
        msgBody.setFrom("easyget");
        // 消息标识id 短信表的id作为短信的唯一标识发给渠道商
        msgBody.setReference(String.valueOf(smsId));
        // 短信内容实体
        BulkSmsDownRequest.MsgBody.SmsBody smsBody = new BulkSmsDownRequest.MsgBody.SmsBody();
        smsBody.setContent(content);
        // 设置短信内容实体
        msgBody.setBody(smsBody);
        // 短信接收者
        BulkSmsDownRequest.MsgBody.Member member = new BulkSmsDownRequest.MsgBody.Member();
        // 00加国家代码中国为0086
        member.setNumber("0062" + mobile);
        //设置短信接收列表
        msgBody.setTo(Lists.newArrayList(member));
        // bulk短信下发请求消息
        BulkSmsDownRequest.Message message = new BulkSmsDownRequest.Message();
        // bulk短信下发请求消息body列表
        message.setMsg(Lists.newArrayList(msgBody));
        smsDownRequest.setMessages(message);
        log.info("请求参数：{}", JSONObject.toJSONString(smsDownRequest));
        BulkSmsDownResponse smsDownResponse = BulkSmsUtil.smsSend(smsDownRequest);
        if (ObjectUtils.isEmpty(smsDownResponse)) {
            log.error("返回空的数据对象");
            throw new WarnException(BizCodeEnum.SMS_CODE_SEND_FAIL);
        }
        log.info("发送短信返回数据：{}", JSONObject.toJSONString(smsDownResponse));
        if (ComConsts.BULK_SMS_OK != smsDownResponse.getErrorCode()) {
            log.error("CM发送失败：{}", JSONObject.toJSONString(smsDownResponse.getMessages()));
            throw new WarnException(BizCodeEnum.SMS_CODE_SEND_FAIL);
        }
    }

    /**
     * 获取验证码
     */
    public String getCode() {
        if (EnvEnum.isTestEnv(systemProp.getEnv())) {
            return "0000";
        }
        return StringUtils.leftPad(String.valueOf(new Random().nextInt(9999)), 4, "0");
    }
}
