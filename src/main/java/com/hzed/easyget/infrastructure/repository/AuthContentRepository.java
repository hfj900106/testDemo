package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.AuthContent;
import com.hzed.easyget.persistence.auto.mapper.AuthContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author hfj
 * @date 2018/5/23
 */
@Repository
public class AuthContentRepository {

    @Autowired
    private AuthContentMapper authContentMapper;

    public void insertSelective(AuthContent authContent) {
        authContentMapper.insertSelective(authContent);
    }

}