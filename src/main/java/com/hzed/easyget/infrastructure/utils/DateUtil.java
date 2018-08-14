package com.hzed.easyget.infrastructure.utils;

import lombok.extern.slf4j.Slf4j;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
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

    public static final DateTimeFormatter FORMAT6 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private DateUtil() {
    }


    /**
     * 时间戳转LocalDateTime
     */
    public static LocalDateTime timestampToLocalDateTimeTo(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime转时间戳
     */
    public static Long localDateTimeToTimestamp(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
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
     * 添加小时
     */
    public static LocalDateTime addHour(LocalDateTime date, int hour) {
        return date.plusHours(hour);
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
    public static int daysBetween(LocalDateTime smdate, LocalDateTime bdate) {
        Duration duration = Duration.between(smdate, bdate);
        return (int) duration.toDays();
    }

    /**
     * 获取两个日期的天数差,去掉时分秒
     */
    public static int daysBetweenNoHMS(LocalDateTime startTime, LocalDateTime endTime) {
        LocalDateTime start = LocalDateTime.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth(), 0, 0);
        LocalDateTime end = LocalDateTime.of(endTime.getYear(), endTime.getMonth(), endTime.getDayOfMonth(), 0, 0);
        return daysBetween(start, end);
    }

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

    /**
     * 按照格式：yyyy-MM-dd格式化日期
     * 当前日期
     * @return
     */
    public static String formatToDay(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String formatDate = format.format(new Date());
        return formatDate;
    }

    /**
     * 将yyyy-MM-dd拼接成yyyy-MM-dd :HH:mm:ss
     * @param startDateStr
     * @return
     */
    public static Date strDateToStartDate(String startDateStr){
        return DateUtil.strToDate(startDateStr + " 00:00:00");
    }

    /**
     * 将yyyy-MM-dd拼接成yyyy-MM-dd :HH:mm:ss
     * @param endDateStr
     * @return
     */
    public static Date strDateToEndDate(String endDateStr){
        return DateUtil.strToDate(endDateStr + " 23:59:59");
    }

    public static Date strToDate(String dateString){
        if(null == dateString) {
            return new Date();
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
        } catch (ParseException e) {
            return new Date();
        }
    }

    /**
     * 判断一个时间是不是今天的时间范围内
     * @param localTime
     * @return
     */
    public static boolean isToday(LocalDateTime localTime){
        LocalDateTime startTime = LocalDate.now().atTime(0, 0, 0);
        LocalDateTime endTime = LocalDate.now().atTime(23, 59, 59);

        //如果大于今天的开始日期，小于今天的结束日期 时间是今天
        if (localTime.isAfter(startTime) && localTime.isBefore(endTime)) {
            return true;
        }

        return false;
    }
}
