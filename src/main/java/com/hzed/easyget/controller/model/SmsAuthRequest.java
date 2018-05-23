package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 运营商认证
 *
 * @author hfj
 * @date 2018/5/23
 */

@Data
public class SmsAuthRequest {

    @NotBlank(message = "[mobile]不能为空")
    private String mobile;
    @NotBlank(message = "[serverKey]不能为空")
    private String serverKey;

}