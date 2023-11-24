package ch03;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author andyron
 **/
public class CompletableFutureAPI3Demo {
    public static void main(String[] args) {
//        t1();

        // 对比三种方式的不同
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenRun(() -> {}).join()); // null
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenAccept(r -> System.out.println(r)).join());
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenApply(r -> r + "resultB").join());

    }

    private static void t1() {
        CompletableFuture.supplyAsync(() -> {
            return 1;
        }).thenApply(f -> {
            return f + 2;
        }).thenApply(f -> {
            return f + 3;
        }).thenAccept(System.out::println);  // 6

        /*
        .thenAccept(r -> {
            System.out.println(r);
         })
         */
    }

}
