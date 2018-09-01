package com.hzed.easyget.application.service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.hzed.easyget.application.enums.EnvEnum;
import com.hzed.easyget.application.enums.SmsStatusEnum;
import com.hzed.easyget.infrastructure.config.SystemProp;
import com.hzed.easyget.infrastructure.consts.ComConsts;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.WarnException;
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
        String remark = "用户还款短信通知";
        MdcUtil.putTrace();
        MdcUtil.putModuleName(remark);

        // 短信语言
        String local = systemProp.getLocal();

        String contentCode;
        String titleCode;
        // 默认待还总额0
        BigDecimal balance = BigDecimal.ZERO;

        // 全部结清
        if (bidId == null) {
            contentCode = ComConsts.SMS_CONTENT_5;
            titleCode = ComConsts.MESSAGE_TITLE_4;
        } else {
            contentCode = ComConsts.SMS_CONTENT_6;
            titleCode = ComConsts.MESSAGE_TITLE_5;
            // 获取剩余待还总额
            balance = comService.getBidNoRepayFee(bidId, LocalDateTime.now());
        }
        // 短信标题
        String title = dictService.getDictByCodeAndLanguage(titleCode, local).getDicValue();
        // 短信内容
        String content = dictService.getDictByCodeAndLanguage(contentCode, local).getDicValue();
        // 短信内容占位取代
        content = MessageFormat.format(content, DateUtil.localDateTimeToStr6(LocalDateTime.now()), repaymentAmount.toString(), balance.toString());
        // 发送及保存短信
        sendDefaultSms(mobile, content, remark);
        // 通过手机号获取用户id
        Long userId = userRepository.findByMobile(mobile).getId();
        messageRepository.addUserMessage(userId, title, content, remark);
        log.info("用户还款短信通知，手机号码：{}，短信内容：{}", mobile, content);
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
        if (ComConsts.NX.equalsIgnoreCase(smsChannel)) {
            // 使用牛信发送短信 默认通道 0-验证通道
            sendNxSms(mobile, content, remark, 0);
        } else {
            // 使用国际发短信
            sendCMSms(mobile, content, remark);
        }
    }

    /**
     * 牛信渠道发送短信并保存
     *
     * @param mobile    手机号
     * @param content   内容
     * @param remark    备注
     * @param nxChannel 通道 0-验证通道 1-营销账号
     */
    public void sendNxSms(String mobile, String content, String remark, Integer nxChannel) {
        // 发短信
        nxSend(mobile, content, nxChannel);
        // 保存短信记录 暂时默认发送成功
        SmsLog smsLogInsert = SmsLog.builder()
                .id(IDGenerator.nextId())
                .content(content)
                .mobile(mobile)
                .status(SmsStatusEnum.SUCCESS.getCode().byteValue())
                .sendBy(ComConsts.NX)
                .remark(remark)
                .build();
        smsLogRepository.insertSelective(smsLogInsert);
    }

    /**
     * 国际短信渠道发送短信并保存
     *
     * @param mobile  手机号
     * @param content 内容
     * @param remark  备注
     */
    public void sendCMSms(String mobile, String content, String remark) {
        long smsID = IDGenerator.nextId();
        // 发短信
        cmSend(smsID, mobile, content);
        // 保存短信记录 暂时默认发送成功
        SmsLog smsLogInsert = SmsLog.builder()
                .id(smsID)
                .content(content)
                .mobile(mobile)
                .status(SmsStatusEnum.SUCCESS.getCode().byteValue())
                .sendBy(ComConsts.CM)
                .remark(remark)
                .build();
        smsLogRepository.insertSelective(smsLogInsert);
    }

    /**
     * 牛信发短信
     *
     * @param channel 通道 0-验证通道 1-营销账号
     */
    public void nxSend(String mobile, String content, Integer channel) {
        // 测试环境不发短信
        if (EnvEnum.isTestEnv(systemProp.getEnv())) {
            log.info("测试环境不发短信");
            return;
        }

        // 手机号去掉开始的0
        mobile = mobile.replaceAll("^0+", "");
        NxSmsSendRequest smsSendRequest = new NxSmsSendRequest();
        smsSendRequest.setPhone("62" + mobile);
        smsSendRequest.setTaskTime(DateUtil.localDateTimeToStr1(LocalDateTime.now()));
        smsSendRequest.setContent(content);
        smsSendRequest.setSmsChannelType(channel);
        log.info("牛信，请求参数：{}", JSONObject.toJSONString(smsSendRequest));
        NxSmsSendResponse smsSendResponse = NxSmsUtil.smsSend(smsSendRequest);
        log.info("牛信，返回数据：{}", JSONObject.toJSONString(smsSendResponse));
        if (ObjectUtils.isEmpty(smsSendResponse)) {
            log.error("牛信，短信返回空");
            throw new WarnException(BizCodeEnum.SMS_CODE_SEND_FAIL);
        }
        // 发送失败
        if (!SmsCodeEnum.OK.getKey().equals(smsSendResponse.getCode())) {
            log.error("牛信，发送失败：{}", smsSendResponse.getResult());
            throw new WarnException(BizCodeEnum.SMS_CODE_SEND_FAIL);
        }
    }

    /**
     * 国际发短信
     */
    public void cmSend(Long smsId, String mobile, String content) {
        // 测试环境不发短信
        if (EnvEnum.isTestEnv(systemProp.getEnv())) {
            log.info("测试环境不发短信");
            return;
        }

        // 手机号去掉开始的0
        mobile = mobile.replaceAll("^0+", "");
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
        log.info("国际，请求参数：{}", JSONObject.toJSONString(smsDownRequest));
        BulkSmsDownResponse smsDownResponse = BulkSmsUtil.smsSend(smsDownRequest);
        log.info("国际，返回数据：{}", JSONObject.toJSONString(smsDownResponse));
        if (ObjectUtils.isEmpty(smsDownResponse)) {
            log.error("国际，短信返回空");
            throw new WarnException(BizCodeEnum.SMS_CODE_SEND_FAIL);
        }
        if (ComConsts.BULK_SMS_OK != smsDownResponse.getErrorCode().intValue()) {
            log.error("国际，发送失败：{}", JSONObject.toJSONString(smsDownResponse.getMessages()));
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
