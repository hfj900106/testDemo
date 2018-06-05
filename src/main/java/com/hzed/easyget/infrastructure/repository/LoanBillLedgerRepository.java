package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.LoanBillLedger;
import com.hzed.easyget.persistence.auto.entity.example.LoanBillLedgerExample;
import com.hzed.easyget.persistence.auto.mapper.LoanBillLedgerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账单台账表
 *
 * @author wuchengwu
 * @data 2018/6/5
 */
@Repository
public class LoanBillLedgerRepository {

    @Autowired
    private LoanBillLedgerMapper loanBillLedgerMapper;

    public LoanBillLedger findLoanBillLedger(Long billId, String type) {
        LoanBillLedgerExample example = new LoanBillLedgerExample();
        example.createCriteria().andBillIdEqualTo(billId).andRepaymentItemEqualTo((byte)Integer.parseInt(type));
        return loanBillLedgerMapper.selectOneByExample(example);
    }

    public List<LoanBillLedger> findTotalAmount(Long billId) {
        LoanBillLedgerExample example = new LoanBillLedgerExample();
        example.createCriteria().andBillIdEqualTo(billId);
        return loanBillLedgerMapper.selectByExample(example);
    }
}