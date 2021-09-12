java注解
-------------

可以把注解（Annotation）理解为**标签**🏷。标签是一张便利纸，标签上的内容可以自由定义。往抽象地说，标签并不一定是一张纸，它可以是对人和事物的属性评价。也就是说，标签具备对于抽象事物的解释。

**标签是对事物行为的某些角度的评价与解释。** **想像代码具有生命，注解就是对于代码中某些鲜活个体的贴上去的一张标签。**



### 注解的定义

注解同 `classs` 和 `interface` 一样，也属于一种类型，通过`@interface`关键字定义。

```java
public @interface TestAnnotation {
}
```

### 注解的使用

```java
@TestAnnotation
public class Test {
}
```

将 TestAnnotation 这张标签(注解)贴到 Test 这个类上面。

要想注解能够正常工作，还需要用到新的概念那就是**元注解**。

### 元注解

元注解是可以注解到注解上的注解，或者说元注解是一种基本注解，但是它能够应用到其它的注解上面。

元注解也可以理解为一张标签，但是它是一张特殊的标签，它的作用和目的就是<u>给其他普通的标签进行解释说明的。</u>

元标签有 `@Retention`、`@Documented`、`@Target`、`@Inherited`、`@Repeatable` 5 种。

#### @Retention

为保留期的意思。解释说明了注解的的**存活时间**。它的取值有三种：

- `RetentionPolicy.SOURCE` 	注解只在源码阶段保留，在编译器进行编译时它将被丢弃忽视。
- `RetentionPolicy.CLASS`        注解只被保留到编译进行的时候，它并不会被加载到 JVM 中。
- `RetentionPolicy.RUNTIME`    注解可以保留到程序运行的时候，它会被加载进入到 JVM 中，所以在程序运行时可以获取到它们。

```java
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {
}
```



#### @Documented

顾名思义，这个元注解肯定是和文档有关。它的作用是能够将注解中的元素包含到 Javadoc 中去。



#### @Target

@Target 指定了注解运用的地方。 当一个注解被 @Target 注解时，这个注解就被限定了**运用的场景**。

类比到标签，原本标签是你想张贴到哪个地方就到哪个地方，但是因为 @Target 的存在，它张贴的地方就非常具体了。@Target 的取值:

- `ElementType.ANNOTATION_TYPE`   可以给一个注解进行注解
- `ElementType.CONSTRUCTOR`   可以给构造方法进行注解
- `ElementType.FIELD`   可以给属性进行注解
- `ElementType.LOCAL_VARIABLE`   可以给局部变量进行注解
- `ElementType.METHOD`   可以给方法进行注解
- `ElementType.PACKAGE`   可以给一个包进行注解
- `ElementType.PARAMETER`   可以给一个方法内的参数进行注解
- `ElementType.TYPE`   可以给一个类型进行注解，比如类、接口、枚举



#### @Inherited

如果一个超类被 @Inherited 注解过的注解进行注解的话，那么如果它的子类没有被任何注解应用的话，那么这个子类就继承了超类的注解。

```java
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@interface Test {}

@Test
public class A {}

public class B extends A {}

```

类 B 也拥有 @Test 这个注解。



#### @Repeatable

可重复的意思。通常是注解的值可以同时取多个。

```java
@interface Persons {
    Person[]  value();
}

@Repeatable(Persons.class)
@interface Person{
    String role default "";
}

@Person(role="artist")
@Person(role="coder")
@Person(role="PM")
public class SuperMan{
}
```

@Repeatable 注解了 Person。而 @Repeatable 后面括号中的类相当于一个**容器注解**。容器注解就是用来存放其它注解的地方。

什么是容器注解呢？就是用来存放其它注解的地方。它本身也是一个注解。



### 注解的属性

注解的属性也叫做**成员变量**。注解只有成员变量，没有方法。注解的成员变量在注解的定义中以“<u>无形参的方法</u>”形式来声明，其方法名定义了该成员变量的名字，其返回值定义了该成员变量的类型。

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {
    int id();
    String msg();
}

