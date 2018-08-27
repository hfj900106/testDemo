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

    @TokenIgnore
    @ModuleFunc("检验手机号是否三大运营商")
    @PostMapping("/checkMobile")
    public void checkMobile(@Valid @RequestBody SmsCodeRequest request) {
        loginService.checkMobileBeforeSend(request);
    }

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
    @ModuleFunc("facebook短信登录")
    @PostMapping("/loginByFacebook")
    public LoginByCodeResponse loginByCode(@Valid @RequestBody LoginByFacebookRequest request) {
        return loginService.loginByFacebook(request);
    }

    @IgnoreH5
    @ModuleFunc("H5发送短信验证码")
    @PostMapping("/sendSmsCodeH5")
    public void sendSmsCodeH5(@Valid @RequestBody SmsCodeRequest request) {
        loginService.sendSmsCode(request);
    }

    @IgnoreH5
    @ModuleFunc("H5手机验证码注册")
    @PostMapping("/registerH5")
    public void registerH5(@Valid @RequestBody RegisterH5Request request) {
        loginService.registerH5(request);
    }

    @IgnoreH5
    @ModuleFunc("facebook短信登录H5")
    @PostMapping("/registerByFacebookH5")
    public void registerByFacebookH5(@Valid @RequestBody RegisterH5FbRequest request) {
        loginService.registerH5FB(request);
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

    @IgnoreH5
    @ModuleFunc("H5获取图片验证码")
    @PostMapping("/getPictureCodeH5")
    public PictureCodeResponse getPictureCodeH5(@Valid @RequestBody PictureCodeRequest request) {
        return loginService.getPictureCode(request.getMobile());
    }

    @IgnoreH5
    @ModuleFunc("H5验证图片验证码")
    @PostMapping("/checkPictureCodeH5")
    public void checkPictureCodeH5(@Valid @RequestBody CheckPictureCodeRequest request) {
        loginService.checkPictureCode(request.getMobile(), request.getCode());
    }

    @IgnoreHeader
    @ModuleFunc("获取facebook发送短信标识")
    @GetMapping("/getFacebookSms")
    public FacebookSmsResponse getFacebookSms() {
        return loginService.getFacebookSms();
    }
}
