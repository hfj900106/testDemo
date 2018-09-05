package com.hzed.easyget.controller.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 试算接口请求参数
 *
 * @author wuchengwu
 * @data 2018/5/24
 */
@Data
public class LoanCalculateRequest {
    @NotNull(message = "[loanAmount]不能为空")
    private BigDecimal loanAmount;
    @NotNull(message = "[period]不能为空")
    private Integer period;
}