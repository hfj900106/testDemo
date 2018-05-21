package com.hzed.easyget.controller.web;


import com.hzed.easyget.application.service.LoginServicce;
import com.hzed.easyget.application.service.LoginService;
import com.hzed.easyget.controller.model.LoginBycodeRequest;
import com.hzed.easyget.application.service.LoginService;
import com.hzed.easyget.controller.model.LoginByCodeRequest;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.annotation.TokenIgnore;
import com.hzed.easyget.infrastructure.exception.WarnException;
import com.hzed.easyget.infrastructure.model.Response;
import com.hzed.easyget.infrastructure.utils.RegexUtils;
import com.hzed.easyget.infrastructure.utils.SMSUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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


    @Autowired
    private LoginService loginService;

    /**
     * 发送短信验证码
     * @param mobile
     */
    @TokenIgnore
    @ModuleFunc("发送短信验证码")
    @PostMapping("/sendSmsCode")
    public static void sendSmsCode(String mobile) {


        loginService.sendSmsCode(mobile);
        Response.getSuccessResponse();
        return;
    }

    /**
     * 手机验证码登录
     * @param
     */

    @TokenIgnore
    @ModuleFunc("验证码登录")
    @PostMapping("/loginByCode")
    public Response loginByCode(@RequestBody LoginByCodeRequest params){


        return loginService.loginByCode(params);
    }
}
