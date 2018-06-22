package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.controller.model.RepaymentRequest;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.persistence.auto.entity.UserTransaction;
import com.hzed.easyget.persistence.auto.entity.UserTransactionRepay;
import com.hzed.easyget.persistence.auto.entity.example.UserTransactionExample;
import com.hzed.easyget.persistence.auto.entity.example.UserTransactionRepayExample;
import com.hzed.easyget.persistence.auto.mapper.UserTransactionMapper;
import com.hzed.easyget.persistence.auto.mapper.UserTransactionRepayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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
    public UserTransaction findoldTrance(UserTransactionExample userTransactionExample) {
        return userTransactionMapper.selectOneByExample(userTransactionExample);
    }

    /**
     * 返回交易记录
     */
    public UserTransaction selectByKey(Long payId, byte status) {
        UserTransactionExample userTransactionExample = new UserTransactionExample();
        userTransactionExample.createCriteria().andIdEqualTo(payId).andStatusEqualTo(status);
        return userTransactionMapper.selectOneByExample(userTransactionExample);
    }

    /**
     * 获取所有的va码记录
     */
    public List<UserTransactionRepay> finaAllVAcodeBypermas(RepaymentRequest request) {
        UserTransactionRepayExample repayExample = new UserTransactionRepayExample();
        repayExample.createCriteria()
                .andTransactionIdEqualTo(request.getPayId())
                .andModeEqualTo(request.getMode());
        return userTransactionRepayMapper.selectByExample(repayExample);

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
     * 找到还款两小时后还处于交易中的数据
     */
    public List<UserTransaction> findUserTransToUpdateRepayFail(){
        UserTransactionExample repayExample = new UserTransactionExample();
        repayExample.createCriteria().andTypeEqualTo((byte)2).andStatusEqualTo((byte)1).andConfirmTimeLessThan(DateUtil.addHour(LocalDateTime.now(),-2));
        return userTransactionMapper.selectByExample(repayExample);
    }
}