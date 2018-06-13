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

/**
 * 用户认证
 *
 * @author hfj
 * @date 2018/5/22
 */

@Slf4j
@ExceptionAnno
@RestController
@RequestMapping("/easy-get/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @ModuleFunc(value = "通讯录认证", isParameterValidate = false)
    @PostMapping("/contacts")
    public Response contacts(@RequestBody ContactsRequest request) {
        authService.authContacts(request);
        return Response.getSuccessResponse();
    }

    @ModuleFunc("短信认证")
    @PostMapping("/messages")
    public Response messages(@RequestBody MessagesRequest request) {
        authService.authMessages(request);
        return Response.getSuccessResponse();
    }

    @ModuleFunc("个人信息认证")
    @PostMapping("/personInfo")
    public Response personInfoAuth(@RequestBody PersonInfoAuthRequest request) {
        authService.authPersonInfo(request);
        return Response.getSuccessResponse();
    }

    @ModuleFunc(value = "身份证识别", isParameterPrint = false)
    @PostMapping("/idCardRecognition")
    public Response<IdCardRecognitionResponse> idCardRecognition(@RequestBody IdCardRecognitionRequest request) {
        return Response.getSuccessResponse(authService.idCardRecognition(request));
    }

    @ModuleFunc(value = "人脸识别", isParameterPrint = false)
    @PostMapping("/faceRecognition")
    public Response faceRecognition(@RequestBody FaceRecognitionRequest request) {
        authService.faceRecognition(request);
        return Response.getSuccessResponse();
    }

    @ModuleFunc(value = "身份信息认证", isParameterPrint = false)
    @PostMapping("/identityInfo")
    public Response identityInformationAuth(@RequestBody IdentityInfoAuthRequest request) {
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
    public Response operatorSendSmsCode(@RequestBody PeratorSendRequest request) {
        authService.operatorSendSmsCode(request);
        return Response.getSuccessResponse();
    }

    @ModuleFunc("/运营商认证-验证码认证")
    @PostMapping("/operatorAuth")
    public Response operatorAuth(PeratorAuthRequest request) {
        authService.operatorAuth(request);
        return Response.getSuccessResponse();
    }

    @ModuleFunc("专业信息认证")
    @PostMapping("/professional")
    public Response professionalAuth(@RequestBody ProfessionalRequest request) {
        authService.professionalAuth(request);
        return Response.getSuccessResponse();
    }

}
