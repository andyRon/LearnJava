package com.andyorn.demo02;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

/**
 线程创建方式三：实现Callable接口

 callable的好处：

 1. 可以定义返回值
 2. 可以抛出异常

 */
public class TestCallable implements Callable<Boolean> {
    private String url;
    private String name;

    public TestCallable(String url, String name) {
        this.url = url;
        this.name = name;
    }

    /**
     * 下载图片线程的执行体
     *
     * call()的返回值是Callable<Boolean>中的泛型相同
     */
    @Override
    public Boolean call() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url, name);
        System.out.println("下载了图片，文件名保为：" + name);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable t1 = new TestCallable("https://i2.hoopchina.com" +
                ".cn/hupuapp/bbs/111111/thread_111111_20210925072957_s_19733_w_465_h_420_30758.jpg", "1.jpg");
        TestCallable t2 = new TestCallable("https://i2.hoopchina.com" +
                ".cn/hupuapp/bbs/111111/thread_111111_20210925072957_s_19733_w_465_h_420_30758.jpg", "2.jpg");
        TestCallable t3 = new TestCallable("https://i2.hoopchina.com" +
                ".cn/hupuapp/bbs/111111/thread_111111_20210925072957_s_19733_w_465_h_420_30758.jpg", "3.jpg");

//         1. 创建执行服务：
        ExecutorService ser = Executors.newFixedThreadPool(3);
//        2. 提交执行：
        Future<Boolean> r1 = ser.submit(t1);
        Future<Boolean> r2 = ser.submit(t2);
        Future<Boolean> r3 = ser.submit(t3);
//        3. 获取结果：
        boolean rs1 = r1.get();
        boolean rs2 = r2.get();
        boolean rs3 = r3.get();
//        4. 关闭服务：
        ser.shutdownNow();
    }
}

/**
 * 下载器
 */
class WebDownloader {
    // 下载方法
    public void downloader(String url, String name) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常，downloader方法出现问题");
        }
    }
}
