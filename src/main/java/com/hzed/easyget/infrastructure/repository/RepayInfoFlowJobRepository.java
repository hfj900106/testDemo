package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.RepayInfoFlowJob;
import com.hzed.easyget.persistence.auto.entity.example.RepayInfoFlowJobExample;
import com.hzed.easyget.persistence.auto.mapper.RepayInfoFlowJobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author guichang
 * @date 2018/6/7
 */
@Repository
public class RepayInfoFlowJobRepository {
    @Autowired
    private RepayInfoFlowJobMapper repayInfoFlowJobMapper;

    public List<RepayInfoFlowJob> findJobList(List<Byte> status, Integer times) {
        RepayInfoFlowJobExample example = new RepayInfoFlowJobExample();
        example.createCriteria().andStatusIn(status).andTimesEqualTo(times.byteValue());

        return repayInfoFlowJobMapper.selectByExample(example);
    }

    public void update(RepayInfoFlowJob jobUpdate) {
        repayInfoFlowJobMapper.updateByPrimaryKeySelective(jobUpdate);
    }

    public void insert(RepayInfoFlowJob jobInsert) {
        repayInfoFlowJobMapper.insertSelective(jobInsert);
    }
}
