package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.UserBank;
import com.hzed.easyget.persistence.auto.entity.example.UserBankExample;
import com.hzed.easyget.persistence.auto.mapper.UserBankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户银行资料
 *
 * @author wuchengwu
 * @date 2018/7/9
 */
@Repository
public class UserBankRepository {
    @Autowired
    private UserBankMapper userBankMapper;
    public List<UserBank> findByUserId(Long userId) {
        UserBankExample example = new UserBankExample();
        example.createCriteria().andUserIdEqualTo(userId);
        example.setOrderByClause(UserBank.Column.createTime.desc());
        return userBankMapper.selectByExample(example);
    }
}