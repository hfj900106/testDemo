package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.UserAuthStatus;
import com.hzed.easyget.persistence.auto.entity.UserPic;
import com.hzed.easyget.persistence.auto.mapper.UserAuthStatusMapper;
import com.hzed.easyget.persistence.auto.mapper.UserPicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Autowired
    private UserPicMapper userPicMapper;

    @Transactional(rollbackFor = Exception.class)
    public void insertProfessionalAndUserAuthStatus(List<UserPic> list, Work work, UserAuthStatus userAuthStatus) {
        userPicMapper.batchInsertSelective(list,UserPic.Column.id,UserPic.Column.userId,UserPic.Column.type,UserPic.Column.picUrl);
        workMapper.insertSelective(work);
        userAuthStatusMapper.insertSelective(userAuthStatus);
    }
}