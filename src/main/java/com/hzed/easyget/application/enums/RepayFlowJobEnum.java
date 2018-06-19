package com.hzed.easyget.application.enums;

import lombok.Getter;

/**
*@description：还款走信息流 枚举类
*@author：[zhangruilin]
*@time：2018/6/19-18:00
**/
@Getter
public enum RepayFlowJobEnum {
    ALL_CLEAR(1,"全部结清"),
    PARTIAL_CLEARANCE(2,"部分结清"),
    ON_LINE(1,"线上还款"),
    UNDER_LINE(2, "线下还款");

    private Integer code;
    private String msg;

    RepayFlowJobEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
