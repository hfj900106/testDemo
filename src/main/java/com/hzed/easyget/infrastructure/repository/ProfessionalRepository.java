package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.UserAuthStatus;
import com.hzed.easyget.persistence.auto.entity.Work;
import com.hzed.easyget.persistence.auto.mapper.UserAuthStatusMapper;
import com.hzed.easyget.persistence.auto.mapper.WorkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hfj
 * @date 2018/5/23
 */
@Repository
public class ProfessionalRepository {
    @Autowired
    private WorkMapper workMapper;
    @Autowired
    private UserAuthStatusMapper userAuthStatusMapper;
    @Transactional(rollbackFor = Exception.class)
    public void insertProfessionalAndUserAuthStatus(Work work, UserAuthStatus userAuthStatus) {
        workMapper.insertSelective(work);
        userAuthStatusMapper.insertSelective(userAuthStatus);
    }
}