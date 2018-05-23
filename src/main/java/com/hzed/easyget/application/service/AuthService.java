package com.hzed.easyget.application.service;

import com.hzed.easyget.application.enums.AuthCodeEnum;
import com.hzed.easyget.controller.model.ContactsRequest;
import com.hzed.easyget.controller.model.MessagesRequest;
import com.hzed.easyget.controller.model.SmsAuthRequest;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.repository.AuthContentRepository;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.AuthContent;
import com.hzed.easyget.persistence.auto.entity.UserAuthStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.hzed.easyget.infrastructure.utils.RequestUtil.getGlobalUser;

/**
 * 认证信息公用
 * @author hfj
 * @date 2018/5/23
 */

@Slf4j
@Service
public class AuthService {
    @Autowired
    private AuthContentRepository authContentRepository;

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
     * 将返回值写入认证返回内容表
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
     * 修改用户认证表的认证状态
     *
     * @param userId
     * @param remark
     */
    public UserAuthStatus buildUserAuthStatus(Long userId, String remark, Long userAuthId) {
        //保存到数据库短信记录表
        UserAuthStatus userAuthStatus = new UserAuthStatus();
        userAuthStatus.setId(userAuthId);
        userAuthStatus.setUserId(userId);
        userAuthStatus.setAuthCode(AuthCodeEnum.CONTACTS.getCode());
        userAuthStatus.setAuthStatus(200);
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
     * @param request
     */
    public void authSms(SmsAuthRequest request) {
        GlobalUser user = getGlobalUser();
        //写入用户授权信息返回值
        Long userAuthId = IdentifierGenerator.nextId();
        //未完成
    }

}
