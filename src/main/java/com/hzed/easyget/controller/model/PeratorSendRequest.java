package com.hzed.easyget.controller.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 运营商认证-发送验证码
 *
 * @author hfj
 * @date 2018/6/6
 */

@Data
public class PeratorSendRequest {
    @NotNull(message = "{param.auth.timeStamp.isNotEmpty}")
    private Long timeStamp;

}