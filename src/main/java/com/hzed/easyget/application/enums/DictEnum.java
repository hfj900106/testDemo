package com.hzed.easyget.application.enums;

import lombok.Getter;

@Getter
public enum DictEnum {
    /**
     * 字典表枚举
     */
    AUTH_MODULE_CODE("auth_module_code","认证");

    private String code;
    private String msg;

    DictEnum(String code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
