package ch03;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author andyron
 **/
public class FutureAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ----come in");
            // 暂停几秒钟线程
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
            return "task over";
        });
        Thread t1 = new Thread(futureTask, "t1");
        t1.start();

        // 1 不见不散，get非要等到结构才会离开，不管你是否计算完成，容易引起程序堵塞。一般会将其放到程序最后
//        System.out.println(futureTask.get());

        System.out.println(Thread.currentThread().getName() + "\t ----忙其它任务了");

        // 2 假如我不愿意等待，可以设置离开时间。会抛出TimeoutException，也不是很优雅
//        System.out.println(futureTask.get(3, TimeUnit.SECONDS));
        
        while (true) {
            if (futureTask.isDone()) {
                System.out.println(futureTask.get());
                break;
            } else {
                // 暂停毫秒
                try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println("正在处理中，不要再催了，越催越慢，再催熄火");
            }
        }

    }
}
/**
 * 1 get容器导致阻塞，一般放在程序最后
 * 2 假如我不愿意等待，可以设置离开时间
 */
