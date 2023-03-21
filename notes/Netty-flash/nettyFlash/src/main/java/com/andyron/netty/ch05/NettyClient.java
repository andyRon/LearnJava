package com.andyron.netty.ch05;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author andyron
 **/
public class NettyClient {


    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                // 1 指定线程模型
                .group(workerGroup)
                // 2 指定IO类型NIO
                .channel(NioSocketChannel.class)
                // 3 IO处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                    }
                });
        // 4 建立连接
        bootstrap.connect("juejin.cn", 80).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功！");
            } else {
                System.out.println("连接失败！");
            }
        });
        connect(bootstrap, "juejin.cn", 80, MAX_RETRY);
    }
    private static final int MAX_RETRY = 5;

    /**
     *
     * @param bootstrap
     * @param host
     * @param port
     * @param retry 重连次数
     */
    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功！");
            } else if (retry == 0) {
                System.out.println("重试次数用完，放弃连接！");
            } else {
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.err.println(new Date() + "：连接失败，第" + order + "次重连......");
                bootstrap.config().group().schedule(() ->
                        connect(bootstrap, host, port, retry--), delay, TimeUnit.SECONDS);
            }
        });
    }
}
