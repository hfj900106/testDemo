package com.hzed.easyget.application.enums;

import lombok.Getter;

/**
 * 交易类型
 *
 * @author guichang
 * @date 2018/6/6
 */

@Getter
public enum TransactionTypeEnum {
    /**
     * 过规则
     */
    IN(1, "入账"),
    OUT(2, "出账"),
    OTHER(3, "其他");

    private Integer code;
    private String msg;

    TransactionTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
