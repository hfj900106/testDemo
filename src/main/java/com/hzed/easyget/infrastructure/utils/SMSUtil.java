package com.hzed.easyget.infrastructure.utils;


import com.hzed.easyget.controller.model.SmsCodResponse;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 发送短信
 */

public class SMSUtil {

    @ModuleFunc("发送短信验证码")
<<<<<<< HEAD
    public static SmsCodResponse sendCode(String mobile){
=======
    public static Map<String,String> sendCode(String mobile){
>>>>>>> f2953eedcd28ca46782435d5581d0e1bfa8ee388
        // 最大值位9999
        int randomCode = (new Random()).nextInt(8999) + 1000;
        String content = "动态验证码："+randomCode+" 。工作人员不会向您索要，请勿向任何人泄露";
        //具体发送接口调用
        //TODO


        //返回验证码用于保存
<<<<<<< HEAD
        return  SmsCodResponse.builder().code(randomCode).code1(randomCode).code2(randomCode).build() ;
=======
        return  new HashMap<String,String>(){
            {
                put("randomCode",String.valueOf(randomCode));
                put("content",content);
            }
        };
>>>>>>> f2953eedcd28ca46782435d5581d0e1bfa8ee388
    }


}
