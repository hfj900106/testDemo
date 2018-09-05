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
    @NotNull(message = "[loanAmount]不能为空")
    private BigDecimal loanAmount;
    @NotNull(message = "[period]不能为空")
    private Integer period;
}