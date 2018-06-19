package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.IDArea;
import com.hzed.easyget.persistence.auto.entity.example.IDAreaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IDAreaMapper {
    long countByExample(IDAreaExample example);

    int deleteByExample(IDAreaExample example);

    int deleteByPrimaryKey(Long id);

    int insert(IDArea record);

    int insertSelective(IDArea record);

    List<IDArea> selectByExample(IDAreaExample example);

    List<IDArea> selectByExampleSelective(@Param("example") IDAreaExample example, @Param("selective") IDArea.Column ... selective);

    IDArea selectByPrimaryKey(Long id);

    IDArea selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") IDArea.Column ... selective);

    int updateByExampleSelective(@Param("record") IDArea record, @Param("example") IDAreaExample example);

    int updateByExample(@Param("record") IDArea record, @Param("example") IDAreaExample example);

    int updateByPrimaryKeySelective(IDArea record);

    int updateByPrimaryKey(IDArea record);

    IDArea selectOneByExample(IDAreaExample example);

    IDArea selectOneByExampleSelective(@Param("example") IDAreaExample example, @Param("selective") IDArea.Column ... selective);

    int batchInsert(@Param("list") List<IDArea> list);

    int batchInsertSelective(@Param("list") List<IDArea> list, @Param("selective") IDArea.Column ... selective);
}