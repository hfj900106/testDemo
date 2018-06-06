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
    private final Boolean isTestDev;

    EnvEnum(String env, String msg, Boolean isTestDev) {
        this.env = env;
        this.msg = msg;
        this.isTestDev = isTestDev;
    }

    public static boolean isTestEnv(String env) {
        for (EnvEnum eEnum : EnvEnum.values()) {
            if(eEnum.getEnv().equals(env)) {
                return eEnum.getIsTestDev();
            }
        }

        // 默认是测试环境
        return true;
    }
}
