《Java核心技术卷二第10版》笔记

----------------

## 1 Java SE 8的流库

### 1.1 从迭代到流

`java.util.Collection`的  `stream()`，`parallelStream()`

流遵循了“**做什么而非怎么做**”的原则。

流与集合的差异：

1. 流并不存储其元素。
2. 流的操作不会修改其数据源。
3. 流的操作是尽可能**惰性执行**的。

### 1.2 流的创建

`Stream.of()`

`Array.stream(array, from, to)`

`Stream.empty()`

`Stream.generate()`

`Stream.iterate()`





### 1.3 filter, map, flatMap



## 2 输入与输出

![输入流与输出流的层次结构](/Users/andyron/myfield/github/LearnJava/images/java-029.jpg)



![Reader和Writer的层次结构](/Users/andyron/myfield/github/LearnJava/images/java-030.jpg)





![Closeable,Flushable,Readable,Appendable接口](/Users/andyron/myfield/github/LearnJava/images/java-031.jpg)



### 2.2 文本输入与输出





### 2.3 读写二进制数据

#### 

#### ZIP文档



### 2.4 序列化



### 2.5 操作文件



### 2.6 内存映射文件



### 2.7 正则表达式











## 4 网络



### 4.1 连接到服务器

`telnet time-a.nist.gov 13`



### 4.2 实现服务器



### 4.3 可中断套接字



### 4.4 获取Web数



### 4.5 发送Email



## 5 数据库编程

### 5.1 JDBC的设计



### 5.2 结构化查询语言



## 6 日期和时间API









## 8 脚本、编译与注解处理





### 8.2 编译器API



### 8.3 使用注解



### 8.4 注解语法

注解是注解接口来定义的：

```
mofiers @interface AnnotationName {
	elementDeclaration1
	elementDeclaration1
}
```

### 

### 8.5 标准注解



### 8.6 源码级注解处理



### 8.7 字节码工程