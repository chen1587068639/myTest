package com.example.test.algorithm.string;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Manacher算法：求回文区域
 * 时间复杂度:O(n)
 * 回文半径：
 * 回文直径：
 * 最大回文边界R：
 * 最大回文边界中心C：
 * @Author: chengw
 * @Date: 2023/7/22 上午10:25
 */
@SpringBootTest
public class ManacherAlgorithm {

    @Test
    public void testManacherAlgorithm() {
        String str = "12345678987654321";
        System.out.println(manacherAlgorithm(str));
    }


    /**
     * 该函数实现了Manacher算法，用于找到给定字符串中最长回文子串的长度。
     *
     * @param s 输入字符串
     * @return 最长回文子串的长度
     */
    private static int manacherAlgorithm(String s) {
        //获取处理后的字符串
        String str = handler(s);
        //每一个位置的回文长度
        int[] result = new int[str.length()];
        //最大回文边界R
        int r = 0; //有效区域最后一个位置再往后一个位置
        //最大回文边界中心C
        int c = 0;
        int max = Integer.MIN_VALUE; //记录最长回文字符串长度
        for (int i = 0; i != str.length(); i++) { //每个位置都要求回文半径
            //一共有两种情况
            //1. i在R外：从i位置开始暴力扩展
            //2. i在R内：又分三种情况
                //1. i'在回文边界L...R中:当i'的回文字符串完全在L...R内的时候，i的回文字符串长度就是i'的回文字符串
                //3. i'有一部分在L...R外面:当i'的回文字符串有一部分在L...R外的时候，R-i就是i的回文回文字符串长度(假设i的回文字符串大于r-i，那么R+1位置的字符也等于L-1位置的字符，那么c位置的回文字符串的边界就不应该是R，而是R+1，所以是矛盾的)
                //2. i'和L...R压线：只有压线的时候i才需要接着对比字符，看i位置的回文字符串长度是多少
                //以上三种情况，第一种，i的回文字符串是result[i'],第二种是r-i,第三种可以初始化成r-i，然后再比较，
                // 这样的话，就可以先把result[i]的回文字符串的长度先初始化成两者较小的一个，
            //先给一个初始回文半径长度：索引0位置的回文字符串为1
            //当i在R中的时候，先给一个i位置回文字符串的初始值，比较i'的回文字符串长度。和r-i的长度，哪个小就初始化成哪一个
            result[i] = r > i ? Math.min(result[2 * c - i],r - i) : 1;
            //判断：如果i加上回文字符串长度小于原字符串长度，和保证回文半径不超过i的值
            //就是右边要小于str.length()，左边不能超过0
            while (i + result[i] < str.length() && i - result[i] > -1) {
                //如果字符相同，则回文字符串加1
                if (str.charAt(i + result[i]) == str.charAt(i - result[i])) {
                    result[i]++;
                } else {
                    break;
                }
            }
            //如果这次循环i超过了上次的回文半径，则把最大回文边界和最大回文边界中心修改一下
            if (i + result[i] > r) {
                r = i + result[i];
                c = i;
            }
            max = Math.max(max,result[i]);
        }
        return max - 1;
    }

    private static String handler(String s) {
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder("#");
        for (char c : chars) {
            sb.append(c).append("#");
        }
        return sb.toString();
    }

}
