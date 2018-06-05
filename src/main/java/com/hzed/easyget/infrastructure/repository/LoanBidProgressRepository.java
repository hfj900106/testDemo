package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.BidProgress;
import com.hzed.easyget.persistence.auto.entity.example.BidProgressExample;
import com.hzed.easyget.persistence.auto.mapper.BidProgressMapper;
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
    private BidProgressMapper loanBidProgressMapper;

    public BidProgress findHandleTimeByBidAndType(Long bid, String type) {
        BidProgressExample example = new BidProgressExample();
        example.createCriteria().andBidIdEqualTo(bid).andTypeEqualTo(Byte.valueOf(type));

        return loanBidProgressMapper.selectOneByExample(example);
    }
}