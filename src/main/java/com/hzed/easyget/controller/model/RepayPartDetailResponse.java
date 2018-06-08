package com.hzed.easyget.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 展示部分还款返回参数
 *
 * @author wuchengwu
 * @data 2018/6/8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepayPartDetailResponse {
    private String totalAmount;
    private String inAccount;
    private String minRepayAmount;
}