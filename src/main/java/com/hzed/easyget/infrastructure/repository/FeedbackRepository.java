package com.hzed.easyget.infrastructure.repository;

import com.hzed.easyget.persistence.auto.entity.Feedback;
import com.hzed.easyget.persistence.auto.mapper.FeedbackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 公告
 *
 * @author hfj
 * @since 2018/8/30
 */
@Repository
public class FeedbackRepository {

    @Autowired
    private FeedbackMapper feedbackMapper;

    public void add(Feedback feedback) {
        feedbackMapper.insertSelective(feedback);
    }

}