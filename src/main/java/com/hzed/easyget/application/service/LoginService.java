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
import com.hzed.easyget.infrastructure.exception.BaseBizException;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.model.GlobalHead;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.repository.SmsLogRepository;
import com.hzed.easyget.infrastructure.repository.UserRepository;
import com.hzed.easyget.infrastructure.repository.UserTokenRepository;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.JwtUtil;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import com.hzed.easyget.infrastructure.utils.SmsUtils;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.SmsLog;
import com.hzed.easyget.persistence.auto.entity.User;
import com.hzed.easyget.persistence.auto.entity.UserToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
        //校验验证码
        checkSmsCode(mobile, smsCode);

        // 用户是否存在,不存在去注册
        User user = userRepository.findByMobile(mobile);

        if (user == null) {
            user = new User();
            user.setId(IdentifierGenerator.nextId());
            user.setMobileAccount(mobile);
            user.setPlatform(platform);
            userRepository.insert(user);
        }

        Long userId = user.getId();
        // 生成token
        GlobalUser newUserToken = GlobalUser.builder().userId(userId).mobile(mobile).build();
        String token = JwtUtil.createToken(newUserToken);
        UserToken userToken = userTokenRepository.findByUserIdAndImei(userId, imei);
        if (userToken != null) {
            UserToken userTokenUpdate = new UserToken();
            userTokenUpdate.setId(userToken.getId());
            userTokenUpdate.setUpdateTime(LocalDateTime.now());
            userTokenUpdate.setToken(token);
            userTokenUpdate.setImei(imei);
            userTokenUpdate.setExpireTime(DateUtil.addDays(LocalDateTime.now(), systemProp.getTokenExpire()));
            userTokenRepository.updateByUserIdAndImei(userTokenUpdate);
        } else {
            userToken = new UserToken();
            userToken.setId(IdentifierGenerator.nextId());
            userToken.setUserId(userId);
            userToken.setToken(token);
            userToken.setImei(imei);
            userToken.setExpireTime(DateUtil.addDays(LocalDateTime.now(), systemProp.getTokenExpire()));
            userToken.setCreateTime(LocalDateTime.now());
            userTokenRepository.insertByUserIdAndImei(userToken);
        }

        //放入redis 3个小时
        redisService.setCache(RedisConsts.TOKEN + RedisConsts.SPLIT + String.valueOf(userId) + RedisConsts.SPLIT + imei, token, 3 * 3600L);

        // TODO 更新用户最后登录时间
///        userRepository.updateLastLoginTime(User.builder().id(userId).lastLoginTime(LocalDateTime.now()).build());

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
            throw new ComBizException(BizCodeEnum.FREQUENTLY_SEND);
        }
        if(StringUtils.isNotBlank(redisService.getCache(RedisConsts.LOGIN_PIC_CODE_SEND + RedisConsts.SPLIT + mobile))){
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
     * 随机字符串，去掉易混淆的字符
     */
    private String codeStr = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
    /**
     * 绘制字符串
     */
    private String drowString(Graphics g, String randomString, int i) {

        Random random = new Random();
        g.setFont(new Font("Fixedsys", Font.CENTER_BASELINE, 18));
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(codeStr.length())));
        randomString += rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 13 * i, 16);
        return randomString;
    }

    /**
     * 获取随机的字符
     */
    public String getRandomString(int num) {
        return String.valueOf(codeStr.charAt(num));
    }
    /**
     * 生成随机图片
     */
    public PictureCodeResponse getPictureCode(String mobile) {
        PictureCodeResponse codeResponse = new PictureCodeResponse();
        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(80, 26, BufferedImage.TYPE_INT_BGR);
        // 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        Graphics graphics = image.getGraphics();
        graphics.fillRect(0, 0, 80, 26);
        graphics.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
        graphics.setColor(new Color(225, 255, 255));
        // 绘制随机字符
        String randomString = "";
        for (int i = 1; i <= 4; i++) {
            randomString = drowString(graphics, randomString, i);
        }
        //保存到Redis，五分钟有效时间
        redisService.setCache(RedisConsts.PICTURE_CODE + RedisConsts.SPLIT + mobile, randomString, 300L);
        //销毁graphics图形界面资源
        graphics.dispose();
        //返回图片二进制
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", bos);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseBizException(BizCodeEnum.SERVICE_EXCEPTION.getCode(), "获取验证码图片时，图片转二进制异常" + e.getStackTrace().toString());
        } finally {
            if (bos != null) {
                try {
                    bos.close(); // 关闭流
                } catch (IOException e) {
                    throw new BaseBizException(BizCodeEnum.SERVICE_EXCEPTION.getCode(), "获取验证码图片时，关闭流异常" + e.getStackTrace().toString());
                }
            }
        }
        codeResponse.setPicture(bos.toByteArray());
        return codeResponse;
    }

    /**
     * 验证码验证
     */
    public void checkPictureCode(String mobile, String code) {
        //获取缓存数据
        String cacheCode = redisService.getCache(RedisConsts.PICTURE_CODE + RedisConsts.SPLIT + mobile);
        if (StringUtils.isBlank(cacheCode) || !code.equals(cacheCode)) {
            throw new ComBizException(BizCodeEnum.ILLEGAL_PICTURECODE);
        }
    }



}