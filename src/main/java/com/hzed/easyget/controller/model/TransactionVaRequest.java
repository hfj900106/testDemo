package com.hzed.easyget.controller.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

/**
 * @description：获取va码请求实体
 * @author：[zhangruilin]
 * @time：2018/6/18-11:06
 **/
@Data
public class TransactionVaRequest {
    /**
     * 标的id
     */
    @NotNull(message = "{param.repay.bidId.isNotEmpty}")
    private Long bidId;
    /**
     * 交易方式 ATM OTC
     */
    @Pattern(regexp = "^BNI ATM|OTC$", message = "{param.repay.mode.must}")
    private String mode;
    /**
     * 交易金额
     */
    @NotNull(message = "{param.repay.amount.isNotEmpty}")
    private BigDecimal amount;
    /**
     * 全部结清标识
     */
    @Pattern(regexp = "^true|false", message = "{param.repay.flag.must}")
    private boolean flag;
}
