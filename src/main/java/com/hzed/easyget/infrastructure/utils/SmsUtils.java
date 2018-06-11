package com.hzed.easyget.infrastructure.utils;

import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.indonesia.sms.constants.SmsCodeEnum;
import com.hzed.indonesia.sms.model.request.NxSmsDownRequest;
import com.hzed.indonesia.sms.model.response.NxSmsDownResponse;
import com.hzed.indonesia.sms.utils.NxSmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;

/**
 * 短信工具类
 * @author hfj
 * @date 2018/6/9
 */
@Slf4j
public class SmsUtils {

    public static void sendSms(String mobile,String content){
        NxSmsDownRequest smsDownRequest = new NxSmsDownRequest();
        smsDownRequest.setPhone(mobile);
        smsDownRequest.setTimestamp(String.valueOf(System.currentTimeMillis()));
        smsDownRequest.setSourceadd("hztele");
        smsDownRequest.setExtno(123);
        smsDownRequest.setContent(content);
        NxSmsDownResponse smsDownResponse = NxSmsUtil.smsSend(smsDownRequest);
        //发送失败
        if(!SmsCodeEnum.OK.getKey().equals(smsDownResponse.getCode())){
            throw new ComBizException(BizCodeEnum.SMS_CODE_SEND_FAIL);
        }
    }

    public static String getCode(){
        return StringUtils.leftPad(String.valueOf(new Random().nextInt(9999)), 4, "0");
    }
}
