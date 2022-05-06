package com.andyron.bcdlj.c05.c53;

import com.andyron.bcdlj.c05.c53.c531.Outer;

public class Main {

    public static void main(String[] args) {
        Outer.StaticInner inner = new Outer.StaticInner();
        inner.innerMethod();
    }
}
