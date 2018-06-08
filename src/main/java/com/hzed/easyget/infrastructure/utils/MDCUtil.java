package com.hzed.easyget.infrastructure.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * @author hfj
 * @date 2018/6/8
 */
public class MDCUtil {
    private static final Logger log = LoggerFactory.getLogger(MDCUtil.class);

    public MDCUtil() {
    }

    public static void put(String key, String val) {
        MDC.put(key, val);
    }

    public static String get(String key) {
        return MDC.get(key);
    }

    public static void clear() {
        MDC.clear();
    }
}
