package com.hzed.easyget.application.enums;

import lombok.Getter;

/**
 * @author wuchengwu
 */

@Getter
public enum RepayStatusEnum {
    /** 过规则 */
    UN_REPAY(2,"未逾期未还"),
    OVDUE_UN_REPAY(3,"逾期未还"),
    CLEAR_REPAY(4,"已结清");

    private Integer code;
    private String msg;

    RepayStatusEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }


}
