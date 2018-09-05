package com.hzed.easyget.controller.model;

import lombok.Data;

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

    @NotNull(message = "[bidId]不能为空")
    private Long bidId;
    @Pattern(regexp = "^BNI ATM|Mandiri ATM|Permata ATM|OTC$", message = "[mode]不能为空，交易方式支持 BNI ATM、Mandiri ATM、Permata ATM、OTC")
    private String mode;
    @NotNull(message = "[amount]不能为空，最小值10000")
    @Min(value = 10_000)
    private BigDecimal amount;
    @NotNull(message = "[flag]全部结清标识不能为空")
    private boolean flag;

}
