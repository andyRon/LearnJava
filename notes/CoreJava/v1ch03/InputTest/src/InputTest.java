import java.util.*;
/**
 * @author Andy Ron
 */
public class InputTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 第一个输出
        System.out.println("What is your name?");
        String name = in.nextLine();

        // 第二个输出
        System.out.println("How old are you?");
        int age = in.nextInt();

        System.out.println("Hello, " + name + ". Next year, you'll be " + (age + 1));

    }
}
