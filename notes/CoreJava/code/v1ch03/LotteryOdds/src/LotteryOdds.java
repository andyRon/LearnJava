import java.util.Scanner;

/**
 * 3.8.4
 * <code>for</code> loop
 * 计算抽奖中奖的概率
 */
public class LotteryOdds {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 可以抽几个数，比如 6
        System.out.print("How many numbers do you need to draw? ");
        int k = in.nextInt();

        // 一共多少个数，比如 50
        System.out.print("What is the highest number you can draw? ");
        int n = in.nextInt();

        // lottery 彩票、运气，odds 可能性、概率
        int lotteryOdds = 1;
        for (int i = 1; i <= k; i++) {
            lotteryOdds = lotteryOdds * (n - i + 1) / i;
        }

        System.out.println("Your odds are 1 in " + lotteryOdds + ". Good luck!");
    }
}
