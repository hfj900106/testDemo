package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.Auth;
import com.hzed.easyget.persistence.auto.entity.UserAuthStatus;
import com.hzed.easyget.persistence.auto.entity.example.AuthExample;
import com.hzed.easyget.persistence.auto.entity.example.UserAuthStatusExample;
import com.hzed.easyget.persistence.auto.mapper.AuthMapper;
import com.hzed.easyget.persistence.auto.mapper.UserAuthStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 暂无描述
 *
 * @author wuchengwu
 * @data 2018/5/23
/**
 * @author hfj
 * @date 2018/5/22
 */
@Repository
public class UserAuthStatusRepository {

    @Autowired
    private UserAuthStatusMapper userAuthStatusMapper;
    @Autowired
    private AuthMapper authMapper;


    public List<UserAuthStatus> getAuthStatusByUserId(Long userId) {
        UserAuthStatusExample example = new UserAuthStatusExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return userAuthStatusMapper.selectByExample(example);
    }
    public void insertSelective(UserAuthStatus userAuthStatus) {
        userAuthStatusMapper.insertSelective(userAuthStatus);
    }


    public Auth findAuthByCode(String authCode) {

        AuthExample example = new AuthExample();
        example.createCriteria().andCodeEqualTo(authCode);
        return authMapper.selectOneByExample(example);
    }
}
