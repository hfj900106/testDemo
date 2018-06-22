package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.UserRepaymentVisit;
import com.hzed.easyget.persistence.auto.entity.example.UserRepaymentVisitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRepaymentVisitMapper {
    long countByExample(UserRepaymentVisitExample example);

    int deleteByExample(UserRepaymentVisitExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserRepaymentVisit record);

    int insertSelective(UserRepaymentVisit record);

    List<UserRepaymentVisit> selectByExample(UserRepaymentVisitExample example);

    List<UserRepaymentVisit> selectByExampleSelective(@Param("example") UserRepaymentVisitExample example, @Param("selective") UserRepaymentVisit.Column ... selective);

    UserRepaymentVisit selectByPrimaryKey(Long id);

    UserRepaymentVisit selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UserRepaymentVisit.Column ... selective);

    int updateByExampleSelective(@Param("record") UserRepaymentVisit record, @Param("example") UserRepaymentVisitExample example);

    int updateByExample(@Param("record") UserRepaymentVisit record, @Param("example") UserRepaymentVisitExample example);

    int updateByPrimaryKeySelective(UserRepaymentVisit record);

    int updateByPrimaryKey(UserRepaymentVisit record);

    UserRepaymentVisit selectOneByExample(UserRepaymentVisitExample example);

    UserRepaymentVisit selectOneByExampleSelective(@Param("example") UserRepaymentVisitExample example, @Param("selective") UserRepaymentVisit.Column ... selective);

    int batchInsert(@Param("list") List<UserRepaymentVisit> list);

    int batchInsertSelective(@Param("list") List<UserRepaymentVisit> list, @Param("selective") UserRepaymentVisit.Column ... selective);
}