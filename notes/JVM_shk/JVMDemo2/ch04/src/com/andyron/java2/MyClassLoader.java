package com.andyron.java2;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author andyron
 **/
public class MyClassLoader extends ClassLoader {
    private String byteCodePath;

    public MyClassLoader(String byteCodePath) {
        this.byteCodePath = byteCodePath;
    }

    public MyClassLoader(ClassLoader parent, String byteCodePath) {
        super(parent);
        this.byteCodePath = byteCodePath;
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        String fileName = byteCodePath + className + ".class";
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName));
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            int len;
            byte[] data = new byte[1024];
            while ((len = bis.read(data)) != -1) {
                baos.write(data, 0, len);
            }
            // 获取内存中的完整的字节数组的数据
            byte[] byteCodes = baos.toByteArray();
            // 调用defineClass方法，将字节数据的数据转换为Class的实例
            return defineClass(null, byteCodes, 0, byteCodes.length);

        } catch (IOException e) {
            e.printStackTrace();
        } 
        return null;
    }
}
