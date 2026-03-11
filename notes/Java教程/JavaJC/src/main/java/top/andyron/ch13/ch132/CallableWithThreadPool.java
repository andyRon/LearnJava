package top.andyron.ch13.ch132;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

// Callable+ 线程池
public class CallableWithThreadPool {
    // 实现一个有返回值和异常抛出的任务
    static class CalculationTask implements Callable<Double> {
        private final int number;

        public CalculationTask(int number) {
            this.number = number;
        }

        @Override
        public Double call() throws Exception {
            if (number < 0) {
                throw new IllegalArgumentException("Number must be non-negative");
            }

            // 模拟复杂计算：计算平方根
            Thread.sleep(500); // 模拟耗时
            return Math.sqrt(number);
        }
    }

    public static void main(String[] args) {
        // 1. 创建线程池（固定大小为4）
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // 2. 创建任务列表
        List<Future<Double>> futureList = new ArrayList<>();
        List<CalculationTask> tasks = new ArrayList<>();

        // 准备10个任务
        for (int i = 0; i < 10; i++) {
            tasks.add(new CalculationTask(i));
        }

        // 3. 提交所有任务到线程池
        for (CalculationTask task : tasks) {
            Future<Double> future = executor.submit(task);
            futureList.add(future);
        }

        // 4. 获取结果
        for (int i = 0; i < futureList.size(); i++) {
            try {
                Future<Double> future = futureList.get(i);
                // get()会阻塞直到任务完成
                Double result = future.get();
                System.out.printf("Task %d: sqrt(%d) = %.4f%n",
                        i, i, result);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Task interrupted: " + e.getMessage());
            } catch (ExecutionException e) {
                // 捕获call()方法抛出的异常
                System.out.println("Task failed: " + e.getCause().getMessage());
            } catch (CancellationException e) {
                System.out.println("Task was cancelled");
            }
        }

        // 5. 关闭线程池
        executor.shutdown();

        // 等待所有任务完成
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        System.out.println("All tasks completed!");
    }
}
