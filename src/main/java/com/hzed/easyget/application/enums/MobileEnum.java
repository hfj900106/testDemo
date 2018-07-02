package com.hzed.easyget.application.enums;

import lombok.Getter;

/**
 * 手机类型枚举
 *
 * @author zhangrl
 */
@Getter
public enum MobileEnum {
    /**
     * 过规则
     */
    CHINA("86", "国内手机号前缀"),
    IDR("62", "印尼手机号前缀");
    public String mobile;
    public String desc;

    MobileEnum(String mobile, String desc) {
        this.mobile = mobile;
        this.desc = desc;
    }

}
