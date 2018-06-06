package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.User;
import com.hzed.easyget.persistence.auto.entity.UserAuthStatus;
import com.hzed.easyget.persistence.auto.entity.UserPic;
import com.hzed.easyget.persistence.auto.mapper.UserAuthStatusMapper;
import com.hzed.easyget.persistence.auto.mapper.UserMapper;
import com.hzed.easyget.persistence.auto.mapper.UserPicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author hfj
 * @date 2018/5/22
 */
@Repository
public class WorkRepository {
    @Autowired
    private UserPicMapper userPicMapper;
    @Autowired
    private UserAuthStatusMapper userAuthStatusMapper;
    @Autowired
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    public void insertIdentityInfo(UserPic userPic, UserAuthStatus userAuthStatus, User user) {
        userPicMapper.insertSelective(userPic);
        userAuthStatusMapper.insertSelective(userAuthStatus);
        userMapper.updateByPrimaryKeySelective(user);
    }


}