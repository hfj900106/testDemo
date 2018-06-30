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
    ID_CARD("auth_code_01", "身份证认证"),
    CONTACTS("auth_code_02", "通讯录授权"),
    MESSAGE("auth_code_03", "短信授权"),
    PERSON_INFO("auth_code_04", "个人信息认证"),
    SMS("auth_code_05", "运营商认证"),
    FACEBOOK("auth_code_06", "Facebook认证"),
    INS("auth_code_07", "ins认证"),
    PROFESSIONAL("auth_code_08", "专业信息认证");

    private String code;
    private String msg;

    AuthCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
