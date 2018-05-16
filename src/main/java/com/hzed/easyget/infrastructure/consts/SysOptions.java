package com.hzed.easyget.infrastructure.consts;

/**
 * 平台参数
 *
 * @author guichang
 * @since 2017/12/31
 */

public class SysOptions {
    //SELECT (@i:=@i+1)-1 AS rownum,t1.* FROM t_system_options t1,(SELECT @i:=0) t2 ORDER BY t1.id ASC;
    // 初始信用额度
    public static String initialAmount = "credit_initial_amount";
    // 注册关键字否定词
    public static String keywords = "register_neg";

    public static String borrowFee = "borrow_fee";
    public static String borrowFeeMonth = "borrow_fee_month";
    public static String borrowFeeRate = "borrow_fee_rate";
    public static String borrowFeeDay = "borrow_fee_day";
    public static String investmentFee = "investment_fee";
    public static String overdueFee = "overdue_fee";

}