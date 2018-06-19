package com.hzed.easyget.controller.model;

import lombok.Data;

import java.math.BigDecimal;

/**
*@description：部分还款、全部还款到确认转账页面标详情
*@author：[zhangruilin]
*@time：2018/6/15-14:23
**/
@Data
public class LoanManagResponse {
    /**
     * 交易流水
     */
    private Long payId;
    /**
     * 标应还金额
     */
    private BigDecimal amount;
    /**
     * 应还时间
     */
    private String repaymentTime;
    /**
     * VA码
     */
    private String vaCodel;
    /**
     * va码创建时间
     */
    private String  createTime;
    /**
     * 交易方式
     */
    private String mode;
}
