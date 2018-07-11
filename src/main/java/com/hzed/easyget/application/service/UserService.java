package com.hzed.easyget.application.service;

import com.google.common.collect.Lists;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.repository.UserMessageRepository;
import com.hzed.easyget.infrastructure.repository.UserRepository;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import com.hzed.easyget.persistence.auto.entity.User;
import com.hzed.easyget.persistence.auto.entity.UserMessage;
import com.hzed.easyget.persistence.auto.entity.UserTransaction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
    @Autowired
    private UserMessageRepository userMessageRepository;

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
        List<UserTransaction> list = userRepository.findTransactionRecordByUserId(user.getUserId(), request.getPageNo(), request.getPageSize());
        List<TransactionVO> listResponse = new ArrayList<>();
        if (!ObjectUtils.isEmpty(list)) {
            list.forEach(userTransaction -> {
                String account = userTransaction.getAccount();
                if (!StringUtils.isBlank(account)) {
                    account = account.substring(account.length() - 4, account.length());
                }
                TransactionVO transactionVO = new TransactionVO();
                transactionVO.setBidId(userTransaction.getBidId());
                transactionVO.setAmount(userTransaction.getAmount());
                transactionVO.setType(userTransaction.getType());
                transactionVO.setStatus(userTransaction.getStatus());
                transactionVO.setBankAccount(account);
                transactionVO.setUpdateTime(DateUtil.localDateTimeToTimestamp(userTransaction.getUpdateTime()));
                listResponse.add(transactionVO);
            });
        }
        response.setList(listResponse);
        return response;
    }


    public List<MessageResponse> getMessageList(MessageRequest request) {

        List<MessageResponse> messageResponseList = Lists.newArrayList();
        Long userId = RequestUtil.getGlobalUser().getUserId();
        Integer pageNo = request.getPageNo();
        Integer pageSize = request.getPageSize();
        List<UserMessage> list = userMessageRepository.findList(userId, pageNo, pageSize);
        if (ObjectUtils.isEmpty(list)) {
            return messageResponseList;
        }
        list.forEach(userMessage -> {
            MessageResponse messageResponse = new MessageResponse();
            messageResponse.setMessageTitle(userMessage.getTitle());
            messageResponse.setMessage(userMessage.getMessage());
            if (userMessage.getHasRead() != null) {
                messageResponse.setHasRead(userMessage.getHasRead());
            }
            messageResponse.setCreateTime(DateUtil.localDateTimeToTimestamp(userMessage.getCreateTime()));
            messageResponseList.add(messageResponse);
        });

        return messageResponseList;
    }
}
