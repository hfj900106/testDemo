package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Facebook发送短信并验证成功后请求参数
 * @author hfj
 * @date 2018/8/18
 */
@Data
public class LoginByFacebookRequest {
    @NotBlank(message = "[mobile]不能为空")
    private String mobile;
    @NotBlank(message = "[md5]加密串不能为空")
    private String md5;
    @NotBlank(message = "[device]不能为空")
    private String device;
    @NotBlank(message = "[anonymousId]匿名id不能为空")
    private String anonymousId;
}