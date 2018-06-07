package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.UserRepayment;
import com.hzed.easyget.persistence.auto.entity.example.UserRepaymentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRepaymentMapper {
    long countByExample(UserRepaymentExample example);

    int deleteByExample(UserRepaymentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserRepayment record);

    int insertSelective(UserRepayment record);

    List<UserRepayment> selectByExample(UserRepaymentExample example);

    List<UserRepayment> selectByExampleSelective(@Param("example") UserRepaymentExample example, @Param("selective") UserRepayment.Column ... selective);

    UserRepayment selectByPrimaryKey(Long id);

    UserRepayment selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UserRepayment.Column ... selective);

    int updateByExampleSelective(@Param("record") UserRepayment record, @Param("example") UserRepaymentExample example);

    int updateByExample(@Param("record") UserRepayment record, @Param("example") UserRepaymentExample example);

    int updateByPrimaryKeySelective(UserRepayment record);

    int updateByPrimaryKey(UserRepayment record);

    UserRepayment selectOneByExample(UserRepaymentExample example);

    UserRepayment selectOneByExampleSelective(@Param("example") UserRepaymentExample example, @Param("selective") UserRepayment.Column ... selective);

    int batchInsert(@Param("list") List<UserRepayment> list);

    int batchInsertSelective(@Param("list") List<UserRepayment> list, @Param("selective") UserRepayment.Column ... selective);
}