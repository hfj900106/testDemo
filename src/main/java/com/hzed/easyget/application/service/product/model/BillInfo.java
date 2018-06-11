package com.hzed.easyget.application.service.product.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 账单明细
 * @author guichang
 */

@Data
public class BillInfo implements Serializable {

    /**
     * 期数
     */
    private Integer indexPeriod;

    /**
     * 应还时间
     */
    private LocalDateTime repaymentTime;

    /**
     * 应还金额
     */
    private BigDecimal repaymentAmount;

    /**
     * 还款类型
     */
    private BigDecimal repaymentType;

    /**
     * 还款状态
     */
    private Integer status;

}
