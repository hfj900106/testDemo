package com.hzed.easyget.persistence.ext.mapper;

import com.hzed.easyget.persistence.ext.entity.UserMessageExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserMessageExtMapper {

    List<UserMessageExt> selectNewsAndMessageList(@Param("userId") Long userId, @Param("i18n") String i18n,
                                                  @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);
}


