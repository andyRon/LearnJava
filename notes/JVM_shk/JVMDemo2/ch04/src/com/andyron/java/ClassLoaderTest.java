package com.andyron.java;

import sun.misc.Launcher;

import java.net.URL;

/**
 * @author andyron
 **/
public class ClassLoaderTest {
    public static void main(String[] args) {
        System.out.println("************启动类加载器******************");
        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urls) {
            System.out.println(url.toExternalForm());
        }

        System.out.println("************扩展类加载器******************");
        for (String path : System.getProperty("java.ext.dirs").split(System.getProperty("path.separator") )) {
            System.out.println(path);
        }

        System.out.println("************应用程序类加载器******************");
        for (String path : System.getProperty("java.class.path").split(System.getProperty("path.separator") )) {
            System.out.println(path);
        }

    }
}
