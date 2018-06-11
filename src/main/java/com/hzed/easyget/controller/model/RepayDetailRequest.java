package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 还款详情请求参数
 *
 * @author wuchengwu
 * @data 2018/6/6
 */
@Data
public class RepayDetailRequest {
    @NotBlank(message = "bid不能为空")
    private Long bid;
}