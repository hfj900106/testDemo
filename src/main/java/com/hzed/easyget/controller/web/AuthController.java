package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.AuthService;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.head.IgnoreHeader;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户认证
 *
 * @author hfj
 * @date 2018/5/22
 */

@Slf4j
@ExceptionAnno
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @ModuleFunc(value = "通讯录认证", printParameterLength = 300)
    @PostMapping("/contacts")
    public void contacts(@Valid @RequestBody ContactsRequest request) {
        authService.authContacts(request);
    }

    @ModuleFunc(value = "短信认证", printParameterLength = 300)
    @PostMapping("/messages")
    public void messages(@Valid @RequestBody MessagesRequest request) {
        authService.authMessages(request);
    }

    @ModuleFunc("个人信息认证")
    @PostMapping("/personInfo")
    public void personInfoAuth(@Valid @RequestBody PersonInfoAuthRequest request) {
        authService.authPersonInfo(request);
    }

    @ModuleFunc(value = "身份证识别", printParameterLength = 300)
    @PostMapping("/idCardRecognition")
    public IdCardRecognitionResponse idCardRecognition(@Valid @RequestBody IdCardRecognitionRequest request) {
        return authService.idCardRecognition(request);
    }

    @ModuleFunc(value = "人脸识别", printParameterLength = 300)
    @PostMapping("/faceRecognition")
    public void faceRecognition(@Valid @RequestBody FaceRecognitionRequest request) {
        authService.faceRecognition(request);
    }

    @ModuleFunc(value = "身份信息认证", printParameterLength = 300)
    @PostMapping("/identityInfo")
    public void identityInformationAuth(@Valid @RequestBody IdentityInfoAuthRequest request) {
        authService.identityInfoAuth(request);
    }

    @ModuleFunc("获取用户认证信息")
    @PostMapping("/getAuthStatus")
    public List<AuthStatusResponse> getAuthStatus(@Valid @RequestBody AuthStatusRequest request) {
        return authService.getAuthStatus(request);
    }

    @ModuleFunc("获取用户认证分组信息")
    @PostMapping("/getAuthGroupStatus")
    public List<AuthGroupStatusResponse> getAuthGroupStatus(@Valid @RequestBody AuthStatusRequest request) {
        return authService.getAuthGroupStatus(request);
    }

    @ModuleFunc("运营商认证-发送验证码")
    @PostMapping("/operatorSendSmsCode")
    public void operatorSendSmsCode() {
        authService.operatorSendSmsCode();
    }

    @ModuleFunc("运营商认证-验证码认证")
    @PostMapping("/operatorAuth")
    public void operatorAuth(@Valid @RequestBody PeratorAuthRequest request) {
        authService.operatorAuth(request);
    }

    @ModuleFunc(value = "专业信息认证", printParameterLength = 300)
    @PostMapping("/professional")
    public void professionalAuth(@Valid @RequestBody ProfessionalRequest request) {
        authService.professionalAuth(request);
    }

    @IgnoreHeader
    @ModuleFunc("facebook认证")
    @PostMapping("/facebook")
    public void facebookAuth(@Valid @RequestBody FacebookRequest request) {
        authService.facebookAuth(request);
    }

    @IgnoreHeader
    @ModuleFunc("ins认证")
    @PostMapping("/ins")
    public void insAuth(@Valid @RequestBody InsRequest request) {
        authService.insAuth(request);
    }

    @ModuleFunc("facebook和ins认证时数据推风控")
    @PostMapping("/facebookAndIns")
    public void facebookAndIns(@Valid @RequestBody FacebookInsRequest request){
        authService.facebookAndIns(request);
    }

}
