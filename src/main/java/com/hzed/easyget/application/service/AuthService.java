package com.hzed.easyget.application.service;

import com.google.common.collect.Lists;
import com.hzed.easyget.application.enums.AuthCodeEnum;
import com.hzed.easyget.application.enums.AuthStatusEnum;
import com.hzed.easyget.application.enums.StatusEnum;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.config.aliyun.AliyunService;
import com.hzed.easyget.infrastructure.exception.NestedException;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.repository.FaceIdcardAuthRepository;
import com.hzed.easyget.infrastructure.repository.PersonInfoRepository;
import com.hzed.easyget.infrastructure.repository.ProfessionalRepository;
import com.hzed.easyget.infrastructure.repository.UserAuthStatusRepository;
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
    private PersonInfoRepository personInfoRepository;
    @Autowired
    private FaceIdcardAuthRepository faceIdcardAuthRepository;
    @Autowired
    private UserAuthStatusRepository authStatusRepository;
    @Autowired
    private ProfessionalRepository professionalRepository;
    @Autowired
    private AliyunService aliyunService;

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
            authStatusResponse.setIsUse(auth.getIsUse() ? StatusEnum.ENABLE.getCode() : StatusEnum.DISENABLE.getCode());
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
//        GlobalUser user = getGlobalUser();
//        //写入用户授权信息返回值
//        //TODO 调风控接口
//        Long userAuthId = IdentifierGenerator.nextId();
//        UserAuthStatus userAuthStatus = buildUserAuthStatus(user.getUserId(), userAuthId, "短信授权");
//        authContentRepository.insertContactAndUserAuthStatus(authContent, userAuthStatus);
    }

    /**
     * 个人信息认证
     */
    public void authPersonInfo(PersonInfoAuthRequest request) {
        GlobalUser user = getGlobalUser();
        UserAuthStatus userAuthStatus = buildUserAuthStatus(user.getUserId(), IdentifierGenerator.nextId(), "个人信息认证");
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
        UserAuthStatus userAuthStatus = buildUserAuthStatus(user.getUserId(), IdentifierGenerator.nextId(), "身份信息认证");
        faceIdcardAuthRepository.insertIdentityInfo(userPic, userAuthStatus, userObj);
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
        UserAuthStatus userAuthStatus = buildUserAuthStatus(user.getUserId(), IdentifierGenerator.nextId(), "专业信息认证");
        professionalRepository.insertProfessionalAndUserAuthStatus(work, userAuthStatus);
    }
}
