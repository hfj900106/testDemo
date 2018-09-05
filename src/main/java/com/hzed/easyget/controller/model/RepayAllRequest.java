package com.hzed.easyget.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 全部结清
 *
 * @author guichang
 * @date 2018/6/6
 */

@Data
@AllArgsConstructor
public class RepayAllRequest {
    @NotNull(message = "[bidId]不能为空")
    private Long bidId;
}