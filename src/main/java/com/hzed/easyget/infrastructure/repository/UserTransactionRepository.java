package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.UserTransaction;
import com.hzed.easyget.persistence.auto.mapper.UserTransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author guichang
 * @since 2018/4/3
 */

@Repository
public class UserTransactionRepository {
    @Autowired
    private UserTransactionMapper userTransactionMapper;

    public void insert(UserTransaction userTransaction) {
        userTransactionMapper.insertSelective(userTransaction);
    }
}