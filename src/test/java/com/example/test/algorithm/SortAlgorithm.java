package com.example.test.algorithm;

import com.example.test.common.ListCommon;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * 排序算法
 * @Author: chengw
 * @Date: 2023/6/16 下午9:25
 */
@SpringBootTest
public class SortAlgorithm {


    @Test
    public void selectSort(){
//        List<Integer> copyList = new ArrayList<>(ListCommon.arrayList);
//        List<Integer> copyTwoList = new ArrayList<>(ListCommon.arrayList);
//        System.out.println("原始数据组:" + ListCommon.arrayList);
//        bubblingSort(ListCommon.arrayList);
//        System.out.println(ListCommon.arrayList);
//        System.out.println("拷贝数据组:" + copyList);
//        selectSort(copyList);
//        System.out.println(copyList);
//        System.out.println("拷贝数据组2:" + copyTwoList);
//        insertSort(copyTwoList);
//        System.out.println(copyTwoList);
//        List<Integer> copyThreeList = new ArrayList<>(ListCommon.arrayList);
//        System.out.println("拷贝数据组2:" + copyThreeList);
//        mergeSort(copyThreeList);
//        System.out.println(copyThreeList);
//        quickSort(arr,0,arr.length-1);
//        System.out.println(Arrays.toString(arr));
        int[] arr = {3,7,2,9,0,1,5,5,8,4,6,5};
        testBubblingSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    public static void testBubblingSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    testSwap(arr,j,j + 1);
                }
            }
        }
    }
    private static void testSwap(int[] arr,int i,int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    /**
     * 选择排序算法
     * 时间复杂度：n^2
     * 稳定性：不稳定
     * 空间复杂度：O(1)
     * @param arrayList 数组
     */
    public static void selectSort(List<Integer> arrayList) {
        int n = 0;
        for (int i = 0; i < arrayList.size() - 1; i++) {
            //最小值索引
            int minIndex = i;
            for (int j = i + 1; j < arrayList.size(); j++) {
                minIndex = arrayList.get(j) < arrayList.get(minIndex) ? j : minIndex;
                n++;
            }
            swapByXOR(arrayList,i,minIndex);
        }
        System.out.println("选择排序循环了:" + n + "次");
    }

    /**
     * 冒泡排序
     * 时间复杂度：O(n^2)
     * 稳定性：稳定
     * 空间复杂度：O(1)
     * @param arrayList 数组
     */
    public static void  bubblingSort(List<Integer> arrayList){
        int n = 0;
        for (int j = arrayList.size(); j > 0; j--) {
            for (int i = 0; i < arrayList.size() - 1; i++) {
                if (arrayList.get(i) > arrayList.get(i + 1)) {
                    swap(arrayList,i,i+1);
                }
                n++;
            }
        }
        System.out.println("冒泡排序循环了:" + n + "次");
    }

    /**
     * 插入排序：
     * 时间复杂度：O(n^2)
     * 稳定性：稳定
     * 空间复杂度：O(1)
     * @param arrayList 数组
     */
    public static void insertSort(List<Integer> arrayList) {
        if (null == arrayList || 2 > arrayList.size()) {
            return;
        }
        int n = 0;
        for (int i = 1; i < arrayList.size(); i++) {
            for (int j = i;j > 0 && arrayList.get(j) < arrayList.get(j -1); j--) {
                swapByXOR(arrayList,j,j-1);
                n++;
            }
        }
        System.out.println("插入排序循环了:" + n + "次");
    }

    /**
     * 归并排序算法：
     * 时间复杂度：O(n * log N)
     * 稳定性：稳定
     * 空间复杂度：O(n)
     * 把数组分成两个子数组，使用递归的方式排序
     * @param arrayList 数组
     */
    private static void mergeSort(List<Integer> arrayList) {
        process(arrayList,0,arrayList.size()-1);
    }

    private static void process(List<Integer> arrayList ,int i,int j) {
        if (i == j) {
            return;
        }
        int middleIndex = (i + j) >> 1;
        process(arrayList,i,middleIndex);
        process(arrayList,middleIndex + 1,j);
        merge(arrayList,i,middleIndex,j);
    }

    /**
     * 两个数组合并
     * @param arrayList 数据
     * @param i 开始索引
     * @param middleIndex 中间索引
     * @param j 结束索引
     */
    private static void merge(List<Integer> arrayList,int i ,int middleIndex,int j) {
        if (i == j) {
            return;
        }
        List<Integer> helpList = new ArrayList<>(j - i + 1);
        //help数组下一个应该插入的索引位置
        int l1 = i;
        int l2 = middleIndex + 1;
        while (l1 <= middleIndex && l2 <= j) {
            if (arrayList.get(l1) < arrayList.get(l2)) {
                helpList.add(arrayList.get(l1));
                l1++;
            } else {
                helpList.add(arrayList.get(l2));
                l2++;
            }
        }
        while (l1 <= middleIndex) {
            helpList.add(arrayList.get(l1));
            l1++;
        }
        while (l2 <= j) {
            helpList.add(arrayList.get(l2));
            l2++;
        }
        for (int k = 0; k < helpList.size(); k++) {
            arrayList.set(i + k,helpList.get(k));
        }
    }

    /**
     * 快速排序：
     * 时间复杂度：O(n * log n)
     * 稳定性：不稳定
     * 空间复杂度：O(log n)
     * @param arr 数组
     */
    private void quickSort(long[] arr,int i,int j) {
        if (i >= j) {
            return;
        }
        Random random = new Random();
        int pivotIndex = random.nextInt(j - i + 1) + i;
        int pivot = partition(arr, i, j,pivotIndex);
        // 对基准值左侧和右侧的子数组递归调用快速排序
        quickSort(arr,i,pivot - 1);
        quickSort(arr,pivot + 1,j);
    }


    /**
     * 堆排序
     * 时间复杂度：O(n * log n)
     * 稳定性：不稳定
     * 空间复杂度：O(1)
     * @param arr 数组
     */
    private void heapSort(long[] arr) {
        Queue<Long> heap = new PriorityQueue<>();
        for (long l : arr) {
            heap.add(l);
        }
        for (int i = 0; i < arr.length; i++) {
            if (!heap.isEmpty()) {
                arr[i] = heap.poll();
            }
        }

    }

    /**
     * 根据基准索引分片，基准索引左边的小于等于基准值，右边的大于等于基准值
     * @param arr 数组
     * @param i
     * @param j
     * @return
     */

    private int partition(long[] arr, int i, int j, int pivotIndex) {
        // 将基准值移到结尾
        if (pivotIndex != j) {
            swap(arr, pivotIndex, j);
        }
        long pivot = arr[j];
        int left = i;
        int right = j - 1;
        //使用 left <= right 条件可以处理边界情况
        while (left <= right) {
            //arr[left] 大于pivot的时候，跳出循环
            while (left <= right && arr[left] <= pivot) {
                // 先移动头部索引，向后，直到找到大于基准值的索引停止
                left++;
            }
            //arr[right] 小于pivot的时候，跳出循环
            while (left <= right && arr[right] >= pivot) {
                // 先移动尾部索引，向前，直到找到小于基准值的索引停止
                right--;
            }
            if (left < right) {
                swap(arr, left, right);
            }
        }
        // 将基准值放到正确的位置
        if (left != j) {
            swap(arr, left, j);
        }

        return left;
    }

    /**
     * 交换数组元素
     * @param arrayList 数组
     * @param i 索引
     * @param j 索引
     */
    private static void swap(List<Integer> arrayList,int i , int j) {
        int tmp = arrayList.get(i);
        arrayList.set(i,arrayList.get(j));
        arrayList.set(j,tmp);
    }

    /**
     * 交换数组元素
     * @param arrayList 数组
     * @param i 索引
     * @param j 索引
     */
    private static void swapByXOR(List<Integer> arrayList,int i , int j) {
        //[i] = i ^ j
        arrayList.set(i,arrayList.get(i) ^ arrayList.get(j));
        //[j] = i ^ j ^ j = i
        arrayList.set(j,arrayList.get(i) ^ arrayList.get(j));
        //[i] = i ^ j ^ i  = j
        arrayList.set(i,arrayList.get(i) ^ arrayList.get(j));
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
