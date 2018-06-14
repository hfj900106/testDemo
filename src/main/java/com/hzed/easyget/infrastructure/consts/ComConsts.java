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
    public static final int RISK_OK = 0;
    public static final String RISK_CODE = "code";
    public static final int IS_ANDROID = 1;
    public static final int IS_IOS = 2;
    /**
     * 推送风控定时任务名
     */
    public static final String PUSH_RISK_TASK="pushBid";
    /**
     * 推送放款定时任务名
     */
    public static final String PUSH_BANK_TASK="bankLoan";
    /**
     * 放款回调
     */
    public  static final String CASHOUT="cashout";
    /**
     * 还款回调
     */
    public  static final String BANK="bank";


}