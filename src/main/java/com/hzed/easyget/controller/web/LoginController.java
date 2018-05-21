package com.hzed.easyget.controller.web;


import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.annotation.TokenIgnore;
import com.hzed.easyget.infrastructure.exception.WarnException;
import com.hzed.easyget.infrastructure.model.Response;
import com.hzed.easyget.infrastructure.utils.RegexUtils;
import com.hzed.easyget.infrastructure.utils.SMSUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




/**
 * 用户登录
 */

@Slf4j
@ExceptionAnno
@RestController
@RequestMapping("/hzed/login")
public class LoginController {


    /**
     * 发送短信验证码
     * @param mobile
     */
    @TokenIgnore
    @ModuleFunc("发送短信验证码")
    @PostMapping("/sendSmsCode")
    public static void sendSmsCode(String mobile) {

        if(StringUtils.isBlank(mobile) ) {
            Response.getSuccessResponse(new WarnException("-1", "手机号码不能为空"));
            return;
        }

        if(!RegexUtils.isMobileNum(mobile)) {
            Response.getSuccessResponse(new WarnException("-1", "手机号格式有误"));
            return;
        }
        //获取短信验证码
        int code = SMSUtil.sendCode(mobile);
        //保存到数据库

        //保存到Redis

        Response.getSuccessResponse("验证码发送成功");
        return;
    }

}
