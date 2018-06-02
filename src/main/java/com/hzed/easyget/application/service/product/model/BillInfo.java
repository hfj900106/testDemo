package com.hzed.easyget.application.service.product.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 账单信息
 */

@Data
public class BillInfo implements Serializable {

    /**
     * 借款本金
     */
    private BigDecimal corpus;

    /**
     * 借款利息
     */
    private BigDecimal interest;

    /**
     * 借款本息
     */
    private BigDecimal corpusAndinterest;

    /**
     * 砍头息（快速信审费）-放款时收取
     */
    private BigDecimal headFee;

    /**
     * 尾款-还款时收取
     */
    private BigDecimal tailFee;

    /**
     * 账单列表
     */
    List<BillDetail> bills;

}
