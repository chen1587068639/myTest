package com.example.test.common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @Author: chengw
 * @Date: 2023/6/17 上午10:07
 */
public class ListCommon {

    /**
     * 无序数组
     */
    public static List<Integer> arrayList = new ArrayList<>();

    /**
     * 无序链表
     */
    public static List<Integer> linkedList = new LinkedList<>();

    /**
     * 该数组中出现奇数次的元素一共有两个
     */
    public static List<Integer> oddList = new ArrayList<>();

    public static Random random = new Random();

    private static int number = 30;
    static {
        for (int i = 0; i < 100; i++) {
            arrayList.add(random.nextInt(number));
            linkedList.add(random.nextInt(number));
        }
    }

    static {
        oddList.add(143);
        oddList.add(143);
        oddList.add(143);
        oddList.add(143);
        oddList.add(143);
        oddList.add(143);
        oddList.add(222);
        oddList.add(222);
        oddList.add(222);
        oddList.add(1000);
        oddList.add(1000);
        oddList.add(1000);
        oddList.add(1000);
        oddList.add(1000);
        oddList.add(1000);
        oddList.add(32);
    }

}
