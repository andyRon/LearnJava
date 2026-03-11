package top.andyron.ch13.ch132;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableDemo {

    // 1. 实现Callable接口，指定泛型为返回类型
    static class MyCallable implements Callable<String> {
        private final int taskId;

        public MyCallable(int taskId) {
            this.taskId = taskId;
        }

        @Override
        public String call() throws Exception {
            // 模拟耗时计算
            Thread.sleep(1000);
            return String.format("Task-%d completed at %d",
                    taskId, System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        // 2. 创建Callable实例
        Callable<String> callable = new MyCallable(1);

        // 3. 用FutureTask包装Callable
        FutureTask<String> futureTask = new FutureTask<>(callable);

        // 4. 创建线程并启动
        Thread thread = new Thread(futureTask);
        thread.start();

        // 5. 获取结果（会阻塞直到计算完成）
        try {
            String result = futureTask.get();  // 阻塞等待结果
            System.out.println("Result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
