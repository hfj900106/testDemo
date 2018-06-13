package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 展示部分还款请求参数
 *
 * @author wuchengwu
 * @data 2018/6/8
 */
@Data
public class RepayPartDetailRequest {
    @NotBlank(message = "{param.repay.bidId.isNotEmpty}")
    private String bidId;
}