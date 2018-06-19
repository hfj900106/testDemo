package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.application.enums.TransactionRepayEnum;
import com.hzed.easyget.application.enums.TransactionTypeEnum;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.*;
import com.hzed.easyget.persistence.auto.entity.example.UserTransactionExample;
import com.hzed.easyget.persistence.auto.entity.example.UserTransactionRepayExample;
import com.hzed.easyget.persistence.auto.mapper.UserPicMapper;
import com.hzed.easyget.persistence.auto.mapper.UserTransactionMapper;
import com.hzed.easyget.persistence.auto.mapper.UserTransactionRepayMapper;
import com.hzed.easyget.persistence.auto.mapper.UserTransactionRepayPicMapper;
import com.hzed.easyget.persistence.ext.mapper.RepayExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
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
    private UserTransactionRepository userTransactionRepository;
    @Autowired
    private UserTransactionMapper userTransactionMapper;
    @Autowired
    private BidRepository bidRepository;
    @Autowired
    private UserTransactionRepayMapper repayMapper;
    @Autowired
    private UserPicMapper userPicMapper;
    @Autowired
    private UserTransactionRepayPicMapper transactionRepayPicMapper;
    /**
     * 还款详情
     * @param bidId
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    public LoanManagResponse findloanManagResponse(BigDecimal amount, Long bidId, boolean flag) {
        LoanManagResponse managResponse=repayExtMapper.findloanManagResponse(bidId);
        //查询标的信息
        Bid bid=bidRepository.findById(bidId);
        //先查询是否有交易记录
        UserTransaction trance;
        trance=userTransactionRepository.findoldTrance(bidId,flag,TransactionTypeEnum.OUT.getCode().byteValue(),TransactionTypeEnum.IN_RANSACTION.getCode().byteValue());
        if(ObjectUtils.isEmpty(trance)){
            //生成交易记录
            trance=UserTransaction.builder()
                    .id(IdentifierGenerator.nextId())
                    .userId(bid.getUserId())
                    .paymentId(IdentifierGenerator.nextSeqNo())
                    .bank(bid.getInBank())
                    .account(bid.getInAccount())
                    .type(TransactionTypeEnum.OUT.getCode().byteValue())
                    .amount(amount)
                    .bidId(bidId)
                    .status(TransactionTypeEnum.IN_RANSACTION.getCode().byteValue())
                    .repaymentType(flag?TransactionTypeEnum.ALL_CLEAR.getCode().byteValue():TransactionTypeEnum.PARTIAL_CLEARANCE.getCode().byteValue())
                    .createTime(LocalDateTime.now())
                    .remark(flag?"全部还款":"部分还款").build();
            userTransactionRepository.insertSelective(trance);
        }else {
            trance.setAmount(amount);
            trance.setCreateTime(LocalDateTime.now());
            trance.setRemark(flag?"全部还款":"部分还款");
            userTransactionRepository.transactionUpdateByKey(trance);
        }
        //查询va码
        LoanManagResponse vaCode=repayExtMapper.getVACode(trance.getId());
        if(!ObjectUtils.isEmpty(vaCode)){
            managResponse.setVaCodel(vaCode.getVaCodel());
            managResponse.setCreateTime(vaCode.getCreateTime());
        }
        managResponse.setPayId(trance.getId());
        return  managResponse;
    }

    /**
     * 获取va码
     * @param request
     * @return
     */
    public TransactionVAResponse findVATranc(TransactionVARequest request) {
        return repayExtMapper.findVATranc(request);
    }

    /**
     * 获取交易记录 根据交易id
     * @param payId
     * @return
     */
    public PaymentCodeRequest finduserTransBypaymentId(Long payId) {
        return repayExtMapper.finduserTransBypaymentId(payId);
    }

    /**
     * 生成va码记录
     * @param repay
     */
    public void insertSelective(UserTransactionRepay repay) {
        repayMapper.insertSelective(repay);
    }

    /**
     * 生成凭证pic记录
     * @param userPic
     */
    public void userPicinsertSelective(UserPic userPic) {
        userPicMapper.insertSelective(userPic);
    }

    /**
     * 获取所有的va码记录
     * @param request
     * @return
     */
    public List<UserTransactionRepay> finaAllVAcodeBypermas(RepaymentRequest request) {
        return  userTransactionRepository.finaAllVAcodeBypermas(request);
    }

    /**
     * va码应的凭证
     */
    public void picinsertSelective(UserTransactionRepayPic repayPic) {
        transactionRepayPicMapper.insertSelective(repayPic);
    }

    /**
     * 修改va码状态 处理中
     * @param x
     */
    public void updateUserTransacRepayState(UserTransactionRepay x) {
        x.setStatus(TransactionRepayEnum.PROCESS_PROCESSING.getCode().byteValue());
        userTransactionRepository.updateUserTransacRepayState(x);
    }

    /**
     * 还款完成走资金流
     */
    @Transactional(rollbackFor = Exception.class)
    public void afterRepayment(UserTransaction transaction, UserTransactionRepay x) {
        transaction.setStatus(TransactionTypeEnum.SUCCESS_RANSACTION.getCode().byteValue());
        transaction.setUpdateTime(LocalDateTime.now());
        userTransactionRepository.transactionUpdateByKey(transaction);
        x.setStatus(TransactionRepayEnum.PROCESS_SUCCESS.getCode().byteValue());
        x.setConfirmTime(LocalDateTime.now());
        repayMapper.updateByPrimaryKey(x);
    }

    /**
     * 根据交易id获取对应的va码信息
     * @param id
     * @return
     */
    public UserTransactionRepay findRepayTrans(Long id) {
        UserTransactionRepayExample repayExample=new UserTransactionRepayExample();
        repayExample.createCriteria()
                .andTransactionIdEqualTo(id)
                .andStatusEqualTo(TransactionRepayEnum.PROCESS_PROCESSING.getCode().byteValue());
        return repayMapper.selectByExample(repayExample).get(0);
    }

    /**
     * 修改对应va码记录状态
     * @param t_id
     * @param b
     */
    public void updateUserepyTranState(String t_id, byte b) {
        UserTransactionExample transactionExample=new UserTransactionExample();
        transactionExample.createCriteria()
                .andPaymentIdEqualTo(t_id)
                .andTypeEqualTo(TransactionTypeEnum.OUT.getCode().byteValue());
        UserTransaction transaction=userTransactionMapper.selectOneByExample(transactionExample);
        repayExtMapper.updateByPaymenid(transaction.getId(),b);
    }
}
