package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

/**
*@description：获取还款码请求bluepay实体
*@author：[zhangruilin]
*@time：2018/6/18-14:01
**/
@Data
public class PaymentCodeRequest {
    /**
     *  交易id
     */
    @NotBlank(message = "交易id不能为空")
    private String  transactionId;
    /**
     * 还款金额
     */
    @NotBlank(message = "交易金额不能为空")
    private BigDecimal price;
    /**
     * 手机号
     */
    private String msisdn;
    /**
     * 支付方式
     */
    @Pattern(regexp = "^ATM|OTC$", message = "值只能为ATM|OTC")
    private String  payType;
    /**
     * 银行类型
     */
    private String bankType;
}
