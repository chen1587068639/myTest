package com.example.test.algorithm;

import com.example.test.common.ListCommon;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 查找元素算法
 * @Author: chengw
 * @Date: 2023/6/17 上午11:07
 */
@SpringBootTest
public class SearchElementAlgorithm {


    @Test
    public void dichotomySearchTest () {
        SortAlgorithm.insertSort(ListCommon.arrayList);
        System.out.println("打印一下数组:" + ListCommon.arrayList);
        dichotomySearch(ListCommon.arrayList,ListCommon.random.nextInt(13));
        searchNumber(ListCommon.arrayList,13);
    }

    /**
     * 二分法查找
     * @param arrayList 数组
     * @param target 目标
     */
    private static void dichotomySearch (List<Integer> arrayList,int target) {
        int n = 0;
        int startIndex = 0;
        int endIndex = arrayList.size() - 1;
        while (startIndex <= endIndex) {
            n++;
            int middleIndex = (startIndex + endIndex) / 2;
            if (arrayList.get(middleIndex) == target) {
                System.out.println("找到了，索引是：" + middleIndex + "，target：" + target);
                System.out.println("二分法查找循环次数为:" + n + "次");
                return;
            } else if (arrayList.get(middleIndex) > target) {
                endIndex = middleIndex - 1;
            } else {
                startIndex = middleIndex + 1;
            }
        }
        System.out.println("结束循环，没有找到target");
    }

    /**
     * 寻找数字target在arrayList的数组中等于target的最左侧的索引
     */
    private void searchNumber(List<Integer> arrayList,int target) {
        int startIndex = 0;
        int endIndex = arrayList.size() - 1;
        int targetIndex = -1;
        while (startIndex < endIndex) {
            int middleIndex = (startIndex + endIndex) / 2;
            if (arrayList.get(middleIndex) == target) {
                targetIndex = middleIndex;
                //如果找到了数据，就再向左侧查找，把endIndex变成targetIndex左侧的索引
                endIndex = middleIndex;
            } else if (arrayList.get(middleIndex) > target) {
                endIndex = middleIndex - 1;
            } else {
                startIndex = middleIndex + 1;
            }
        }
        System.out.println("找到了数字" + target + "在数组中最左侧的索引位置=" + targetIndex);
    }

    /**
     * 顺序查找
     * @param arrayList 数组
     * @param target 目标
     */
    private static void orderSearch (List<Integer> arrayList,int target) {
        int n = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            n++;
            if (target == arrayList.get(i)) {
                System.out.println("目标数据的索引位置为:" + i + ",数字为:" + arrayList.get(i));
                System.out.println("顺序查找循环次数为:" + n + "次");
                return;
            }
        }
        System.out.println("目标数据未在数组中");
    }

}
