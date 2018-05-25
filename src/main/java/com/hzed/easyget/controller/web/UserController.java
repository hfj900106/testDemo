package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.UserService;
import com.hzed.easyget.controller.model.UserResponse;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 我的
 *
 * @author hfj
 * @date 2018/5/25
 */
@Slf4j
@ExceptionAnno
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ModuleFunc("我的")
    @PostMapping("/getAcountInfo")
    public Response<UserResponse> getAcountInfo() {
        return Response.getSuccessResponse(userService.getAcountInfo());
    }
}
