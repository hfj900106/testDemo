package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.RepayInfoFlowJob;
import com.hzed.easyget.persistence.auto.entity.UserTransaction;
import com.hzed.easyget.persistence.auto.entity.UserTransactionRepay;
import com.hzed.easyget.persistence.auto.entity.example.BillExample;
import com.hzed.easyget.persistence.auto.entity.example.UserTransactionRepayExample;
import com.hzed.easyget.persistence.auto.mapper.BillMapper;
import com.hzed.easyget.persistence.auto.mapper.RepayInfoFlowJobMapper;
import com.hzed.easyget.persistence.auto.mapper.UserTransactionMapper;
import com.hzed.easyget.persistence.auto.mapper.UserTransactionRepayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 还款 仓储类
 *
 * @description：
 * @author：[zhangruilin]
 * @time：2018/6/15-15:49
 **/
@Repository
public class RepayRepository {
    @Autowired
    private UserTransactionRepayMapper repayMapper;
    @Autowired
    private RepayInfoFlowJobMapper repayInfoFlowJobMapper;
    @Autowired
    private UserTransactionMapper userTransactionMapper;
    @Autowired
    private BillMapper billMapper;

    /**
     * 标的应还时间
     */
    public LocalDateTime findRepaymentTime(Long bidId) {
        BillExample billExample = new BillExample();
        billExample.createCriteria().andBidIdEqualTo(bidId);
        return billMapper.selectOneByExample(billExample).getRepaymentTime();
    }

    /**
     * 生成va码记录
     */
    public void insertSelective(UserTransactionRepay repay) {
        repayMapper.insertSelective(repay);
    }


    /**
     * 还款完成走信息流
     */
    @Transactional(rollbackFor = Exception.class)
    public void afterRepayment(UserTransaction transaction, RepayInfoFlowJob repayInfoFlowJob, UserTransactionRepay repayUpdate) {
        //修改交易记录
        userTransactionMapper.updateByPrimaryKeySelective(transaction);
        //插入还款定时任务
        repayInfoFlowJobMapper.insertSelective(repayInfoFlowJob);
        //修改va码信息
        UserTransactionRepayExample repayExample=new UserTransactionRepayExample();
        repayExample.createCriteria().andPaymentIdEqualTo(repayUpdate.getPaymentId());
        repayMapper.updateByExampleSelective(repayUpdate,repayExample);
    }

    /**
     * 修改交易信息
     */
    public void updateByPrimaryKey(UserTransaction userTransaction) {
        userTransactionMapper.updateByPrimaryKeySelective(userTransaction);
    }

    /**
     * 根据bid 金额 还款类型 获取va码
     */
    public UserTransactionRepay getVaCode(Long id, BigDecimal amount, Byte repayMentType) {
        LocalDateTime time = LocalDateTime.now();
        UserTransactionRepayExample repayExample = new UserTransactionRepayExample();
        repayExample.createCriteria()
                .andBidIdEqualTo(id)
                .andAmountEqualTo(amount)
                .andRepaymentTypeEqualTo(repayMentType)
                .andCreateTimeGreaterThanOrEqualTo(time)
                .andVaExpireTimeLessThan(time)
                .example().orderBy(UserTransactionRepay.Column.createTime.desc()).limit(1);
        return repayMapper.selectOneByExample(repayExample);
    }

    /**
     * 根绝交易订单号查询va码记录
     *
     * @param paymentId 订单号
     * @return va码信息
     */
    public UserTransactionRepay findReayInfoByPayMentId(String paymentId) {
        UserTransactionRepayExample repayExample = new UserTransactionRepayExample();
        repayExample.createCriteria()
                .andPaymentIdEqualTo(paymentId);
        return repayMapper.selectOneByExample(repayExample);
    }

    /**
     * 根据交易id 金额 还款类型 交易方式 获取va码
     */
    public UserTransactionRepay getVaCodeByParmers(Long bidId, BigDecimal amount, byte repayMentType, String mode) {
        LocalDateTime time = LocalDateTime.now();
        UserTransactionRepayExample repayExample = new UserTransactionRepayExample();
        repayExample.createCriteria()
                .andBidIdEqualTo(bidId)
                .andAmountEqualTo(amount)
                .andRepaymentTypeEqualTo(repayMentType)
                .andModeEqualTo(mode)
                .andCreateTimeGreaterThanOrEqualTo(time)
                .andVaExpireTimeLessThan(time)
                .example().orderBy(UserTransactionRepay.Column.createTime.desc()).limit(1);
        return repayMapper.selectOneByExample(repayExample);
    }

    /**
     * 修改va码信息
     *
     * @param repayUpdate va码对象
     */
    public void updateUserTransactionRepay(UserTransactionRepay repayUpdate) {
        UserTransactionRepayExample repayExample = new UserTransactionRepayExample();
        repayExample.createCriteria().andPaymentIdEqualTo(repayUpdate.getPaymentId());
        repayMapper.updateByExample(repayUpdate, repayExample);
    }
}
