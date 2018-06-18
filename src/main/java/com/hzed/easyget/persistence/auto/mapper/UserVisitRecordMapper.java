package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.UserVisitRecord;
import com.hzed.easyget.persistence.auto.entity.example.UserVisitRecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserVisitRecordMapper {
    long countByExample(UserVisitRecordExample example);

    int deleteByExample(UserVisitRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserVisitRecord record);

    int insertSelective(UserVisitRecord record);

    List<UserVisitRecord> selectByExample(UserVisitRecordExample example);

    List<UserVisitRecord> selectByExampleSelective(@Param("example") UserVisitRecordExample example, @Param("selective") UserVisitRecord.Column ... selective);

    UserVisitRecord selectByPrimaryKey(Long id);

    UserVisitRecord selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UserVisitRecord.Column ... selective);

    int updateByExampleSelective(@Param("record") UserVisitRecord record, @Param("example") UserVisitRecordExample example);

    int updateByExample(@Param("record") UserVisitRecord record, @Param("example") UserVisitRecordExample example);

    int updateByPrimaryKeySelective(UserVisitRecord record);

    int updateByPrimaryKey(UserVisitRecord record);

    UserVisitRecord selectOneByExample(UserVisitRecordExample example);

    UserVisitRecord selectOneByExampleSelective(@Param("example") UserVisitRecordExample example, @Param("selective") UserVisitRecord.Column ... selective);

    int batchInsert(@Param("list") List<UserVisitRecord> list);

    int batchInsertSelective(@Param("list") List<UserVisitRecord> list, @Param("selective") UserVisitRecord.Column ... selective);
}