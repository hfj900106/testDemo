package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author wuchengwu
 * @since 2018/5/21
 */
@Data
public class LoginByCodeRequest {
//    @NotBlank(message = "手机号不能为空")
    @NotBlank(message = "{param.mobile.isNotEmpty}")
    private String mobile;
    @NotBlank(message = "短信验证码不能为空")
    private String smsCode;
    @NotBlank(message = "{param.device.isNotEmpty}")
    private String device;
}