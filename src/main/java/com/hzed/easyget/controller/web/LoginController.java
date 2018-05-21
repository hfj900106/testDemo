package com.hzed.easyget.controller.web;


import com.hzed.easyget.application.service.LoginServicce;
import com.hzed.easyget.controller.model.LoginBycodeRequest;
import com.hzed.easyget.controller.model.SmsCodRequest;
import com.hzed.easyget.controller.model.SmsCodResponse;
import com.hzed.easyget.infrastructure.annotation.ExceptionAnno;
import com.hzed.easyget.infrastructure.annotation.ModuleFunc;
import com.hzed.easyget.infrastructure.annotation.TokenIgnore;
import com.hzed.easyget.infrastructure.model.Response;
import com.hzed.easyget.infrastructure.utils.SMSUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户登录
 * @author hfj
 * @date 2018/2132/
 */

@Slf4j
@ExceptionAnno
@RestController
@RequestMapping("/hzed/easy-get/login")
public class LoginController {


    @Autowired
    private LoginServicce loginServicce;

    @TokenIgnore
    @ModuleFunc("发送短信验证码")
    @PostMapping("/sendSmsCode")
    public Response<SmsCodResponse> sendSmsCode(@RequestBody SmsCodRequest request) {

        //获取短信验证码
        SmsCodResponse smsCodResponse = SMSUtil.sendCode(request.getMobile());
        //保存到数据库

        //保存到Redis

        return Response.getSuccessResponse(smsCodResponse);
    }

    /**
     * 手机验证码登录
     * @param
     */

    @TokenIgnore
    @ModuleFunc("验证码登录")
    @PostMapping("/loginByCode")
    public Response loginByCode(@RequestBody LoginBycodeRequest params){


        return loginServicce.loginByCode(params);
    }
}
