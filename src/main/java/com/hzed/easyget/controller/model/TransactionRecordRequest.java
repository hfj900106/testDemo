package com.hzed.easyget.controller.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 交易记录请求参数
 * @author hfj
 * @date 2018/6/22
 */
@Data
public class TransactionRecordRequest {
    @NotNull
    private Integer pageNo = 0;
    @NotNull
    private Integer pageSize = 5;
}
