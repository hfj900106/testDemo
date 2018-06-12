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

    @NotBlank(message = "短信内容不能为空")
    private String message;
    @NotNull(message = "时间戳不能为空")
    private Long timeStamp;
}