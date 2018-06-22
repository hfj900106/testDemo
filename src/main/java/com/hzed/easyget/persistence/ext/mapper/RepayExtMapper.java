package com.hzed.easyget.persistence.ext.mapper;

import com.hzed.easyget.controller.model.LoanManagResponse;
import com.hzed.easyget.controller.model.PaymentCodeRequest;
import com.hzed.easyget.controller.model.TransactionVAResponse;
import org.apache.ibatis.annotations.Param;

/**
*@description：还款相关mybatis接口
*@author：[zhangruilin]
*@time：2018/6/15-16:03
**/
public interface RepayExtMapper {
    /**
     * 标的账单信息
     */
    LoanManagResponse findloanManagResponse(@Param("bidId") Long bidId);

    /**
     * 查询va码
     */
    LoanManagResponse getVACode(@Param("id") Long id);

    /**
     * 查询va码
     */
    TransactionVAResponse findVaTranc(@Param("payId") Long payId,@Param("mode") String mode);
    /**
     * 获取交易记录 根据交易id
     */
    PaymentCodeRequest finduserTransBypaymentId(@Param("id") Long id);

}
