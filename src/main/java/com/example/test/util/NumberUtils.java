package com.example.test.util;

import java.util.List;

/**
 * @Author: chengw
 * @Date: 2023/3/28 下午2:59
 */
public class NumberUtils {

    /**
     * 输入数值list，求几何平均数
     * @param numbers
     * @return
     */
    public static double geometricMean(List<Integer> numbers,int length) {
        int n = numbers.size();
        double product = 1.0;

        for (int i = 0; i < n; i++) {
            product *= numbers.get(i);
        }

        return Math.pow(product, 1.0 / length);
    }

    /**
     * 计算方差
     * @param arr
     * @return
     */
    public static double calculateVariance(List<Integer> arr) {
        //元素和
        double sum = 0;
        //平均值
        double mean = 0;
        //方差
        double variance = 0;

        // 计算元素和
        for (int i = 0; i < arr.size(); i++) {
            sum += arr.get(i);
        }

        // 计算平均值
        mean = sum / arr.size();

        // 计算每个元素与平均值的差的平方，并将所有差的平方相加
        for (int i = 0; i < arr.size(); i++) {
            variance += Math.pow((arr.get(i) - mean), 2);
        }

        variance = variance / arr.size();
        return variance;
    }

    /**
     * 计算标准差
     * @param data
     * @return
     */
    public static double calculateStandardDeviation(List<Integer> data) {
        double stdDev = 0.0;
        int n = data.size();
        double mean = calculateMean(data);
        // 计算标准差
        for (double a : data)
            stdDev += Math.pow(a - mean, 2);
        return Math.sqrt(stdDev / (n - 1));
    }


    /**
     * 计算平均值
     * @param data
     * @return
     */
    public static double calculateMean(List<Integer> data) {
        double sum = 0.0;
        int n = data.size();
        for (double a : data)
            sum += a;
        return sum / n;
    }
}
