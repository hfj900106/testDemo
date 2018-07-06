package com.hzed.easyget.application.service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.hzed.easyget.application.enums.EnvEnum;
import com.hzed.easyget.infrastructure.config.SystemProp;
import com.hzed.easyget.infrastructure.consts.ComConsts;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.WarnException;
import com.hzed.easyget.infrastructure.repository.DictRepository;
import com.hzed.easyget.infrastructure.repository.SmsLogRepository;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.SpringContextUtil;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.Dict;
import com.hzed.easyget.persistence.auto.entity.SmsLog;
import com.hzed.indonesia.sms.constants.SmsCodeEnum;
import com.hzed.indonesia.sms.model.request.BulkSmsDownRequest;
import com.hzed.indonesia.sms.model.request.NxSmsDownRequest;
import com.hzed.indonesia.sms.model.response.BulkSmsDownResponse;
import com.hzed.indonesia.sms.model.response.NxSmsDownResponse;
import com.hzed.indonesia.sms.utils.BulkSmsUtil;
import com.hzed.indonesia.sms.utils.NxSmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
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
    private SmsService smsService;
    @Autowired
    private SmsLogRepository smsLogRepository;

    /**
     * 部分结清/全部结清 短信通知
     *
     * @param repaymentAmount 本次还款金额
     * @param mobile          手机号
     * @param bidId           标的id 结清不传
     */
    @Async
    public void repaymentNotice(BigDecimal repaymentAmount, String mobile, Long bidId) {
        String local = systemProp.getLocal();
        // 默认全部结清
        String smsCode = ComConsts.SMS_CONTENT_5;
        // 默认代还总额0
        BigDecimal balance = BigDecimal.valueOf(0);
        if (!ObjectUtils.isEmpty(bidId)) {
            smsCode = ComConsts.SMS_CONTENT_6;
            // 获取剩余代还总额
            balance = comService.getBidNoRepayFee(bidId, LocalDateTime.now());
        }
        String content = dictRepository.findByCodeAndLoacl(local, smsCode).getDicValue()
                .replace("{1}", DateUtil.localDateTimeToStr(LocalDateTime.now(), DateUtil.FORMAT6))
                .replace("{2}", repaymentAmount.toString())
                .replace("{3}", balance.toString());
        Long smsId = IdentifierGenerator.nextId();
        // 发送短信
        smsService.sendSms(mobile, content, String.valueOf(smsId));
        // 保存短信记录
        smsService.saveSmsLog(smsId,content,mobile,(byte)2,"用户还款短信通知");
        log.info("用户还款短信通知，手机号码：{},短信类容：{}", mobile, content);
    }

    public  void sendSms(String mobile, String content, String smsIdStr) {
        DictService dictService = SpringContextUtil.getBean(DictService.class);
        Dict dictSms = dictService.getDictByCode(ComConsts.SMS_DICT_CODE);
        if (ObjectUtils.isEmpty(dictSms)) {
            log.error("没有配置短信渠道");
            throw new WarnException(BizCodeEnum.UNKNOWN_EXCEPTION);
        }
        String dicValue = dictSms.getDicValue();
        if (!ObjectUtils.isEmpty(dicValue) && ComConsts.NX.equalsIgnoreCase(dicValue)) {
            //使用牛信发送短信
            NxSmsDownRequest smsDownRequest = new NxSmsDownRequest();
            smsDownRequest.setPhone(mobile);
            smsDownRequest.setTimestamp(String.valueOf(System.currentTimeMillis()));
            //拆分短信表的id作为短信的唯一标识发给渠道商
            smsDownRequest.setSourceadd(smsIdStr.substring(0, 10));
            smsDownRequest.setExtno(Integer.valueOf(smsIdStr.substring(10, 18)));
            smsDownRequest.setContent(content);
            log.info("发送短信请求参数：{}", JSONObject.toJSONString(smsDownRequest));
            NxSmsDownResponse smsDownResponse = NxSmsUtil.smsSend(smsDownRequest);
            log.info("发送短信返回数据：{}", JSONObject.toJSONString(smsDownResponse));
            //发送失败
            if (!SmsCodeEnum.OK.getKey().equals(smsDownResponse.getCode())) {
                log.error("发送失败：{}", smsDownResponse.getInfo());
                throw new WarnException(BizCodeEnum.SMS_CODE_SEND_FAIL);
            }
        } else {
            //bulk短信下发请求bean
            BulkSmsDownRequest smsDownRequest = new BulkSmsDownRequest();
            //短信请求body
            BulkSmsDownRequest.MsgBody msgBody = new BulkSmsDownRequest.MsgBody();
            //短信来源
            msgBody.setFrom("easy-get");
            //消息标识id 短信表的id作为短信的唯一标识发给渠道商
            msgBody.setReference(smsIdStr);
            //短信内容实体
            BulkSmsDownRequest.MsgBody.SmsBody smsBody = new BulkSmsDownRequest.MsgBody.SmsBody();
            smsBody.setContent(content);
            //设置短信内容实体
            msgBody.setBody(smsBody);
            //短信接收者
            BulkSmsDownRequest.MsgBody.Member member = new BulkSmsDownRequest.MsgBody.Member();
            //00加国家代码中国为0086
            member.setNumber(mobile);
            //设置短信接收列表
            msgBody.setTo(Lists.newArrayList(member));
            //bulk短信下发请求消息
            BulkSmsDownRequest.Message message = new BulkSmsDownRequest.Message();
            //bulk短信下发请求消息body列表
            message.setMsg(Lists.newArrayList(msgBody));
            smsDownRequest.setMessages(message);
            log.info("发送短信请求参数：{}", JSONObject.toJSONString(smsDownRequest));
            BulkSmsDownResponse smsDownResponse = BulkSmsUtil.smsSend(smsDownRequest);
            log.info("发送短信返回数据：{}", JSONObject.toJSONString(smsDownResponse));
            if (ComConsts.BULK_SMS_OK != smsDownResponse.getErrorCode()) {
                log.error("发送失败：{}", JSONObject.toJSONString(smsDownResponse.getMessages()));
                throw new WarnException(BizCodeEnum.SMS_CODE_SEND_FAIL);
            }
        }
    }

    /**
     * 保存短信记录
     * @param id
     * @param content
     * @param mobile
     * @param status
     * @param remark
     */
    public void saveSmsLog(Long id,String content,String mobile,byte status,String remark){
        DictService dictService = SpringContextUtil.getBean(DictService.class);
        Dict dictSms = dictService.getDictByCode(ComConsts.SMS_DICT_CODE);
        if (ObjectUtils.isEmpty(dictSms)) {
            log.error("没有配置短信渠道");
            throw new WarnException(BizCodeEnum.UNKNOWN_EXCEPTION);
        }
        // 保存到数据库短信记录表
        SmsLog smsLog = new SmsLog();
        smsLog.setId(id);
        smsLog.setCreateTime(LocalDateTime.now());
        smsLog.setContent(content);
        smsLog.setMobile(mobile);
        // 发送成功
        smsLog.setStatus(status);
        smsLog.setSendBy(dictSms.getDicValue());
        smsLog.setRemark(remark);
        smsLogRepository.insertSelective(smsLog);
    }

    /**
     * 获取验证码
     * @return
     */
    public  String getCode() {
        SystemProp systemProp = SpringContextUtil.getBean(SystemProp.class);
        if (EnvEnum.isTestEnv(systemProp.getEnv())) {
            return "0000";
        }
        return StringUtils.leftPad(String.valueOf(new Random().nextInt(9999)), 4, "0");
    }
}
