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

    @NotBlank(message = "[message]不能为空")
    private String message;
    /**
     * 1 标识安卓，2 标识IOS
     */
    @NotNull(message = "[source]不能为空")
    private Integer source;

}