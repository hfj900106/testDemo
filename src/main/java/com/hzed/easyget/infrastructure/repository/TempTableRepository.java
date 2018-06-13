package com.hzed.easyget.infrastructure.repository;


import com.hzed.easyget.persistence.auto.entity.*;
import com.hzed.easyget.persistence.auto.entity.example.TempTableExample;
import com.hzed.easyget.persistence.auto.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hfj
 * @date 2018/6/7
 */
@Repository
public class TempTableRepository {
    @Autowired
    TempTableMapper tempMapper;
    @Autowired
    private BidProgressMapper bidProgressMapper;
    @Autowired
    private BidMapper bidMapper;
    @Autowired
    private BillMapper billMapper;
    @Autowired
    private BillLedgerMapper billLedgerMapper;
    @Autowired
    private UserTransactionMapper transactionMapper;

    public void insertJob(TempTable tempTable) {
        tempMapper.insert(tempTable);
    }

    @Transactional(rollbackFor = Exception.class)
    public void pushBidCallback( Bid bid,BidProgress bidProgress,Long bidId  ) {
        bidMapper.updateByPrimaryKeySelective(bid);
        bidProgressMapper.insert(bidProgress);
        TempTableExample tableExample  = new TempTableExample();
        tableExample.createCriteria().andRelaseIdEqualTo(bidId).andJobNameEqualTo("bankLoan");
        tempMapper.deleteByExample(tableExample);
    }



    public void upDateTemp(TempTable tempTable) {
        tempMapper.updateByPrimaryKeySelective(tempTable);
    }

    @Transactional(rollbackFor = Exception.class)
    public void afterBankLoan(Bid bid , BidProgress bidProgress,Bill bill, List<BillLedger> billLedgers,Long  tempId,UserTransaction transaction) {
        bidMapper.updateByPrimaryKeySelective(bid);
        bidProgressMapper.insertSelective(bidProgress);
        billMapper.insertSelective(bill);
        //TODO
        billLedgerMapper.batchInsertSelective(billLedgers);
        tempMapper.deleteByPrimaryKey(tempId);
        transactionMapper.insertSelective(transaction);
    }
}
