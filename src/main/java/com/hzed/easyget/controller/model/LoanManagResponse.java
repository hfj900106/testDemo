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
     * 交易id 唯一
     */
    private String payMentId;
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
     * va码过期时间
     */
    private String  expiryTime;
    /**
     * 交易方式
     */
    private String mode;
}
