package com.hzed.easyget.controller.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 试算接口返回参数
 *
 * @author wuchengwu
 * @date 2018/5/24
 */
@Data
public class LoanCalculateResponse {
    private BigDecimal totalAmount;
    private BigDecimal loanAmount;
    private Integer period;
    private BigDecimal cost;
    private BigDecimal receiveAmount;
}