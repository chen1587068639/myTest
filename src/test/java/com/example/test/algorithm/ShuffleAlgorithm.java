package com.example.test.algorithm;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/**
 * 洗牌算法
 * @Author: chengw
 * @Date: 2023/7/19 下午5:20
 */
@SpringBootTest
public class ShuffleAlgorithm {

    @Test
    public void shuffleTest() {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        shuffle(arr);
        System.out.println(Arrays.toString(arr));
    }


    /**
     * 把数组打乱
     * @param arr 数组
     */
    private void shuffle(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int swapIndex = (int) (Math.random() * i);
            swap(arr,i,swapIndex);
        }
    }

    /**
     * 交换元素
     * @param arr 数组
     * @param i
     * @param j
     */
    private void swap(int[] arr,int i,int j) {
        int iCopy = arr[i];
        arr[i] = arr[j];
        arr[j] = iCopy;
    }
}
