package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.RepaymentRecord;
import com.hzed.easyget.persistence.auto.entity.example.RepaymentRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepaymentRecordMapper {
    long countByExample(RepaymentRecordExample example);

    int deleteByExample(RepaymentRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RepaymentRecord record);

    int insertSelective(RepaymentRecord record);

    List<RepaymentRecord> selectByExample(RepaymentRecordExample example);

    List<RepaymentRecord> selectByExampleSelective(@Param("example") RepaymentRecordExample example, @Param("selective") RepaymentRecord.Column ... selective);

    RepaymentRecord selectByPrimaryKey(Long id);

    RepaymentRecord selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") RepaymentRecord.Column ... selective);

    int updateByExampleSelective(@Param("record") RepaymentRecord record, @Param("example") RepaymentRecordExample example);

    int updateByExample(@Param("record") RepaymentRecord record, @Param("example") RepaymentRecordExample example);

    int updateByPrimaryKeySelective(RepaymentRecord record);

    int updateByPrimaryKey(RepaymentRecord record);

    RepaymentRecord selectOneByExample(RepaymentRecordExample example);

    RepaymentRecord selectOneByExampleSelective(@Param("example") RepaymentRecordExample example, @Param("selective") RepaymentRecord.Column ... selective);

    int batchInsert(@Param("list") List<RepaymentRecord> list);

    int batchInsertSelective(@Param("list") List<RepaymentRecord> list, @Param("selective") RepaymentRecord.Column ... selective);
}