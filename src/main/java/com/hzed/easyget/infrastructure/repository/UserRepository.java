package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.*;
import com.hzed.easyget.persistence.auto.entity.example.UserExample;
import com.hzed.easyget.persistence.auto.entity.example.UserTransactionExample;
import com.hzed.easyget.persistence.auto.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * demo
 *
 * @author guichang
 * @since 2018/4/3
 */

@Repository
public class UserRepository {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserTransactionMapper userTransactionMapper;
    @Autowired
    private UserTokenMapper tokenMapper;
    @Autowired
    private UserLoginMapper loginMapper;
    @Autowired
    private UserStatusMapper statusMapper;

    public User findByMobile(String mobile) {
        UserExample example = new UserExample();
        example.createCriteria().andMobileAccountEqualTo(mobile);
        return userMapper.selectOneByExample(example);
    }

    public void updateLastLoginTime(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    public void insert(User user) {

        userMapper.insertSelective(user);
    }

    public void updateServerKey(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    public User findById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public List<UserTransaction> findTransactionRecordBySelect(Long userId) {
        UserTransactionExample example = new UserTransactionExample();
        example.createCriteria().andUserIdEqualTo(userId).andStatusEqualTo((byte)2);
        List<UserTransaction> transactionRecords = userTransactionMapper.selectByExample(example);
        return transactionRecords;
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertUserAndTokenAndLoginAndStatus(User user, UserToken token, UserLogin login, UserStatus status) {
        userMapper.insertSelective(user);
        tokenMapper.insertSelective(token);
        loginMapper.insertSelective(login);
        statusMapper.insertSelective(status);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateTokenAndInsertLogin(UserToken token, UserLogin login) {
        tokenMapper.updateByPrimaryKeySelective(token);
        loginMapper.insertSelective(login);
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertTokenAndLogin(UserToken token, UserLogin login) {
        tokenMapper.insertSelective(token);
        loginMapper.insertSelective(login);
    }


}