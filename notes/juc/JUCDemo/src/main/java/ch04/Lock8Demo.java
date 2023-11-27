package ch04;


import java.util.concurrent.TimeUnit;
// 线程操作资源类
// 资源类
class Phone {
    public synchronized void sendEmail() {
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("-----sendEmail");
    }
    public synchronized void sendSMS() {
        System.out.println("-----sendSMS");
    }

    public static synchronized void sendEmailStatic() {
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("-----sendEmail");
    }
    public static synchronized void sendSMSStatic() {
        System.out.println("-----sendSMS");
    }
    public void hello() {
        System.out.println("-----hello");
    }
}
/**
 * 题目：谈谈你对多线程锁的理解，8锁案例说明
 * 口诀：线程    操作      资源类
 * 8锁案例说明
 * 1 标准访问有a、b两个线程，请问先打印邮件还是短信？ 先邮件后短信
 * 2 在sendEmail方法中加入暂停3秒钟，请问先打印邮件还是短信？暂停3秒，先邮件后短信
 * 3 添加一个普通的hello方法，在b线程中调用（替换调用短信），请问先打印右键换是hello？ 先hello，3秒后打印邮件
 * 4 有两部手机，分别调用邮件和短信，请问先打印邮件还是短信？先短信，暂停3秒，后邮件
 * 5 有两个静态同步方法，有1部手机，请问先打印邮件还是短信？ 暂停后，先邮件后短信
 * 6 有两个静态同步方法，有2部手机，请问先打印邮件还是短信？ 暂停后，先邮件后短信
 * 7 一个静态同步方法（邮件），一个普通同步方法（短信），有1部手机，请问先打印邮件还是短信？  先短信，暂停 后邮件
 * 8 一个静态同步方法（邮件），一个普通同步方法（短信），有2部手机，请问先打印邮件还是短信？  先短信，暂停 后邮件
 **/
public class Lock8Demo {
    public static void main(String[] args) {
        t1();
    }

    static void t1() {
        Phone phone = new Phone();

        new Thread(() -> {
            phone.sendEmail();
        }, "a").start();

        // 暂停毫秒，保证a线程先启动
        try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            phone.sendSMS();
        }, "b").start();
    }
    static void t3() {
        Phone phone = new Phone();

        new Thread(() -> {
            phone.sendEmail();
        }, "a").start();

        // 暂停毫秒，保证a线程先启动
        try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            phone.hello();
        }, "b").start();
    }

    static void t4() {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            phone.sendEmail();
        }, "a").start();

        // 暂停毫秒，保证a线程先启动
        try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            phone2.sendSMS();
        }, "b").start();
    }

    static void t5() {
        Phone phone = new Phone();

        new Thread(() -> {
            phone.sendEmailStatic();
        }, "a").start();

        // 暂停毫秒，保证a线程先启动
        try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            phone.sendSMSStatic();
        }, "b").start();
    }

    static void t6() {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            phone.sendEmailStatic();
        }, "a").start();

        // 暂停毫秒，保证a线程先启动
        try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            phone2.sendSMSStatic();
        }, "b").start();
    }

    static void t7() {
        Phone phone = new Phone();

        new Thread(() -> {
            phone.sendEmailStatic();
        }, "a").start();

        // 暂停毫秒，保证a线程先启动
        try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            phone.sendSMS();
        }, "b").start();
    }

    static void t8() {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            phone.sendEmailStatic();
        }, "a").start();

        // 暂停毫秒，保证a线程先启动
        try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            phone2.sendSMS();
        }, "b").start();
    }
}
