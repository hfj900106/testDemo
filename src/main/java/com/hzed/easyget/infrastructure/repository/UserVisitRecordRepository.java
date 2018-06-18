package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.UserVisitRecord;
import com.hzed.easyget.persistence.auto.entity.example.UserVisitRecordExample;
import com.hzed.easyget.persistence.auto.mapper.UserVisitRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 用户访问记录
 *
 * @author wuchengwu
 * @date 2018/6/18
 */
@Repository
public class UserVisitRecordRepository {

    @Autowired
    private UserVisitRecordMapper userVisitRecordMapper;

    public int insert(UserVisitRecord userVisitRecord) {
        return userVisitRecordMapper.insertSelective(userVisitRecord);
    }

    public UserVisitRecord findByUserIdAndBidId(Long userId, Long bidId) {
        UserVisitRecordExample example = new UserVisitRecordExample();
        example.createCriteria().andUserIdEqualTo(userId).andBidIdEqualTo(bidId);
        return userVisitRecordMapper.selectOneByExample(example);
    }
}