package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.UserLogin;
import com.hzed.easyget.persistence.auto.entity.example.UserLoginExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserLoginMapper {
    long countByExample(UserLoginExample example);

    int deleteByExample(UserLoginExample example);

    int insert(UserLogin record);

    int insertSelective(UserLogin record);

    List<UserLogin> selectByExample(UserLoginExample example);

    List<UserLogin> selectByExampleSelective(@Param("example") UserLoginExample example, @Param("selective") UserLogin.Column ... selective);

    int updateByExampleSelective(@Param("record") UserLogin record, @Param("example") UserLoginExample example);

    int updateByExample(@Param("record") UserLogin record, @Param("example") UserLoginExample example);

    UserLogin selectOneByExample(UserLoginExample example);

    UserLogin selectOneByExampleSelective(@Param("example") UserLoginExample example, @Param("selective") UserLogin.Column ... selective);

    UserLogin selectByPrimaryKeySelective(@Param("selective") UserLogin.Column ... selective);

    int batchInsert(@Param("list") List<UserLogin> list);

    int batchInsertSelective(@Param("list") List<UserLogin> list, @Param("selective") UserLogin.Column ... selective);
}