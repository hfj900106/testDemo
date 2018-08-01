package com.hzed.easyget.application.enums;

import lombok.Getter;

/**
 * @author wuchengwu
 */

@Getter
public enum AppVersionEnum {
    /**
     * 过规则
     */
    ANDROID("android", "安卓"),
    NO_NEED_TO_UPDATE("1", "不需要更新"),
    NEED_TO_BE_UPDATED("0", "需要更新"),
    NO_MANDATORY_UPDATES_ARE_REQUIRED("1", "不需要强制更新"),
    NEED_TO_FORCE_AN_UPDATE("0", "需要强制更新");

    private String code;
    private String msg;

    AppVersionEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
