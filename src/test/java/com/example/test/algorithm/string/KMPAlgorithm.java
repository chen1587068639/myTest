package com.example.test.algorithm.string;

import com.example.test.util.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * KMP算法：子串在父串的重合起始位置
 * 时间复杂度：O(m+n)
 * @Author: chengw
 * @Date: 2023/7/22 上午10:25
 */
@SpringBootTest
public class KMPAlgorithm {

    @Test
    public void testKMPAlgorithm() {
        String parent = "asdfghjkl";
        String son = "ghjkl12";
        System.out.println(kmpAlgorithm(parent, son));
    }

    /**
     * KMP算法
     * 时间复杂度：O(m+n)
     * 先计算子串每一个索引的前缀和后缀的重合长度
     * 利用子串的前缀和后缀加速
     *
     * @param parent 父串：较长的串
     * @param son 子串：较短的串
     * @return 重合的起始位置:-1代表两个字符串没有重合
     */
    private static int kmpAlgorithm(String parent,String son) {
        //如果两个字符串有一个是空则返回0
        if (StringUtils.isEmpty(parent) || StringUtils.isEmpty(son) || parent.length() < son.length()) {
            return -1;
        }
        //求子串的前/后缀数组
        int[] next = next(son); //O(M)
        //父串的比较索引
        int parentIndex = 0;
        //子串的比较索引
        int sonIndex = 0;
        //父串每一次对比循环都会向下走一个位置，子串在循环中首先对比相同与否，相同则向下走，不相同则跳到重合前缀的下一个位置
        while (sonIndex < son.length() && parentIndex < parent.length()) {
            //如果两个字符是相等的，则parent和son同时进一位，否则的话子串回到前缀位置，parent进一位
            if (parent.charAt(parentIndex) == son.charAt(sonIndex)) {
                parentIndex++;
                sonIndex++;
            } else if (sonIndex == 0) {//next[sonIndex] == -1,子串已经回到了起点位置，则父串换一个开头对比
                parentIndex++;
            } else {
                sonIndex = next[sonIndex];
            }
        }
        return  sonIndex == son.length() ? parentIndex - son.length() : -1;
    }

    /**
     * 计算next数组
     * @param son 字符串
     * @return 返回next数组
     */
    private static int[] next(String son) {
        if (son.length() == 1) {
            return new int[]{-1};
        }
        int[] next = new int[son.length()];
        //第一个位置和第二个位置的值是确定的
        next[0] = -1;
        next[1] = 0;
        int index = 2; //当前元素位置
        int cn = 0; //前缀的对比索引位置
        while (index < son.length()) {
            //当前位置的前一个位置和前缀的下一个位置相等，则next数组的index位置 等于 cn+1；
            if (son.charAt(index - 1) == son.charAt(cn)) {
                next[index++] = ++cn;
            } else if (cn > 0) { //当前位置的前一个位置和前缀的下一个位置不相等，
                cn = next[cn];
            } else { //当前位置的前一个位置和前缀的下一个位置不相等，而且压根没有前缀数组
                next[index++] = 0;
            }
        }
        return next;
    }

}
