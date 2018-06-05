package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.AuthItem;
import com.hzed.easyget.persistence.auto.entity.example.AuthItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthItemMapper {
    long countByExample(AuthItemExample example);

    int deleteByExample(AuthItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AuthItem record);

    int insertSelective(AuthItem record);

    List<AuthItem> selectByExample(AuthItemExample example);

    List<AuthItem> selectByExampleSelective(@Param("example") AuthItemExample example, @Param("selective") AuthItem.Column ... selective);

    AuthItem selectByPrimaryKey(Long id);

    AuthItem selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") AuthItem.Column ... selective);

    int updateByExampleSelective(@Param("record") AuthItem record, @Param("example") AuthItemExample example);

    int updateByExample(@Param("record") AuthItem record, @Param("example") AuthItemExample example);

    int updateByPrimaryKeySelective(AuthItem record);

    int updateByPrimaryKey(AuthItem record);

    AuthItem selectOneByExample(AuthItemExample example);

    AuthItem selectOneByExampleSelective(@Param("example") AuthItemExample example, @Param("selective") AuthItem.Column ... selective);

    int batchInsert(@Param("list") List<AuthItem> list);

    int batchInsertSelective(@Param("list") List<AuthItem> list, @Param("selective") AuthItem.Column ... selective);
}