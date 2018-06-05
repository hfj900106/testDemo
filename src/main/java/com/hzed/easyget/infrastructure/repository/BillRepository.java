package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.Bill;
import com.hzed.easyget.persistence.auto.entity.example.BillExample;
import com.hzed.easyget.persistence.auto.mapper.BillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 账单表仓储层
 *
 * @author wuchengwu
 * @data 2018/6/5
 */
@Repository
public class BillRepository {

    @Autowired
    private BillMapper loanBillMapper;

    public Bill findRepayTimeByBid(Long bid) {
        BillExample example = new BillExample();
        example.createCriteria().andBidIdEqualTo(bid);
        return loanBillMapper.selectOneByExample(example);

    }
}