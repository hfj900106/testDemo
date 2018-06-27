package com.hzed.easyget.infrastructure.utils;

import com.alibaba.fastjson.JSONObject;
import com.hzed.easyget.application.enums.EnvEnum;
import com.hzed.easyget.application.service.DictService;
import com.hzed.easyget.infrastructure.config.PayProp;
import com.hzed.easyget.infrastructure.config.SystemProp;
import com.hzed.easyget.infrastructure.consts.ComConsts;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.exception.WarnException;
import com.hzed.easyget.persistence.auto.entity.Dict;
import com.hzed.indonesia.sms.constants.SmsCodeEnum;
import com.hzed.indonesia.sms.model.request.BulkSmsDownRequest;
import com.hzed.indonesia.sms.model.request.NxSmsDownRequest;
import com.hzed.indonesia.sms.model.response.BulkSmsDownResponse;
import com.hzed.indonesia.sms.model.response.NxSmsDownResponse;
import com.hzed.indonesia.sms.utils.BulkSmsUtil;
import com.hzed.indonesia.sms.utils.NxSmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 短信工具类
 *
 * @author hfj
 * @date 2018/6/9
 */
@Slf4j
public class SmsUtils {

    private static String env;

    @Value("${spring.profiles.active}")
    public void setEnv(String env) {
        SmsUtils.env = env;
    }

    public static void sendSms(String mobile, String content, Long smsId) {
        String smsIdStr = String.valueOf(smsId);
        DictService dictService = SpringContextUtil.getBean(DictService.class);
        Dict dictSms = dictService.getDictByCode(ComConsts.SMS_DICT_CODE);
        if(ObjectUtils.isEmpty(dictSms)){
            log.error("没有配置短信渠道");
            throw new ComBizException(BizCodeEnum.UNKNOWN_EXCEPTION);
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
                log.error("发送失败：{}",smsDownResponse.getInfo());
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
            msgBody.setReference(String.valueOf(smsId));
            //短信内容实体
            BulkSmsDownRequest.MsgBody.SmsBody smsBody = new BulkSmsDownRequest.MsgBody.SmsBody();
            smsBody.setContent(content);
            //设置短信内容实体
            msgBody.setBody(smsBody);
            //短信接收者
            BulkSmsDownRequest.MsgBody.Member member = new BulkSmsDownRequest.MsgBody.Member();
            //00加国家代码中国为0086
            member.setNumber(mobile);
            List<BulkSmsDownRequest.MsgBody.Member> members = new ArrayList<>();
            members.add(member);
            //设置短信接收列表
            msgBody.setTo(members);

            //bulk短信下发请求消息
            BulkSmsDownRequest.Message message = new BulkSmsDownRequest.Message();
            //bulk短信下发请求消息body列表
            List<BulkSmsDownRequest.MsgBody> msgBodyList = new ArrayList<>();
            msgBodyList.add(msgBody);
            //设置短信下发请求消息body列表
            message.setMsg(msgBodyList);
            smsDownRequest.setMessages(message);
            log.info("发送短信请求参数：{}", JSONObject.toJSONString(smsDownRequest));
            BulkSmsDownResponse smsDownResponse = BulkSmsUtil.smsSend(smsDownRequest);
            log.info("发送短信返回数据：{}", JSONObject.toJSONString(smsDownResponse));
            if (ComConsts.BULK_SMS_OK != smsDownResponse.getErrorCode()) {
                log.error("发送失败：{}",JSONObject.toJSONString(smsDownResponse.getMessages()));
                throw new ComBizException(BizCodeEnum.SMS_CODE_SEND_FAIL);
            }
        }

    }

    public static String getCode() {
        SystemProp systemProp = SpringContextUtil.getBean(SystemProp.class);
        if (EnvEnum.isTestEnv(systemProp.getEnv())) {
            return "0000";
        }
        return StringUtils.leftPad(String.valueOf(new Random().nextInt(9999)), 4, "0");
    }
}
