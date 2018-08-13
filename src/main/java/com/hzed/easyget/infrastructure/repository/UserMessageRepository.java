package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.UserMessage;
import com.hzed.easyget.persistence.auto.entity.example.UserMessageExample;
import com.hzed.easyget.persistence.auto.mapper.UserMessageMapper;
import com.hzed.easyget.persistence.ext.entity.UserMessageExt;
import com.hzed.easyget.persistence.ext.mapper.UserMessageExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private UserMessageExtMapper userMessageExtMapper;


    public List<UserMessageExt> findList(Long userId, String i18n, Integer pageNo, Integer pageSize) {

        return userMessageExtMapper.selectNewsAndMessageList(userId,i18n,pageNo,pageSize);
    }

    public UserMessage findOne() {
        UserMessageExample example = new UserMessageExample();
        example.createCriteria().andUserIdIsNull();
        example.setOrderByClause(UserMessage.Column.createTime.desc());
        return userMessageMapper.selectOneByExample(example);
    }

    /**
     * 插入消息
     */
    public void addUserMessage(Long userId, String title, String message, String remark) {
        UserMessage userMessage = new UserMessage();
        // id经常重复，用纳秒
        userMessage.setId(System.nanoTime());
        userMessage.setUserId(userId);
        userMessage.setTitle(title);
        userMessage.setMessage(message);
        userMessage.setHasRead(false);
        userMessage.setRemark(remark);
        userMessageMapper.insertSelective(userMessage);
    }

    /**
<<<<<<< HEAD
     * 根据id和语言获取公告内容
     * @param id
     * @return
=======
     * 根据id获取公告内容
>>>>>>> remotes/origin/dev
     */
    public UserMessage findOneById(Long id) {
        UserMessageExample example = new UserMessageExample();
        example.createCriteria().andIdEqualTo(id);
        return userMessageMapper.selectOneByExample(example);
    }

    /**
     * 保存公告
     */
    public void insert(UserMessage userMessage) {

        userMessageMapper.insertSelective(userMessage);
    }

    /**
     * 更新是否已读
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateHasReadById(UserMessage userMessage) {
        UserMessageExample example = new UserMessageExample();
        example.createCriteria().andIdEqualTo(userMessage.getId());
        userMessageMapper.updateByExampleSelective(userMessage, example);
    }
}
