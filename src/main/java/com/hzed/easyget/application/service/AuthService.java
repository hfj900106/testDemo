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
import com.hzed.easyget.infrastructure.consts.SaConsts;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.enums.LocaleEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    private ProfileRepository profileRepository;
    @Autowired
    private WorkRepository workRepository;
    @Autowired
    private UserAuthStatusRepository authStatusRepository;
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
     * 获取用户认证项信息
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
     * 获取用户认证组状态
     */
    public List<AuthGroupStatusResponse> getAuthGroupStatus(AuthStatusRequest request) {
        List<AuthGroupStatusResponse> authStatusList = Lists.newArrayList();
        Long userId = RequestUtil.getGlobalUser().getUserId();
        String i18n = RequestUtil.getGlobalHead().getI18n();
        String remark1;
        String remark2;
        if (LocaleEnum.en_US.getI18n().equals(i18n)) {
            remark1 = "Basic certification";
            remark2 = "Social certification";
        } else if (LocaleEnum.zh_CN.getI18n().equals(i18n)) {
            remark1 = "基本认证";
            remark2 = "社交认证";
        } else {
            remark1 = "Sertifikasi dasar";
            remark2 = "Sertifikasi sosial";
        }
        List<Dict> dictList = dictRepository.findGroupByModuleCodeAndLanguage(request.getCode(), i18n, remark1, remark2);
        String personAuth = AuthCodeEnum.PERSON_INFO.getCode();
        String workAuth = AuthCodeEnum.PROFESSIONAL.getCode();
        Integer authStatus = AuthStatusEnum.HAS_AUTH.getCode();
        // 合并认证结果
        for (Dict dict1 : dictList) {
            String code1 = dict1.getDicCode();
            if ((personAuth.equals(code1) || workAuth.equals(code1))) {
                UserAuthStatus userAuthStatus = authStatusRepository.findEnableAuthStatusByUserId(userId, code1);
                if (!ObjectUtils.isEmpty(userAuthStatus)) {
                    if (userAuthStatus.getAuthStatus().equals(AuthStatusEnum.TO_AUTH.getCode())) {
                        authStatus = AuthStatusEnum.TO_AUTH.getCode();
                        break;
                    } else if (userAuthStatus.getAuthStatus().equals(AuthStatusEnum.FAIl_AUTH.getCode())) {
                        authStatus = AuthStatusEnum.FAIl_AUTH.getCode();
                        break;
                    }
                }
            }
        }

        final String authStatus2 = String.valueOf(authStatus);
        dictList.forEach(dict -> {
            // 查出认证的数据
            String code = dict.getDicCode();
            UserAuthStatus userAuthStatus = authStatusRepository.findEnableAuthStatusByUserId(userId, code);
            AuthGroupStatusResponse authStatusResponse = new AuthGroupStatusResponse();
            authStatusResponse.setAuthCode(code);
            authStatusResponse.setAuthName(dict.getDicValue());
            authStatusResponse.setAuthGroup(dict.getRemark());
            if (!ObjectUtils.isEmpty(userAuthStatus)) {
                if (personAuth.equals(code) || workAuth.equals(code)) {
                    authStatusResponse.setAuthStatus(String.valueOf(authStatus2));
                } else {
                    authStatusResponse.setAuthStatus(String.valueOf(userAuthStatus.getAuthStatus()));
                }
            } else {
                authStatusResponse.setAuthStatus(String.valueOf(AuthStatusEnum.UN_AUTH.getCode()));
            }
            // 去掉专业信息认证
            if (!authStatusResponse.getAuthCode().equals(workAuth)) {
                authStatusList.add(authStatusResponse);
            }
        });

        return authStatusList;
    }

    /**
     * 通讯录认证通讯录和通话记录都可能为空，已认证的通讯录还会更新，获取最新通讯录
     */
    public void authContacts(ContactsRequest request) {
        LocalDateTime startTime = LocalDateTime.now();
        Long userId = RequestUtil.getGlobalUser().getUserId();
        String authCode = AuthCodeEnum.CONTACTS.getCode();
        // 请求防重
        String key = RedisConsts.AUTH + RedisConsts.SPLIT + authCode + RedisConsts.SPLIT + userId;
        redisService.defensiveRepet(key, BizCodeEnum.FREQUENTLY_AUTH_RISK);
        // 调风控
        RiskResponse response = riskService.authContacts(request.getContacts(), request.getCallLogs());
        // 保存或更新
        saveOrUpdateContactsAndSmsAuth(userId, authCode, response);
        saService.saCommonOperator(RequestUtil.getGlobalUser(), startTime, response, SaConsts.CONTACTS_DATA);
    }

    /**
     * 短信认证，已认证的短信还会更新，获取最新短信
     */
    public void authMessages(MessagesRequest request) {
        GlobalUser user = RequestUtil.getGlobalUser();
        LocalDateTime startTime = LocalDateTime.now();
        Long userId = user.getUserId();
        String authCode = AuthCodeEnum.MESSAGE.getCode();
        // 请求防重
        String key = RedisConsts.AUTH + RedisConsts.SPLIT + authCode + RedisConsts.SPLIT + user.getUserId();
        redisService.defensiveRepet(key, BizCodeEnum.FREQUENTLY_AUTH_RISK);
        // 调风控
        RiskResponse response = riskService.authMessages(request.getMessage());
        // 保存或更新
        saveOrUpdateContactsAndSmsAuth(userId, authCode, response);
        saService.saCommonOperator(RequestUtil.getGlobalUser(), startTime, response, SaConsts.SMS_DATA);
    }

    /**
     * 运营商认证-发送验证码接口
     */
    public void operatorSendSmsCode() {
        GlobalUser user = RequestUtil.getGlobalUser();
        Long userId = user.getUserId();
        String key = RedisConsts.IDENTITY_SMS_CODE_SEND + RedisConsts.SPLIT + userId;
        String isSend = redisService.getCache(key);
        if (StringUtils.isNotBlank(isSend)) {
            // 发送过于频繁
            saService.saOperator(user, false, BizCodeEnum.NOT_END_AUTH_RISK.getMessage());
            throw new WarnException(BizCodeEnum.NOT_END_AUTH_RISK);
        }

        User userInfo = userRepository.findById(userId);
        String realName = userInfo.getRealName();
        String identityCode = userInfo.getIdCardNo();
        if (StringUtils.isBlank(realName) || StringUtils.isBlank(identityCode)) {
            // 未进行身份验证
            saService.saOperator(user, false, BizCodeEnum.UN_IDENTITY_AUTH.getMessage());
            throw new WarnException(BizCodeEnum.UN_IDENTITY_AUTH);
        }
        // 调风控创建发送验证码任务
        RiskResponse response = riskService.operatorSendSmsCode();

        if (!response.getHead().getStatus().equals(ComConsts.RISK_OK)) {
            saService.saOperator(user, false, BizCodeEnum.FAIL_AUTH.getMessage());
            throw new WarnException(BizCodeEnum.FAIL_AUTH);
        }

        Object bodyObj = response.getBody();
        if (ObjectUtils.isEmpty(bodyObj)) {
            throw new WarnException(BizCodeEnum.ERROR_RISK_RESULT);
        }

        Object codeObj = ((LinkedHashMap) bodyObj).get(ComConsts.RISK_CODE);
        if (codeObj.equals(ComConsts.RISK_OPERATOR_HAVE_AUTH)) {
            // 已经认证过
            saService.saOperator(user, false, BizCodeEnum.HAVE_AUTH_RISK.getMessage());
            throw new WarnException(BizCodeEnum.HAVE_AUTH_RISK);
        }
        if (codeObj.equals(ComConsts.RISK_OPERATOR_PARAMS_ERROR)) {
            // 认证数据不正确，数据从数据库取，一般不出现
            saService.saOperator(user, false, BizCodeEnum.PARAMS_AUTH_RISK.getMessage());
            throw new WarnException(BizCodeEnum.PARAMS_AUTH_RISK);
        }
        if (codeObj.equals(ComConsts.RISK_OPERATOR_ERROR)) {
            // 认证失败
            saService.saOperator(user, false, BizCodeEnum.FAIL_AUTH.getMessage());
            throw new WarnException(BizCodeEnum.FAIL_AUTH);
        }

        saService.saOperator(user, true, BizCodeEnum.SUCCESS_AUTH.getMessage());
        // redis存一个发送标识，要等输入验证认证结束才可以重新发送，第三方接口要求
        redisService.setCache(key, "operatorAuth", 180L);

    }

    /**
     * 运营商认证 - 输入验证码认证运行商，创建一条认证中的记录
     */
    public void operatorAuth(PeratorAuthRequest request) {
        GlobalUser user = RequestUtil.getGlobalUser();
        Long userId = user.getUserId();
        // 验证码验证
        RiskResponse response = riskService.operatorAuth(request.getSmsCode());

        String authCode = AuthCodeEnum.SMS.getCode();
        // 认证中
        Integer statusCode = AuthStatusEnum.TO_AUTH.getCode();
        // 判断该用户是否已经验证或者认证中、失败
        Long authId = checkAuth(userId, authCode);
        if (ObjectUtils.isEmpty(authId)) {
            // 未认证新增认证中的状态
            UserAuthStatus userAuthStatus = buildUserAuthStatus(IDGenerator.nextId(), userId, authCode, statusCode, response.getHead().getError_msg());
            authStatusRepository.insertSelective(userAuthStatus);
        } else {
            // 认证失败，可重新认证，重新改为认证中
            UserAuthStatus userAuthStatus = buildUserAuthStatus(authId, userId, authCode, statusCode, response.getHead().getError_msg());
            authStatusRepository.updateSelective(userAuthStatus);
        }

        saService.saOperator(user, true, BizCodeEnum.HAVE_AUTH_RISK.getMessage());

        //认证成功，删除重发标志
        redisService.clearCache(RedisConsts.IDENTITY_SMS_CODE_SEND + RedisConsts.SPLIT + userId);
    }

    /**
     * 运营商认证-风控回调
     */
    public void operatorAuthCallback(PeratorAuthCallbackRequest request) {
        Long userId = request.getUserId();
        String authCode = AuthCodeEnum.SMS.getCode();
        // 请求防重
        String key = RedisConsts.AUTH + RedisConsts.SPLIT + authCode + RedisConsts.SPLIT + userId;
        redisService.defensiveRepet(key, BizCodeEnum.FREQUENTLY_AUTH_RISK);
        // 返回认证中的id
        Long authId = isInAuth(userId, authCode);

        // 不是认证中的状态直接终止
        if (ObjectUtils.isEmpty(authId)) {
            return;
        }

        // 更新状态
        Integer code = ComConsts.RISK_OK.equals(request.getResultCode()) ? AuthStatusEnum.HAS_AUTH.getCode() : AuthStatusEnum.FAIl_AUTH.getCode();
        UserAuthStatus userAuthStatus = buildUserAuthStatus(authId, userId, AuthCodeEnum.SMS.getCode(), code, "运营商认证");
        authStatusRepository.updateSelective(userAuthStatus);
    }

    /**
     * 个人信息认证
     */
    public void authPersonInfo(PersonInfoAuthRequest request) {
        Long userId = RequestUtil.getGlobalUser().getUserId();
        String authCode = AuthCodeEnum.PERSON_INFO.getCode();
        // 请求防重
        String key = RedisConsts.AUTH + RedisConsts.SPLIT + authCode + RedisConsts.SPLIT + userId;
        redisService.defensiveRepet(key, BizCodeEnum.FREQUENTLY_AUTH_RISK);
        // 拦截已认证
        UserAuthStatus userAuthStatusHas = authStatusRepository.findEnableAuthStatusByUserId(userId, authCode);
        if (!ObjectUtils.isEmpty(userAuthStatusHas)) {
            if (userAuthStatusHas.getAuthStatus().equals(AuthStatusEnum.HAS_AUTH.getCode())) {
                log.info("该用户id，{} 已认证成功，不能重新认证", userId);
                throw new WarnException(BizCodeEnum.HAVE_AUTH_RISK);
            }
        }
        // 处理数据
        List<UserAuthStatus> listAuthStatus = Lists.newArrayList();
        listAuthStatus.add(buildUserAuthStatus(IDGenerator.nextId(), userId, AuthCodeEnum.PERSON_INFO.getCode(), AuthStatusEnum.HAS_AUTH.getCode(), AuthCodeEnum.PERSON_INFO.getMsg()));
        listAuthStatus.add(buildUserAuthStatus(IDGenerator.nextId(), userId, AuthCodeEnum.PROFESSIONAL.getCode(), AuthStatusEnum.HAS_AUTH.getCode(), AuthCodeEnum.PROFESSIONAL.getMsg()));

        String relationship1 = request.getRelationship1();
        String personName1 = request.getPersonName1();
        String personTel1 = request.getPersonTel1().replaceAll("\\s+", "");

        String relationship2 = request.getRelationship2();
        String personName2 = request.getPersonName2();
        String personTel2 = request.getPersonTel2().replaceAll("\\s+", "");

        Profile profile = new Profile();
        profile.setId(IDGenerator.nextId());
        profile.setUserId(userId);
        profile.setEducation(request.getEducation());
        profile.setCompanyName(request.getCompanyName());
        profile.setCompanyAddr(request.getCompanyAddr());
        profile.setCompanyAddrDetail(request.getCompanyAddrDetail());
        profile.setCompanyTel(request.getCompanyTel());
        profile.setEmail(request.getEmail());
        profile.setBirthMotherName(request.getBirthMotherName());
        profile.setChildrenNumber(request.getChildrenNumber());
        profile.setMaritalStatus(request.getMaritalStatus());
        profile.setRelationship1(relationship1 + ":" + personName1 + ":" + personTel1);
        profile.setRelationship2(relationship2 + ":" + personName2 + ":" + personTel2);
        profile.setRemark(AuthCodeEnum.PERSON_INFO.getMsg());

        //组装pic对象
        List<String> listBase64 = request.getPicTypeAndPathBase64Str();
        String suffix = request.getPicSuffix();

        List<UserPic> listPic = Lists.newArrayList();
        listBase64.forEach(base64Str -> {
            String workplacePhotoPath = getPhotoPath(base64Str.substring(11), suffix);
            listPic.add(UserPic.builder().id(IDGenerator.nextId()).userId(userId).type(base64Str.substring(0, 11)).picUrl(workplacePhotoPath).build());
        });

        // 组建Work对象
        Work work = new Work();
        work.setId(IDGenerator.nextId());
        work.setUserId(userId);
        work.setJobType(request.getJobType());
        work.setMonthlyIncome(request.getMonthlyIncome());
        work.setIndustry(request.getIndustry());
        work.setPayday(request.getPayday());
        work.setRemark(AuthCodeEnum.PROFESSIONAL.getMsg());

        // 保存认证信息
        profileRepository.insertProfileAuth(work, profile, listAuthStatus, listPic);

        // 个人信息推风控
        List<Map<String, String>> objectList = Lists.newArrayList();
        Map<String, String> stringMap1 = Maps.newHashMap();
        stringMap1.put("relationship", relationship1);
        stringMap1.put("contact", personName1);
        stringMap1.put("phone", personTel1);
        objectList.add(stringMap1);
        Map<String, String> stringMap2 = Maps.newHashMap();
        stringMap2.put("relationship", relationship2);
        stringMap2.put("contact", personName2);
        stringMap2.put("phone", personTel2);
        objectList.add(stringMap2);
        riskService.pushProfile(objectList, userId);
    }

    /**
     * 身份证识别
     */
    public IdCardRecognitionResponse idCardRecognition(IdCardRecognitionRequest request) {
        IdCardRecognitionResponse recognitionResponse = new IdCardRecognitionResponse();
        RiskResponse response = riskService.idCardRecognition(request);

        Object body = response.getBody();
        if (ObjectUtils.isEmpty(body)) {
            throw new WarnException(BizCodeEnum.FAIL_IDCARD_RECOGNITION);
        }

        JSONObject obj = JSONObject.parseObject(String.valueOf(body), JSONObject.class);
        if (ObjectUtils.isEmpty(obj)) {
            throw new WarnException(BizCodeEnum.FAIL_IDCARD_RECOGNITION);
        }

        String name = obj.getString("name");
        String gender = obj.getString("gender");
        String idNumber = obj.getString("idcardNumber");
        String birthday = obj.getString("birthday");
        String religion = obj.getString("religion");
        String idcardImage = obj.getString("idcardImage");

        // 识别生日数据有错则直接给空串
        recognitionResponse.setBirthday((StringUtils.isBlank(birthday) && birthday.length() < 10) ? "" : birthday);
        recognitionResponse.setName(name);
        // 性别 1-男 2-女
        recognitionResponse.setGender(ComConsts.FEMALE.equalsIgnoreCase(gender) ? 2 : 1);
        recognitionResponse.setIdNumber(idNumber);
        recognitionResponse.setReligion(religion);
        recognitionResponse.setIdcardImage(idcardImage);
        return recognitionResponse;
    }

    /**
     * 人脸识别
     */
    public void faceRecognition(String faceBase64ImgStr) {
        riskService.faceRecognition(faceBase64ImgStr);
    }

    /**
     * 身份信息认证
     * t_loan_user 修改用户信息
     * t_loan_user_pic
     * t_loan_user_auth_status
     */
    public void identityInfoAuth(IdentityInfoAuthRequest request) {
        // 身份证号
        String idCardNo = request.getIdCardNo();
        Long userId = RequestUtil.getGlobalUser().getUserId();
        String authCode = AuthCodeEnum.ID_CARD.getCode();

        // 判断该用户是否已经认证成功、失败
        Long authId = checkAuth(userId, authCode);

        Integer failAuthCode = AuthStatusEnum.FAIl_AUTH.getCode();
        Integer successAuthCode = AuthStatusEnum.HAS_AUTH.getCode();

        // 查询身份证是否已存在
        User user = userRepository.findByIdCardNo(idCardNo);
        if (!ObjectUtils.isEmpty(user) && !user.getId().equals(userId)) {
            // 比较已有记录的userId和当前userId是否相同，不同则直接拦截
            throw new WarnException(BizCodeEnum.IDCARD_EXIST);
        }

        // 调风控
        RiskResponse response = riskService.identityInfoAuth();
        // 认证状态
        Integer statusCode = response.getHead().getStatus().equals(ComConsts.RISK_OK) ? successAuthCode : failAuthCode;

        // 本次认证失败
        if (statusCode.equals(failAuthCode)) {
            // 插入或更新
            if (ObjectUtils.isEmpty(authId)) {
                UserAuthStatus userAuthStatus = buildUserAuthStatus(IDGenerator.nextId(), userId, authCode, statusCode, "身份信息认证");
                authStatusRepository.insertSelective(userAuthStatus);
            } else {
                UserAuthStatus userAuthStatus = buildUserAuthStatus(authId, userId, authCode, statusCode, "身份信息认证");
                authStatusRepository.updateSelective(userAuthStatus);
            }
            // 反馈APP认证失败
            throw new WarnException(BizCodeEnum.FAIL_IDENTITY_AUTH);
        } else {
            String picSuffix = request.getPicSuffix();
            String idCardPhotoPath = getPhotoPath(request.getIdCardBase64ImgStr(), picSuffix);
            String facePhotoPath = getPhotoPath(request.getFaceBase64ImgStr(), picSuffix);

            //组装user对象
            User userUpdate = new User();
            userUpdate.setId(userId);
            userUpdate.setRealName(request.getRealName().trim());
            userUpdate.setIdCardNo(idCardNo);
            userUpdate.setGender(request.getGender().byteValue());
            userUpdate.setUpdateTime(LocalDateTime.now());

            // 插入或更新
            if (ObjectUtils.isEmpty(authId)) {
                List<UserPic> list = Lists.newArrayList();
                list.add(UserPic.builder().id(IDGenerator.nextId()).userId(userId).type("idCard").picUrl(idCardPhotoPath).build());
                list.add(UserPic.builder().id(IDGenerator.nextId()).userId(userId).type("face").picUrl(facePhotoPath).build());
                // 获取UserAuthStatus对象，创建
                UserAuthStatus userAuthStatus = buildUserAuthStatus(IDGenerator.nextId(), userId, authCode, statusCode, "身份信息认证");
                workRepository.insertIdentityInfo(list, userAuthStatus, userUpdate);
            } else {
                List<UserPic> list = Lists.newArrayList();
                list.add(UserPic.builder().id(authId).userId(userId).type("idCard").picUrl(idCardPhotoPath).build());
                list.add(UserPic.builder().id(authId).userId(userId).type("face").picUrl(facePhotoPath).build());
                // 获取UserAuthStatus对象，更新
                UserAuthStatus userAuthStatus = buildUserAuthStatus(authId, userId, authCode, statusCode, "身份信息认证");
                workRepository.updateIdentityInfo(list, userAuthStatus, userUpdate);
            }
        }
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
        String authCode = AuthCodeEnum.FACEBOOK.getCode();
        // 请求防重
        String key = RedisConsts.AUTH + RedisConsts.SPLIT + authCode + RedisConsts.SPLIT + userId;
        redisService.defensiveRepet(key, BizCodeEnum.FREQUENTLY_AUTH_RISK);
        // 返回认证中的id
        Long authId = isInAuth(userId, authCode);

        // 不在认证中直接终止
        if (ObjectUtils.isEmpty(authId)) {
            return;
        }

        // 更新状态
        Integer code = ComConsts.RISK_OK.equals(request.getResultCode()) ? AuthStatusEnum.HAS_AUTH.getCode() : AuthStatusEnum.FAIl_AUTH.getCode();
        UserAuthStatus userAuthStatus = buildUserAuthStatus(authId, userId, AuthCodeEnum.FACEBOOK.getCode(), code, "Facebook认证");
        authStatusRepository.updateSelective(userAuthStatus);
    }

    /**
     * ins认证-风控回调
     */
    public void insAuth(InsRequest request) {
        Long userId = request.getUserId();
        String authCode = AuthCodeEnum.INS.getCode();
        // 请求防重
        String key = RedisConsts.AUTH + RedisConsts.SPLIT + authCode + RedisConsts.SPLIT + userId;
        redisService.defensiveRepet(key, BizCodeEnum.FREQUENTLY_AUTH_RISK);

        // 判断该用户是否在认证中,返回认证中的id
        Long authId = isInAuth(userId, authCode);

        // 不在认证中直接终止
        if (ObjectUtils.isEmpty(authId)) {
            return;
        }

        // 更新状态
        Integer code = ComConsts.RISK_OK.equals(request.getResultCode()) ? AuthStatusEnum.HAS_AUTH.getCode() : AuthStatusEnum.FAIl_AUTH.getCode();
        UserAuthStatus userAuthStatus = buildUserAuthStatus(authId, userId, AuthCodeEnum.INS.getCode(), code, "Ins认证");
        authStatusRepository.updateSelective(userAuthStatus);
    }

    /**
     * facebook和ins认证-数据推风控，然后写入facebook或者ins的认证中的记录，等风控回调修改结果
     */
    public void facebookAndIns(FacebookInsRequest request) {
        Long userId = RequestUtil.getGlobalUser().getUserId();
        // 通知风控
        riskService.facebookAndIns(userId, request.getTaskId());

        String authCode = "ins".equals(request.getFacebookOrIns()) ? AuthCodeEnum.INS.getCode() : AuthCodeEnum.FACEBOOK.getCode();

        try {
            // 判断该用户是否已经验证或者认证中、失败
            Long authId = checkAuth(userId, authCode);

            if (ObjectUtils.isEmpty(authId)) {
                // 没有认证记录，插入认证中状态
                UserAuthStatus userAuthStatus = buildUserAuthStatus(IDGenerator.nextId(), userId, authCode, AuthStatusEnum.TO_AUTH.getCode(), "");
                authStatusRepository.insertSelective(userAuthStatus);
            } else {
                // 认证失败，需要重新认证，修改状态为认证中
                UserAuthStatus userAuthStatus = buildUserAuthStatus(authId, userId, authCode, AuthStatusEnum.TO_AUTH.getCode(), "");
                authStatusRepository.updateSelective(userAuthStatus);
            }
        } catch (WarnException ex) {
            // 已认证和认证中报错不做处理
        }
    }

    /**
     * 检测用户是否已经认证成功，没有成功则返回id用于更新数据，适用于 身份认证、运营商认证、Facebook、ins 认证
     */
    public Long checkAuth(Long userId, String authCode) {
        // 判断该用户是否已经验证
        UserAuthStatus userAuthStatus = authStatusRepository.findEnableAuthStatusByUserId(userId, authCode);
        if (ObjectUtils.isEmpty(userAuthStatus)) {
            return null;
        }

        // 认证记录不为空
        Integer code = userAuthStatus.getAuthStatus();
        // 已认证成功，拦截
        if (code.equals(AuthStatusEnum.HAS_AUTH.getCode())) {
            log.warn("该用户id，{} 已认证成功，不能重新认证", userId);
            throw new WarnException(BizCodeEnum.HAVE_AUTH_RISK);
        }
        // 在认证中(身份认证没有)，拦截
        else if (code.equals(AuthStatusEnum.TO_AUTH.getCode())) {
            log.warn("该用户id，{} 在认证中，不能重新认证", userId);
            throw new WarnException(BizCodeEnum.AUTH_RISK_ING);
        }
        // 认证失败，可以重新认证，返回id用于更新
        else {
            return userAuthStatus.getId();
        }
    }

    /**
     * 检测用户是否已在认证中，用于风控运营商回调、facebook回调、ins回调
     */
    public Long isInAuth(Long userId, String authCode) {
        // 判断该用户是否已经验证
        UserAuthStatus uas = authStatusRepository.findEnableAuthStatusByUserId(userId, authCode);
        if (ObjectUtils.isEmpty(uas)) {
            // 非运营商插入认证中数据
            if (!authCode.equals(AuthCodeEnum.SMS.getCode())) {
                Long authId = IDGenerator.nextId();
                UserAuthStatus uasInsert = buildUserAuthStatus(authId, userId, authCode, AuthStatusEnum.TO_AUTH.getCode(), "");
                authStatusRepository.insertSelective(uasInsert);
                return authId;
            }
            return null;
        } else if (!(uas.getAuthStatus().equals(AuthStatusEnum.TO_AUTH.getCode()))) {
            return null;
        } else {
            return uas.getId();
        }
    }


    public void saveOrUpdateContactsAndSmsAuth(Long userId, String authCode, RiskResponse response) {
        // 处理结果
        RiskResponse.RiskHead head = response.getHead();
        Integer statusCode = head.getStatus().equals(ComConsts.RISK_OK) ? AuthStatusEnum.HAS_AUTH.getCode() : AuthStatusEnum.FAIl_AUTH.getCode();
        // 判断该用户是否已经验证
        UserAuthStatus userAuthStatusHas = authStatusRepository.findEnableAuthStatusByUserId(userId, authCode);

        if (ObjectUtils.isEmpty(userAuthStatusHas)) {
            UserAuthStatus userAuthStatus = buildUserAuthStatus(IDGenerator.nextId(), userId, authCode, statusCode, head.getError_msg());
            authStatusRepository.insertSelective(userAuthStatus);
        } else {
            // 修改 认证表的状态
            UserAuthStatus userAuthStatus = buildUserAuthStatus(userAuthStatusHas.getId(), userId, authCode, statusCode, head.getError_msg());
            authStatusRepository.updateSelective(userAuthStatus);
        }
    }


    /**
     * 构建UserAuthStatus对象
     */
    public UserAuthStatus buildUserAuthStatus(Long authId, Long userId, String code, Integer statusCode, String remark) {
        //保存到数据库短信记录表
        UserAuthStatus userAuthStatus = new UserAuthStatus();
        userAuthStatus.setId(authId);
        userAuthStatus.setUserId(userId);
        userAuthStatus.setAuthCode(code);
        userAuthStatus.setAuthStatus(Integer.valueOf(statusCode));
        //过期时间，半年
        userAuthStatus.setExpireTime(DateUtil.addMonth(LocalDateTime.now(), 6));
        userAuthStatus.setRemark(remark);
        return userAuthStatus;
    }

}
