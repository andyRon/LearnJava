package com.andyron.bio;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author andyron
 **/
public class BIOServer {

    public static void main(String[] args) throws Exception {
        //
        // 思路
        // 1 创建一个线程池
        // 2 如果有客户端连接，就创建一个线程，与之通信（单独写一个方法）


        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

        // 创建ServerSocket
        ServerSocket serverSocket = new ServerSocket(6666);

        System.out.println("服务器启动了");

        while (true) {
            System.out.println("a线程信息 id = " + Thread.currentThread().getId() + " 名字 = " + Thread.currentThread().getName());

            // 监听，等待客户端连接
            System.out.println("等待连接...");
            final Socket socket = serverSocket.accept();  // 没有客户端连接就会阻塞在这
            System.out.println("连接到一个客户端");

            // 创建一个线程，与之通信（单独写一个方法）
            newCachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    handler(socket);
                }
            });
        }
    }
    // 和客户端通讯方法
    public static void handler(Socket socket) {
        try {
            System.out.println("b 线程信息 id = " + Thread.currentThread().getId() + " 名字 = " + Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            // 通过Socket获取输入流
            InputStream inputStream = socket.getInputStream();

            // 循环的读取客户端发送的数据
            while (true) {
                System.out.println("c 线程信息 id = " + Thread.currentThread().getId() + " 名字 = " + Thread.currentThread().getName());

                System.out.println("read...");
                // 把数据读到bytes里，返回读取的数据量
                int read = inputStream.read(bytes);  // 连接后没有数据发送就会阻塞在这
                if (read != -1) {
                    // 输出客户端发送的数据
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭和client的链接");
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
