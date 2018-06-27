package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author wuchengwu
 * @date 2018/5/21
 */
@Data
public class LoginByCodeRequest {
    @NotBlank(message = "{param.login.mobile.isNotEmpty}")
    private String mobile;
    @NotBlank(message = "{param.login.smsCode.isNotEmpty}")
    private String smsCode;
    @NotBlank(message = "{param.login.device.isNotEmpty}")
    private String device;
    @NotBlank(message = "{param.login.anonymousId.isNotEmpty}")
    private String anonymousId;
}