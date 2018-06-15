package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.controller.model.LoanTransactionRequest;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.persistence.auto.entity.Bid;
import com.hzed.easyget.persistence.auto.entity.UserBank;
import com.hzed.easyget.persistence.auto.entity.UserTransaction;
import com.hzed.easyget.persistence.auto.entity.example.BidExample;
import com.hzed.easyget.persistence.auto.mapper.BidMapper;
import com.hzed.easyget.persistence.auto.mapper.UserBankMapper;
import com.hzed.easyget.persistence.ext.entity.BidExt;
import com.hzed.easyget.persistence.ext.mapper.BidExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    public List<Bid> findByUserIdAndStatus(Long userId, List<Byte> statuses) {
        BidExample example = new BidExample();
        example.createCriteria().andUserIdEqualTo(userId).andStatusIn(statuses);
        return bidMapper.selectByExample(example);
    }


    public Bid findById(Long id) {
        return bidMapper.selectByPrimaryKey(id);
    }

    public Bid findByIdWithExp(Long id) {
        Bid bid = findById(id);
        if (bid == null) {
            throw new ComBizException(BizCodeEnum.ILLEGAL_BIDID,id);
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

    public List<BidExt> gitBidsToPush(){
        return bidExtMapper.selectBidsToPush();
    }
    public List<BidExt> findBankLoanBids() {
        return bidExtMapper.findBankLoanBids();
    }

    public List<Bid> findByUserId(Long userId) {
        BidExample example = new BidExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return bidMapper.selectByExample(example);
    }

    /**
     * 放款信息查询
     * @param bidId
     * @return
     */
    public LoanTransactionRequest findLoanTransaction(Long bidId) {
        return bidExtMapper.findLoanTransaction(bidId);
    }

    public void updateUserTranState(String t_id, byte b) {
        UserTransaction userTransaction=UserTransaction.builder().paymentId(t_id).status(b).updateTime(LocalDateTime.now()).build();
        bidExtMapper.updateUserTranceOverstate(userTransaction);
    }
}