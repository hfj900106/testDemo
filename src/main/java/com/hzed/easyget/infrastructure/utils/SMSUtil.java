package com.hzed.easyget.infrastructure.utils;


import com.google.common.collect.Maps;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 发送短信
 * @author hfj
 * @date 2018-05-21
 */

public class SMSUtil {

    @ModuleFunc("发送短信验证码")
    public static Map<String , String> sendCode(String mobile){
        // 最大值位9999
        int randomCode = (new Random()).nextInt(8999) + 1000;
        String content = "动态验证码："+randomCode+" 。工作人员不会向您索要，请勿向任何人泄露";
        //具体发送接口调用
        Map<String , String> map  = new HashMap(2);
        map.put("smsCode",String.valueOf(randomCode));
        map.put("content",content);
        return  map;
    }


}
