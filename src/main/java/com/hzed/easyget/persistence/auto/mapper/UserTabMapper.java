package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.UserTab;
import com.hzed.easyget.persistence.auto.entity.example.UserTabExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserTabMapper {
    long countByExample(UserTabExample example);

    int deleteByExample(UserTabExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserTab record);

    int insertSelective(UserTab record);

    List<UserTab> selectByExample(UserTabExample example);

    List<UserTab> selectByExampleSelective(@Param("example") UserTabExample example, @Param("selective") UserTab.Column ... selective);

    UserTab selectByPrimaryKey(Long id);

    UserTab selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UserTab.Column ... selective);

    int updateByExampleSelective(@Param("record") UserTab record, @Param("example") UserTabExample example);

    int updateByExample(@Param("record") UserTab record, @Param("example") UserTabExample example);

    int updateByPrimaryKeySelective(UserTab record);

    int updateByPrimaryKey(UserTab record);

    UserTab selectOneByExample(UserTabExample example);

    UserTab selectOneByExampleSelective(@Param("example") UserTabExample example, @Param("selective") UserTab.Column ... selective);

    int batchInsert(@Param("list") List<UserTab> list);

    int batchInsertSelective(@Param("list") List<UserTab> list, @Param("selective") UserTab.Column ... selective);
}