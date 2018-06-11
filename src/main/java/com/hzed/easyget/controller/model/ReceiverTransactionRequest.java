package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 收款请求参数
 *
 * @author wuchengwu
 * @date  2018/6/9
 */
@Data
public class ReceiverTransactionRequest {
    /**
     * 交易id
     */
    private String transactionId;
    /**
     * 价格
     */
    @NotBlank(message = "价格不能为空")
    private String price;
    /**
     * 渠道id 固定1000
     */
    private Integer promotionId = 1000;
    /**
     * ui
     */
    private String ui = "none";
    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    private Integer msisdn;
    /**
     * 支付方式
     */
    @NotBlank(message = "支付方式不能为空")
    private String payType;
    /**
     * 银行类型
     */
    @NotBlank(message = "银行类型不能为空")
    private String bankType;
    /**
     * 产品id
     */
    @NotBlank(message = "产品id不能为空")
    private String productId;
}