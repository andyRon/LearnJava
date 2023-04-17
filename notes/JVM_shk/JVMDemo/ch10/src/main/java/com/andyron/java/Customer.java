package com.andyron.java;

/**
 * 测试对象实例化的过程
 * 1 加载类信息 2 为对象分配内存 3 处理并发问题 4 属性的默认初始化（零值初始化）
 * 5 设置对象头信息 6 属性的显示初始化、代码块中初始化、构造器中初始化
 *
 * 给对象的属性赋值的操作：
 * 1️⃣ 属性的默认初始化； 2️⃣显示初始化/3️⃣代码块中初始化； 4️⃣构造器中初始化
 * @author andyron
 **/
public class Customer {
    int id = 1001;  //  2️⃣显示初始化
    String name;
    Account acct;

    {
        name = "匿名客户";  // 3️⃣代码块中初始化
    }
    public Customer() {
        acct = new Account();  // 4️⃣构造器中初始化
    }
}
class Account {
}
