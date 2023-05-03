package com.andyron.bcdlj.c15.c152;

/**
 * @author andyron
 **/
public class Switcher {
    // 保证内存可见性
    private volatile boolean on;

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }
}
