package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.Auth;
import com.hzed.easyget.persistence.auto.entity.example.AuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthMapper {
    long countByExample(AuthExample example);

    int deleteByExample(AuthExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Auth record);

    int insertSelective(Auth record);

    List<Auth> selectByExample(AuthExample example);

    List<Auth> selectByExampleSelective(@Param("example") AuthExample example, @Param("selective") Auth.Column ... selective);

    Auth selectByPrimaryKey(Long id);

    Auth selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Auth.Column ... selective);

    int updateByExampleSelective(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByExample(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);

    Auth selectOneByExample(AuthExample example);

    Auth selectOneByExampleSelective(@Param("example") AuthExample example, @Param("selective") Auth.Column ... selective);

    int batchInsert(@Param("list") List<Auth> list);

    int batchInsertSelective(@Param("list") List<Auth> list, @Param("selective") Auth.Column ... selective);
}