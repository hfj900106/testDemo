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
public class RefreshPayMentRequest {
    /**
     * 交易流水id(非交易id)
     */
    @NotNull(message = "{param.repay.payId.isNotEmpty}")
    private Long payId;
    /**
     * 到计时是否到期
     */
    @NotNull(message = "{param.repay.expire.isNotEmpty}")
    private boolean expire;
}
