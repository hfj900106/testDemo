package com.hzed.easyget.infrastructure.utils;


import com.hzed.easyget.infrastructure.exception.ComBizException;
import com.hzed.easyget.infrastructure.exception.NestedException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.Random;

/**
 * @author Administrator
 */
@Slf4j
public class ComUtil {

    public static Locale IDLOCALE = new Locale("in", "ID");

    public static String subString(String str, int length) {
        if (length < 0) {
            return str;
        } else if (StringUtils.isBlank(str)) {
            return null;
        } else if (str.length() <= length) {
            return str;
        } else {
            return str.substring(0, length);
        }
    }

    /**
     * 字符串首字母大写
     */
    public static String toUpperCaseIndex(String str) {
        char[] cs = str.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }

    /**
     * 产生随机码
     */
    public static String getRandomNumber(int size) {
        //元素

        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        //随机对象
        Random rand = new Random();

        //循环产生
        for (int i = 10; i > 1; i--) {
            int index = rand.nextInt(i);
            int tmp = array[index];
            array[index] = array[i - 1];
            array[i - 1] = tmp;
        }

        //拼接结果为字符串
        int result = 0;
        for (int i = 0; i < size; i++) {
            result = result * 10 + array[i];
        }
        String sixString = Integer.toString(result);

        //有可能出现5位数，前面加0补全
        if (sixString.length() == size - 1) {
            sixString = "0" + sixString;
        }
        //打印结果
        return sixString;
    }

    /**
     * 通用处理异常方式
     *
     * @param ex 异常
     */
    public static void expHandler(Throwable ex) {
        // 统一异常拦截处理
        if (ex instanceof ComBizException) {
            ComBizException cbEx = (ComBizException) ex;
            log.error("异常，{}", cbEx.getSerializeMsg());
        } else if (ex instanceof NestedException) {
            NestedException nsEx = (NestedException) ex;
            log.error("异常，{}", nsEx.getSerializeMsg(), nsEx);
        } else {
            log.error("异常", ex);
        }
    }

}
