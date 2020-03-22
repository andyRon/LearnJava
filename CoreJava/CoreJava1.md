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

在Java中，整型的范围与运行的机器无关；没有无符号类型。



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

建议不要使用八进制常数，易混淆。

#### 浮点数

`float`    4字节；

`double`     8字节；

`NaN`

`Double.POSITIVE_INFINITY`

`Double.NEGATIVE_INFINITY`

#### char

有些Unicode字符可以用一个char值描述，另外一些Unicode字符则需要两个char值。

char类型的字面量值要用**单引号**括起来。`'A'`   `'\u03C0'`



**Unicode转义序列会在解析代码之前得到处理。**

一定要当心注释中的\u。

强烈建议不要使用char类型。

#### Unicode 和 char

**code point**（码点）是指与一个编码表中的某个字符对应的代码值。a code value that is associated with a character in an encoding scheme.

在 Unicode 标准中， 码点采用十六进制书写，并加上前缀U+, 例如**U+0041**就是拉丁字母A的码点。

UTF 16 编码采用不同长度的编码表示所有 Unicode 码点。在基本的多语言级别中， 每个字符用16位表示，通常被称为代码单元(**code unit**)。

### 3.4 变量

`Character.isJavaIdentifierPart()` `Character.isJavaIdentifierStart()`



逐一声明每一个变量可以提高程序的可读性。

变量的声明尽可能地靠近变量第一次使用的地方。

#### 常量

关键字`final`指示**常量**。习惯上，常量名使用全大写。

使用关键字`static final`设置**类常量**。 



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



#### Code Points and Code Units

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

### 3.7 IO

#### 读取输入

`Scanner`  

`console`来处理控制台输入

```java
Console cons = System.console();
String username = cons.readLine("User name: ");
char[] passwd = cons.readPassword("Password: ");
```



#### 格式化输出

`System.out.printf`

#### 文件输入与输出

`Paths`

`PrintWriter`

### 3.8 控制流程



### 3.9 大数值

`BigInteger`

`BigDecimal`

任意长度





### 3.10 数组??

```java
int[] a;

int[] a = new int[100];

int[] smallPrimes = {2, 3, 5};

new int[]{17, 19, 31};

smallPrimes = new int[]{17, 19, 31};
```



#### 命令行参数



#### 数组排序







## 4.对象与类



### 4.1 面向对象程序设计概述

#### 类

**封装**（encapsulation，也称**数据隐藏**）

**instance field**  是数据

**方法**（method）是操纵数据的过程

#### 对象

**对象的行为 (behavior )**     可以对对象施加哪些操作，或可以对对象施加哪些方法? 

**对象的状态 (state )**       当施加那些方法时， 对象如何响应?

**对象标识（identity）**   如何辨别具有相同行为与状态的不同对象?

对象状态的改变必须通过调用方法实现。

#### 识别类

识别类的简单规则是在**分析问题的过程中寻找名词，而方法对应着动词。

#### 类之间的关系

依赖（“users-a）

聚合（”has-a“）

继承（”is-a“）



### 4.2 使用预定义类



#### 对象与对象变量

一个对象变量并没有实际包含一个对象，而仅仅引用一个对象。

#### LocalDate



#### mutator method and accessor method



### 4.3 用户自定义类

在一个源文件中，只能有一个公有类，但可以有任意数目的非公有类。

构造器总是伴随着new操作符的执行被调用。



**final实例字段**



### 4.4 静态字段和静态方法

静态变量  

静态常量

静态方法

工厂方法

main方法



### 4.5 Method Parameters 

Java程序设计语言总是采用按值调用。



**方法得到的是对象引用的拷贝，对象引用以及其他的拷贝同时引用同一个对象。**



### 4.6 对象构造



#### 重载(overloading)

方法的签名（signature）

#### Default Field Initialization

#### 无参数的构造器



#### 调用另一个构造器

`this()`

#### 初始化块

首先运行初始化块， 然后才运行构造器的主体部分。

#### 对象析构与finalize方法

由于Java有自动的垃圾回收器，不需要人工回收内存，所以Java不支持析构器。



### 4.7 包



从编译器的角度来看，嵌套的包之间没有任何关系。

