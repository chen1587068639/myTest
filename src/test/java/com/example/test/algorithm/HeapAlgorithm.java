package com.example.test.algorithm;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 堆：大根堆；小根堆
 * java中
 * @Author: chengw
 * @Date: 2023/6/18 下午5:54
 */
@SpringBootTest
public class HeapAlgorithm {

    /**
     * 默认是小根堆
     */
    private static PriorityQueue<Integer> heap = new PriorityQueue<>();

    /**
     * 改成大根堆
     */
    private static PriorityQueue<Integer> bigHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });

    static {
        heap.add(8);
        heap.add(4);
        heap.add(4);
        heap.add(9);
        heap.add(10);
        heap.add(3);
        bigHeap.add(8);
        bigHeap.add(4);
        bigHeap.add(4);
        bigHeap.add(9);
        bigHeap.add(10);
        bigHeap.add(3);
    }

    /**
     * 一组动态数字，求中位数
     * 一半数据进小根堆（小根堆存储大数字），一半进大根堆（大根堆进小数字），所以大根堆和小根堆的堆顶是中位数
     */

    /**
     * 给你n个项目和项目对应的成本，收益
     * 限制只能做n，你目前的成本是m，求最大收益
     * @param init
     * @param programNum
     * @param profits
     * @param capital
     * @return
     */
//    private static int findMaxProfit(int init,int programNum,int[] profits,int[] capital) {
//        for (int i = 0; i < capital.length; i++) {
//            heap.add(capital[i]);
//        }
//        for (int i = 0; i < programNum; i++) {
//            //解锁项目
////            while (!heap.isEmpty() && heap)
//        }
//    }

    /**
     * 霍夫曼编码问题
     * 赫夫曼编码的具体方法：先按出现的概率大小排队，把两个最小的概率相加，作为新的概率 和剩余的概率重新排队，
     * 再把最小的两个概率相加，再重新排队，直到最后变成1。每次相加时都将“0”和“1”赋与相加的两个概率，
     * 读出时由该符号开始一直走到最后的“1”， 将路线上所遇到的“0”和“1”按最低位到最高位的顺序排好，就是该符号的赫夫曼编码。
     * 一块金条切成两半，是需要花费和长度数值一样的铜板的。比如长度为20的金条，不管切成长度多大的两半，都要花费20个铜板。
     * 一群人想整分整块金条，怎么分最省铜板?
     * 输入一个数组、返回分割的最小代价。
     * @param arr
     * @return
     */
    private static int lessMoney(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            heap.add(arr[i]);
        }
        int sum = 0;
        int cur = 0;
        while (heap.size() > 1) {
            cur = heap.poll() + heap.poll();
            sum += cur;
            heap.add(cur);
        }
        return sum;
    }

    /**
     * 由大到小
     */
    @Test
    public void orderArray() {
        while (!bigHeap.isEmpty()) {
            System.out.println(bigHeap.poll());
        }
    }


    /**
     * 已知一个几乎有序的数组中(几乎有序：每个元素在排完序之后移动的位置不超过k，k相对较小)，
     * 设计一个合适的排序算法对数据进行排序
     * 由小到大
     */
    @Test
    public void almostOrderArray() {
//        //使用没有优化之前的小根堆排序：时间复杂度：O(n*log n)
//        while (!heap.isEmpty()) {
//            System.out.println(heap.poll());
//        }
        //使用没有优化之前的小根堆排序：时间复杂度：O(n)
        int[] array = {3,2,4,0,0,3,2,4,5,3,2,4,5,0};
        sortAlmostOrderedArray(array,5);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 已知k，利用k优化，当k个元素add到heap中，则poll出最小元素，然后循环添加k+i索引处的元素，poll出元素
     * 当k+i>=array.length,则跳出循环，while继续poll出剩余元素
     * 这样的话每次poll的效率都是logk，循环n之后，时间复杂度就是O(n){logk是常数，k很小，则忽略}
     * @param array
     * @param k
     */
    public void sortAlmostOrderedArray(int[] array,int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        //下一个要add到heap中的元素
        int index = 0;
        //这里Math.min判断一下，k不能越界
        for (;index <= Math.min(array.length-1,k);index++) {
            heap.add(array[index]);
        }
        //下一个已经排好序的元素索引
        int i = 0;
        for (; index < array.length; index++,i++) {
            array[i] = heap.poll();
            heap.add(array[index]);
        }
        while (!heap.isEmpty()) {
            array[i++] = heap.poll();
        }
        for (int j = 0; j < array.length; j++) {

        }

    }

    /**
     * 这里测试比较器是为了：通过比较器，实现大根堆
     */
    @Test
    public void comparator() {
        List<Integer> list = new ArrayList<Integer>();

        list.sort((o1, o2) -> {
            System.out.println("chen");
            return 0;
        });

        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                return 0;
            }
        });
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                return 0;
            }
        });
    }
}
