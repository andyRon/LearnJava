package com.andyorn.demo03;

/**
 * 静态代理模式
 * 真实对象和代理对象都要实现同一个接口
 * 代理对象要代理真实角色
 *
 * 好处：
 *      代理对象可以做很多真实对象做不了的事
 *      真实对象专注于自己的事情
 * @author Andy Ron
 */
public class StaticProxy {
    public static void main(String[] args) {
        You you = new You();
        WeddingCompany weddingCompany = new WeddingCompany(you);
        weddingCompany.happyMarry();
    }
}

interface Marry {
    void happyMarry();
}

/**
 * 真实角色，你去结婚
 */
class You implements Marry {

    @Override
    public void happyMarry() {
        System.out.println("我要结婚了，好开心");
    }
}

/**
 * 代理角色，帮助你结婚
 */
class WeddingCompany implements Marry {

    // 代理谁？ --》 真实目标角色
    private Marry target;

    public WeddingCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void happyMarry() {
        before();
        this.target.happyMarry(); // 这是真实对象
        after();
    }

    private void after() {
        System.out.println("结婚之后收尾款");
    }

    private void before() {
        System.out.println("结婚之前布置现场");
    }
}


