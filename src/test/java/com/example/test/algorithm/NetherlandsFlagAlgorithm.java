package com.example.test.algorithm;

import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * 荷兰国旗问题
 * @Author: chengw
 * @Date: 2023/6/18 上午10:25
 */
public class NetherlandsFlagAlgorithm {

    private static int[] array = {3,2,4,5,0,6,9,1,8,3,6};

    private static int num = 4;

    /**
     * 给定一个数组arr，和一个数num，
     * 请把小于等于num的数放到数组的左边，大于num的数放到数组的右边
     * 要求扼腕空间复杂对O(1)，时间复杂度O(N)
     */
    @Test
    public void netherlandsFlagOne() {
        process(array,num);
        for (int i : array) {
            System.out.print(i + " ");
        }
    }

    //{3,2,4,5,0,6,9,1,8,3,6},5
    public void process(int[] array, int num) {
        int left = 0;       // 左边界索引
        int right = array.length - 1;    // 右边界索引
        int i = 0;
        while (i <= right) {
            if (array[i] <= num) {
                swap(array, i, left);
                left++;
                i++;
            } else {
                swap(array, i, right);
                right--;
            }
        }
    }

    /**
     * 交换数组元素
     * @param arrayList 数组
     * @param i 索引
     * @param j 索引
     */
    private static void swap(int[] arrayList,int i , int j) {
        int tmp = arrayList[i];
        arrayList[i] = arrayList[j];
        arrayList[j] = tmp;
    }


    /**
     * 交换数组元素
     * @param array 数组
     * @param i 索引
     * @param j 索引
     */
    private static void swapByXOR(int[] array, int i , int j) {
        //[i] = i ^ j
        array[i] = array[i] ^ array[j];
        //[j] = i ^ j ^ j = i
        array[j] = array[i] ^ array[j];
        //[i] = i ^ j ^ i  = j
        array[i] = array[i] ^ array[j];
    }

    /**
     * 给定一个数组arr，和一个数num，
     * 请把小于num的数放到数组的左边，等于num的数放到数组的中间，大于num的数放到数组的右边
     * 要求扼腕空间复杂对O(1)，时间复杂度O(N)
     */
    @Test
    public void netherlandsFlagTwo() {

    }
}
