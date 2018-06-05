package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.Professional;
import com.hzed.easyget.persistence.auto.entity.example.ProfessionalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProfessionalMapper {
    long countByExample(ProfessionalExample example);

    int deleteByExample(ProfessionalExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Professional record);

    int insertSelective(Professional record);

    List<Professional> selectByExample(ProfessionalExample example);

    List<Professional> selectByExampleSelective(@Param("example") ProfessionalExample example, @Param("selective") Professional.Column ... selective);

    Professional selectByPrimaryKey(Long id);

    Professional selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Professional.Column ... selective);

    int updateByExampleSelective(@Param("record") Professional record, @Param("example") ProfessionalExample example);

    int updateByExample(@Param("record") Professional record, @Param("example") ProfessionalExample example);

    int updateByPrimaryKeySelective(Professional record);

    int updateByPrimaryKey(Professional record);

    Professional selectOneByExample(ProfessionalExample example);

    Professional selectOneByExampleSelective(@Param("example") ProfessionalExample example, @Param("selective") Professional.Column ... selective);

    int batchInsert(@Param("list") List<Professional> list);

    int batchInsertSelective(@Param("list") List<Professional> list, @Param("selective") Professional.Column ... selective);
}