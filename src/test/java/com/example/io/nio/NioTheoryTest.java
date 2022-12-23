package com.example.io.nio;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @Author: chengw
 * @Date: 2022/12/20 上午10:36
 */
public class NioTheoryTest {

    public static void main(String[] args) throws Exception {
        //获取ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 6666);
        //绑定地址，端口号
        serverSocketChannel.bind(address);
        //创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (true) {
            //获取SocketChannel
            SocketChannel socketChannel = serverSocketChannel.accept();
            while (socketChannel.read(byteBuffer) != -1){
                //打印结果
                //命令窗通过curl发出post访问：curl -X POST -d '{"user_id": "123", "coin":100, "success":1, "msg":"OK!" }'  localhost:6666
                System.out.println(new String(byteBuffer.array()));
                //清空缓冲区
                byteBuffer.clear();
            }
        }
    }

    @Test
    public void testNIO(){
        //创建堆内内存块HeapByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        String msg = "java";
        //ByteBuffer是HeapByteBuffer：HeapByteBuffer所创建的字节缓冲区就是在JVM堆中的
        ByteBuffer wrap = ByteBuffer.wrap(msg.getBytes());
    }

    @Test
    public void testByteBuffer(){
        String msg = "java程序！";
        //创建一个固定大小的buffer(返回的是HeapByteBuffer)
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byte[] bytes = msg.getBytes();
        byteBuffer.put(bytes);
        //切换成读模式，关键一步
        //缓存区是双向的，既可以往缓冲区写入数据，也可以从缓冲区读取数据。但是不能同时进行，需要切换。
        //目前buffer中已经有字节流了，position会因为字节流存入buffer的字节大小，存入多少个字节，position增加多少
        //当调用flip（源码：limit = position; position = 0; mark = -1; return this;）方法时
        byteBuffer.flip();
        //创建一个临时数组，用于存储获取到的数据
        byte[] tempByte = new byte[bytes.length];
        int i = 0;
        //如果还有数据，就循环。循环判断条件
        //hasRemaining方法（源码：position < limit;）如position<limit返回true，而在此期间buffer的长度capacity是不变的
        while (byteBuffer.hasRemaining()) {
            //获取byteBuffer中的数据
            byte b = byteBuffer.get();
            //放到临时数组中
            tempByte[i] = b;
            i++;
        }
        //打印从buffer中获取的字节流
        System.out.println(new String(tempByte));
    }

    @Test
    public void testFileChannel() throws Exception {
        File file = new File("/Users/chengw/myWorld/work/workFile/sql/update.xml");
        FileInputStream inputStream = new FileInputStream(file);
        //从文件输入流获取通道
        FileChannel fileInPutChannel = inputStream.getChannel();
        //获取文件输出流
        FileOutputStream outputStream = new FileOutputStream(new File("/Users/chengw/myWorld/work/workFile/sql/fileChannel.xml"));
        //从文件输出流获取通道
        FileChannel fileOutPutChannel = outputStream.getChannel();
        //buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate((int)file.length());
        //读取文件到缓冲流
        fileInPutChannel.read(byteBuffer);
        //切换成读模式
        byteBuffer.flip();
        //把数据从缓冲区写入到输出流通道
        fileOutPutChannel.write(byteBuffer);
        //关闭通道
        outputStream.close();
        inputStream.close();
        fileInPutChannel.close();
        fileOutPutChannel.close();
    }

    /**
     * 下面的例子是阻塞式的，要做到非阻塞还需要使用选择器Selector。
     * @throws IOException
     */
    @Test
    public void testSocketChannel() throws IOException {
        //获取ServerSocketChannel,通过ServerSocketChannel.open()方法可以获取服务器的通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 6677);
        //绑定地址，端口号
        serverSocketChannel.bind(address);
        //创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (true){
            //获取SocketChannel,accept()方法可获得一个SocketChannel通道，也就是客户端的连接通道。
            SocketChannel socketChannel = serverSocketChannel.accept();
            while (socketChannel.read(byteBuffer) != -1){
                //打印结果
                System.out.println(new String(byteBuffer.array()));
                //清空缓冲区
                byteBuffer.clear();
            }
        }
    }

    /**
     * transferTo()：把源通道的数据传输到目的通道中。
     * transferFrom()：把来自源通道的数据传输到目的通道。
     * @throws Exception
     */
    @Test
    public void testTransferTo() throws Exception{
        //获取文件输入流
        File file = new File("/Users/chengw/myWorld/work/workFile/sql/update.xml");
        FileInputStream inputStream = new FileInputStream(file);
        //从文件输入流获取通道
        FileChannel inputStreamChannel = inputStream.getChannel();
        //获取文件输出流
        FileOutputStream outputStream = new FileOutputStream(new File("/Users/chengw/myWorld/work/workFile/sql/2222.xml"));
        //从文件输出流获取通道
        FileChannel outputStreamChannel = outputStream.getChannel();
        //创建一个byteBuffer，小文件所以就直接一次读取，不分多次循环了
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        //直接缓冲区：最大不同在直接缓冲区不用把内容copy到物理内存中。这就大大地提高了性能。（小文件不用这样）
        ByteBuffer directByteBuffer = ByteBuffer.allocateDirect(5 * 1024 * 1024);

        //把输入流通道的数据读取到输出流的通道
        inputStreamChannel.transferTo(0, byteBuffer.limit(), outputStreamChannel);
        //把输入流通道的数据读取到输出流的通道
        outputStreamChannel.transferFrom(inputStreamChannel,0,byteBuffer.limit());
        //关闭通道
        outputStream.close();
        inputStream.close();
        outputStreamChannel.close();
        inputStreamChannel.close();
    }

}
