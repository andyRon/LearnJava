package ch03;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author andyron
 **/
public class CompletableFutureAPIDemo {
    public static void main(String[] args)
//            throws ExecutionException, InterruptedException, TimeoutException
    {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "abc";
        });

//        System.out.println(completableFuture.get());
//        System.out.println(completableFuture.get(2L, TimeUnit.SECONDS));
//        System.out.println(completableFuture.join());

        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }

//        System.out.println(completableFuture.getNow("xxx"));  // 立即获取结果不阻塞，计算完就返回计算完结果，没算完就直接返回设定的valueIfAbsent的值

        // 返结果视情况出现两种情况：
        //      true    completValue
        //      false   abc
        System.out.println(completableFuture.complete("completValue") + "\t" + completableFuture.join());
    }
}
