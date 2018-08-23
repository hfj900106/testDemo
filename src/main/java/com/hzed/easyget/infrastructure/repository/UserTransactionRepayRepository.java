package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.controller.model.VaHistoryRequest;
import com.hzed.easyget.persistence.auto.entity.UserTransactionRepay;
import com.hzed.easyget.persistence.auto.entity.example.UserTransactionRepayExample;
import com.hzed.easyget.persistence.auto.mapper.UserTransactionRepayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dengzhenhao
 * @since 2018/6/28 16:33
 */
@Repository
public class UserTransactionRepayRepository {

    @Autowired
    private UserTransactionRepayMapper userTransactionRepayMapper;

    public List<UserTransactionRepay> findByBidId(VaHistoryRequest request) {
        UserTransactionRepayExample repayExample = new UserTransactionRepayExample();
        repayExample.createCriteria().andBidIdEqualTo(request.getBidId());
        repayExample.orderBy(UserTransactionRepay.Column.createTime.desc());
        repayExample.page(request.getPageNo(),request.getPageSize());
        return userTransactionRepayMapper.selectByExample(repayExample);
    }
}
