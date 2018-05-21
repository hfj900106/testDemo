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
public class SmsCodRequest {

    @NotBlank(message = "[mobile]不能为空")
    private String mobile;

}