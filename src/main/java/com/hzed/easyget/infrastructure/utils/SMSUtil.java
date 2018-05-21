package com.hzed.easyget.infrastructure.utils;


import com.hzed.easyget.controller.model.SmsCodResponse;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;

import java.util.Random;

/**
 * 发送短信
 */

public class SMSUtil {

    @ModuleFunc("发送短信验证码")
    public static SmsCodResponse sendCode(String mobile){
        // 最大值位9999
        int randomCode = (new Random()).nextInt(8999) + 1000;
        String content = "动态验证码："+randomCode+" 。工作人员不会向您索要，请勿向任何人泄露";
        //具体发送接口调用
        return  SmsCodResponse.builder().code(randomCode).code1(randomCode).code2(randomCode).build() ;
    }


}
