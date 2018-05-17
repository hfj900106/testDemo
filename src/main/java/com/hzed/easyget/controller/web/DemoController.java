package com.hzed.easyget.controller.web;

import com.hzed.easyget.application.service.DemoService;
import com.hzed.easyget.controller.model.UserRequest;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleAnno;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
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

    @ModuleAnno("查询用户")
    @PostMapping("/user")
    public Response<User> user(@RequestBody UserRequest request) {
        if(true) throw new ComBizException(BizCodeEnum.INVALID_REQUEST);
        User user = demoService.getUserByMobileAndIdcard(request);
        return Response.getSuccessResponse(user);
    }




}