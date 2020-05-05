package com.lxtx.framework.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Czh
 * Date: 2018/8/20 下午6:49
 */
public class DateUtils {

    public static String DATE_FORMAT = "yyyyMMddHHmmss";
    public static String DATE_FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static String DATE_FORMAT_HH_MM_SS = "HH:mm:ss";
    public static String DATE_FORMAT_YYYY_MM = "yyyy-MM";
    public static String DATE_FORMAT_MM = "MM";
    public static String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";


    /**
     * 获取当前日期
     *
     * @return
     */
    public static Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取日期前一天
     *
     * @param date
     * @return
     */
    public static Date getDayBefore(Date date) {
        Calendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        int day = gregorianCalendar.get(Calendar.DATE);
        gregorianCalendar.set(Calendar.DATE, day - 1);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取日期后一天
     *
     * @param date
     * @return
     */
    public static Date getDayAfter(Date date) {
        Calendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        int day = gregorianCalendar.get(Calendar.DATE);
        gregorianCalendar.set(Calendar.DATE, day + 1);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取offset天后的日期
     *
     * @param date
     * @param offset
     * @return
     */
    public static Date getOffsetDay(Date date, int offset) {
        Calendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        int day = gregorianCalendar.get(Calendar.DATE);
        gregorianCalendar.set(Calendar.DATE, day + offset);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取当前时间前后N(offsetAmount)分钟的日期
     *
     * @param offsetAmount
     * @return
     */
    public static Date getOffsetDate(int offsetAmount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, offsetAmount);
        return calendar.getTime();
    }

    /**
     * 得到当前日期字符串 格式（yyyyMMdd）
     */
    public static String getDate() {
        return getDate("yyyyMMdd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 日期格式化yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String getDateFormat(Date date) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
        return df.format(date);
    }

    /**
     * 日期格式化yyyy-MM-dd 00:00:00
     *
     * @param date
     * @return
     */
    public static String getBeginDateFormat(Date date) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD + " 00:00:00");
        return df.format(date);
    }

    /**
     * 日期格式化yyyy-MM-dd 23:59:59
     *
     * @param date
     * @return
     */
    public static String getEndDateFormat(Date date) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD + " 23:59:59");
        return df.format(date);
    }

    /**
     * 根据传递格式 - 日期格式化
     *
     * @param date
     * @return
     */
    public static Date formatDate(String date, String format) {
        try {
            return new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 日期格式化
     *
     * @param date
     * @return
     */
    public static String getDateFormat(Date date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * 获取日期多少个月后的日期
     *
     * @param Offset 负数时表往前计算
     * @param date
     * @return
     */
    public static Date getDateMonthOffset(Date date, int Offset) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        c.add(Calendar.MONTH, Offset);
        return c.getTime();
    }

    public static Date getThisWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

    public static Date getNextWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        cal.add(Calendar.DATE, 7);
        return cal.getTime();
    }

    // 获得下个月第一天的日期
    public static Date getNextMonthFirst(Date date) {
        Calendar lastDate = Calendar.getInstance();
        lastDate.setTime(date);
        lastDate.add(Calendar.MONTH, 1);// 减一个月
        lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
        return lastDate.getTime();
    }

    /**
     * 获取日期前days天
     *
     * @param date
     * @return
     */
    public static Date getDayBeforeDay(Date date, int days) {
        Calendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        int day = gregorianCalendar.get(Calendar.DATE);
        gregorianCalendar.set(Calendar.DATE, day - days);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取指定日期加上offset小时后的日期
     *
     * @param date
     * @param offset
     * @return
     */
    public static Date getOffsetHoursDate(Date date, int offset) {
        Calendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        int hour = gregorianCalendar.get(Calendar.HOUR);
        gregorianCalendar.set(Calendar.HOUR, hour + offset);
        return gregorianCalendar.getTime();
    }

    /**
     * 获取当年的第一天
     *
     * @param
     * @return
     */
    public static Date getCurrYearFirst() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取某年第一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    public static void main(String[] args) {
        System.out.println(getCurrYearFirst().compareTo(DateUtils.getCurrentDate()));
    }
}