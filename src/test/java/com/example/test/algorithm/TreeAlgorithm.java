package com.example.test.algorithm;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * 树算法
 * @Author: chengw
 * @Date: 2023/6/22 下午3:35
 */
@SpringBootTest
public class TreeAlgorithm {

    static class TreeNode<E> {
        E item;
        TreeNode<E> left;
        TreeNode<E> right;

        public TreeNode(E item) {
            this.item = item;
        }
    }

    private static TreeNode<Integer> head = new TreeNode<>(5);
    static {
        head.left = new TreeNode<>(3);
        head.left.left = new TreeNode<>(2);
        head.left.right = new TreeNode<>(4);
        head.left.left.left = new TreeNode<>(1);
        head.right = new TreeNode<>(7);
        head.right.left = new TreeNode<>(6);
        head.right.right = new TreeNode<>(8);
    }

    //递归遍历树
    //非递归遍历数
    //前序遍历，中序遍历，后序遍历，深度遍历，宽度遍历，
    //计算树的最大宽度
    @Test
    public void treeWrite() {
        //第一层
        TreeNode<Integer> treeNodeOne = new TreeNode<>(1);
        //第二层
        TreeNode<Integer> treeNodeTwo = new TreeNode<>(2);
        TreeNode<Integer> treeNodeThree = new TreeNode<>(3);
        treeNodeOne.left = treeNodeTwo;
        treeNodeOne.right = treeNodeThree;
        //第三层
        treeNodeTwo.left = new TreeNode<>(4);
        treeNodeTwo.right = new TreeNode<>(5);
        treeNodeThree.left = new TreeNode<>(6);
        treeNodeThree.right = new TreeNode<>(7);
        //        1
        //    2       3
        //  4   5   6   7
//        stackErgodicMiddle(treeNodeOne);
//        recursion(treeNodeOne);
//        countWidth(treeNodeOne);
        Boolean searchTree = isSearchTree(head);
        System.out.println(searchTree);

    }

    /**
     * 递归打印tree元素
     * 前序遍历
     * 中序遍历
     * 后序遍历
     * @param head 树的头节点
     */
    private static void recursion(TreeNode<Integer> head) {
        if (head == null) {
            return;
        }
        //头，左，右
//        System.out.println("前序遍历:" + head.item);
        recursion(head.left);
        //左，头，右
        System.out.println("中序遍历:" + head.item);
        recursion(head.right);
        //左，右，头
//        System.out.println("后序遍历:" + head.item);
    }

    /**
     * 非递归形式的遍历树：前序遍历：头左右：使用栈的方式
     * @param head 树的头节点
     */
    private static void stackErgodicByBefore(TreeNode<Integer> head) {
        //        1
        //    2       3
        //  4   5   6   7
        if (null == head) {
            return;
        }
        Stack<TreeNode<Integer>> stack = new Stack<>();
        stack.add(head);
        //从头节点开始遍历，如果头节点的左节点有则循环入栈，如果没有左节点，则弹出最先进来的节点，遍历，
        while (!stack.empty()) {
            head = stack.pop();
            System.out.println(head.item);
            if (head.right != null) {
                stack.push(head.right);
            }
            if (head.left != null) {
                stack.push(head.left);
            }
        }
    }

