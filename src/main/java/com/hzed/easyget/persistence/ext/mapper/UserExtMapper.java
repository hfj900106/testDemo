package com.hzed.easyget.persistence.ext.mapper;

import com.hzed.easyget.persistence.auto.entity.UserTransaction;
import com.hzed.easyget.persistence.ext.entity.TransactionExt;
import com.hzed.easyget.persistence.ext.entity.UserExt;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;


public interface UserExtMapper {

    /**
     * 找到还款两小时后还处于交易中的数据
     * @param time
     * @return
     */
    List<UserTransaction> findUserTransToUpdateRepayFail(@Param("time")LocalDateTime time);

    /**
     * 查询是否有没访问过的交易记录
     * @param id
     */
    TransactionExt queryTransactionVisit(@Param("id") Long id);

    /**
     * 查询该用户已放款的标的对应未结清的账单的对应信息
     * @param id
     * @return
     */
    UserExt queryUnRepayment(@Param("id") Long id);
}


