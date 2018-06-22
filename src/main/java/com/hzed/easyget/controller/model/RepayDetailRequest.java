package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 还款详情请求参数
 *
 * @author wuchengwu
 * @data 2018/6/6
 */
@Data
public class RepayDetailRequest {
    @NotNull(message = "{param.repay.bidId.isNotEmpty}")
    private Long bidId;
}