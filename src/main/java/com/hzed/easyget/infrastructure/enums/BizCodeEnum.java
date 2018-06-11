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
    ILLEGAL_REQUEST("9997", "无效的请求"),
    ALIYUN_EXCEPTION("9996", "阿里云操作异常"),

    // 8-交易码
    sahfsdkfsahd("8001", "用户余额不足"),
    LOAN_TRANSACTION_ERROR("8002","支付放款接口异常"),
    RECEIVER_TRANSACTION_ERROR("8003","支付收款接口异常"),


    // 1-请求业务校验
    ILLEGAL_PARAM("1000", "请求参数非法"),
    ILLEGAL_APPKEY("1001", "无效的appKey"),
    ILLEGAL_PLATFORM("1002", "无效的platform"),
    ILLEGAL_VERSION("1003", "无效的version"),
    ILLEGAL_TOKEN("1004", "无效的token"),
    ILLEGAL_I18N("1005", "无效的i18n"),
    ILLEGAL_IMEI("1006", "无效的imei"),

    ILLEGAL_SMSCODE("1007", "无效的验证码"),
    ERROR_SMSCODE("1008","验证码错误或已过期"),
    USER_NOTEXISTS("1009", "用户不存在"),
    DICT_NOTEXISTS("1010", "字典不存在"),
    NO_USEFUL_PRODUCT("1011", "无可用产品"),
    TOKEN_EXPIRE("1012", "用户登录信息失效"),
    ILLEGAL_PICTURECODE("1013", "图片验证码错误"),
    UN_IDENTITY_AUTH("1014", "用户未进行身份认证"),
    OVER_TIME_SMS_CODE("1015", "验证码超时"),
    FREQUENTLY_SEND("1016", "短信发送过于频繁"),
    SMS_CODE_SEND_FAIL("1017", "短信发送失败"),
    PIC_CODE_TO_CHECK("1018", "需要图片验证码校验"),

    // 2-上传文件异常
    FILE_FORMAT_ERROR("","此格式不支持"),
    FILE_NULL_ERROR("","文件不能为空"),

    ;


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
