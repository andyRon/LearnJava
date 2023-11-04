package ch03;

import java.util.concurrent.*;

/**
 * Future的优点
 * @author andyron
 **/
public class FutureThreadDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 3个任务，目前开启多个异步任务线程来处理，请问耗时多少？ 如果没有需要返回结果约340ms，需要返回结构约840ms+

        // 使用有3个线程的线程池，能够复用。减少使用new来创建
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        long startTime = System.currentTimeMillis();

        FutureTask<String> futureTask1 = new FutureTask<>(() -> {
            try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
            return "task1 over";
        });
        threadPool.submit(futureTask1);

        FutureTask<String> futureTask2 = new FutureTask<>(() -> {
            try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
            return "task2 over";
        });
        threadPool.submit(futureTask2);

        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());

        // main 线程
        try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }

        long endTime = System.currentTimeMillis();
        System.out.println("----costTime: " + (endTime - startTime) + "毫秒");
        System.out.println(Thread.currentThread().getName() + "\t ----end");

        threadPool.shutdown();
    }

    private static void m1() {
        // 3个任务，目前只有一个线程main来处理，请问耗时多少？ 越1100ms+
        long startTime = System.currentTimeMillis();

        try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
        try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
        try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }

        long endTime = System.currentTimeMillis();
        System.out.println("----costTime: " + (endTime - startTime) + "毫秒");

        System.out.println(Thread.currentThread().getName() + "\t ----end");
    }
}
