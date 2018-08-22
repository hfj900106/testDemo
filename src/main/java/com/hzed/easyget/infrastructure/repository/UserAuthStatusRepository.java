package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.UserAuthStatus;
import com.hzed.easyget.persistence.auto.entity.example.UserAuthStatusExample;
import com.hzed.easyget.persistence.auto.mapper.UserAuthStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author hfj
 * @data 2018/5/23
 */
@Repository
public class UserAuthStatusRepository {

    @Autowired
    private UserAuthStatusMapper userAuthStatusMapper;

    public UserAuthStatus findEnableAuthStatusByUserId(Long userId, String authCode,Integer status) {
        UserAuthStatusExample example = new UserAuthStatusExample();
        example.createCriteria().andUserIdEqualTo(userId).andAuthCodeEqualTo(authCode).andAuthStatusEqualTo(status);
        return userAuthStatusMapper.selectOneByExample(example);
    }

    public void insertSelective(UserAuthStatus userAuthStatus) {
        userAuthStatusMapper.insertSelective(userAuthStatus);
    }

}
