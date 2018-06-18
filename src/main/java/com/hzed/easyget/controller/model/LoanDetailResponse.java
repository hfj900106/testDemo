package com.hzed.easyget.controller.model;

import lombok.Data;

/**
 * 借款详情返回参数
 *
 * @author wuchengwu
 * @data 2018/6/7
 */
@Data
public class LoanDetailResponse {
    private String applyAmount;
    private String inBank;
    private String inAccount;
    private String applyTime;
    private String auditTime;
    private String loanTime;
    private Byte status;
}