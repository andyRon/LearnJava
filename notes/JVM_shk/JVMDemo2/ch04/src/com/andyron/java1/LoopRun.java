package com.andyron.java1;

import java.lang.reflect.Method;

/**
 * 热替换示列
 * 在代码目录先用`javac Demo1.java`生成Demo1.class文件，然后运行程序后，重新修改Demo1.hot()方法，
 * 再用`javac Demo1.java`生成Demo1.class文件，程序不需要重新启动，修改结果就生效。
 * @author andyron
 **/
public class LoopRun {
    public static void main(String[] args) {
        // 每次调用Demo1.hot()方法之前，都会重新加载DemoA
        while (true) {
            try {
                MyClassLoader loader = new MyClassLoader(System.getProperty("user.dir") + "/ch04/src/");
                Class<?> clazz = loader.findClass("com.andyron.java1.Demo1");
                Object demo = clazz.newInstance();
                Method m = clazz.getMethod("hot");
                m.invoke(demo);
                Thread.sleep(5000);
            } catch (Exception e) {
                System.out.println("not find");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
