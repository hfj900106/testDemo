package com.hzed.easyget.application.service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hzed.easyget.application.enums.AuthCodeEnum;
import com.hzed.easyget.application.enums.AuthStatusEnum;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.config.redis.RedisService;
import com.hzed.easyget.infrastructure.consts.ComConsts;
import com.hzed.easyget.infrastructure.consts.RedisConsts;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.exception.NestedException;
import com.hzed.easyget.infrastructure.exception.WarnException;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.model.RiskResponse;
import com.hzed.easyget.infrastructure.repository.*;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.RequestUtil;

import com.hzed.easyget.infrastructure.utils.id.IDGenerator;
import com.hzed.easyget.persistence.auto.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.hzed.easyget.infrastructure.utils.RequestUtil.getGlobalHead;
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
    private RedisService redisService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FileService fileService;
    @Autowired
    private SaService saService;
    @Autowired
    private RiskService riskService;
    @Autowired
    private DictRepository dictRepository;

    /**
     * 获取用户认证状态
     */
    public List<AuthStatusResponse> getAuthStatus(AuthStatusRequest request) {
        List<AuthStatusResponse> authStatusList = Lists.newArrayList();
        Long userId = RequestUtil.getGlobalUser().getUserId();
        String i18n = RequestUtil.getGlobalHead().getI18n();
        List<Dict> dictList = dictRepository.findEnableByModuleCodeAndLanguage(request.getCode(), i18n);
        dictList.forEach(dict -> {
            UserAuthStatus userAuthStatus = authStatusRepository.findEnableAuthStatusByUserId(userId, dict.getDicCode());
            AuthStatusResponse authStatusResponse = new AuthStatusResponse();
            authStatusResponse.setAuthCode(dict.getDicCode());
            authStatusResponse.setAuthName(dict.getDicValue());
            if (!ObjectUtils.isEmpty(userAuthStatus)) {
                authStatusResponse.setAuthStatus(String.valueOf(userAuthStatus.getAuthStatus()));
            }
            authStatusList.add(authStatusResponse);
        });

        return authStatusList;
    }

    /**
     * 通讯录认证通讯录和通话记录都可能为空
     */
    public void authContacts(ContactsRequest request) {
        GlobalUser user = getGlobalUser();
        String auth_code = AuthCodeEnum.CONTACTS.getCode();
        // 请求防重
        String key = RedisConsts.AUTH + RedisConsts.SPLIT + auth_code + RedisConsts.SPLIT + user.getUserId();
        redisService.defensiveRepet(key, BizCodeEnum.FREQUENTLY_AUTH_RISK);
        // 判断该用户是否已经验证
        checkAuth(user.getUserId(), auth_code);

        String platForm = getGlobalHead().getPlatform();
        int source = "android".equals(platForm) ? ComConsts.IS_ANDROID : ComConsts.IS_IOS;
        RiskResponse response = riskService.authContacts(request.getContacts(), request.getCallLogs(), source);
        afterResponse(response, user.getUserId(), auth_code, "通讯录认证");
    }

    /**
     * 构建UserAuthStatus对象
     *
     * @param userId
     * @param remark
     */
    public UserAuthStatus buildUserAuthStatus(Long userId, String code, String remark) {
        //保存到数据库短信记录表
        UserAuthStatus userAuthStatus = new UserAuthStatus();
        userAuthStatus.setId(IDGenerator.nextId());
        userAuthStatus.setUserId(userId);
        userAuthStatus.setAuthCode(code);
        userAuthStatus.setAuthStatus(Integer.valueOf(AuthStatusEnum.HAS_AUTH.getCode()));
        //过期时间，半年
        userAuthStatus.setExpireTime(DateUtil.addMonth(LocalDateTime.now(), 6));
        userAuthStatus.setRemark(remark);
        return userAuthStatus;
    }

    /**
     * 短信认证
     */
    public void authMessages(MessagesRequest request) {
        GlobalUser user = getGlobalUser();
        String auth_code = AuthCodeEnum.MESSAGE.getCode();
        // 请求防重
        String key = RedisConsts.AUTH + RedisConsts.SPLIT + auth_code + RedisConsts.SPLIT + user.getUserId();
        redisService.defensiveRepet(key, BizCodeEnum.FREQUENTLY_AUTH_RISK);

        // 判断该用户是否已经验证
        checkAuth(user.getUserId(), auth_code);

        String platForm = getGlobalHead().getPlatform();
        int source = "android".equals(platForm) ? ComConsts.IS_ANDROID : ComConsts.IS_IOS;
        RiskResponse response = riskService.authMessages(request.getMessage(), source);
        afterResponse(response, user.getUserId(), auth_code, "短信认证");
    }

    /**
     * 运营商认证 - 发送验证码接口
     */
    public void operatorSendSmsCode() {
        GlobalUser user = getGlobalUser();
        String isSend = redisService.getCache(RedisConsts.IDENTITY_SMS_CODE_SEND + RedisConsts.SPLIT + user.getUserId());
        if (StringUtils.isNotBlank(isSend)) {
            //发送过于频繁,不打error
            saService.saOperator(user, false, BizCodeEnum.NOT_END_AUTH_RISK.getMessage());
            throw new WarnException(BizCodeEnum.NOT_END_AUTH_RISK);
        }
        User userInfo = userRepository.findById(user.getUserId());
        String realName = userInfo.getRealName();
        String identityCode = userInfo.getIdCardNo();
        if (StringUtils.isBlank(realName) || StringUtils.isBlank(identityCode)) {
            //未进行身份验证
            saService.saOperator(user, false, BizCodeEnum.UN_IDENTITY_AUTH.getMessage());
            throw new WarnException(BizCodeEnum.UN_IDENTITY_AUTH);
        }
        String platForm = getGlobalHead().getPlatform();
        int source = "android".equals(platForm) ? ComConsts.IS_ANDROID : ComConsts.IS_IOS;
        riskService.operatorSendSmsCode(source);

        saService.saOperator(user, true, BizCodeEnum.SUCCESS_AUTH.getMessage());
        //redis存一个发送标识，要等输入验证认证结束才可以重新发送，第三方接口要求
        redisService.setCache(RedisConsts.IDENTITY_SMS_CODE_SEND + RedisConsts.SPLIT + user.getUserId(), "operatorAuth", 180L);

    }

    /**
     * 运营商认证 - 输入验证码认证运行商
     */
    public void operatorAuth(PeratorAuthRequest request) {
        GlobalUser user = getGlobalUser();
        // 判断该用户是否已经验证
        checkAuth(user.getUserId(), AuthCodeEnum.SMS.getCode());

        RiskResponse response = riskService.operatorAuth(request.getSmsCode());

        afterResponse(response, user.getUserId(), AuthCodeEnum.SMS.getCode(), "运营商认证");

        saService.saOperator(user, true, BizCodeEnum.HAVE_AUTH_RISK.getMessage());

        //认证成功，删除重发标志
        redisService.clearCache(RedisConsts.IDENTITY_SMS_CODE_SEND + RedisConsts.SPLIT + user.getUserId());
    }

    /**
     * 个人信息认证
     */
    public void authPersonInfo(PersonInfoAuthRequest request) {
        Long userId = getGlobalUser().getUserId();
        String auth_code = AuthCodeEnum.PERSON_INFO.getCode();
        // 请求防重
        String key = RedisConsts.AUTH + RedisConsts.SPLIT + auth_code + RedisConsts.SPLIT + userId;
        redisService.defensiveRepet(key, BizCodeEnum.FREQUENTLY_AUTH_RISK);
        // 判断该用户是否已经验证

        checkAuth(userId, auth_code);

        UserAuthStatus userAuthStatus = buildUserAuthStatus(userId, AuthCodeEnum.PERSON_INFO.getCode(), "个人信息认证");

        String parentName = request.getParentName();
        String parentTel = request.getParentTel().replaceAll("\\s*", "");

        String relatedPersonName = request.getRelatedPersonName();
        String relatedPersonTel = request.getRelatedPersonTel().replaceAll("\\s*", "");

        Profile profile = new Profile();

        profile.setId(IDGenerator.nextId());

        profile.setUserId(userId);
        profile.setEducation(request.getEducation());
        profile.setCompanyName(request.getCompanyName());
        profile.setCompanyAddr(request.getCompanyAddr());
        profile.setCompanyAddrDetail(request.getCompanyAddrDetail());
        profile.setEmail(request.getEmail());
        profile.setParentName(request.getParentName());

        profile.setParentTel(parentTel);
        profile.setRelationship(request.getRelationship());
        profile.setRelatedPersonName(request.getRelatedPersonName());

        profile.setRelatedPersonTel(relatedPersonTel);
        profile.setRemark("个人信息认证");
        personInfoRepository.insertPersonInfoAndUserAuthStatus(profile, userAuthStatus);

        List<Map<String, String>> objectList = Lists.newArrayList();
        Map<String, String> stringMap1 = Maps.newHashMap();
        stringMap1.put("relationship", "parent");
        stringMap1.put("contact", parentName);
        stringMap1.put("phone", parentTel);
        objectList.add(stringMap1);

        Map<String, String> stringMap2 = Maps.newHashMap();
        stringMap2.put("relationship", request.getRelationship());
        stringMap2.put("contact", relatedPersonName);
        stringMap2.put("phone", relatedPersonTel);
        objectList.add(stringMap2);
        riskService.pushProfile(objectList, userId);
    }

    /**
     * 身份证识别
     */
    public IdCardRecognitionResponse idCardRecognition(IdCardRecognitionRequest request) {
        IdCardRecognitionResponse recognitionResponse = new IdCardRecognitionResponse();
        String idCardBase64ImgStr = request.getIdCardBase64ImgStr();
        RiskResponse response = riskService.idCardRecognition(idCardBase64ImgStr);

        if (ObjectUtils.isEmpty(response.getBody())) {
            throw new WarnException(BizCodeEnum.FAIL_IDCARD_RECOGNITION);
        }

        String bobyStr = response.getBody().toString();
        if (ObjectUtils.isEmpty(bobyStr)) {
            throw new WarnException(BizCodeEnum.FAIL_IDCARD_RECOGNITION);
        }

        JSONObject obj = JSONObject.parseObject(bobyStr, JSONObject.class);
        if (ObjectUtils.isEmpty(obj)) {
            throw new WarnException(BizCodeEnum.FAIL_IDCARD_RECOGNITION);
        }

        JSONObject data = obj.getJSONObject("data");
        if (ObjectUtils.isEmpty(data)) {
            throw new WarnException(BizCodeEnum.FAIL_IDCARD_RECOGNITION);
        }
        String name = data.getString("name");
        String gender = data.getString("gender");
        String idNumber = data.getString("idNumber");
        String birthPlaceBirthday = data.getString("birthPlaceBirthday");
        if (!StringUtils.isBlank(birthPlaceBirthday)) {
            // 注意有空格
            int strLength = birthPlaceBirthday.length();
            if (strLength < 10) {
                // 识别生日数据有错则直接给空串
                recognitionResponse.setBirthday("");
            } else {
                recognitionResponse.setBirthday(birthPlaceBirthday.substring(strLength - 10, strLength));
            }
        }
        recognitionResponse.setName(name);
        int genderInt = 1;
        if (!ObjectUtils.isEmpty(gender) && ComConsts.FEMALE.equalsIgnoreCase(gender)) {
            genderInt = 2;
        }
        recognitionResponse.setGender(genderInt);
        recognitionResponse.setIdNumber(idNumber);
        return recognitionResponse;
    }

    /**
     * 人脸识别
     */
    public void faceRecognition(FaceRecognitionRequest request) {
        String faceBase64ImgStr = request.getFaceBase64ImgStr();
        riskService.faceRecognition(faceBase64ImgStr);
    }

    /**
     * 身份信息认证，信息分3个表存（用户表、身份信息认证表，认证状态表）
     */
    public void identityInfoAuth(IdentityInfoAuthRequest request) {
        GlobalUser user = getGlobalUser();
        String auth_code = AuthCodeEnum.ID_CARD.getCode();
        // 请求防重
        String key = RedisConsts.AUTH + RedisConsts.SPLIT + auth_code + RedisConsts.SPLIT + user.getUserId();
        redisService.defensiveRepet(key, BizCodeEnum.FREQUENTLY_AUTH_RISK);
        // 判断该用户是否已经验证
        checkAuth(user.getUserId(), auth_code);

        String realName = request.getRealName();
        String idCardNo = request.getIdCardNo();
        Integer gender = request.getGender();
        // 查询身份证是否已存在
        User user1 = userRepository.findByIdCardNo(idCardNo);
        if (!ObjectUtils.isEmpty(user1)) {
            throw new WarnException(BizCodeEnum.IDCARD_EXIST);
        }
        // 调风控身份认证接口，认证通过记录表数据
        riskService.identityInfoAuth();
        String idCardBase64ImgStr = request.getIdCardBase64ImgStr();
        String faceBase64ImgStr = request.getFaceBase64ImgStr();
        String picSuffix = request.getPicSuffix();
        try {
            String idCardPhotoPath = getPhotoPath(idCardBase64ImgStr, picSuffix);
            String facePhotoPath = getPhotoPath(faceBase64ImgStr, picSuffix);

            //根据拿到json串组装对象
            User userObj = new User();
            //组装user对象
            userObj.setId(user.getUserId());
            userObj.setRealName(realName);
            userObj.setIdCardNo(idCardNo);
            userObj.setGender(gender.byteValue());
            userObj.setUpdateTime(LocalDateTime.now());
            //组装pic对象
            List<UserPic> list = Lists.newArrayList();

            list.add(UserPic.builder().id(IDGenerator.nextId()).userId(user.getUserId()).type("idCard").picUrl(idCardPhotoPath).build());
            list.add(UserPic.builder().id(IDGenerator.nextId()).userId(user.getUserId()).type("face").picUrl(facePhotoPath).build());
            //获取UserAuthStatus对象
            UserAuthStatus userAuthStatus = buildUserAuthStatus(user.getUserId(), auth_code, "身份信息认证");
            workRepository.insertIdentityInfo(list, userAuthStatus, userObj);
        } catch (NestedException e) {
            throw new ComBizException(BizCodeEnum.FAIL_IDENTITY_AUTH);
        }
    }

    /**
     * 专业信息认证
     */
    public void professionalAuth(ProfessionalRequest request) {
        GlobalUser user = getGlobalUser();
        // 判断该用户是否已经验证
        checkAuth(user.getUserId(), AuthCodeEnum.PROFESSIONAL.getCode());

        try {
            //照片上传
            String employeeCardPhotoPath = getPhotoPath(request.getEmployeeCardBase64ImgStr(), request.getPicSuffix());
            String workplacePhotoPath = getPhotoPath(request.getEmployeeCardBase64ImgStr(), request.getPicSuffix());
            Work work = new Work();

            work.setId(IDGenerator.nextId());
            work.setUserId(user.getUserId());
            work.setJobType(request.getJobType());
            work.setMonthlyIncome(request.getMonthlyIncome());
            work.setEmployeeCard(employeeCardPhotoPath);
            work.setWorkplace(workplacePhotoPath);
            work.setRemark("专业信息认证");
            //获取UserAuthStatus对象
            UserAuthStatus userAuthStatus = buildUserAuthStatus(user.getUserId(), AuthCodeEnum.PROFESSIONAL.getCode(), "专业信息认证");
            professionalRepository.insertProfessionalAndUserAuthStatus(work, userAuthStatus);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ComBizException(BizCodeEnum.FAIL_PROFESSIONAL_AUTH);
        }

    }

    private void afterResponse(RiskResponse response, Long userId, String code, String remark) {
        if (ObjectUtils.isEmpty(response)) {
            throw new WarnException(BizCodeEnum.ERROR_RISK_RESULT);
        }
        if (!response.getHead().getStatus().equals(ComConsts.RISK_OK)) {
            throw new WarnException(BizCodeEnum.FAIL_AUTH);
        }
        //修改认证表的状态
        UserAuthStatus userAuthStatus = buildUserAuthStatus(userId, code, remark);
        authStatusRepository.insertSelective(userAuthStatus);
    }

    /**
     * 获取图片保存路径
     */
    private String getPhotoPath(String base64Img, String picSuffix) {
        String path;
        try {
            path = fileService.uploadBase64Img(base64Img, picSuffix);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("上传图片异常");
            throw new ComBizException(BizCodeEnum.SERVICE_EXCEPTION);
        }
        return path;
    }

    /**
     * Facebook认证-风控回调
     */
    public void facebookAuth(FacebookRequest request) {
        Long userId = request.getUserId();
        String auth_code = AuthCodeEnum.FACEBOOK.getCode();
        // 请求防重
        String key = RedisConsts.AUTH + RedisConsts.SPLIT + auth_code + RedisConsts.SPLIT + userId;
        redisService.defensiveRepet(key, BizCodeEnum.FREQUENTLY_AUTH_RISK);
        // 判断该用户是否已经验证
        checkAuth(userId, auth_code);

        if (!ComConsts.RISK_OK.equals(request.getResultCode())) {
            throw new WarnException(BizCodeEnum.FAIL_AUTH);
        }
        UserAuthStatus userAuthStatus = buildUserAuthStatus(userId, AuthCodeEnum.FACEBOOK.getCode(), "Facebook认证");
        authStatusRepository.insertSelective(userAuthStatus);
    }

    /**
     * ins认证-风控回调
     */
    public void insAuth(InsRequest request) {
        Long userId = request.getUserId();
        String auth_code = AuthCodeEnum.INS.getCode();
        // 请求防重
        String key = RedisConsts.AUTH + RedisConsts.SPLIT + auth_code + RedisConsts.SPLIT + userId;
        redisService.defensiveRepet(key, BizCodeEnum.FREQUENTLY_AUTH_RISK);

        // 判断该用户是否已经验证
        checkAuth(userId, auth_code);

        if (!ComConsts.RISK_OK.equals(request.getResultCode())) {
            throw new WarnException(BizCodeEnum.FAIL_AUTH);
        }
        UserAuthStatus userAuthStatus = buildUserAuthStatus(userId, AuthCodeEnum.INS.getCode(), "ins认证");
        authStatusRepository.insertSelective(userAuthStatus);
    }

    /**
     * facebook和ins认证-风控回调
     */
    public void facebookAndIns(FacebookInsRequest request) {
        String taskId = request.getTaskId();
        int source = "android".equals(getGlobalHead().getPlatform()) ? ComConsts.IS_ANDROID : ComConsts.IS_IOS;
        GlobalUser user = getGlobalUser();
        riskService.facebookAndIns(user.getUserId(), taskId, source);
    }

    /**
     * 检测用户是否已经认证成功
     */
    public void checkAuth(Long userId, String authCode) {
        // 判断该用户是否已经验证
        UserAuthStatus userAuthStatusHas = authStatusRepository.findEnableAuthStatusByUserId(userId, authCode);
        if (!ObjectUtils.isEmpty(userAuthStatusHas)) {
            log.info("该用户已认证");
            throw new WarnException(BizCodeEnum.HAVE_AUTH_RISK);
        }
    }

}
