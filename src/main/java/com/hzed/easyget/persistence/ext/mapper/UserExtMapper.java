package com.hzed.easyget.persistence.ext.mapper;

import com.hzed.easyget.persistence.ext.entity.TransactionExt;
import com.hzed.easyget.persistence.ext.entity.UserExt;
import com.hzed.easyget.persistence.ext.entity.VaData;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


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
     * 获取用户最近的VA码数据
     * @param id
     * @return
     */
    VaData queryLastVa(@Param("id")Long id);



    /**
     * 查询用户的交易流水transactionId和确认时间confirmTime
     * @param id
     * @return
     */
    TransactionExt queryTransaction(@Param("id")Long id);
}


