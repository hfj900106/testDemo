package com.hzed.easyget.application.service;

import com.google.common.collect.Lists;
import com.hzed.easyget.application.enums.AuthCodeEnum;
import com.hzed.easyget.application.enums.AuthStatusEnum;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.config.aliyun.AliyunService;
import com.hzed.easyget.infrastructure.config.redis.RedisService;
import com.hzed.easyget.infrastructure.consts.RedisConsts;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.exception.NestedException;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.repository.*;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hzed.easyget.infrastructure.utils.RequestUtil.getGlobalUser;

/**
 * 认证信息公用
 *
 * @author hfj
 * @date 2018/5/23
 */

@Slf4j
@Service
public class AuthService {
    @Autowired
    private PersonInfoRepository personInfoRepository;
    @Autowired
    private WorkRepository workRepository;
    @Autowired
    private UserAuthStatusRepository authStatusRepository;
    @Autowired
    private ProfessionalRepository professionalRepository;
    @Autowired
    private AliyunService aliyunService;
    @Autowired
    private RestTemplate template;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserRepository userRepository;

    /**
     * 获取用户认证状态
     */
    public List<AuthStatusResponse> getAuthStatus() {
        List<AuthStatusResponse> authStatusList = Lists.newArrayList();
        GlobalUser globalUser = RequestUtil.getGlobalUser();
        Long userId = globalUser.getUserId();
        List<UserAuthStatus> userAuthStatus = authStatusRepository.getAuthStatusByUserId(userId);
        for (UserAuthStatus uas : userAuthStatus) {
            AuthItem auth = authStatusRepository.findAuthByCode(uas.getAuthCode());
            AuthStatusResponse authStatusResponse = new AuthStatusResponse();
            authStatusResponse.setCode(uas.getAuthCode());
            authStatusResponse.setStatus(String.valueOf(uas.getAuthStatus()));
            authStatusResponse.setIsUse(auth.getIsUse());
            authStatusList.add(authStatusResponse);
        }
        return authStatusList;
    }

    /**
     * 通讯录认证
     */
    public void authContacts(ContactsRequest request) {
//        GlobalUser user = getGlobalUser();
//        //写入用户授权信息返回值
//        //TODO 调风控接口
//        Long userAuthId = IdentifierGenerator.nextId();
//        AuthContent authContent = buildAuthContent(request.getContacts(), userAuthId, "通讯录授权");
//        UserAuthStatus userAuthStatus = buildUserAuthStatus(user.getUserId(), userAuthId, "通讯录授权");
//        authContentRepository.insertContactAndUserAuthStatus(authContent, userAuthStatus);
    }

    /**
     * 构建UserAuthStatus对象
     *
     * @param userId
     * @param remark
     */
    public UserAuthStatus buildUserAuthStatus(Long userId, String remark) {
        //保存到数据库短信记录表
        UserAuthStatus userAuthStatus = new UserAuthStatus();
        userAuthStatus.setId(IdentifierGenerator.nextId());
        userAuthStatus.setUserId(userId);
        userAuthStatus.setAuthCode(AuthCodeEnum.CONTACTS.getCode());
        userAuthStatus.setAuthStatus(Integer.valueOf(AuthStatusEnum.HAS_AUTH.getCode()));
        //过期时间，半年
        userAuthStatus.setExpireTime(DateUtil.addMonth(LocalDateTime.now(), 6));
        userAuthStatus.setCreateTime(LocalDateTime.now());
        userAuthStatus.setUpdateTime(LocalDateTime.now());
        userAuthStatus.setRemark(remark);
        return userAuthStatus;
    }

    /**
     * 短信认证
     */
    public void authMessages(MessagesRequest request) {
//        GlobalUser user = getGlobalUser();
//        //写入用户授权信息返回值
//        //TODO 调风控接口
//        Long userAuthId = IdentifierGenerator.nextId();
//        UserAuthStatus userAuthStatus = buildUserAuthStatus(user.getUserId(), userAuthId, "短信授权");
//        authContentRepository.insertContactAndUserAuthStatus(authContent, userAuthStatus);
    }

    /**
     * 运营商认证 - 发送验证码接口
     */
    public void operatorSendSmsCode(){
        GlobalUser user = getGlobalUser();
        String isSend = redisService.getCache(RedisConsts.IDENTITY_SMS_CODE_SEND + RedisConsts.SPLIT + user.getUserId());
        if (StringUtils.isNotBlank(isSend)) {
            //发送过于频繁
            throw new ComBizException(BizCodeEnum.FREQUENTLY_SEND);
        }
        User userInfo = userRepository.findById(user.getUserId());
        String realName = userInfo.getRealName();
        String identityCode = userInfo.getIdCardNo();
        if (StringUtils.isBlank(realName) || StringUtils.isBlank(identityCode)) {
            //未进行身份验证
            throw new ComBizException(BizCodeEnum.UN_IDENTITY_AUTH);
        }
        //redis存一个发送标识，避免频繁发送
        redisService.setCache(RedisConsts.IDENTITY_SMS_CODE_SEND + RedisConsts.SPLIT + userInfo.getId(), "operatorAuth", 60L);
        Map<String, Object> map = new HashMap<>(16);
        //TODO 待定参数
        map.put("sign", "1212");
        map.put("channelType", "1212");
        map.put("channelCode", "1212");
        map.put("realName", userInfo.getRealName());
        map.put("identityCode", userInfo.getIdCardNo());
        map.put("userMobile", userInfo.getMobileAccount());
        map.put("userId", userInfo.getId());
        //TODO 待验证方式
        String response = template.postForObject("/app/riskOperator/createTaskAndlogin", map, String.class);
        if (StringUtils.isNotBlank(response)) {
            //TODO 解析返回值并保存taskId 到 Redis ,一小时有效，调认证接口用
            String taskId = "wewe";
            redisService.setCache(RedisConsts.IDENTITY_AUTH_CODE + RedisConsts.SPLIT + userInfo.getId(), taskId, 3600L);
        }

    }

