package com.hzed.easyget.infrastructure.utils;

import org.slf4j.MDC;

import java.util.UUID;

/**
 * @author hfj
 * @date 2018/6/8
 */
public class MdcUtil {

    public static final String MODULENAME = "moduleName";
    public static final String TRACE = "trace";

    private MdcUtil() {
    }

    public static void putTrace() {
        MDC.remove(TRACE);
        MDC.put(TRACE, UUID.randomUUID().toString().replaceAll("-", "").substring(3, 20));
    }

    public static void putModuleName(String moduleName) {
        MDC.put(MODULENAME, moduleName);
    }

    public static void clear() {
        MDC.clear();
    }
}
