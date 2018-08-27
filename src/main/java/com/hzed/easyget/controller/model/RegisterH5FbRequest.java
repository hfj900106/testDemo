package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author hfj
 * @date 2018/6/18
 */
@Data
public class RegisterH5FbRequest {
    @NotBlank(message = "{param.login.mobile.isNotEmpty}")
    private String mobile;

    private String fromCode;
}