import java.util.*;

/**
 * @author Andy Ron
 */
public class Retirement2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("每年有多少钱贡献给退休金？");
        Double payment = scanner.nextDouble();

        System.out.println("年利率是多少？");
        Double interestRate = scanner.nextDouble();

        double balance = 0;
        int year = 0;

        String input;

        do {
            balance += payment;
            Double interest = balance * interestRate / 100;
            balance += interest;

            year++;

            System.out.printf("在%d年后，你的退休金是%,.2f", year, balance);

            System.out.print("是否准备退休？（是/否）");
            input = scanner.next();
        } while (input.equals("否"));

    }
}
