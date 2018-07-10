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
    IOS("ios", "苹果"),
    ANDROID_VERSION("android_version", "安卓版本号"),
    IOS_VERSION("ios_update", "苹果版本号"),
    ANDROID_UPDATE("android_update", "安卓版本号更新"),
    IOS_UPDATE("ios_update", "苹果版本号更新"),
    NOT_UPDATE("1", "不需要更新"),
    HAS_UPDATE("0", "需要更新");

    private String code;
    private String msg;

    AppVersionEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
