package com.example.test.collections;

import java.util.Stack;

/**
 * 基于栈实现队列
 * @Author: chengw
 * @Date: 2023/8/24 下午4:54
 */

public class StackQueue<T> {
    private Stack<T> stack1; // 用于入队操作
    private Stack<T> stack2; // 用于出队操作

    public  StackQueue() {
        this.stack1 = new Stack<T>();
        this.stack2 = new Stack<T>();
    }

    public void push(T x) {
        stack1.push(x);
    }

    /**
     * 出队操作，如果 stack2 为空，则将 stack1 中的元素逐个出栈并放入 stack2，然后从 stack2 出队
     * @return
     */
    public T pop() {
        switchStack();
        return stack2.pop();
    }

    /**
     * 查看队首元素，同样先将元素从 stack1 转移到 stack2，然后查看 stack2 的栈顶元素
     * @return
     */
    public T peek() {
        switchStack();
        return stack2.peek();
    }
    /**
     * 将入栈栈的元素，弹出重新放入出栈栈
     */
    public void switchStack() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
    }
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
