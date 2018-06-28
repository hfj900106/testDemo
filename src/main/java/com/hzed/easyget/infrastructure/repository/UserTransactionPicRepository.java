package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.UserTransactionPic;
import com.hzed.easyget.persistence.auto.mapper.UserTransactionPicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dengzhenhao
 * @since 2018/6/28 17:10
 */
@Repository
public class UserTransactionPicRepository {

    @Autowired
    private UserTransactionPicMapper userTransactionPicMapper;

    public void batchInsert(List<UserTransactionPic> userTransactionPicList) {
        userTransactionPicMapper.batchInsertSelective(userTransactionPicList, UserTransactionPic.Column.evidencePicUrl, UserTransactionPic.Column.bidId,
                UserTransactionPic.Column.va, UserTransactionPic.Column.mode);
    }
}
