package com.andyron.java;

/**
 * @author andyron
 **/
public class StringExer {
    String str = new String("good");
    char[] ch = {'t', 'e', 's', 't'};

    public void chang(String str, char ch[]) {
        str = "test Ok";        // 这里的str是方法中的局部变量，不是对象的成员变量
//        this.str = "test Ok";  // this.str这个成员变量指向了字符串常量池中"test Ok"地址
        ch[0] = 'b';        // this.ch 和 ch虽然是两个不同的变量，但它们都指向同一个地址，而ch[0]='b' 修改了这个地址对应对象的值
    }

    public static void main(String[] args) {
        StringExer ex = new StringExer();
        ex.chang(ex.str, ex.ch);
        System.out.println(ex.str); // good
        System.out.println(ex.ch); // best
    }
}
