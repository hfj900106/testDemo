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
    private String  transactionId;
    /**
     * 还款金额
     */
    private BigDecimal price;
    /**
     * 手机号
     */
    private String msisdn;
    /**
     * 支付方式
     */
    private String  payType;
    /**
     * 银行类型
     */
    private String bankType;
    /**
     * 付款账号
     */
    private String cardNo;


}
