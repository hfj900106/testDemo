package com.hzed.easyget.infrastructure.utils;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 发送短信
 * @author hfj
 * @date 2018-05-21
 */

public class SmsUtil {


    /**
     * 短信发送验证码
     *
     * @param mobile
     * @return
     */
    public static Map<String, String> sendCode(String mobile) {
        Map<String, String> map = sendMobileCode(mobile);
        return map;
    }

    public static Map<String, String> sendMobileCode(String mobile) {
        int randomCode = (new Random()).nextInt(8999) + 1000;
        String content = "您的注册验证码是：" + randomCode + " ，两分钟内有效，欢迎使用本平台";
        sendSMS(mobile, content);
        Map<String, String> map = new HashMap(2);
        map.put("smsCode", String.valueOf(randomCode));
        map.put("content", content);
        return map;
    }

    public static void sendSMS(String mobile, String content) {
        //具体调用验证码发送接口
    }

}
