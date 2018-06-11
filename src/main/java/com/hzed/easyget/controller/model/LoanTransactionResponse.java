package com.hzed.easyget.controller.model;

import lombok.Data;

/**
 * 支付放款返回参数
 *
 * @author wuchengwu
 * @data 2018/6/9
 */
@Data
public class LoanTransactionResponse {
    /**
     * 交易id
     */
    private String transactionId;
    /**
     * 转账请求状态
     */
    private String code;
    /**
     * 转账状态
     */
    private String transferStatus;

}