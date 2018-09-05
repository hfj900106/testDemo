package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author wuchengwu
 * @date 2018/5/21
 */
@Data
public class LoginByCodeRequest {
    @NotBlank(message = "[mobile]不能为空")
    private String mobile;
    @NotBlank(message = "[smsCode]不能为空")
    private String smsCode;
    @NotBlank(message = "[device]不能为空")
    private String device;
    @NotBlank(message = "[anonymousId]匿名id不能为空")
    private String anonymousId;
}