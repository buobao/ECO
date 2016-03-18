package com.joint.turman.app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by dqf on 2016/3/18.
 */
public class DateUtils {

    private static Calendar calendar = Calendar.getInstance();
    private static SimpleDateFormat format_date = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat format_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取当前起始时间
     * @param str
     * @return
     */
    public static Date getDayStart(String str){
        try {
            return format_date.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getDayStart(Date date){
        try {
            date = format_date.parse(format_date.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    /**
     * 获取当前截止时间
     * @param str
     * @return
     */
    public static Date getDayEnd(String str){
        try {
            return format_time.parse(str+" 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getDayEnd(Date date){
        try {
            date = format_time.parse(format_date.format(date)+" 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 依据当前日期获取当前月第一天日期
     * @param date
     * @return
     */
    public static Date getMonthFirstDay(Date date){
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        try {
            return format_date.parse(format_date.format(calendar.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 依据当前日期获取当前月最后一天日期
     * @param date
     * @return
     */
    public static Date getMonthLastDay(Date date){
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        try {
            return format_date.parse(format_date.format(calendar.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串转日期
     * @param str
     * @return
     */
    public static Date parse(String str){
        try {
            return format_date.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串转时间
     * @param str
     * @return
     */
    public static Date parseTime(String str){
        try {
            return format_time.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前日期的下一天日期
     * @param date
     * @return
     */
    public static Date tomorrow(Date date){
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 当前日期的前一天
     * @param date
     * @return
     */
    public static Date yesterday(Date date){
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }
}



































