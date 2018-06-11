package com.hzed.easyget.controller.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 还款详情返回参数
 *
 * @author wuchengwu
 * @date 2018/6/6
 */
@Data
public class RepayDetailResponse {

    private BigDecimal totalRepayAmount;
    private Integer period;
    /** 借款时间 */
    private String loanTime;
    /** 到期时间或结清时间 */
    private String repayTime;
    private Integer status;
}