package com.hzed.easyget.application.enums;

import lombok.Getter;

@Getter
public enum AppVersionEnum {
    ANDROID("1","安卓"),
    IOS("2","苹果"),
    ANDROID_VERSION("android_version","安卓版本号"),
    IOS_VERSION("ios_update","苹果版本号"),
    ANDROID_UPDATE("android_update","安卓版本号更新"),
    IOS_UPDATE("ios_update","苹果版本号更新"),
    NOT_UPDATE("0","不需要更新"),
    HAS_UPDATE("1","需要更新");
    private String code;
    private String msg;

    AppVersionEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
