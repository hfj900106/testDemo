package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

/**
 * 获取还款码 bluepay请求实体
 *
 * @author zhangrl
 * @date 2018/6/8
 */
@Data
public class PaymentCodeRequest {
    /**
     * 交易id
     */
    private String transactionId;
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
    private String payType;
    /**
     * 银行类型
     */
    private String bankType;


}
