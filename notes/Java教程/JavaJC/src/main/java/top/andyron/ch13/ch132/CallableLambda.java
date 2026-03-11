package top.andyron.ch13.ch132;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// 使用 Lambda 表达式简化
public class CallableLambda {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // 使用Lambda创建Callable
        Callable<Integer> sumTask = () -> {
            int sum = 0;
            for (int i = 1; i <= 100; i++) {
                sum += i;
            }
            return sum;
        };

        Callable<Double> avgTask = () -> {
            double sum = 0;
            for (int i = 1; i <= 50; i++) {
                sum += i;
            }
            return sum / 50;
        };

        // 提交任务
        Future<Integer> future1 = executor.submit(sumTask);
        Future<Double> future2 = executor.submit(avgTask);

        // 获取结果
        System.out.println("Sum 1-100: " + future1.get());
        System.out.println("Avg 1-50: " + future2.get());

        executor.shutdown();
    }
}
