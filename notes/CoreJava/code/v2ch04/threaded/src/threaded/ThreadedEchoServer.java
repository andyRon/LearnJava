package threaded;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 4.2.2 为多个客户端服务，每个客户端请求生成一个新的线程
 * @author Andy Ron
 */
public class ThreadedEchoServer {

    public static void main(String[] args) {
        try (ServerSocket s = new ServerSocket(8189)) {
            int i = 1;
            // 为什么没有进入死循环
            while (true) {
                Socket incoming = s.accept();
                System.out.println("Spawning " + i);
                Runnable r = new ThreadEchoHandler(incoming);
                Thread t = new Thread(r);
                t.start();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ThreadEchoHandler implements Runnable {
    private Socket incoming;

    public ThreadEchoHandler(Socket incomingSocket) {
        incoming = incomingSocket;
    }

    @Override
    public void run() {
        try (InputStream inStream = incoming.getInputStream();
             OutputStream outStream = incoming.getOutputStream()) {
            Scanner in = new Scanner(inStream, "UTF-8");
            PrintWriter out = new PrintWriter(
                    new OutputStreamWriter(outStream, "UTF-8"), true);

            out.println("Hello! Enter BYE to exit.");

            boolean done = false;
            while (!done && in.hasNextLine()) {
                String line = in.nextLine();
                out.println("Echo: " + line);
                if (line.trim().equals("BYE")) {
                    done = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}