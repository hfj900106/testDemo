package com.hzed.easyget.application.service;

import com.hzed.easyget.application.enums.EnvEnum;
import com.hzed.easyget.infrastructure.config.redis.RedisService;
import com.hzed.easyget.infrastructure.consts.ComConsts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 验证码校验
 * @author wuchengwu
 * @date 2018/5/21
 */
@Slf4j
@Service
public class SmsCodeService {

    @Value("${spring.profiles.active}")
    private String env;
    @Value("${spring.application.name}")
    private String appName;
    @Autowired
    private RedisService redisService;

    public boolean checkSmsCode(String mobile, String smsCode) {

        if(ComConsts.DEFAULT_SMS_CODE.equals(smsCode)&& EnvEnum.isTestEnv(env)){

            log.info("使用测试环境验证码：{}，当前环境是:{}",ComConsts.DEFAULT_SMS_CODE,env);
        }else{
            //获取缓冲数据
            String cacheSmsCode = redisService.getCache(ComConsts.SMS_CODE);
            log.info("校验验证码，手机号：{}，缓存验证码：{}，参数验证码：{}", mobile, cacheSmsCode, smsCode);
            if(!smsCode.equals(cacheSmsCode)){

                return false;
            }

        }

        return true;
    }
}