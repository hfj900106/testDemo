package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.UserToken;
import com.hzed.easyget.persistence.auto.entity.example.UserTokenExample;
import com.hzed.easyget.persistence.auto.mapper.UserTokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author wuchengwu
 * @date 2018/5/24
 */
@Repository
public class UserTokenRepository {

    @Autowired
    private UserTokenMapper userTokenMapper;

    public  UserToken findByUserIdAndImei(Long userId, String imei) {

        UserTokenExample example = new UserTokenExample();
        example.createCriteria().andUserIdEqualTo(userId).andImeiEqualTo(imei);
        return userTokenMapper.selectOneByExampleSelective(example);
    }

    public int updateByUserIdAndImei(UserToken userToken) {

        return userTokenMapper.updateByPrimaryKey(userToken);
    }


    public void insertByUserIdAndImei(UserToken userToken) {
        userTokenMapper.insertSelective(userToken);
    }
}