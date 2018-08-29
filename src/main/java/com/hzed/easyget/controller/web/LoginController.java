package com.hzed.easyget.controller.web;


import com.hzed.easyget.application.service.LoginService;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.annotation.head.IgnoreH5;
import com.hzed.easyget.infrastructure.annotation.head.IgnoreHeader;
import com.hzed.easyget.infrastructure.annotation.head.TokenIgnore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @IgnoreHeader
    @ModuleFunc("facebook发送短信标识")
    @GetMapping("/getFacebookSms")
    public FacebookSmsResponse getFacebookSms() {
        return loginService.getFacebookSms();
    }

    @TokenIgnore
    @ModuleFunc("APP-检验手机号是否三大运营商")
    @PostMapping("/checkMobile")
    public void checkMobile(@Valid @RequestBody SmsCodeRequest request) {
        loginService.checkMobileBeforeSend(request.getMobile());
    }

    @TokenIgnore
    @ModuleFunc("APP-发送短信验证码")
    @PostMapping("/sendSmsCode")
    public void sendSmsCode(@Valid @RequestBody SmsCodeRequest request) {
        loginService.sendSmsCode(request);
    }

    @TokenIgnore
    @ModuleFunc("APP-手机验证码登录")
    @PostMapping("/loginByCode")
    public LoginByCodeResponse loginByCode(@Valid @RequestBody LoginByCodeRequest request) {
        return loginService.loginByCode(request);
    }

    @TokenIgnore
    @ModuleFunc("APP-facebook短信登录")
    @PostMapping("/loginByFacebook")
    public LoginByCodeResponse loginByCode(@Valid @RequestBody LoginByFacebookRequest request) {
        return loginService.loginByFacebook(request);
    }

    @TokenIgnore
    @ModuleFunc("APP-获取图片验证码")
    @PostMapping("/getPictureCode")
    public PictureCodeResponse getPictureCode(@Valid @RequestBody PictureCodeRequest request) {
        return loginService.getPictureCode(request.getMobile());
    }

    @TokenIgnore
    @ModuleFunc("APP-验证图片验证码")
    @PostMapping("/checkPictureCode")
    public void checkPictureCode(@Valid @RequestBody CheckPictureCodeRequest request) {
        loginService.checkPictureCode(request.getMobile(), request.getCode());
    }

    @IgnoreH5
    @ModuleFunc("H5-发送短信验证码")
    @PostMapping("/sendSmsCodeH5")
    public void sendSmsCodeH5(@Valid @RequestBody SmsCodeRequest request) {
        loginService.sendSmsCode(request);
    }

    @IgnoreH5
    @ModuleFunc("H5-手机验证码注册")
    @PostMapping("/registerH5")
    public void registerH5(@Valid @RequestBody RegisterH5Request request) {
        loginService.registerH5(request);
    }

    @IgnoreH5
    @ModuleFunc("H5-facebook短信登录")
    @PostMapping("/registerByFacebookH5")
    public void registerByFacebookH5(@Valid @RequestBody RegisterH5FbRequest request) {
        loginService.registerH5FB(request);
    }

    @IgnoreH5
    @ModuleFunc("H5-获取图片验证码")
    @PostMapping("/getPictureCodeH5")
    public PictureCodeResponse getPictureCodeH5(@Valid @RequestBody PictureCodeRequest request) {
        return loginService.getPictureCode(request.getMobile());
    }

    @IgnoreH5
    @ModuleFunc("H5-验证图片验证码")
    @PostMapping("/checkPictureCodeH5")
    public void checkPictureCodeH5(@Valid @RequestBody CheckPictureCodeRequest request) {
        loginService.checkPictureCode(request.getMobile(), request.getCode());
    }

    @IgnoreH5
    @ModuleFunc("H5-检验手机号是否三大运营商且是新用户")
    @PostMapping("/checkMobileH5")
    public CheckMobileResponse checkMobileH5(@Valid @RequestBody CheckMobileRequest request) {
        return loginService.checkMobileBeforeSendH5(request.getMobile());
    }

}
