package top.andyron.ch13.ch132;

import java.util.concurrent.*;

// Callable 带超时的获取结果
public class CallableWithTimeout {
    static class LongRunningTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            Thread.sleep(5000); // 模拟5秒耗时任务
            return "Task Completed";
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new LongRunningTask());

        try {
            // 设置超时时间为2秒
            String result = future.get(2, TimeUnit.SECONDS);
            System.out.println("Result: " + result);
        } catch (TimeoutException e) {
            System.out.println("Task timed out!");
            future.cancel(true); // 尝试取消任务
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdownNow();
        }
    }
}
