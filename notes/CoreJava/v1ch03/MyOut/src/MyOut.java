import java.util.Date;

public class MyOut {
    public static void main(String[] args) {

//        System.out.printf("%,.2f", 100000.0 / 3.0);   //逗号表示添加分隔符
//        System.out.printf("%,+.2f", -100000.0/ 3.0);
//        System.out.printf("%,(.2f", -100000.0/ 3.0);

        String name = "IntelliJ IDEA";
        String message = String.format("Hello, $s!", name);

//        System.out.printf("%tc", new Date());
//        System.out.printf("%tB", new Date());
        // 参数索引，从1开始
        System.out.printf("%1$s %2$tB %2$te, %2$tY", "Due Date:", new Date());
    }
}
