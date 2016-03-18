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
}
