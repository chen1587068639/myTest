package com.example.test.algorithm;

import com.example.test.common.ListCommon;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * 排序算法
 * @Author: chengw
 * @Date: 2023/6/16 下午9:25
 */
@SpringBootTest
public class SortAlgorithm {

    @Test
    public void selectSort(){
        List<Integer> copyList = new ArrayList<>(ListCommon.arrayList);
        List<Integer> copyTwoList = new ArrayList<>(ListCommon.arrayList);
        System.out.println("原始数据组:" + ListCommon.arrayList);
        bubblingSort(ListCommon.arrayList);
        System.out.println(ListCommon.arrayList);
        System.out.println("拷贝数据组:" + copyList);
        selectSort(copyList);
        System.out.println(copyList);
        System.out.println("拷贝数据组2:" + copyTwoList);
        insertSort(copyTwoList);
        System.out.println(copyTwoList);
    }

    /**
     * 选择排序算法
     * 时间负责度：n^2
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
     * 插入排序
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
}
