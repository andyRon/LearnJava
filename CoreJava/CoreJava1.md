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



> “毕竟，语言只是实现目标的工具，而不是目标本身”。
>    --- James Gosling



## 2 Java程序设计环境

### 2.1 安装Java开发工具包

#### 下载JDK

Mac中JDK安装位置一般为：`/Library/Java/JavaVirtualMachines/jdk-13.0.2.jdk/Contents/Home/`

#### 设置JDK



#### 安装库源文件和文档



### 2.2 使用命令行工具



### 2.4 运行图形化应用程序



### 2.5构建并运行applet

我的系统没有 `appletviewer`

## 3.Java的基本程序设计结构



### 3.1 一个简单的Java应用程序

源代码的文件名必须与公共类的名字相同。

Java虚拟机将从指定类中的main方法开始执行。

http://bugs.java.com/bugdatabase/index.jsp   ，java bug数据库，通过bug号（例如 4252539），来查询。

### 3.2 注释

### 3.3 数据类型

8中基本类型，其中4种整型：`int`，`short`，`long`，`byte`；2种浮点类型：`float`，`double`；表示Unicode编码的`char`；`boolean`。

#### 整型

在Java中，整型的范围与运行的机器无关；没有无符号。



`int`      4字节；

`short`    2字节；

`long`   8字节；

`byte`    1字节；

```java
400000000000L;
0xCAFE;
010;
0b1001;   
```

#### 浮点数

`float`

`double`

`NaN`

`Double.POSITIVE_INFINITY`

`Double.NEGATIVE_INFINITY`

#### char

有些Unicode字符可以用一个char值描述，另外一些Unicode字符则需要两个char值。

Unicode转义序列会在解析代码之前得到处理。

一定要当心注释中的\u。

强烈建议不要使用char类型。

### 3.4 变量

逐一声明每一个变量可以提高程序的可读性。

变量的声明尽可能地靠近变量第一次使用的地方。

#### 常量

关键字`final`指示常量。习惯上，常量名使用全大写。

### 3.5 运算符



### 3.6 字符串

从概念上讲，Java字符串就是**Unicode字符序列**。

#### 子串

`substring(0, 3)`

#### 拼接

`+`

`String.join()`  

#### 不可变字符串

`String`

不可变字符串的优点：编译器可以让字符串**共享**。

Java的设计者认为共享带来的高效率远远胜过于提取、拼接字符串所带来的低效率。

#### 检测字符串是否相等

`equals()`

`equalsIgnoreCase()`

`==`只能确定两个字符串是否放置在同一个位置上。

#### 空串与Null串

检查一个字符串既不是null也不为空串：

```java
if (str != null && str.length() != 0)
```



#### Code Points and Code Units??

`char()`和`length()`都是与**Code Units**相关的。

要想得到第i个Code Points，使用：

```java
int index = greeting.offsetByCodePoints(0, i);
int cp = greeting.codePointAt(index);
```



#### String API

`String`有50+有用的方法

chartAt

codePointAt



#### StringBuilder

### IO

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



### 5.1 超类和子类



### 5.2 Object：所有类的超类



#### 5.2.1 equals 方法？？

```java
Objects.equals
```



#### toString()



### 5.3  ArrayList



### 5.4 Object Wrappers and Autoboxing



### 5.5 参数数量可变的方法



### 5.6 枚举类



### 5.7 反射??





## 6.接口、lambda表达式与内部类



### 6.1 接口



## 7.异常、断言和日志



### 7.1 处理错误



### 7.5 记录日志



### 7.6 调试技巧



## 8.泛型程序设计







## 9.集合



### 9.1 Java集合框架



#### 接口与实现分离



#### Collection



#### Iterator

`Iterator`

`Iterable`   **for each**

`iterator.forEachRemaining()`



#### 泛型使用方法



`AbstractCollection`



#### 集合框架中的接口

![集合框架中的接口](/Users/andyron/myfield/github/LearnJava/images/java-021.jpg)

`SortedSet`  `SortedMap`

`NavigableSet`   `NagigableMap`

### 9.2 具体的集合

![](../images/java-022.jpg)

![集合框架中的类](../images/java-023.jpg)



#### 链表

`Linkedlist`



#### 数组列表



#### 散列集  HashSet



#### 树集  TreeSet



#### 队列与双端队列



#### 优先级队列



### 9.3 映射





### 9.6 遗留的集合

![](../images/java-024.jpg)

#### Hashtable



#### 枚举



#### Properties



#### Stack



#### 位集







## 13.部署Java应用程序

### 13.1 Jar文件



### 13.2 应用首选项的存储

`java.util.prefs.Preferences`



