package com.hzed.easyget.persistence.ext.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author dengzhenhao
 * @since 2018/6/20 16:29
 */
@Data
public class TransactionExt {
    /**
     * 交易记录id
     */
    private Long transactionId;

    /**
     * 确认时间
     */
    private Date confirmTime;
}
