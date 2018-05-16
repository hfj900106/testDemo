package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.SafekeepReport;
import com.hzed.easyget.persistence.auto.entity.example.SafekeepReportExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SafekeepReportMapper {
    long countByExample(SafekeepReportExample example);

    int deleteByExample(SafekeepReportExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SafekeepReport record);

    int insertSelective(SafekeepReport record);

    List<SafekeepReport> selectByExample(SafekeepReportExample example);

    List<SafekeepReport> selectByExampleSelective(@Param("example") SafekeepReportExample example, @Param("selective") SafekeepReport.Column ... selective);

    SafekeepReport selectByPrimaryKey(Long id);

    SafekeepReport selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") SafekeepReport.Column ... selective);

    int updateByExampleSelective(@Param("record") SafekeepReport record, @Param("example") SafekeepReportExample example);

    int updateByExample(@Param("record") SafekeepReport record, @Param("example") SafekeepReportExample example);

    int updateByPrimaryKeySelective(SafekeepReport record);

    int updateByPrimaryKey(SafekeepReport record);

    SafekeepReport selectOneByExample(SafekeepReportExample example);

    SafekeepReport selectOneByExampleSelective(@Param("example") SafekeepReportExample example, @Param("selective") SafekeepReport.Column ... selective);

    int batchInsert(@Param("list") List<SafekeepReport> list);

    int batchInsertSelective(@Param("list") List<SafekeepReport> list, @Param("selective") SafekeepReport.Column ... selective);
}