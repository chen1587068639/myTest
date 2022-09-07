package com.example.test.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author chengw 2022/8/31
 */
public class DateUtils {

    public final static SimpleDateFormat DATE_FORMAT_CN = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");



    /**
     * 传入周日得到下周日
     * @param date
     * @return
     */
    public Date getNextSunday(Date date){
        Calendar lastCalendar = Calendar.getInstance();
        lastCalendar.setTime(date);
        lastCalendar.add(Calendar.DAY_OF_YEAR,7);
        return lastCalendar.getTime();
    }
    /**
     * 传入周日得到上周日
     * @param date
     * @return
     */
    public Date getLastSunday(Date date){
        Calendar lastCalendar = Calendar.getInstance();
        lastCalendar.setTime(date);
        lastCalendar.add(Calendar.DAY_OF_YEAR,-7);
        lastCalendar.set(Calendar.HOUR_OF_DAY,0);
        lastCalendar.set(Calendar.MINUTE, 0);
        lastCalendar.set(Calendar.SECOND,0);
        return lastCalendar.getTime();
    }
    /**
     * 传入周一时间，得到周日
     * @param date
     * @return
     */
    static Date getSundayOfWeek(Date date) {
        Date monday = getMondayOfWeek(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(monday);
        cal.add(Calendar.DATE, 6);
        //cal.set(Calendar.HOUR_OF_DAY,0);
        return cal.getTime();
    }

    /**
     * 得到周一时间
     * @param date
     * @return
     */
    static Date getMondayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

    public static Long diffDay(Date p1,Date p2){
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance.setTime(p1);
        instance2.setTime(p2);
        long day = (p1.getTime() - p2.getTime()) /1000/ 60 / 60 /24;
        return day;
    }


    //得出本年的xxxx:i+1:j日期
    public static Date getDateByThisYear(int y,int i,int j){
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY,0);
        todayEnd.set(Calendar.MINUTE, 0);
        todayEnd.set(Calendar.SECOND,0);
        todayEnd.set(Calendar.YEAR,y);
        todayEnd.set(Calendar.MONTH,i);
        todayEnd.set(Calendar.DATE,j);
        return todayEnd.getTime();
    }
}
