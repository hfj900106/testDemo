package com.hzed.easyget.application.enums;

import lombok.Getter;

/**
 * @author wuchengwu
 * @date 2018/6/4
 */

@Getter
public enum ProgressTypeEnum {
    AUDIT("1","审核"),
    LOAN("2","放款"),
    CLEAR("3","结清");

    private String code;
    private String msg;

    ProgressTypeEnum(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

}
