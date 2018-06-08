package com.hzed.easyget.application.enums;

import lombok.Getter;

/**
 * 还款类型
 * @author guichang
 * @date 2018/6/8
 */

@Getter
public enum RepayTypeEnum {
    /**
     * 过规则
     */
    CLEAR(1, "全部结清"),
    PART(2, "部分还款");

    private Integer code;
    private String msg;

    RepayTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
