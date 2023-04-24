package com.andyron.java;

/**
 * @author andyron
 **/
public class UserTest {
    public static void main(String[] args) {
        User user = new User();  // 隐式加载

        try {
            Class<?> clazz = Class.forName("com.andyron.java.User"); // 显式加载
            ClassLoader.getSystemClassLoader().loadClass("com.andyron.java.User");  // 显式加载
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
