import static java.lang.Math.*;

/**
 * 第一个程序
 * @version 2020-02-01
 * @author Andy Ron
 */
public class FirstSample {

    /**
     * 类常量
     */
    public static final double CM_PER_INCH = 2.54;

    public static void main(String[] args) {
        System.out.println("Hello, IntelliJ IDEA");

        int a = 0x3;
        // 八进制
        int b = 010;
        int c = 0b10001;
        long l = 4000001L;
        int m = 1_000_000;
        float f = 3.14f;
        double d = 3.14d;
        long num = 2^16;

        // 字符字面量
        char ch = 'A';
        char uni = '\u2122';
        // 判断字符ch是否属于Java的"字母"
        Character.isJavaIdentifierPart(ch);

        double salary;
        int vacationDays;
        boolean done;

        // 常量
        final double PI = 3.1415161397;

        double y = Math.sqrt(4.0);
        double y2 = Math.pow(2, 5);
        double x;
        x = Math.sin(90);
        x = Math.exp(2.1);
        x = PI;

        int nx = (int)round(x);

        int fl = Math.floorMod(13, 12);



        String greeting = "Hello! ";
        String subS = greeting.substring(1, 3);
        String all = "S " + "M";
        all = String.join("/","S", "M", "L", "XL");

        all.equals(subS);
        all.equalsIgnoreCase(subS);

        if (greeting == "Hello! ") {
            System.out.println("相同1");
        }
        if (greeting.substring(1,4) == "ell") {
            System.out.println("相同2");
        }


    }

}


