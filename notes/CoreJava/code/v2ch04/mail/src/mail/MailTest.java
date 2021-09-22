package mail;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;


/**
 * @author Andy Ron
 */
public class MailTest {

    public static void main(String[] args) throws IOException {
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("mail.properties"))){
            props.load(in);
        }
        List<String> lines = Files.readAllLines(Paths.get(args[0]), Charset.forName("UTF-8"));

        String from = lines.get(0);
        String to = lines.get(1);
        String subject = lines.get(2);

        StringBuilder builder = new StringBuilder();
        for (int i = 3; i < lines.size(); i++) {
            builder.append(lines.get(i));
            builder.append("\n");
        }

        Console console = System.console();
        String password = new String(console.readPassword("Password: "));

//        Session mailSession = Session.getDefaultInstance(props);  ??
    }

    public int climbStairs(int n) {
        int a = 1, b = 1;
        while (--n > 0) {
            b += a;
            a = b - a;
        }
        return b;
    }
}
