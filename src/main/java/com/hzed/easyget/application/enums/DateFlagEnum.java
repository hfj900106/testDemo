package com.hzed.easyget.application.enums;

import lombok.Getter;

/**
 * 年月日
 *
 * @author guichang
 * @date 2018/6/6
 */

@Getter
public enum DateFlagEnum {
    /**
     * 过规则
     */
    D("D", "年"),
    M("M", "月"),
    Y("Y", "日");

    private String code;
    private String msg;

    DateFlagEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
