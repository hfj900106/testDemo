package com.hzed.easyget.application.enums;

import lombok.Getter;

/**
*@description：还款交易记录表枚举类
*@author：[zhangruilin]
*@time：2018/6/18-14:50
**/
@Getter
public enum  TransactionRepayEnum {
    TO_BE_TREATED(1,"待处理"),
    PROCESS_PROCESSING(2,"处理中"),
    PROCESS_SUCCESS(3,"处理成功"),
    PROCESS_FAIL(4,"处理失败");

    private Integer code;
    private String msg;

    TransactionRepayEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
