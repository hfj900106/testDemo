package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;

/**
 * 提交借款请求参数
 *
 * @author wuchengwu
 * @data 2018/6/7
 */
@Data
public class SubmitLoanRequest {
    @NotBlank(message = "[applyAmount]借款金额不能为空")
    private BigDecimal applyAmount;
    @NotBlank(message = "[period]借款期限不能为空")
    private Integer period;
    @NotBlank(message = "[inBank]收款银行不能为空")
    private String inBank;
    @NotBlank(message = "[inAccount]收款账户不能为空")
    private String inAccount;
    @NotBlank(message = "[purposeId]借款理由不能为空")
    private String purposeId;

}
