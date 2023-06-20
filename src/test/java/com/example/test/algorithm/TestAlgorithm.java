package com.example.test.algorithm;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @Author: chengw
 * @Date: 2023/6/19 下午4:54
 */
@SpringBootTest
public class TestAlgorithm {

    private static long[] arr = {5,9,0,2,7,4,1,3,6,8};

    @Test
    public void selectSortTest() {
        //测试排序算法：其中包括：选择排序，冒泡排序，插入排序，归并排序，堆排序（可以利用priorityQueue），快速排序，计数排序，基数排序（计数排序，基数排序都属于桶排序）
//        selectSort(arr);
//        bubblingSort(arr);
//        insertSort(arr);
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 选择排序：
     * 时间复杂度：O(n^2)
     * 稳定性：不稳定
     * 空间复杂度；O(1)
     * @param arr 数组
     */
    private void selectSort(long[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            //要移到list前面的索引
            int target = i;
            for (int j = i + 1; j < arr.length; j++) {
                target = arr[target] > arr[j] ? j : target;
            }
            if (i != target) {
                swap(arr,i,target);
            }
        }
    }


    /**
     * 冒泡排序：
     * 时间复杂度：O(n^2)；
     * 稳定性；稳定
     * 空间复杂度：O(1)
     * {5,9,0,2,7,4,1,3,6,8}
     * @param arr 数组
     */
    private void bubblingSort(long[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j+1]) {
                    swap(arr,j,j+1);
                }
            }
        }
    }

    /**
     * 插入排序，每次让前n个元素保持有序,
     * 时间复杂度：O(n^2)
     * 稳定性：稳定
     * 空间复杂度：O(1)
     * @param arr 数组
     */
    private void insertSort(long[] arr) {
        if (null != arr && arr.length == 1) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j-1]) {
                    swap(arr,j,j- 1);
                }
            }
        }
    }

    /**
     * 归并排序：把数组递归分组排序
     * 时间复杂度：O(n * log n)
     * 稳定性：稳定
     * 空间复杂度：O(log n)
     * @param arr 数组
     */
    private void mergeSort(long[] arr) {
        process(arr,0,arr.length - 1);
    }

    /**
     * 递归调用merge方法对数组排序
     * @param arr
     * @param i
     * @param j
     */
    private void process(long[] arr,int i,int j) {
        if (i == j) {
            return;
        }
        int middle = (i + j) >> 1;
        process(arr,i,middle);
        process(arr,middle + 1,j);merge(arr,i,middle,j);
    }

    /**
     * 对arr[i],arr[middle]已经有序 和arr[middle + 1],arr[j]的数组进行merge排序
     * @param arr 数组
     * @param i 已经排好序的起始索引
     * @param middle 已经排好序的中值索引
     * @param j 已经排好序的结束索引
     */
    private void merge(long[] arr,int i,int middle,int j) {
        long[] help = new long[j-i+1];
        if (i == j) {
            return;
        }
        int helpIndex = 0;
        int l1 = i;
        int l2 = middle + 1;
        while (l1 <= middle && l2 <= j) {
            help[helpIndex++] = arr[l2] < arr[l1] ? arr[l2++] : arr[l1++];
        }
        while (l1<=middle) {
            help[helpIndex++] = arr[l1++];
        }
        while (l2<= j) {
            help[helpIndex++] = arr[l2++];
        }
        for (long l : help) {
            arr[i++] = l;
        }
    }

    @Test
    public void testRandom() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            System.out.println(random.nextInt(2));
        }
    }
    /**
     * 交换数组中的元素
     * @param arr 数组
     * @param i target
     * @param j move
     */
    private void swap(long[] arr,int i,int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
