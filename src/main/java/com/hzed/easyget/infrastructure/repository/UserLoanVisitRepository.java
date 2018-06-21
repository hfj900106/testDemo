package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.UserLoanVisit;
import com.hzed.easyget.persistence.auto.entity.example.UserLoanVisitExample;
import com.hzed.easyget.persistence.auto.mapper.UserLoanVisitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 用户访问记录
 *
 * @author wuchengwu
 * @date 2018/6/18
 */
@Repository
public class UserLoanVisitRepository {

    @Autowired
    private UserLoanVisitMapper userLoanVisitMapper;

    public int insert(UserLoanVisit userLoanVisit) {
        return userLoanVisitMapper.insertSelective(userLoanVisit);
    }

    public UserLoanVisit findByUserIdAndBidId(Long userId, Long bidId) {
        UserLoanVisitExample example = new UserLoanVisitExample();
        example.createCriteria().andUserIdEqualTo(userId).andBidIdEqualTo(bidId);
        return userLoanVisitMapper.selectOneByExample(example);
    }
}