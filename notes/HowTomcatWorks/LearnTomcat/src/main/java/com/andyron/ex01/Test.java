package com.andyron.ex01;

import java.io.*;
import java.net.Socket;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1", 8081);
        OutputStream os = socket.getOutputStream();
        boolean autoFlush = true;
        PrintWriter out = new PrintWriter(os, autoFlush);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // 发送一个HTTP请求到web服务器
        out.println("Get /hello HTTP/1.1");
        out.println("Host: localhost:8081");
        out.println("Connection: Close");
        out.println();
        // 读取response
        boolean loop = true;
        StringBuffer sb = new StringBuffer(8096);
        while (loop) {
            if (in.ready()) {
                int i = 0;
                while (i != -1) {
                    i = in.read();
                    sb.append((char) i);
                }
                loop = false;
            }
            Thread.sleep(50);
        }
        // 向控制台展示响应信息
        System.out.println(sb.toString());
        socket.close();
    }
}
