package com.hzed.easyget.infrastructure.repository;


import com.hzed.easyget.infrastructure.consts.ComConsts;
import com.hzed.easyget.persistence.auto.entity.*;
import com.hzed.easyget.persistence.auto.entity.example.TempTableExample;
import com.hzed.easyget.persistence.auto.entity.example.UserTransactionExample;
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
    @Autowired
    private TempTableMapper tempTableMapper;

    public void insertJob(TempTable tempTable) {
        tempMapper.insertSelective(tempTable);
    }

    @Transactional(rollbackFor = Exception.class)
    public void pushBidCallback(Bid bid, BidProgress bidProgress, Long bidId) {
        bidMapper.updateByPrimaryKeySelective(bid);
        bidProgressMapper.insertSelective(bidProgress);
        TempTableExample tableExample = new TempTableExample();
        tableExample.createCriteria().andRelaseIdEqualTo(bidId).andJobNameEqualTo(ComConsts.PUSH_RISK_TASK);
        tempMapper.deleteByExample(tableExample);
    }


    public void updateTemp(TempTable tempTable) {
        tempMapper.updateByPrimaryKeySelective(tempTable);
    }

    @Transactional(rollbackFor = Exception.class)
    public void afterBankLoan(Bid bid, BidProgress bidProgress, Bill bill, List<BillLedger> billLedgers, Long tempId, UserTransaction transaction, boolean flag) {
        bidMapper.updateByPrimaryKeySelective(bid);
        bidProgressMapper.insertSelective(bidProgress);
        billMapper.insertSelective(bill);
        billLedgerMapper.batchInsertSelective(billLedgers,BillLedger.Column.id,BillLedger.Column.billId,BillLedger.Column.repaymentTime,BillLedger.Column.createTime,
                BillLedger.Column.realRepaymentAmount,BillLedger.Column.repaymentItem);
        tempMapper.deleteByPrimaryKey(tempId);
        if (flag) {
            transactionMapper.insertSelective(transaction);
        } else {
            UserTransactionExample userTransaction = new UserTransactionExample();
            userTransaction.createCriteria().andPaymentIdEqualTo(transaction.getPaymentId());
            transactionMapper.updateByExampleSelective(transaction, userTransaction);
        }
    }

    public Long findTempTableByBidNoAndName(Long bidId, String pushBankTask) {
        TempTableExample tempTableExample = new TempTableExample();
        tempTableExample.createCriteria()
                .andRelaseIdEqualTo(bidId)
                .andJobNameEqualTo(pushBankTask);
        return tempTableMapper.selectOneByExample(tempTableExample).getId();
    }

    public TempTable findTempTableByBidNoAndJobName(Long bidId, String pushBankTask) {
        TempTableExample tempTableExample = new TempTableExample();
        tempTableExample.createCriteria()
                .andRelaseIdEqualTo(bidId)
                .andJobNameEqualTo(pushBankTask);
        return tempTableMapper.selectOneByExample(tempTableExample);
    }

    public void deleteById(Long id) {
        tempMapper.deleteByPrimaryKey(id);
    }
}
