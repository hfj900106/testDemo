package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.AuthStatusService;
import com.hzed.easyget.controller.model.AuthStatusResponse;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户认证状态
 *
 * @author wuchengwu
 * @date 2018/5/23
 */
@ExceptionAnno
@RestController
@RequestMapping("/hzed/easy-get/auth")
public class AuthStatusController {

    @Autowired
    private AuthStatusService authStatusService;

    @ModuleFunc("/获取用户认证信息")
    @PostMapping("/getAuthStatus")
    public Response<AuthStatusResponse> getAuthStatus(){

        return Response.getSuccessResponse(authStatusService.getAuthStatus());
    }
}