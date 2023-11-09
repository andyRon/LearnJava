package com.andyron.bcdlj.c05.c53.c532;

/**
 * @author andyron
 **/
public class Outer {
    private int a = 100;
    public class Inner {
        public void innerMethod() {
            System.out.println("outer a " + a);
            Outer.this.action();
        }

    }

    private void action() {
        System.out.println("action");
    }

    public void test() {
        Inner si = new Inner();
        si.innerMethod();
    }
}
