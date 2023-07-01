package com.example.test.algorithm.graph;

import java.util.HashMap;
import java.util.List;

/**
 * 图的集合
 * @Author: chengw
 * @Date: 2023/6/25 上午11:12
 */
public class Graph<E> {
    /**
     * 节点集合：相当于把节点进行编号，遍历nodes就是遍历所有节点
     */
    HashMap<E,Node<E>> nodes;
    /**
     * 线的集合：相当于把线进行编号：遍历edges就是遍历所有线
     */
    List<Edge<E>> edges;

    public Graph() {
    }

    public Graph(HashMap<E, Node<E>> nodes, List<Edge<E>> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }
}
