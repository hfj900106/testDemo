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
    @NotNull(message = "{param.repay.bidId.isNotEmpty}")
    private Long bidId;
    @NotNull(message = "{param.repay.repayAmount.isNotEmpty}")
    @DecimalMin(value="100")
    private BigDecimal repayAmount;
}