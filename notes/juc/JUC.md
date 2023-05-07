尚硅谷JUC
----
https://www.bilibili.com/video/BV1ar4y1x727



[脑图](https://www.yuque.com/liuyanntes/vx9leh/fpy93i?inner=lwUQY)



## 2 线程基础知识复习

### 为什么学习并用好多线程及其重要

#### 硬件方面

摩尔定律失效 

摩尔定律：

它是由英特尔创始人之—Gordon Moore(戈登 •摩尔)提出来的。其内容为：

当价格不变时，集成电路上可容纳的元器件的数目约每隔18-24个月便会增加一倍，性能也将提升一倍。

换言之，每一美元所能买到的电脑性能，将每隔18-24个月翻一倍以上。这一定律揭示了信息技术进步的速度。

可是从2003年开始CPU主顿已经不再翻信，而是采用多核而不是更快的主频。

==摩尔定律失效了==

在主频不再提高且核数在不断增加的情况下，要想让程序更快就要用到**==并行或并发编程==**。

#### 软件方面

- 充分利用多核处理器
- 提高程序性能，高并发系统
- 提高程序吞吐量，异步+回调等生产需求

#### 弊端及问题

- 线程安全性问题

  i++

  集合类安全否

- 线程锁问题

- 线程性能问题



### 从start一个线程说起



#### 涉及OpenJDK源码

[openjdk8/jdk/src/share/native/java/lang/thread.c](https://hg.openjdk.org/jdk8u/jdk8u/jdk/file/7fcf35286d52/src/share/native/java/lang/Thread.c)

[openjdk8/hotspot/src/share/vm/prims/jvm.cpp](https://hg.openjdk.org/jdk8u/jdk8u/hotspot/file/69087d08d473/src/share/vm/prims/jvm.cpp)

[openjdk8/hotspot/src/share/vm/runtime/thread.cpp](https://hg.openjdk.org/jdk8u/jdk8u/hotspot/file/69087d08d473/src/share/vm/runtime/thread.cpp)

java线程是通过start的万法启动执行的，主要内容在native方法`start0()`，openjdk的与JNI一般是一一对应的，Thread.java对应的就是Thread.c，`start0`其实就是JVM_StartThread。此时查看源代码可以看到在jvm.h中找到了声明，jvm.cpp中有实现。

```C
static JNINativeMethod methods[] = {
    {"start0",           "()V",        (void *)&JVM_StartThread},
    {"stop0",            "(" OBJ ")V", (void *)&JVM_StopThread},
    {"isAlive",          "()Z",        (void *)&JVM_IsThreadAlive},
    {"suspend0",         "()V",        (void *)&JVM_SuspendThread},
    {"resume0",          "()V",        (void *)&JVM_ResumeThread},
    {"setPriority0",     "(I)V",       (void *)&JVM_SetThreadPriority},
    {"yield",            "()V",        (void *)&JVM_Yield},
    {"sleep",            "(J)V",       (void *)&JVM_Sleep},
    {"currentThread",    "()" THD,     (void *)&JVM_CurrentThread},
    {"countStackFrames", "()I",        (void *)&JVM_CountStackFrames},
    {"interrupt0",       "()V",        (void *)&JVM_Interrupt},
    {"isInterrupted",    "(Z)Z",       (void *)&JVM_IsInterrupted},
    {"holdsLock",        "(" OBJ ")Z", (void *)&JVM_HoldsLock},
    {"getThreads",        "()[" THD,   (void *)&JVM_GetAllThreads},
    {"dumpThreads",      "([" THD ")[[" STE, (void *)&JVM_DumpThreads},
    {"setNativeName",    "(" STR ")V", (void *)&JVM_SetNativeThreadName},
};
```

![](images/image-20230507101021377.png)

![](images/image-20230507101130795.png)

### Java多线程相关概念

#### 1把锁

synchronized

#### 2个并

##### 并发concurrent

抢票、秒杀

- 实在同一实体上的多个事件
- 是在一台处理器上”同时“处理多个任务
- 同一个时刻，其实是只有一个事件在发生

##### 并行parallel

- 是在不同实体上的多个事件
- 是在多态处理器上同时处理多个任务
- 同一个时刻大家真的都在做事情，你做你的，我做我的

![](images/image-20230507101909667.png)

#### 3个程

##### 进程

简单的说，在系统中运行的一个应用程序就是一个进程，每一个进程都有它自己的内存空间和系统资源。

##### 线程

也被称为==轻量级进程==，在同一个进程内会有1个或多个线程，是大多数操作系统进行时序调度的基本单元。

##### 管程

Monitor（监视器），也就是我们平时所说的锁

Monitor其实是一种同步机制，他的义务是保证（同一时间）只有一个线程可以访问被保护的数据和代码。

JVM中同步是基于进入和退出监视器对象(Monitor,==管程对象==)来实现的，每个对象实例都会有一个Monitor对象：

```java
Object o = new Object();
new Thread(() -> {
  synchronized (o) {

  }
}, "t1").start();
```

Monitor对象会和Java对象一同创建并销毁，它底层是由C++语言来实现的。

![](images/image-20230507103831189.png)

### 用户线程和守护线程

一般情况下不做特别说明配置，==默认都是用户线程（User Thread）==，它是系统的工作线程，它会完成这个程序需要完成的业务操作。

==守护线程（Daemon Thread）==是一种特殊的线程==为其它线程服务的==，在后台默默地完成一些系统性的服务，比如**垃圾回收线程**就是最典型的例子。

守护线程作为一个服务线程，没有服务对象就没有必要继续运行了，如果用户线程全部结束了，意味着程序需耍完成的业务操作包经结束了，系统可以退出了。所以假如当系统只剩下守护线程的时候，java虚拟机会自动退出。

`setDaemon(true)`必须在`start()`之前设置，否则`IllegalThreadStateException`异常。

## 3 CompletableFuture