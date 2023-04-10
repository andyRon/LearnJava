package com.andyron.java1;


import java.net.URL;

/**
 * @author andyron
 **/
public class ClassLoaderTest1 {
    public static void main(String[] args) {
        System.out.println("======启动类加载器=======");
        // 获取启动类加载器能够加载的api的路径
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urls) {
            System.out.println(url.toExternalForm());
        }
        // 从结果中随意选择一个类，看看类加载器是什么
        System.out.println(com.sun.security.sasl.Provider.class.getClassLoader());

        System.out.println("=======扩展类加载器=====");
        String extDirs = System.getProperty("java.ext.dirs");
        for (String path : extDirs.split(":")) {
            System.out.println(path);
        }
        /* macos
        /Users/andyron/Library/Java/Extensions
        /Library/Java/JavaVirtualMachines/zulu-8.jdk/Contents/Home/jre/lib/ext
        /Library/Java/Extensions
        /Network/Library/Java/Extensions
        /System/Library/Java/Extensions
        /usr/lib/java
         */
        System.out.println(org.openjsse.sun.security.util.CurveDB.class.getClassLoader());

        System.out.println("=======应用程序类加载器=====");
        String appDirs = System.getProperty("java.class.path");
        for (String path : appDirs.split(":")) {
            System.out.println(path);
        }
    }
}
