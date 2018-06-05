package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.Professional;
import com.hzed.easyget.persistence.auto.entity.UserAuthStatus;
import com.hzed.easyget.persistence.auto.mapper.ProfessionalMapper;
import com.hzed.easyget.persistence.auto.mapper.UserAuthStatusMapper;
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
    private ProfessionalMapper professionalMapper;
    @Autowired
    private UserAuthStatusMapper userAuthStatusMapper;
    @Transactional(rollbackFor = Exception.class)
    public void insertProfessionalAndUserAuthStatus(Professional professional, UserAuthStatus userAuthStatus) {
        professionalMapper.insertSelective(professional);
        userAuthStatusMapper.insertSelective(userAuthStatus);
    }
}