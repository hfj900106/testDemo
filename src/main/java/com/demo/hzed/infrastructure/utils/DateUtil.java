package com.demo.hzed.infrastructure.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 时间工具类
 *
 * @author Administrator
 */
public class DateUtil {

    public static String dateToStr1(Date date) {
        return date == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public static String dateToStr2(Date date) {
        return date == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static String dateToStr3(Date date) {
        return date == null ? "" : new SimpleDateFormat("yyyy年M月dd日").format(date);
    }

    public static String dateToStr4(Date date) {
        return date == null ? "" : new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date);
    }

    public static String dateToStr5(Date date) {
        return date == null ? "" : new SimpleDateFormat("yyyyMMdd").format(date);
    }

    public static String dateToStr6(Date date) {
        return date == null ? "" : new SimpleDateFormat("yyyyMMddHHmmss").format(date);
    }

    public static Date strToDate1(String dateString) {
        if (null == dateString) return new Date();
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
        } catch (ParseException e) {
            return new Date();
        }
    }

    public static Date strToDate2(String dateString) {
        if (null == dateString) return new Date();
        try {
            return new SimpleDateFormat("HH:mm:ss").parse(dateString);
        } catch (ParseException e) {
            return new Date();
        }
    }

    public static Date strToDate3(String dateString) {
        if (null == dateString) return new Date();
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            return new Date();
        }
    }

    /**
     * 得到当前时间距2013-11-01 00:00:00的小时数
     *
     * @return
     * @throws ParseException
     */
    public long getHours() {
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simple.parse("2013-11-01 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long millisecond = System.currentTimeMillis() - date.getTime();
        long temp = 1000 * 60 * 60;
        return millisecond / temp;
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            smdate = sdf.parse(sdf.format(smdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            bdate = sdf.parse(sdf.format(bdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 计算两个时间之间相差的分钟数
     */
    public static long diffMinutes(Date startDate, Date endDate) {
        return (endDate.getTime() - startDate.getTime()) / 60000;
    }

    /**
     * 日期加上分钟的时间
     */
    public static Date dateAddMinute(Date date, int minute) {
        return add(date, Calendar.MINUTE, minute);
    }

    /**
     * 日期加上月数的时间
     */
    public static Date dateAddMonth(Date date, int month) {
        return add(date, Calendar.MONTH, month);
    }

    /**
     * 日期加上天数的时间
     */
    public static Date dateAddDay(Date date, int day) {
        return add(date, Calendar.DAY_OF_YEAR, day);
    }

    /**
     * 日期加上年数的时间
     */
    public static Date dateAddYear(Date date, int year) {
        return add(date, Calendar.YEAR, year);
    }

    /**
     * 计算剩余时间 (多少天多少时多少分)
     *
     * @return
     */
    public static String remainDateToString(Date startDate, Date endDate) {
        StringBuilder result = new StringBuilder();
        if (endDate == null) {
            return "过期";
        }
        long times = endDate.getTime() - startDate.getTime();
        if (times < -1) {
            result.append("过期");
        } else {
            long temp = 1000 * 60 * 60 * 24;
            //天数
            long d = times / temp;

            //小时数
            times %= temp;
            temp /= 24;
            long m = times / temp;
            //分钟数
            times %= temp;
            temp /= 60;
            long s = times / temp;

            result.append(d);
            result.append("天");
            result.append(m);
            result.append("小时");
            result.append(s);
            result.append("分");
        }
        return result.toString();
    }

    private static Date add(Date date, int type, int value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(type, value);
        return calendar.getTime();
    }


    /**
     * @MethodName: getLinkUrl
     * @Param: DateUtil
     * flag ： true 转换  false 不转换
     * @Author: gang.lv
     * @Date: 2013-5-8 下午02:52:44
     * @Return:
     * @Descb:
     * @Throws:
     */
    public static String getLinkUrl(boolean flag, String content, String id) {
        if (flag) {
            content = "<a href='finance.do?id=" + id + "'>" + content + "</a>";
        }
        return content;
    }

    /**
     * 时间转换为时间戳
     *
     * @param format
     * @param date
     * @return
     * @throws ParseException
     */
    public static long getTimeCur(String format, String date) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.parse(sf.format(date)).getTime();
    }

    /**
     * 日期转换成以秒为单位的时间戳
     */
    public static Long getLSeconds(Date date) {
        return date == null ? null : date.getTime() / 1000;
    }

    public static Integer getISeconds(Date date) {
        return date == null ? null : getLSeconds(date).intValue();
    }

    /**
     * 将时间戳转为字符串
     *
     * @param cc_time
     * @return
     */
    public static String getStrTime(String cc_time) {
        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        return re_StrTime;
    }

    public static int getAge(Date birthday) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthday);
        Calendar nowDate = Calendar.getInstance();

        return nowDate.get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
    }

    /**
     * 当前时间减去分钟数得到的时间
     *
     * @param minutes
     * @return
     * @throws ParseException
     */
    public static String getDateMinusMinutes(int minutes) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowTime = new Date();
        String datetest = formatter.format(nowTime);
        Date date = formatter.parse(datetest);
        long Time1 = (date.getTime() / 1000) - 60 * minutes;//减去30分
        date.setTime(Time1 * 1000);
        return formatter.format(date);
    }

    /**
     * 和当前时间比较是否在给定的时长内
     *
     * @param validTime 给定的时间
     * @param time      给定的时长（s）
     * @return true 有效 false 无效
     */
    public static boolean inValidTime(Date validTime, int time) {
        long currTime = System.currentTimeMillis();
        long valid = validTime.getTime();
        return ((currTime - valid) < time * 1000);
    }

    /**
     * 获取年
     */
    public static int getYear(Date time) {
        if (time == null) return -1;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取月
     */
    public static int getMonth(Date time) {
        if (time == null) return -1;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日
     */
    public static int getDay(Date time) {
        if (time == null) return -1;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 开始日期，将yyyy-MM-dd拼接成yyyy-MM-dd :HH:mm:ss
     */
    public static Date strDateToStartDate(String startDateStr) {
        return strToDate1(startDateStr + " 00:00:00");
    }

    /**
     * 结束日期，将yyyy-MM-dd拼接成yyyy-MM-dd :HH:mm:ss
     */
    public static Date strDateToEndDate(String endDateStr) {
        return DateUtil.strToDate1(endDateStr + " 23:59:59");
    }

    /**
     * 当前时间减去天数得到的时间
     */
    public static Date getDateMinusDay(int day) {
        Date time = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.set(Calendar.DATE, c.get(Calendar.DATE) - day);
        return c.getTime();
    }

    private static final Map<String, String> DATE_FORMAT_REGEXPS = new HashMap<String, String>() {{
        put("^\\d{8}$", "yyyyMMdd");
        put("^\\d{1,2}-\\d{1,2}-\\d{4}$", "dd-MM-yyyy");
        put("^\\d{4}-\\d{1,2}-\\d{1,2}$", "yyyy-MM-dd");
        put("^\\d{1,2}/\\d{1,2}/\\d{4}$", "MM/dd/yyyy");
        put("^\\d{4}/\\d{1,2}/\\d{1,2}$", "yyyy/MM/dd");
        put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}$", "dd MMM yyyy");
        put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}$", "dd MMMM yyyy");
        put("^\\d{12}$", "yyyyMMddHHmm");
        put("^\\d{8}\\s\\d{4}$", "yyyyMMdd HHmm");
        put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}$", "dd-MM-yyyy HH:mm");
        put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy-MM-dd HH:mm");
        put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}$", "MM/dd/yyyy HH:mm");
        put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy/MM/dd HH:mm");
        put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMM yyyy HH:mm");
        put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMMM yyyy HH:mm");
        put("^\\d{14}$", "yyyyMMddHHmmss");
        put("^\\d{8}\\s\\d{6}$", "yyyyMMdd HHmmss");
        put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd-MM-yyyy HH:mm:ss");
        put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy-MM-dd HH:mm:ss");
        put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "MM/dd/yyyy HH:mm:ss");
        put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy/MM/dd HH:mm:ss");
        put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMM yyyy HH:mm:ss");
        put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMMM yyyy HH:mm:ss");
    }};

    public static String determineDateFormat(String dateString) {
        for (String regexp : DATE_FORMAT_REGEXPS.keySet()) {
            if (dateString.toLowerCase().matches(regexp)) {
                return DATE_FORMAT_REGEXPS.get(regexp);
            }
        }
        return null; // Unknown format.
    }

    public static Date stringToDateUnkonwFormat(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(determineDateFormat(dateString));
        try {
            Date dateOutput = dateFormat.parse(dateString);
            return dateOutput;
        } catch (Exception e) {
            return null;
        }
    }


    public static Date getNextDayDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    public static Date getFirstDayOfWeek(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        int dayofweek = cale.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cale.add(Calendar.DATE, 2 - dayofweek);
        return cale.getTime();
    }

    public static Date getFirstDayOfNextWeek(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(getFirstDayOfWeek(date));
        cale.add(Calendar.WEEK_OF_YEAR, 1);
        return cale.getTime();
    }

    public static Date getFirstDayOfMonth(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.set(cale.get(Calendar.YEAR), cale.get(Calendar.MONTH), 1);
        return cale.getTime();
    }

    public static Date getFirstDayOfNextMonth(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.MONTH, 1);
        return getFirstDayOfMonth(cale.getTime());
    }
}
