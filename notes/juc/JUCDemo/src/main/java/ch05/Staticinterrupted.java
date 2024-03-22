package ch05;

/**
 * 测试静态方法interrupted( )
 * @author andyron
 **/
public class Staticinterrupted {
    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());     // false
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());     // false
        System.out.println("-----1");
        Thread.currentThread().interrupt();  // 中断标志位设置为true
        System.out.println("-----2");
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());     // true  返回当前线程的中断状态并清除
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());     // false

    }

}
