package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.AuthItem;
import com.hzed.easyget.persistence.auto.entity.UserAuthStatus;
import com.hzed.easyget.persistence.auto.entity.example.AuthItemExample;
import com.hzed.easyget.persistence.auto.entity.example.UserAuthStatusExample;
import com.hzed.easyget.persistence.auto.mapper.AuthItemMapper;
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
    private AuthItemMapper authItemMapper;


    public List<UserAuthStatus> getAuthStatusByUserId(Long userId) {
        UserAuthStatusExample example = new UserAuthStatusExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return userAuthStatusMapper.selectByExample(example);
    }
    public void insertSelective(UserAuthStatus userAuthStatus) {
        userAuthStatusMapper.insertSelective(userAuthStatus);
    }


    public AuthItem findAuthByCode(String authCode) {

        AuthItemExample example = new AuthItemExample();
        example.createCriteria().andCodeEqualTo(authCode);
        return authItemMapper.selectOneByExample(example);
    }
}
