package com.hzed.easyget.application.enums;

import lombok.Getter;

/**
 * 账单表状态
 *
 * @author guichang
 * @date 2018/6/6
 */

@Getter
public enum BillStatusEnum {
    /**
     * 过规则
     */
    WAIT_CLEAR(1, "待结清"),
    NORMAL_CLEAR(2, "正常结清"),
    OVERDUE_CLEAR(3, "逾期结清");

    private Integer code;
    private String msg;

    BillStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
