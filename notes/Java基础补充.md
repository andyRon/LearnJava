Java基础补充
------------



https://www.bilibili.com/video/BV12J41137hu



编程语言：

编译型   compile 编译器 C/C++

解释型	解释器	



Java 两种结合 先预编译成字节码（.class）然后再解释



### 标识符 关键字

#### 标识符

Java中标识符是为变量名、类名、方法名或其他用户定义项所定义的名称。标识符的构成规则：

1. 标识符由26个英文字符大小写（`a~z`，`A~Z`）、数字(`0~9`)、下划线(_)和美元符号($)组成；
2. 不能以数字开头，不能是关键字；
3. 严格区分大小写；
4. 标识符的可以为任意长度；



#### 关键字

关键字（或者保留字）是对编译器有特殊意义的固定单词，不能在程序中做其他目的使用。关键字具有专门的意义和用途，和自定义的标识符不同，不能当作一般的标识符来使用。

保留字是为 Java 预留的关键字，它们虽然现在没有作为关键字，但在以后的升级版本中有可能作为关键字。

Java 语言目前定义了 51 个关键字，这些关键字不能作为名来使用。以下对这些关键字进行了分类。

1. 数据类型：boolean、int、long、short、byte、float、double、char、class、interface。
2. 流程控制：if、else、do、while、for、switch、case、default、break、continue、return、try、catch、finally。
3. 修饰符：public、protected、private、final、void、static、strict、abstract、transient、synchronized、volatile、native。
4. 动作：package、import、throw、throws、extends、implements、this、supper、instanceof、new。
5. 保留字：true、false、null、goto、const。

https://www.nowcoder.com/questionTerminal/f049440648ae4a1d83c2205bfa87289e



https://www.nowcoder.com/questionTerminal/dc5651a872ff44a187afd7034a1708ba





[1]Java标识符只能由数字、字母、下划线“_”或“$”符号以及Unicode字符集组成

[2]Java标识符必须以字母、下划线“_”或“$”符号以及Unicode字符集开头
[3]Java标识符不可以是Java关键字、保留字（const、goto）和字面量（true、false、null）
[4]Java标识符区分大小写，是大小写敏感的



### 数据类型

Java是**强类型语言**（要求变量的使用要严格符合规定，所有变量都必须先定义后才能使用）

弱类型语言

Java的数据类型分为两大类：

- 基本类型

  数值类型：整数类型（byte、short、int、long），浮点类型（float、double），字符类型（char）

  boolean类型（占1位）：true、false

- 引用类型

### 类型转换

由于Java是强类型语言，所以要进行有些运算的时候，需要类型转换。

```java
byte,short,char -> int -> long -> float -> double
```

运算中，不同类型的数据先转化为统一类型，然后进行运算。



强制类型转换：高->低   (类型)变量名

自动类型转换：低->高

> 1. 不能对布尔值进行转换
> 2. 不能把对象类型转换为不相干的类型
> 3. 转换的时候可能存在内存溢出，或者精度问题

### 数组

- 长度确定，一旦被创建大小不可以改变

- 元素必须是相同类型

- 数组中的元素可以使任何数据类型

- 数组变量是引用类型，数组也可以看出对象，数组中的每个元素相当于该对象的成员变量。

  Java中对象时在堆中的，因此数组无论保存原始类型还是其它对象类型，<font color=#FF8C00>数组对象本身在堆中的</font>。

数组工具类`java.util.Arrays`

#### 稀疏数组

一种数据结构

当一个数据中大部分元素为0，或者为同一个值的数组是，可以用稀疏数组哎保存该数组。

稀疏数组的处理方式：

- 记录数组一共有几行几列，有多少个不同值
- 把具有不同值的元素和行列及值记录在一个小规模的数组中，从而缩小程序的规模

![image-20210920164908333](/Users/andyron/Library/Application Support/typora-user-images/image-20210920164908333.png)

### 面向对象

#### 构造器

和类名相同，没有返回值。

使用new关键字，本质是在调用构造器。

一旦定义了有参构造，无参就必须显示定义。

#### 对象内存分析

https://www.bilibili.com/video/BV12J41137hu?p=65&spm_id_from=pageDriver

![image-20210920170350459](/Users/andyron/Library/Application Support/typora-user-images/image-20210920170350459.png)



#### 重写

1. 需要有继承关系，子类重写父类的方法！
2. 方法名必须相同
3. 参数列表必须相同
4. 修饰符范围可以扩大但不能缩小
5. 抛出异常范围可以被缩小但不能扩大

为什么需要重写：父类的功能，子类不一定需要，或者不一定满足。

#### 多态

一个对象的实际类型时确定的：

```java
new Student();
new Person();
```

可以指向的引用类型就不确定了，父类的引用可以指向子类：

```java
Student s1 = new Student();
Person s2 = new Student();
Object s3 = new Student();
```

对象能执行那些方法，主要看左边的类型，和右边关系不大！

> 多态注意：
>
> 1. 多态是方法的多态，属性没有多态
> 2. 存在条件：继承关系，方法重写，父类引用指向子类对象

**多态**即同一方法可以根据发送对象的不同而采用多种不同的行为方式。







### 集合总结

那些常用？

总分类体系

各自的用途

那些是线程安全的





