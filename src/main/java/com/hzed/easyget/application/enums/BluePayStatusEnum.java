package com.hzed.easyget.application.enums;

import lombok.Getter;

/**
 * bluepay status/transferStatus枚举
 *
 * @author madaijun
 * @date 2018/5/21
 */
@Getter
public enum BluePayStatusEnum {
    /**
     * 过规则
     */
    OK("201", "请求 BluePay 成功，表示订单在 BluePay 侧成功建立。"),
    BLUE_PAY_COMPLETE("200", "BluePay 侧交易完成。"),
    REUEST_BANK_FAILURE("600", "请求银行失败。"),
    PARAM_ERROR("400", "参数错误，缺少参数。"),
    ENCRYPT_ERROR("401", "签名错误/加密错误。"),
    REQUEST_BANK_TIMEOUT("501", "银行请求超时，交易失败。可重新发起交易。"),
    IP_RESTRICT("506", "IP 限制。"),
    DEAL_NOT_EXISTS("404", "信息未找到，交易信息未找到。"),
    BLUE_PAY_INNER_ERROR("500", "bluepay服务内部错误。"),
    MERCHANT_BALANCE_SHORTAGE("601", "商户余额不足，放款备付金不足。请联系 BluePay 商务储值。 测试环境请联系技术服务。"),
    BANK_DEAL_FAILURE("646", "银行处理失败。"),
    BANK_ACCOUNT_ERROR("649", "银行账户信息错误，账号不对。");

    private String key;
    private String value;

    BluePayStatusEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValueDesc(String key) {
        BluePayStatusEnum[] values = BluePayStatusEnum.values();
        for (BluePayStatusEnum bEnum : values) {
            if (bEnum.getKey().equals(key)) {
                return bEnum.getValue();
            }
        }
        return "未知的枚举值";
    }

}
