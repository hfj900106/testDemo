package com.hzed.easyget.controller.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 预提交贷款返回参数
 *
 * @author wuchengwu
 * @date 2018/7/11
 */
@Data
public class PreSubmitLoanResponse {

    private BigDecimal totalAmount;
    private BigDecimal loanAmount;
    private Integer period;
    private BigDecimal cost;
    private BigDecimal receiveAmount;
    private String bankCode;
    private String bankName;
    private String inAccount;
    private BigDecimal serviceFee;
    private List<BidDetailFeeResponse> bidDetailFeeList;
}