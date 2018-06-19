package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.UserTransactionPic;
import com.hzed.easyget.persistence.auto.entity.example.UserTransactionPicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserTransactionPicMapper {
    long countByExample(UserTransactionPicExample example);

    int deleteByExample(UserTransactionPicExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserTransactionPic record);

    int insertSelective(UserTransactionPic record);

    List<UserTransactionPic> selectByExample(UserTransactionPicExample example);

    List<UserTransactionPic> selectByExampleSelective(@Param("example") UserTransactionPicExample example, @Param("selective") UserTransactionPic.Column ... selective);

    UserTransactionPic selectByPrimaryKey(Long id);

    UserTransactionPic selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UserTransactionPic.Column ... selective);

    int updateByExampleSelective(@Param("record") UserTransactionPic record, @Param("example") UserTransactionPicExample example);

    int updateByExample(@Param("record") UserTransactionPic record, @Param("example") UserTransactionPicExample example);

    int updateByPrimaryKeySelective(UserTransactionPic record);

    int updateByPrimaryKey(UserTransactionPic record);

    UserTransactionPic selectOneByExample(UserTransactionPicExample example);

    UserTransactionPic selectOneByExampleSelective(@Param("example") UserTransactionPicExample example, @Param("selective") UserTransactionPic.Column ... selective);

    int batchInsert(@Param("list") List<UserTransactionPic> list);

    int batchInsertSelective(@Param("list") List<UserTransactionPic> list, @Param("selective") UserTransactionPic.Column ... selective);
}