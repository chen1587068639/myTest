package com.example.test.thread;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: chengw
 * @Date: 2023/3/27 下午2:05
 */
class MyThread extends Thread{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "-> 跑起来了");
    }
}
class Demo{
    public Integer i = 0;


    public void test(String param,Integer n) throws InterruptedException {
        synchronized (i){
            Thread.sleep(n);
            System.out.println(param);
        }
    }
}
@SpringBootTest
public class ThreadTest {

    /**
     * 共享变量
     */
    public Integer i = 0;
    private Lock lock = new ReentrantLock();
    private Lock lock22 = new ReentrantLock();

    @Test
    public void testThead11() throws InterruptedException {
        System.out.println("测试开始");
        new Thread(()->{
            System.out.println("第一个线程开始执行");
            lock.lock();
            i = i +1;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i ++ ;
            System.out.println("第一个线程名字:" + Thread.currentThread().getName() + "变量值:" + i);
            lock.unlock();
        }).start();
        Thread.sleep(1000);
        new Thread(()->{
            System.out.println("第二个线程开始执行");
            lock22.lock();
            i ++ ;
            System.out.println("第二个线程名字:" + Thread.currentThread().getName() + "变量值:" + i);
            lock22.unlock();
        }).start();
        Thread.sleep(10000);
        System.out.println("测试结束，变量i="+i);
    }


    /**
     * BootstrapClassLoader
     * ExtClassLoader
     * AppClassLoader
     */
        @Test
    public void testThead(){
        Demo demo = new Demo();
        new Thread(()->{

            try {
                demo.test(Thread.currentThread().getName(),1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                demo.test(Thread.currentThread().getName(),3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


    }

}
