package com.andyron.bcdlj.c24.c245;

import com.andyron.bcdlj.c24.c244.MyClassLoader;

import java.io.File;

public class HotDeployDemo {

    /**
     * 实现类名称
     */
    private static final String CLASS_NAME = "com.andyron.bcdlj.c24.HelloImpl";
    /**
     * 具体的class文件路径
     */
    private static final String FILE_NAME = "data/" + CLASS_NAME.replaceAll("\\.", "/")+".class";
    private static volatile IHelloService helloService;

    public static IHelloService getHelloService() {
        if (helloService != null) {
            return helloService;
        }
        synchronized (HotDeployDemo.class) {
            if (helloService == null) {
                helloService = createHelloService();
            }
            return helloService;
        }
    }

    private static IHelloService createHelloService() {
        try {
            MyClassLoader cl = new MyClassLoader();
            Class<?> cls = cl.loadClass(CLASS_NAME);
            if (cls != null) {
                return (IHelloService) cls.newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void client() {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        IHelloService helloService = getHelloService();
                        helloService.sayHello();
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();
    }

    public static void monitor() {
        Thread t = new Thread() {
            private long lastModified = new File(FILE_NAME).lastModified();

            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(100);
                        long now = new File(FILE_NAME).lastModified();
                        if (now != lastModified) {
                            lastModified = now;
                            reloadHelloService();
                        }
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();
    }

    public static void reloadHelloService() {
        helloService = createHelloService();
    }

    public static void main(String[] args) {
        monitor();
        client();
    }

}
