package com.example.test.interview;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: chengw
 * @Date: 2023/6/29 上午11:34
 */
@SpringBootTest
public class NumberTest {

    @Test
    public void test() {
        System.out.println("chen");
    }

    /**
     * 计算两个数组中的中位数
     * 计算第一个数组中的中位数
     * 然后计算第二个数组中的中位数
     * 先根据二分法计算出，第二个数组的中位数
     * @param nums1 有序数组
     * @param nums2 有序数组
     * @return 中位数
     */
//    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//        double d = 0;
//        //第一个数组的长度
//        int m = nums1.length;
//        //第二个数组的长度
//        //计算是
//        int n = nums2.length;
//        //合并数组是偶数是true，奇数是false；
//        boolean flag = (m + n) % 2 == 0;
//        //中位数位置:两个长度除以2
//        int middle = (m + n) >> 1;
//        //数组一中位数一
//        //数组二中位数一
//
//        //1，3，  5  ，7，9
//        //2，4，6，8，10，12，14
//        //10，12，14，16，18，20，22
//        //1，3，5，7，9，  10，12  ，14，16，18，20，22
//        //1，2，3，4，5，6，7，8，9，10，12，14
//        //先取出第一个数组中的5，然后取出第二个数组中的6比较大小，如果两者相等则返回
//        //如果不相等，1数组中位数大于2数组中位数
//
//        //循环依次取出第一个数组中的中位数1m，然后取出第二个数组中的中位数2m比较大小，记录下来两个数字，和索引
//        //然后再循环找数据，直到找到两个数据的大小反过来就break掉
//        //5，8 记下索引3，和4
//        //再循环
//        //m1中位数以下的都小于m2中位数以上的
//        //m2中位数以上的都大于m1中位数以下的
//
//        //再循环数组一直到找出中位数大于数组二的中位数的索引
//
//        //再循环数组二直到找出中位数小于数组二的中位数的索引
//
//        //这
//        return null;
//    }
}
