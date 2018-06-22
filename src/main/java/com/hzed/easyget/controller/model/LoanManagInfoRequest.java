package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 查看还款信息请求实体
 *
 * @author zhangrl
 * @date 2018/6/22
 */
@Data
public class LoanManagInfoRequest {
    /**
     * 交易流水id
     */
    @NotBlank(message = "{param.loan.payId.isNotEmpty}")
    private Long payId;
}