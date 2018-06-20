package com.hzed.easyget.infrastructure.consts;

/**
 * redis相关常量
 *
 * @author guichang
 * @since 2017/12/22
 */

public class RedisConsts {

    /**
     * redis key分隔符
     */
    public static String SPLIT = ":";

    public static final String SMS_CODE = "sms_code";
    public static final String PICTURE_CODE = "picture_code";
    public static final String IDENTITY_AUTH_CODE = "identity_auth_code";
    public static final String IDENTITY_SMS_CODE_SEND = "identity_sms_code_send";
    public static final String LOGIN_SMS_CODE_SEND = "login_sms_code_send";
    public static final String LOGIN_PIC_CODE_SEND = "login_pic_code_send";
    public static final String DICT_MODULE_CODE = "dict_module_code";

    public static final String TOKEN = "token";

    /**
     * token失效时间 单位秒
     */
    public static final Long THREE_HOUR = 10800L;

}