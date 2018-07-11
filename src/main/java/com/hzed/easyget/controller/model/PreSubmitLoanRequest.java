package com.hzed.easyget.controller.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 提交贷款请求参数
 *
 * @author wuchengwu
 * @date 2018/7/11
 */
@Data
public class PreSubmitLoanRequest {
    @NotNull(message = "{param.loan.loanAmount.isNotEmpty}")
    private BigDecimal loanAmount;
    @NotNull(message = "{param.loan.period.isNotEmpty}")
    private Integer period;
}