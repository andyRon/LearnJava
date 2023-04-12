package com.andyron.java3;

/**
 * 虚方法表示列
 * @author andyron
 **/
interface Friendly {
    void sayHello();
    void sayGoodbye();
}
class Dog {
    public void sayHello() {
    }
    public String toString() {
        return "Dog";
    }
}
class Cat implements Friendly {
    public void eat() {

    }
    @Override
    public void sayHello() {
    }

    @Override
    public void sayGoodbye() {
    }
    protected void finalize() {
    }
    public String toString() {
        return "Cat";
    }
}
class CockerSpaniel extends Dog implements Friendly {
    @Override
    public void sayGoodbye() {
    }
    @Override
    public void sayHello() {
        super.sayHello();
    }
}
public class VirtualMethodTable {
}
