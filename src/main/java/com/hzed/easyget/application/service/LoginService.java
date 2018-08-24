package com.hzed.easyget.application.service;

import com.google.common.collect.Maps;
import com.hzed.easyget.application.enums.BidEnum;
import com.hzed.easyget.application.enums.EnvEnum;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.config.SystemProp;
import com.hzed.easyget.infrastructure.config.redis.RedisService;
import com.hzed.easyget.infrastructure.consts.ComConsts;
import com.hzed.easyget.infrastructure.consts.RedisConsts;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.WarnException;
import com.hzed.easyget.infrastructure.model.GlobalHead;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.repository.UserRepository;
import com.hzed.easyget.infrastructure.repository.UserTokenRepository;
import com.hzed.easyget.infrastructure.utils.*;
import com.hzed.easyget.infrastructure.utils.id.IDGenerator;
import com.hzed.easyget.persistence.auto.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author wuchengwu
 * @date 2018/5/21
 */
@Slf4j
@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserTokenRepository userTokenRepository;
    @Autowired
    private SystemProp systemProp;
    @Autowired
    private SaService saService;
    @Autowired
    private SmsService smsService;
    @Autowired
    private DictService dictService;

    /**
     * 用户登录注册
     */
    public LoginByCodeResponse loginByCode(LoginByCodeRequest request) {
        String mobile = request.getMobile();
        String smsCode = request.getSmsCode();
        // 格式化手机号
        mobile = mobileFormat(mobile);
        // 校验验证码
        checkSmsCode(mobile, smsCode);
        // 用户是否存在,不存在去注册
        User user = userRepository.findByMobile(mobile);
        // 新用户标志
        boolean isNew = user == null ? true : false;
        // 用户ID
        Long userId = isNew ? IDGenerator.nextId() : user.getId();
        // 生成token
        String token = JwtUtil.createToken(GlobalUser.builder().userId(userId).mobile(mobile).build());
        // 登录操作
        login(mobile, request.getDevice(), isNew, userId, token);
        // 神策记录
        saService.saLogin(userId, request.getAnonymousId());
        // 删除验证码缓存
        redisService.clearCache(RedisConsts.SMS_CODE + RedisConsts.SPLIT + mobile);
        // 获取首次登录的client
        String client = getFirstLoginClient(mobile);

        return LoginByCodeResponse.builder().token(token).userId(userId).isNew(isNew).client(client).build();
    }

    /**
     * 用户facebook登录注册
     */
    public LoginByCodeResponse loginByFacebook(LoginByFacebookRequest request) {
        String mobile = request.getMobile();
        String md5 = request.getMd5();

        // 校验是否我们平台发来的请求，是-通过 否-拦截
        if (!MD5Utils.verify(mobile, md5)) {
            log.error("请求校验失败，手机号：", mobile);
            throw new WarnException(BizCodeEnum.UNKNOWN_EXCEPTION);
        }

        // 格式化手机号
        mobile = mobileFormat(mobile);
        // 用户是否存在，不存在去注册
        User user = userRepository.findByMobile(mobile);
        // 新用户标志
        boolean isNew = user == null ? true : false;
        // 用户ID
        Long userId = isNew ? IDGenerator.nextId() : user.getId();
        // 生成token
        String token = JwtUtil.createToken(GlobalUser.builder().userId(userId).mobile(mobile).build());
        // 登录操作
        login(mobile, request.getDevice(), isNew, userId, token);
        // 神策记录
        saService.saLogin(userId, request.getAnonymousId());
        // 获取首次登录的client
        String client = getFirstLoginClient(mobile);

        return LoginByCodeResponse.builder().token(token).userId(userId).isNew(isNew).client(client).build();
    }

    /**
     * 登录
     */
    private void login(String mobile, String device, Boolean isNew, Long userId, String token) {
        GlobalHead globalHead = RequestUtil.getGlobalHead();
        String platform = globalHead.getPlatform();
        String imei = globalHead.getImei();
        String ip = RequestUtil.getIp();
        // token失效周期
        LocalDateTime expireTime = DateUtil.addDays(LocalDateTime.now(), systemProp.getTokenExpire());
        // 用户为空，那么该用户的token表数据肯定也为空
        if (isNew) {
            User user = User.builder().id(userId).mobileAccount(mobile).platform(platform).client(BidEnum.INDONESIA_APP.getCode()).imei(imei).build();
            UserToken uTokenInsert = UserToken.builder().id(IDGenerator.nextId()).userId(userId).token(token).imei(imei).expireTime(expireTime).createTime(LocalDateTime.now()).build();
            UserLogin uLoginInsert = buildUserLogin(userId, platform, ip, device);
            UserStatus uStatusInsert = UserStatus.builder().id(IDGenerator.nextId()).userId(userId).isBlacklist(false).isLock(false).remark("注册").build();
            userRepository.insertUserAndTokenAndLoginAndStatus(user, uTokenInsert, uLoginInsert, uStatusInsert);
        } else {
            UserToken userToken = userTokenRepository.findByUserIdAndImei(userId, imei);
            if (ObjectUtils.isEmpty(userToken)) {
                // 说明用户换了设备 新增token
                UserToken uTokenInsert = UserToken.builder().id(IDGenerator.nextId()).userId(userId).token(token).imei(imei).expireTime(expireTime).createTime(LocalDateTime.now()).build();
                UserLogin uLoginInsert = buildUserLogin(userId, platform, ip, device);
                userRepository.insertTokenAndLogin(uTokenInsert, uLoginInsert);
            } else {
                // 老用户登录都要刷新token表，刷新过期时间
                UserToken uTokenUpdate = UserToken.builder().id(IDGenerator.nextId()).userId(userId).token(token).imei(imei).expireTime(expireTime).updateTime(LocalDateTime.now()).build();
                UserLogin uLoginInsert = buildUserLogin(userId, platform, ip, device);
                userRepository.updateTokenAndInsertLogin(uTokenUpdate, uLoginInsert);
            }
        }
        // token放入redis 3个小时
        redisService.setCache(RedisConsts.TOKEN + RedisConsts.SPLIT + String.valueOf(userId) + RedisConsts.SPLIT + imei, token, 10800L);
    }

    private String getFirstLoginClient(String mobile) {
        User userQuery = userRepository.findByMobile(mobile);
        // 查询登录记录标，只有一条登录记录就算首次登录
        List<UserLogin> userLoginList = userRepository.getUserLoginsByUserId(userQuery.getId());
        return userLoginList.size() == 1 ? userQuery.getClient() : "";
    }

    /**
     * H5页面注册
     */
    public void registerH5(RegisterH5Request request) {
        GlobalHead globalHead = RequestUtil.getGlobalHead();
        String smsCode = request.getSmsCode();
        String clinet = request.getFromCode();
        String mobile = request.getMobile();

        // 格式化手机号
        mobile = mobileFormat(mobile);
        if (userRepository.findByMobile(mobile) != null) {
            throw new WarnException(BizCodeEnum.EXIST_USER);
        }
        //校验验证码
        checkSmsCode(mobile, smsCode);

        Long userId = IDGenerator.nextId();
        User userInsert = User.builder().id(userId).mobileAccount(mobile).platform(globalHead.getPlatform()).client(clinet).imei(globalHead.getImei()).remark("H5注册").build();
        UserStatus uStatusInsert = UserStatus.builder().id(IDGenerator.nextId()).userId(userId).isBlacklist(false).isLock(false).remark("注册").build();
        userRepository.insertUserAndStatus(userInsert, uStatusInsert);
    }

    private UserLogin buildUserLogin(Long userId, String platform, String ip, String device) {
        UserLogin userLogin = new UserLogin();
        userLogin.setId(IDGenerator.nextId());
        userLogin.setUserId(userId);
        userLogin.setPlatform(platform);
        userLogin.setClient((byte) 1);
        userLogin.setLoginIp(ip);
        userLogin.setLoginDevice(device);
        userLogin.setLoginTime(LocalDateTime.now());
        userLogin.setCreateBy(userId);
        return userLogin;
    }

    /**
     * 校验验证码
     */
    private void checkSmsCode(String mobile, String smsCode) {

        if (EnvEnum.isTestEnv(systemProp.getEnv()) && "0000".equals(smsCode)) {
            return;
        }

        //获取缓存数据
        String cacheSmsCode = redisService.getCache(RedisConsts.SMS_CODE + RedisConsts.SPLIT + mobile);

        if (StringUtils.isBlank(cacheSmsCode) || !cacheSmsCode.equals(smsCode)) {
            throw new WarnException(BizCodeEnum.ILLEGAL_SMSCODE);
        }
        // 验证成功清除缓存数据
        redisService.clearCache(RedisConsts.SMS_CODE + RedisConsts.SPLIT + mobile);
    }

    public void sendSmsCode(SmsCodeRequest request) {
        SystemProp systemProp = SpringContextUtil.getBean(SystemProp.class);
        String mobile = request.getMobile();
        log.info("发送验证码手机号：{}", mobile);
        // 格式化手机号
        mobile = mobileFormat(mobile);
        // 校验是否三大运营商手机号
        checkMobile(mobile);

        GlobalHead globalHead = RequestUtil.getGlobalHead();
        String isH5 = globalHead.getPlatform();
        if (ComConsts.H5.equals(isH5)) {
            User user = userRepository.findByMobile(mobile);
            if (user != null) {
                throw new WarnException(BizCodeEnum.EXIST_USER);
            }
        }
        String hasBeenSend = redisService.getCache(RedisConsts.LOGIN_SMS_CODE_SEND + RedisConsts.SPLIT + mobile);
        if (StringUtils.isNotBlank(hasBeenSend)) {
            //发送过于频繁
            throw new WarnException(BizCodeEnum.FREQUENTLY_SMS_SEND);
        }
        if (StringUtils.isNotBlank(redisService.getCache(RedisConsts.LOGIN_PIC_CODE_SEND + RedisConsts.SPLIT + mobile))) {
            //10分钟内重发需要验证码
            throw new WarnException(BizCodeEnum.PIC_CODE_TO_CHECK);
        }
        String code = smsService.getCode();

        List<DictResponse> smsContent1 = dictService.getDictByDicCodeAndLanguage(ComConsts.SMS_CONTENT_1, systemProp.getLocal());
        if (ObjectUtils.isEmpty(smsContent1)) {
            log.error("没有配置短信模板");
            throw new WarnException(BizCodeEnum.UNKNOWN_EXCEPTION);
        }
        String dicValue = smsContent1.get(0).getDictValue();
        //替换验证码
        String content = StringUtils.replace(dicValue, "{0}", code);
        smsService.sendAndSaveSms(mobile, content, "短信验证码");

        //保存到Redis，手机验证码30分钟有效
        redisService.setCache(RedisConsts.SMS_CODE + RedisConsts.SPLIT + mobile, code, 1800L);
        //60秒后可以重发
        redisService.setCache(RedisConsts.LOGIN_SMS_CODE_SEND + RedisConsts.SPLIT + mobile, mobile, 60L);
        //10分钟内重发需要验证码
        redisService.setCache(RedisConsts.LOGIN_PIC_CODE_SEND + RedisConsts.SPLIT + mobile, mobile, 600L);
    }

    /**
     * 检验3大运营商手机号
     * @param request
     */
    public void checkMobileBeforeSend(SmsCodeRequest request) {
        String mobile = request.getMobile();
        log.info("发送验证码手机号：{}", mobile);
        // 格式化手机号
        mobile = mobileFormat(mobile);
        // 校验是否三大运营商手机号
        checkMobile(mobile);
    }

    /**
     * 校验手机号是否三大运营商手机号
     *
     * @param mobile
     */
    private void checkMobile(String mobile) {
        if (mobile.length() < 4) {
            throw new WarnException(BizCodeEnum.MOBILE_INCORRECT);
        }
        // 手机号码前四位
        String mobilePre = mobile.substring(0, 4);

        Map<String, List<String>> mobilePrefixMap = Maps.newHashMap();
        SystemProp.MobilePrefix mobilePrefix = systemProp.getMobilePrefix();
        mobilePrefixMap.put("Indosat", Arrays.asList(mobilePrefix.getIndosat().split(",")));
        mobilePrefixMap.put("XL", Arrays.asList(mobilePrefix.getXL().split(",")));
        mobilePrefixMap.put("Telkomsel", Arrays.asList(mobilePrefix.getTelkomsel().split(",")));

        for (Map.Entry<String, List<String>> entry : mobilePrefixMap.entrySet()) {
            for (String v : entry.getValue()) {
                if (v.equals(mobilePre)) {
                    return;
                }
            }
        }

        // 不在则直接抛异常
        throw new WarnException(BizCodeEnum.MOBILE_ILLEGAL, mobilePrefixMap);
    }


    /**
     * 生成随机图片
     */
    public PictureCodeResponse getPictureCode(String mobile) {
        // 格式化手机号
        mobile = mobileFormat(mobile);
        Map<String, String> map = PicUtil.getPictureCode();
        //保存到Redis，五分钟有效时间
        redisService.setCache(RedisConsts.PICTURE_CODE + RedisConsts.SPLIT + mobile, map.get("code"), 300L);
        PictureCodeResponse response = new PictureCodeResponse();
        response.setPicture(map.get("picStr"));
        log.info("图片验证码：{}", map.get("code"));
        return response;
    }

    /**
     * 验证码验证，不区分大小写
     */
    public void checkPictureCode(String mobile, String code) {
        // 格式化手机号
        mobile = mobileFormat(mobile);
        // 获取缓存数据
        String cacheCode = redisService.getCache(RedisConsts.PICTURE_CODE + RedisConsts.SPLIT + mobile);
        if (StringUtils.isBlank(cacheCode) || !code.equalsIgnoreCase(cacheCode)) {
            throw new WarnException(BizCodeEnum.PIC_CODE_ERROR);
        }

        // 验证通过则删除10分钟重发标识，等发送之后会重新加上
        redisService.clearCache(RedisConsts.LOGIN_PIC_CODE_SEND + RedisConsts.SPLIT + mobile);
    }

    /**
     * 格式化印尼手机号，将前缀为0062、62、+62 改为 0 ，没有前缀的加 0
     *
     * @param mobile
     * @return
     */
    public String mobileFormat(String mobile) {
        log.info("格式化前手机号：{}", mobile);
        if (mobile.length() < 4) {
            // 手机号码太短
            throw new WarnException(BizCodeEnum.MOBILE_ILLEGAL);
        }
        String str1 = "0062";
        String str2 = "62";
        String str3 = "+62";
        String str4 = "0";

        if (str1.equals(mobile.substring(0, 4))) {
            mobile = str4 + mobile.substring(4);
        } else if (str2.equals(mobile.substring(0, 2))) {
            mobile = str4 + mobile.substring(2);
        } else if (str3.equals(mobile.substring(0, 3))) {
            mobile = str4 + mobile.substring(3);
        } else if (!mobile.startsWith(str4)) {
            mobile = str4 + mobile;
        }
        log.info("格式化后手机号：{}", mobile);
        return mobile;
    }

    /**
     * 返回Facebook发短信标识
     */
    public FacebookSmsResponse getFacebookSms() {
        // 获取字典配置
        Dict dict = dictService.getDictByCode("facebook_sms");
        if (ObjectUtils.isEmpty(dict)) {
            log.error("没有配置facebook_sms");
            throw new WarnException(BizCodeEnum.UNKNOWN_EXCEPTION);
        }
        boolean facebook_sms = Boolean.valueOf(dict.getDicValue());
        log.info("当前facebook_sms为：{}", facebook_sms);
        FacebookSmsResponse response = new FacebookSmsResponse();
        response.setFacebookSms(facebook_sms);
        return response;
    }

}