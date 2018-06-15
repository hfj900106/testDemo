package com.hzed.easyget.persistence.ext.mapper;

import com.hzed.easyget.controller.model.LoanManagResponse;
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
}
