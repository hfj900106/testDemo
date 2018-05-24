package com.hzed.easyget.controller.model;

import lombok.Builder;
import lombok.Data;

/**
 * 短信验证码返回
 *
 * @author hfj
 * @date 2018/5/21
 */

@Builder
@Data
public class SmsCodeResponse {
    public Integer code;
}