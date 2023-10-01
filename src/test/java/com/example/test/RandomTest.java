package com.example.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * @Author: chengw
 * @Date: 2023/7/22 上午10:33
 */
@SpringBootTest
public class RandomTest {























    private volatile static Queue<Integer> heap;

    static {
        if (heap == null) {
            synchronized(PriorityQueue.class) {
                if (heap == null) {
                    heap = new PriorityQueue<>();
                }
            }
        }
    }

    /**
     * 使用霍夫曼编码解决切割金条问题
     */
    @Test
    public void testHFM() {
        int lessMoney = lessMoney(new int[]{10, 20, 30});
        System.out.println(lessMoney);
    }

    /**
     * 切割金条函数
     * @param arr
     * @return
     */
    private int lessMoney(int[] arr) {
        int lessMoney = 0;
        if (arr.length == 0) {
            return 0;
        }
        for (int i = 0; i < arr.length; i++) {
            heap.offer(arr[i]);
        }
        while(heap.size() > 1) {
            Integer poll = heap.poll() + heap.poll();
            lessMoney += poll;
            heap.offer(poll);
        }
        return lessMoney;
    }


    private static class Node<E>{
        E item;
        List<Node<E>> nodes;
        List<Edge<E>> edges;

        public Node(E item, List<Node<E>> nodes,List<Edge<E>> edges) {
            this.item = item;
            this.nodes = nodes;
            this.edges = edges;
        }

        public Node(E item) {
            this.item = item;
        }

        public Node() {
        }
    }

    private static class Edge<E>{
        //from节点
        Node<E> form;
        //to节点
        Node<E> to;
        //权重
        Integer weight;
        public Edge(Node<E> form, Node<E> to, Integer weight) {
            this.form = form;
            this.to = to;
            this.weight = weight;
        }
    }

    /**
     * 测试dijkstra算法
     */
    @Test
    public void testDijkstra() {

    }

    /**
     * dijkstra算法：
     * 1。 起始节点放入map中
     * 2. 遍历起始节点的next节点集合，放入map集合距离集合
     * 返回每个节点和对应的最短路径
     * @param node 起始节点
     * @return 集合
     */
    private Map<Node<Integer>,Integer> dijkstra(Node<Integer> node) {
        //key:节点，value:最短路径
        Map<Node<Integer>,Integer> map = new HashMap<>();
        map.put(node,0);
        loop(map,node);
        return map;
    }

    Queue<Node<Integer>> queue = new PriorityQueue<>();
    /**
     * 根据当前节点的下级节点判断是否更新map距离集合
     * @param map
     * @param node
     */
    private void loop(Map<Node<Integer>,Integer> map,Node<Integer> node) {
        if (queue.contains(node)) {
            return;
        }
        queue.offer(node);
        Node<Integer> minNode = node.edges.get(0).to;
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < node.edges.size(); i++) {
            //线路的距离
            int distance = map.get(node) + node.edges.get(i).weight;
            if (!map.containsKey(node.edges.get(i).to)) {
                //这个节点到到下个节点的距离
                map.put(node.edges.get(i).to,distance);
            } else {
                //map中已经有节点信息了，就比较哪个线路最短，然后更新map值
                map.put(node.edges.get(i).to,map.get(node.edges.get(i).to) <= distance ? map.get(node.edges.get(i).to) : distance);
            }
            minDistance = node.edges.get(i).weight < minDistance ?  node.edges.get(i).weight : minDistance;
            if (node.edges.get(i).weight < minDistance) {
                minNode = node.edges.get(i).to;
            }
        }
        //找出距离最短的那一个节点，接着loop
        loop(map,minNode);
    }
}
