package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.LoanBill;
import com.hzed.easyget.persistence.auto.entity.example.LoanBillExample;
import com.hzed.easyget.persistence.auto.mapper.LoanBillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 账单表仓储层
 *
 * @author wuchengwu
 * @data 2018/6/5
 */
@Repository
public class LoanBillRepository {

    @Autowired
    private LoanBillMapper loanBillMapper;

    public LoanBill findRepayTimeByBid(Long bid) {
        LoanBillExample example = new LoanBillExample();
        example.createCriteria().andBidIdEqualTo(bid);
        return loanBillMapper.selectOneByExample(example);

    }
}