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

    @NotBlank(message = "验证码不能为空")
    private String smsCode;
    @NotNull(message = "时间戳不能为空")
    private Long timeStamp;

}