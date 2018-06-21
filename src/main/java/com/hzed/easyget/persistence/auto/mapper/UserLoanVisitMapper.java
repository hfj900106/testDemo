package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.UserLoanVisit;
import com.hzed.easyget.persistence.auto.entity.example.UserLoanVisitExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserLoanVisitMapper {
    long countByExample(UserLoanVisitExample example);

    int deleteByExample(UserLoanVisitExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserLoanVisit record);

    int insertSelective(UserLoanVisit record);

    List<UserLoanVisit> selectByExample(UserLoanVisitExample example);

    List<UserLoanVisit> selectByExampleSelective(@Param("example") UserLoanVisitExample example, @Param("selective") UserLoanVisit.Column ... selective);

    UserLoanVisit selectByPrimaryKey(Long id);

    UserLoanVisit selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UserLoanVisit.Column ... selective);

    int updateByExampleSelective(@Param("record") UserLoanVisit record, @Param("example") UserLoanVisitExample example);

    int updateByExample(@Param("record") UserLoanVisit record, @Param("example") UserLoanVisitExample example);

    int updateByPrimaryKeySelective(UserLoanVisit record);

    int updateByPrimaryKey(UserLoanVisit record);

    UserLoanVisit selectOneByExample(UserLoanVisitExample example);

    UserLoanVisit selectOneByExampleSelective(@Param("example") UserLoanVisitExample example, @Param("selective") UserLoanVisit.Column ... selective);

    int batchInsert(@Param("list") List<UserLoanVisit> list);

    int batchInsertSelective(@Param("list") List<UserLoanVisit> list, @Param("selective") UserLoanVisit.Column ... selective);
}