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
    @NotNull(message = "[applyAmount]不能为空")
    private BigDecimal applyAmount;
    @NotNull(message = "[period]不能为空")
    private Integer period;
    @NotBlank(message = "[inBank]不能为空")
    private String inBank;
    @NotBlank(message = "[inAccount]不能为空")
    private String inAccount;
    @NotBlank(message = "[purposeId]不能为空")
    private String purposeId;
    @NotNull(message = "[authFee]不能为空")
    private BigDecimal authFee;
    @NotNull(message = "[reviewFee]不能为空")
    private BigDecimal reviewFee;
    @NotNull(message = "[handlingFee]不能为空")
    private BigDecimal handlingFee;


    public String getInAccount() {
        return inAccount.replaceAll(" ", "");
    }
}
