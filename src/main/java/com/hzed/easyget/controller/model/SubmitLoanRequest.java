package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 提交借款请求参数
 *
 * @author wuchengwu
 * @data 2018/6/7
 */
@Data
public class SubmitLoanRequest {
    @NotNull(message = "{param.loan.applyAmount.isNotEmpty}")
    private BigDecimal applyAmount;
    @NotNull(message = "{param.loan.period.isNotEmpty}")
    private Integer period;
    @NotBlank(message = "{param.loan.inBank.isNotEmpty}")
    private String inBank;
    @NotBlank(message = "{param.loan.inAccount.isNotEmpty}")
    private String inAccount;
    @NotBlank(message = "{param.loan.purposeId.isNotEmpty}")
    private String purposeId;

}
