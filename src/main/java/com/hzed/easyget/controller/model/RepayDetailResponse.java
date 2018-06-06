package com.hzed.easyget.controller.model;

import lombok.Data;

/**
 * 还款详情返回参数
 *
 * @author wuchengwu
 * @date 2018/6/6
 */
@Data
public class RepayDetailResponse {

    private String totalRepayAmount;
    private Integer period;
    private String loanTime;
    private String repayTime;
}