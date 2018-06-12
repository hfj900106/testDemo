package com.hzed.easyget.infrastructure.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author guichang
 */

@Slf4j
public final class DateUtil {

    public static final DateTimeFormatter FORMAT1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final DateTimeFormatter FORMAT2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final DateTimeFormatter FORMAT3 = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static final DateTimeFormatter FORMAT4 = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    public static final DateTimeFormatter FORMAT5 = DateTimeFormatter.ofPattern("HH:mm:ss");

    private DateUtil() {
    }

    /**
     * 把字符串转化成LocalDateTime
     */
    public static LocalDateTime strToLocalDateTime(String dateStr, DateTimeFormatter formatter) {
        return LocalDateTime.parse(dateStr, formatter);
    }

    public static LocalDateTime strToLocalDateTime1(String dateStr) {
        return strToLocalDateTime(dateStr, FORMAT1);
    }

    /**
     * 把LocalDateTime转化成字符串
     */
    public static String localDateTimeToStr(LocalDateTime localDateTime, DateTimeFormatter formatter) {
        if (localDateTime == null) {
            return "";
        }
        return localDateTime.format(formatter);
    }

    public static String localDateTimeToStr1(LocalDateTime localDateTime) {
        return localDateTimeToStr(localDateTime, FORMAT1);
    }

    public static String localDateTimeToStr2(LocalDateTime localDateTime) {
        return localDateTimeToStr(localDateTime, FORMAT2);
    }

    public static String localDateTimeToStr3(LocalDateTime localDateTime) {
        return localDateTimeToStr(localDateTime, FORMAT3);
    }

    /**
     * 获取两个日期的秒数
     */
    public static Long getBetweenSeconds(LocalDateTime startTime, LocalDateTime endTime) {
        return Duration.between(startTime, endTime).getSeconds();
    }

    /**
     * 获取两个日期的天数差,去掉时分秒
     */
    public static int getBetweenDays(LocalDateTime startTime, LocalDateTime endTime) {

        LocalDateTime start = LocalDateTime.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth(), 0, 0);
        LocalDateTime end = LocalDateTime.of(endTime.getYear(), endTime.getMonth(), endTime.getDayOfMonth(), 0, 0);


        return (int)Duration.between(start, end).toDays();
    }

    /**
     * 添加月
     */
    public static LocalDateTime addMonth(LocalDateTime startDate, int month) {
        return startDate.plusMonths(month);
    }

    /**
     * 添加天数
     */
    public static LocalDateTime addDays(LocalDateTime date, int days) {
        return date.plusDays(days);
    }

    /**
     * 添加分
     */
    public static LocalDateTime addMins(LocalDateTime date, int mins) {
        return date.plusMinutes(mins);
    }

    /**
     * 获取当前日期所在月的最后一天
     */
    public static LocalDateTime getLastDayOfMonth(LocalDateTime date) {
        return LocalDateTime.from(TemporalAdjusters.lastDayOfMonth().adjustInto(date));
    }

    /**
     * 判断当前为周几
     */
    public static Integer getNowWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return w;
    }

    /**
     * 判断当前为周几
     */
    public static String getNowWeekName() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w == 1) {
            return "Monday";
        }
        if (w == 2) {
            return "Tuesday";
        }
        if (w == 3) {
            return "Wednesday";
        }
        if (w == 4) {
            return "Thursday";
        }
        if (w == 5) {
            return "Friday";
        }
        if (w == 6) {
            return "Saturday";
        }
        return "Sunday";
    }

    /**
     * 获取两个时间差 天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     */
   /* public static int daysBetween(LocalDateTime smdate, LocalDateTime bdate) {
        Duration duration = Duration.between(smdate, bdate);
        return (int) duration.toDays();
    }*/

    /**
     * 判断大小
     *
     * @param bDate 较大
     * @param sDate 较小
     */
    public static boolean compare(LocalDateTime bDate, LocalDateTime sDate) {
        int result = bDate.compareTo(sDate);
        return result > 0 ? true : false;
    }

}
