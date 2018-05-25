package com.hzed.easyget.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 可贷款返回值
 *
 * @author wuchengwu
 * @data 2018/5/25
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoanResponse {
    private String isLoan;
}