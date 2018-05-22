package com.hzed.easyget.application.service;

import com.hzed.easyget.controller.model.LoginByCodeRequest;
import com.hzed.easyget.controller.model.LoginByCodeResponse;
import com.hzed.easyget.controller.model.SmsCodRequest;
import com.hzed.easyget.infrastructure.config.redis.RedisService;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.WarnException;
import com.hzed.easyget.infrastructure.repository.SmsLogRepository;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.repository.UserRepository;
import com.hzed.easyget.infrastructure.utils.SmsUtil;
import com.hzed.easyget.persistence.auto.entity.SmsLog;
import com.hzed.easyget.infrastructure.utils.JwtUtil;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

/**
 * @author wuchengwu
 * @date 2018/5/21
 */
@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SmsLogRepository smsLogRepository;

    @Autowired
    private RedisService redisService;

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
        GlobalUser userToken = GlobalUser.builder().userId(user.getId()).mobile(mobile).build();
        String token = JwtUtil.createToken(userToken);

        return null;
    }

    private void saveUser(String mobile) {
        User user = new User();
        user.setId(IdentifierGenerator.nextId());
        user.setMobileAccount(mobile);
        user.setPlatform("android");
        user.setIsLocked(false);
        user.setIsBlacklist(false);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userRepository.insert(user);
    }


    public void sendSmsCode(SmsCodRequest request){
        //获取短信验证码
        Map<String , String> map = SmsUtil.sendCode(request.getMobile());

        //保存到数据库短信记录表
        SmsLog smsLog = new SmsLog();
        smsLog.setId(IdentifierGenerator.nextId());
        smsLog.setCreateTime(LocalDateTime.now());
        smsLog.setContent(map.get("content"));
        smsLog.setMobile(request.getMobile());
        smsLogRepository.insertSelective(smsLog);

        //保存到Redis
        redisService.setCache("smsCode", map.get("smsCode"), 120L);


    }
}