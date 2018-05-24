package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.UserToken;
import com.hzed.easyget.persistence.auto.entity.example.UserTokenExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserTokenMapper {
    long countByExample(UserTokenExample example);

    int deleteByExample(UserTokenExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserToken record);

    int insertSelective(UserToken record);

    List<UserToken> selectByExample(UserTokenExample example);

    List<UserToken> selectByExampleSelective(@Param("example") UserTokenExample example, @Param("selective") UserToken.Column ... selective);

    UserToken selectByPrimaryKey(Long id);

    UserToken selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UserToken.Column ... selective);

    int updateByExampleSelective(@Param("record") UserToken record, @Param("example") UserTokenExample example);

    int updateByExample(@Param("record") UserToken record, @Param("example") UserTokenExample example);

    int updateByPrimaryKeySelective(UserToken record);

    int updateByPrimaryKey(UserToken record);

    UserToken selectOneByExample(UserTokenExample example);

    UserToken selectOneByExampleSelective(@Param("example") UserTokenExample example, @Param("selective") UserToken.Column ... selective);

    int batchInsert(@Param("list") List<UserToken> list);

    int batchInsertSelective(@Param("list") List<UserToken> list, @Param("selective") UserToken.Column ... selective);
}