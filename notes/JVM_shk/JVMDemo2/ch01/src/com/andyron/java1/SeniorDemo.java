package com.andyron.java1;

/**
 * 测试查看字节码文件
 * @author andyron
 **/
public class SeniorDemo {
    private int num = 1;
    public final String info = "andyron";
    boolean[] counts;

    public SeniorDemo() {
    }
    public SeniorDemo(int num) {
        this.num = num;
    }

    public String getInfo() {
        return info;
    }
    public void addNum(int n) {
        num += n;
        System.out.println(num);
    }
}
