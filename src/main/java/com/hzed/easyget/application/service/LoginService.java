package com.hzed.easyget.application.service;

import com.hzed.easyget.application.enums.EnvEnum;
import com.hzed.easyget.controller.model.LoginByCodeRequest;
import com.hzed.easyget.controller.model.LoginByCodeResponse;
import com.hzed.easyget.controller.model.PictureCodeResponse;
import com.hzed.easyget.controller.model.SmsCodeRequest;
import com.hzed.easyget.infrastructure.config.SystemProp;
import com.hzed.easyget.infrastructure.config.redis.RedisService;
import com.hzed.easyget.infrastructure.consts.RedisConsts;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.model.GlobalHead;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.repository.SmsLogRepository;
import com.hzed.easyget.infrastructure.repository.UserRepository;
import com.hzed.easyget.infrastructure.repository.UserTokenRepository;
import com.hzed.easyget.infrastructure.utils.*;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

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
    @Autowired
    private UserTokenRepository userTokenRepository;
    @Autowired
    private SystemProp systemProp;

    @Value("${spring.profiles.active}")
    private String env;

    public LoginByCodeResponse loginByCode(LoginByCodeRequest request) {

        GlobalHead globalHead = RequestUtil.getGlobalHead();
        String mobile = request.getMobile();
        String smsCode = request.getSmsCode();
        String platform = globalHead.getPlatform();
        String imei = globalHead.getImei();
        String device = request.getDevice();
        String ip = RequestUtil.getIp();
        //校验验证码
        checkSmsCode(mobile, smsCode);

        // 用户是否存在,不存在去注册
        User user = userRepository.findByMobile(mobile);
        Long userId;
        String token;
        //用户为空，那么该用户的token肯定也为空
        if (user == null) {
            userId = IdentifierGenerator.nextId();
            //build User
            user = new User();
            user.setId(userId);
            user.setMobileAccount(mobile);
            user.setPlatform(platform);
            user.setClient("Rupiah Get");
            user.setImei(RequestUtil.getGlobalHead().getImei());
            // 生成token
            GlobalUser newUserToken = GlobalUser.builder().userId(userId).mobile(mobile).build();
            token = JwtUtil.createToken(newUserToken);
            //build UserToken
            UserToken userToken = buildUserToken(IdentifierGenerator.nextId(), userId, token, imei);
            userToken.setCreateTime(LocalDateTime.now());
            // UserLogin
            UserLogin userLogin = buildUserLogin(userId, platform, ip, device);
            //UserStatus
            UserStatus userStatus = buildUserStatus(user.getId());
            userRepository.insertUserAndTokenAndLoginAndStatus(user, userToken, userLogin, userStatus);
        } else {
            userId = user.getId();
            // 生成token
            GlobalUser newUserToken = GlobalUser.builder().userId(userId).mobile(mobile).build();
            token = JwtUtil.createToken(newUserToken);
            UserToken userToken = userTokenRepository.findByUserIdAndImei(userId, imei);
            if (userToken != null) {
                // UserToken
                UserToken userTokenUpdate = buildUserToken(userToken.getId(), userId, token, imei);
                userTokenUpdate.setUpdateTime(LocalDateTime.now());
                // UserLogin
                UserLogin userLogin = buildUserLogin(userId, platform, ip, device);
                userRepository.updateTokenAndInsertLogin(userTokenUpdate, userLogin);
            } else {
                //有用户但是没token，说明过期要重新登录
                UserToken userTokenInsert = buildUserToken(IdentifierGenerator.nextId(), userId, token, imei);
                userTokenInsert.setCreateTime(LocalDateTime.now());
                // UserLogin
                UserLogin userLogin = buildUserLogin(userId, platform, ip, device);
                userRepository.insertTokenAndLogin(userTokenInsert, userLogin);
            }
        }
        //放入redis 3个小时
        redisService.setCache(RedisConsts.TOKEN + RedisConsts.SPLIT + String.valueOf(userId) + RedisConsts.SPLIT + imei, token, 3 * 3600L);
        return LoginByCodeResponse.builder().token(token).build();
    }

    private UserLogin buildUserLogin(Long userId, String platform, String ip, String device) {
        UserLogin userLogin = new UserLogin();
        userLogin.setId(IdentifierGenerator.nextId());
        userLogin.setUserId(userId);
        userLogin.setPlatform(platform);
        userLogin.setClient((byte) 1);
        userLogin.setLoginIp(ip);
        userLogin.setLoginDevice(device);
        userLogin.setLoginTime(LocalDateTime.now());
        userLogin.setCreateBy(userId);
        userLogin.setCreateTime(LocalDateTime.now());
        return userLogin;
    }

    private UserToken buildUserToken(Long id, Long userId, String token, String imei) {
        UserToken userToken = new UserToken();
        userToken.setId(id);
        userToken.setUserId(userId);
        userToken.setToken(token);
        userToken.setImei(imei);
        userToken.setExpireTime(DateUtil.addDays(LocalDateTime.now(), systemProp.getTokenExpire()));
        return userToken;
    }

    private UserStatus buildUserStatus(Long userId) {
        UserStatus userStatus = new UserStatus();
        userStatus.setId(IdentifierGenerator.nextId());
        userStatus.setUserId(userId);
        userStatus.setIsBlacklist(false);
        userStatus.setIsLock(false);
        userStatus.setCreateTime(LocalDateTime.now());
        userStatus.setRemark("注册");
        return userStatus;
    }

    /**
     * 校验验证码
     */
    private void checkSmsCode(String mobile, String smsCode) {

        if (EnvEnum.isTestEnv(env) && DEFAULT_SMS_CODE.equals(smsCode)) {
            return;
        }

        //获取缓存数据
        String cacheSmsCode = redisService.getCache(RedisConsts.SMS_CODE + RedisConsts.SPLIT + mobile);

        if (StringUtils.isBlank(cacheSmsCode) || !cacheSmsCode.equals(smsCode)) {
            throw new ComBizException(BizCodeEnum.ILLEGAL_SMSCODE);
        }

    }

    public void sendSmsCode(SmsCodeRequest request) {
        String mobile = request.getMobile();
        String hasBeenSend = redisService.getCache(RedisConsts.LOGIN_SMS_CODE_SEND + RedisConsts.SPLIT + mobile);
        if (StringUtils.isNotBlank(hasBeenSend)) {
            //发送过于频繁
            throw new ComBizException(BizCodeEnum.FREQUENTLY_SMS_SEND);
        }
        if (StringUtils.isNotBlank(redisService.getCache(RedisConsts.LOGIN_PIC_CODE_SEND + RedisConsts.SPLIT + mobile))) {
            //10分钟内重发需要验证码
            throw new ComBizException(BizCodeEnum.PIC_CODE_TO_CHECK);
        }
        String code = SmsUtils.getCode();
        String content = "您的注册验证码是：" + code + " ，两分钟内有效，欢迎使用本平台";
        //发送短信
        //todo 余额不足，暂时注销
//        SmsUtils.sendSms(mobile,content);
        // 保存到数据库短信记录表
        SmsLog smsLog = new SmsLog();
        smsLog.setId(IdentifierGenerator.nextId());
        smsLog.setCreateTime(LocalDateTime.now());
        smsLog.setContent(content);
        smsLog.setMobile(mobile);
        smsLog.setRemark("短信验证码");
        smsLogRepository.insertSelective(smsLog);
        //保存到Redis，手机验证码2分钟有效
        redisService.setCache(RedisConsts.SMS_CODE + RedisConsts.SPLIT + mobile, code, 120L);
        //60秒后可以重发
        redisService.setCache(RedisConsts.LOGIN_SMS_CODE_SEND + RedisConsts.SPLIT + mobile, mobile, 60L);
        //10分钟内重发需要验证码
        redisService.setCache(RedisConsts.LOGIN_PIC_CODE_SEND + RedisConsts.SPLIT + mobile, mobile, 600L);
    }


    /**
     * 生成随机图片
     */
    public PictureCodeResponse getPictureCode(String mobile) {
        Map<String, String> map = PicUtil.getPictureCode();
        //保存到Redis，五分钟有效时间
        redisService.setCache(RedisConsts.PICTURE_CODE + RedisConsts.SPLIT + mobile, map.get("code"), 300L);
        PictureCodeResponse response = new PictureCodeResponse();
        response.setPicture(map.get("picStr"));
        return response;
    }

    /**
     * 验证码验证
     */
    public void checkPictureCode(String mobile, String code) {
        //获取缓存数据
        String cacheCode = redisService.getCache(RedisConsts.PICTURE_CODE + RedisConsts.SPLIT + mobile);
        if (StringUtils.isBlank(cacheCode) || !code.equals(cacheCode)) {
            throw new ComBizException(BizCodeEnum.PIC_CODE_ERROR);
        } else {
            //验证通过则删除10分钟重发标识，等发送之后会重新加上
            redisService.clearCache(RedisConsts.LOGIN_PIC_CODE_SEND + RedisConsts.SPLIT + mobile);
        }
    }


}