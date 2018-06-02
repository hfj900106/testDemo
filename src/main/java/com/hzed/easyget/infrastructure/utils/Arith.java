package com.hzed.easyget.infrastructure.utils;

import java.math.BigDecimal;

/**
 * 计算通用类
 *
 * @author guichang
 */
public class Arith {

    public static BigDecimal add(BigDecimal s1, BigDecimal s2) {
        return s1.add(s2);
    }

    public static BigDecimal add(BigDecimal... ss) {
        BigDecimal total = new BigDecimal(0);
        for (BigDecimal s : ss) {
            total = add(total, s);
        }
        return total;
    }

    public static BigDecimal sub(BigDecimal s1, BigDecimal s2) {
        return s1.subtract(s2);
    }

    public static BigDecimal mul(BigDecimal s1, BigDecimal s2) {
        return s1.multiply(s2);
    }

    public static BigDecimal mul(BigDecimal... ss) {
        BigDecimal total = new BigDecimal(1);
        for (BigDecimal s : ss) {
            total = mul(total, s);
        }
        return total;
    }

    public static BigDecimal mulDown(BigDecimal s1, BigDecimal s2) {
        return mul(s1, s2).setScale(2, BigDecimal.ROUND_DOWN);
    }

    /**
     * 功能：提供（相对）精确的乘法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     */
    public static BigDecimal mulHalUp(BigDecimal s1, BigDecimal s2) {
        return s1.multiply(s2).setScale(2, BigDecimal.ROUND_HALF_UP);
    }


    /**
     * 功能：提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     * @param scale 小数点后保留几位
     */
    public static BigDecimal div(BigDecimal s1, BigDecimal s2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("精确度不能小于0！");
        }
        return s1.divide(s2, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 功能：提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     * @param scale 小数点后保留几位
     */
    public static int roundHalfUp(BigDecimal s1, BigDecimal s2, int scale) {
        return s1.divide(s2).setScale(scale, BigDecimal.ROUND_HALF_UP).intValue();
    }

    /**
     * 功能：提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字向上取。
     * @param scale 小数点后保留几位
     */
    public static BigDecimal divUp(BigDecimal s1, BigDecimal s2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("精确度不能小于0！");
        }
        return s1.divide(s2, scale, BigDecimal.ROUND_UP);
    }

    /**
     * 向下取值
     * 功能：提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param scale 小数点后保留几位
     */
    public static BigDecimal divDown(BigDecimal s1, BigDecimal s2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("精确度不能小于0！");
        }
        return s1.divide(s2, scale, BigDecimal.ROUND_DOWN);
    }

    /**
     * 功能：提供精确的小数位四舍五入处理。
     *
     * @param scale 小数点后保留几位
     */
    public static BigDecimal round(BigDecimal v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("精确度不能小于0！");

        }
        return v.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }


}
