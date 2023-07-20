package com.example.test.algorithm;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: chengw
 * @Date: 2023/7/10 上午9:59
 */
@SpringBootTest
public class StringAlgorithm {

    @Test
    public void test() {
        int i = 3;
        int position = 4;
        //取出整数的第n个bit
        int bit = getBit(i, position);
    }

    /**
     * 取出整数的第n个bit
     * @param i 整数
     * @param position 第多少位
     * @return 返回第position位的状态：1:是position位上是1；false:是position位上是0
     */
    private int getBit(int i,int position) {
        int j = 1 << position;
        int bit = i & j;
        return bit >> position;
    }

    /**
     * 计算字符串（纯字母）有多少种排列方式
     */

    /**
     * 计算字符串从头到尾有多少种排列方式
     */

    /**
     * 一个大文件，里面有40亿个大数字（数字范围：0-2^32），计算哪个数字出现的频率最高
     */

    
}
