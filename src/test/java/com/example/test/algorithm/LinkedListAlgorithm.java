package com.example.test.algorithm;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;

/**
 * 链表算法
 * @Author: chengw
 * @Date: 2023/6/20 下午5:11
 */
@SpringBootTest
public class LinkedListAlgorithm {
    List<Integer> list = new LinkedList<>();


    static class ListNode<E> {
        E item;
        ListNode<E> next;

        public ListNode() {
        }

        public ListNode(E item) {
            this.item = item;
        }

        public ListNode(E item,ListNode<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    @Test
    public void linkedList() {
        // 创建链表：1 -> 2 -> 3 -> 4 -> 5
        ListNode<Integer> head = new ListNode<>(1);
        head.next = new ListNode<>(2);
        ListNode<Integer> integerListNode3 = new ListNode<>(3);
        head.next.next = integerListNode3;
        head.next.next.next = new ListNode<>(4);
        head.next.next.next.next = new ListNode<>(5);

//        // 反转链表
//        ListNode<Integer> reversedHead = revertLinkedList(head);
//
//
//        // 遍历打印反转后的链表
//        while (reversedHead != null) {
//            System.out.print(reversedHead.item + " ");
//            reversedHead = reversedHead.next;
//        }

        ListNode<Integer> head222 = new ListNode<>(10);
        head222.next = new ListNode<>(1);
        head222.next.next = new ListNode<>(3);
        head222.next.next.next = new ListNode<>(4);
        head222.next.next.next.next = integerListNode3;
        printPublicContent(head,head222);
    }

    /**
     * 反转链表
     * @param head 头部
     * @return 反转链表
     */
    private ListNode<Integer> revertLinkedList(ListNode<Integer> head) {
        ListNode<Integer> prev = null;
        ListNode<Integer> curr = head;
        while (curr != null) {
            ListNode<Integer> nextNode = curr.next;//取出下一个节点
            curr.next = prev;//将下一个节点指向上一个节点
            prev = curr;//存储当前节点为上一个节点
            curr = nextNode;//循环遍历下一个节点
        }
        return prev;
    }

    /**
     * 打印链表的环起始位置,使用快慢指针，当快慢指针相碰的时候，快指针指向head，快慢指针都循环走一步，相等的时候就是公共部分的开头
     * @param head 链表头
     */
    private void loopLocation(ListNode<Integer> head) {

    }
    /**
     * 寻找两个链表的公共部分
     * @param oneHead 第一个链表头
     * @param twoHead 第二个链表头
     */
    private void printPublicContent(ListNode<Integer> oneHead, ListNode<Integer> twoHead) {
        //循环记录两个链表的长度
        ListNode<Integer> oneNode = oneHead;
        ListNode<Integer> twoNode = twoHead;
        int oneLength = 0;
        int twoLength = 0;
        //记录第一个链表长度
        while (oneNode != null) {
            oneLength++;
            oneNode = oneNode.next;
        }
        //记录第二个链表长度
        while (twoNode != null) {
            twoLength++;
            twoNode = twoNode.next;
        }
        int maxLength = Math.max(oneLength, twoLength);
        for (int i = 0; i < maxLength; i++) {
            if (oneLength > twoLength) {
                oneHead = oneHead.next;
                oneLength--;
            } else if (oneLength < twoLength){
                twoHead = twoHead.next;
                twoLength--;
            } else {
                if (oneHead == twoHead) {
                    System.out.println("第一个链表:重合部分" + oneHead.item);
                    System.out.println("第二个链表:重合部分" + twoHead.item);
                }
                oneHead = oneHead.next;
                twoHead = twoHead.next;
            }
        }
    }

}

