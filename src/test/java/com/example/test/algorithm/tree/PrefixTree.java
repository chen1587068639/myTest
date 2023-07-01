package com.example.test.algorithm.tree;

/**
 * 前缀树
 * @Author: chengw
 * @Date: 2023/7/1 上午9:41
 */
public class PrefixTree {
    /**
     * 前缀树根节点
     */
    private PrefixTreeNode root;
    public PrefixTree() {
        root = new PrefixTreeNode();
    }


    /**
     * 前缀树节点
     */
    public static class PrefixTreeNode {

        /**
         * 有多少个单词通过这个节点
         */
        public int pass;

        /**
         * 有多少个单词在这个字母结束
         */
        public int end;

        /**
         * 下属节点
         */
        public PrefixTreeNode[] nextNode; //HashMap<char,Node> nextNode;

        public PrefixTreeNode() {
            this.pass = 0;
            this.end = 0;
            this.nextNode = new PrefixTreeNode[26];
        }
    }

    /**
     * 插入单词
     * @param word 单词
     */
    public void insert(String word) {
        if (word == null) {
            return;
        }
        char[] chars = word.toCharArray();
        //获取根节点
        PrefixTreeNode node = this.root;
        //根节点记录有多少单词录入
        node.pass++;
        int index;
        for (int i = 0; i < chars.length; i++) {
            //字母属于下级哪个节点
            index = chars[i] - 'a';
            if (null == node.nextNode[index]) {
                node.nextNode[index] = new PrefixTreeNode();
            }
            //先移动到下一个节点（当前字母对应下一个节点），再pass++
            node = node.nextNode[index];
            node.pass++;
        }
        node.end++;
    }

    /**
     * 查询单词出现的频率
     * @param word 单词
     * @return 出现的次数
     */
    public int search(String word) {
        PrefixTreeNode node = searchNode(word);
        return null == node ? 0 : node.end;
    }

    /**
     * 查询单词前缀的数量
     * @param prefix 单词前缀
     * @return 数量
     */
    public int searchPre(String prefix) {
        PrefixTreeNode node = searchNode(prefix);
        return null == node ? 0 : node.pass;
    }

    public boolean delete(String word) {
        //如果单词内容为null
        if (word == null) {
            return true;
        }
        //如果前缀树没有该单词
        if (null == searchNode(word)) {
            return true;
        }
        char[] chars = word.toCharArray();
        PrefixTreeNode node = root;
        int index;
        for (int i = 0; i < chars.length; i++) {
            index = chars[i] - 'a';
            //先对下一个节点的pass减一（注意这里已经对pass--了，不用再重复操作了），再看pass是否为0，为0则删除下级节点
            if (--node.nextNode[index].pass == 0) {
                node.nextNode[index] = null;
                return true;
            }
            node = node.nextNode[index];
        }
        node.end--;
        return true;
    }

    /**
     * 查找单词结束的对应节点
     * @param word word
     * @return node
     */
    private PrefixTreeNode searchNode(String word) {
        if (word == null) {
            return null;
        }
        char[] chars = word.toCharArray();
        PrefixTreeNode node = root;
        int index;
        for (int i = 0; i < chars.length; i++) {
            index = chars[i] - 'a';
            if (node.nextNode[index] == null) {
                return null;
            }
            node = node.nextNode[index];
        }
        return node;
    }
}
