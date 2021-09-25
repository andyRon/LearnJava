package com.andyorn.demo01;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 练习Thread，实现多线程下载图片
 * @author Andy Ron
 */
public class TestThread2 extends Thread{
    /**
     * 网络图片地址
     */
    private String url;
    /**
     * 保存的文件名
     */
    private String name;

    public TestThread2(String url, String name) {
        this.url = url;
        this.name = name;
    }

    /**
     * 下载图片线程的执行体
     */
    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url, name);
        System.out.println("下载了图片，文件名保为：" + name);
    }

    public static void main(String[] args) {
        TestThread2 t1 = new TestThread2("https://img-blog.csdnimg.cn/19dd3832669b40aca8906ee26a87d2a0" +
                ".png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10," +
                "text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzODk4MTQx,size_16,color_FFFFFF,t_70#pic_center", "1.jpg");
        TestThread2 t2 = new TestThread2("https://img-blog.csdnimg.cn/19dd3832669b40aca8906ee26a87d2a0" +
                ".png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10," +
                "text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzODk4MTQx,size_16,color_FFFFFF,t_70#pic_center", "2.jpg");
        TestThread2 t3 = new TestThread2("https://img-blog.csdnimg.cn/19dd3832669b40aca8906ee26a87d2a0" +
                ".png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10," +
                "text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzODk4MTQx,size_16,color_FFFFFF,t_70#pic_center", "3.jpg");

        // 下载顺序不一定，他们是同时进行的
        t1.start();
        t2.start();
        t3.start();
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
