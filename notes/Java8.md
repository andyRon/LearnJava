Java8新特性

----------

https://www.runoob.com/java/java8-new-features.html



### Lambda 表达式

Lambda 允许把函数作为一个方法的参数（函数作为参数传递到方法中）。

语法：

```java
(parameters) -> expression
或
(parameters) ->{ statements; }
```

lambda表达式的特征:

- **可选类型声明：**不需要声明参数类型，编译器可以统一识别参数值。
- **可选的参数圆括号：**一个参数无需定义圆括号，但多个参数需要定义圆括号。
- **可选的大括号：**如果主体包含了一个语句，就不需要使用大括号。
- **可选的返回关键字：**如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定表达式返回了一个数值。



例子：

```java
// 1. 不需要参数,返回值为 5  
() -> 5  
  
// 2. 接收一个参数(数字类型),返回其2倍的值  
x -> 2 * x  
  
// 3. 接受2个参数(数字),并返回他们的差值  
(x, y) -> x – y  
  
// 4. 接收2个int型整数,返回他们的和  
(int x, int y) -> x + y  
  
// 5. 接受一个 string 对象,并在控制台打印,不返回任何值(看起来像是返回void)  
(String s) -> System.out.print(s)
```





### 方法引用

方法引用通过方法的名字来指向一个方法；可以使语言的构造更紧凑简洁，减少冗余代码。

方法引用使用一对冒号 `::` 。

4 种不同方法的引用：

```java
package com.runoob.main;
 
@FunctionalInterface
public interface Supplier<T> {
    T get();
}
 
class Car {
    //Supplier是jdk1.8的接口，这里和lamda一起使用了
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }
 
    public static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }
 
    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }
 
    public void repair() {
        System.out.println("Repaired " + this.toString());
    }
}
```

1. **构造器引用：**语法是`Class::new`，或者更一般的`Class< T >::new`。

```java
final Car car = Car.create( Car::new );
final List<Car> cars = Arrays.asList(car);
```

2. **静态方法引用：**语法是`Class::static_method`。

```java
cars.forEach(Car::collide);
```

3. **特定类的任意对象的方法引用：**语法是`Class::method`。

```java
cars.forEach(Car::repair);
```

4. **特定对象的方法引用：**语法是`instance::method`。

```java
final Car police = Car.create(Car::new);
cars.forEach(police::follow);
```



### 函数式接口

函数式接口(Functional Interface)就是一个有且仅有一个抽象方法，但是可以有多个非抽象方法的接口。

函数式接口可以被隐式转换为 lambda 表达式。



### 默认方法

简单说，默认方法就是接口可以有实现方法，而且不需要实现类去实现其方法。`default`





### Stream API

新添加的Stream API（java.util.stream） 把真正的函数式编程风格引入到Java中。



### Date Time API

加强对日期与时间的处理。



### Optional 类

用来解决空指针异常。



### Base64