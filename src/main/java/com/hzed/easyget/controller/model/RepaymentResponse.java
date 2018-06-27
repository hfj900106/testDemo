package com.hzed.easyget.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 *
 * @author wuchengwu
 * @data 2018/6/4
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepaymentResponse {
    /** 借款金额 */
    private BigDecimal loanAmount;
    /** 实际还款时间 或 到期时间 -结清才会显示*/
    private Long repayTime;
    private Integer status;
    private Integer days;
    private Long bid;
}