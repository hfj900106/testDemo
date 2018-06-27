package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.application.enums.TransactionTypeEnum;
import com.hzed.easyget.controller.model.RepaymentRequest;
import com.hzed.easyget.persistence.auto.entity.UserTransaction;
import com.hzed.easyget.persistence.auto.entity.UserTransactionRepay;
import com.hzed.easyget.persistence.auto.entity.example.UserTransactionExample;
import com.hzed.easyget.persistence.auto.entity.example.UserTransactionRepayExample;
import com.hzed.easyget.persistence.auto.mapper.UserTransactionMapper;
import com.hzed.easyget.persistence.auto.mapper.UserTransactionRepayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author guichang
 * @since 2018/4/3
 */

@Repository
public class UserTransactionRepository {
    @Autowired
    private UserTransactionMapper userTransactionMapper;
    @Autowired
    private UserTransactionRepayMapper userTransactionRepayMapper;

    public void insert(UserTransaction userTransaction) {
        userTransactionMapper.insertSelective(userTransaction);
    }

    public void insertSelective(UserTransaction transaction) {
        userTransactionMapper.insertSelective(transaction);
    }

    public UserTransaction findUserTranByPaymentId(String paymnetId) {
        UserTransactionExample userTransactionExample = new UserTransactionExample();
        userTransactionExample.createCriteria().andPaymentIdEqualTo(paymnetId);
        return userTransactionMapper.selectOneByExample(userTransactionExample);
    }

    /**
     * 查询匹配的交易记录
     */
    public UserTransaction findOldTrance(Long bidId, byte type, byte status,byte repayMentType) {
        UserTransactionExample userTransactionExample=new UserTransactionExample();
        userTransactionExample.createCriteria()
                .andBidIdEqualTo(bidId)
                .andTypeEqualTo(type)
                .andStatusEqualTo(status)
                .andRepaymentTypeEqualTo(repayMentType);
        return userTransactionMapper.selectOneByExample(userTransactionExample);
    }

    /**
     * 修改交易表信息
     */
    public void transactionUpdateByKey(UserTransaction transaction) {
        userTransactionMapper.updateByPrimaryKeySelective(transaction);
    }

    /**
     * 根据主键返回查询对象
     */
    public UserTransaction findUserTranById(Long payId) {
        return userTransactionMapper.selectByPrimaryKey(payId);
    }

    /**
     * 查询交易信息
     */
    public UserTransaction findOldTranceByExample(Long payId, byte type,Byte status) {
        UserTransactionExample userTransactionExample = new UserTransactionExample();
        userTransactionExample.createCriteria()
                .andIdEqualTo(payId)
                .andTypeEqualTo(TransactionTypeEnum.OUT.getCode().byteValue())
                .andStatusEqualTo(status);
        return userTransactionMapper.selectOneByExample(userTransactionExample);
    }
}