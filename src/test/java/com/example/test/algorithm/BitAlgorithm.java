package com.example.test.algorithm;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * bit相关算法
 * @Author: chengw
 * @Date: 2023/7/27 上午10:04
 */
@SpringBootTest
public class BitAlgorithm {

    @Test
    public void test() {
        System.out.println(compare(11241210,-10));
    }

    /**
     * 两个整数不通过比较得出较大值
     * @param a int
     * @param b int
     * @return 较大值
     */
    private int compare(int a,int b) {
        int c = a - b;
        //a大，sign为1；b大，sign为0
        int mirrorA = sign(c);
        int mirrorB = flip(mirrorA);
        return a * mirrorA + b * mirrorB;
    }

    /**
     * 传入整数返回1，传入负数返回0
     * n >> 31位后，n为负数变成-1，n为整数变成0
     * 与上1后，-1变成了1，0变成0
     * 结果再通过flip函数转换一下，就是传入整数返回1，传入负数返回0
     * @param n
     * @return
     */
    private static int sign(int n) {
        return flip((n >> 31) & 1);
    }

    /**
     * 当n=1的时候，返回值为0，当n = 0的时候，返回值为1
     * @param n 1 ｜ 0
     * @return 0 ｜ 1
     */
    private static int flip(int n) {
        return n ^ 1;
    }

    /**
     * 判断一个数字是不是2次幂
     * @param i 整数
     * @return true:是2次幂；false：不是2次幂
     */
    private boolean isTwoPow(int i) {
        return (i & (~i + 1)) == i;
    }

    /**
     * 判断一个数字是不是4次幂
     * 十六进制的0x55555555 = 二进制的0101010101......010101
     * 1。四的次幂首先要是二的次幂
     * 2。满足与上0x55555555不能等于零一定满足，唯一的一个1在0，2，4（2的倍数）...上
     * @param i 数字
     * @return true:是2次幂；false：不是2次幂
     */
    private boolean isFourPow(int i) {
        return (i & (i -1)) == 0 && (i & 0x55555555) != 0;
    }
}
