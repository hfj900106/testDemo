package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 短信授权请求
 *
 * @author hfj
 * @date 2018/5/21
 */

@Data
public class MessagesRequest {

    @NotBlank(message = "[message]不能为空")
    private String message;

}