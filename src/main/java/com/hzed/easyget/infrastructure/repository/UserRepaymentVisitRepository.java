package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.UserRepaymentVisit;
import com.hzed.easyget.persistence.auto.mapper.UserRepaymentVisitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author dengzhenhao
 * @since 2018/6/22 16:24
 */
@Repository
public class UserRepaymentVisitRepository {

    @Autowired
    private UserRepaymentVisitMapper userRepaymentVisitMapper;

    public void insertUserRepaymentVisit(UserRepaymentVisit repaymentVisit){
       userRepaymentVisitMapper.insertSelective(repaymentVisit);
    }
}
