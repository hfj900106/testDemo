package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 短信授权请求
 *
 * @author hfj
 * @date 2018/5/21
 */

@Data
public class MessagesRequest {

    @NotBlank(message = "{param.auth.message.isNotEmpty}")
    private String message;

}