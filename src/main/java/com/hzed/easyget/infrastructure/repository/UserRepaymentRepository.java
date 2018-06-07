package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.UserRepayment;
import com.hzed.easyget.persistence.auto.mapper.UserRepaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author guichang
 * @since 2018/4/3
 */

@Repository
public class UserRepaymentRepository {
    @Autowired
    private UserRepaymentMapper userRepaymentMapper;

    public void insert(UserRepayment userRepayment) {
        userRepaymentMapper.insertSelective(userRepayment);
    }
}