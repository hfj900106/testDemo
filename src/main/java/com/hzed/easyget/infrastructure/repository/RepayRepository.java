package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.persistence.auto.entity.*;
import com.hzed.easyget.persistence.auto.entity.example.BillExample;
import com.hzed.easyget.persistence.auto.entity.example.UserTransactionRepayExample;
import com.hzed.easyget.persistence.auto.mapper.*;
import com.hzed.easyget.persistence.ext.mapper.RepayExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**还款 仓储类
*@description：
*@author：[zhangruilin]
*@time：2018/6/15-15:49
**/
@Repository
public class RepayRepository {
    @Autowired
    private RepayExtMapper repayExtMapper;
    @Autowired
    private UserTransactionRepayMapper repayMapper;
    @Autowired
    private UserTransactionPicMapper picMapper;
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
        BillExample billExample=new BillExample();
        billExample.createCriteria().andBidIdEqualTo(bidId);
        return billMapper.selectOneByExample(billExample).getRepaymentTime();
    }

    /**
     * 获取va码
     */
    public TransactionVAResponse findVaTranc(Long payId,String mode) {
        return repayExtMapper.findVaTranc(payId,mode);
    }

    /**
     * 获取交易记录 根据交易id
     */
    public PaymentCodeRequest finduserTransBypaymentId(Long payId) {
        return repayExtMapper.finduserTransBypaymentId(payId);
    }

    /**
     * 生成va码记录
     */
    public void insertSelective(UserTransactionRepay repay) {
        repayMapper.insertSelective(repay);
    }


    /**
     * 获取所有的va码记录
     */
    public List<UserTransactionRepay> finaAllVAcodeBypermas(RepaymentRequest request) {
        UserTransactionRepayExample repayExample=new UserTransactionRepayExample();
        repayExample.createCriteria()
                .andTransactionIdEqualTo(request.getPayId())
                .andModeEqualTo(request.getMode());
        return repayMapper.selectByExample(repayExample);
    }

    /**
     * 还款完成走信息流
     */
    @Transactional(rollbackFor = Exception.class)
    public void afterRepayment(UserTransaction transaction ,RepayInfoFlowJob repayInfoFlowJob) {
        //修改交易记录
        userTransactionMapper.updateByPrimaryKeySelective(transaction);
        //插入还款定时任务
        repayInfoFlowJobMapper.insertSelective(repayInfoFlowJob);

    }

    /**
     * 修改交易信息
     */
    public void updateConfirmTime(UserTransaction userTransaction) {
        userTransactionMapper.updateByPrimaryKeySelective(userTransaction);
    }

    /**
     * 根绝交易id获取va码
     */
    public LoanManagResponse getVACode(Long id) {
        return repayExtMapper.getVACode(id);
    }

    /**
     * 保存凭证图片
     */
    public void picinsertSelective(UserTransactionPic repayPic) {
        picMapper.insertSelective(repayPic);
    }
}
