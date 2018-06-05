package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.TransactionRecord;
import com.hzed.easyget.persistence.auto.entity.example.TransactionRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TransactionRecordMapper {
    long countByExample(TransactionRecordExample example);

    int deleteByExample(TransactionRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TransactionRecord record);

    int insertSelective(TransactionRecord record);

    List<TransactionRecord> selectByExample(TransactionRecordExample example);

    List<TransactionRecord> selectByExampleSelective(@Param("example") TransactionRecordExample example, @Param("selective") TransactionRecord.Column ... selective);

    TransactionRecord selectByPrimaryKey(Long id);

    TransactionRecord selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") TransactionRecord.Column ... selective);

    int updateByExampleSelective(@Param("record") TransactionRecord record, @Param("example") TransactionRecordExample example);

    int updateByExample(@Param("record") TransactionRecord record, @Param("example") TransactionRecordExample example);

    int updateByPrimaryKeySelective(TransactionRecord record);

    int updateByPrimaryKey(TransactionRecord record);

    TransactionRecord selectOneByExample(TransactionRecordExample example);

    TransactionRecord selectOneByExampleSelective(@Param("example") TransactionRecordExample example, @Param("selective") TransactionRecord.Column ... selective);

    int batchInsert(@Param("list") List<TransactionRecord> list);

    int batchInsertSelective(@Param("list") List<TransactionRecord> list, @Param("selective") TransactionRecord.Column ... selective);
}