package com.harvest.core.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @Author: Alodi
 * @Date: 2022/11/27 11:14 PM
 * @Description: TODO
 **/
public class DateUtils {

    public static String DATE_FORMAT = "yyyy-MM-dd";
    public static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static String MILLISECOND_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    public static String YEAR_FORMAT = "yyyy";
    public static String MOUTH_FORMAT = "MM";
    public static final String YEAR_MOUTH_FORMAT = "yyyy-MM";

    /**
     * 日期天数 操作
     *
     * @param date
     * @param day
     * @return Date
     */
    public static Date addDate(Date date, int day) {
        if (null == date) {
            return null;
        }
        Calendar calendar = newCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    public static Calendar newCalendar() {
        return Calendar.getInstance();
    }

    /**
     * @param text
     * @param format
     * @return
     */
    public static Date parse(String text, String format) {
        return null;
    }

    public static Date removeTime(Date min) {
        return null;
    }
}
