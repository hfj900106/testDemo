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
    TIMEOUT("timeout", "请求超时"),

    // 9-系统服务异常
    UNKNOWN_EXCEPTION("9999", "系统未知错误"),
    SERVICE_EXCEPTION("9998", "内部服务处理异常"),
    ILLEGAL_REQUEST("9997", "无效的请求"),
    ALIYUN_EXCEPTION("9996", "阿里云操作异常"),

    // 8-支付交易
    NOT_ENOUGH_MONEY("8001", "用户余额不足"),
    LOAN_TRANSACTION_ERROR("8002", "支付放款接口异常"),
    RECEIVER_TRANSACTION_ERROR("8003", "支付收款接口异常"),
    OVER_REPAYMENT_MONEY("8004", "还款金额大于项目待还总额"),
    REPAYMENTS("0001","还款处理中"),
    PROCESS_LENDING("0002","放款处理中"),



    // 1-请求业务校验
    ILLEGAL_PARAM("1000", "请求参数非法"),
    ILLEGAL_APPKEY("1001", "无效的appKey"),
    ILLEGAL_PLATFORM("1002", "无效的platform"),
    ILLEGAL_VERSION("1003", "无效的version"),
    ILLEGAL_TOKEN("1004", "无效的token"),
    ILLEGAL_I18N("1005", "无效的i18n"),
    ILLEGAL_IMEI("1006", "无效的imei"),

    ILLEGAL_SMSCODE("1007", "无效的验证码"),
    ERROR_SMS_RESULT("1008", "运营商认证发送验证码返回数据异常"),
    ERROR_IDCARD_RESULT("1009", "身份证识别返回数据异常"),
    DICT_NOTEXISTS("1010", "字典不存在"),
    NO_USEFUL_PRODUCT("1011", "无可用产品"),
    TOKEN_EXPIRE("1012", "用户登录信息失效"),
    PIC_CODE_ERROR("1013", "图片验证码错误"),
    UN_IDENTITY_AUTH("1014", "用户未进行身份认证"),
    OVER_TIME_SMS_CODE("1015", "验证码超时"),
    FREQUENTLY_SMS_SEND("1016", "短信发送过于频繁"),
    SMS_CODE_SEND_FAIL("1017", "短信发送失败"),
    PIC_CODE_TO_CHECK("1018", "需要图片验证码校验"),
    BID_EXISTS("1019", "有未结清标的"),
    ILLEGAL_LEDGER_TYPE("1020", "账单ID：{0}的台账类型{1}不存在"),
    ERROR_RISK__RESULT("1021", "风控返回数据异常"),

    FAIL_IDCARD_RECOGNITION("1022", "身份证识别失败"),
    FAIL_FACE_RECOGNITION("1023", "人脸识别失败"),
    FAIL_IDENTITY_AUTH("1024", "身份信息认证失败"),
    FAIL_PROFESSIONAL_AUTH("1025", "专业信息认证失败"),
    FAIL_AUTH("1026", "认证失败"),
    ILLEGAL_BIDID("1027", "标ID：{0}不存在"),
    NOT_INDONESIA_PRODUCT("1028", "标ID：{0}不是印尼产品"),
    ILLEGAL_LEDGER("1029", "账单ID：{0}不存在台账"),
    ILLEGAL_BILLID("1030", "账单ID：{0}不存在"),
    NOT_EXIST_BILL("1031",  "标ID：{0}的账单不存在"),
    INSUFFICIENT_QUOTA("1032","名额不足"),
    UN_LOAN_QUALIFICATION("1033","无贷款资格"),
    NEED_JUMP("1034","需要跳转"),
    EXIST_USER("1035","用户已经存在")

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
