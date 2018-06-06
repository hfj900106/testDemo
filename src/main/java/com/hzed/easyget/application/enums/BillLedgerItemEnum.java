package com.hzed.easyget.application.enums;

import lombok.Getter;

/**
 * 台账类型
 * @author wuchengwu
 * @date 2018/6/4
 */

@Getter
public enum BillLedgerItemEnum {

    /** 过规则 */
    CORPUS("1","本金"),
    INTEREST("2","利息"),
    TAIL_FEE("3","尾款"),
    OVERDUE_FEE("4","逾期费");

    private String type;
    private String msg;

    BillLedgerItemEnum(String type, String msg){
        this.type = type;
        this.msg = msg;
    }

}
