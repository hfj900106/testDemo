package com.hzed.easyget.application.service;

import com.alibaba.fastjson.JSONObject;
import com.hzed.easyget.application.enums.AuthCodeEnum;
import com.hzed.easyget.application.enums.AuthStatusEnum;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.repository.AuthContentRepository;
import com.hzed.easyget.infrastructure.repository.FaceIdcardAuthRepository;
import com.hzed.easyget.infrastructure.repository.PersonInfoRepository;
import com.hzed.easyget.infrastructure.repository.UserRepository;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.FaJsonUtil;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.*;
import com.sun.tools.javac.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
    private AuthContentRepository authContentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PersonInfoRepository personInfoRepository;
    @Autowired
    private FaceIdcardAuthRepository faceIdcardAuthRepository;

    /**
     * 通讯录认证
     *
     * @param request
     */
    public void authContacts(ContactsRequest request) {
        GlobalUser user = getGlobalUser();
        //写入用户授权信息返回值
        Long userAuthId = IdentifierGenerator.nextId();
        AuthContent authContent = buildAuthContent(request.getContacts(), "通讯录授权", userAuthId);
        UserAuthStatus userAuthStatus = buildUserAuthStatus(user.getUserId(), "通讯录授权", userAuthId);
        authContentRepository.insertContactAndUserAuthStatus(authContent, userAuthStatus);
    }

    /**
     * 构建AuthContent对象
     *
     * @param content
     * @param remark
     */
    public AuthContent buildAuthContent(String content, String remark, Long userAuthId) {
        //写入用户认证授权返回信息
        AuthContent authContent = new AuthContent();
        authContent.setId(IdentifierGenerator.nextId());
        authContent.setUserAuthStatusId(userAuthId);
        authContent.setCreateBy(0L);
        authContent.setCreateTime(LocalDateTime.now());
        authContent.setUpdateBy(0L);
        authContent.setUpdateTime(LocalDateTime.now());
        authContent.setRemark(remark);
        authContent.setContent(content);
        return authContent;
    }

    /**
     * 构建UserAuthStatus对象
     *
     * @param userId
     * @param remark
     */
    public UserAuthStatus buildUserAuthStatus(Long userId, String remark, Long userAuthStatusId) {
        //保存到数据库短信记录表
        UserAuthStatus userAuthStatus = new UserAuthStatus();
        userAuthStatus.setId(userAuthStatusId);
        userAuthStatus.setUserId(userId);
        userAuthStatus.setAuthCode(AuthCodeEnum.CONTACTS.getCode());
        userAuthStatus.setAuthStatus(Integer.valueOf(AuthStatusEnum.HAS_AUTH.getCode()));
        //过期时间，半年
        userAuthStatus.setExpireTime(DateUtil.addMonth(LocalDateTime.now(), 6));
        userAuthStatus.setCreateBy(0L);
        userAuthStatus.setCreateTime(LocalDateTime.now());
        userAuthStatus.setUpdateBy(0L);
        userAuthStatus.setUpdateTime(LocalDateTime.now());
        userAuthStatus.setRemark(remark);
        return userAuthStatus;
    }

    /**
     * 短信认证
     *
     * @param request
     */
    public void authMessages(MessagesRequest request) {
        GlobalUser user = getGlobalUser();
        //写入用户授权信息返回值
        Long userAuthId = IdentifierGenerator.nextId();
        AuthContent authContent = buildAuthContent(request.getMessage(), "短信授权", userAuthId);
        UserAuthStatus userAuthStatus = buildUserAuthStatus(user.getUserId(), "短信授权", userAuthId);
        authContentRepository.insertContactAndUserAuthStatus(authContent, userAuthStatus);
    }

    /**
     * 运营商认证
     *
     * @param request
     */
    public void authSms(SmsAuthRequest request) {
        GlobalUser user = getGlobalUser();
        //更新用户运营商服务密码
        User user1 = new User();
        user1.setId(user.getUserId());
        user1.setSmsPassword(request.getServerKey());
        userRepository.updateServerKey(user1);
    }

    /**
     * 个人信息认证
     *
     * @param request
     */
    public void authPersonInfo(PersonInfoAuthRequest request) {
        GlobalUser user = getGlobalUser();
        String personInfoStr = request.getDate();
        //根据拿到json串组装对象
        PersonInfo personInfo = FaJsonUtil.parseObj(personInfoStr, PersonInfo.class);
        if (null != personInfo) {
            Long userAuthId = IdentifierGenerator.nextId();
            UserAuthStatus userAuthStatus = buildUserAuthStatus(user.getUserId(), "个人信息认证", userAuthId);
            personInfo.setId(IdentifierGenerator.nextId());
            personInfo.setUserId(user.getUserId());
            personInfo.setUserStatusId(userAuthId);
            personInfo.setRemark("个人信息认证");
            personInfoRepository.insertPersonInfoAndUserAuthStatus(personInfo, userAuthStatus);
        } else {
            throw new ComBizException(BizCodeEnum.ILLEGAL_PARAM, "数据不符合要求，请仔细核对");
        }
    }

    /**
     * 身份信息认证，信息分两个表存（用户表、身份信息认证表）
     */
    public void identityInformationAuth(IdentityInformationAuthRequest request) {
        GlobalUser user = getGlobalUser();
        String identityInformationStr = request.getDate();
        JSONObject jsStr = JSONObject.parseObject(identityInformationStr);
        String realName = (String) jsStr.get("realName");
        String idCardNo = (String) jsStr.get("idCardNo");
        String gender = (String) jsStr.get("gender");
        String idCardPhotoPath = (String) jsStr.get("idCardPhotoPath");
        String facePhotoPath = (String) jsStr.get("facePhotoPath");
        //根据拿到json串组装对象
        User userObj = new User();
        FaceIdcardAuth faceIdcardAuth = new FaceIdcardAuth();
        if (null != userObj && null != faceIdcardAuth) {
            //组装user对象
            userObj.setId(user.getUserId());
            userObj.setRealName(realName);
            userObj.setIdCardNo(idCardNo);
            userObj.setGender(gender);
            userObj.setUpdateTime(LocalDateTime.now());
            userObj.setUpdateBy(user.getUserId());
            //组装faceIdcardAuth对象
            Long faceIdcardAuthId = IdentifierGenerator.nextId();
            Long userAuthStatusId = IdentifierGenerator.nextId();
            faceIdcardAuth.setId(faceIdcardAuthId);
            faceIdcardAuth.setUserId(user.getUserId());
            faceIdcardAuth.setUserStatusId(userAuthStatusId);
            faceIdcardAuth.setIdCardPhotoPath(idCardPhotoPath);
            faceIdcardAuth.setFacePhotoPath(facePhotoPath);
            faceIdcardAuth.setCreateBy(faceIdcardAuthId);
            faceIdcardAuth.setCreateTime(LocalDateTime.now());
            faceIdcardAuth.setRemark("身份信息认证");
            //获取UserAuthStatus对象
            UserAuthStatus userAuthStatus = buildUserAuthStatus(user.getUserId(), "通讯录授权", userAuthStatusId);
            faceIdcardAuthRepository.insertIdentityInformation(faceIdcardAuth, userAuthStatus, userObj);
        } else {
            throw new ComBizException(BizCodeEnum.ILLEGAL_PARAM, "数据不符合要求，请仔细核对");
        }
    }
}
