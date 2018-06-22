package com.hzed.easyget.controller.model;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
*@description： 还款接口app请求后台实体
*@author：[zhangruilin]
*@time：2018/6/20-11:21
**/
@Data
public class RepaymentRequest {
    /**
     * 交易流水id(非交易id)
     */
    @NotNull(message = "{param.repay.payId.isNotEmpty}")
    private Long payId;
    /**
     * 交易方式 ATM OTC
     */
    @Pattern(regexp = "^ATM|OTC$", message = "{param.repay.mode.must}")
    private String mode;
    /**
     * 还款金额
     */
    @NotNull(message = "{param.repay.amount.isNotEmpty}")
    @DecimalMin(value="10000")
    private BigDecimal amount;
    /**
     * base64Img 图片数组
     */
    @NotNull(message = "{param.repay.base64Imgs.isNotEmpty}")
    @Size(min = 1)
    private String[] base64Imgs;
    /**
     * 对应的图片后缀
     */
    @NotNull(message = "{param.repay.picSuffixs.isNotEmpty}")
    @Size(min = 1)
    private String[] picSuffixs;
}
