package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

/**
 * 还款接口app请求后台实体
 */
@Data
public class RepaymentRequest {
    /**
     * 交易流水id(非交易id)
     */
    @NotNull(message = "{param.repay.payId.isNotEmpty}")
    private Long payId;
    /**
     * 交易方式 ATM OTC
     */
    @Pattern(regexp = "^ATM|OTC$", message = "{Nilai harus 'ATM' atau 'OTC'}")
    private String mode;
    /**
     * 还款金额
     */
    @NotBlank(message = "{param.repay.amount.isNotEmpty}")
    private BigDecimal amount;
    /**
     * base64Img 图片数组
     */
    @NotBlank(message = "{param.repay.base64Imgs.isNotEmpty}")
    private String[] base64Imgs;
    /**
     * 对应的图片后缀
     */
    @NotBlank(message = "{param.repay.picSuffixs.isNotEmpty}")
    private String[] picSuffixs;
}
