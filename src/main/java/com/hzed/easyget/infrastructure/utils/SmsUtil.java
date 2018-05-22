package com.hzed.easyget.infrastructure.utils;


import com.hzed.easyget.infrastructure.annotation.ModuleFunc;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 发送短信
 * @author hfj
 * @date 2018-05-21
 */

public class SmsUtil {

    @ModuleFunc("发送短信验证码")
    public static Map<String , String> sendCode(String mobile){
        // 最大值位9999
        int randomCode = (new Random()).nextInt(8999) + 1000;
        String content = "您的注册验证码是："+randomCode+" ，两分钟内有效，欢迎使用本平台";
        //具体发送接口调用
        //TODO
        Map<String , String> map  = new HashMap(2);
        map.put("smsCode",String.valueOf(randomCode));
        map.put("content",content);
        return  map;
    }


}
