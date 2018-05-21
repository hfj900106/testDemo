package com.hzed.easyget.application.service;

import com.hzed.easyget.controller.model.LoginBycodeRequest;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.WarnException;
import com.hzed.easyget.infrastructure.model.Response;
import com.hzed.easyget.infrastructure.repository.UserRepository;
import com.hzed.easyget.persistence.auto.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author wuchengwu
 * @since 2018/5/21
 */
@Service
public class LoginServicce {

    @Autowired
    private UserRepository userRepository;

    public Response loginByCode(LoginBycodeRequest params) {

        String mobile = params.getMobile();
        String smsCode = params.getSmsCode();

        //用户是否存在,不存在去注册
        User user = userRepository.findByMobile(mobile);
        if (Objects.isNull(user)){
            throw new WarnException(BizCodeEnum.USER_NOT_EXISTS);
        }
        //校验验证码
        if(!SmsCodeService.cheSmsCode(mobile,smsCode)){
            throw new WarnException(BizCodeEnum.ERROR_SMSCODE);
        }
        return Response.getSuccessResponse();
    }
}