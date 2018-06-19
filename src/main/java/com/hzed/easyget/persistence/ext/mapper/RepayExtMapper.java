package com.hzed.easyget.persistence.ext.mapper;

import com.hzed.easyget.controller.model.LoanManagResponse;
import com.hzed.easyget.controller.model.PaymentCodeRequest;
import com.hzed.easyget.controller.model.TransactionVARequest;
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
     * @param bidId
     * @return
     */
    LoanManagResponse findloanManagResponse(@Param("bidId") Long bidId);

    /**
     * 查询va码
     * @param id
     * @return
     */
    LoanManagResponse getVACode(@Param("id") Long id);

    /**
     * 查询va码
     * @param request
     * @return
     */
    TransactionVAResponse findVATranc(TransactionVARequest request);
    /**
     * 获取交易记录 根据交易id
     * @param id
     * @return
     */
    PaymentCodeRequest finduserTransBypaymentId(@Param("id") Long id);

    /**
     * 修改va码状态
     * @param id
     * @param b
     */
    void updateByPaymenid(@Param("id") Long id,@Param("b") byte b);
}
