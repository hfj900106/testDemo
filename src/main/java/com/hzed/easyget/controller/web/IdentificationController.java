package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.ContactsService;
import com.hzed.easyget.application.service.LoginService;
import com.hzed.easyget.controller.model.ContactsRequest;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.model.GlobalHeadr;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.model.Response;
import com.hzed.easyget.infrastructure.utils.JwtUtil;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
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
@RequestMapping("/hzed/easy-get/identification")
public class IdentificationController {


    @Autowired
    private ContactsService contactsService;

    @ModuleFunc("通讯录认证")
    @PostMapping("/contacts")
    public Response contacts(@RequestBody ContactsRequest request) {

        contactsService.contacts(request);
        return Response.getSuccessResponse();

    }



}
