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
     * 二分法查找：本质上是递归，将数组分为左右两个子数组，通过判断target与中间值做比较，判断在哪个数组，然后重复以上操作，直到分无可分，则确定数组中没有target
     * 时间复杂度：log n
     * @param arrayList 数组
     * @param target 目标
     */
    private static int dichotomySearch(List<Integer> arrayList,int target) {
        int startIndex = 0;
        int endIndex = arrayList.size() - 1;
        while (startIndex <= endIndex) {
            int middleIndex = (startIndex + endIndex) / 2;
            if (arrayList.get(middleIndex) == target) {
                return middleIndex;
            } else if (arrayList.get(middleIndex) > target) {
                endIndex = middleIndex - 1;
            } else {
                startIndex = middleIndex + 1;
            }
        }
        return -1;
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
     * 顺序查找：对比数组中的每一个元素与target
     * @param arrayList 数组
     * @param target 目标
     */
    private static int orderSearch(List<Integer> arrayList,int target) {
        int n = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            n++;
            if (target == arrayList.get(i)) {
                return i;
            }
        }
        return -1;
    }

}
