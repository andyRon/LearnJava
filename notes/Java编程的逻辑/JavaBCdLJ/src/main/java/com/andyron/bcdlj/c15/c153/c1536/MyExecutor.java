package com.andyron.bcdlj.c15.c153.c1536;

import java.util.concurrent.Callable;

/**
 * @author andyron
 **/
public class MyExecutor {
    /**
     * 异步执行任务
     * execute启动一个线程，并返回MyFuture对象，MyFuture的get方法会阻塞等待直到线程运行结束。
     */
    public <V> MyFuture<V> execute(final Callable<V> task) {
        final Object lock = new Object();
        final ExecuteThread<V> thread = new ExecuteThread<>(task, lock);
        thread.start();

        MyFuture<V> future = new MyFuture<V>() {
            @Override
            public V get() throws Exception {
                synchronized (lock) {
                    while (!thread.isDone()) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {

                        }
                    }
                    if (thread.getException() != null) {
                        throw thread.getException();
                    }
                    return thread.getResult();
                }
            }
        };
        return future;
    }

    /**
     * 子线程执行实际的子任务，记录执行结果到result变量、异常到exception变量，执行结束后设置共享状态变量done为true，
     * 并调用notifyAll，以唤醒可能在等待结果的主线程。
     */
    static class ExecuteThread<V> extends Thread {
        private V result = null;
        private Exception exception = null;
        private boolean done = false;
        private Callable<V> task;
        private Object lock;

        public ExecuteThread(Callable<V> task, Object lock) {
            this.task = task;
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                result = task.call();
            } catch (Exception e) {
                exception = e;
            } finally {
                synchronized (lock) {
                    done = true;
                    lock.notifyAll();
                }
            }
        }
        public V getResult() {
            return result;
        }
        public boolean isDone() {
            return done;
        }
        public Exception getException() {
            return exception;
        }
    }
}
