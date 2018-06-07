package com.hzed.easyget.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 提交借款返回参数
 *
 * @author wuchengwu
 * @data 2018/6/7
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmitLoanResponse {
    private String bid;
}