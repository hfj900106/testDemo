package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.ParameterScriptAssert;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

/**
 * 获取还款码请求实体
 *
 * @author zhangrl
 * @date 2018/6/8
 */
@Data
public class TransactionVaRequest {
    /**
     * 标的id
     */
    @NotNull(message = "{param.repay.bidId.isNotEmpty}")
    private Long bidId;
    /**
     * 交易方式 BNI ATM|Mandiri ATM|Permata ATM|OTC
     */
    @Pattern(regexp = "^BNI ATM|Mandiri ATM|Permata ATM|OTC$", message = "{param.repay.mode.must}")
    private String mode;
    /**
     * 交易金额
     */
    @NotNull(message = "{param.repay.amount.isNotEmpty}")
    @Min(value = 10_000)
    private BigDecimal amount;
    /**
     * 全部结清标识
     */
    @NotNull(message = "{param.repay.flag.isNotEmpty}")
    private boolean flag;
}
