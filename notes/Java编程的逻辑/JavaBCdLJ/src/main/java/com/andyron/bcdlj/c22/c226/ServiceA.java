package com.andyron.bcdlj.c22.c226;

public class ServiceA {
    @SimpleInject
    ServiceB b;

    public void callB() {
        b.action();
    }
}
