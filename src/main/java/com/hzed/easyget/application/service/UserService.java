package com.hzed.easyget.application.service;

import com.hzed.easyget.controller.model.TransactionRecordResponse;
import com.hzed.easyget.controller.model.UserResponse;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.repository.UserRepository;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import com.hzed.easyget.persistence.auto.entity.TransactionRecord;
import com.hzed.easyget.persistence.auto.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hzed.easyget.infrastructure.utils.RequestUtil.getGlobalUser;

/**
 * 我的
 *
 * @author hfj
 * @date 2018/5/25
 */
@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    /**
     * 我的
     */
    public UserResponse getAcountInfo() {
        GlobalUser globalUser = RequestUtil.getGlobalUser();
        Long userId = globalUser.getUserId();
        User user = userRepository.findById(userId);
        UserResponse userResponse = new UserResponse();
        userResponse.setMobile(user.getMobileAccount());
        userResponse.setNickName(user.getNickName());
        userResponse.setProfile_photo(user.getProfilePhoto());
        return userResponse;
    }

    /**
     * 我的交易记录,根据用户id，时间倒序
     */
    public TransactionRecordResponse getTransactionRecord() {
        TransactionRecordResponse response = new TransactionRecordResponse();
        GlobalUser user = getGlobalUser();
        List<TransactionRecord> list = queryTransactionRecordForApp(user.getUserId(),true);
        response.setList(list);
        return response;
    }

    /**
     * 查询交易记录app
     */

    public  List<TransactionRecord> queryTransactionRecordForApp(Long userId,Boolean isDisplay) {
        return userRepository.findTransactionRecordBySelect(userId,isDisplay);
    }
}
