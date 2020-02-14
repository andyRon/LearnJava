《Java核心技术卷一第10版》笔记

------

http://horstmann.com/corejava



## 1 Java程序设计概述

一旦一种语言应用于某个领域，与现存代码的相容性问题就摆在了人们的面前。



### Java的11个关键术语

1. 简单性
2. 面向对象
3. 分布式
4. 健壮性
5. 安全性
6. 体系结构中立
7. 可移植性
8. 解释型
9. 高性能
10. 多线程
11. 动态性



在网页中运行的Java程序成为**applet**（已过时）。



“毕竟，语言只是实现目标的工具，而不是目标本身”。

--- James Gosling



## 2 Java程序设计环境

### 2.1 安装Java开发工具包

#### 下载JDK

Mac中JDK安装位置一般为：`/Library/Java/JavaVirtualMachines/jdk-13.0.2.jdk/Contents/Home/`

#### 设置JDK



#### 安装库源文件和文档



### 2.2 使用命令行工具



### 2.5构建并运行applet

我的系统没有 `appletviewer`

## 3.Java的基本程序设计结构



### 3.1 一个简单的Java应用程序

源代码的文件名必须与公共类的名字相同。

Java虚拟机将从指定类中的main方法开始执行。



http://bugs.java.com/bugdatabase/index.jsp

java bug数据库，通过bug号（例如 4252539），来查询。

### 3.2 注释

### 3.3 数据类型

8中基本类型，其中4种整型：`int`，`short`，`long`，`byte`；2种浮点类型：`float`，`double`；表示Unicode编码的`char`；`boolean`。

在Java中，整型的范围与运行的机器无关；没有无符号。

#### char

强烈建议不要使用char类型。

### 3.4 变量

逐一声明每一个变量可以提高程序的可读性。

变量的声明尽可能地靠近变量第一次使用的地方。

### 3.5 运算符



### 3.6 字符串

不可变字符串的优点：编译器可以让字符串**共享**。

Java的设计者认为共享带来的高效率远远胜过于提取、拼接字符串所带来的低效率。

#### 3.6.6 Code Points and Code Units



#### 3.6.7 String API

`String`有50+有用的方法

chartAt

codePointAt



#### StringBuilder

### 3.7 IO

```java
Console cons = System.console();
String username = cons.readLine("User name: ");
char[] passwd = cons.readPassword("Password: ");
```

#### 

#### 格式化输出



#### 文件输入与输出



### 3.8 控制流程



### 3.9 大数值

`BigInteger`

`BigDecimal`

任意长度

### 3.10 数组



## 4.对象与类



### 4.2 使用预定义类



#### 4.2.1 对象与对象变量

一个对象变量并没有实际包含一个对象，而仅仅引用一个对象。

#### 4.2.2 LocalDate



#### 4.2.3 mutator method and accessor method



### 4.3 用户自定义类



### 4.4 静态属性和静态方法??



### 4.5 Method Parameters ??



### 4.6 对象构造



#### 对象析构与finalize方法

由于Java有自动的垃圾回收器，不需要人工回收内存，所以Java不支持析构器。



### 4.7 包



### 4.8 类路径??



## 5 继承