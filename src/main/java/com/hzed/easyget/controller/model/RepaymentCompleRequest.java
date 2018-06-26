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
    private String transactionId;
    /**
     * 支付方式
     *
     */
    private String payType;
    /**
     * 银行类型如果payType=atm,那么bankType必须等于其中之一：permata,bni。如果payType=otc,bankType不用传,取决于payType
     */
    private String bankType;
    /**
     * 收款码
     */
    private String paymentCode;
    /**
     * 手机号
     */
    private String msisdn;
    /**
     * 交易金额
     */
    private BigDecimal price;
    /**
     * 流水号
     */
    private String requestNo;
    /**
     * 付款账号
     */
    private String cardNo;
}
