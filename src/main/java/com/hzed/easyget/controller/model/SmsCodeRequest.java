package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 短信验证码发送请求
 *
 * @author hfj
 * @date 2018/5/21
 */

@Data
public class SmsCodeRequest {

    @NotBlank(message = "{param.login.mobile.isNotEmpty}")
    private String mobile;

}