    /**
     * 非递归形式的遍历树：后序遍历：左右头：使用两个栈的方式
     * @param head
     */
    private static void stackErgodicByAfter(TreeNode<Integer> head) {
        if (null == head) {
            return;
        }
        Stack<TreeNode<Integer>> stack = new Stack<>();
        Stack<TreeNode<Integer>> colletStack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            head = stack.pop();
            colletStack.push(head);
            if (head.left != null) {
                stack.push(head.left);
            }
            if (head.right != null) {
                stack.push(head.right);
            }
        }
        while (!colletStack.isEmpty()) {
            System.out.println(colletStack.pop().item);
        }
    }

    /**
     * 非递归形式的遍历树：中序遍历:左头右：使用栈的方式
     * @param head 头节点
     */
    private static void stackErgodicMiddle(TreeNode<Integer> head) {
        if (null == head) {
            return;
        }
        Stack<TreeNode<Integer>> stack = new Stack<>();

        while (!stack.isEmpty() || head != null) {
            if (null != head) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                System.out.println(head.item);
                head = head.right;
            }

        }
    }

    /**
     * 宽度遍历：通过队列的方式
     */
    private static void widthErgodic(TreeNode<Integer> head) {
        if (head == null) {
            return;
        }
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            TreeNode<Integer> current = queue.poll();
            System.out.println(current.item);
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }

    /**
     * 计算二叉树的宽度：使用hashmap的方式
     * @param head
     */
    private static void countWidth(TreeNode<Integer> head) {
        if (head == null) {
            return;
        }
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(head);
        //节点，节点对应的层数
        Map<TreeNode<Integer>,Integer> map = new HashMap<>();
        map.put(head,1);
        //当前层级的节点个数
        int currentWidth = 0;
        //最大宽度
        int maxWidth = Integer.MIN_VALUE;
        //目前的层数
        int currentTier = 1;
        while (!queue.isEmpty()){
            TreeNode<Integer> current = queue.poll();
            //节点所在层
            int currentNum = map.get(current);
            if (currentNum == currentTier) {
                currentWidth++;
            } else {
                //切换到下一层，和上一层的节点数对比，取出较大值
                maxWidth = Math.max(maxWidth,currentWidth);
                //下一层层数加一
                currentTier++;
                //这个时候已经是下一层的一个节点了，所以下一层的节点个数变成1
                currentWidth = 1;
            }
            if (current.left != null) {
                map.put(current.left,currentTier+1);
                queue.add(current.left);
            }
            if (current.right != null) {
                map.put(current.right,currentTier+1);
                queue.add(current.right);
            }
            if (queue.isEmpty()) {
                maxWidth = Math.max(maxWidth,currentWidth);
            }
        }
        System.out.println("二叉树最大宽度:" + maxWidth);
    }

    private Integer preValue = Integer.MIN_VALUE;
    /**
     * 判断是否是搜索二叉树：搜索二叉树的头节点大于左节点，小于右节点
     * 递归
     * @param head 头节点
     */
    public Boolean isSearchTree(TreeNode<Integer> head) {
        //base case:如果等于null，该节点及以下子树节点为搜索二叉树
        if (head == null) {
            return true;
        }
        Boolean leftFlag = isSearchTree(head.left);
        //head.left以下为搜索二叉树，判断head是否大于head.left
        if (!leftFlag) {
            return false;
        }
        if (head.item <= preValue) {
            return false;
        } else {
            preValue = head.item;
        }
        return isSearchTree(head.right);
    }

    /**
     * 返回最大值和最小值
     * 头节点的左子树返回最大值
     * 头节点的友子树返回最小值
     * 然后判断：如果
     */
    private static int min = Integer.MAX_VALUE;
    private static int max = Integer.MIN_VALUE;
//    private Boolean isSearchTreeByLoop(TreeNode<Integer> head) {
//        if (head == null) {
//            return true;
//        }
//
//        while (head != null) {
//
//            if ()
//        }
//    }
    /**
     * 判断是否是完全二叉树：
     * @param head 头节点
     * @return
     */
//    private Boolean isCompleteTree(TreeNode<Integer> head) {
//        if (head)
//    }



    //判断是不是平衡二叉树
    //判断是不是满二叉树
    //树形DP

    //分布式锁，分布式事务
    //找两个节点的公共祖先节点：利用递归把节点和节点的夫节点放到map中

    //二叉树的字符串的序列化和反序列化

    //请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。
    // 例如:N=1时，打印: down N=2时，打印: down down up。：



}
