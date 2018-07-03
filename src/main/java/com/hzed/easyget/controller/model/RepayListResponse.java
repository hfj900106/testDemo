package com.hzed.easyget.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author wuchengwu
 * @since 2018/6/4
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepayListResponse {
    /** 审核金额 */
    private BigDecimal totalAmount;
    private List<RepaymentResponse> repaymentInfo;
}