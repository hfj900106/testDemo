package com.hzed.easyget.infrastructure.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author Administrator
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
     * 把字符串转化成LocalDate
     */
    public static LocalDate strToLocalDate(String dateStr, DateTimeFormatter formatter) {
        return LocalDate.parse(dateStr, formatter);
    }

    /**
     * 把字符串转化成LocalDate
     */
    public static LocalDate strToLocalDate(String dateStr) {
        return strToLocalDate(dateStr, FORMAT2);
    }


    /**
     * 把字符串转化成LocalDateTime
     *
     * @param dateStr
     * @return
     */
    public static LocalDateTime strToLocalDateTime(String dateStr) {
        return strToLocalDateTime(dateStr, FORMAT1);
    }

    /**
     * 把字符串转化成LocalDateTime
     *
     * @param dateStr
     * @return
     */
    public static LocalDateTime strToLocalDateTime(String dateStr, DateTimeFormatter formatter) {
        return LocalDateTime.parse(dateStr, formatter);
    }

    /**
     * 把LocalDate转化成字符串
     *
     * @param localDate
     * @return
     */
    public static String dateToStr(LocalDate localDate) {
        if (null == localDate) {
            return null;
        }
        return dateToStr(localDate, FORMAT2);
    }

    /**
     * 把LocalDate转化成字符串
     */
    public static String dateToStr(LocalDate localDate, DateTimeFormatter formatter) {
        return localDate.format(formatter);
    }

    /**
     * 把LocalDateTime转化成字符串
     */
    public static String dateToStr(LocalDateTime localDateTime) {
        return dateToStr(localDateTime, FORMAT1);
    }

    /**
     * 把LocalDateTime转化成字符串
     */
    public static String dateToStr(LocalDateTime localDateTime, DateTimeFormatter formatter) {
        if (localDateTime == null) {
            return "";
        }
        return localDateTime.format(formatter);
    }

    /**
     * 把字符串转化成LocalDateTime
     */
    public static Date strToDate(String dateString) {
        if (null == dateString) {
            return new Date();
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
        } catch (ParseException e) {
            return new Date();
        }
    }

    /**
     * 获取两个日期的月份数
     */
    public static Integer getBetweenMonths(LocalDate startDate, LocalDate endDate) {
        Period pe = Period.between(startDate, endDate);
        return pe.getYears() * 12 + pe.getMonths();
    }

    /**
     * 获取两个日期的年
     */
    public static Integer getBetweenYears(LocalDate startDate, LocalDate endDate) {
        return Period.between(startDate, endDate).getYears();
    }

    /**
     * 获取两个日期的天数
     */
    public static Long getBetweenDays(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    /**
     * 获取两个日期的秒数
     */
    public static Long getBetweenSeconds(LocalDateTime startTime, LocalDateTime endTime) {
        return Duration.between(startTime, endTime).getSeconds();
    }

    /**
     * 添加月
     */
    public static LocalDate addMonth(LocalDate startDate, int month) {
        return startDate.plusMonths(month);
    }

    /**
     * 添加月
     */
    public static LocalDateTime addMonth(LocalDateTime startDate, int month) {
        return startDate.plusMonths(month);
    }

    /**
     * 获得指定日期的前一天
     */
    public static LocalDate getSpecifiedDayBefore(LocalDate date) {
        return date.minusDays(1L);
    }

    /**
     * 获得指定日期的后一天
     */
    public static LocalDate getSpecifiedDayAfter(LocalDate date) {
        return date.plusDays(1L);
    }

    /**
     * 添加天数
     */
    public static LocalDate addDays(LocalDate date, int days) {
        return date.plusDays(days);
    }

    /**
     * 添加天数
     */
    public static LocalDateTime addDays(LocalDateTime date, int days) {
        return date.plusDays(days);
    }

    /**
     * 根据分为单位添加天数
     */
    public static LocalDateTime addDaysBymin(LocalDateTime date, int days) {
        return date.plusMinutes(days);
    }

    /**
     * 获取当前日期所在月的最后一天
     */
    public static LocalDate getLastDayOfMonth(LocalDate date) {
        return LocalDate.from(TemporalAdjusters.lastDayOfMonth().adjustInto(date));
    }

    /**
     * 获取当前日期所在月的最后一天
     */
    public static LocalDateTime getLastDayOfMonth(LocalDateTime date) {
        return LocalDateTime.from(TemporalAdjusters.lastDayOfMonth().adjustInto(date));
    }

    /**
     * 判断 compareTime 是否在 startTime，endTime 之间
     */
    public static boolean isBetweenTimes(LocalTime compareTime, LocalTime startTime, LocalTime endTime) {
        return compareTime.compareTo(startTime) >= 0 && (compareTime.compareTo(endTime)) <= 0;
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
     * @return
     */
    public static int daysBetween(LocalDateTime smdate, LocalDateTime bdate) {
        Duration duration = Duration.between(smdate, bdate);
        return (int) duration.toDays();
    }

    /**
     * 判断大小
     *
     * @param bDate 较大
     * @param sDate 较小
     * @return
     */
    public static boolean compare(LocalDateTime bDate, LocalDateTime sDate) {
        int result = bDate.compareTo(sDate);
        return result > 0 ? true : false;
    }

}
