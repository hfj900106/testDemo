package com.hzed.easyget.persistence.ext.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author guichang
 * @since 2018/8/23
 */
@Data
public class UserTransactionExt {
    /**
     * 标id
     */
    private Long bidId;
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
     * 银行卡后四位
     */
    private String bankAccount;
    /**
     * 交易类型 1-入账 2-出账 3-其他
     */
    private Byte type;
}
