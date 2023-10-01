package com.example.test.algorithm.collection;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;

/**
 * 滑动窗口：求最大最小值
 * 使用数据结构：双端队列：PriorityQueue
 * 时间复杂度:O(n)
 * @Author: chengw
 * @Date: 2023/7/23 下午6:23
 */
@SpringBootTest
public class SlideWindowAlgorithm {

    /**
     * 滑动窗口求最大值：
     * 维持一个双端队列
     * 当窗口滑动的时候
     * 1. 把新加入的元素通过对比队列尾部，大的话loop弹出队尾元素,直到比队尾元素小/队列为null就加入队列尾部
     * 2. 检查队列头部的元素是否已经滑出窗口范围，如果是，则将其从队列中弹出。
     * 这样队列的头部就是滑动窗口的最大值，记录下来
     */
    @Test
    public void testSlideWindow() {

    }

    private static void slideWindow(int[] arr,int num) {
        //实现了Deque接口
        List<Integer> list = new LinkedList<>();
    }
}
