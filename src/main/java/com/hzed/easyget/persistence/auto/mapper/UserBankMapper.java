package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.UserBank;
import com.hzed.easyget.persistence.auto.entity.example.UserBankExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserBankMapper {
    long countByExample(UserBankExample example);

    int deleteByExample(UserBankExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserBank record);

    int insertSelective(UserBank record);

    List<UserBank> selectByExample(UserBankExample example);

    List<UserBank> selectByExampleSelective(@Param("example") UserBankExample example, @Param("selective") UserBank.Column ... selective);

    UserBank selectByPrimaryKey(Long id);

    UserBank selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UserBank.Column ... selective);

    int updateByExampleSelective(@Param("record") UserBank record, @Param("example") UserBankExample example);

    int updateByExample(@Param("record") UserBank record, @Param("example") UserBankExample example);

    int updateByPrimaryKeySelective(UserBank record);

    int updateByPrimaryKey(UserBank record);

    UserBank selectOneByExample(UserBankExample example);

    UserBank selectOneByExampleSelective(@Param("example") UserBankExample example, @Param("selective") UserBank.Column ... selective);

    int batchInsert(@Param("list") List<UserBank> list);

    int batchInsertSelective(@Param("list") List<UserBank> list, @Param("selective") UserBank.Column ... selective);
}