package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.LoanBidProgress;
import com.hzed.easyget.persistence.auto.entity.example.LoanBidProgressExample;
import com.hzed.easyget.persistence.auto.mapper.LoanBidProgressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author wuchengwu
 * @data 2018/6/4
 */

@Repository
public class LoanBidProgressRepository {

    @Autowired
    private LoanBidProgressMapper loanBidProgressMapper;

    public LoanBidProgress findHandleTimeByBidAndType(Long bid, String type) {
        LoanBidProgressExample example = new LoanBidProgressExample();
        example.createCriteria().andBidIdEqualTo(bid).andTypeEqualTo(Byte.valueOf(type));

        return loanBidProgressMapper.selectOneByExample(example);
    }
}