package com.example.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: chengw
 * @Date: 2023/2/22 下午3:24
 */
@Slf4j
@SpringBootTest
public class ExceptionTest {

    @Test
    public void testException(){
        try {
            throw new ArithmeticException();
        } catch (Exception e){
            log.error("错误:",e);
        }
    }
}
