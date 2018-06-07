package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.TempTable;
import com.hzed.easyget.persistence.auto.entity.example.TempTableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TempTableMapper {
    long countByExample(TempTableExample example);

    int deleteByExample(TempTableExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TempTable record);

    int insertSelective(TempTable record);

    List<TempTable> selectByExample(TempTableExample example);

    List<TempTable> selectByExampleSelective(@Param("example") TempTableExample example, @Param("selective") TempTable.Column ... selective);

    TempTable selectByPrimaryKey(Long id);

    TempTable selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") TempTable.Column ... selective);

    int updateByExampleSelective(@Param("record") TempTable record, @Param("example") TempTableExample example);

    int updateByExample(@Param("record") TempTable record, @Param("example") TempTableExample example);

    int updateByPrimaryKeySelective(TempTable record);

    int updateByPrimaryKey(TempTable record);

    TempTable selectOneByExample(TempTableExample example);

    TempTable selectOneByExampleSelective(@Param("example") TempTableExample example, @Param("selective") TempTable.Column ... selective);

    int batchInsert(@Param("list") List<TempTable> list);

    int batchInsertSelective(@Param("list") List<TempTable> list, @Param("selective") TempTable.Column ... selective);
}