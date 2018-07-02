package com.hzed.easyget.infrastructure.utils;

import java.util.*;

/**
 * @author guichang
 * @data 2018/6/7
 */
public final class ThreadLocalUtil {
    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL = ThreadLocal.withInitial(() -> new HashMap(4));

    public static Map<String, Object> getThreadLocal() {
        return THREAD_LOCAL.get();
    }

    public static <T> T get(String key) {
        Map map = THREAD_LOCAL.get();
        return (T) map.get(key);
    }

    public static <T> T get(String key, T defaultValue) {
        Map map = THREAD_LOCAL.get();
        return (T) map.get(key) == null ? defaultValue : (T) map.get(key);
    }

    public static void set(String key, Object value) {
        Map map = THREAD_LOCAL.get();
        map.put(key, value);
    }

    public static void set(Map<String, Object> keyValueMap) {
        Map map = THREAD_LOCAL.get();
        map.putAll(keyValueMap);
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }

    public static <T> T remove(String key) {
        Map map = THREAD_LOCAL.get();
        return (T) map.remove(key);
    }

}