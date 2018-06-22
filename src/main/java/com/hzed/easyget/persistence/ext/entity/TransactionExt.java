package com.hzed.easyget.persistence.ext.entity;

import lombok.Data;

import java.time.LocalDateTime;
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
    private Long id;

    /**
     * 确认时间
     */
    private LocalDateTime confirmTime;

    /**
     * 交易状态
     */
    private Byte status;
}
