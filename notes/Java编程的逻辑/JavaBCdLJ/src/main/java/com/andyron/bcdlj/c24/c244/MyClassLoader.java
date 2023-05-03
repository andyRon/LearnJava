package com.andyron.bcdlj.c24.c244;

import com.andyron.bcdlj.c13.BinaryFileUtils;

import java.io.IOException;

public class MyClassLoader extends ClassLoader {

    private static final String BASE_DIR = "data/";

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String fileName = name.replaceAll("\\.", "/");
        fileName = BASE_DIR + fileName + ".class";

        try {
            byte[] bytes = BinaryFileUtils.readFileToByteArray(fileName);
            return defineClass(fileName, bytes, 0, bytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("failed to load class " + name, e);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
//        Class<?> cls = Class.forName("com.andyron.bcdlj.c24.Hello");
//        System.out.println(cls.getName());

//        MyClassLoader cl = new MyClassLoader();
//        Class<?> aClass = cl.findClass("Student");
//        System.out.println(aClass.getName());
    }

}
