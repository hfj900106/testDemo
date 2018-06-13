package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 验证码认证运营商
 *
 * @author hfj
 * @date 2018/6/6
 */

@Data
public class PeratorAuthRequest {

    @NotBlank(message = "{param.auth.smsCode.isNotEmpty}")
    private String smsCode;
    @NotNull(message = "{param.auth.timeStamp.isNotEmpty}")
    private Long timeStamp;

}