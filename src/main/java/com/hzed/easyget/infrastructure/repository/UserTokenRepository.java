package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.UserToken;
import com.hzed.easyget.persistence.auto.entity.example.UserTokenExample;
import com.hzed.easyget.persistence.auto.mapper.UserTokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wuchengwu
 * @date 2018/5/24
 */
@Repository
public class UserTokenRepository {

    @Autowired
    private UserTokenMapper userTokenMapper;

    public UserToken findByUserIdAndImei(Long userId, String imei) {

        UserTokenExample example = new UserTokenExample();
        example.createCriteria().andUserIdEqualTo(userId).andImeiEqualTo(imei);
        return userTokenMapper.selectOneByExample(example);
    }

    @Transactional(rollbackFor = Exception.class)
    public int updateByUserIdAndImei(UserToken userToken) {
        UserTokenExample example = new UserTokenExample();
        example.createCriteria().andIdEqualTo(userToken.getId());
        return userTokenMapper.updateByExampleSelective(userToken, example);
    }

}