package com.hzed.easyget.controller.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 返回交易记录
 * @author hfj
 * @date 2018/06/19
 */
@Data
public class TransactionVO {
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 交易状态 1-交易中 2-交易成功 3-交易失败
     */
    private Byte status;
    /**
     * 交易完成时间
     */
    private Long updateTime;
    /**
     * 交易备注
     */
    private String remark;
}
