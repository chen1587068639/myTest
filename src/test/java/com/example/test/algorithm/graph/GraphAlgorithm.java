package com.example.test.algorithm.graph;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * 图算法
 * 掌握图的数据结构到图的数据结构的转化
 * 掌握算法，不同的数据结构转化成固定的自己掌握的数据结构，然后使用自己掌握的算法
 * @Author: chengw
 * @Date: 2023/6/24 下午6:42
 */
@SpringBootTest
public class GraphAlgorithm {

    @Test
    public void test() {
        Node<Integer> integerNodeA = new Node<>(1,new ArrayList<>(),null);

        Node<Integer> integerNodeB = new Node<>(2,new ArrayList<>(),null);
        Node<Integer> integerNodeC = new Node<>(3,new ArrayList<>(),null);
        Node<Integer> integerNodeD = new Node<>(4,new ArrayList<>(),null);

        integerNodeA.nodes.add(integerNodeB);
        integerNodeA.nodes.add(integerNodeC);
        integerNodeA.nodes.add(integerNodeD);


        Node<Integer> integerNodeE = new Node<>(5,new ArrayList<>(),null);

        integerNodeB.nodes.add(integerNodeE);

        integerNodeE.nodes.add(integerNodeA);

        deepLoop(integerNodeA);
        deepLoopByRecursion(integerNodeA);
    }

    /**
     * 图按照宽度遍历
     * @param node 某一个节点
     */
    private void widthLoop(Node<Integer> node) {
        if (node == null) {
            return;
        }
        //利用queue实现同宽度进队列，set对遍历的node判断如果已经进入队列则跳过
        Queue<Node<Integer>> queue = new LinkedList<>();
        Set<Node<Integer>> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        System.out.println(node);
        while (!queue.isEmpty()) {
            Node<Integer> current = queue.poll();
            System.out.println(current.item);
            for (Node<Integer> n : current.nodes) {
                if (!set.contains(n)) {
                    queue.add(n);
                    set.add(n);
                }
            }
        }
    }





    /**
     * 图按照深度遍历
     * @param node 某一个节点
     */
    private void deepLoop(Node<Integer> node) {
        if (node == null) {
            return;
        }
        Stack<Node<Integer>> stack = new Stack<>();
        Set<Node<Integer>> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        System.out.println(node);
        while (!stack.isEmpty()) {
            Node<Integer> current = stack.pop();
            for (Node<Integer> next : current.nodes) {
                if (!set.contains(next)) {
                    stack.push(current);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.item);
                    break;
                }
            }
        }
    }

    /**
     * 深度遍历通过遍历
     * @param node
     */
    private void deepLoopByRecursion(Node<Integer> node) {
        Set<Node<Integer>> set = new HashSet<>();
        process(node,set);
    }

    private void process(Node<Integer> node,Set<Node<Integer>> set) {
        if (node == null) {
            return;
        }
        set.add(node);
        System.out.println("深度遍历:" + node.item);
        for (Node<Integer> next : node.nodes) {
            if (!set.contains(next)) {
                process(next,set);
            }
        }
    }


    /**
     * 拓扑排序：
     * 要给予一个没有前驱的顶点且输出
     * @param node 某一个节点
     */
    private void topologySort(Node<Integer> node) {

    }

    /**
     * 图数据结构->从别的数据结构转换成熟悉的数据结构
     * 从一个二维数组提取图数据转换到数据graph类型中
     * @param nodes 某一个节点
     */
    private void convert(int[][] nodes) {
        Graph<Integer> graph = new Graph<>();
        for (int[] node : nodes) {
            //出发节点
            int from = node[0];
            //抵达节点
            int to = node[1];
            //线的权重
            int weight = node[2];
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from,new Node<>(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to,new Node<>(to));
            }
            Node<Integer> fromNode = graph.nodes.get(from);
            Node<Integer> toNode = graph.nodes.get(to);
            Edge<Integer> edge = new Edge<>(weight,fromNode,toNode);
            fromNode.nodes.add(toNode);
            fromNode.out++;
            fromNode.in++;
            fromNode.edges.add(edge);
            graph.edges.add(edge);
        }
    }

    /**
     * dj特斯拉算法,计算某个节点到所有节点的最小路径
     * 注意：需要没有负数的节点
     * @param head 开始节点
     */
    private static HashMap<Node<Integer>,Integer> dijkstra(Node<Integer> head) {
        //维护一个map，key值是节点，value值是head到该节点的距离
        HashMap<Node<Integer>,Integer> distanceMap = new HashMap<>();
        //放入头节点，头节点到本身的距离是0
        distanceMap.put(head,0);
        //求过距离的节点放到set中，不再求距离了
        Set<Node<Integer>> selectNode = new HashSet<>();
        //得到在distanceMap中找到距离最小的节点
        Node<Integer> minNode = getMinDistance(distanceMap, selectNode);
        while(minNode != null) {
            //得出minNode节点到head节点的距离，计算minNode的下级节点的距离
            int distance = distanceMap.get(minNode);
            for (Edge<Integer> edge: minNode.edges) {
                //minNode的下一个节点
                Node<Integer> to = edge.to;
                //如果从没有计算to节点到head的距离，则put
                if (null != distanceMap.get(to)) {
                    distanceMap.put(to,distance + edge.weight);
                }
                distanceMap.put(to,Math.min(distanceMap.get(to),distance + edge.weight));
            }
            selectNode.add(minNode);
            minNode = getMinDistance(distanceMap,selectNode);
        }
        return distanceMap;
    }

    /**
     * 在distanceMap中找到距离最小的节点（但是不能在selectMap中）
     * @param distanceMap 距离集合
     * @param selectNode 已求过距离的节点集合
     * @return 返回最小距离节点
     */
    private static Node<Integer> getMinDistance(Map<Node<Integer>,Integer> distanceMap,Set<Node<Integer>> selectNode) {
        //最小距离节点
        Node<Integer> minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node<Integer>,Integer> n : distanceMap.entrySet()) {
            Node<Integer> currentNode = n.getKey();
            Integer distance = n.getValue();
            if (!selectNode.contains(currentNode) && distance < minDistance) {
                minNode = currentNode;
                minDistance = distance;
            }
        }
        return minNode;
    }

}
