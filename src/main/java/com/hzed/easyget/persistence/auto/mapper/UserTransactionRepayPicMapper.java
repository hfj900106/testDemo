package com.hzed.easyget.persistence.auto.mapper;

import com.hzed.easyget.persistence.auto.entity.UserTransactionRepayPic;
import com.hzed.easyget.persistence.auto.entity.example.UserTransactionRepayPicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserTransactionRepayPicMapper {
    long countByExample(UserTransactionRepayPicExample example);

    int deleteByExample(UserTransactionRepayPicExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserTransactionRepayPic record);

    int insertSelective(UserTransactionRepayPic record);

    List<UserTransactionRepayPic> selectByExample(UserTransactionRepayPicExample example);

    List<UserTransactionRepayPic> selectByExampleSelective(@Param("example") UserTransactionRepayPicExample example, @Param("selective") UserTransactionRepayPic.Column ... selective);

    UserTransactionRepayPic selectByPrimaryKey(Long id);

    UserTransactionRepayPic selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UserTransactionRepayPic.Column ... selective);

    int updateByExampleSelective(@Param("record") UserTransactionRepayPic record, @Param("example") UserTransactionRepayPicExample example);

    int updateByExample(@Param("record") UserTransactionRepayPic record, @Param("example") UserTransactionRepayPicExample example);

    int updateByPrimaryKeySelective(UserTransactionRepayPic record);

    int updateByPrimaryKey(UserTransactionRepayPic record);

    UserTransactionRepayPic selectOneByExample(UserTransactionRepayPicExample example);

    UserTransactionRepayPic selectOneByExampleSelective(@Param("example") UserTransactionRepayPicExample example, @Param("selective") UserTransactionRepayPic.Column ... selective);

    int batchInsert(@Param("list") List<UserTransactionRepayPic> list);

    int batchInsertSelective(@Param("list") List<UserTransactionRepayPic> list, @Param("selective") UserTransactionRepayPic.Column ... selective);
}