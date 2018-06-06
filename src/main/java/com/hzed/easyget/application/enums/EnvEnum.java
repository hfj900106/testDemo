package com.hzed.easyget.application.enums;

import lombok.Getter;

/**
 * @author guichang
 */

@Getter
public enum EnvEnum {
    /** 过规则 */
    DEV("dev", "开发环境", true),
    TEST("test", "测试环境", true),
    PROD("prod", "生产环境", false);

    private final String env;
    private final String msg;
    private final Boolean isTest;

    EnvEnum(String env, String msg, Boolean isTest) {
        this.env = env;
        this.msg = msg;
        this.isTest = isTest;
    }

    public static boolean isTestEnv(String env) {
        for (EnvEnum eEnum : EnvEnum.values()) {
            if(eEnum.getEnv().equals(env)) {
                return eEnum.getIsTest();
            }
        }

        // 默认是测试环境
        return true;
    }
}
