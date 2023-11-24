package ch03;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author andyron
 **/
public class CompletableFutureAPI4Demo {
    public static void main(String[] args) {
        CompletableFuture<String> playA = CompletableFuture.supplyAsync(() -> {
            System.out.println("A come in");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "playA";
        });
        CompletableFuture<String> playB = CompletableFuture.supplyAsync(() -> {
            System.out.println("B come in");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "playB";
        });
        CompletableFuture<String> result = playA.applyToEither(playB, f -> {
            return f + " is winner";
        });

        System.out.println(Thread.currentThread().getName() + "\t-----: " + result.join());
    }



}
