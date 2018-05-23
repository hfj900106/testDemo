package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.UserAuthStatus;
import com.hzed.easyget.persistence.auto.entity.example.UserAuthStatusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAuthStatusMapper {
    long countByExample(UserAuthStatusExample example);

    int deleteByExample(UserAuthStatusExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserAuthStatus record);

    int insertSelective(UserAuthStatus record);

    List<UserAuthStatus> selectByExample(UserAuthStatusExample example);

    List<UserAuthStatus> selectByExampleSelective(@Param("example") UserAuthStatusExample example, @Param("selective") UserAuthStatus.Column ... selective);

    UserAuthStatus selectByPrimaryKey(Long id);

    UserAuthStatus selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UserAuthStatus.Column ... selective);

    int updateByExampleSelective(@Param("record") UserAuthStatus record, @Param("example") UserAuthStatusExample example);

    int updateByExample(@Param("record") UserAuthStatus record, @Param("example") UserAuthStatusExample example);

    int updateByPrimaryKeySelective(UserAuthStatus record);

    int updateByPrimaryKey(UserAuthStatus record);

    UserAuthStatus selectOneByExample(UserAuthStatusExample example);

    UserAuthStatus selectOneByExampleSelective(@Param("example") UserAuthStatusExample example, @Param("selective") UserAuthStatus.Column ... selective);

    int batchInsert(@Param("list") List<UserAuthStatus> list);

    int batchInsertSelective(@Param("list") List<UserAuthStatus> list, @Param("selective") UserAuthStatus.Column ... selective);
}