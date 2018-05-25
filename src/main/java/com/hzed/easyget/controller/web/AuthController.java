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
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @ModuleFunc("通讯录上传")
    @PostMapping("/contacts")
    public Response contacts(@RequestBody ContactsRequest request) {
        authService.authContacts(request);
        return Response.getSuccessResponse();
    }

    @ModuleFunc("短信上传")
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

    @ModuleFunc("身份信息认证")
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
}
