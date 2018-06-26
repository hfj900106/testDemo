package com.hzed.easyget.infrastructure.utils;

import com.alibaba.fastjson.JSONObject;
import com.hzed.easyget.application.enums.EnvEnum;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.indonesia.sms.constants.SmsCodeEnum;
import com.hzed.indonesia.sms.model.request.NxSmsDownRequest;
import com.hzed.indonesia.sms.model.response.NxSmsDownResponse;
import com.hzed.indonesia.sms.utils.NxSmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import java.util.Random;

/**
 * 短信工具类
 * @author hfj
 * @date 2018/6/9
 */
@Slf4j
public class SmsUtils {

    @Value("${spring.profiles.active}")
    private static String env;

    public static void sendSms(String mobile,String content){
        NxSmsDownRequest smsDownRequest = new NxSmsDownRequest();
        smsDownRequest.setPhone(mobile);
        smsDownRequest.setTimestamp(String.valueOf(System.currentTimeMillis()));
        smsDownRequest.setSourceadd("hztele");
        smsDownRequest.setExtno(123);
        smsDownRequest.setContent(content);
        log.info("发送短信请求参数：{}",JSONObject.toJSONString(smsDownRequest));
        NxSmsDownResponse smsDownResponse = NxSmsUtil.smsSend(smsDownRequest);
        log.info("发送短信返回数据：{}",JSONObject.toJSONString(smsDownResponse));
        //发送失败
        if(!SmsCodeEnum.OK.getKey().equals(smsDownResponse.getCode())){
            throw new ComBizException(BizCodeEnum.SMS_CODE_SEND_FAIL);
        }
    }

    public static String getCode(){
        if (EnvEnum.isTestEnv(env) ) {
            return "0000";
        }
        return StringUtils.leftPad(String.valueOf(new Random().nextInt(9999)), 4, "0");
    }
}
