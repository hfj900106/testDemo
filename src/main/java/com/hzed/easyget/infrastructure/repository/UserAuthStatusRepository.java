package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.UserAuthStatus;
import com.hzed.easyget.persistence.auto.entity.example.UserAuthStatusExample;
import com.hzed.easyget.persistence.auto.mapper.UserAuthStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * 暂无描述
 *
 * @author wuchengwu
 * @author hfj
 * @data 2018/5/23
 * /**
 * @date 2018/5/22
 */
@Repository
public class UserAuthStatusRepository {

    @Autowired
    private UserAuthStatusMapper userAuthStatusMapper;

    public UserAuthStatus findEnableAuthStatusByUserId(Long userId, String authCode) {
        UserAuthStatusExample example = new UserAuthStatusExample();
        example.createCriteria().andUserIdEqualTo(userId).andAuthCodeEqualTo(authCode).andExpireTimeGreaterThan(LocalDateTime.now());
        return userAuthStatusMapper.selectOneByExample(example);
    }

    public void insertSelective(UserAuthStatus userAuthStatus) {
        userAuthStatusMapper.insertSelective(userAuthStatus);
    }

}
