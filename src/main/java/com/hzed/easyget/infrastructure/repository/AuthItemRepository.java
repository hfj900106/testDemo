package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.AuthItem;
import com.hzed.easyget.persistence.auto.entity.example.AuthItemExample;
import com.hzed.easyget.persistence.auto.mapper.AuthItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 认证列表相关
 *
 * @author wuchengwu
 * @date 2018/6/19
 */
@Repository
public class AuthItemRepository {
    @Autowired
    private AuthItemMapper authItemMapper;

    public AuthItem findByCode(String dicCode) {
        AuthItemExample example = new AuthItemExample();
        example.createCriteria().andCodeEqualTo(dicCode).andIsUseEqualTo(true);
        return authItemMapper.selectOneByExample(example);
    }
}