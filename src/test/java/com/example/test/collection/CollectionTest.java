package com.example.test.collection;

import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: chengw
 * @Date: 2023/3/10 下午8:39
 */
@SpringBootTest
public class CollectionTest {
    @Test
    public void testsss() throws InterruptedException {
        String s = "bbbbb";
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<s.length() ; i++){
            sb.append(s.charAt(i));
            int last = s.lastIndexOf(sb.toString());
            if (0 == last){
                break;
            }
        }
        sb.wait();
        Integer a = 8;
        List<Integer> list = new ArrayList<>(16);

        a = 20;
        System.out.println(sb.length()-1);

    }
}
