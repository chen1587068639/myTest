package com.example.io.netty;

import com.example.test.hanlder.MyServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: chengw
 * @Date: 2022/12/19 下午5:04
 */
@SpringBootTest
public class NettyServerTest {

    public void testNetty(){
        //创建两个线程组 boosGroup、workerGroup
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

    }

    public static void main(String[] args) {
        System.out.println("服务端启动开始");
        //创建两个线程组 boosGroup、workerGroup
        //bossGroup 用于监听客户端连接，专门负责与客户端创建连接，并把连接注册到workerGroup的Selector中。
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //workerGroup用于处理每一个连接发生的读写事件。
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        //创建服务端的启动对象，设置参数：Bootstrap和ServerBootStrap是Netty提供的一个创建客户端和服务端启动器的工厂类
        ServerBootstrap bootstrap = new ServerBootstrap();
        //设置两个线程组boosGroup和workerGroup
        bootstrap.group(bossGroup,workerGroup)
            //设置服务端通道实现类型
            .channel(NioServerSocketChannel.class)
            //设置线程队列得到连接个数
            .option(ChannelOption.SO_BACKLOG, 128)
            //设置保持活动连接状态
            .childOption(ChannelOption.SO_KEEPALIVE, true)
            //使用匿名内部类的形式初始化通道对象
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //给pipeline管道设置处理器
                        socketChannel.pipeline().addLast(new MyServerHandler());
                    }
                });//给workerGroup的EventLoop对应的管道设置处理器
        System.out.println("java的服务端已经准备就绪...");
        try {
            //绑定端口号，启动服务端
            ChannelFuture channelFuture = bootstrap.bind(6666).sync();
            //对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放掉所有的资源，包括创建的线程
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
        System.out.println("服务端执行完毕");
    }


}
