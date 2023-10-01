package com.example.test;

import com.example.test.util.DateUtils;
import com.example.test.util.HttpUtils;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@SpringBootTest
public class DateTest {

    private static Object lock = new Object();
    private static int count = 1;


    @Test
    public void testsdf() throws Exception {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();

        lock.unlock();
        Class className = Class.forName("com.Test");
        Thread letterThread = new Thread(() -> {
            synchronized (lock) {
                for (char c = 'a'; c <= 'e'; c++) {
                    while (count != 1) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.print(c);
                    count = 2;
                    lock.notifyAll();
                }
            }
        });

        Thread numberThread = new Thread(() -> {
            synchronized (lock) {
                for (int i = 1; i <= 5; i++) {
                    while (count != 2) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.print(i);
                    count = 1;
                    lock.notifyAll();
                }
            }
        });

        letterThread.start();
        numberThread.start();
    }


class MyThread extends Thread{

    @Override
    public void run() {
        try {
            System.out.println("chen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



















    /**
     * 阻塞队列
     */
    private static BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(20);

    private final static String TABLE_NAME_PRE = "box_data_backup_";

    public StringBuilder s = new StringBuilder("chen");

    public synchronized void addOne() throws InterruptedException {
        synchronized (s) {
            s.append("gaowei");
            System.out.println("thread开始睡眠十秒");
            Thread.sleep(10000);
            System.out.println("thread结束睡眠十秒");
        }
    }

    public synchronized StringBuilder get(){
        return s.append("chen");
    }
    @Test
    public void LongConversionDate() throws Exception {
        Class<?> dateTest = Class.forName("DateTest");
        DateTest o = (DateTest)dateTest.newInstance();
        
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set("chen");
        MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(3000);
        System.out.println("i来了"+get());
        Thread.sleep(10000);
        System.out.println("主线程执行完毕");
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


    @Test
    public void test222234() throws Exception {
        Date endTime = new Date();
        Date startTime = DateUtils.DATE_FORMAT_D.parse("2022--01-01 00:00:00");
        System.out.println(DateUtils.dateDiff(startTime,endTime));
    }

    @Test
    public void test22222() throws InterruptedException, ParseException {
        String date = "2023-05-05 03";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = simpleDateFormat.parse(date);
        System.out.println(simpleDateFormat.format(parse));

    }

    @Test
    public void test234(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println(calendar.get(Calendar.HOUR_OF_DAY) + 1);
    }

    private Long diffMinute(Date startTime,Date endTime){
        return (endTime.getTime() - startTime.getTime())/(1000*60);
    }

//
//    @Test
//    public void test3452(){
//        int i = 6;
//        switch (i){
//            case i>6:
//
//        }
//    }

    public final static SimpleDateFormat S_Y_M_D_H_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH");

    public final static SimpleDateFormat S_Y_M_D_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH");

    @Test
    public void test45() throws ParseException {
        String date = "2022-06-22 23:12:43";
        Date parssse = S_Y_M_D_H_FORMAT.parse("2022-06-22 23:12:43");
        System.out.println(S_Y_M_D_H_FORMAT.format(parssse));
        String format = S_Y_M_D_FORMAT.format(parssse);
        System.out.println(format);
        System.out.println(Integer.parseInt(date.substring(date.length() - 2)));
    }

    /**
     * 比价金额计算：同一个商品在不同平台的同一个时间有多个价格，请描绘一个品在整个生命周期内的最低价格。
     *
     * 例如，商品A
     * 在平台A 10:00 - 12:00的价格为5
     * 在平台B 11:30 - 14:30的价格为3
     * 在平台C 14:00 -16:00的价格为2
     *
     * 那么全生命周期的最低价格（忽略平台）为
     * 10:00 - 11:30的价格为5
     * 11:30 - 14:00的价格为3
     * 14:00 - 16:00的价格为2
     *
     * 请尝试多种corner case下的解题结果
     *
     * 要编码，笔试题要和简历一起推，您看下能做的话再联系
     */
//    public void test56() throws ParseException {
//        CommodityPrice commodityPrice = new CommodityPrice(DateUtils.DATE_FORMAT_D.parse("2023-05-01"));
//
//    }
//
//    private String countPrice(){
//
//    }
}
