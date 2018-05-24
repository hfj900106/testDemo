package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.PersonInfo;
import com.hzed.easyget.persistence.auto.entity.example.PersonInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PersonInfoMapper {
    long countByExample(PersonInfoExample example);

    int deleteByExample(PersonInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PersonInfo record);

    int insertSelective(PersonInfo record);

    List<PersonInfo> selectByExample(PersonInfoExample example);

    List<PersonInfo> selectByExampleSelective(@Param("example") PersonInfoExample example, @Param("selective") PersonInfo.Column ... selective);

    PersonInfo selectByPrimaryKey(Long id);

    PersonInfo selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") PersonInfo.Column ... selective);

    int updateByExampleSelective(@Param("record") PersonInfo record, @Param("example") PersonInfoExample example);

    int updateByExample(@Param("record") PersonInfo record, @Param("example") PersonInfoExample example);

    int updateByPrimaryKeySelective(PersonInfo record);

    int updateByPrimaryKey(PersonInfo record);

    PersonInfo selectOneByExample(PersonInfoExample example);

    PersonInfo selectOneByExampleSelective(@Param("example") PersonInfoExample example, @Param("selective") PersonInfo.Column ... selective);

    int batchInsert(@Param("list") List<PersonInfo> list);

    int batchInsertSelective(@Param("list") List<PersonInfo> list, @Param("selective") PersonInfo.Column ... selective);
}