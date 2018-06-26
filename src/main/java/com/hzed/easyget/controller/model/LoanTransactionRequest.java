package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;

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
     * 收款所在国
     */
    private String payeeCountry="ID";
    /**
     * 银行名称
     */
    private String payeeBankName;
    /**
     * 收款方名字
     */
    private String payeeName;
    /**
     * 收款方账号
     */
    private String payeeAccount;
    /**
     * 收款方手机号
     */
    private String payeeMsisdn;
    /**
     * 收款方类型,固定值 NORMAL
     */
    private String payeeType = "NORMAL";
    /**
     * 放款金额
     */
    private BigDecimal amount;
    /**
     * 币种值,固定值 IDR
     */
    private String currency = "IDR";
    /**
     * 流水号
     */
    private String requestNo;




}