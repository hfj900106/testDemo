package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.SmsLog;
import com.hzed.easyget.persistence.auto.entity.example.SmsLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsLogMapper {
    long countByExample(SmsLogExample example);

    int deleteByExample(SmsLogExample example);

    int insert(SmsLog record);

    int insertSelective(SmsLog record);

    List<SmsLog> selectByExample(SmsLogExample example);

    List<SmsLog> selectByExampleSelective(@Param("example") SmsLogExample example, @Param("selective") SmsLog.Column ... selective);

    int updateByExampleSelective(@Param("record") SmsLog record, @Param("example") SmsLogExample example);

    int updateByExample(@Param("record") SmsLog record, @Param("example") SmsLogExample example);

    SmsLog selectOneByExample(SmsLogExample example);

    SmsLog selectOneByExampleSelective(@Param("example") SmsLogExample example, @Param("selective") SmsLog.Column ... selective);

    SmsLog selectByPrimaryKeySelective(@Param("selective") SmsLog.Column ... selective);

    int batchInsert(@Param("list") List<SmsLog> list);

    int batchInsertSelective(@Param("list") List<SmsLog> list, @Param("selective") SmsLog.Column ... selective);
}