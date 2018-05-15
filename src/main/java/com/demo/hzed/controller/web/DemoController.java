package com.demo.hzed.controller.web;

import com.demo.hzed.application.service.DemoService;
import com.demo.hzed.infrastructure.annotation.ModuleLog;
import com.demo.hzed.infrastructure.model.Response;
import com.demo.hzed.persistence.auto.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * demo
 *
 * @author guichang
 * @since 2018/4/3
 */

@RestController
@RequestMapping("/hzed/demo/users")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @ModuleLog("查询用户")
    @GetMapping("/{mobile}/{idCard}")
    public Response<User> users(@PathVariable("mobile") String mobile, @PathVariable("idCard") String idCard) {
        User user = demoService.getUserByMobileAndIdcard(mobile, idCard);
        return Response.getSuccessResponse(user);
    }




}