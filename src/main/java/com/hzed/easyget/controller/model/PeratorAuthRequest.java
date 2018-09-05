package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 验证码认证运营商
 *
 * @author hfj
 * @date 2018/6/6
 */

@Data
public class PeratorAuthRequest {

    @NotBlank(message = "[smsCode]不能为空")
    private String smsCode;
}