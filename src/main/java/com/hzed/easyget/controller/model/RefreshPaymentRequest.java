package com.hzed.easyget.controller.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 刷新还款结果请求实体
 *
 * @author zhangrl
 * @date 2018/6/20
 */
@Data
public class RefreshPaymentRequest {

    @NotNull(message = "[payId]交易流水不能为空")
    private Long payId;
    @NotNull(message = "[expire]不能为空")
    private boolean expire;
}
