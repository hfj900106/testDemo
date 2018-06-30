package com.hzed.easyget.infrastructure.consts;

/**
 * 公共常量
 *
 * @author guichang
 * @since 2017/12/22
 */

public class ComConsts {
    public static final String I18N = "i18n";
    public static final String APPKEY = "appKey";
    public static final String PLATFORM = "platform";
    public static final String TOKEN = "token";
    public static final String VERSION = "version";
    public static final String IMEI = "imei";
    public static final int EXPIRE_DAYS = 7;
    public static final int APP_PAGESIZE = 18;
    public static final String RISK_OK = "ok";
    public static final String RISK_CODE = "code";
    public static final String RISK_OPERATOR_HAVE_SEND = "2";
    public static final String RISK_OPERATOR_FREQ = "2.1";
    public static final String RISK_OPERATOR_ERROR = "3";
    public static final String RISK_OPERATOR_PARAMS_ERROR = "1";
    public static final String RISK_OPERATOR_HAVE_AUTH = "0000";
    public static final String H5 = "H5";
    /**
     * 印尼身份证上的性别-女
     */
    public static final String FEMALE = "PEREMPUAN";
    /**
     * 短信发送渠道字典code
     */
    public static final String SMS_DICT_CODE = "sms_01";
    /**
     * 牛信
     */
    public static final String NX = "NX";
    public static final int BULK_SMS_OK = 0;
    public static final int IS_ANDROID = 1;
    public static final int IS_IOS = 2;
    /**
     * 推送风控定时任务名
     */
    public static final String PUSH_RISK_TASK = "pushBid";
    /**
     * 推送放款定时任务名
     */
    public static final String PUSH_BANK_TASK = "bankLoan";
    /**
     * 处理还款失败定时任务名
     */
    public static final String REPAY_DAIL_TASK = "repayFail";
}