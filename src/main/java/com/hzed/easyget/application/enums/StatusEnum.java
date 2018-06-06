package com.hzed.easyget.application.enums;

import lombok.Getter;

/**
 * @author wuchengwu
 */

@Getter
public enum StatusEnum {
    /** 过规则 */
    ENABLE("1", "可用"),
    DISENABLE("0", "不可用");

    private String code;
    private String msg;

    StatusEnum(String code,String msg){
        this.code = code;
        this.msg = msg;
    }
    
    
}
