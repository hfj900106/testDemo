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


    public List<UserMessage> findList(Long userId, Integer pageNo, Integer pageSize) {
        UserMessageExample example = new UserMessageExample();

        UserMessageExample.Criteria criteria1 = example.createCriteria();
        criteria1.andUserIdEqualTo(userId);
        UserMessageExample.Criteria criteria2 = example.createCriteria();
        criteria2.andUserIdIsNull();
        example.or(criteria2);
        example.setOrderByClause(UserMessage.Column.createTime.desc());
        example.page(pageNo, pageSize);

        return userMessageMapper.selectByExample(example);
    }

    public UserMessage findOne() {
        UserMessageExample example = new UserMessageExample();
        example.createCriteria().andUserIdIsNull();
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

    /**
     * 根据id获取公告内容
     * @param id
     * @return
     */
    public UserMessage findOneById(Long id) {
        UserMessageExample example = new UserMessageExample();
        example.createCriteria().andIdEqualTo(id);
        return userMessageMapper.selectOneByExample(example);
    }
}
