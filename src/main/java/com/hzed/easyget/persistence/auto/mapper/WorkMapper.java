package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.Work;
import com.hzed.easyget.persistence.auto.entity.example.WorkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WorkMapper {
    long countByExample(WorkExample example);

    int deleteByExample(WorkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Work record);

    int insertSelective(Work record);

    List<Work> selectByExample(WorkExample example);

    List<Work> selectByExampleSelective(@Param("example") WorkExample example, @Param("selective") Work.Column ... selective);

    Work selectByPrimaryKey(Long id);

    Work selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Work.Column ... selective);

    int updateByExampleSelective(@Param("record") Work record, @Param("example") WorkExample example);

    int updateByExample(@Param("record") Work record, @Param("example") WorkExample example);

    int updateByPrimaryKeySelective(Work record);

    int updateByPrimaryKey(Work record);

    Work selectOneByExample(WorkExample example);

    Work selectOneByExampleSelective(@Param("example") WorkExample example, @Param("selective") Work.Column ... selective);

    int batchInsert(@Param("list") List<Work> list);

    int batchInsertSelective(@Param("list") List<Work> list, @Param("selective") Work.Column ... selective);
}