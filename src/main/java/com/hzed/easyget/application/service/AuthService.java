package com.hzed.easyget.application.service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.hzed.easyget.application.enums.AuthCodeEnum;
import com.hzed.easyget.application.enums.AuthStatusEnum;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.config.RiskProp;
import com.hzed.easyget.infrastructure.config.redis.RedisService;
import com.hzed.easyget.infrastructure.config.rest.RestService;
import com.hzed.easyget.infrastructure.consts.ComConsts;
import com.hzed.easyget.infrastructure.consts.RedisConsts;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.exception.NestedException;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.model.RiskResponse;
import com.hzed.easyget.infrastructure.repository.*;
import com.hzed.easyget.infrastructure.utils.AesUtil;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.FaJsonUtil;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
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
    private RestService restService;
    @Autowired
    private RiskProp riskProp;

    /**
     * 获取用户认证状态
     */
    public List<AuthStatusResponse> getAuthStatus() {
        List<AuthStatusResponse> authStatusList = Lists.newArrayList();
        Long userId = RequestUtil.getGlobalUser().getUserId();
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
     * 通讯录认证通讯录和通话记录都可能为空
     */
    public void authContacts(ContactsRequest request) {
        GlobalUser user = getGlobalUser();
        String platForm = getGlobalHead().getPlatform();
        Long timeStamp = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>(16);
//        map.put("sign", ""sign" -> "H6cI5VzWvg5YubD0CAVkg/EDuB68tGEsufeO7jFEppPcfSAuD/4rmpmbfkdIdh24nG+tBjjdUTRbgAV179uhQw=="");"timeStamp" -> "1529057135292""userId" -> "105534315668316160"
        map.put("sign", AesUtil.aesEncode(user.getUserId(), timeStamp));
        map.put("userId", user.getUserId());
        map.put("timeStamp", timeStamp);
        map.put("contacts", request.getContacts());
        map.put("callRecord", request.getCallLogs());
        map.put("source", "android".equals(platForm) ? ComConsts.IS_ANDROID : ComConsts.IS_IOS);
        //TODO 待验证方式
        RiskResponse response = restService.postJson("http://10.10.20.203:9611/api/risk/Contacts/add", map, RiskResponse.class);
        System.out.println(response.toString());
        //TODO
//        afterResponse(response, "通讯录认证返回数据异常", user.getUserId(), "通讯录认证");
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
        userAuthStatus.setRemark(remark);
        return userAuthStatus;
    }

    /**
     * 短信认证
     */
    public void authMessages(MessagesRequest request) {
        GlobalUser user = getGlobalUser();
        String platForm = getGlobalHead().getPlatform();
        Long timeStamp = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>(16);
        map.put("sign", AesUtil.aesEncode(user.getUserId(), timeStamp));
        map.put("sms", request.getMessage());
        map.put("userId", user.getUserId());
        map.put("timeStamp", timeStamp);
        map.put("source", "android".equals(platForm) ? ComConsts.IS_ANDROID : ComConsts.IS_IOS);
        //TODO 待验证方式
        RiskResponse response = restService.postJson("/app/risk/Sms/add", map, RiskResponse.class);
//        afterResponse(response, "短信认证返回数据异常", user.getUserId(), "短信认证");
    }

    /**
     * 运营商认证 - 发送验证码接口
     */
    public void operatorSendSmsCode() {
        GlobalUser user = getGlobalUser();
        String isSend = redisService.getCache(RedisConsts.IDENTITY_SMS_CODE_SEND + RedisConsts.SPLIT + user.getUserId());
        if (StringUtils.isNotBlank(isSend)) {
            //发送过于频繁
            throw new ComBizException(BizCodeEnum.FREQUENTLY_SMS_SEND);
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
        Long timeStamp = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>(16);
        map.put("sign", AesUtil.aesEncode(user.getUserId(),timeStamp));
        map.put("userId", userInfo.getId());
        map.put("timeStamp", timeStamp);
        map.put("realName", userInfo.getRealName());
        map.put("identityCode", userInfo.getIdCardNo());
        map.put("userMobile", userInfo.getMobileAccount());
        //TODO 待验证方式
        RiskResponse response = restService.postJson("http://10.10.20.203:9611/app/riskOperator/createTaskAndlogin", map, RiskResponse.class);
//        if (StringUtils.isNotBlank(response)) {
//            JSONObject jsonObject = FaJsonUtil.parseObj(response, JSONObject.class);
//            if (null == jsonObject) {
//                throw new ComBizException(BizCodeEnum.ERROR_SMS_RESULT);
//            }
//            if (Integer.valueOf(jsonObject.get(ComConsts.RISK_CODE).toString()) == ComConsts.RISK_OK) {
//                String taskId = jsonObject.get("task_id").toString();
//                redisService.setCache(RedisConsts.IDENTITY_AUTH_CODE + RedisConsts.SPLIT + userInfo.getId(), taskId, 3600L);
//            } else {
//                throw new ComBizException(BizCodeEnum.UN_IDENTITY_AUTH);
//            }
//        }
    }

    /**
     * 运营商认证 - 输入验证码认证运行商
     */
    public void operatorAuth(PeratorAuthRequest request) {
        GlobalUser user = getGlobalUser();
        String taskId = redisService.getCache(RedisConsts.IDENTITY_AUTH_CODE + RedisConsts.SPLIT + user.getUserId());
        if (StringUtils.isBlank(taskId)) {
            throw new ComBizException(BizCodeEnum.OVER_TIME_SMS_CODE);
        }
        Long timeStamp = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>(16);
        map.put("sign", AesUtil.aesEncode(user.getUserId(), timeStamp));
        map.put("userId", user.getUserId());
        map.put("timeStamp", timeStamp);
        map.put("taskId", taskId);
        map.put("smsCode", request.getSmsCode());
        //TODO 待验证方式
        RiskResponse response = restService.postJson("http://10.10.20.203:9611/app/riskOperator/sendSmsCode", map, RiskResponse.class);
//        afterResponse(response, "运营商验证码认证返回数据异常", user.getUserId(), "运营商认证");
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
        profile.setRemark("个人信息认证");
        personInfoRepository.insertPersonInfoAndUserAuthStatus(profile, userAuthStatus);
    }

    /**
     * 身份证识别
     */
    public IdCardRecognitionResponse idCardRecognition(IdCardRecognitionRequest request) {
        IdCardRecognitionResponse recognitionResponse = new IdCardRecognitionResponse();
        GlobalUser user = getGlobalUser();
        String idCardBase64ImgStr = request.getIdCardBase64ImgStr();
        Long timeStamp = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>(16);
        map.put("sign", AesUtil.aesEncode(user.getUserId(), timeStamp));
        map.put("userId", user.getUserId());
        map.put("timeStamp", timeStamp);
        map.put("imageFile", idCardBase64ImgStr);
        RiskResponse riskResponse = restService.postJson(riskProp.getIdCardRecognitionUrl(), map, RiskResponse.class);
        log.info("身份证识别-风控返回数据："+riskResponse.toString());
        String bobyStr = riskResponse.getBody().toString();
        String name = JSONObject.parseObject(bobyStr, JSONObject.class).getJSONObject("data").getString("name");
        String gender = JSONObject.parseObject(bobyStr, JSONObject.class).getJSONObject("data").getString("gender");
        String idNumber = JSONObject.parseObject(bobyStr, JSONObject.class).getJSONObject("data").getString("idNumber");
        recognitionResponse.setName(name);
        recognitionResponse.setGender(gender);
        recognitionResponse.setIdNumber(idNumber);
        return recognitionResponse;
    }

    /**
     * 人脸识别
     */
    public void faceRecognition(FaceRecognitionRequest request) {
        GlobalUser user = getGlobalUser();
        String faceBase64ImgStr = request.getFaceBase64ImgStr();
        Long timeStamp = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>(16);
        map.put("sign", AesUtil.aesEncode(user.getUserId(), timeStamp));
        map.put("userId", user.getUserId());
        map.put("timeStamp", timeStamp);
        map.put("imageFile", faceBase64ImgStr);
        RiskResponse riskResponse = restService.postJson(riskProp.getFaceRecognitionUrl(),map,RiskResponse.class);
        if(((Boolean) riskResponse.getBody()).booleanValue()==false){
            log.error("人脸认证返回数据："+riskResponse.toString());
            throw new ComBizException(BizCodeEnum.FAIL_FACE_RECOGNITION);
        }
    }

    /**
     * 身份信息认证，信息分3个表存（用户表、身份信息认证表，认证状态表）
     */
    public void identityInfoAuth(IdentityInfoAuthRequest request) {
        GlobalUser user = getGlobalUser();
        String realName = request.getRealName();
        String idCardNo = request.getIdCardNo();
        Integer gender = request.getGender();
        Long timeStamp = System.currentTimeMillis();
        //调风控身份认证接口，认证通过记录表数据
        Map<String, Object> mapRisk = new HashMap<>(16);
        mapRisk.put("sign", AesUtil.aesEncode(user.getUserId(), timeStamp));
        mapRisk.put("userId", user.getUserId());
        mapRisk.put("timeStamp", timeStamp);
        //TODO 待验证方式
        RiskResponse response = restService.postJson("/app/riskOperator/getIdentityReport", mapRisk, RiskResponse.class);
        //TODO
//        if (!isSuccess) {
//            throw new ComBizException(BizCodeEnum.FAIL_IDENTITY_AUTH);
//        }
        String idCardBase64ImgStr = request.getIdCardBase64ImgStr();
        String faceBase64ImgStr = request.getFaceBase64ImgStr();
        String picSuffix = request.getPicSuffix();
        Map<String, Object> map = new HashMap<>(16);
        map.put("imgBase64", idCardBase64ImgStr);
        map.put("pictureSuffix", picSuffix);
        String idCardPhotoPath;
        String facePhotoPath;
        try {
            idCardPhotoPath = getPhotoPath(map);

            map.put("imgBase64", faceBase64ImgStr);
            facePhotoPath = getPhotoPath(map);

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
            //组装pic对象
            List<UserPic> list = Lists.newArrayList();
            list.add(UserPic.builder().id(IdentifierGenerator.nextId()).userId(user.getUserId()).type("idCard").picUrl(idCardPhotoPath).build());
            list.add(UserPic.builder().id(IdentifierGenerator.nextId()).userId(user.getUserId()).type("face").picUrl(facePhotoPath).build());
            //获取UserAuthStatus对象
            UserAuthStatus userAuthStatus = buildUserAuthStatus(user.getUserId(), "身份信息认证");
            workRepository.insertIdentityInfo(list, userAuthStatus, userObj);
        } catch (NestedException e) {
            throw new ComBizException(BizCodeEnum.FAIL_IDENTITY_AUTH);
        }

    }

    /**
     * 专业信息认证
     */
    public void professionalAuth(ProfessionalRequest request) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("imgBase64", request.getEmployeeCardBase64ImgStr());
        map.put("pictureSuffix", request.getPicSuffix());
        String employeeCardPhotoPath;
        String workplacePhotoPath;
        try {
            //照片上传
            employeeCardPhotoPath = getPhotoPath(map);

            map.put("imgBase64", request.getEmployeeCardBase64ImgStr());
            workplacePhotoPath = getPhotoPath(map);

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
            UserAuthStatus userAuthStatus = buildUserAuthStatus(user.getUserId(), "专业信息认证");
            professionalRepository.insertProfessionalAndUserAuthStatus(work, userAuthStatus);
        } catch (Exception ex) {
            throw new ComBizException(BizCodeEnum.FAIL_PROFESSIONAL_AUTH);
        }

    }

    private void afterResponse(String response, String ExMsg, Long userId, String remark) {
        if (StringUtils.isNotBlank(response)) {
            JSONObject jsonObject = FaJsonUtil.parseObj(response, JSONObject.class);
            if (null == jsonObject) {
                throw new ComBizException(BizCodeEnum.SERVICE_EXCEPTION, ExMsg);
            }
            if (Integer.valueOf(jsonObject.get(ComConsts.RISK_CODE).toString()) == ComConsts.RISK_OK) {
                //修改认证表的状态
                UserAuthStatus userAuthStatus = buildUserAuthStatus(userId, remark);
                authStatusRepository.insertSelective(userAuthStatus);
            } else {
                log.error(jsonObject.get("msg").toString());
                throw new ComBizException(BizCodeEnum.FAIL_AUTH);
            }
        }
    }

    private String getPhotoPath(Map<String, Object> map) {
        UploadImgResponse response = restService.postJson("/hzed/easy-get/upload", map, UploadImgResponse.class);
        return response.getVisitUrl();
    }
}
