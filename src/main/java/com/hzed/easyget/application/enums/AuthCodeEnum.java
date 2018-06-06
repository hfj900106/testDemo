package com.hzed.easyget.application.enums;

import lombok.Getter;

/**
 * 认证枚举类
 * @author hfj
 * @date 2018/5/23
 */

@Getter
public enum AuthCodeEnum {
    /** 过规则 */
    ID_CARD("001", "身份证认证"),
    CONTACTS("002", "通讯录授权"),
    MESSAGE("003", "短信授权"),
    PERSON_INFO("004", "个人信息认证"),
    SMS("005", "运营商认证"),
    FACEBOOK("006", "Facebook认证"),
    INS("007", "ins认证"),
    PROFESSIONAL("008", "专业信息认证");

    private String code;
    private String msg;

    AuthCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
