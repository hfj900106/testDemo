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

    public static final String TOKEN = "token";

    /**
     * 失效时间
     */
    public static final Long THREE_HOUR = 10800L;

}