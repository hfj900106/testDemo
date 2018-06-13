package com.hzed.easyget.controller.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 全部结清
 *
 * @author guichang
 * @date 2018/6/6
 */

@Data
public class RepayAllRequest {
    @NotNull(message = "{param.repay.bidId.isNotEmpty}")
    private Long bidId;
}