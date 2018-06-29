package com.hzed.easyget.application.service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
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
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;

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
        List<Dict> dictList = dictRepository.findByModuleCodeAndLanguage(request.getCode(), i18n);
        dictList.forEach(dict -> {
            UserAuthStatus userAuthStatus = authStatusRepository.findAuthStatusByUserId(userId, dict.getDicCode());
            AuthStatusResponse authStatusResponse = new AuthStatusResponse();
            authStatusResponse.setCode(userAuthStatus.getAuthCode());
            authStatusResponse.setDicName(dict.getDicValue());
            authStatusResponse.setStatus(String.valueOf(userAuthStatus.getAuthStatus()));
            authStatusList.add(authStatusResponse);
        });

        return authStatusList;
    }

    /**
     * 通讯录认证通讯录和通话记录都可能为空
     */
    public void authContacts(ContactsRequest request) {
        GlobalUser user = getGlobalUser();
        String platForm = getGlobalHead().getPlatform();
        int source = "android".equals(platForm) ? ComConsts.IS_ANDROID : ComConsts.IS_IOS;
        RiskResponse response = riskService.authContacts(request.getContacts(), request.getCallLogs(), source);
        afterResponse(response, user.getUserId(), AuthCodeEnum.CONTACTS.getCode(), "通讯录认证");
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
        userAuthStatus.setId(IdentifierGenerator.nextId());
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
        String platForm = getGlobalHead().getPlatform();
        int source = "android".equals(platForm) ? ComConsts.IS_ANDROID : ComConsts.IS_IOS;
        RiskResponse response = riskService.authMessages(request.getMessage(), source);
        afterResponse(response, user.getUserId(), AuthCodeEnum.MESSAGE.getCode(), "短信认证");
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
        redisService.setCache(RedisConsts.IDENTITY_SMS_CODE_SEND + RedisConsts.SPLIT + user.getUserId(), "operatorAuth", 24 * 3600L);

    }

    /**
     * 运营商认证 - 输入验证码认证运行商
     */
    public void operatorAuth(PeratorAuthRequest request) {
        GlobalUser user = getGlobalUser();

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
        GlobalUser user = getGlobalUser();
        UserAuthStatus userAuthStatus = buildUserAuthStatus(user.getUserId(), AuthCodeEnum.PERSON_INFO.getCode(), "个人信息认证");
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
        profile.setRemark("个人信息认证");
        personInfoRepository.insertPersonInfoAndUserAuthStatus(profile, userAuthStatus);
    }

    /**
     * 身份证识别
     */
    public IdCardRecognitionResponse idCardRecognition(IdCardRecognitionRequest request) {
        IdCardRecognitionResponse recognitionResponse = new IdCardRecognitionResponse();
        String idCardBase64ImgStr = request.getIdCardBase64ImgStr();
        RiskResponse response = riskService.idCardRecognition(idCardBase64ImgStr);
        if(ObjectUtils.isEmpty(response)){
            throw new WarnException(BizCodeEnum.ERROR_IDCARD_RESULT);
        }
        String bobyStr = response.getBody().toString();
        JSONObject jsonObject = JSONObject.parseObject(bobyStr, JSONObject.class).getJSONObject("data");
        String name = jsonObject.getString("name");
        String gender = jsonObject.getString("gender");
        String idNumber = jsonObject.getString("idNumber");
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
        String realName = request.getRealName();
        String idCardNo = request.getIdCardNo();
        Integer gender = request.getGender();
        //调风控身份认证接口，认证通过记录表数据
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
            userObj.setUpdateBy(user.getUserId());
            //组装pic对象
            List<UserPic> list = Lists.newArrayList();
            list.add(UserPic.builder().id(IdentifierGenerator.nextId()).userId(user.getUserId()).type("idCard").picUrl(idCardPhotoPath).build());
            list.add(UserPic.builder().id(IdentifierGenerator.nextId()).userId(user.getUserId()).type("face").picUrl(facePhotoPath).build());
            //获取UserAuthStatus对象
            UserAuthStatus userAuthStatus = buildUserAuthStatus(user.getUserId(), AuthCodeEnum.ID_CARD.getCode(), "身份信息认证");
            workRepository.insertIdentityInfo(list, userAuthStatus, userObj);
        } catch (NestedException e) {
            throw new ComBizException(BizCodeEnum.FAIL_IDENTITY_AUTH);
        }
    }

    /**
     * 专业信息认证
     */
    public void professionalAuth(ProfessionalRequest request) {
        try {
            //照片上传
            String employeeCardPhotoPath = getPhotoPath(request.getEmployeeCardBase64ImgStr(), request.getPicSuffix());
            String workplacePhotoPath = getPhotoPath(request.getEmployeeCardBase64ImgStr(), request.getPicSuffix());
            GlobalUser user = getGlobalUser();
            Work work = new Work();
            work.setId(IdentifierGenerator.nextId());
            work.setUserId(user.getUserId());
            work.setJobType(request.getJobType().byteValue());
            work.setMonthlyIncome(request.getMonthlyIncome().byteValue());
            work.setEmployeeCard(employeeCardPhotoPath);
            work.setWorkplace(workplacePhotoPath);
            work.setCreateBy(user.getUserId());
            work.setCreateTime(LocalDateTime.now());
            work.setRemark("专业信息认证");
            //获取UserAuthStatus对象
            UserAuthStatus userAuthStatus = buildUserAuthStatus(user.getUserId(), AuthCodeEnum.PROFESSIONAL.getCode(), "专业信息认证");
            professionalRepository.insertProfessionalAndUserAuthStatus(work, userAuthStatus);
        } catch (Exception ex) {
            throw new ComBizException(BizCodeEnum.FAIL_PROFESSIONAL_AUTH);
        }

    }

    private void afterResponse(RiskResponse response, Long userId, String code, String remark) {
        if (null == response) {
            throw new WarnException(BizCodeEnum.ERROR_RISK__RESULT);
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
            log.info("上传图片异常：" + e.getStackTrace().toString());
            throw new ComBizException(BizCodeEnum.SERVICE_EXCEPTION);
        }
        return path;
    }

    /**
     * Facebook认证-风控回调
     *
     * @param request
     */
    public void facebookAuth(FacebookRequest request) {
        log.info("facebook认证-风控回调数据：{}", JSONObject.toJSONString(request));
        if (!ComConsts.RISK_OK.equals(request.getResultCode())) {
            throw new WarnException(BizCodeEnum.FAIL_AUTH);
        }
        UserAuthStatus userAuthStatus = buildUserAuthStatus(request.getUserId(), AuthCodeEnum.FACEBOOK.getCode(), "Facebook认证");
        authStatusRepository.insertSelective(userAuthStatus);
    }

    /**
     * ins认证-风控回调
     *
     * @param request
     */
    public void insAuth(InsRequest request) {
        log.info("ins认证-风控回调数据：{}", JSONObject.toJSONString(request));
        if (!ComConsts.RISK_OK.equals(request.getResultCode())) {
            throw new WarnException(BizCodeEnum.FAIL_AUTH);
        }
        UserAuthStatus userAuthStatus = buildUserAuthStatus(request.getUserId(), AuthCodeEnum.INS.getCode(), "ins认证");
        authStatusRepository.insertSelective(userAuthStatus);
    }

    /**
     * ins认证-风控回调
     *
     * @param request
     */
    public void facebookAndIns(FacebookInsRequest request) {
        String task_id = request.getTask_id();
        int source = "android".equals(getGlobalHead().getPlatform()) ? ComConsts.IS_ANDROID : ComConsts.IS_IOS;
        GlobalUser user = getGlobalUser();
        riskService.facebookAndIns(user.getUserId(),task_id,source);
    }


}
