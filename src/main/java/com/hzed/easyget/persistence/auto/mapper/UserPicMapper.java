package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.UserPic;
import com.hzed.easyget.persistence.auto.entity.example.UserPicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserPicMapper {
    long countByExample(UserPicExample example);

    int deleteByExample(UserPicExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserPic record);

    int insertSelective(UserPic record);

    List<UserPic> selectByExample(UserPicExample example);

    List<UserPic> selectByExampleSelective(@Param("example") UserPicExample example, @Param("selective") UserPic.Column ... selective);

    UserPic selectByPrimaryKey(Long id);

    UserPic selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UserPic.Column ... selective);

    int updateByExampleSelective(@Param("record") UserPic record, @Param("example") UserPicExample example);

    int updateByExample(@Param("record") UserPic record, @Param("example") UserPicExample example);

    int updateByPrimaryKeySelective(UserPic record);

    int updateByPrimaryKey(UserPic record);

    UserPic selectOneByExample(UserPicExample example);

    UserPic selectOneByExampleSelective(@Param("example") UserPicExample example, @Param("selective") UserPic.Column ... selective);

    int batchInsert(@Param("list") List<UserPic> list);

    int batchInsertSelective(@Param("list") List<UserPic> list, @Param("selective") UserPic.Column ... selective);
}