package com.example.test.interview;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: chengw
 * @Date: 2023/6/28 下午2:38
 */
@SpringBootTest
public class StringTest {

    @Test
    public void test() {
        String a = "abcabcbb";
        System.out.println(maxLengthString(a));
    }

    /**
     * 计算字符串的最长子字符串
     * @param s
     */
    private static String maxLengthString(String s) {
        StringBuilder current = new StringBuilder();
        String lastString = "";
        //"dvdf"
        for (int i = 0; i < s.length(); i++) {
            if (current.toString().contains(String.valueOf(s.charAt(i)))) {
                //比较当前字符串和lastString哪个更长
                lastString = current.length() > lastString.length() ? current.toString() : lastString;
                current.delete(0,current.indexOf(String.valueOf(s.charAt(i))) + 1);
            }
            current.append(s.charAt(i));
        }
        return current.length() > lastString.length() ? current.toString() : lastString;
    }


}
