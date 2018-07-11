package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.UserMessage;
import com.hzed.easyget.persistence.auto.entity.example.UserMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMessageMapper {
    long countByExample(UserMessageExample example);

    int deleteByExample(UserMessageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserMessage record);

    int insertSelective(UserMessage record);

    List<UserMessage> selectByExample(UserMessageExample example);

    List<UserMessage> selectByExampleSelective(@Param("example") UserMessageExample example, @Param("selective") UserMessage.Column ... selective);

    UserMessage selectByPrimaryKey(Long id);

    UserMessage selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UserMessage.Column ... selective);

    int updateByExampleSelective(@Param("record") UserMessage record, @Param("example") UserMessageExample example);

    int updateByExample(@Param("record") UserMessage record, @Param("example") UserMessageExample example);

    int updateByPrimaryKeySelective(UserMessage record);

    int updateByPrimaryKey(UserMessage record);

    UserMessage selectOneByExample(UserMessageExample example);

    UserMessage selectOneByExampleSelective(@Param("example") UserMessageExample example, @Param("selective") UserMessage.Column ... selective);

    int batchInsert(@Param("list") List<UserMessage> list);

    int batchInsertSelective(@Param("list") List<UserMessage> list, @Param("selective") UserMessage.Column ... selective);
}