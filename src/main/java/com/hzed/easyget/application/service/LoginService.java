package com.hzed.easyget.application.service;

import com.hzed.easyget.controller.model.LoginByCodeRequest;
import com.hzed.easyget.controller.model.LoginByCodeResponse;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.WarnException;
import com.hzed.easyget.infrastructure.repository.UserRepository;
import com.hzed.easyget.persistence.auto.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author wuchengwu
 * @date 2018/5/21
 */
@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public LoginByCodeResponse loginByCode(LoginByCodeRequest params) {

        String mobile = params.getMobile();
        String smsCode = params.getSmsCode();

        //用户是否存在,不存在去注册
        User user = userRepository.findByMobile(mobile);
        if (Objects.isNull(user)){
            //创建用户
            saveUser(mobile);
        }
        //校验验证码
        if(!SmsCodeService.cheSmsCode(mobile,smsCode)){
            throw new WarnException(BizCodeEnum.ERROR_SMSCODE);
        }
        //更新用户最后登录时间
        user.setLastLoginTime(LocalDateTime.now());
        userRepository.updateLastLoginTime(user);

        return null;
    }

    private void saveUser(String mobile) {
        User user = new User();
        user.setId(0L);
        user.setMobileAccount(mobile);
        user.setPlatform("android");
        user.setIsLocked(false);
        user.setIsBlacklist(false);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userRepository.insert(user);
    }
}