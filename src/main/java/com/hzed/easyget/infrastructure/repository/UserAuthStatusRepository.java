package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.AuthItem;
import com.hzed.easyget.persistence.auto.entity.UserAuthStatus;
import com.hzed.easyget.persistence.auto.entity.example.AuthItemExample;
import com.hzed.easyget.persistence.auto.entity.example.UserAuthStatusExample;
import com.hzed.easyget.persistence.auto.mapper.AuthItemMapper;
import com.hzed.easyget.persistence.auto.mapper.UserAuthStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    @Autowired
    private AuthItemMapper authItemMapper;

    public UserAuthStatus findAuthStatusByUserId(Long userId,String authCode) {
        UserAuthStatusExample example = new UserAuthStatusExample();
        example.createCriteria().andUserIdEqualTo(userId).andAuthCodeEqualTo(authCode);
        return userAuthStatusMapper.selectOneByExample(example);
    }

    public void insertSelective(UserAuthStatus userAuthStatus) {
        userAuthStatusMapper.insertSelective(userAuthStatus);
    }

    public AuthItem findAuthByCode(String authCode) {
        AuthItemExample example = new AuthItemExample();
        example.createCriteria().andCodeEqualTo(authCode).andIsUseEqualTo(true);
        return authItemMapper.selectOneByExample(example);
    }
}
