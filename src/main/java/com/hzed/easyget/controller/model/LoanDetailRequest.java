package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 借款详情请求参数
 *
 * @author wuchengwu
 * @data 2018/6/7
 */
@Data
public class LoanDetailRequest {
    @NotBlank(message = "bid不能为空")
    private String bid;
}