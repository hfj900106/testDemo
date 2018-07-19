package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.controller.model.LoanTransactionRequest;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.persistence.auto.entity.Bid;
import com.hzed.easyget.persistence.auto.entity.UserBank;
import com.hzed.easyget.persistence.auto.entity.UserTransaction;
import com.hzed.easyget.persistence.auto.entity.example.BidExample;
import com.hzed.easyget.persistence.auto.entity.example.UserTransactionExample;
import com.hzed.easyget.persistence.auto.mapper.BidMapper;
import com.hzed.easyget.persistence.auto.mapper.UserBankMapper;
import com.hzed.easyget.persistence.auto.mapper.UserTransactionMapper;
import com.hzed.easyget.persistence.ext.entity.BidExt;
import com.hzed.easyget.persistence.ext.mapper.BidExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wuchengwu
 * @data 2018/5/25
 */
@Repository
public class BidRepository {

    @Autowired
    private BidMapper bidMapper;
    @Autowired
    private BidExtMapper bidExtMapper;
    @Autowired
    private UserBankMapper userBankMapper;
    @Autowired
    private UserTransactionMapper userTransactionMapper;

    public List<Bid> findByUserIdAndStatus(Long userId, List<Byte> statuses) {
        BidExample example = new BidExample();
        example.createCriteria().andUserIdEqualTo(userId).andStatusIn(statuses);
        return bidMapper.selectByExample(example);
    }

    public List<Bid> findPageByUserIdAndStatus(Long userId, List<Byte> statuses, Integer pageNo, Integer pageSize) {
        BidExample example = new BidExample();
        example.createCriteria().andUserIdEqualTo(userId).andStatusIn(statuses);
        example.setOrderByClause(Bid.Column.createTime.desc());
        example.page(pageNo, pageSize);
        return bidMapper.selectByExample(example);
    }


    public Bid findById(Long id) {
        return bidMapper.selectByPrimaryKey(id);
    }

    public Bid findByIdWithExp(Long id) {
        Bid bid = findById(id);
        if (bid == null) {
            throw new ComBizException(BizCodeEnum.ILLEGAL_BIDID, new Object[]{String.valueOf(id)});
        }
        return bid;
    }

    public void update(Bid bid) {
        bidMapper.updateByPrimaryKeySelective(bid);
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertBidAndUserBank(Bid bid, UserBank userBank) {
        bidMapper.insertSelective(bid);
        userBankMapper.insertSelective(userBank);
    }

    public List<BidExt> selectBidsToPushOrBankLoan(Byte status, Byte times, String jobName) {
        return bidExtMapper.selectBidsToPushOrBankLoan(status, times, jobName);
    }

    public List<Bid> findByUserId(Long userId) {
        BidExample example = new BidExample();
        example.createCriteria().andUserIdEqualTo(userId);
        example.setOrderByClause(Bid.Column.createTime.desc());
        return bidMapper.selectByExample(example);
    }

    public Bid findOneByUserId(Long userId) {
        BidExample example = new BidExample();
        example.createCriteria().andUserIdEqualTo(userId);
        example.setOrderByClause(Bid.Column.createTime.desc());
        return bidMapper.selectOneByExample(example);
    }

    /**
     * 放款信息查询
     *
     * @param bidId
     * @return
     */
    public LoanTransactionRequest findLoanTransaction(Long bidId) {
        return bidExtMapper.findLoanTransaction(bidId);
    }

    public void updateUserTranStatus(UserTransaction userTransaction) {
        UserTransactionExample transaction = new UserTransactionExample();
        transaction.createCriteria().andPaymentIdEqualTo(userTransaction.getPaymentId());
        userTransactionMapper.updateByExampleSelective(userTransaction, transaction);
    }

    public Bid getUserIdByBidId(Long bidId) {
        return bidMapper.selectByPrimaryKey(bidId);
    }

}