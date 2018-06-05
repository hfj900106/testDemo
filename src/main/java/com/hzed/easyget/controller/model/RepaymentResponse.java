package com.hzed.easyget.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author wuchengwu
 * @data 2018/6/4
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepaymentResponse {
    private String loanMount;
    private String loanTime;
    private String status;
    private String type;
    private String days;
}