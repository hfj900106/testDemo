package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.infrastructure.utils.id.IdentifierGenerator;
import com.hzed.easyget.persistence.auto.entity.UserMessage;
import com.hzed.easyget.persistence.auto.entity.example.UserMessageExample;
import com.hzed.easyget.persistence.auto.mapper.UserMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 消息公告
 *
 * @author wuchengwu
 * @date 2018/7/11
 */
@Repository
public class UserMessageRepository {
    @Autowired
    private UserMessageMapper userMessageMapper;

    public List<UserMessage> findList() {
        UserMessageExample example = new UserMessageExample();
        example.setOrderByClause(UserMessage.Column.createTime.desc());
        return userMessageMapper.selectByExample(example);
    }

    public UserMessage findOne() {
        UserMessageExample example = new UserMessageExample();
        example.setOrderByClause(UserMessage.Column.createTime.desc());
        return userMessageMapper.selectOneByExample(example);
    }

    /**
     * 插入消息
     *
     * @param userId
     * @param title
     * @param message
     * @param remark
     */
    public void addUserMessage(Long userId, String title, String message, String remark) {
        UserMessage userMessage = new UserMessage();
        userMessage.setId(IdentifierGenerator.nextId());
        userMessage.setUserId(userId);
        userMessage.setTitle(title);
        userMessage.setMessage(message);
        userMessage.setHasRead(false);
        userMessage.setRemark(remark);
        userMessageMapper.insertSelective(userMessage);
    }
}
