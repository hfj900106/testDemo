package com.hzed.easyget.controller.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 查看还款信息请求实体
 *
 * @author zhangrl
 * @date 2018/6/22
 */
@Data
public class LoanManagInfoRequest {

    @NotNull(message = "[payId]交易流水id不能为空")
    private Long payId;
}
