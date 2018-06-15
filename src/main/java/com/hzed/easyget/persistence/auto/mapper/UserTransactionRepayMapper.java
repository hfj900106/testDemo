package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.UserTransactionRepay;
import com.hzed.easyget.persistence.auto.entity.example.UserTransactionRepayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserTransactionRepayMapper {
    long countByExample(UserTransactionRepayExample example);

    int deleteByExample(UserTransactionRepayExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserTransactionRepay record);

    int insertSelective(UserTransactionRepay record);

    List<UserTransactionRepay> selectByExample(UserTransactionRepayExample example);

    List<UserTransactionRepay> selectByExampleSelective(@Param("example") UserTransactionRepayExample example, @Param("selective") UserTransactionRepay.Column ... selective);

    UserTransactionRepay selectByPrimaryKey(Long id);

    UserTransactionRepay selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UserTransactionRepay.Column ... selective);

    int updateByExampleSelective(@Param("record") UserTransactionRepay record, @Param("example") UserTransactionRepayExample example);

    int updateByExample(@Param("record") UserTransactionRepay record, @Param("example") UserTransactionRepayExample example);

    int updateByPrimaryKeySelective(UserTransactionRepay record);

    int updateByPrimaryKey(UserTransactionRepay record);

    UserTransactionRepay selectOneByExample(UserTransactionRepayExample example);

    UserTransactionRepay selectOneByExampleSelective(@Param("example") UserTransactionRepayExample example, @Param("selective") UserTransactionRepay.Column ... selective);

    int batchInsert(@Param("list") List<UserTransactionRepay> list);

    int batchInsertSelective(@Param("list") List<UserTransactionRepay> list, @Param("selective") UserTransactionRepay.Column ... selective);
}