package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author hfj
 * @date 2018/6/18
 */
@Data
public class RegisterH5Request {
    @NotBlank(message = "{param.login.mobile.isNotEmpty}")
    private String mobile;
    @NotBlank(message = "{param.login.smsCode.isNotEmpty}")
    private String smsCode;
    @NotBlank
    private String fromCode;

}