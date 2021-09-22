import java.util.Arrays;

public class MyArray {
    public static void main(String[] args) {
        int[] a = new int[100];
        for(int i = 0; i < 100; i++)
            a[i] = i;


        int[] smallPrimes = {2, 3, 5};

//        new int[]{17, 19, 31};
        smallPrimes = new int[]{17, 19, 31, 23, 25, 35};

        int[] luckyNumbers = smallPrimes;
        luckyNumbers[5] = 13;

        int[] copiedLuckyNumbers = Arrays.copyOf(luckyNumbers, luckyNumbers.length);


        



    }
}
