package com.demo.hzed.infrastructure.repository;

import com.demo.hzed.persistence.auto.entity.User;
import com.demo.hzed.persistence.auto.entity.example.UserExample;
import com.demo.hzed.persistence.auto.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * demo
 *
 * @author guichang
 * @since 2018/4/3
 */

@Repository
public class UserRepository {
    @Autowired
    private UserMapper userMapper;

    public User findByMobileAndIdcard(String mobile, String idCard) {
        UserExample example = new UserExample();
        example.createCriteria().andMobileEqualTo(mobile).andIdNumberEqualTo(idCard);
        return userMapper.selectOneByExample(example);
    }
}