package com.example.test;

import com.example.test.collections.StackQueue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: chengw
 * @Date: 2023/8/6 上午11:23
 */
@SpringBootTest
public class AlgorithmTest {

    public static int[] arr = {7,8,1,2,4,5,3,6,7,8};

    @Test
    public void test() {
//        merge(arr);
//        System.out.println(Arrays.toString(arr));
//        int[] a = {1,4,8,9,10};
//        int[] b = {1,4,5,6,45,69,70};
//        System.out.println(Arrays.toString(merge(a,b)));
        String s = "chcecn";
        System.out.println(search(s));
        new StackQueue<Integer>();
    }
    public String search(String s) {
        int start = 0;
        int end = 0;
        int finishStart = 0;
        int finishEnd = 0;
        HashSet<Character> charSet = new HashSet<>(); // 用于存储当前窗口内的字符
        while (end < s.length()) {
            char c = s.charAt(end);
            if (charSet.contains(c)) {
                charSet.remove(s.charAt(start));
                start++;
            } else {
                charSet.add(c);
                end++;
                if (finishEnd - finishStart < end - start) {
                    finishStart = start;
                    finishEnd = end;
                }
            }
        }
        return s.substring(finishStart, finishEnd);
    }

    public int[] merge(int[] a,int[] b) {
        int[] mergeArr = new int[Math.max(a.length, b.length)];
        int mergeIndex = 0; //合并数组的指针
        int aIndex = 0;//a的指针
        int bIndex = 0;//b的指针
        while(aIndex < a.length && bIndex < b.length) {
            if(a[aIndex] == b[bIndex]) {
                mergeArr[mergeIndex++] = a[aIndex++];
                bIndex++;
            } else if(a[aIndex] > b[bIndex]) {
                bIndex++;
            } else {
                aIndex++;
            }
        }
        while(aIndex < a.length) {
            if(a[aIndex] == b[bIndex]) {
                mergeArr[mergeIndex] = a[aIndex];
                break;
            }
            aIndex++;
        }
        while(bIndex < b.length) {
            if(a[aIndex] == b[bIndex]) {
                mergeArr[mergeIndex] = b[bIndex];
                break;
            }
            bIndex++;
        }
        return Arrays.copyOf(mergeArr, mergeIndex);
    }

    /**
     * 归并排序：时间复杂度：O(O * log n)，稳定
     * @param arr 数组
     */
    private void merge(int[] arr) {
        process(arr,0,arr.length - 1);
    }

    private void process(int[] arr,int i,int j) {
        if (i == j) {
            return;
        }
        int middle = (i + j) >> 1;
        process(arr,i,middle);
        process(arr,middle + 1,j);
        mergeArray(arr,i,middle,j);
    }

    /**
     * 以middle为分界线合并两个数组
     * @param arr 数组
     * @param i 开始索引
     * @param middle 中间索引
     * @param j 结束索引
     */
    protected void mergeArray(int[] arr,int i,int middle,int j) {
        if (i == j){
            return;
        }
        //前一个数组指针位置
        int i1 = i;
        //后一个数组指针位置
        int i2 = middle + 1;
        int[] help = new int[j - i + 1];
        int helpIndex = 0;
        while (i1 <= middle && i2 <= j) {
            if (arr[i1] < arr[i2]) {
                help[helpIndex++] = arr[i1++];
            } else {
                help[helpIndex++] = arr[i2++];
            }
        }
        while (i1 <= middle) {
            help[helpIndex++] = arr[i1++];
        }

        while (i2 <= j) {
            help[helpIndex++] = arr[i2++];
        }
        for (int k = 0; k < help.length;k++) {
            arr[i++] = help[k];
        }
    }

    /**
     * 插入排序,时间复杂度O(n ^ 2)，稳定
     * @param arr 数组
     */
    private void insertSort(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length - 1; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                swap(arr,j,j - 1);
            }
        }
    }


    /**
     * 堆排序，时间复杂度O(n * log n)
     * @param arr 数组
     */
    private void heapSort(int[] arr) {
        //小根堆
        Queue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < arr.length - 1; i++) {
            heap.offer(arr[i]);
        }
        for (int i = 0; i < arr.length - 1; i++) {
            arr[i] = heap.poll();
        }
    }

    /**
     * 冒牌排序
     * @param arr
     */
    private void bubbling(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j+1]) {
                    swap(arr,j,j+1);
                }
            }
        }
    }

    /**
     * 交换数组中不同索引的元素，如果是同一个索引的话会把元素变成0
     * @param arr
     * @param a
     * @param b
     */
    private void swap(int[] arr,int a,int b) {
        arr[a] = arr[a] ^ arr[b];
        arr[b] = arr[a] ^ arr[b];
        arr[a] = arr[a] ^ arr[b];
    }




    /**
     * 二分法查找
     */
    private int search(int[] arr ,int target) {
        if (arr.length < 1) {
            return -1;
        }
        int a = 0;
        int b = arr.length - 1;
        while (a <= b) {
            int mid = (a + b) >> 1;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                b = mid - 1;
            } else {
                a = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 递归二分法查找数组元素
     * @param arr 数组
     * @param a 开始索引
     * @param b 结束索引
     * @return 返回索引位置
     */
    private static int recursionSearch(int[] arr,int target,int a ,int b) {
        if (arr.length < 1 || a > b) {
            return -1;
        }
        int mid = (a + b) >> 1;
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            return recursionSearch(arr,target,mid + 1,b);
        } else {
            return recursionSearch(arr,target,a,mid - 1);
        }
    }

    @Test
    public void test849() {
        System.out.println(manacher("12345678987654321"));
    }

    /**
     * manacher算法计算回文字符串
     * 时间复杂度:O(n)
     * @param str 字符串
     */
    private int manacher(String str) {
        String handler = handler(str);
        //存放每一个handler对应下标下的回文字符串长度
        int[] result = new int[handler.length()];
        //回文字符串的终点位置
        int r = 0;
        //回文字符串的中心位置
        int c = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < handler.length(); i++) {
            result[i] = r > i ? Math.min(result[2 * c - i],r-i) : 1;
            while (i + result[i] < handler.length() && i - result[i] >= 0) {
                if (handler.charAt(i + result[i]) == handler.charAt(i - result[i])) {
                    result[i]++;
                } else {
                    break;
                }
            }
            if (i + result[i] > r) {
                r = i + result[i];
                c = i;
            }
            max = Math.max(max,result[i]);
        }
        return max - 1;
    }

    /**
     * 转换字符串
     * @param str 字符串
     */
    private String handler(String str) {
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder("*");
        for (char c: chars){
            sb.append(c).append("*");
        }
        return sb.toString();
    }

    @Test
    public void test748() {
        int[] arr = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(sum(arr));
    }

    /**
     * 题意是在一个整数数组中，找到一个连续的子数组，使得这个子数组的元素之和最大：子数组最大和
     * Kadane算法：卡登算法
     * 时间复杂度:O(n)
     * @param arr 数组
     * @return 最大和
     */
    private static int sum(int[] arr) {
        int maxSum = arr[0];  // 最大子数组和
        int currentSum = arr[0];  // 当前子数组和
        for (int i = 1; i < arr.length; i++) {
            // 判断当前元素自成一段还是加入前面的子数组
            currentSum = Math.max(arr[i], currentSum + arr[i]);
            // 更新最大子数组和
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }
}
