package com.hzed.easyget.controller.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 借款费用明细
 *
 * @author wuchengwu
 * @since 2018/8/21
 */
@Data
public class BidDetailFeeResponse {
    private BigDecimal authFee;
    private BigDecimal reviewFee;
    private BigDecimal handlingFee;
}