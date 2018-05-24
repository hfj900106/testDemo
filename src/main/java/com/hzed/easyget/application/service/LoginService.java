package com.hzed.easyget.application.service;

import com.hzed.easyget.application.enums.EnvEnum;
import com.hzed.easyget.controller.model.LoginByCodeRequest;
import com.hzed.easyget.controller.model.LoginByCodeResponse;
import com.hzed.easyget.controller.model.SmsCodeRequest;
import com.hzed.easyget.infrastructure.config.redis.RedisService;
import com.hzed.easyget.infrastructure.consts.RedisConsts;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.repository.SmsLogRepository;
import com.hzed.easyget.infrastructure.repository.UserRepository;
import com.hzed.easyget.infrastructure.utils.JwtUtil;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.SmsLog;
import com.hzed.easyget.persistence.auto.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * @author wuchengwu
 * @date 2018/5/21
 */
@Service
public class LoginService {
    private static final String DEFAULT_SMS_CODE = "0000";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SmsLogRepository smsLogRepository;
    @Autowired
    private RedisService redisService;

    @Value("${spring.profiles.active}")
    private String env;

    public LoginByCodeResponse loginByCode(LoginByCodeRequest params) {

        String mobile = params.getMobile();
        String smsCode = params.getSmsCode();

        //校验验证码
        checkSmsCode(mobile, smsCode);

        // 用户是否存在,不存在去注册
        User user = userRepository.findByMobile(mobile);
        if (user == null) {
            user.setId(IdentifierGenerator.nextId());
            user.setMobileAccount(mobile);
            user.setPlatform("android");
            user.setIsLocked(false);
            user.setIsBlacklist(false);
            user.setCreateTime(LocalDateTime.now());
            userRepository.insert(user);
        }

        // 生成token
        GlobalUser userToken = GlobalUser.builder().userId(user.getId()).mobile(mobile).build();
        String token = JwtUtil.createToken(userToken);

        // 1、插入t_user_token表

        // 2、放入redis 3个小时

        // 刷新token接口 token未过期的情况
        // 1、从当前token拿到 GlobalUser 调用
//        String newToken = JwtUtil.createToken(oldGlobalUser);
        // 更新到t_user_token表 redis 3个小时
        // 将新token返回给APP


        //更新用户最后登录时间
        user.setLastLoginTime(LocalDateTime.now());
        userRepository.updateLastLoginTime(user);

        return LoginByCodeResponse.builder().token(token).build();
    }

    /**
     * 校验验证码
     */
    private void checkSmsCode(String mobile, String smsCode) {

        if (EnvEnum.isTestEnv(env) && DEFAULT_SMS_CODE.equals(smsCode)) {
            return;
        }

        //获取缓存数据
        String cacheSmsCode = redisService.getCache(mobile);

        if (StringUtils.isBlank(cacheSmsCode) || !cacheSmsCode.equals(smsCode)) {
            throw new ComBizException(BizCodeEnum.ILLEGAL_SMSCODE);
        }

    }

    public void sendSmsCode(SmsCodeRequest request) {
        String mobile = request.getMobile();
        String smsCodeKey = RedisConsts.SMS_CODE + ":" + mobile;
        String cacheSmsCode = redisService.getCache(smsCodeKey);
        if(StringUtils.isNotBlank(cacheSmsCode)) {
            throw new ComBizException(BizCodeEnum.ILLEGAL_REQUEST, "验证码发送频繁，请稍后重试");
        }
        String code = StringUtils.leftPad(String.valueOf(new Random().nextInt(9999)), 4, "0");
        String content = "您的注册验证码是：" + code + " ，两分钟内有效，欢迎使用本平台";
        // TODO 发送验证码 content

        // 保存到数据库短信记录表
        SmsLog smsLog = new SmsLog();
        smsLog.setId(IdentifierGenerator.nextId());
        smsLog.setCreateTime(LocalDateTime.now());
        smsLog.setContent(content);
        smsLog.setMobile(mobile);
        smsLog.setRemark("短信验证码");
        smsLogRepository.insertSelective(smsLog);
        //保存到Redis
        redisService.setCache(RedisConsts.SMS_CODE + ":" + mobile, content, 120L);
    }
}