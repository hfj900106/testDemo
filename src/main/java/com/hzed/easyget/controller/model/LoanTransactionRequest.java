package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 放款交易请求参数
 *
 * @author wuchengwu
 * @data 2018/6/9
 */
@Data
public class LoanTransactionRequest {
    /**
     * 固定值 MD5
     */
    private String encrypt = "MD5";
    /**
     * 交易id
     */
    private String transactionId;
    /**
     * 渠道id,固定值 100
     */
    private Integer promotionId = 100;
    /**
     * 收款所在国
     */
    @NotBlank(message = "[payeeCountry]收款所在国不能为空")
    private String payeeCountry;
    /**
     * 银行名称
     */
    @NotBlank(message = "[payeeBankName]银行名称不能为空")
    private String payeeBankName;
    /**
     * 收款方名字
     */
    @NotBlank(message = "[payeeName]收款方名字不能为空")
    private String payeeName;
    /**
     * 收款方账号
     */
    @NotBlank(message = "[payeeAccount]收款方账号不能为空")
    private String payeeAccount;
    /**
     * 收款方手机号
     */
    @NotBlank(message = "[payeeMsisdn]收款方手机号不能为空")
    private Integer payeeMsisdn;
    /**
     * 收款方类型,固定值 NORMAL
     */
    private String payeeType = "NORMAL";
    /**
     * 放款金额
     */
    @NotBlank(message = "[payeeName]放款金额不能为空")
    private Integer amount;
    /**
     * 币种值,固定值 IDR
     */
    private String currency = "IDR";
    /**
     * 产品id
     */
    @NotBlank(message = "[productId]产品id不能为空")
    private Integer productId;




}