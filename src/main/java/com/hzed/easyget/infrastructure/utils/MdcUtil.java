package com.hzed.easyget.infrastructure.utils;

import org.slf4j.MDC;

import java.util.UUID;

/**
 * @author hfj
 * @date 2018/6/8
 */
public class MdcUtil {

    private MdcUtil() {
    }

    public static void putTrace() {
        MDC.put("trace", UUID.randomUUID().toString().replaceAll("-", "").substring(3, 20));
    }

    public static void putModuleName(String moduleName) {
        MDC.put("moduleName", moduleName);
    }
}
