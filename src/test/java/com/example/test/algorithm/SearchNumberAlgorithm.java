package com.example.test.algorithm;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 多个数组(多个数据源/文件)中找到最大的前500个数字
 * @Author: chengw
 * @Date: 2023/7/20 下午3:49
 */
@SpringBootTest
public class SearchNumberAlgorithm {

    public static volatile Random random;

    static {
        if (random == null) {
            synchronized(Random.class) {
                if (random == null) {
                    random = new Random();
                }
            }
        }
    }

    /**
     * 默认小根堆，改成大根堆
     */
    public static PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());

    /**
     * 有 20 个数组，每个数组有 500 个元素，并且有序排列。如何在这 20*500 个数中找出前500的数？
     * 使用heap
     */
    @Test
    public void test() {
        //20行500列的二维数组
        int[][] arr = new int[20][500];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = random.nextInt(10000);
            }
        }
        //排序
        for (int i = 0; i < arr.length; i++) {
            heapSort(arr[i]);
        }
        searchNumber(arr);
    }

    @Test
    public void test2() {
        HashMap<String,String> hashMap = new HashMap<>();
        ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<>();
        List<String> strings = Arrays.asList("chen", "chen");
        //会抛出UnsupportedOperationException，查看源码发现返回的ArrayList是Arrays的内部类ArrayList，这个ArrayList内部类底层是一个数组：E[] a;
        strings.add("asdf");
        HashSet<Integer> hashSet = new HashSet<>();
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
        TreeSet<Integer> treeSet = new TreeSet<>(Comparator.reverseOrder());
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);
        hashSet.add(4);
        hashSet.add(5);
        hashSet.add(6);
        hashSet.add(7);
        hashSet.add(8);
        hashSet.forEach(System.out::println);
        System.out.println("hashSet打印完毕");
        linkedHashSet.add(1);
        linkedHashSet.add(2);
        linkedHashSet.add(3);
        linkedHashSet.add(4);
        linkedHashSet.add(5);
        linkedHashSet.add(6);
        linkedHashSet.add(7);
        linkedHashSet.add(8);
        linkedHashSet.forEach(System.out::println);
        System.out.println("linkedHashSet打印完毕");
        treeSet.add(1);
        treeSet.add(2);
        treeSet.add(3);
        treeSet.add(4);
        treeSet.add(5);
        treeSet.add(6);
        treeSet.add(7);
        treeSet.add(8);
        treeSet.forEach(System.out::println);
        System.out.println("treeSet打印完毕");
    }

    /**
     *
     * @param arr 二维数组
     */
    private void searchNumber(int[][] arr) {
        HashSet set = new HashSet();
        //key:数字,value:数字对应的数组
        Map<Integer,Integer> map = new HashMap<>();
        //key:数字,value:数字对应的数组中的索引
        Map<Integer,Integer> indexMap = new HashMap<>();
        //把每个数组最大的数字放到大根堆中
        for (int i = 0; i < arr.length; i++) {
            int bigNum = arr[i][arr[i].length - 1];
            map.put(arr[i][arr[i].length - 1],i);
            indexMap.put(arr[i][arr[i].length - 1],arr[i].length - 1);
            heap.offer(bigNum);
        }
        Queue<Integer> queue = new LinkedList<>();
        //大根堆弹出最大数字，在map中找到对应的数组，add该数组的上一个元素
        for (int i = 0; i < 500; i++) {
            //heap中最大元素
            Integer poll = heap.poll();
            //heap中最大元素加入队列
            queue.add(poll);
            //第几个数组
            Integer arrayIndex = map.get(poll);
            //数组中第几个元素
            Integer elementIndex = indexMap.get(poll);
            //这一轮要加入堆中的元素
            int num = arr[arrayIndex][--elementIndex];
            heap.offer(num);
            indexMap.put(num,elementIndex);
            map.put(num,arrayIndex);
        }
        Map<String,String> concurrentHashMap = new ConcurrentHashMap<>();

        System.out.println(Arrays.toString(queue.toArray()));
    }



    /**
     * 堆排序
     * 时间复杂度：O(n * log n)
     * 稳定性：不稳定
     * 空间复杂度：O(1)
     * @param arr 数组
     */
    private void heapSort(int[] arr) {
        Queue<Integer> heap = new PriorityQueue<>();
        for (int l : arr) {
            heap.add(l);
        }
        for (int i = 0; i < arr.length; i++) {
            if (!heap.isEmpty()) {
                arr[i] = heap.poll();
            }
        }

    }


    public static void bubblingSort(int[] arr) {
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

}
