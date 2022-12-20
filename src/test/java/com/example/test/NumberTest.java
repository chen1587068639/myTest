package com.example.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: chengw
 * @Date: 2022/12/5 下午5:20
 */
@SpringBootTest
public class NumberTest {

    @Test
    public void testNumberOne(){
        System.out.println((10/3) * 1.5);
    }
}
