package com.hzed.easyget.application.enums;

import lombok.Getter;

/**
 * 标的处理进度表处理类型
 * @author wuchengwu
 * @date 2018/6/4
 */

@Getter
public enum BidProgressTypeEnum {
    /** 过规则 */
    AUDIT("1","审核"),
    LOAN("2","放款"),
    CLEAR("3","结清");

    private String code;
    private String msg;

    BidProgressTypeEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

}
