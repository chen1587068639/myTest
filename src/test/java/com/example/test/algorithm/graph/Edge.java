package com.example.test.algorithm.graph;

/**
 * 边缘线类
 * @Author: chengw
 * @Date: 2023/6/25 上午11:07
 */
public class Edge<E> {
    /**
     * 权重/长度/距离等属性
     */
    int weight;
    /**
     * 出发节点
     */
    Node<E> from;

    /**
     * 抵达节点
     */
    Node<E> to;

    public Edge(int weight, Node<E> from, Node<E> to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
