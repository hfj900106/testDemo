package com.hzed.easyget.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wuchengwu
 * @since 2018/6/4
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepayResponse {

    private String TotalAmount;
    private List<RepaymentResponse> repaymentInfo;
}