package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.PersonInfo;
import com.hzed.easyget.persistence.auto.entity.UserAuthStatus;
import com.hzed.easyget.persistence.auto.mapper.PersonInfoMapper;
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
    private PersonInfoMapper personInfoMapper;
    @Autowired
    private UserAuthStatusMapper userAuthStatusMapper;

    @Transactional(rollbackFor = Exception.class)
    public void insertPersonInfoAndUserAuthStatus(PersonInfo personInfo, UserAuthStatus userAuthStatus) {
        personInfoMapper.insertSelective(personInfo);
        userAuthStatusMapper.insertSelective(userAuthStatus);
    }
}