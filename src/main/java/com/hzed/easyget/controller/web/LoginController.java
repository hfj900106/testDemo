package com.hzed.easyget.controller.web;


import com.hzed.easyget.application.service.LoginService;
import com.hzed.easyget.controller.model.LoginByCodeRequest;
import com.hzed.easyget.controller.model.LoginByCodeResponse;
import com.hzed.easyget.controller.model.SmsCodeRequest;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.annotation.TokenIgnore;
import com.hzed.easyget.infrastructure.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户登录
 *
 * @author hfj
 * @date 2018/2132/
 */

@Slf4j
@ExceptionAnno
@RestController
@RequestMapping("/hzed/easy-get/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @TokenIgnore
    @ModuleFunc("发送短信验证码")
    @PostMapping("/sendSmsCode")
    public Response sendSmsCode(@RequestBody SmsCodeRequest request) {
        loginService.sendSmsCode(request);
        return Response.getSuccessResponse();
    }

    @TokenIgnore
    @ModuleFunc("手机验证码登录")
    @PostMapping("/loginByCode")
    public Response<LoginByCodeResponse> loginByCode(@RequestBody LoginByCodeRequest request) {
        LoginByCodeResponse response = loginService.loginByCode(request);
        return Response.getSuccessResponse(response);
    }
}
