package com.hzed.easyget.application.service;

import com.google.common.collect.Lists;
import com.hzed.easyget.application.enums.AuthCodeEnum;
import com.hzed.easyget.application.enums.AuthStatusEnum;
import com.hzed.easyget.application.enums.StatusEnum;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.repository.*;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
    private PersonInfoRepository personInfoRepository;
    @Autowired
    private FaceIdcardAuthRepository faceIdcardAuthRepository;
    @Autowired
    private UserAuthStatusRepository authStatusRepository;
    @Autowired
    private ProfessionalRepository professionalRepository;

    /**
     * 获取用户认证状态
     */
    public List<AuthStatusResponse> getAuthStatus() {
        List<AuthStatusResponse> authStatusList = Lists.newArrayList();
        GlobalUser globalUser = RequestUtil.getGlobalUser();
        Long userId = globalUser.getUserId();
        List<UserAuthStatus> userAuthStatus = authStatusRepository.getAuthStatusByUserId(userId);
        for (UserAuthStatus uas : userAuthStatus) {
            Auth auth = authStatusRepository.findAuthByCode(uas.getAuthCode());
            AuthStatusResponse authStatusResponse = new AuthStatusResponse();
            authStatusResponse.setCode(uas.getAuthCode());
            authStatusResponse.setStatus(String.valueOf(uas.getAuthStatus()));
            authStatusResponse.setIsUse(auth.getIsUse() ? StatusEnum.ENABLE.getCode() : StatusEnum.DISENABLE.getCode());
            authStatusList.add(authStatusResponse);
        }
        return authStatusList;
    }

    /**
     * 通讯录认证
     */
    public void authContacts(ContactsRequest request) {
        GlobalUser user = getGlobalUser();
        //写入用户授权信息返回值
        //TODO 调风控接口
        Long userAuthId = IdentifierGenerator.nextId();
        AuthContent authContent = buildAuthContent(request.getContacts(), userAuthId, "通讯录授权");
        UserAuthStatus userAuthStatus = buildUserAuthStatus(user.getUserId(), userAuthId, "通讯录授权");
        authContentRepository.insertContactAndUserAuthStatus(authContent, userAuthStatus);
    }

    /**
     * 构建AuthContent对象
     *
     * @param content
     * @param remark
     */
    public AuthContent buildAuthContent(String content, Long userAuthId, String remark) {
        //写入用户认证授权返回信息
        //TODO 后面应该会去掉
        AuthContent authContent = new AuthContent();
        authContent.setId(IdentifierGenerator.nextId());
        authContent.setUserAuthStatusId(userAuthId);
        authContent.setCreateTime(LocalDateTime.now());
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
    public UserAuthStatus buildUserAuthStatus(Long userId, Long userAuthStatusId, String remark) {
        //保存到数据库短信记录表
        UserAuthStatus userAuthStatus = new UserAuthStatus();
        userAuthStatus.setId(userAuthStatusId);
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
        GlobalUser user = getGlobalUser();
        //写入用户授权信息返回值
        //TODO 调风控接口
        Long userAuthId = IdentifierGenerator.nextId();
        AuthContent authContent = buildAuthContent(request.getMessage(), userAuthId, "短信授权");
        UserAuthStatus userAuthStatus = buildUserAuthStatus(user.getUserId(), userAuthId, "短信授权");
        authContentRepository.insertContactAndUserAuthStatus(authContent, userAuthStatus);
    }

    /**
     * 个人信息认证
     */
    public void authPersonInfo(PersonInfoAuthRequest request) {
        GlobalUser user = getGlobalUser();
        UserAuthStatus userAuthStatus = buildUserAuthStatus(user.getUserId(), IdentifierGenerator.nextId(), "个人信息认证");
        PersonInfo personInfo = new PersonInfo();
        personInfo.setId(IdentifierGenerator.nextId());
        personInfo.setUserId(user.getUserId());
        personInfo.setEducation(request.getEducation());
        personInfo.setCompanyName(request.getCompanyName());
        personInfo.setCompanyAddr(request.getCompanyAddr());
        personInfo.setCompanyAddrDetail(request.getCompanyAddrDetail());
        personInfo.setEmail(request.getEmail());
        personInfo.setParentName(request.getParentName());
        personInfo.setParentTel(request.getParentTel());
        personInfo.setRelationship(request.getRelationship());
        personInfo.setRelatedPersonName(request.getRelatedPersonName());
        personInfo.setRelatedPersonTel(request.getRelatedPersonTel());
        personInfo.setCreateTime(LocalDateTime.now());
        personInfo.setRemark("个人信息认证");
        personInfoRepository.insertPersonInfoAndUserAuthStatus(personInfo, userAuthStatus);
    }

    /**
     * 身份信息认证，信息分3个表存（用户表、身份信息认证表，认证状态表）
     */
    public void identityInfoAuth(IdentityInfoAuthRequest request) {
        GlobalUser user = getGlobalUser();
        String realName = request.getRealName();
        String idCardNo = request.getIdCardNo();
        Integer gender = request.getGender();
        String idCardPhotoPath = request.getIdCardPhotoPath();
        String facePhotoPath = request.getFacePhotoPath();
        //根据拿到json串组装对象
        User userObj = new User();
        FaceIdCard faceIdCard = new FaceIdCard();
        //组装user对象
        userObj.setId(user.getUserId());
        userObj.setRealName(realName);
        userObj.setIdCardNo(idCardNo);
        userObj.setGender(gender.toString());
        userObj.setUpdateTime(LocalDateTime.now());
        userObj.setUpdateBy(user.getUserId());
        //组装faceIdcardAuth对象
        Long faceIdcardAuthId = IdentifierGenerator.nextId();
        faceIdCard.setId(faceIdcardAuthId);
        faceIdCard.setUserId(user.getUserId());
        faceIdCard.setIdCardPhotoPath(idCardPhotoPath);
        faceIdCard.setFacePhotoPath(facePhotoPath);
        faceIdCard.setCreateBy(faceIdcardAuthId);
        faceIdCard.setCreateTime(LocalDateTime.now());
        faceIdCard.setRemark("身份信息认证");
        //获取UserAuthStatus对象
        UserAuthStatus userAuthStatus = buildUserAuthStatus(user.getUserId(), IdentifierGenerator.nextId(), "身份信息认证");
        faceIdcardAuthRepository.insertIdentityInfo(faceIdCard, userAuthStatus, userObj);
    }

    /**
     * 专业信息认证
     */
    public void professionalAuth(ProfessionalRequest request){
        GlobalUser user = getGlobalUser();
        Professional professional =new Professional();
        professional.setId(IdentifierGenerator.nextId());
        professional.setUserId(user.getUserId());
        professional.setJobType(request.getJobType().byteValue());
        professional.setMonthlyIncome(request.getMonthlyIncome().byteValue());
        professional.setEmployeeCard(request.getEmployeeCard());
        professional.setWorkplace(request.getWorkplace());
        professional.setCreateBy(user.getUserId());
        professional.setCreateTime(LocalDateTime.now());
        professional.setRemark("专业信息认证");
        //获取UserAuthStatus对象
        UserAuthStatus userAuthStatus = buildUserAuthStatus(user.getUserId(), IdentifierGenerator.nextId(), "专业信息认证");
        professionalRepository.insertProfessionalAndUserAuthStatus(professional, userAuthStatus);
    }
}
