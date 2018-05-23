package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.AuthContent;
import com.hzed.easyget.persistence.auto.entity.UserAuthStatus;
import com.hzed.easyget.persistence.auto.mapper.AuthContentMapper;
import com.hzed.easyget.persistence.auto.mapper.UserAuthStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hfj
 * @date 2018/5/23
 */
@Repository
public class AuthContentRepository {
    @Autowired
    private AuthContentMapper authContentMapper;
    @Autowired
    private UserAuthStatusMapper userAuthStatusMapper;

    @Transactional(rollbackFor = Exception.class)
    public void insertContactAndUserAuthStatus(AuthContent authContent, UserAuthStatus userAuthStatus) {
        authContentMapper.insertSelective(authContent);
        userAuthStatusMapper.insertSelective(userAuthStatus);
    }
}