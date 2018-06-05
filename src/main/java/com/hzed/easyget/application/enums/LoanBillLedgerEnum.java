package com.hzed.easyget.application.enums;

import lombok.Getter;

/**
 * @author wuchengwu
 * @date 2018/6/4
 */

@Getter
public enum LoanBillLedgerEnum {

    CORPUS("1","本金"),
    INTEREST("2","利息"),
    AUDIT("3","砍头息"),
    OVERDUE("5","逾期费");

    private String type;
    private String msg;

    LoanBillLedgerEnum(String type,String msg){
        this.type = type;
        this.msg = msg;
    }

}
