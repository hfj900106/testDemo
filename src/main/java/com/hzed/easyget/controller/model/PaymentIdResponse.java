package com.hzed.easyget.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 返回交易流水号
 *
 * @author zhangrl
 * @date 2018/6/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentIdResponse {
    /**
     * 标的id
     */
    private Long bidId;
    /**
     * 还款金额
     */
    private BigDecimal amount;
    /**
     * 应还时间
     */
    private Long repaymentTime;
    /**
     * 全部结清标识
     */
    private boolean flag;
    /**
     * va码
     */
    private String vaCode;
    /**
     * va码过期时间
     */
    private Long expireTime;
    /**
     * 还款方式
     */
    private String mode;
}
