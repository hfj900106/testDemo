package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.UserTransaction;
import com.hzed.easyget.persistence.auto.entity.example.UserTransactionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserTransactionMapper {
    long countByExample(UserTransactionExample example);

    int deleteByExample(UserTransactionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserTransaction record);

    int insertSelective(UserTransaction record);

    List<UserTransaction> selectByExample(UserTransactionExample example);

    List<UserTransaction> selectByExampleSelective(@Param("example") UserTransactionExample example, @Param("selective") UserTransaction.Column ... selective);

    UserTransaction selectByPrimaryKey(Long id);

    UserTransaction selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UserTransaction.Column ... selective);

    int updateByExampleSelective(@Param("record") UserTransaction record, @Param("example") UserTransactionExample example);

    int updateByExample(@Param("record") UserTransaction record, @Param("example") UserTransactionExample example);

    int updateByPrimaryKeySelective(UserTransaction record);

    int updateByPrimaryKey(UserTransaction record);

    UserTransaction selectOneByExample(UserTransactionExample example);

    UserTransaction selectOneByExampleSelective(@Param("example") UserTransactionExample example, @Param("selective") UserTransaction.Column ... selective);

    int batchInsert(@Param("list") List<UserTransaction> list);

    int batchInsertSelective(@Param("list") List<UserTransaction> list, @Param("selective") UserTransaction.Column ... selective);
}