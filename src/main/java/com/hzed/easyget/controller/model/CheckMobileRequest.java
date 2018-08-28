package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author guichang
 */
@Data
public class CheckMobileRequest {

    @NotBlank(message = "{param.login.mobile.isNotEmpty}")
    private String mobile;

}