package com.andyron.bcdlj.c04.c422;

/**
 * @author andyron
 **/
public class Child extends Base {
    public static String s = "child_base";
    public String m = "child";
    public static void staticTest(){
        System.out.println("child static: "+s);
    }

    public static void main(String[] args) {
        Child c = new Child();
        Base b = c;
        System.out.println(b.s);
        System.out.println(b.m);
        b.staticTest();
        System.out.println(c.s);
        System.out.println(c.m);
        c.staticTest();
    }
}
