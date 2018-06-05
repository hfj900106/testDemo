package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.Profile;
import com.hzed.easyget.persistence.auto.entity.UserAuthStatus;
import com.hzed.easyget.persistence.auto.mapper.ProfileMapper;
import com.hzed.easyget.persistence.auto.mapper.UserAuthStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hfj
 * @date 2018/5/23
 */
@Repository
public class PersonInfoRepository {
    @Autowired
    private ProfileMapper profileMapper;
    @Autowired
    private UserAuthStatusMapper userAuthStatusMapper;

    @Transactional(rollbackFor = Exception.class)
    public void insertPersonInfoAndUserAuthStatus(Profile profile, UserAuthStatus userAuthStatus) {
        profileMapper.insertSelective(profile);
        userAuthStatusMapper.insertSelective(userAuthStatus);
    }
}