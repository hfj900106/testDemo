package com.demo.hzed.infrastructure.utils;

import com.demo.hzed.infrastructure.consts.LogConsts;
import com.demo.hzed.infrastructure.enums.BizCodeEnum;
import com.demo.hzed.infrastructure.exception.ComBizException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.util.Random;

/**
 * @author guichang
 * @since 2018/3/19
 */
public class ComUtil {
    private static Random random = new Random();

    /**
     * 设置trace
     */
    public static void putTrace() {
        String trace = MDC.get(LogConsts.TRACE);
        if (StringUtils.isBlank(trace)) {
            MDC.put(LogConsts.TRACE, Long.toHexString(random.nextLong()));
        }
    }




}
