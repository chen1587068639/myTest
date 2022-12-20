package com.example.test;

import com.example.test.util.DateUtils;
import com.example.test.util.HttpUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class DateTest {

    private final static String TABLE_NAME_PRE = "box_data_backup_";

    @Test
    public void LongConversionDate(){
        System.out.println(DateUtils.DATE_FORMAT_D.format(new Date(1670428800000L)));
    }

    @Test
    public void testDate() throws ParseException {
        Date startTime = DateUtils.DATE_FORMAT_D.parse("2022-08-11 21:14:54");
        Date endTime = DateUtils.DATE_FORMAT_D.parse("2022-08-21 08:32:33");
        long j = 72 * 60 * 60 * 1000L;
        if (endTime.getTime() - startTime.getTime() > j) {
            startTime = AddTime(endTime, -72);
        }
        System.out.println(DateUtils.DATE_FORMAT_D.format(startTime));
        System.out.println(DateUtils.DATE_FORMAT_D.format(endTime));
        List<String> strings = tableNameList(startTime, endTime);
        System.out.println(strings);
    }

    /**
     * 测试data的before和after
     */
    @Test
    public void testTime() throws ParseException {

        List<Date> dateList = new ArrayList<>();
        Date parse = DateUtils.DATE_FORMAT_D.parse("2022-10-01 00:00:00");
        Date parse222 = DateUtils.DATE_FORMAT_D.parse("2022-10-01 01:00:00");
        for (int i=0;i<100000;i++){
            Calendar instance = Calendar.getInstance();
            instance.setTime(parse222);
            instance.add(Calendar.SECOND,1);
            dateList.add(instance.getTime());
            parse222 = instance.getTime();
        }
        System.out.println(dateList.size());
        Date endTime = DateUtils.DATE_FORMAT_D.parse("2022-10-06 00:00:00");
        int countSize = 0;
        while (parse.before(endTime)){
            Date addOneHour1 = addOneHour(parse);
            Date addOneHour = addOneHour(addOneHour1);
            List<Date> refundAfterCollectByAll = dateList.stream().filter(c -> c.before(addOneHour) && c.after(addOneHour1) || c.equals(addOneHour)).collect(Collectors.toList());
            System.out.println(refundAfterCollectByAll.size());
            countSize = countSize + refundAfterCollectByAll.size();
            parse = addOneHour(parse);
            System.out.println(DateUtils.DATE_FORMAT_D.format(addOneHour1) + "----" + DateUtils.DATE_FORMAT_D.format(addOneHour));
        }
        System.out.println(countSize);

    }
    //加一个小时
    private static Date addOneHour(Date date){
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.setTime(date);
        todayEnd.add(Calendar.HOUR, 1);
        return todayEnd.getTime();
    }


    @Test
    public void testDate1() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2021);
        calendar.set(Calendar.MONTH, 2);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        System.out.println(DateUtils.DATE_FORMAT_D.format(calendar.getTime()));
    }
    public final static SimpleDateFormat YEAR_MONTH_DATE_HOUR_MINUTE_SECOND_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public final static SimpleDateFormat YEAR_MONTH_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    @Test
    //0,9   0,12    0,15
    public void testDate22() throws ParseException {
        String da = "2022-11-11 11:11:11";
        Date parse = YEAR_MONTH_DATE_HOUR_MINUTE_SECOND_FORMAT.parse(da.substring(0,13));
        System.out.println(YEAR_MONTH_DATE_HOUR_MINUTE_SECOND_FORMAT.format(parse));


    }

    public static List<String> tableNameList(Date start, Date end) throws ParseException {
        List<String> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyMMdd");
        String startDb = TABLE_NAME_PRE + sdf.format(start);
        String endDb = TABLE_NAME_PRE + sdf.format(end);
        list.add(startDb);
        if (startDb.equals(endDb)) {
            return list;
        }
        int days = daysApart(start, end);
        for (int i = 1; i < days; i++) {
            list.add(tableName(addDay(start, i)));
        }
        list.add(endDb);
        return list;
    }

    /**
     * 增加{days} 天
     *
     * @param date 时间
     * @param days 增加天数
     */
    public static Date addDay(Date date, int days) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.DAY_OF_MONTH, days);
        return ca.getTime();
    }

    /**
     * 两个时间相差几天
     *
     * @param start 开始
     * @param end   结束
     * @return int
     * @throws ParseException
     */
    public static int daysApart(Date start, Date end) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        start = sdf.parse(sdf.format(start));
        end = sdf.parse(sdf.format(end));
        long days = (end.getTime() - start.getTime()) / 86400000;

        return (int) days;
    }

    /**
     * 根据时间获取 表明
     *
     * @param date 时间
     * @return java.lang.String
     */
    public static String tableName(Date date) {
        if (null == date) {
            date = new Date();
        }
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyMMdd");
        String suffix = sdf.format(date);
        return TABLE_NAME_PRE + suffix;
    }

    /**
     * 增加 {i}小时
     *
     * @param nowDate
     * @param i
     * @return
     */
    public static Date AddTime(Date nowDate, Integer i) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(nowDate);
        ca.add(Calendar.HOUR, i);
        return ca.getTime();
    }

    @Test
    public void testAfter() throws ParseException {
        saveActiveUserToRedis(DateUtils.DATE_FORMAT_D.parse("2022-01-02 00:13:13"),(DateUtils.DATE_FORMAT_D.parse("2023-01-02 00:13:13")),12L);
    }

    public void saveActiveUserToRedis(Date beginTime,Date endTime,Long operateAreaId) {
        //统计几个月的数据
        //起始月开始时间
        Date monthStart = DateUtils.getMonthStart(beginTime);
        //起始月结束时间
        Date monthEnd = DateUtils.addOneMonth(monthStart);
        while (monthStart.before(endTime)){
            //查询sql
            System.out.println(DateUtils.DATE_FORMAT_CN.format(monthStart) + "#####" + DateUtils.DATE_FORMAT_CN.format(monthEnd));
            monthStart = DateUtils.addOneMonth(monthStart);
            monthEnd = DateUtils.addOneMonth(monthStart);

        }
    }

}
