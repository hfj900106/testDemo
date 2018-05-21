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
public class SmsCodResponse {
    public Integer code;
    public Integer code1;
    public Integer code2;
    public Integer code3;
}