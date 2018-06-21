package com.hzed.easyget.persistence.ext.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hzed.easyget.infrastructure.utils.DateUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    @JsonIgnore
    private LocalDateTime createTime;

    private String createTimeStr;

    public String getCreateTimeStr(){
        return DateUtil.localDateTimeToStr1(this.createTime);
    }

    /**
     * 还款方式
     */
    private String mode;

    /**
     * 对应账单的应还时间
     */
    private String repaymentTime;

    /**
     * 应还金额
     */
    private BigDecimal amount;

    /**
     * va码是否过期
     */
    private Boolean vaExpire;
}