```

定义了 TestAnnotation 这个注解中拥有 id 和 msg 两个属性。在使用的时候，应该给它们进行赋值。

赋值的方式是在注解的括号内以 `value= ` 形式，多个属性之前用`,`隔开:

```java
@TestAnnotation(id=3,msg="hello annotation")
public class Test {
}
```

**注：**在注解中定义属性时它的类型必须是8种基本数据类型外加类、接口、注解及它们的数组。

注解中属性可以有默认值，默认值需要用`default`关键值指定：

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {
    public int id() default -1;
    public String msg() default "Hi";
}
```

如果一个注解内仅仅只有一个名字为 value 的属性时，应用这个注解时可以直接接属性值填写到括号内：

```java
public @interface Check {
    String value();
}

@Check("hi")
int a;

```

如果一个注解没有任何属性，那么在应用这个注解的时候，括号都可以省略：

```java
public @interface Perform {}

@Perform
public void testMethod(){}
```



### Java预置的注解

#### @Deprecated

用来标记过时的元素，编译器在编译阶段遇到这个注解时会发出提醒警告，告诉开发者正在调用一个过时的元素比如过时的方法、过时的类、过时的成员变量。



#### @Override

提示子类要重写父类中被`@Override`修饰的方法。



#### @SuppressWarnings

阻止`@Deprecated`产生的警告。



#### @SafeVarargs

参数安全类型注解。它的目的是提醒开发者不要用参数做一些不安全的操作,它的存在会阻止编译器产生 unchecked 这样的警告。



#### @FunctionalInterface

函数式接口注解。



### 注解的提取

形象的比喻就是把注解标签在合适的时候撕下来，然后检阅上面的内容信息。

注解通过反射获取。

首先通过`Class`对象的`isAnnotationPresent()`方法判断它是否应用了某个注解；

然后通过`getAnnotation()`方法（或者`getAnnotations()`）来获取`Annotation`对象。

```java
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@TestAnnotation()
public class JavaJustTest {
    public static void main(String[] args) {

        boolean hasAnnotation = JavaJustTest.class.isAnnotationPresent(TestAnnotation.class);
        if (hasAnnotation) {
            TestAnnotation testAnnotation = JavaJustTest.class.getAnnotation(TestAnnotation.class);
            System.out.println("id= " + testAnnotation.id());
            System.out.println("msg= " + testAnnotation.msg());
        }

    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface TestAnnotation {
    int id() default -1;
    String msg() default  "Hi";
}
```

结果：

```
id= -1
msg= Hi
```

上面只获取了注解在类上的注解，其实属性、方法上的注解也是可以获取的。🔖



### 注解的使用场景

官方文档对注解的定义：

> 注解是一系列元数据，它提供数据用来解释程序代码，但是注解并非是所解释的代码本身的一部分。注解对于代码的运行效果没有直接影响。
>
> 注解有许多用处，主要如下：
>
> - 提供信息给编译器： 编译器可以利用注解来探测错误和警告信息
> - 编译阶段时的处理： 软件工具可以用来利用注解信息来生成代码、Html文档或者做其它相应处理。
> - 运行时的处理： 某些注解可以在程序运行的时候接受代码的提取值得注意的是，注解不是代码本身的一部分。

注解主要针对的是编译器和其它工具软件(SoftWare tool)。

当开发者使用了Annotation 修饰了类、方法、Field 等成员之后，这些 Annotation 不会自己生效，必须由开发者提供相应的代码来提取并处理 Annotation 信息。这些处理提取和处理 Annotation 的代码统称为 **APT**（Annotation Processing Tool，注解处理器)。



### 注解应用实例

🔖

### 参考

[java注解-最通俗易懂的讲解](https://blog.csdn.net/qq1404510094/article/details/80577555)

