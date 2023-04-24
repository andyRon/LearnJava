package com.andyron.java;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author andyron
 * ;// TODO
 **/
public class UserClassLoader extends ClassLoader {
    private String rootDir;

    public UserClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 获取类的class文件字节数组
        byte[] classData = getClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            // 直接生成class对象
            return defineClass(name, classData, 0, classData.length);
        }
    }

    /**
     * 编写获取class文件并转换为字节码流的逻辑
     */
    private byte[] getClassData(String className) {
        // 读取类文件的字节
        String path = classNameToPath(className);
        try {
            FileInputStream ins = new FileInputStream(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            // 读取类文件的字节码
            while ((len = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 类文件的完全路径
     */
    private String classNameToPath(String className) {
        String sep = System.getProperty("file.separator");
        return rootDir + sep + className.replace('.', sep.charAt(0)) + ".class";
    }

    public static void main(String[] args) {
        String rootDir = System.getProperty("user.dir") + "/ch04/src";

        try {
            // 创建自定义的类加载器1
            UserClassLoader loader1 = new UserClassLoader(rootDir);
            Class<?> clazz1 = loader1.findClass("com.andyron.java.User");
            // 创建自定义的类加载器2
            UserClassLoader loader2 = new UserClassLoader(rootDir);
            Class<?> clazz2 = loader2.findClass("com.andyron.java.User");

            System.out.println(clazz1 == clazz2);   // clazz1与clzz2对应了不同的类模版结构
            System.out.println(clazz1.getClassLoader());
            System.out.println(clazz2.getClassLoader());

            // ###########
            // 使用系统类加载器，加载的IDEA编译好的class文件。可删除测试，报错
            Class clazz3 = ClassLoader.getSystemClassLoader().loadClass("com.andyron.java.User");
            System.out.println(clazz3.getClassLoader());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
