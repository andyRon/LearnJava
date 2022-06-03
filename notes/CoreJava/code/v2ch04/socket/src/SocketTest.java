import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *  4.1 连接服务器
 * @author Andy Ron
 */
public class SocketTest {
    public static void main(String[] args) throws IOException {
//        String url = "time-a.nist.gov";
//        int port = 13;
        String url = "www.baidu.com";
        int port = 80;
        try (Socket s = new Socket(url, port);
             Scanner in = new Scanner(s.getInputStream(), "UTF-8")){

            while (in.hasNextLine()) {
                String line = in.nextLine();
                System.out.println(line);
            }
        }
    }
}
