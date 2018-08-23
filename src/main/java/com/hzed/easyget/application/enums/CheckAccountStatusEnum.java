package com.hzed.easyget.application.enums;

import lombok.Getter;

/**
 * 校验银行卡信息枚举类
 * @author wuchengwu
 * @date 2018/8/20
 */
@Getter
public enum CheckAccountStatusEnum {

    /**
     * 过规则
     */
    OK("200", "请求 BluePay 成功，表示订单在 BluePay 侧成功建立。"),
    BANK_NOT_EXISTS("687", "校验失败，找不到匹配的银行。"),
    CUSTOMER_NAME_NOT_EXISTS("692", "校验失败，用户名称不匹配。"),
    ACCOUNT_NOT_EXISTS("602", "校验失败，没有找到这个账户。"),
    ACCOUNT_ERROR("608", "校验失败，银行账户异常。");

    private String key;
    private String value;

    CheckAccountStatusEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

}
