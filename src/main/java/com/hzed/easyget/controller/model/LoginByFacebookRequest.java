package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Facebook发送短信并验证成功后请求参数
 * @author hfj
 * @date 2018/8/18
 */
@Data
public class LoginByFacebookRequest {
    @NotBlank(message = "{param.login.mobile.isNotEmpty}")
    private String mobile;
    @NotBlank(message = "{param.login.aesString.isNotEmpty}")
    private String aesString;
    @NotBlank(message = "{param.login.device.isNotEmpty}")
    private String device;
    @NotBlank
    private String anonymousId;
}