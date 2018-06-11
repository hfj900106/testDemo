package com.hzed.easyget.controller.model;

import lombok.Data;

/**
 * 支付收款返回参数
 *
 * @author wuchengwu
 * @data 2018/6/9
 */
@Data
public class ReceiverTransactionResponse {

    /**
     * VA手续费
     */
    private Integer vaFee;
    /**
     * OTC手续费
     */
    private Integer otcFee;
    /**
     * 是否为静态VA
     */
    private Integer isStatic;
    /**
     * 交易状态码
     */
    private Integer status;
    /**
     * 还款码
     */
    private Integer PaymentCode;

}