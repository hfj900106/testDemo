package com.hzed.easyget.controller.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 还款详情请求参数
 *
 * @author wuchengwu
 * @data 2018/6/6
 */
@Data
public class RepayDetailRequest {
    @NotNull(message = "[bidId]不能为空")
    private Long bidId;
}