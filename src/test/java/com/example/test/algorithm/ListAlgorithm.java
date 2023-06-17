package com.example.test.algorithm;

import com.example.test.common.ListCommon;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: chengw
 * @Date: 2023/6/17 上午10:05
 */
@SpringBootTest
public class ListAlgorithm {


    /**
     * 求一个数组中的唯一一个出现次数为奇数次的数字
     */
    @Test
    public void selectNumber(){
        int lastNumber = 0;
        for (int i : ListCommon.arrayList) {
            lastNumber = lastNumber ^ i;
//            lastNumber = lastNumber ^ i;和lastNumber ^= i;效果相等
        }
        System.out.println(lastNumber);
    }

    /**
     * 求一个数组中有两个出现次数为奇数次的数字
     */
    @Test
    public void selectTwoNumber(){
        //tmp = 两个数字的异或结果 a^b
        int tmp = 0;
        for (int i : ListCommon.arrayList){
            tmp ^= i;
        }
        //取出tmp最右侧的1（二进制）,~取反，例如tmp = 00110010，～tmp(非) = 11001101,～tmp + 1 = 11001110,rightOne = 00110010 & 11001110 = 00000010
        int rightOne = tmp & (~tmp + 1);
        //a或者b其中一个
        int onlyOne = 0;
        for (int j : ListCommon.arrayList) {
            if ((j & rightOne) != 0) {
                onlyOne ^= j;
            }
        }
        //另一个奇数次的数字,假设onlyOne = a，则：a^b^a = b
        System.out.println("奇数次的数字：一个是->"+ onlyOne + ",另一个是->" + (tmp ^ onlyOne));
    }

}
