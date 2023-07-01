package com.example.test.algorithm.tree;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 前缀树测试
 * @Author: chengw
 * @Date: 2023/7/1 上午9:41
 */
@SpringBootTest
public class PrefixTreeAlgorithm {
    private PrefixTree prefixTree = new PrefixTree();

    @Test
    public void testWordSearch() {
        prefixTree.insert("chen");
        System.out.println(prefixTree.search("chen"));
        System.out.println(prefixTree.searchPre("ch"));
        prefixTree.delete("chen");
        System.out.println(prefixTree.search("chen"));
    }


}
