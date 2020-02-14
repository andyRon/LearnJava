import java.io.Console;
import java.util.Scanner;

public class MyIO {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("What is your name?");
        String name = in.nextLine();    // 可以包括空格
//        String firstName = in.next();
        System.out.println("How old are you?");
        int age = in.nextInt();

        System.out.println("Hello, " + name + ". Next year, you'll be " + (age + 1));
        System.out.printf("Hello, %s. Next year, you'll be %d", name, age);

    }


}
