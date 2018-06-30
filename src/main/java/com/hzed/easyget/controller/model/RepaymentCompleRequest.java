package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

/**
 * 完成还款求情bluepay实体
 *
 * @author zhangruilin
 * @date 2018/6/18
 **/
@Data
public class RepaymentCompleRequest {
    /**
     * 交易订单id
     */
    @NotBlank(message = "订单id不能为空")
    private String transactionId;
    /**
     * 支付方式
     */
    @Pattern(regexp = "^atm|otc$", message = "支付方式必须是atm或者otc")
    private String payType;
    /**
     * 银行类型如果payType=atm,那么bankType必须等于其中之一：permata,bni。如果payType=otc,bankType不用传,取决于payType
     */
    private String bankType;
    /**
     * 收款码(VA码)
     */
    @NotBlank(message = "还款码不能为空")
    private String paymentCode;
    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空(国内：+86，印尼+62)")
    private String msisdn;
    /**
     * 交易金额
     */
    @NotNull(message = "交易金额不能为空")
    @Min(value = 10000)
    private BigDecimal price;
    /**
     * 流水号
     */
    private String requestNo;
    /**
     * 付款账号
     */
    @NotBlank(message = "付款账号不能为空")
    private String cardNo;
}
