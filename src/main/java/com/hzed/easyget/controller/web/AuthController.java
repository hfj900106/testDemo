package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.ContactsService;
import com.hzed.easyget.application.service.MessagesService;
import com.hzed.easyget.application.service.SmsAuthService;
import com.hzed.easyget.controller.model.ContactsRequest;
import com.hzed.easyget.controller.model.MessagesRequest;
import com.hzed.easyget.controller.model.SmsAuthRequest;
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
 * @author hfj
 * @date 2018/5/22
 */

@Slf4j
@ExceptionAnno
@RestController
@RequestMapping("/hzed/easy-get/auth")
public class AuthController {
    @Autowired
    private ContactsService contactsService;

    @Autowired
    private MessagesService messagesService;

    @Autowired
    private SmsAuthService smsAuthService;

    @ModuleFunc("通讯录授权")
    @PostMapping("/contacts")
    public Response contacts(@RequestBody ContactsRequest request) {

        contactsService.authContacts(request);
        return Response.getSuccessResponse();

    }

    @ModuleFunc("短信授权")
    @PostMapping("/messages")
    public Response messages(@RequestBody MessagesRequest request) {

        messagesService.authMessages(request);
        return Response.getSuccessResponse();

    }

    @ModuleFunc("运营商认证")
    @PostMapping("/sms")
    public Response smsAuth(@RequestBody SmsAuthRequest request) {

        smsAuthService.smsAuth(request);
        return Response.getSuccessResponse();

    }


}
