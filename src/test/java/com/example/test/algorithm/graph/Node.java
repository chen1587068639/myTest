package com.example.test.algorithm.graph;

import java.util.List;

/**
 * 图：节点
 * 可以抵达的节点集
 * @Author: chengw
 * @Date: 2023/6/25 上午11:03
 */
public class Node<E> {
    /**
     * 节点存在的数据
     */
    E item;

    /**
     * 能够抵达的节点集
     */
    List<Node<E>> nodes;
    /**
     * 可以抵达的节点数量
     */
    Integer out;
    /**
     * 可以到达自己的节点数量
     */
    Integer in;
    /**
     * 线的集合
     */
    List<Edge<E>> edges;

    public Node() {
    }

    public Node(E item) {
        this.item = item;
    }

    public Node(E item, List<Node<E>> nodes, List<Edge<E>> edges) {
        this.item = item;
        this.nodes = nodes;
        this.edges = edges;
    }
}
