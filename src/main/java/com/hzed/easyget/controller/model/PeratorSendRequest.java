package com.hzed.easyget.controller.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 运营商认证-发送验证码
 *
 * @author hfj
 * @date 2018/6/6
 */

@Data
public class PeratorSendRequest {
    @NotNull(message = "时间戳不能为空")
    private Long timeStamp;

}