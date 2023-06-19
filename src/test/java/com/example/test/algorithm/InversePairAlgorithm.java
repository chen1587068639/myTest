package com.example.test.algorithm;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 逆序对打印问题
 * @Author: chengw
 * @Date: 2023/6/18 上午9:59
 */
@SpringBootTest
public class InversePairAlgorithm {

    private static int[] example = {3,2,4,5,0};

    /**
     * 逆序对问题，在一个数组中，左边的数如果比右边的数大，则这两个数构成一个逆序对，请打印所有的逆序对
     * excample数组中应该有如下逆序对:3,2;3,0;2,0,4,0;5,0;一共舞队
     */
    @Test
    public void inversePair() {
        process(example,0,example.length - 1);

    }

    private void process(int[] array,int i,int j) {
        if (i == j) {
            return;
        }
        int middle = (i + j) >> 1;
        process(array,i,middle);
        process(array,middle + 1,j);
        merge(array,i,middle,j);
    }


    /**
     * 逆序对问题，在一个数组中，左边的数如果比右边的数大，则这两个数构成一个逆序对，请打印所有的逆序对
     * @param array 数组
     * @param i 起点头部索引
     * @param middle 中间位置
     * @param j 数组尾部索引
     * @return
     */

    private void merge(int[] array, int i, int middle, int j) {
        int[] help = new int[j-i+1];
        int helpIndex = 0;
        int l1 = i;
        int l2 = middle + 1;
        while (l1 <= middle && l2 <= j) {
            if (array[l1] > array[l2]) {
                //当array[l1]大于array[l2]的时候，那么在array[l1]到array[middle]闭区间中，所有值都大于大于array[l2]，则需要循环打印
                for (int k = l1; k <= middle; k++) {
                    System.out.println("逆序对：" + array[k] + "，" + array[l2]);
                }
                help[helpIndex++] = array[l2++];
            } else {
                help[helpIndex++] = array[l1++];

            }
        }
        while (l1<=middle) {
            help[helpIndex++] = array[l1++];
        }
        while (l2<=j) {
            help[helpIndex++] = array[l2++];
        }
        for (int k = 0; k < help.length; k++) {
            array[i + k] = help[k];
        }
    }

}
