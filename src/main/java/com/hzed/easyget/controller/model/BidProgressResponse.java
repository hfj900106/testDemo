package com.hzed.easyget.controller.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 标的进度请求参数
 *
 * @author wuchengwu
 * @date 2018/7/18
 */
@Data
public class BidProgressResponse {
    private BigDecimal loanAmount;
    private Integer period;
    private Long applyTime;
    private Long reviewTime;
    private Long loanTime;
    private BigDecimal totalRepayAmount;
    private Integer overdueDay;
    private Long repayTime;
    private Integer reviewStatus;
    private Integer popupChoice;
    private boolean isPopup;



}