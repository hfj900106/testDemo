package com.hzed.easyget.application.enums;

import lombok.Getter;

/**
 * @author wuchengwu
 */

@Getter
public enum AmountEnum {
    AUDIT_FREE("0.15","快速信审费"),
    FINAL_PAYMENT("0.06","尾款"),
    TEN_PERCENT("0.02","百分之1"),

    ;

    private String num;
    private String des;

    AmountEnum(String num, String des){
        this.num = num;
        this.des = des;
    }
}
