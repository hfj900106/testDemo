package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author hfj
 * @date 2018/6/4
 */
@Data
public class CheckPictureCodeRequest {
    @NotBlank(message = "手机号不能为空")
    private String mobile;
    @NotBlank(message = "验证码不能为空")
    private String code;
}
