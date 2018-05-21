package com.hzed.easyget.application.service;

import com.hzed.easyget.controller.model.LoginBycodeRequest;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.WarnException;
import com.hzed.easyget.infrastructure.model.Response;
import com.hzed.easyget.infrastructure.repository.UserRepository;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.persistence.auto.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        //更新用户最后登录时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String strDate=sdf.format(new Date());
        user.setLastLoginTime(DateUtil.strToLocalDateTime(strDate));
        userRepository.updateLastLoginTime(user);
        return Response.getSuccessResponse();
    }
}