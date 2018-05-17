package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.DemoService;
import com.hzed.easyget.controller.model.UserRequest;
import com.hzed.easyget.controller.model.UserResponse;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.annotation.TokenIgnore;
import com.hzed.easyget.infrastructure.model.Response;
import com.hzed.easyget.persistence.auto.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * demo
 *
 * @author guichang
 * @since 2018/4/3
 */

@Slf4j
@ExceptionAnno
@RestController
@RequestMapping("/hzed/demo/users")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @TokenIgnore
    @ModuleFunc("查询用户")
    @PostMapping("/user")
    public Response<User> user(@RequestBody UserRequest request) {
        UserResponse response = demoService.getUserByMobileAndIdcard(request);
        return Response.getSuccessResponse(response);
    }




}