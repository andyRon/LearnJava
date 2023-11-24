package ch03;

import java.util.concurrent.*;

/**
 * @author andyron
 **/
public class CompletableFutureUseDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        future3();

    }

    // 改进future2，通过自定义线程池来规避，主线程结束后守护线程直接结束，收不到想要的结果的情况
    private static void future3() {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        try {
            CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "-----come in");
                int result =ThreadLocalRandom.current().nextInt(10);
                // 暂停几秒钟线程
                try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println("--------1秒钟后出结果" + result);
                // 测试异常情况
                if (result > 2) {
                    int i = 10/0;
                }

                return result;
            }, threadPool).whenComplete((v, e) -> {
                if (e == null) {
                    System.out.println("----计算完成，更新系统UpdateValue： " + v);
                }
            }).exceptionally(e -> {
                e.printStackTrace();
                System.out.println("异常情况： " + e.getCause() + "\t" + e.getMessage());
                return null;
            });
            System.out.println(Thread.currentThread().getName() + "线程先去忙其它任务");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    private static void future2() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "-----come in");
            int result =ThreadLocalRandom.current().nextInt(10);
            // 暂停几秒钟线程
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("--------1秒钟后出结果" + result);
            return result;
        }).whenComplete((v, e) -> {  // 上一步顺利是。当你完成时。BiConsumer代表两个消费型参数接口，第一个是上一步的值（此处为result），第二个是会不会产生异常
            // 第一阶段结束后自动执行
            if (e == null) {
                System.out.println("----计算完成，更新系统UpdateValue： " + v);
            }
        }).exceptionally(e -> {  // 上一步不顺利时。
            e.printStackTrace();
            System.out.println("异常情况： " + e.getCause() + "\t" + e.getMessage());
            return null;
        });
        System.out.println(Thread.currentThread().getName() + "线程先去忙其它任务");

        // 让主线程不要立刻结束：暂停3秒。
        // 否则由于主线程执行太快，CompletableFuture默认线程池（守护线程）还没来得及执行，就关闭了。
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    private static void future1() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "------ come in");
            int result = ThreadLocalRandom.current().nextInt(10);
            // 暂停几秒钟线程
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("-----1秒钟后出结果：" + result);
            return result;
        });

        System.out.println(Thread.currentThread().getName() + "线程先去忙其它任务");
        System.out.println(completableFuture.get());
    }
}
