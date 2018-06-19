package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

/**
*@description：完成还款求情bluepay实体
*@author：[zhangruilin]
*@time：2018/6/18-17:10
**/
@Data
public class RepaymentCompleRequest {
    /**
     * 交易id
     */
    @NotBlank(message = "交易id不能为空")
    private String transactionId;
    /**
     * 支付方式
     *
     */
    @NotBlank(message = "支付方式不能为空")
    @Pattern(regexp = "^ATM|OTC$", message = "值只能为ATM|OTC")
    private String payType;
    /**
     * 银行类型如果payType=atm,那么bankType必须等于其中之一：permata,bni。如果payType=otc,bankType不用传,取决于payType
     */
    private String bankType;
    /**
     * 收款码
     */
    @NotBlank(message = "收款码不能为空")
    private String paymentCode;
    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    private String msisdn;
    /**
     * 交易金额
     */
    @NotBlank(message = "交易金额不能为空")
    private BigDecimal price;
    /**
     * 流水号
     */
    @NotBlank(message = "请求流水号不能为空")
    private String requestNo;
    /**
     * 付款账号
     */
    @NotBlank(message = "付款账号不能为空")
    private String cardNo;
}
