package com.hzed.easyget.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@NoArgsConstructor
public class RepayPartRequest {
    @NotNull(message = "{param.repay.bidId.isNotEmpty}")
    private Long bidId;
    @NotNull(message = "{param.repay.repayAmount.isNotEmpty}")
    @DecimalMin(value="100")
    private BigDecimal repayAmount;
}