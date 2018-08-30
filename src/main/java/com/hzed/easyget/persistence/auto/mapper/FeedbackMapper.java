package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.Feedback;
import com.hzed.easyget.persistence.auto.entity.example.FeedbackExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FeedbackMapper {
    long countByExample(FeedbackExample example);

    int deleteByExample(FeedbackExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Feedback record);

    int insertSelective(Feedback record);

    List<Feedback> selectByExample(FeedbackExample example);

    List<Feedback> selectByExampleSelective(@Param("example") FeedbackExample example, @Param("selective") Feedback.Column ... selective);

    Feedback selectByPrimaryKey(Long id);

    Feedback selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Feedback.Column ... selective);

    int updateByExampleSelective(@Param("record") Feedback record, @Param("example") FeedbackExample example);

    int updateByExample(@Param("record") Feedback record, @Param("example") FeedbackExample example);

    int updateByPrimaryKeySelective(Feedback record);

    int updateByPrimaryKey(Feedback record);

    Feedback selectOneByExample(FeedbackExample example);

    Feedback selectOneByExampleSelective(@Param("example") FeedbackExample example, @Param("selective") Feedback.Column ... selective);

    int batchInsert(@Param("list") List<Feedback> list);

    int batchInsertSelective(@Param("list") List<Feedback> list, @Param("selective") Feedback.Column ... selective);
}