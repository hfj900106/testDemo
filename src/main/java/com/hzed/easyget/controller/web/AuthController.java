package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.AuthService;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

    @ModuleFunc("通讯录认证")
    @PostMapping("/contacts")
    public Response contacts(@Valid @RequestBody ContactsRequest request) {
        authService.authContacts(request);
        return Response.getSuccessResponse();
    }

    @ModuleFunc("短信认证")
    @PostMapping("/messages")
    public Response messages(@Valid @RequestBody MessagesRequest request) {
        authService.authMessages(request);
        return Response.getSuccessResponse();
    }

    @ModuleFunc("个人信息认证")
    @PostMapping("/personInfo")
    public Response personInfoAuth(@Valid @RequestBody PersonInfoAuthRequest request) {
        authService.authPersonInfo(request);
        return Response.getSuccessResponse();
    }

    @ModuleFunc(value = "身份证识别", isParameterPrint = false)
    @PostMapping("/idCardRecognition")
    public Response<IdCardRecognitionResponse> idCardRecognition(@Valid @RequestBody IdCardRecognitionRequest request) {
        return Response.getSuccessResponse(authService.idCardRecognition(request));
    }

    @ModuleFunc(value = "人脸识别", isParameterPrint = false)
    @PostMapping("/faceRecognition")
    public Response faceRecognition(@Valid @RequestBody FaceRecognitionRequest request) {
        authService.faceRecognition(request);
        return Response.getSuccessResponse();
    }

    @ModuleFunc(value = "身份信息认证", isParameterPrint = false)
    @PostMapping("/identityInfo")
    public Response identityInformationAuth(@Valid @RequestBody IdentityInfoAuthRequest request) {
        authService.identityInfoAuth(request);
        return Response.getSuccessResponse();
    }

    @ModuleFunc("/获取用户认证信息")
    @PostMapping("/getAuthStatus")
    public Response<AuthStatusResponse> getAuthStatus() {
        return Response.getSuccessResponse(authService.getAuthStatus());
    }

    @ModuleFunc("/运营商认证-发送验证码")
    @PostMapping("/operatorSendSmsCode")
    public Response operatorSendSmsCode() {
        authService.operatorSendSmsCode();
        return Response.getSuccessResponse();
    }

    @ModuleFunc("/运营商认证-验证码认证")
    @PostMapping("/operatorAuth")
    public Response operatorAuth(@Valid @RequestBody PeratorAuthRequest request) {
        authService.operatorAuth(request);
        return Response.getSuccessResponse();
    }

    @ModuleFunc("专业信息认证")
    @PostMapping("/professional")
    public Response professionalAuth(@Valid @RequestBody ProfessionalRequest request) {
        authService.professionalAuth(request);
        return Response.getSuccessResponse();
    }

}
