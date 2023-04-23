package com.andyron.java2;

/**
 * 比较`javac` 和 `javac -g`生成的字节码文件的不同
 *
 * @author andyron
 **/
public class JavapTest {
    private int num;
    boolean flag;
    protected char gender;
    public String info;

    public static final int COUNTS = 1;
    static {
        String url = "andyron.com";
    }
    {
        info = "java";
    }

    public JavapTest() {
    }

    private JavapTest(boolean flag) {
        this.flag = flag;
    }
    private void methodPrivate() {

    }
    int getNum(int i) {
        return num + i;
    }
    protected char showGender() {
        return gender;
    }
    public void showInfo() {
        int i = 10;
        System.out.println(info + i);
    }
}
