package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 4.2.1 服务器套接字
 * @author Andy Ron
 */
public class EchoServer {

    public static void main(String[] args) throws IOException {

        // ServerSocket用于创建服务器套接字
        try (ServerSocket s = new ServerSocket(8189)){
            // 创建监控端口8189的等待程序Socket对象
            try (Socket incoming = s.accept()){
                // 通过Socket对象获得输入流和输出流
                InputStream inStream = incoming.getInputStream();
                OutputStream outStream = incoming.getOutputStream();

                try (Scanner in = new Scanner(inStream, "UTF-8")){
                    // 服务器的输出流就成为客服端的输入
                    PrintWriter out = new PrintWriter(
                            new OutputStreamWriter(outStream, "UTF-8"), true);
                    // 向客服端打印...（也就是客服端的输入）
                    out.println("Hello! Enter BYE to exit.");
                    boolean done = false;
                    while (!done && in.hasNextLine()) {
                        String line = in.nextLine();
                        out.println("Echo: " + line);
                        if (line.trim().equals("BYE")) {
                            done = true;
                        }
                    }
                }
            }
        }
    }
}
