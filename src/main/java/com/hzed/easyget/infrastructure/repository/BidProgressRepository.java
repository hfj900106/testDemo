package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.BidProgress;
import com.hzed.easyget.persistence.auto.entity.example.BidProgressExample;
import com.hzed.easyget.persistence.auto.mapper.BidProgressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author wuchengwu
 * @data 2018/6/4
 */

@Repository
public class BidProgressRepository {

    @Autowired
    private BidProgressMapper bidProgressMapper;

    public BidProgress findByBidId(Long bidId, Byte type) {
        BidProgressExample example = new BidProgressExample();
        example.createCriteria().andBidIdEqualTo(bidId).andTypeEqualTo(type);

        return bidProgressMapper.selectOneByExample(example);
    }

    public void insert(BidProgress bidProgress) {
        bidProgressMapper.insertSelective(bidProgress);
    }

    public void update(BidProgress bidProgress) {
        bidProgressMapper.updateByPrimaryKeySelective(bidProgress);
    }
}