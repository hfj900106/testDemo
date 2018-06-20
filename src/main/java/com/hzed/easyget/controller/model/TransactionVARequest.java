package com.hzed.easyget.controller.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
*@description：获取va码请求实体
*@author：[zhangruilin]
*@time：2018/6/18-11:06
**/
@Data
public class TransactionVARequest {
    /**
     * 交易流水id
     */
    @NotNull(message = "{param.repay.payId.isNotEmpty}")
    private Long payId;
    /**
     * 交易方式 ATM OTC
     */
    @Pattern(regexp = "^ATM|OTC$", message = "{param.repay.mode.must}")
    private String mode;
}
