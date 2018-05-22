package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.SmsLog;
import com.hzed.easyget.persistence.auto.mapper.SmsLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author hfj
 * @date 2018/5/21
 */
@Repository
public class SmsLogRepository {

    @Autowired
    private SmsLogMapper smsLogMapper;

    public void insertSelective(SmsLog smsLog) {
        smsLogMapper.insertSelective(smsLog);
    }


}
