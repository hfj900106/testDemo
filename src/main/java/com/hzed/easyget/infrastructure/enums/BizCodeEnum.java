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
    SUCCESS("0000", "处理成功"),

    // 9-系统服务异常
    UNKNOWN_EXCEPTION("9999", "系统未知错误"),
    SERVICE_EXCEPTION("9998", "内部服务处理异常"),
    INVALID_REQUEST("9997", "无效的请求"),

    // 1-请求业务校验
    ILLEGAL_PARAM("1000", "请求参数非法"),
    ILLEGAL_APPKEY("1001", "无效的appKey"),
    ILLEGAL_PLATFORM("1002", "无效的platform"),
    ILLEGAL_VERSION("1003", "无效的version"),
    ILLEGAL_TOKEN("1004", "无效的token"),
    ILLEGAL_I18N("1005", "无效的i18n"),
    ERROR_SMSCODE("1006","验证码错误或已过期"),
    USER_NOTEXISTS("1007", "用户不存在"),
    DICT_NOTEXISTS("1008", "字典不存在"),
    PRODUT_NOTEXISTS("1009", "当前产品不可用");

    // 20-

    // 21-

    // 3-


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
