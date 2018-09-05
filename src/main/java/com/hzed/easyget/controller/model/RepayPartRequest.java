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
    @NotNull(message = "[bidId]不能为空")
    private Long bidId;
    @NotNull(message = "[repayAmount]不能为空，最小值300000")
    @DecimalMin(value="300000")
    private BigDecimal repayAmount;
}