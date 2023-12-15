package com.andyron.bcdlj.c23.c235;

import com.andyron.bcdlj.c22.c226.SimpleInject;

/**
 * @author andyron
 **/
public class ServiceA {

    @SimpleInject
    ServiceB b;

    public void callB(){
        b.action();
    }

    public ServiceB getB() {
        return b;
    }

}