<font color=#FF8C00>**一个类可以使用所属包中的所有类，以及其他包中的公有类。**</font>



**静态导入**

`import static java.lang.System.*;`

不必加类名前缀，就可以使用System类的静态方法和静态字段。

将包中的文件放到与完整的包名匹配的子目录中。



### 4.8 类路径

类文件也可以存储在 JAR(Java 归档 )文件中。

JAR 文件使用 ZIP 格式组织文件和子目录。可以使用所有ZIP实用程序查看内部的rt.jar以及其他的JAR文件。

##### 设置类路径

`java -classpath /home/user/dassdir:.:/home/user/archives/archive.jar HyProg`

### 4.9 文档注释



#### 通用注释

`@see com.andyron.com.corejava.Employee#raiseSalary(double)`



#### 包与概述注释

包注释需要在每个包目录中添加一个单独的文件。



### 4.10 类设计技巧



## 5 继承



### 5.1 超类和子类

子类比超类拥有的功能更加丰富。

应该将通用的方法放在超类中，将具有特殊用途的方法放在子类中。



super不是一个对象的引用，不能将其赋给另一个对象变量，它只是一个指示编译器调用超类方法的**特殊关键字**。



#### 理解方法调用??



#### 阻止继承：final类和方法



#### 强制类型转换

`ClassCastException`

`instanceof`

#### 抽象类

#### 受保护访问

`protected`







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



### 5.7 反射

**反射库**(reflection library，`java.lang.reflect.*`) 提供了一个非常丰富且精心设计的工具集， 以便编写能够动态操纵 Java 代码的程序。

能够分析类能力的程序称为**反射**(reflective )。 

反射主要使用人员是**工具构造者**，它的用途:

- 在运行时分析类的能力。

- 在运行时查看对象， 例如， 编写一个 toString 方法供所有类使用。

- 实现通用的数组操作代码。

- 利用 Method 对象， 这个对象很像中的函数指针。

#### Class类

Java运行时系统始终为所有的对象维护一个被称为**运行时的类型标识**。 这个信息跟踪着每个对象所属的类。 **虚拟机利用运行时类型信息选择相应的方法执行**。保存这些信息的类被称为`Class`。

三种获得Class类对象的方法：

```java
Random generator = new Random0:
Class cl = generator.getClass();
String name = cl.getName(); // name is set to "java.util .Random"


String dassName = "java.util .Random";
Class cl = Class.forName(dassName);


Class dl = Random.class; // if you import java.util
Gass cl2 = int.class;
Class cl3 = Double[].class;

```



Class类实际上是泛型类。例如，`Employee.class`的类型是`Class<Employee>`。

另外一创建Class类实例的方法：

```java
String s = "java.util.Random";
Object m = Class.forName(s).newlnstance();
```



#### 捕获异常

#### 利用反射分析类

`java.lang.reflect.*`

`Field`

`Method`

`Constructor`

`Modifier`   

`.getName()`

`.getType()`

...



#### 在运行时使用反射分析对象



#### 使用反射编写泛型数组代码



#### 调用任意方法



## 6.接口、lambda表达式与内部类



### 6.1 接口

接口不是类，而是对类的一组需求描述。

**接口中所有方法自动地属于public**。

接口绝不能含有实例属性。可以将接口看成是没有实例属性的抽象类。

#### 接口的特性

尽管不能构造接口的对象， 却能声明接口的变量；接口变量必须弓I用实现了接口的类对象:

```java
Comparable x;
x= new Employee(...) // Employee 实现了 Comparable
```

检测一个对象是否实现了某个接口：

```java
if (anObj instanceof Comparable) { ... }
```

接口可以建立像类一样的继承关系，从而**扩展**接口；接口中可以包含常量：

```java
public interface Moveable {
  void move(double x, double y);
}

public interface Powered extends Moveable {
  double milesPerGallon();
  double SPEED_LIMIT = 95;  // 省略了 public static final 
}
```

**接口中的方法和常量不需要加任何修饰符号。**

#### 接口与抽象类

接口可以提供多重继承的大多数好处， 同时还能避免多重继承的复杂性和低效性。

#### 静态方法

