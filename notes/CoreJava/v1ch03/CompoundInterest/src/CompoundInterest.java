/**
 * 表-不同利率下的投资增长情况
 *
 * 建立一个数值表，用来显示在不同利率下投资$10，000会增长多少，利息每年兑现，而且又被用于投资
 *
 * @author Andy Ron
 */
public class CompoundInterest {
    public static void main(String[] args) {
        final double STARTRATE = 10;
        final int NRATES = 6;
        // 显示多少年
        final int NYEARS = 10;

        // 设置利息 10% 到 15%
        double[] interestRate = new double[NRATES];
        for (int j = 0; j < interestRate.length; j++) {
            interestRate[j] = (STARTRATE + j)/100.0;
        }

        double[][] balances = new double[NYEARS][NRATES];

        // 设置初始金额为10000
        for (int j = 0; j < balances[0].length; j++) {
            balances[0][j] = 10000;
        }

        // 计算未来的利息
        for (int i = 1; i < balances.length; i++) {
            for (int j = 0; j < balances[i].length; j++) {

                double oldBalance = balances[i - 1][j];

                double interest = oldBalance * interestRate[j];

                balances[i][j] = oldBalance + interest;
            }
        }

        for (int j = 0; j < interestRate.length; j++) {
            System.out.printf("%9.0f%%", 100 * interestRate[j]);
        }

        System.out.println();

        for (double[] row : balances) {
            for (double b : row) {
                System.out.printf("%10.2f", b);
            }
            System.out.println();
        }
    }
}
