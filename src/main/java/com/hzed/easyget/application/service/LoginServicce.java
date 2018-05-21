package com.hzed.easyget.application.service;

import com.hzed.easyget.controller.model.LoginBycodeRequest;
import com.hzed.easyget.infrastructure.model.Response;
import org.springframework.stereotype.Service;

/**
 * @author wuchengwu
 * @since 2018/5/21
 */
@Service
public class LoginServicce {

    public Response loginByCode(LoginBycodeRequest params) {

        return Response.getSuccessResponse();
    }
}