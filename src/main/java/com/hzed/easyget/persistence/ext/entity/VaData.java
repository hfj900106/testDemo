package com.hzed.easyget.persistence.ext.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dengzhenhao
 * @since 2018/6/20 18:18
 */
@Data
public class VaData {
    /**
     * 交易流水id
     */
    private Long transactionId;

    /**
     * va码
     */
    private String vaCode;

    /**
     * va码创建时间
     */
    private Date createTime;

    /**
     * 还款方式
     */
    private String mode;

    /**
     * 对应账单的应还时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date repaymentTime;

    /**
     * 应还金额
     */
    private BigDecimal amount;

    /**
     * va码是否过期
     */
    private Boolean vaExpire;
}
