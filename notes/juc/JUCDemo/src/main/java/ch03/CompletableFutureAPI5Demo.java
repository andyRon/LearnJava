package ch03;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author andyron
 **/
public class CompletableFutureAPI5Demo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        t1();
        t2();

    }

    private static void t2() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> thenCombineResult = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " ----come in 1");
            return 10;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " ----come in 2");
            return 20;
        }), (x, y) -> {
            System.out.println(Thread.currentThread().getName() + " ----come in 3");
            return x + y;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " ----come in 4");
            return 50;
        }), (a, b) -> {
            System.out.println(Thread.currentThread().getName() + " ----come in 5");
            return a + b;
        });

        System.out.println("----主线程结束");
        System.out.println(thenCombineResult.get());
    }

    private static void t1() {
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t-----启动");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t-----启动");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 20;
        });

        // x,y就是用合并的两个CompletableFuture的结果
        CompletableFuture<Integer> result = completableFuture1.thenCombine(completableFuture2, (x, y) -> {
            System.out.println("------开始两个结果合并");
            return x + y;
        });

        System.out.println(result.join());
    }


}
