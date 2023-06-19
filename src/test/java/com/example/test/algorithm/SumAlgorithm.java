package com.example.test.algorithm;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 一组数求小和问题
 * @Author: chengw
 * @Date: 2023/6/17 下午10:43
 */
@SpringBootTest
public class SumAlgorithm {
    private static int[] array = {1,0,4,2,5};

    /**
     * 在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。求一个数组的小和
     * 求一组数的小和：(1,3,4,2,5),
     * 0索引位置1左侧没有比1小的数字，小和：0；
     * 1索引为3，1比0小，小和；1；
     * 4比，1，3大，小和：4 + 1 = 5 ；2比1大，小和：1 + 4 + 1 = 6；
     * 5比左侧所有的数字大，10 + 1 + 4 + 1 = 16
     */
    @Test
    public void smallSum() {
        int sum = process(array, 0, array.length - 1);
        System.out.println("sum = " + sum);
    }

    /**
     * 求小和
     * @param array
     * @param i
     * @param j
     * @return
     */
    private int process(int[] array,int i,int j) {
        if (i == j) {
            return 0;
        }
        //求中间值
        int middleIndex = (i + j) >> 1;
        return process(array,i,middleIndex) + process(array,middleIndex + 1,j) + merge(array,i,middleIndex,j);
    }

    /**
     * 归并排序并计算小和
     * @param array 数组
     * @param i 起点头部索引
     * @param middle 中间位置
     * @param j 数组尾部索引
     * @return
     */
    private int merge(int[] array, int i, int middle, int j) {
        int[] help = new int[j - i + 1];
        int helpIndex = 0;
        int l1 = i;
        int l2 = middle + 1;
        int sum = 0;
        while (l1 <= middle && l2 <= j) {
            //如果l1索引位置的数据小于l2索引的数据，则，sum = sum + (array[j] - array[l2] + 1) * array[l1]，否则sum = sum + 0;
            sum += array[l1] < array[l2] ? (j - l2 + 1) * array[l1] : 0;
            //如果array[l2] <= array[l1] 则，help.add(array[l2]);l2++;helpIndex++;
            help[helpIndex++] = array[l1] < array[l2] ? array[l1++] : array[l2++];
        }
        while (l1 <= middle) {
            help[helpIndex++] = array[l1++];
        }
        while (l2 <= j) {
            help[helpIndex++] = array[l2++];
        }
        for (int k = 0; k < help.length; k++) {
            array[i + k] = help[k];
        }
        return sum;
    }

}
