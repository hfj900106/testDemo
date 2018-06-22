package com.hzed.easyget.persistence.ext.mapper;

import com.hzed.easyget.persistence.auto.entity.UserTransaction;
import com.hzed.easyget.persistence.ext.entity.TransactionExt;
import com.hzed.easyget.persistence.ext.entity.UserExt;
import com.hzed.easyget.persistence.ext.entity.VaData;
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

    /**
     * 查询用户最新的交易记录
     * @param id
     * @return
     */
    UserTransaction queryLastTransaction(@Param("id") Long id);

    /**
     * 查询用户该条还款记录是否查看过
     * @param userId
     * @param transactionId
     * @return
     */
    Long queryRepaymentVisit(@Param("userId")Long userId,@Param("transactionId")Long transactionId);

    /**
     * 添加还款结果访问记录
     */
    int insertUserRepaymentVisit(@Param("userId")Long userId,@Param("transactionId")Long transactionId);

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


