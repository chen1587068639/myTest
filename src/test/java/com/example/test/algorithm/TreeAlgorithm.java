package com.example.test.algorithm;

import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * 树算法
 * 完全二叉树：换句话说，对于完全二叉树，从左到右、从上到下依次填充节点，不留下空缺。
 * 平衡二叉树：左右子树之间的高度差值不超过1
 * 满二叉树：每一层的节点都是满的
 * 搜索二叉树：左节点小于头节点，右节点大于头节点
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
//        head.left.left.left.left = new TreeNode<>(10);
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
        System.out.println(isFullTree(treeNodeThree));
//        recursion(treeNodeOne);
//        countWidth(treeNodeOne);
//        Boolean searchTree = isSearchTree(head);
//        System.out.println(searchTree);

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
     * 检查是不是搜索树：栈的方式
     * @param head
     * @return
     */
    private static Boolean checkSearchTree(TreeNode<Integer> head) {
        if (head == null) {
            return true;
        }
        Stack<TreeNode<Integer>> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {
            if (null != head) {
                stack.add(head);
                head = head.left;
            } else {
                head = stack.pop();
                System.out.println(head.item);
                head = head.right;
            }
        }
        return false;
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
     * 平衡二叉树的返回值
     */
    static class ReturnData {
        public boolean isBalanced;
        public int height;
        public ReturnData (boolean isBalanced,int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    /**
     * 判断是不是平衡二叉树：
     * 使用递归的方式：
     * 利用树形DP（Dynamic Programming）方式求解
     * @param head 头节点
     * @return
     */
    private ReturnData isBalanceTree(TreeNode<Integer> head) {
        //head = null 则是完全二叉树
        if (head == null) {
            return new ReturnData(true,0);
        }
        //递归左子树
        ReturnData leftCompleteTree = isBalanceTree(head.left);

        //递归右子树
        ReturnData rightCompleteTree = isBalanceTree(head.right);
        //高度
        int height = Math.max(leftCompleteTree.height,rightCompleteTree.height) + 1;
        //是不是完全二叉树:如果左子树和右子树是完全二叉树，然后左子树的高度和右子树的高度相差小于2，则，该节点的子树为平衡二叉树
        boolean flag = leftCompleteTree.isBalanced && rightCompleteTree.isBalanced && Math.abs(leftCompleteTree.height - rightCompleteTree.height)  < 2;
        return new ReturnData(flag,height);
    }

    private static int nodes = 0;

    private Boolean isFullTree(TreeNode<Integer> head) {
        if (head == null) {
            return true;
        }
        FullReturn fullReturn = countNodes(head);
        return fullReturn.nodes == ((1 << fullReturn.height) - 1);
    }
    static class FullReturn {
        public Integer height;
        public Integer nodes;
        FullReturn(int height,int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }
    /**
     * 判断是不是满二叉树:
     * 使用树形DP（Dynamic Programming）方式求解
     * 高度，和节点个数
     * @param head
     */
    private FullReturn countNodes(TreeNode<Integer> head) {
        if (head == null) {
            return new FullReturn(0,0);
        }
        FullReturn leftFullReturn = countNodes(head.left);
        FullReturn rightFullReturn = countNodes(head.right);
        //高度
        int height = Math.max(leftFullReturn.height, rightFullReturn.height) + 1;
        int nodes = leftFullReturn.nodes + rightFullReturn.nodes + 1;
        return new FullReturn(height,nodes);
    }

    /**
     * 判断是不是完全二叉树
     * 利用树形DP（Dynamic Programming）方式求解：头节点下面左右节点的个数，
     * 循环：
     * @param head 头节点
     * @return
     */
//    private static Boolean isCompleteTree(TreeNode<Integer> head) {
//
//    }

    //树形DP
    //分布式锁，分布式事务

    /**
     * 找两个节点的公共祖先节点：利用递归把节点和节点的父节点放到map中
     * @param head
     * @param one
     * @param two
     */
    private void publicFatherNode(TreeNode<Integer> head,TreeNode<Integer> one,TreeNode<Integer> two) {
        //key:子节点，value：父节点
        if (head == null || head == one || head == two) {
            System.out.println("公共父节点是head");
        }
        Map<TreeNode<Integer>,TreeNode<Integer>> map = new HashMap<>();
        map.put(head,head);
        f(head,map);
        TreeNode<Integer> currentNode = one;
        HashSet<TreeNode<Integer>> linked = new HashSet<>();
        linked.add(one);
        while(map.get(currentNode) != currentNode) {
            linked.add(map.get(currentNode));
            currentNode = map.get(currentNode);
        }
        linked.add(head);
        currentNode = two;
        while (map.get(currentNode) != currentNode) {
            if (linked.contains(currentNode)) {
                System.out.println("找到了公共节点:" + currentNode);
            }
            currentNode = map.get(currentNode);
        }

    }
    private void f(TreeNode<Integer> head,Map<TreeNode<Integer>,TreeNode<Integer>> map) {
        if (head == null) {
            return;
        }
        f(head.left,map);
        f(head.right,map);
        map.put(head.left,head);
        map.put(head.right,head);
    }

    //二叉树的字符串的序列化和反序列化

    @Test
    public void test() {
        p(true,1,3);
    }

    //请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。
    // 例如:N=1时，打印: down N=2时，打印: down down up。：
    public static void print() {
        p(true,1,3);
    }


    //递归过程中。来到了某一个节点
    //i是节点的层数，一共n层，down == true down; down = false up
    public static void p(boolean down,int i,int n) {
        if(i > n) {
            return;
        }
        p(true,i+1,n);
        System.out.println(down ? "down" : "up");
        p(false,i+1,n);
    }


}
