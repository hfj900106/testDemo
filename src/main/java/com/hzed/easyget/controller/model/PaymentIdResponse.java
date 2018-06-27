package com.hzed.easyget.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

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
     * 交易流水id(非交易id)
     */
    @NotNull(message = "{param.repay.payId.isNotEmpty}")
    private Long payId;
}
