package com.example.test.testAlgorithm;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @Author: chengw
 * @Date: 2023/8/16 下午2:59
 */
@SpringBootTest
public class AlgorithmOne {

    private static int[] arr = {1,2,4,5,9,11,14};
    /**
     * 给定一个有序数组arr，代表数轴上从左到右有n个点arr[0]、arr[1]...arr[n-1].（第二个点距离原点距离为：arr[1]）
     * 给定一个正数L，代表一根长度为L的绳子，求绳子最多能覆盖其中的几个点。
     * 要求：时间复杂度O(n)
     */
    @Test
    public void test() {
        System.out.println(count(arr,14));
    }

    /**
     * 创建局部变量：最大容量：macCapacity = 0;
     * @param arr 数组
     * @param l 绳子长度
     * @return 最多能覆盖的点数
     */
    private int count(int[] arr,int l) {
        if (l < 1){
            return 0;
        }
        int macCapacity = 0;
        int startIndex = 0;
        int endIndex = 0;
        //如果尾指针数组越界，终止循环
        while (endIndex <= (arr.length - 1)) {
            //判断:如果绳子长度大于起始点与终止点之间的长度，则最大容量++;尾指针++;
            if(arr[endIndex] - arr[startIndex] < l) {
                if (endIndex - startIndex + 1 > macCapacity) {
                    macCapacity = endIndex - startIndex + 1;
                }
                endIndex++;
            } else {
                startIndex++;
            }
        }
        return macCapacity;
    }

    /**
     * 小虎去附近的商店买苹果，奸诈的商贩使用了捆绑交易，只提供6个每袋和8个每袋的包装包装不可拆分。
     * 可是小虎现在只想购买恰好n个苹果，小虎想购买尽量少的袋数方便携带。如果不能购买恰好n个苹果，小虎将不会购买。
     * 输入一个整数n，表示小虎想购买的个苹果，返回最小使用多少袋子。如果无论如何都不能正好装下，返回-1。
     */
    @Test
    public void testBuyApple() {
        for (int i = 0;i<=100;i++) {
            System.out.println("买" + i + "个苹果,需要:" + buy(i) + "袋子");
        }
    }

    /**
     *
     * @param num 苹果数量
     * @return 需要的袋子
     */
    private int buy(int num) {
        //如果num是奇数则返回-1
        if (num <= 6 || 1 == num % 2) {
            return num == 6 ? 1 : -1;
        }
        int bag = num >> 3;
        int remainder = num % 8;
        //如果是8的倍数，则直接返回
        if (remainder == 0) {
            return bag;
        }
        while (remainder <= 24 && remainder <= num) {
            //
            if (0 == remainder % 6) {
                bag = bag + remainder / 6;
                break;
            }
            remainder = remainder + 8;
            bag--;
        }
        return bag;
    }

    public int maxSubsetSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }

        return dp[n-1];
    }

    /**
     * 斐波那契数列是一个递归数列，第一个和第二个数为 1，从第三个数开始，每个数都是前两个数之和。形式化表示如下：
     * 计算斐波那契数列的第 n 个数。
     */
    @Test
    public void testF() {
        int n = 9;
        System.out.println(dp(1,1,n,1));
    }

    private static int[] memo;

    /**
     *
     * @param n 斐波那契第n个数
     * @return 第n个数
     */
    public int dp(int before,int after, int n,int a) {
        if (n <= 1) {
            return n;
        }
        if (++a == n) {
            return after;
        }
        return dp(after, after + before,n,a);
    }
}
