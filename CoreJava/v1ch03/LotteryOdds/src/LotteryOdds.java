import java.util.Scanner;

/**
 * <code>for</code> loop
 * 计算抽奖中奖的概率
 */
public class LotteryOdds {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("How many numbers do you need to draw? ");  // 可以抽几个数，比如 6
        int k = in.nextInt();

        System.out.print("What is the highest number you can draw? "); // 一共多少个数，比如 50
        int n = in.nextInt();

        int lotteryOdds = 1;
        for (int i = 1; i <= k; i++) {
            lotteryOdds = lotteryOdds * (n - i + 1) / i;
        }

        System.out.println("Your odds are 1 in " + lotteryOdds + ". Good luck!");
    }
}
