package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.UserStatus;
import com.hzed.easyget.persistence.auto.entity.example.UserStatusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserStatusMapper {
    long countByExample(UserStatusExample example);

    int deleteByExample(UserStatusExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserStatus record);

    int insertSelective(UserStatus record);

    List<UserStatus> selectByExample(UserStatusExample example);

    List<UserStatus> selectByExampleSelective(@Param("example") UserStatusExample example, @Param("selective") UserStatus.Column ... selective);

    UserStatus selectByPrimaryKey(Long id);

    UserStatus selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UserStatus.Column ... selective);

    int updateByExampleSelective(@Param("record") UserStatus record, @Param("example") UserStatusExample example);

    int updateByExample(@Param("record") UserStatus record, @Param("example") UserStatusExample example);

    int updateByPrimaryKeySelective(UserStatus record);

    int updateByPrimaryKey(UserStatus record);

    UserStatus selectOneByExample(UserStatusExample example);

    UserStatus selectOneByExampleSelective(@Param("example") UserStatusExample example, @Param("selective") UserStatus.Column ... selective);

    int batchInsert(@Param("list") List<UserStatus> list);

    int batchInsertSelective(@Param("list") List<UserStatus> list, @Param("selective") UserStatus.Column ... selective);
}