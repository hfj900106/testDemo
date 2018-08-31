package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.Profile;
import com.hzed.easyget.persistence.auto.entity.UserAuthStatus;
import com.hzed.easyget.persistence.auto.entity.UserPic;
import com.hzed.easyget.persistence.auto.entity.Work;
import com.hzed.easyget.persistence.auto.mapper.ProfileMapper;
import com.hzed.easyget.persistence.auto.mapper.UserAuthStatusMapper;
import com.hzed.easyget.persistence.auto.mapper.UserPicMapper;
import com.hzed.easyget.persistence.auto.mapper.WorkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hfj
 * @date 2018/5/23
 */
@Repository
public class PersonInfoRepository {
    @Autowired
    private ProfileMapper profileMapper;
    @Autowired
    private WorkMapper workMapper;
    @Autowired
    private UserAuthStatusMapper userAuthStatusMapper;
    @Autowired
    private UserPicMapper userPicMapper;

    @Transactional(rollbackFor = Exception.class)
    public void insertPersonInfoAndUserAuthStatus(Work work, Profile profile, List<UserAuthStatus> userAuthStatus,List<UserPic> list) {
        userPicMapper.batchInsertSelective(list,UserPic.Column.id,UserPic.Column.userId,UserPic.Column.type,UserPic.Column.picUrl);
        workMapper.insertSelective(work);
        profileMapper.insertSelective(profile);
        userAuthStatusMapper.batchInsertSelective(userAuthStatus,
                UserAuthStatus.Column.id,
                UserAuthStatus.Column.userId,
                UserAuthStatus.Column.authCode,
                UserAuthStatus.Column.authStatus,
                UserAuthStatus.Column.expireTime,
                UserAuthStatus.Column.remark);
    }
}
