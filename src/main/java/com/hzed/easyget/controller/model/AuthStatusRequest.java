package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 认证请求参数
 *
 * @author wuchengwu
 * @date 2018/6/29
 */
@Data
public class AuthStatusRequest {
    @NotBlank(message = "[code]不能为空")
    private String code;
}