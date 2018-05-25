package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.FaceIdcardAuth;
import com.hzed.easyget.persistence.auto.entity.User;
import com.hzed.easyget.persistence.auto.entity.UserAuthStatus;
import com.hzed.easyget.persistence.auto.mapper.FaceIdcardAuthMapper;
import com.hzed.easyget.persistence.auto.mapper.UserAuthStatusMapper;
import com.hzed.easyget.persistence.auto.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author hfj
 * @date 2018/5/22
 */
@Repository
public class FaceIdcardAuthRepository {
    @Autowired
    private FaceIdcardAuthMapper faceIdcardAuthMapper;
    @Autowired
    private UserAuthStatusMapper userAuthStatusMapper;
    @Autowired
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    public void insertIdentityInfo(FaceIdcardAuth faceIdcardAuth, UserAuthStatus userAuthStatus, User user) {
        faceIdcardAuthMapper.insertSelective(faceIdcardAuth);
        userAuthStatusMapper.insertSelective(userAuthStatus);
        userMapper.updateByPrimaryKeySelective(user);
    }


}
