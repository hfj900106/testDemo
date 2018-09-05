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

    @NotBlank(message = "[transactionId]交易id不能为空")
    private String transactionId;
    @Pattern(regexp = "^atm|otc$", message = "[payType]支付方式必须是atm或者otc")
    private String payType;
    /**
     * 银行类型如果payType=atm,那么bankType必须等于其中之一：permata,bni,mandiri。如果payType=otc,bankType不用传,取决于payType
     */
    private String bankType;
    @NotBlank(message = "[paymentCode]还款码(VA码)不能为空")
    private String paymentCode;
    @NotBlank(message = "[msisdn]手机号不能为空(国内：+86，印尼+62)")
    private String msisdn;
    @NotNull(message = "[price]交易金额不能为空，最小10000")
    @Min(value = 10000)
    private BigDecimal price;
    private String requestNo;
    @NotBlank(message = "[cardNo]付款账号不能为空")
    private String cardNo;
}
