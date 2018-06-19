package com.hzed.easyget.controller.web;


import com.hzed.easyget.application.service.LoginService;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.annotation.TokenIgnore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * 用户登录
 *
 * @author hfj
 * @date 2018/06/12
 */

@Slf4j
@ExceptionAnno
@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @TokenIgnore
    @ModuleFunc("发送短信验证码")
    @PostMapping("/sendSmsCode")
    public void sendSmsCode(@Valid @RequestBody SmsCodeRequest request) {
        loginService.sendSmsCode(request);
    }

    @TokenIgnore
    @ModuleFunc("手机验证码登录")
    @PostMapping("/loginByCode")
    public LoginByCodeResponse loginByCode(@Valid @RequestBody LoginByCodeRequest request) {
        return loginService.loginByCode(request);
    }

    @TokenIgnore
    @ModuleFunc("H5手机验证码注册")
    @PostMapping("/registerH5")
    public void registerH5(@Valid @RequestBody RegisterH5Request request) {
        loginService.registerH5(request);
    }

    @TokenIgnore
    @ModuleFunc("获取图片验证码")
    @PostMapping("/getPictureCode")
    public PictureCodeResponse getPictureCode(@Valid @RequestBody PictureCodeRequest request) {
        return loginService.getPictureCode(request.getMobile());
    }

    @TokenIgnore
    @ModuleFunc("验证图片验证码")
    @PostMapping("/checkPictureCode")
    public void checkPictureCode(@Valid @RequestBody CheckPictureCodeRequest request) {
        loginService.checkPictureCode(request.getMobile(), request.getCode());
    }
}
