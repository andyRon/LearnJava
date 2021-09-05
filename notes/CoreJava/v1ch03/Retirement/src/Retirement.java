import java.util.Scanner;

/**
 * 3.8.3 循环语句
 * <code>while</code> loop
 * 计算要多长时间才能存够一定数量的退休金
 */
public class Retirement {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 目标退休金
        System.out.print("How much money do you need to retire? ");
        double goal = in.nextDouble();

        // 每年收入
        System.out.print("How much money will you contribute every year? ");
        double payment = in.nextDouble();

        // 利率
        System.out.print("Interest rate in %: ");
        double interestRate = in.nextDouble();

        // 余额、存款
        double balance = 0;
        int years = 0;

        while (balance < goal) {
            balance += payment;
            // 利息
            double interest = balance * interestRate / 100;
            balance += interest;
            years++;
        }

        System.out.println("You can retire in " + years + " years.");
    }
}
