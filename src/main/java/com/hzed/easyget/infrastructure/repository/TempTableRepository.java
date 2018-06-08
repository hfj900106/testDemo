package com.hzed.easyget.infrastructure.repository;


import com.hzed.easyget.persistence.auto.entity.BidProgress;
import com.hzed.easyget.persistence.auto.entity.TempTable;
import com.hzed.easyget.persistence.auto.entity.example.TempTableExample;
import com.hzed.easyget.persistence.auto.mapper.BidProgressMapper;
import com.hzed.easyget.persistence.auto.mapper.TempTableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hfj
 * @date 2018/6/7
 */
@Repository
public class TempTableRepository {
    @Autowired
    TempTableMapper tempMapper;
    @Autowired
    private BidProgressMapper bidProgressMapper;

    public void insertJob(TempTable tempTable) {
        tempMapper.insert(tempTable);
    }

    @Transactional(rollbackFor = Exception.class)
    public void afterPushBid(BidProgress bidProgress, Long jobId) {
        bidProgressMapper.insert(bidProgress);
        TempTableExample tableExample = new TempTableExample();
        tableExample.createCriteria().andRelaseIdEqualTo(jobId).andJobNameEqualTo("pushBid");
        tempMapper.deleteByExample(tableExample);
    }

    public List<TempTable> getByJobName() {
        TempTableExample example = new TempTableExample();
        example.createCriteria().andJobNameEqualTo("pushBid").andReRunTimesGreaterThanOrEqualTo(Integer.valueOf(5).byteValue());
        List<TempTable> jobs = tempMapper.selectByExampleSelective(example);
        return jobs;
    }

    public List<TempTable> getTempByJobNameAndReId(String name,Long id) {
        TempTableExample example = new TempTableExample();
        example.createCriteria().andJobNameEqualTo(name).andRelaseIdEqualTo(id);
        List<TempTable> jobs = tempMapper.selectByExample(example);
        return jobs;
    }

    public void upDateTemp(TempTable tempTable) {
        tempMapper.updateByPrimaryKeySelective(tempTable);
    }

}
