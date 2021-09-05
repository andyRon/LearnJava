import java.math.BigInteger;
import java.util.Collections;
import java.util.Scanner;

public class BigIntegerTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 可以抽几个数，比如 60
        System.out.print("How many numbers do you need to draw? ");
        int k = in.nextInt();

        // 一共多少个数，比如 490
        System.out.print("What is the highest number you can draw? ");
        int n = in.nextInt();

        BigInteger lotteryOdds = BigInteger.valueOf(1);

        for (int i = 1; i <= k; i++) {
            lotteryOdds = lotteryOdds.multiply(BigInteger.valueOf(n - i + 1)).divide(BigInteger.valueOf(i));
        }

        System.out.println("Your odds are 1 in " + lotteryOdds + ". Good luck!");

    }

}
