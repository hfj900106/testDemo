package com.hzed.easyget.controller.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 还款进度返回参数
 *
 * @author wuchengwu
 * @date 2018/7/19
 */
@Data
public class RepayProgressResponse {
    private BigDecimal loanAmount;
    private Integer period;
    private BigDecimal tailFree;
    private Long loanTime;
    private BigDecimal totalRepayAmount;
    private Long repayTime;
    private BigDecimal realRepayAmount;
    private Integer overdueDay;
    private BigDecimal remainderAmount;
    private BigDecimal overdueFree;
    private Byte bidStatus;
}