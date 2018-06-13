package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 试算接口请求参数
 *
 * @author wuchengwu
 * @data 2018/5/24
 */
@Data
public class LoanCalculateRequest {
    @NotBlank(message = "{param.home.loanAmount.isNotEmpty}")
    private String loanAmount;
}