import java.util.Random;


// -Xms8m -Xmx8m -XX:+PrintGCDetails
public class Demo2 {
    public static void main(String[] args) {
        String str = "testString";

        while (true) {
            str += str + new Random().nextInt(888888888) + new Random().nextInt(99999999);
        }
    }
}
