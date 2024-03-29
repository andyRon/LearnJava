import java.io.IOException;
import java.net.InetAddress;

/**
 * 4.1 域名转IP地址
 * @author Andy Ron
 */
public class InetAddressTest {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
//            String host = args[0];
            String host = "www.baidu.com";
            InetAddress[] addresses = InetAddress.getAllByName(host);
            for (InetAddress a : addresses) {
                System.out.println(a);
            }
        } else {
            // 本机IP地址
            InetAddress localHostAddress = InetAddress.getLocalHost();
            System.out.println(localHostAddress);
        }
    }
}
