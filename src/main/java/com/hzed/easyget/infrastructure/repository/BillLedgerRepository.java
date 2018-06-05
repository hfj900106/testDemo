package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.BillLedger;
import com.hzed.easyget.persistence.auto.entity.example.BillLedgerExample;
import com.hzed.easyget.persistence.auto.mapper.BillLedgerMapper;
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
public class BillLedgerRepository {

    @Autowired
    private BillLedgerMapper loanBillLedgerMapper;

    public BillLedger findLoanBillLedger(Long billId, String type) {
        BillLedgerExample example = new BillLedgerExample();
        example.createCriteria().andBillIdEqualTo(billId).andRepaymentItemEqualTo((byte)Integer.parseInt(type));
        return loanBillLedgerMapper.selectOneByExample(example);
    }

    public List<BillLedger> findTotalAmount(Long billId) {
        BillLedgerExample example = new BillLedgerExample();
        example.createCriteria().andBillIdEqualTo(billId);
        return loanBillLedgerMapper.selectByExample(example);
    }
}