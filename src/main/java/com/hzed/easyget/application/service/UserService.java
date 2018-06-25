package com.hzed.easyget.application.service;

import com.hzed.easyget.controller.model.TransactionRecordRequest;
import com.hzed.easyget.controller.model.TransactionRecordResponse;
import com.hzed.easyget.controller.model.TransactionVO;
import com.hzed.easyget.controller.model.UserResponse;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.repository.UserRepository;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import com.hzed.easyget.persistence.auto.entity.User;
import com.hzed.easyget.persistence.auto.entity.UserTransaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.util.ArrayList;
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
    public UserResponse getAccountInfo() {
        //已认证时显示姓名手机号，未认证时显示手机号，因为身份认证之后才有真实姓名，所以有名字意味着已经认证
        GlobalUser globalUser = RequestUtil.getGlobalUser();
        Long userId = globalUser.getUserId();
        User user = userRepository.findById(userId);
        UserResponse userResponse = new UserResponse();
        userResponse.setMobile(user.getMobileAccount());
        userResponse.setRealName(user.getRealName());
        userResponse.setProfilePhoto(user.getProfilePhoto());
        return userResponse;
    }

    /**
     * 我的交易记录,根据用户id，时间倒序
     */
    public TransactionRecordResponse getTransactionRecord(TransactionRecordRequest request) {
        TransactionRecordResponse response = new TransactionRecordResponse();
        GlobalUser user = getGlobalUser();
        List<UserTransaction> list = queryTransactionRecordForApp(user.getUserId(),request.getPageNo(),request.getPageSize());
        List<TransactionVO> listResponse = new ArrayList<>();
        list.forEach(userTransaction -> {
            TransactionVO transactionVO = new TransactionVO();
            transactionVO.setAmount(userTransaction.getAmount());
            transactionVO.setRemark(userTransaction.getRemark());
            transactionVO.setStatus(userTransaction.getStatus());
            transactionVO.setUpdateTime(userTransaction.getUpdateTime().toInstant(ZoneOffset.of("+8")).toEpochMilli());
            listResponse.add(transactionVO);
        });
        response.setList(listResponse);
        return response;
    }

    /**
     * 查询交易记录app
     */

    public  List<UserTransaction> queryTransactionRecordForApp(Long userId,Integer pageNo,Integer pageSize) {
        return userRepository.findTransactionRecordBySelect(userId,pageNo,pageSize);
    }


}
