package com.example.test.io.javaio;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author: chengw
 * @Date: 2023/7/26 下午3:55
 */
@SpringBootTest
public class JavaIO {

    /**
     * socket：
     * socketAddress：socket监听地址
     * channel：通道，可读可写，负责从文件或者IO中读取数据，channel读取数据都需要经过buffer
     * selector：选择器：selector可以从多个注册的channel中读取数据，相当于selector循环访问通道是否有数据，不用阻塞
     * @throws IOException
     */
    @Test
    public void test() throws Exception {
        //创建一个服务器端socket通道
//        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        LinkedList<Integer> linkedList = new LinkedList<>();

        Thread thread = new Thread();
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return -1;
            }
        };
        Integer call = callable.call();
        System.out.println(call);

    }
}
