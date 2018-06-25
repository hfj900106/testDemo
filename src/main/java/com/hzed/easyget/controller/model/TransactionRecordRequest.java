package com.hzed.easyget.controller.model;

import lombok.Data;

/**
 * 交易记录请求参数
 * @author hfj
 * @date 2018/6/22
 */
@Data
public class TransactionRecordRequest {
    private Integer pageNo = 0;
    private Integer pageSize = 5;
}
