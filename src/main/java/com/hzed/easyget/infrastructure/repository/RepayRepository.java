package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.application.enums.TransactionTypeEnum;
import com.hzed.easyget.controller.model.LoanManagResponse;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.Bid;
import com.hzed.easyget.persistence.auto.entity.UserTransaction;
import com.hzed.easyget.persistence.auto.mapper.UserBankMapper;
import com.hzed.easyget.persistence.ext.mapper.RepayExtMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

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
    private BidRepository bidRepository;
    /**
     * 还款详情
     * @param bidId
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    public LoanManagResponse findloanManagResponse(Long bidId,boolean flag) {
        LoanManagResponse managResponse=repayExtMapper.findloanManagResponse(bidId);
        //查询标的信息
        Bid bid=bidRepository.findById(bidId);
        //生成交易记录
        UserTransaction transaction=UserTransaction.builder()
                .id(IdentifierGenerator.nextId())
                .userId(bid.getUserId())
                .paymentId(IdentifierGenerator.nextSeqNo())
                .bank(bid.getInBank())
                .type(TransactionTypeEnum.OUT.getCode().byteValue())
                .amount(managResponse.getAmount())
                .status(TransactionTypeEnum.IN_RANSACTION.getCode().byteValue())
                .repaymentType(flag?TransactionTypeEnum.ALL_CLEAR.getCode().byteValue():TransactionTypeEnum.PARTIAL_CLEARANCE.getCode().byteValue())
                .createTime(LocalDateTime.now())
                .remark("部分还款").build();
        userTransactionRepository.insertSelective(transaction);
        //查询va码
        LoanManagResponse vaCode=repayExtMapper.getVACode(transaction.getId());
        if(!ObjectUtils.isEmpty(vaCode)){
            managResponse.setVaCodel(vaCode.getVaCodel());
            managResponse.setExpiryTime(vaCode.getExpiryTime());
        }
        managResponse.setPayMentId(transaction.getPaymentId());
        return  managResponse;
    }
}