    /**
     * 运营商认证 - 输入验证码认证运行商
     */
    public void operatorAuth(PeratorAuthRequest request){
        GlobalUser user = getGlobalUser();
        String taskId = redisService.getCache(RedisConsts.IDENTITY_AUTH_CODE + RedisConsts.SPLIT + user.getUserId());
        if(StringUtils.isBlank(taskId)){
            throw new ComBizException(BizCodeEnum.OVER_TIME_SMS_CODE);
        }
        Map<String, Object> map = new HashMap<>(16);
        //TODO 待定参数
        map.put("sign", "1212");
        map.put("taskId", taskId);
        map.put("smsCode",request.getSmsCode());
        //TODO 待验证方式
        String response = template.postForObject("/app/riskOperator/sendSmsCode", map, String.class);
        if (StringUtils.isNotBlank(response)) {
            //TODO 解析返回值并保存

        }
    }

    /**
     * 个人信息认证
     */
    public void authPersonInfo(PersonInfoAuthRequest request) {
        GlobalUser user = getGlobalUser();
        UserAuthStatus userAuthStatus = buildUserAuthStatus(user.getUserId(), "个人信息认证");
        Profile profile = new Profile();
        profile.setId(IdentifierGenerator.nextId());
        profile.setUserId(user.getUserId());
        profile.setEducation(request.getEducation());
        profile.setCompanyName(request.getCompanyName());
        profile.setCompanyAddr(request.getCompanyAddr());
        profile.setCompanyAddrDetail(request.getCompanyAddrDetail());
        profile.setEmail(request.getEmail());
        profile.setParentName(request.getParentName());
        profile.setParentTel(request.getParentTel());
        profile.setRelationship(request.getRelationship());
        profile.setRelatedPersonName(request.getRelatedPersonName());
        profile.setRelatedPersonTel(request.getRelatedPersonTel());
        profile.setCreateTime(LocalDateTime.now());
        profile.setRemark("个人信息认证");
        personInfoRepository.insertPersonInfoAndUserAuthStatus(profile, userAuthStatus);
    }

    /**
     * 身份信息认证，信息分3个表存（用户表、身份信息认证表，认证状态表）
     */
    public void identityInfoAuth(IdentityInfoAuthRequest request) {
        GlobalUser user = getGlobalUser();
        String realName = request.getRealName();
        String idCardNo = request.getIdCardNo();
        Integer gender = request.getGender();
        String idCardBase64ImgStr = request.getIdCardBase64ImgStr();
        String faceBase64ImgStr = request.getFaceBase64ImgStr();
        String picSuffix = request.getPicSuffix();
        //上传至阿里云

        String idCardPhotoPath ;
        String facePhotoPath ;
        try {
            idCardPhotoPath = aliyunService.uploadBase64PicStr(idCardBase64ImgStr,picSuffix);
            facePhotoPath = aliyunService.uploadBase64PicStr(faceBase64ImgStr,picSuffix);
        } catch (NestedException e) {
            //上传base64图片字符串失败
            log.error(e.getMessage());
            return;
        }
        //根据拿到json串组装对象
        User userObj = new User();
        UserPic userPic = new UserPic();
        //组装user对象
        userObj.setId(user.getUserId());
        userObj.setRealName(realName);
        userObj.setIdCardNo(idCardNo);
        userObj.setGender(gender.byteValue());
        userObj.setUpdateTime(LocalDateTime.now());
        userObj.setUpdateBy(user.getUserId());
        //组装faceIdcardAuth对象
        Long faceIdcardAuthId = IdentifierGenerator.nextId();
        userPic.setId(faceIdcardAuthId);
        userPic.setUserId(user.getUserId());
        userPic.setIdCardPhoto(idCardPhotoPath);
        userPic.setFacePhoto(facePhotoPath);
        userPic.setCreateBy(faceIdcardAuthId);
        userPic.setCreateTime(LocalDateTime.now());
        userPic.setRemark("身份信息认证");
        //获取UserAuthStatus对象
        UserAuthStatus userAuthStatus = buildUserAuthStatus(user.getUserId(), "身份信息认证");
        workRepository.insertIdentityInfo(userPic, userAuthStatus, userObj);
    }

    /**
     * 专业信息认证
     */
    public void professionalAuth(ProfessionalRequest request){
        GlobalUser user = getGlobalUser();
        Work work =new Work();
        work.setId(IdentifierGenerator.nextId());
        work.setUserId(user.getUserId());
        work.setJobType(request.getJobType().byteValue());
        work.setMonthlyIncome(request.getMonthlyIncome().byteValue());
        work.setEmployeeCard(request.getEmployeeCard());
        work.setWorkplace(request.getWorkplace());
        work.setCreateBy(user.getUserId());
        work.setCreateTime(LocalDateTime.now());
        work.setRemark("专业信息认证");
        //获取UserAuthStatus对象
        UserAuthStatus userAuthStatus = buildUserAuthStatus(user.getUserId(), "专业信息认证");
        professionalRepository.insertProfessionalAndUserAuthStatus(work, userAuthStatus);
    }
}
