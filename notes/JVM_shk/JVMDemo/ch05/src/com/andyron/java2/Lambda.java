package com.andyron.java2;

/**
 * 体会 invokedynamic 指令
 * @author andyron
 **/
@FunctionalInterface  // 可有可无，提示一个方法的接口为函数接口
interface Func {
    public boolean func(String str);
}
public class Lambda {
    public void lambda(Func func) {
        return;
    }

    public static void main(String[] args) {
        Lambda lambda = new Lambda();
        /*
         对Java虚拟机规范的修改，而不是对Java语言规则的修改。
         语言规则还是，确定变量类型，让后赋值。
         */
        Func func = s -> {
            return true;
        };

        lambda.lambda(func);

        lambda.lambda(s -> {
            return true;
        });
    }
}
