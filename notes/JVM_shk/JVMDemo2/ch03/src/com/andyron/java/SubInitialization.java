package com.andyron.java;

/**
 * @author andyron
 **/
public class SubInitialization extends InitializationTest {
    static {
        number = 4; // number字段必须提前已经加载：一会会先加载父类。
        System.out.println("son static{}");
    }

    public static void main(String[] args) {
        System.out.println(number);
    }
}
