package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author hfj
 * @date 2018/6/18
 */
@Data
public class RegisterH5Request {
    @NotBlank(message = "[mobile]不能为空")
    private String mobile;
    @NotBlank(message = "[smsCode]不能为空")
    private String smsCode;
    private String fromCode;
}