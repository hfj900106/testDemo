package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.UserAuthStatus;
import com.hzed.easyget.persistence.auto.mapper.UserAuthStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author hfj
 * @date 2018/5/22
 */
@Repository
public class UserAuthStatusRepository {

    @Autowired
    private UserAuthStatusMapper userAuthStatusMapper;

    public void insertSelective(UserAuthStatus userAuthStatus) {
        userAuthStatusMapper.insertSelective(userAuthStatus);
    }


}
