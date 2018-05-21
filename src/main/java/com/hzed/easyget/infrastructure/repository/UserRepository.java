package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.User;
import com.hzed.easyget.persistence.auto.entity.example.UserExample;
import com.hzed.easyget.persistence.auto.mapper.UserMapper;
import com.hzed.service.common.util.ObjUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public User findByMobile(String mobile) {
        UserExample example = new UserExample();
        example.createCriteria().andMobileAccountEqualTo(mobile);
        List<User> users = userMapper.selectByExample(example);
        return ObjUtil.isEmpty(users) ? null : users.get(0);
    }

}