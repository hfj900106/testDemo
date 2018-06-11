package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.persistence.auto.entity.Bid;
import com.hzed.easyget.persistence.auto.entity.example.BidExample;
import com.hzed.easyget.persistence.auto.mapper.BidMapper;
import com.hzed.easyget.persistence.ext.entity.BidExt;
import com.hzed.easyget.persistence.ext.mapper.BidExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author wuchengwu
 * @data 2018/5/25
 */
@Repository
public class BidRepository {

    @Autowired
    private BidMapper bidMapper;
    @Autowired
    private BidExtMapper bidExtMapper;

    public List<Bid> findByUserIdAndStatus(Long userId, List<Byte> statuses) {
        BidExample example = new BidExample();
        example.createCriteria().andUserIdEqualTo(userId).andStatusIn(statuses);
        return bidMapper.selectByExample(example);
    }


    public Bid findById(Long id) {
        return bidMapper.selectByPrimaryKey(id);
    }

    public Bid findByIdWithExp(Long id) {
        Bid bid = findById(id);
        if (bid == null) {
            throw new ComBizException(BizCodeEnum.ILLEGAL_PARAM, "标ID：" + id + " 不存在");
        }
        return bid;
    }

    public void update(Bid bid) {
        bidMapper.updateByPrimaryKeySelective(bid);
    }

    public int save(Bid bid) {
        return bidMapper.insertSelective(bid);
    }

    public List<BidExt> gitBidsToPush(Map<String,Object> map){
        return bidExtMapper.selectBidsToPush(map);
    }
}