package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.LoanBidProgress;
import com.hzed.easyget.persistence.auto.entity.example.LoanBidProgressExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoanBidProgressMapper {
    long countByExample(LoanBidProgressExample example);

    int deleteByExample(LoanBidProgressExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LoanBidProgress record);

    int insertSelective(LoanBidProgress record);

    List<LoanBidProgress> selectByExample(LoanBidProgressExample example);

    List<LoanBidProgress> selectByExampleSelective(@Param("example") LoanBidProgressExample example, @Param("selective") LoanBidProgress.Column ... selective);

    LoanBidProgress selectByPrimaryKey(Long id);

    LoanBidProgress selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") LoanBidProgress.Column ... selective);

    int updateByExampleSelective(@Param("record") LoanBidProgress record, @Param("example") LoanBidProgressExample example);

    int updateByExample(@Param("record") LoanBidProgress record, @Param("example") LoanBidProgressExample example);

    int updateByPrimaryKeySelective(LoanBidProgress record);

    int updateByPrimaryKey(LoanBidProgress record);

    LoanBidProgress selectOneByExample(LoanBidProgressExample example);

    LoanBidProgress selectOneByExampleSelective(@Param("example") LoanBidProgressExample example, @Param("selective") LoanBidProgress.Column ... selective);

    int batchInsert(@Param("list") List<LoanBidProgress> list);

    int batchInsertSelective(@Param("list") List<LoanBidProgress> list, @Param("selective") LoanBidProgress.Column ... selective);
}