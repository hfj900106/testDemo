package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.AuthContent;
import com.hzed.easyget.persistence.auto.entity.example.AuthContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthContentMapper {
    long countByExample(AuthContentExample example);

    int deleteByExample(AuthContentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AuthContent record);

    int insertSelective(AuthContent record);

    List<AuthContent> selectByExampleWithBLOBs(AuthContentExample example);

    List<AuthContent> selectByExample(AuthContentExample example);

    List<AuthContent> selectByExampleSelective(@Param("example") AuthContentExample example, @Param("selective") AuthContent.Column ... selective);

    AuthContent selectByPrimaryKey(Long id);

    AuthContent selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") AuthContent.Column ... selective);

    int updateByExampleSelective(@Param("record") AuthContent record, @Param("example") AuthContentExample example);

    int updateByExampleWithBLOBs(@Param("record") AuthContent record, @Param("example") AuthContentExample example);

    int updateByExample(@Param("record") AuthContent record, @Param("example") AuthContentExample example);

    int updateByPrimaryKeySelective(AuthContent record);

    int updateByPrimaryKeyWithBLOBs(AuthContent record);

    int updateByPrimaryKey(AuthContent record);

    AuthContent selectOneByExample(AuthContentExample example);

    AuthContent selectOneByExampleSelective(@Param("example") AuthContentExample example, @Param("selective") AuthContent.Column ... selective);

    AuthContent selectOneByExampleWithBLOBs(AuthContentExample example);

    int batchInsert(@Param("list") List<AuthContent> list);

    int batchInsertSelective(@Param("list") List<AuthContent> list, @Param("selective") AuthContent.Column ... selective);
}