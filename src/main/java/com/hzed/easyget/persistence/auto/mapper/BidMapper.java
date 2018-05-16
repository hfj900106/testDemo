package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.Bid;
import com.hzed.easyget.persistence.auto.entity.example.BidExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BidMapper {
    long countByExample(BidExample example);

    int deleteByExample(BidExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Bid record);

    int insertSelective(Bid record);

    List<Bid> selectByExampleWithBLOBs(BidExample example);

    List<Bid> selectByExample(BidExample example);

    List<Bid> selectByExampleSelective(@Param("example") BidExample example, @Param("selective") Bid.Column ... selective);

    Bid selectByPrimaryKey(Long id);

    Bid selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Bid.Column ... selective);

    int updateByExampleSelective(@Param("record") Bid record, @Param("example") BidExample example);

    int updateByExampleWithBLOBs(@Param("record") Bid record, @Param("example") BidExample example);

    int updateByExample(@Param("record") Bid record, @Param("example") BidExample example);

    int updateByPrimaryKeySelective(Bid record);

    int updateByPrimaryKeyWithBLOBs(Bid record);

    int updateByPrimaryKey(Bid record);

    Bid selectOneByExample(BidExample example);

    Bid selectOneByExampleSelective(@Param("example") BidExample example, @Param("selective") Bid.Column ... selective);

    Bid selectOneByExampleWithBLOBs(BidExample example);

    int batchInsert(@Param("list") List<Bid> list);

    int batchInsertSelective(@Param("list") List<Bid> list, @Param("selective") Bid.Column ... selective);
}