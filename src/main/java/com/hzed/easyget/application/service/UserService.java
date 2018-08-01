package com.hzed.easyget.application.service;

import com.google.common.collect.Lists;
import com.hzed.easyget.controller.model.*;
import com.hzed.easyget.infrastructure.config.SystemProp;
import com.hzed.easyget.infrastructure.enums.BizCodeEnum;
import com.hzed.easyget.infrastructure.exception.WarnException;
import com.hzed.easyget.infrastructure.model.GlobalUser;
import com.hzed.easyget.infrastructure.repository.NewsRepository;
import com.hzed.easyget.infrastructure.repository.UserMessageRepository;
import com.hzed.easyget.infrastructure.repository.UserRepository;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import com.hzed.easyget.infrastructure.utils.RequestUtil;
import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.News;
import com.hzed.easyget.persistence.auto.entity.User;
import com.hzed.easyget.persistence.auto.entity.UserMessage;
import com.hzed.easyget.persistence.auto.entity.UserTransaction;
import com.hzed.easyget.persistence.ext.entity.UserMessageExt;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
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
    @Autowired
    private SystemProp systemProp;
    @Autowired
    private NewsRepository newsRepository;

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


    public List<NewsAndMessageResponse> getNewsAndMessageList(NewsRequest request) {

        List<NewsAndMessageResponse> newsAndMessageResponseList = Lists.newArrayList();
        Long userId = RequestUtil.getGlobalUser().getUserId();
        String i18n = RequestUtil.getGlobalHead().getI18n();
        Integer pageNo = request.getPageNo();
        Integer pageSize = request.getPageSize();

        // 关联查询公告表与用户消息表
        List<UserMessageExt> newsAndMessageList = userMessageRepository.findList(userId, i18n, pageNo, pageSize);
        newsAndMessageList.forEach(newsAndMessage -> {
            NewsAndMessageResponse newsAndMessageResponse = new NewsAndMessageResponse();
            newsAndMessageResponse.setTitle(newsAndMessage.getTitle());
            newsAndMessageResponse.setToUrl(systemProp.getH5MessageUrl() + newsAndMessage.getId() + "&type=news");
            if (!ObjectUtils.isEmpty(newsAndMessage.isHasRead())) {
                newsAndMessageResponse.setHasRead(newsAndMessage.isHasRead());
            }

            if (!ObjectUtils.isEmpty(newsAndMessage.getUserId())) {
                newsAndMessageResponse.setUserId(newsAndMessage.getUserId());
                newsAndMessageResponse.setToUrl(systemProp.getH5MessageUrl() + newsAndMessage.getId() + "&type=message");
            }

            newsAndMessageResponse.setCreateTime(DateUtil.localDateTimeToTimestamp(newsAndMessage.getCreateTime()));
            newsAndMessageResponse.setId(newsAndMessage.getId());

            newsAndMessageResponseList.add(newsAndMessageResponse);
        });

        return newsAndMessageResponseList;
    }

    public MessageContentH5Response getMessageContentH5(MessageContentH5Request request) {

        MessageContentH5Response messageContentH5Response = new MessageContentH5Response();
        String type = request.getType();
        if (("news").equals(type)) {
            News news = newsRepository.findOneByIdAndLanguage(request.getId());
            if (ObjectUtils.isEmpty(news)) {
                return messageContentH5Response;
            }
            messageContentH5Response.setTitle(news.getTitle());
            messageContentH5Response.setH5Message(news.getContent());
            messageContentH5Response.setCreateTime(DateUtil.localDateTimeToStr1(news.getCreateTime()));
        } else if (("message").equals(type)) {
            UserMessage userMessage = userMessageRepository.findOneById(request.getId());
            if (ObjectUtils.isEmpty(userMessage)) {
                return messageContentH5Response;
            }
            messageContentH5Response.setTitle(userMessage.getTitle());
            messageContentH5Response.setH5Message(userMessage.getMessage());
            messageContentH5Response.setCreateTime(DateUtil.localDateTimeToStr1(userMessage.getCreateTime()));
            if (!userMessage.getHasRead()) {
                userMessage.setHasRead(true);
                userMessage.setUpdateTime(LocalDateTime.now());
                userMessageRepository.updateHasReadById(userMessage);
            }
        } else {
            throw new WarnException(BizCodeEnum.ILLEGAL_PARAM);
        }



        return messageContentH5Response;
    }

    public void saveUserMessage(UserMessageRequest request) {

        UserMessage userMessage = new UserMessage();
        userMessage.setId(IdentifierGenerator.nextId());
        userMessage.setTitle(request.getTitle());
        userMessage.setMessage(request.getAppMessage());
        userMessageRepository.insert(userMessage);
    }
}
