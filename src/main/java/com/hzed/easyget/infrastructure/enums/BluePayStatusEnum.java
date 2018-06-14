package com.hzed.easyget.infrastructure.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * bluepay status/transferStatus枚举
 * <p>Title: BluePayStatusEnum</p>
 * @author  madaijun
 * @date    2018年5月21日 下午17:08:12
 */
public enum BluePayStatusEnum {
    /**请求 BluePay 成功，表示订单在 BluePay 侧成功建立。*/
    OK("201", "请求 BluePay 成功，表示订单在 BluePay 侧成功建立。"),
    /**BluePay 侧交易完成。*/
    BLUE_PAY_COMPLETE("200", "BluePay 侧交易完成。"),
    /**请求银行失败。*/
    REUEST_BANK_FAILURE("600", "请求银行失败。"),
    /**参数错误，缺少参数。*/
    PARAM_ERROR("400", "参数错误，缺少参数。"),
    /**签名错误/加密错误。*/
    ENCRYPT_ERROR("401", "签名错误/加密错误。"),
    /**银行请求超时，交易失败。可重新发起交易。*/
    REQUEST_BANK_TIMEOUT("501", "银行请求超时，交易失败。可重新发起交易。"),
    /**IP 限制。*/
    IP_RESTRICT("506", "IP 限制。"),
    /**信息未找到，交易信息未找到。*/
    DEAL_NOT_EXISTS("404", "信息未找到，交易信息未找到。"),
    /**bluepay服务内部错误。*/
    BLUE_PAY_INNER_ERROR("500", "bluepay服务内部错误。"),
    /**商户余额不足，放款备付金不足。请联系 BluePay 商务储值*/
    MERCHANT_BALANCE_SHORTAGE("601", "商户余额不足，放款备付金不足。请联系 BluePay 商务储值。 测试环境请联系技术服务。"),
    /**银行处理失败*/
    BANK_DEAL_FAILURE("646", "银行处理失败。"),
    /**银行账户信息错误，账号不对。*/
    BANK_ACCOUNT_ERROR("649", "银行账户信息错误，账号不对。");

    private String key;
    private String value;
    private static Map<String , BluePayStatusEnum> map = new HashMap();

    static {
        map.put(OK.key, OK);
        map.put(BLUE_PAY_COMPLETE.key, BLUE_PAY_COMPLETE);
        map.put(REUEST_BANK_FAILURE.key, REUEST_BANK_FAILURE);
        map.put(PARAM_ERROR.key, PARAM_ERROR);
        map.put(ENCRYPT_ERROR.key, ENCRYPT_ERROR);
        map.put(REQUEST_BANK_TIMEOUT.key, REQUEST_BANK_TIMEOUT);
        map.put(IP_RESTRICT.key, IP_RESTRICT);
        map.put(DEAL_NOT_EXISTS.key, DEAL_NOT_EXISTS);
        map.put(BLUE_PAY_INNER_ERROR.key, BLUE_PAY_INNER_ERROR);
        map.put(MERCHANT_BALANCE_SHORTAGE.key, MERCHANT_BALANCE_SHORTAGE);
        map.put(BANK_DEAL_FAILURE.key, BANK_DEAL_FAILURE);
        map.put(BANK_ACCOUNT_ERROR.key, BANK_ACCOUNT_ERROR);
    }

    BluePayStatusEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static BluePayStatusEnum getPayStatusEnum(String key) {
        return map.get(key);
    }
}
