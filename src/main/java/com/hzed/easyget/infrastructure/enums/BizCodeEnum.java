package com.hzed.easyget.infrastructure.enums;

import lombok.Getter;

/**
 * 错误枚举
 *
 * @author guichang
 */
@Getter
public enum BizCodeEnum {

    // 0-成功
    SUCCESS("0000", "受理成功"),

    // 9-系统服务异常
    UNKNOWN_EXCEPTION("9999", "系统未知错误"),
    SERVICE_EXCEPTION("9998", "内部服务处理异常"),
    INVALID_REQUEST("9997", "无效的请求"),

    //1-请求业务校验
    REQUEST_PARAM_ILLEGAL("1001", "请求参数非法");

    /**
     * 错误码
     */
    private final String code;

    /**
     * 错误信息
     */
    private final String message;

    BizCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
