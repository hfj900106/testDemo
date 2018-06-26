package com.hzed.easyget.controller.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 借款详情请求参数
 *
 * @author wuchengwu
 * @data 2018/6/7
 */
@Data
public class LoanDetailRequest {
    @NotNull(message = "{param.loan.bid.isNotEmpty}")
    private Long bid;
}