标准库中，成对的接口和实用工具类，如Collection/Collections、Path/Paths。

#### 默认方法

可以为接口方法提供一个默认实现。需要`default`修饰符。

#### 解决默认方法冲突



### 6.2 接口实例

#### 接口与回调

#### Comparator接口



#### 对象克隆

clone方法是 Object 的一个 protected 方法。

**浅拷贝**：没有克隆对象中引用的其他对象。默认拷贝是浅拷贝，还会共享信息。

![浅拷贝](../images/java-032.jpg)

`Cloneable`   

重写clone方法实现深拷贝。

### 6.3 lambda表达式

#### 为什么引入lambda表达式



#### lambda表达式的语法

lambda表达式就是**一个代码块， 以及必须传人 代码的变量规范**。

```java
(参数) -> { 表达式 }
```

```java
(String first, String second) -> { first.lengthO - second.lengthO }
```



#### 函数式接口

对于只有一个抽象方法的接口， 需要这种接口的对象时， 就可以提供一个 lambda 表达 式。 这种接口称为**函数式接口 (functional interface )**。

最好把 lambda 表达式看作是一 个函数， 而不是一个对象。

`Comparator `

`Predicate`  专门用来传递 lambda 表达式。

#### 方法引用
```java
Timer t = new Timer(1000, System.out::println);
```

表达式 `System.out::println` 是一个**方法引用(method reference)**, 它等价于 lambda 表达式 `x 一> System.out.println(x)`。

```java
Timer t = new Timer(1000, event -> System.out.println(event));
```

用:: 操作符分隔方法名与对象或类名有三种方式：


```java
object::instanceMethod
```

```java
Class::staticMethod
Math::pow 等价于 (x，y) -> Math.pow(x, y)
```

```java
Class::instnaceMethod
String::compareToIgnoreCase 等同于 (x, y) -> x.compareToIgnoreCase(y)
```

#### 构造器引用

`Person::new`

#### 变量作用域



#### 处理lambda表达式??



#### 再谈Comparator

```java
Arrays.sort(people, Comparator.comparing(Person::getName))
  
Arrays.sort(people, Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName));

Array.sort(people, Comparator.comparing(Person::getName, (s, t) -> Integer.compare(s.length(), t.length())));
```



### 6.4 内部类(inner class)??

使用内部类的原因：

- 内部类方法可以访问该类定义所在的作用域中的数据， 包括私有的数据。 
- 内部类可以对同一个包中的其他类隐藏起来。 
- 当想要定义一个回调函数且不想编写大量代码时，使用匿名(anonymous) 内部类比较便捷。



内部类既可以访问自身的数据域， 也 可以访问创建它的外围类对象的数据域。

#### 内部类的特殊语法规则



#### 内部类是否有用、必要和安全

#### 局部内部类

在某个代码块中。

#### 由外部方法访问变量

#### 匿名内部类



#### 静态内部类



### 6.5 代理

#### 何时使用代理

#### 创建代理对象

#### 代理类的特性




## 7.异常、断言和日志

向用户通告错误;

保存所有的工作结果; 

允许用户以妥善的形式退出程序。



### 7.1 处理错误

\1. 用户输入错误

\2. 设备错误

\3. 物理限制

\4. 代码错误

在 Java 中， 如果某个方法不能够采用正常的途径完整它的任务， 就可以通过另外一个路径**退出**方法。 在这种情况下， 方法并**不返回任何值**， 而是抛出 (throw) 一个封装了错误信息的对象。 异常处理机制开始搜索能够处理这种异常状况的**异常处理器** (exception handler )。

#### 异常分类

![](../images/java-028.jpg)





### 7.2 捕获异常

如果某个异常发生的时候没有在任何地方进行捕获， 那程序就会终止执行， 并在控制台上打印出异常信息， 其中包括异常的类型和堆栈的内容。

#### finally子句

不管是否有异常被捕获， finally 子句中的代码都被执行。 

#### 带资源的 try 语句

```java
try (Resource res = ...) {
  work with res
}
```



#### 分析堆栈轨迹元素

### 7.3 使用异常机制的技巧



### 7.4 使用断言



### 7.5 记录日志



### 7.6 调试技巧



