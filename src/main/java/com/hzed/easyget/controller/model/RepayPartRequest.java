package com.hzed.easyget.controller.model;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 部分还款
 *
 * @author guichang
 * @date 2018/6/6
 */

@Data
public class RepayPartRequest {
    @NotNull(message = "标ID不可为空")
    private Long bidId;
    @NotNull(message = "还款金额不可为空")
    @DecimalMin(value="100")
    private BigDecimal repayAmount;
}