package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.application.enums.TransactionRepayEnum;
import com.hzed.easyget.application.enums.TransactionTypeEnum;
import com.hzed.easyget.controller.model.RepaymentRequest;
import com.hzed.easyget.persistence.auto.entity.Bid;
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

    public List<UserTransaction> findUserTranBypayMenid(String paymnetId) {
        UserTransactionExample userTransactionExample=new UserTransactionExample();
        userTransactionExample.createCriteria().andPaymentIdEqualTo(paymnetId);
        return  userTransactionMapper.selectByExample(userTransactionExample);
    }

    /**
     * 查询匹配的交易记录
     * @param bidId 标
     * @param flag 是否全部结清 true 是
     * @param type 交易类型
     * @param state 状态
     * @return
     */
    public UserTransaction findoldTrance(Long bidId, boolean flag, Byte type, Byte state) {
        UserTransactionExample userTransactionExample=new UserTransactionExample();
        userTransactionExample.createCriteria().andBidIdEqualTo(bidId)
                .andTypeEqualTo(type)
                .andStatusEqualTo(state)
                .andRepaymentTypeEqualTo(flag?TransactionTypeEnum.ALL_CLEAR.getCode().byteValue():TransactionTypeEnum.PARTIAL_CLEARANCE.getCode().byteValue());
        return  userTransactionMapper.selectOneByExample(userTransactionExample);
    }

    /**
     * 主键返回记录
     * @param payId
     * @return
     */
    public UserTransaction selectByKey(Long payId) {
        return  userTransactionMapper.selectByPrimaryKey(payId);
    }

    /**
     * 获取所有的va码记录
     * @param request
     * @return
     */
    public List<UserTransactionRepay> finaAllVAcodeBypermas(RepaymentRequest request) {
        UserTransactionRepayExample repayExample=new UserTransactionRepayExample();
        repayExample.createCriteria()
                .andTransactionIdEqualTo(request.getPayId())
                .andModeEqualTo(request.getMode())
                .andStatusEqualTo(TransactionRepayEnum.TO_BE_TREATED.getCode().byteValue());
        return userTransactionRepayMapper.selectByExample(repayExample);

    }
    /**
     * 修改va码状态 处理中
     * @param x
     */
    public void updateUserTransacRepayState(UserTransactionRepay x) {
        userTransactionRepayMapper.updateByPrimaryKeySelective(x);
    }

    /**
     * 修改交易表信息
     * @param transaction
     */
    public void transactionUpdateByKey(UserTransaction transaction) {
        userTransactionMapper.updateByPrimaryKeySelective(transaction);
    }
}