package com.hzed.easyget.controller.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author dengzhenhao
 * @since 2018/6/28 16:20
 */
@Data
public class VaHistoryRequest {

    @NotNull(message = "{param.repay.bidId.isNotEmpty}")
    private Long bidId;

    @Min(0) @NotNull(message = "{param.repay.pageNo.isNotEmpty}")
    private Integer page;

    @Min(10)@NotNull(message = "{param.repay.pageSize.isNotEmpty}")
    private Integer pageSize;
}
