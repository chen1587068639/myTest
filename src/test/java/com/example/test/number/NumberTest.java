package com.example.test.number;

import com.example.test.util.NumberUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: chengw
 * @Date: 2023/1/10 下午6:23
 */
@SpringBootTest
public class NumberTest {

    @Test
    public void test22222() throws InterruptedException {
        double one = 5.26419;
        String  str = String.format("%.2f",one);
        double four = Double.parseDouble(str);
        System.out.println(four);
    }

    @Test
    public void test3() throws InterruptedException {
        BigDecimal zero = BigDecimal.ZERO;
        BigDecimal bigDecimal = new BigDecimal(-1);
        if (-1 < zero.compareTo(bigDecimal)){
            System.out.println(zero);
        }

    }

    @Test
    public void testNumber(){
        String fileName2222 = "/Users/chengw/myWorld/test.txt";
        List<Integer> list = new ArrayList<>();
        try (BufferedReader br222 = new BufferedReader(new FileReader(fileName2222))) {
            while (null != br222.readLine()) {
                System.out.println(br222.readLine());
                list.add(Integer.valueOf(br222.readLine().trim()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
        double v = NumberUtils.geometricMean(list,548);
        System.out.println(v);
        for (int i = list.size();i< 548 ;i++){
            list.add(0);
        }
        //计算方差
        double calculateVariance = NumberUtils.calculateVariance(list);
        System.out.println("方差：" + calculateVariance);
        //计算标准差
        double calculateStandardDeviation = NumberUtils.calculateStandardDeviation(list);
        System.out.println("标准差："+calculateVariance);
    }


    @Test
    public void testHour(){
        String fileName2222 = "/Users/chengw/myWorld/hour.txt";
        List<Integer> list = new ArrayList<>();
        try (BufferedReader br222 = new BufferedReader(new FileReader(fileName2222))) {
            while (null != br222.readLine()) {
                System.out.println(br222.readLine());
                if (null == br222.readLine()){
                    break;
                }
                list.add(Integer.valueOf(br222.readLine().trim()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
        double v = NumberUtils.geometricMean(list,548*24);
        System.out.println(v);
        for (int i = list.size();i< 548 ;i++){
            list.add(0);
        }
        //计算方差
        double calculateVariance = NumberUtils.calculateVariance(list);
        System.out.println("方差：" + calculateVariance);
        //计算标准差
        double calculateStandardDeviation = NumberUtils.calculateStandardDeviation(list);
        System.out.println("标准差："+calculateVariance);
    }


    @Test
    public void testNumberOne(){
        System.out.println((10/3) * 1.5);
    }

    @Test
    public void testInteger(){
        int a = 0;
        int b = 0;
        a = a++;
        int i1 = ++a;
        int i = a++;
        System.out.println(a);
        System.out.println(i);
        System.out.println(b);
    }

    @Test
    public void testInt(){
        int i = 0;
        for (i = 0; i < 100; i++) {
            System.out.println(i);
        }
        System.out.println("输出结果,"+i);
    }

}
