package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.BidProgress;
import com.hzed.easyget.persistence.auto.entity.example.BidProgressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BidProgressMapper {
    long countByExample(BidProgressExample example);

    int deleteByExample(BidProgressExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BidProgress record);

    int insertSelective(BidProgress record);

    List<BidProgress> selectByExample(BidProgressExample example);

    List<BidProgress> selectByExampleSelective(@Param("example") BidProgressExample example, @Param("selective") BidProgress.Column ... selective);

    BidProgress selectByPrimaryKey(Long id);

    BidProgress selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") BidProgress.Column ... selective);

    int updateByExampleSelective(@Param("record") BidProgress record, @Param("example") BidProgressExample example);

    int updateByExample(@Param("record") BidProgress record, @Param("example") BidProgressExample example);

    int updateByPrimaryKeySelective(BidProgress record);

    int updateByPrimaryKey(BidProgress record);

    BidProgress selectOneByExample(BidProgressExample example);

    BidProgress selectOneByExampleSelective(@Param("example") BidProgressExample example, @Param("selective") BidProgress.Column ... selective);

    int batchInsert(@Param("list") List<BidProgress> list);

    int batchInsertSelective(@Param("list") List<BidProgress> list, @Param("selective") BidProgress.Column ... selective);
}