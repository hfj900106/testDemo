package com.hzed.easyget.application.service;

import com.hzed.easyget.application.enums.AuthCodeEnum;
import com.hzed.easyget.infrastructure.repository.AuthContentRepository;
import com.hzed.easyget.infrastructure.repository.UserAuthStatusRepository;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.AuthContent;
import com.hzed.easyget.persistence.auto.entity.UserAuthStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 认证信息公用
 * @author hfj
 * @date 2018/5/23
 */

@Slf4j
@Service
public class AuthService {
    @Autowired
    private UserAuthStatusRepository userAuthStatusRepository ;
    @Autowired
    private AuthContentRepository authContentRepository ;

    private Long userAuthId = IdentifierGenerator.nextId();


        public  void insertAuthContent(String content ,String remark){
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
            authContentRepository.insertSelective(authContent);

         }

        public  void insertUserAuthStatus(  Long userId ,String remark){
            //保存到数据库短信记录表
            UserAuthStatus userAuthStatus = new UserAuthStatus();
            userAuthStatus.setId(userAuthId);
            userAuthStatus.setUserId(userId);
            userAuthStatus.setAuthCode(AuthCodeEnum.CONTACTS.getCode());
            userAuthStatus.setAuthStatus(200);
            //过期时间，半年
            userAuthStatus.setExpireTime(DateUtil.addMonth(LocalDateTime.now(),6));
            userAuthStatus.setCreateBy(0L);
            userAuthStatus.setCreateTime(LocalDateTime.now());
            userAuthStatus.setUpdateBy(0L);
            userAuthStatus.setUpdateTime(LocalDateTime.now());
            userAuthStatus.setRemark(remark);
            userAuthStatusRepository.insertSelective(userAuthStatus);
        }



}
