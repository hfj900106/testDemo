package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.TransactionRecord;
import com.hzed.easyget.persistence.auto.entity.User;
import com.hzed.easyget.persistence.auto.entity.example.TransactionRecordExample;
import com.hzed.easyget.persistence.auto.entity.example.UserExample;
import com.hzed.easyget.persistence.auto.mapper.TransactionRecordMapper;
import com.hzed.easyget.persistence.auto.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    private TransactionRecordMapper transactionRecordMapper;

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

    public List<TransactionRecord> findTransactionRecordBySelect(Long userId,Boolean isDisplay){
        TransactionRecordExample transactionRecordExample = new TransactionRecordExample();
        transactionRecordExample.createCriteria().andUserIdEqualTo(userId).andIsDisplayEqualTo(isDisplay);
        List<TransactionRecord> transactionRecords = transactionRecordMapper.selectByExampleSelective(transactionRecordExample);
        return transactionRecords ;
    }


}