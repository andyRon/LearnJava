package com.andyron.netty.ch04;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author andyron
 **/
public class NettyServer {
    public static void main(String[] args) {
        // bossGroup表示监听端口，接收新连接的线程组；workerGroup表示处理每一个连接的数据读写的线程组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        // 引导类负责引导服务端的启动工作
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        // 给引导类配置两大线程组
        serverBootstrap.group(bossGroup, workerGroup)
                // 指定IO模型
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception {
                        System.out.println("xxxx");
                    }
                });
        serverBootstrap.handler(new ChannelInitializer<NioServerSocketChannel>() {
            @Override
            protected void initChannel(NioServerSocketChannel ch) throws Exception {
                System.out.println("服务器启动中");

            }
        });

//        serverBootstrap.bind(8000);
        bind(serverBootstrap, 8000);
    }

    /**
     * 从指定端口开始向上寻找到可用端口
     * @param serverBootstrap
     * @param port
     */
    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("端口[" + port + "]绑定成功！");
                } else {
                    System.err.println("端口[" + port + "]绑定失败！");
                    bind(serverBootstrap, port + 1);
                }
            }
        });
    }
}
