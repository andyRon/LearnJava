
import static java.lang.Math.*;

/**
 * 第一个程序
 * @version 2020-02-01
 * @author Andy Ron
 */
public class FirstSample {

    public static final double CM_PER_INCH = 2.54;  // 类常量

    public static void main(String[] args) {
        System.out.println("Hello, IntelliJ IDEA");

        int a = 0x3;
        int b = 0245;
        int c = 0b10001;
        long l = 4000001l;
        int m = 1_000_000;
        float f = 3.14f;
        double d = 3.14d;

//        Double.POSITIVE_INFINITY;
//        Double.NEGATIVE_INFINITY;
//        Double.NaN;
//        Float.POSITIVE_INFINITY;
        Double.isNaN(d);

        char ch = 'A';
        char uni = '\u2122';
        long num = 2^16;
        Character.isJavaIdentifierPart(ch);

        double salary;
        int vacationDays;
        boolean done;

        final double PI = 3.1415161397;  // 常量

        double y = Math.sqrt(4.0);
        double y2 = Math.pow(2, 5);
        double x;
        x = Math.sin(90);
        x = Math.exp(2.1);
        x = PI;

        int nx = (int)round(x);

        String greeting = "Hello! ";
        String subS = greeting.substring(1, 3);
        String all = "S " + "M";
        all = String.join("/","S", "M", "L", "XL");

        all.equals(subS);
        all.equalsIgnoreCase(subS);

    }

}
