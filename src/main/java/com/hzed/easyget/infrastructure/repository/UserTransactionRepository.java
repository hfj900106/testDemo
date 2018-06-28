package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.UserTransaction;
import com.hzed.easyget.persistence.auto.entity.example.UserTransactionExample;
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

    public void insertSelective(UserTransaction transaction) {
        userTransactionMapper.insertSelective(transaction);
    }

    public UserTransaction findUserTranByPaymentId(String paymnetId,Byte type) {
        UserTransactionExample userTransactionExample = new UserTransactionExample();
        userTransactionExample.createCriteria().andPaymentIdEqualTo(paymnetId).andTypeEqualTo(type);
        return userTransactionMapper.selectOneByExample(userTransactionExample);
    }


    /**
     * 修改交易表信息
     */
    public void transactionUpdateByKey(UserTransaction transaction) {
        userTransactionMapper.updateByPrimaryKeySelective(transaction);
    }

}