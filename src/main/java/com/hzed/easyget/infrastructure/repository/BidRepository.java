package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.Bid;
import com.hzed.easyget.persistence.auto.entity.example.BidExample;
import com.hzed.easyget.persistence.auto.mapper.BidMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author wuchengwu
 * @data 2018/5/25
 */
@Repository
public class BidRepository {

    @Autowired
    private BidMapper bidMapper;

    public List<Bid> findBStatusByUserId(Long userId, List<Byte> statuses) {
        BidExample example = new BidExample();
        example.createCriteria().andUserIdEqualTo(userId).andStatusIn(statuses);
        return bidMapper.selectByExample(example);
    }
}