## 8.泛型程序设计



### 8.1 为什么要使用泛型程序设计

泛型程序设计(Generic programming) 





### 8.9 反射和泛型？？



## 9.集合



### 9.1 Java集合框架



#### 将集合的接口与实现分离

以队列为例子看是如何分离的。

队列接口：

```java
public interface Queue<E> { // a simplified form of the interface in the standard library
void add(E element); E remove();
int size();
}  
```

队列通常有两种实现方式：循环数组；链表。

```java
public class Ci cularAr ayQueue<E> implements Queue<E> { // not an actual library class
  private int head;
  private int tail;
  CircularArrayQueue(int capacity) { . . . } 
  public void add(E element) { . . . }
  public E remove0{ . . . }
  public int size() { . . . }
  private EQ elements;
}
```

```java
public class LinkedListQueue<E> iipleients Queue<E> { // not an actual library class
  private Link head; 
  private Link tail;
  
  LinkedListQueueO { . . . } 
  public void add(Eelement) {...} 
  public E remove() { ...}
  public int size() { . . . }
}  
```

使用：

```java
Queue<Customer> expresslane = new CircularArrayQueue<>(100); 
expessLane.add(new Customer("Harry"));
```

一旦改变了想法， 可以轻松地使用另外一种不同的实现：

```java
Queue<Custoaer> expressLane = new LinkedListQueue<>(); expressLane.add(new Custonier("Harry"));
```



#### Collection接口

基本接口 `Collection`

```java
public interface Collection<E> {
	boolean add(E element); 
  Iterator<E> iterator();
  ...
}
```



#### 迭代器 Iterator

```java
public interface Iterator<E> {
  E next();
  boolean hasNext();
  void remove();
  default void forEachRemaining(Consumer<? super E> action);
}
```



`Iterator`

`Iterable`   **for each**

`iterator.forEachRemaining()`



#### 泛型使用方法

由于 Collection 与 Iterator 都是泛型接口， 可以编写操作任何集合类型的实用方法。

```java
int sizeO
boolean isEmptyO
boolean contains(Object obj)
boolean containsAl1(Col1ection<?> c)
boolean equals(Object other)
boolean addAll (Collection<? extends E> from) 
boolean remove(Object obj)
boolean removeAl1(Col1ection<?> c)
void clear()
boolean retainAl1(Col1ection<?> c)
Object口 toArray()
<T> T[] toArray(T[] arrayToFill)
...
```

`AbstractCollection`



#### 集合框架中的接口

![集合框架中的接口](../images/java-021.jpg)

两个基本接口 `Collection`  `Map`

`List` 是有序集合。

`SortedSet`  `SortedMap`

`NavigableSet`   `NagigableMap`

### 9.2 具体的集合

![](../images/java-022.jpg)

集合框架中的类：

![集合框架中的类](../images/java-023.jpg)



#### 链表 Linkedlist

`Linkedlist`

在 Java 程序设计语言中， 所有链表实际上都是**双向链接**的。

```java
// 链表中删除操作
List<String> staff = new LinkedList<>(); // LinkedList implements List staff.add("Amy") ;
staff.add("Amy");
staff.add("BobH");
staff.add("Carl");
Iterator iter = staff.iterator();
String first = iter.next(); // visit first element
String second = iter.next(); // visit second element
iter.remove();  // remove last visited element "
```

`LinkedList.add`方法将对象添加到链表的尾部。

但是， 常常需要将元素添加到链表的中间。由于迭代器是描述**集合中位置**的， 所以这种依赖于位置的add方法将由迭代器负责（add方法定义在子接口ListIterator中而不是Iterator中）：

```java
interface ListIterator<E> extends Iterator<E> {
	void add(E element);
  ...
} 
```

例子：

```java
List<String> staff = new LinkedList<>(); 
staff.add("Amy");
staff.add("Bob");
staff.add("Carl") ;
ListIterator<String> iter = staff.listlterator(); iter.next();// skip past first element 
iter.add("Juliet") ;
```

![将一个元素添加到链表中](../images/java-033.jpg)



#### 数组列表 ArrayList

动态数组

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




```

```





## 14.并发



### 14.1 什么是线程