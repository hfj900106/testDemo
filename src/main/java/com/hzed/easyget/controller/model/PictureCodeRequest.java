package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author hfj
 * @date 2018/6/4
 */
@Data
public class PictureCodeRequest {
    @NotBlank(message = "{param.login.mobile.isNotEmpty}")
    private String mobile;
}
