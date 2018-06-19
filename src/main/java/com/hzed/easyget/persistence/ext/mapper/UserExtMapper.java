package com.hzed.easyget.persistence.ext.mapper;

import com.hzed.easyget.persistence.ext.entity.UserExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserExtMapper {
    /**
     * 查询用户的逾期天数
     * @param id
     * @return
     */
    UserExt queryOverdueDayByUId(@Param("id") Long id);

    /**
     * 查询用户的还款凭证
     * @param id
     * @return
     */
    List<String> queryEvidences(@Param("id")Long id);

    /**
     * 获取用户还款的VA码
     * @param id
     * @return
     */
    String queryVa(@Param("id")Long id);

    /**
     * 查询用户的还款交易流水id
     * @param id
     * @return
     */
    Long queryTransactionId(@Param("id")Long id);
}


