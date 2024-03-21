JUC并发编程与源码分析
----
尚硅谷 [JUC并发编程与源码分析](https://www.bilibili.com/video/BV1ar4y1x727)



[脑图](https://www.yuque.com/liuyanntes/vx9leh/fpy93i?inner=lwUQY)

https://tangzhi.blog.csdn.net/article/details/109210095



Doug Lea JUC作者

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

java线程是通过start的方法法启动执行的，主要内容在native方法`start0()`，openjdk的与JNI一般是一一对应的，Thread.java对应的就是Thread.c，`start0`其实就是JVM_StartThread。此时查看源代码可以看到在jvm.h中找到了声明，jvm.cpp中有实现。

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

`os::start_thread(thread);`表示多线程是操作系统范畴，与语言无关。

### Java多线程相关概念

#### 1把锁

`synchronized`

#### 2个并

##### 并发concurrent

抢票、秒杀

- 是在同一实体上的多个事件
- 是在一台处理器上”同时“处理多个任务
- 同一个时刻，其实是只有一个事件在发生

##### 并行parallel

- 是在不同实体上的多个事件
- 是在多台处理器上同时处理多个任务
- 同一个时刻大家真的都在做事情，你做你的，我做我的

泡方便面：撕调料包和烧热水同时在做

![](images/image-20230507101909667.png)

#### 3个程

##### 进程

简单的说，在系统中运行的一个应用程序就是一个进程，每一个进程都有它自己的**内存空间和系统资源**。

##### 线程

也被称为==轻量级进程==，在同一个进程内会有1个或多个线程，是大多数操作系统进行时序调度的**基本单元**。

> 进程和线程的概念都是来源于操作系统，和语言关系不大。

##### 管程

Monitor（监视器），也就是我们平时所说的==锁==。

Monitor其实是一种==同步机制==，他的义务是保证（同一时间）只有一个线程可以访问被保护的数据和代码。

JVM中同步是基于进入和退出监视器对象(Monitor,==管程对象==)来实现的，每个对象实例都会有一个Monitor对象：

```java
Object o = new Object();
new Thread(() -> {
  synchronized (o) {

  }
}, "t1").start();
```

Monitor对象会和Java对象一同创建并销毁，它底层是由C++语言来实现的。

《深入理解Java虚拟机（第3版）》：

![](images/image-20230507103831189.png)

### 用户线程和守护线程

Java中线程分两种：用户线程和守护线程。

一般情况下不做特别说明配置，==默认都是用户线程（User Thread）==（就是用户自己new出的线程，mian主线程也是），它是系统的工作线程，它会完成这个程序需要完成的业务操作。

==守护线程（Daemon Thread）==（用户线程的影子）是一种特殊的线程==为其它线程服务的==，在后台默默地完成一些系统性的服务，比如**垃圾回收线程**就是最典型的例子。

守护线程作为一个服务线程，没有服务对象就没有必要继续运行了，如果用户线程全部结束了，意味着程序需耍完成的业务操作包经结束了，系统可以退出了。所以假如当系统只剩下守护线程的时候，java虚拟机会自动退出。

`setDaemon(true)`必须在`start()`之前设置，否则`IllegalThreadStateException`异常。

`isDaemon()`判断是否是守护线程。

## 3 CompletableFuture

### 3.1 Future

#### Future接口理论知识

`Future`接口（俗称异步任务接口）(`FutureTask`实现类)定义了操作==异步任务==执行一些方法，如**获取异步任务的执行结果、取消任务的妆行、判断任务是否被取消、判断任务执行是否完毕**等。

```java
public interface Future<V> {
    boolean cancel(boolean mayInterruptIfRunning);
    boolean isCancelled();
    boolean isDone();
    V get() throws InterruptedException, ExecutionException;
    V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException;
}
```

比如主线程让一个子线程去执行任务，子线程可能比较耗时，启动子线程开始执行任务后，主线程就去做其他事情了，忙其它事情或者先执行完，过了一会才去获取子任务的执行结果或变更的任务状态。

案例说明：上课买水

![](images/image-20231029075730451.png)

总结：Future接口可以为主线程开一个分支任务，专门为主线程处理耗时和费力的复杂业务。



Future接口能干什么？

Future是Java5新加的一个接口，它提供了一种==异步并行计算==的功能。

如果主线程需要执行一个很耗时的计算任务，我们就可以通过future把这个任务放到异步线程中执行。

主线程继续处理其他任务或者先行结束，再通过Future获取**计算结果**。



代码说话：

`Runnable`接口

`Callable`接口

`Future`接口和`FutureTask`实现类

目的：异步多线程任务执行且返回有结果，三个特点：==多线程/有返回/异步任务==〔班长为老师去买水作为新启动的异步多线程任务且买到水有结果返回）

> Runnable与Callable区别：是否有返回值，是否抛出异常。
>
> ```java
> class MyThread implements Runnable {
> 
>     @Override
>     public void run() {
> 
>     }
> }
> 
> class MyThread2 implements Callable<String> {
> 
>     @Override
>     public String call() throws Exception {
>         return null;
>     }
> }
> ```

问题：Thread构造方法中只有 `Runnable`（`public Thread(Runnable target, String name)`），不能满足**有返回/异步任务** 的需求。

解决过程：`Runnable`有子接口`RunnableFuture`，其有实现类`FutureTask`，这个实现类又能通过构造器注入`Callable`。

```java
FutureTask(Callable<V> callable)
```



#### FutureTask

Future接口常用实现类FutureTask

![](images/image-20231029083118175.png)

`FutureTask`就解决上面三个需求

```java
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new MyThread());

        Thread t1 = new Thread(futureTask, "t1");
        t1.start();

        // 获取返回
        System.out.println(futureTask.get());
    }
}
class MyThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("---- come in call()");
        return "hello Callable";
    }
}
```

```
---- come in call()
hello Callable
```



#### Future编码实战和优缺点分析

##### 优点

future+线程池异步多线程任务配合，能显著提高程序的执行效率。

P11 🔖

```java
public class FutureThreadDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 3个任务，目前开启多个异步任务线程来处理，请问耗时多少？ 如果没有需要返回结果约340ms，需要返回结构约840ms+

        // 使用有3个线程的线程池，能够复用。减少使用new来创建
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        long startTime = System.currentTimeMillis();

        FutureTask<String> futureTask1 = new FutureTask<>(() -> {
            try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
            return "task1 over";
        });
        threadPool.submit(futureTask1);

        FutureTask<String> futureTask2 = new FutureTask<>(() -> {
            try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
            return "task2 over";
        });
        threadPool.submit(futureTask2);

        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());

        // main 线程
        try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }

        long endTime = System.currentTimeMillis();
        System.out.println("----costTime: " + (endTime - startTime) + "毫秒");
        System.out.println(Thread.currentThread().getName() + "\t ----end");

        threadPool.shutdown();
    }

    private static void m1() {
        // 3个任务，目前只有一个线程main来处理，请问耗时多少？ 越1100ms+
        long startTime = System.currentTimeMillis();

        try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
        try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
        try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }

        long endTime = System.currentTimeMillis();
        System.out.println("----costTime: " + (endTime - startTime) + "毫秒");

        System.out.println(Thread.currentThread().getName() + "\t ----end");
    }
}
```



##### 缺点

1. get()容易阻塞

一旦调用get方法求结果，如果计算没有完成容易导致程序阻塞。

```java
FutureTask<String> futureTask = new FutureTask<>(() -> {
  System.out.println(Thread.currentThread().getName() + "\t ----come in");
  // 暂停几秒钟线程
  try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
  return "task over";
});
Thread t1 = new Thread(futureTask, "t1");
t1.start();

// 1 不见不散，get非要等到结构才会离开，不管你是否计算完成，容易引起程序堵塞。一般会将其放到程序最后
//        System.out.println(futureTask.get());

System.out.println(Thread.currentThread().getName() + "\t ----忙其它任务了");

// 2 假如我不愿意等待，可以设置离开时间。会抛出TimeoutException，也不是很优雅
System.out.println(futureTask.get(3, TimeUnit.SECONDS));
```



2. isDonw轮询

轮询的方式会耗费无谓的CPU资源，而且也不见得能及时地得到计算结果。

```java
while (true) {
  if (futureTask.isDone()) {
    System.out.println(futureTask.get());
    break;
  } else {
    // 暂停毫秒
    try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
    System.out.println("正在处理中，不要再催了，越催越慢，再催熄火");
  }
}
```



结论：**Future对于结果的获取不是很友好，只能通过阻塞或轮询的方式得到任务的结果**。



##### 想完成一些复杂的任务

- 对于简单的业务场景使用Future完全OK

- 回调通知

- 创建异步任务

- 多个任务前后依赖可以组合处理（水煮鱼）

  - 想将多个异步任务的计算结果组合起来，后一个异步任务的计算结果需要前一个异步任务的值
  - 将两个或多个异步计算合成一个异步计算，这几个异步计算互相独立，同时后面这个又依赖前一个处理的结果。

- 对计算速度选最快

  - 当Future集合某个任务最快结束时，返回结果，返回第一名处理结果

  

由于Future的缺点，以及想要更多的复杂任务，催生出了CompletableFuture。



### 3.2 CompletableFuture

CompletableFuture是对Future的改进

#### CompletableFuture为什么出现

get()方法在Future 计算完成之前会一直处在阻塞状态下，isDone()方法容易耗费CPU资源。

对于真正的异步处理我们希望是可以通过传入回调函数，在Future结束时自动调用该回调两数，这样，我们就不用等待结果。



**阻塞的方式和异步编程的设计理念相违背，而轮询的方式会耗费无调的CPU资源**。因此，JDK8设计出CompletableFuture。

CompletableFuture提供了一种**观察者模式类似的机制**，可以让任务执行完成后通知监听的一方，



```java
public class CompletableFuture<T> implements Future<T>, CompletionStage<T>
```



CompletableFuture

- ﻿在Java8中，CompletableFuture提供了非常强大的Future的扩展功能，可以帮助我们简化异步编程的复杂性，并且提供了函数式编程的能力，可以通过回调的方式处理计算结果，也提供了转换和组合 CompletableFuture 的方法。
- ﻿它可能代表一个明确完成的Future，也有可能代表一个完成阶段( Completionstage），它支持在计算完成以后触发一些函数或执行某些动作。
- ﻿它实现了Future和Completionstage接口



CompletionStage

- ﻿`CompletionStage`代表异步计算过程中的某一个阶段，一个阶段完成以后可能会触发另外一个阶段，有些类似Linux系统的管道分隔符传参数。
- ﻿一个阶段的计算执行可以是一个Function, Consumer或者Runnable。比如：`stage.thenApply(x -> square(x)).thenAccept(x -> System.out.print(x)).thenRun(() -> System.out.println())`
- ﻿一个阶段的执行可能是被单个阶段的完成触发，也可能是由多个阶段一起触发



#### 核心的四个静态方法，来创建一个异步任务

不推荐使用new空参构造方法来获取CompletableFuture,推荐使用两组四个静态方法创建：

- runAsync（无返回值）

  ```java
  public static CompletableFuture<Void> runAsync(Runnable runnable)
  public static CompletableFuture<Void> runAsync(Runnable runnable,Executor executor)
  ```

- supplyAsync（有返回值）

  ```java
  public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
  public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)
  ```

没有指定Executor（线程池）的方法，直接使用默认的`ForkJoinPool.commonPool()`作为它的线程池执行异步代码；如果指定线程池，则使用自定义或者特别指定的线程池执行异步代码。



> Supplier  供给函数式接口🔖

```java
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
//            System.out.println(Thread.currentThread().getName());
//            // 暂停几秒钟线程
//            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
//        });

//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
//            System.out.println(Thread.currentThread().getName());
//            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
//        }, threadPool);

//        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
//            System.out.println(Thread.currentThread().getName());
//            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
//            return "hello supplyAsync";
//        });

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            return "hello supplyAsync";
        }, threadPool);

        System.out.println(completableFuture.get());

        threadPool.shutdown();
    }
```



##### 通用演示



从Java8开始引入了CompletableFuture，它是==Future的功能增强版，减少阻塞和轮询==，可以传入回调对象，当异步任务完成或者发生异常时，自动调用回调对象的回调方法。

🌰：CompletableFutureUseDemo



#### CompletableFuture的优点

1. 异步任务结束时，会自动回调某个对象的方法
2. 主线程设置好回调后，不再关心异步任务的执行，异步任务之间可以顺序执行
3. 异步任务出错是，会自动回调某个对象的方法



#### 案例-电商网站的比价需求

##### 函数式编程已成主流

Lambda表达式+Stream流式调用+Chain链式调用+Java8函数式编程

1. `Runnable` 没有参数没有返回值



2. `Function` 有一个参数有返回值



3. `Consumer`

`BiConsumer`



4. `Supplier`  供给型函数结构，有参数有返回值

![](images/image-20231124125153634.png)

链式写法

```java
@Accessors(chain = true)  // 支持链式写法
class Student {
}
```



##### join和get

区别是：join不会报检查异常

```java
public static void main(String[] args) {
  CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
    return "hello 1";
  });
  //        completableFuture.get();
  completableFuture.join();
}
```



> 切记，功能 -> 性能  （先完成，后完美）



##### 电商网站比价需求分析

1需求说明

- 1.1 同一款产品，同时搜索出同款产品在各大电商平台的售价；
- 1.2 同一款产品，同时搜索出本产品在同一个电商平台下，各个入驻卖家售价是多少

2 输出返回：
 出来结果希望是同款产品的在不同地方的价格清单列表，返回一个`List<String>`

《mysql》 in jd price is 88.05

《mysql》 in dangdang price is 86.11

《mysql》 in taobao price is 90.43

3 解决方案，比对同一个商品在各个平台上的价格，要求获得一个清单列表，

​	1 step by step，按部就班，查完京东查淘宝，查完淘宝查天猫•

​	2 all in ，万箭齐发，一口气多线程异步任务同时查询。。。。。



#### CompletableFuture常用方法

`CompletionStage`方法的实现



##### 1 获得结果和触发计算

获得结果：

```java
get()    不见不散
get(long timeout, TimeUnit unit)   过时不候
join()    不报检查异常
getNow(T valueIfAbsent)     立即获取结果不阻塞，计算完就返回计算完结果，没计算完就返回设定的valueIfAbsent值
```

触发计算：

```java
public boolean complete(T value)     是否打断get/join方法立即返回value
```



```java
// 返结果视情况出现两种情况：
//      true    completValue
//      false   abc
System.out.println(completableFuture.complete("completValue") + "\t" + completableFuture.join());
```





##### 2 对计算结果进行处理

🌰CompletableFutureAPI2Demo

上一步的计算结果可以传递给下一步

- `thenApply`  计算结果存储依赖关系，这连个线程串行化

异常处理：由于存在依赖关系（当前步错，不走下一步），当前步骤有异常的话就叫停

- `handle`

与thenApply功能类似，但对异常处理不同：有异常也可以往下走，根据带的异常参数可以进一步处理

> 总结：exceptionally有点类似try/catch，whenComplete和handle有点类似try/finally



##### 3 对计算结果进行消费

`thenAccept`   接受任务的处理结果，并消费处理，==无返回结果==



> 对比：
>
> - `thenRun(Runnable runnable)`，任务A执行完执行B，并且B不需要A的结果
> - `thenAccept(Consumer action)`，任务A执行完执行B，B需要A的结果，但任务B==无返回值==
> - `thenApply(Function fn)`，任务A执行完执行B，B需要A的结果，同时任务B==有返回值==
>
> ```java
> // 对比三种方式的不同
> System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenRun(() -> {}).join()); // null
> System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenAccept(r -> System.out.println(r)).join());
> System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenApply(r -> r + "resultB").join());
> 
> ```
>
> 



##### CompletableFuture和线程池说明

P26 

CompletableFutureWithThreadPollDemo



以`thenRun`和`thenRunAsync`为例，有什么区别？

1. ﻿没有传入自定义线程池，都用默认线程池ForkJoinPool；

2. ﻿传入了一个自定义线程池，

   如果你执行第一个任务的时候，传入了一个自定义线程池：

   调用thenRun方法执行第二个任务时，则第二个任务和第一个任务是共用同一个线程池。

   调用thenRunAsync执行第二个任务时，则第一个任务使用的是你自己传入的线程池，第二个任务使用的是ForkJoin线程池

3. 备注：有可能处理太快，系统优化切换原则，直接使用main线程处理

其它如：thenAccept和thenAcceptAsync,thenApply和thenApplyAsync等，它们之间的区别也是同理



源码分析

```java
public CompletableFuture<Void> thenRun(Runnable action) {
  return uniRunStage(null, action);
}

public CompletableFuture<Void> thenRunAsync(Runnable action) {
  return uniRunStage(asyncPool, action);
}

private static final Executor asyncPool = useCommonPool ? ForkJoinPool.commonPool() : new ThreadPerTaskExecutor();
```



##### 4 对计算速度进行选用

`applyToEither`  谁快用谁

```java
CompletableFuture<String> playA = CompletableFuture.supplyAsync(() -> {
  System.out.println("A come in");
  try {
    TimeUnit.SECONDS.sleep(2);
  } catch (InterruptedException e) {
    e.printStackTrace();
  }
  return "playA";
});
CompletableFuture<String> playB = CompletableFuture.supplyAsync(() -> {
  System.out.println("B come in");
  try {
    TimeUnit.SECONDS.sleep(10);
  } catch (InterruptedException e) {
    e.printStackTrace();
  }
  return "playB";
});
CompletableFuture<String> result = playA.applyToEither(playB, f -> {
  return f + " is winner";
});

System.out.println(Thread.currentThread().getName() + "\t-----: " + result.join());
```



##### 5 对计算结果进行合并

```java
public <U,V> CompletableFuture<V> thenCombine(
  CompletionStage<? extends U> other,
  BiFunction<? super T,? super U,? extends V> fn)
```



## 4 说说Java“锁”事❤️

> 大厂面试题
> 一、Synchronized相关问题
> 1.Synchronized 用过吗，其原理是什么?
> 2.你刚才提到获取对象的锁，这个“锁”到底是什么?如何确定对象的领?
> 3.什么是可重入性，为什么说Synchronized是可重入锁?
> 4.JVM对Java的原生锁做了哪些优化?
> 5.为什么说Synchronized是非公平锁?
> 6.什么是锁消除和锁粗化?
> 7.为什么说Synchronized是—个悲观领?乐观锁的实现原理又是什么?什么是CAS？
> 8.乐观锁—定就是好的吗?
> 9、synchronized实现原理，monitor对象什么时候生成的?知道monitor的monitorenter和monitorexit这两个是怎么保证同步的吗，或者说，这两个操作计算机底层是如何执行的
> 10.刚刚你提到了synchronized的优化过程，详细说一下吧。偏向锁和轻量级锁有什么区别?
>
> 二、可重入锁ReentrantLock及其他显式锁相关问题
> 1.跟Synchronized相比，可重入锁ReentrantLock 其续现原理有什么不同?
> 2那么请谈谈AQS框架是怎么回事儿?
> 3.请尽可能详尽地对比下Synchronized和 ReentrantLock的异同。
> 4.ReentrantLock是如何实现可重入性的?
>
> 三、其他
> 1.你怎么理解iava多线程的?怎么处理并发?线程池有那几个核心参数?你们项目中如何根据实际场景设置参数的?
> 2.Java加锁有哪几种锁？
> 3.简单说说lock ?
> 4.hashmap的实现原理? hash冲突怎么解决?为什么使用红黑树?
> 5.spring里面都使用了那些设计模式?循环依赖怎么解决?
> 6.项目中那个地方用了countdownlanch，怎么使用的?
> 7、从集合开始吧，介绍一下常用的集合类，哪些是有序的，哪些是无序的
> 8、hashmap是如何寻址的，哈希碰撞后是如何存储数据的，1.8后什么时候变成红黑树,红黑树有什么好处
> 9、concurrrenthashmap怎么实现线程安全，一个里面会有几个段 segment，jdk1.8后有优化concurrenthashmap吗?分段锁有什么坏处

### 4.1 乐观锁和悲观锁

- 悲观锁：
  + 认为自己在使用数据的时候==一定有别的线程来修改数据==，因此在获取数据的时候会先加锁，确保数据不会被别的线程修改。
  + `synchronized`关键字和`Lock`的实现类都是悲观锁。
  + 适合==写操作==多的场景，先加锁可以保证写操作时数据正确。
  + **显式的锁定**之后再操作同步资源
  + “狼性锁”

- 乐观锁：“佛系锁”

  + 认为自己在使用数据时==不会有别的线程修改数据或资源==，所以不会添加锁

  + 在Java中是通过使用**无锁编程**来实现，只是在更新数据的时候去判断，之前有没有别的线程更新了这个数据。

    如果这个数据没有被更新，当前线程将自己修改的数据成功写入。

    如果这个数据已经被其它线程更新，则根据不同的实现方式执行不同的操作，比如放弃修改、重试抢锁等等

    如原子操作类那些底层是CAS算法，也就是乐观锁。

乐观锁判断规则

1. 版本号机制Version
2. 最常采用的是==CAS算法==，Java原子类中的递增操作就通过CAS自旋实现的。🔖

乐观锁使用场景：适合==读操作==多的场景，不加锁的特点能够使其读操作的性能大幅提升。

乐观锁则直接去操作同步资源，是种无锁算法，得之我幸不得我命，再努力就是

伪代码说明：

```java
//  ======悲观锁的调用方式========
public synchronized void m1() {
  // 加锁后的业务逻辑....
}
// 保证多个线程使用的事同一个lock对象的前提下
ReentrantLock lock = new ReentrantLock();
public void m2() {
  lock.lock();
  try {
		// 操作同步资源
  } finally {
    lock.unlock();
  }
}


// =======乐观锁的调用方式
// 保证多个线程使用的是同一个AtomicInteger
private AtomicInteger atomicInteger = new AtomicInteger();
atomicInteger.incrementAndGet();
```



### 案例

```java
// 资源类
class Phone {
    public synchronized void sendEmail() {
        // try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("-----sendEmail");
    }
    public synchronized void sendSMS() {
        System.out.println("-----sendSMS");
    }
    public static synchronized void sendEmailStatic() {
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("-----sendEmail");
    }
    public static synchronized void sendSMSStatic() {
        System.out.println("-----sendSMS");
    }
    public void hello() {
        System.out.println("-----hello");
    }
}
```

> 题目：谈谈你对多线程锁的理解，8锁案例说明
>
> 口诀：线程    操作      资源类
>
> 8锁案例说明
>
> 1. 标准访问有a、b两个线程，请问先打印邮件还是短信？ 先邮件后短信
>
> 2. 在sendEmail方法中加入暂停3秒钟，请问先打印邮件还是短信？先邮件后短信
>
> 3. 添加一个普通的hello方法，在b线程中调用（替换调用短信），请问先打印右键换是hello？ 先hello，3秒后打印邮件
>
> 4. 有两部手机，分别调用邮件和短信，请问先打印邮件还是短信？先短信，暂停3秒后邮件
>
> 5. 有两个静态同步方法，有1部手机，请问先打印邮件还是短信？ 暂停后，先邮件后短信
>
> 6. 有两个静态同步方法，有2部手机，请问先打印邮件还是短信？ 暂停后，先邮件后短信
>
> 7. 一个静态同步方法（邮件），一个普通同步方法（短信），有1部手机，请问先打印邮件还是短信？  先短信，暂停 后邮件
>
> 8. 一个静态同步方法（邮件），一个普通同步方法（短信），有2部手机，请问先打印邮件还是短信？  先短信，暂停 后邮件



`synchronized`是悲观锁。

只要一个方法被`synchronized`锁定，那么如果访问这个资源类的任何一个`synchronized`方法时，整个资源类都会被锁定，也就是这个资源类中所有`synchronized`方法

因此案例2中，在加入了3秒休眠后，结果是暂停3秒后，先邮件后短信

总结：

- 案例1、2总结，一个对象里面如果有多个`synchronized`方法，某一个时刻内，只要一个线程去调用其中的一个`synchronized`方法了，其它的线程都只能等待，换句话说，某一个时刻内，智能有唯一的一个线程访问呢这些`synchronized`方法。锁的当前对象this（”==对象锁==“），被锁定后，其它线程都不能进入到当前对象的其它`synchronized`方法。

- 案例3中，普通hello方法，不需要去申请this对象锁，线程之间不会产生竞争。
- 案例4中，两个对象互不干扰，没有竞争
- 案例5、6中，`static synchronized`是==类锁==，锁的是整个类。三种synchronized锁的差别：
  - ==普通同步方法==，锁的当前实例对象，通常指this。
  - ==静态同步方法==，锁的是当前类的Class对象，如`Phone.class`。
  - ==同步方法块==，锁的是synchronized括号内的对象。
- 案例7、8中，对象锁和类锁互补干扰。也就是说，静态同步方法与普通同步方法之间是不会有静态条件的。

阿里手册：

![](images/image-20231127093841121.png)



![](images/image-20231127093946838.png)

#### JDK源码说明synchronized

线程的notify方法有一个说明：

> This method should only be called by a thread that is the owner of this object's monitor. A thread becomes the owner of the object's monitor in one of three ways:
>
> - By executing a synchronized instance method of that object.
> - By executing the body of a synchronized statement that synchronizes on the object.
> - For objects of type Class, by executing a synchronized static method of that class.
>
> 此方法只能由作为此对象监视器所有者的线程调用。线程通过以下三种方式之一成为对象监视器的所有者： 
>
> - 通过执行该对象的同步实例方法。 
> - 通过执行在对象上同步的同步语句的正文。 
> - 对于 Class 类型的对象，通过执行该类的同步静态方法。

这里三种方式也就对应之前说的synchronized锁三种使用方式。



> `java -c *.class`  对代码进行反编译查看
>
> `java -v *.class`   更详细的信息（包括行号、本地变量表、反汇编等详细信息）





- `synchronized`同步代码块，实现使用的是`monitorenter`和`monitorexit`指令

```java
public class LockSyncDemo {
    Object object = new Object();

    public void m1() {
        synchronized (object) {
            System.out.println("-----hello synchronized code block");
        }
    }

    public static void main(String[] args) {

    }
}
```

`javap -c LockSyncDemo`

![](images/image-20231127111850449.png)

有两个退出指令，第二个退出指令是为了保证，如果同步块里出现异常情况，也能退出。

特殊情况：

![](images/image-20231127111925531.png)

- `synchronized`普通同步方法

```java
    public synchronized void m2() {
        System.out.println("-----hello synchronized m2");
    }
```

`javap -v LockSyncDemo`

![](images/image-20231127112843072.png)

调用指令将会检查方法的`ACC_SYNCHRONIZED`访问标志是否被设置。如果设置了，执行线程会将先持有monitor锁，然后再执行方法，最后在方法完成（无论是正常完成还是非正常完成）时释放monitor。

- 静态同步方法

![](images/image-20231127112918343.png)

`ACC_STATIC`, `ACC_SYNCHRONIZED`两个访问标识区分是否是静态同步方法



==管程==（英语：Monitors，也称为监视器）是一种程序结构，结构内的多个子程序（对象或模块）形成的多个工作线程互斥访问共享资源。这些共享资源一般是硬件设备或一群变量。对共享变量能够进行的所有操作集中在一个模块中。（把信号量及其操作原语“封装”在一个对象内部）

管程实现了在一个时间点，最多只有一个线程在执行管程的某个子程序。管程提供了一种机制，管程可以看做一个软件模块，它是将共享的变量和对于这些共享变量的操作封装起来，形成一个具有一定接口的功能模块，进程可以调甲管程来实现进程级别的并发控制。

**==执行线程就要求先成功持有管程，然后才能执行方法，最后当方法完成（无论是正常完成还是非正常完成）时释放管程。在方法执行期间，执行线程持有了管程，其他任何线程都无法再获取到同一个管程。==**如果一个同步方法执行期间抛出了异常，并且在方法内部无法处理此异常，那这个同步方法所持有的管程将在异常抛出到同步方法边界之外时自动释放。

#### C++代码说明synchronized

> 面试题：为什么任何一个对象都可以成为一个锁？

在HotSpot虚拟机中，monitor采用`ObjectMonitor`实现，`ObjectMonitor.java`  ->  `ObjectMonitor.cpp`  ->  `ObjectMonitor.hpp` （hpp是头文件，会被cpp引入）

https://github.com/openjdk/jdk/blob/6aa197667ad05bd93adf3afc7b06adbfb2b18a22/src/jdk.hotspot.agent/share/classes/sun/jvm/hotspot/runtime/ObjectMonitor.java#L35

https://github.com/openjdk/jdk/blob/6aa197667ad05bd93adf3afc7b06adbfb2b18a22/src/hotspot/share/runtime/objectMonitor.cpp

https://github.com/openjdk/jdk/blob/6aa197667ad05bd93adf3afc7b06adbfb2b18a22/src/hotspot/share/runtime/objectMonitor.hpp#L130

![](images/image-20231127114834178.png)

Java中的Object对象的wait、notiy等方法就对应C++中的 `ObjectMonitor::wait`等。

![](images/image-20231127115046689.png)

`ObjectMonitor.hpp`文件中就初始化监视器（管程）：

![](images/image-20231127115858967.png)

ObjectMonitor中有几个关键属性：

| _owner      | 指向持有ObjectMonitor对象的线程   |
| ----------- | --------------------------------- |
| _WaitSet    | 存放处于wait状态的线程队列        |
| _EntryList  | 存放处于等待锁block状态的线程队列 |
| _recursions | 锁的重入次数                      |
| _count      | 用来记录该线程获取锁的次数        |

因此上面的题目的有答案了：

- 每个对象天生都带着一个对象监视器
- 每一个被锁住的对象都会和Monitor关联起来 



### 4.2 公平锁和非公平锁

#### 什么是公平锁和非公平锁

- 公平锁：是指多个线程按照申请锁的顺序来获取锁。类似排队打饭先来后到。排队买票，先来的人先买后来的人在队尾排着，这是公平的Lock lock = new ReentrantLock（true）；//true ==表示公平锁，先来先得==
- 非公平锁：是指在多线程获取锁的顺序并不是按照申请锁的顺序,有可能后申请的线程比先申请的线程优先获取到锁,在高并发的情况下,有可能造成优先级反转或者饥饿的状态（某个线程一直得不到锁）

注意：`synchronized` 和 `ReentrantLock` 默认是非公平锁


#### 排队抢票案例(公平出现锁饥饿)

锁饥饿:我们使用5个线程买100张票,使用ReentrantLock默认是非公平锁,获取到的结果可能都是A线程在出售这100张票,会导致B、C、D、E线程发生锁饥饿(使用公平锁会有什么问题)



#### 为什么会有公平锁、非公平锁的设计?为什么默认非公平？

面试题

- 恢复挂起的线程到真正锁的获取还是有时间差的,从开发人员来看这个时间微乎其微,但是从CPU的角度来看,这个时间存在的还是很明显的,所以非公平锁能更**充分的利用CPU的时间片,尽量减少CPU空闲状态时间**。
- 使用多线程很重要的考量点是**线程切换的开销**,当采用非公平锁时，当一个线程请求锁获取同步状态,然后释放同步状态,因为不需要考虑是否还有前驱节点,所以刚释放锁的线程在此刻再次获取同步状态的概率就变得非常大了,所以就减少了线程的开销线程的开销

#### 什么时候用公平？什么时候用非公平？

面试题

如果为了更高的吞吐量,很显然非公平锁是比较合适的,因为节省很多线程切换时间,吞吐量自然就上去了；

否则那就用公平锁,大家公平使用，没有效率、性能要求没那么高。

#### 源码解读

- 公平锁:排序排队公平锁,就是判断同步队列是否还有先驱节点的存在(我前面还有人吗?),如果没有先驱节点才能获锁
- 先占先得非公平锁,是不管这个事的,只要能抢获到同步状态就可以
- ReentrantLock默认是非公平锁,公平锁要多一个方法,所以非公平锁的性能更好(aqs源码)

![image-20240321235919091](images/image-20240321235919091.png)

![](images/image-20240322000115445.png)

### 4.3 可重入锁(又名递归锁)

#### 什么是可重入锁？

可重入锁又名递归锁,是指在==同一个线程==在外层方法获取锁的时候，再进入该线程的内层方法会==自动获取锁==(前提，锁对象得是同一个对象)，不会因为之前已经获取过还没有释放而阻塞。

如果是1个有`synchronized`修饰得递归调用方法，**程序第2次进入被自己阻塞了岂不是天大的笑话,出现了作茧自缚**。

所以Java中`ReentrantLock`和`synchronized`都是可重入锁，可重入锁的一个优点是**可在一定程度避免死锁**。



#### 可重入锁这四个字分开解释

可: 可以

重: 再次

入: 进入

锁: 同步锁

进入什么：进入同步域(即同步代码块、方法或显示锁锁定的代码)



一个线程中的多个流程可以获取同一把锁，持有这把同步锁可以再次进入。自己可以获取自己的内部锁。



#### 可重入锁的种类

- 隐式锁(即synchronized关键字使用的锁)默认是可重入锁。【在同步块、同步方法都使用】

**在一个synchronized修饰的方法或者代码块的内部调用本类的其他synchronized修饰的方法或代码块时,是永远可以得到锁的**。

同步块：

```java
final Object object = new Object();

new Thread(() -> {
  synchronized (object) {
    System.out.println(Thread.currentThread().getName() + "\t ------外层调用");
    synchronized (object) {
      System.out.println(Thread.currentThread().getName() + "\t ------外层调用");
      synchronized (object) {
        System.out.println(Thread.currentThread().getName() + "\t ------外层调用");
      }
    }
  }
}, "t1").start();
```

同步方法：

```java
    public synchronized void m1() {
        // 在一个synchronized修饰的方法或者代码块的内部调用本类的其他synchronized修饰的方法或代码块时,是永远可以得到锁的
        System.out.println(Thread.currentThread().getName() + "\t ----- come in");
        m2();
        System.out.println(Thread.currentThread().getName() + "\t ------end m1");
    }
    public synchronized void m2() {
        System.out.println(Thread.currentThread().getName() + "\t ----- come in");
        m3();
    }
    public synchronized void m3() {
        System.out.println(Thread.currentThread().getName() + "\t ----- come in");
    }
```

```java
        // 从头到尾只有t2这一个线程持有同一把锁
        ReEntryLockDemo reEntryLockDemo = new ReEntryLockDemo();
        new Thread(() -> {
            reEntryLockDemo.m1();
        }, "t2").start();
```



- 显示锁(即Lock)也有`ReentrantLock`这样的可重入锁

​	lock和unlock一定要一一匹配,如果少了或多了,都会坑到别的线程

```java
    static Lock lock = new ReentrantLock();
    // 显示锁，lock和unlock方法是一一对应的。
    public static void xianshiLock() {

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t ----- come in外层调用");
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "\t ----- come in内层调用");
                } finally {
                    lock.unlock();
                }
            } finally {
//                lock.unlock();
            }
        }, "t1").start();

        // 如果t1线程中缺少unlock，就会死锁，导致t2线程无法获取到锁，一直在等待
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t ----- come in外层调用");
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
```

#### Synchronized的重入的实现机理

为什么任何一个对象都可以成为一个锁

`ObjectMonitor.hpp`（`ReentrantLock`也是使用这个。都是使用这个？）

![](images/image-20240322003531930.png)

ObjectMonitor中有几个关键属性：

| _owner      | 指向持有ObjectMonitor对象的线程   |
| ----------- | --------------------------------- |
| _WaitSet    | 存放处于wait状态的线程队列        |
| _EntryList  | 存放处于等待锁block状态的线程队列 |
| _recursions | 锁的重入次数                      |
| _count      | 用来记录该线程获取锁的次数        |

- 每个锁对象拥有一个锁计数器（`_recursions`）和一个指向持有该锁的线程的指针（`_owner`）。

- 当执行`monitorenter`时，如果目标锁对象的计数器为零，那么说明它没有被其他线程所持有，Java虚拟机会将该锁对象的持有线程设置为当前线程，并且将计数器加1。

- 在目标锁对象的计数器不为零的情况下，如果锁对象的持有线程时当前线程，那么Java虚拟机可以将其计数器加1，否则需要等待,直到持有线程释放该锁。

- 当执行`monitorexit`，Java虚拟机则需将锁对象的计数器减1。计数器为零代表锁已经释放。
  

### 4.4 死锁及排查

#### 什么是死锁？

死锁是指两个或两个以上的线程在执行过程中,因争夺资源而造成的一种==互相等待==的现象,若无外力干涉那它们都将无法推进下去,如果资源充足,进程的资源请求都能够得到满足,死锁出现的可能性就很低,否则就会因争夺有限的资源而陷入死锁。

![](images/image-20240322005455350.png)



#### 产生死锁的原因

- 系统资源不足

- 进程运行推进的顺序不合适

- 资源分配不当

#### 代码展示

```java
public class DeadLockDemo {
    public static void main(String[] args) {
        final Object objectA = new Object();
        final Object objectB = new Object();

        new Thread(() -> {
            synchronized (objectA) {
                System.out.println(Thread.currentThread().getName() + "\t ----- 自己持有A锁，希望获得B锁");
                try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
                synchronized (objectB) {
                    System.out.println(Thread.currentThread().getName() + "\t ----- 成功获得B锁");
                }
            }
        }, "A").start();

        new Thread(() -> {
            synchronized (objectB) {
                System.out.println(Thread.currentThread().getName() + "\t ----- 自己持有B锁，希望获得A锁");
                try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
                synchronized (objectA) {
                    System.out.println(Thread.currentThread().getName() + "\t ----- 成功获得A锁");
                }
            }
        }, "B").start();
    }
}
```



#### 如何排除死锁

程序一直运行（灯不灭），怎么证明是死锁了

##### 方式一:纯命令

`jps`（java ps -ef）

```
jps -l
jstack 进程id
```

![](images/image-20240322010629982.png)

##### 方式二:jconsole(输入cmd,输入jconsole,点击检测死锁按钮)

![](images/image-20240322010825087.png)



> 本章后面部分，学完之后章节，再学
>
> ![](images/image-20240322011044716.png)

### 小总结

![](images/image-20240322011336070.png)

#### ObjectMonitor

指针指向monitor对象（也称为管程或监视器锁）的起始地址。每个对象都存在着一个monitor与之关联，当一个 monitor 被某个线程持有后，它便处于**锁定状态**。在Java虚拟机（HotSpot）中，monitor是由ObjectMonitor实现的，其主要数据结构如下（位于HotSpot虚拟机源码ObjectMonitor.hpp文件，C++实现的）

![](images/iShot_2024-03-21_23.40.48.png)



### 4.5 自旋锁

#### 什么是自旋锁？

(是指尝试获取锁的线程不会立即阻塞，而是采用循环的方式去尝试获取锁,当线程发现锁被占用时，会不断循环判断锁的状态，直到获取。这样的好处是减少线程上下文切换的消耗，缺点是循环会消耗CPU)



#### 如何手写一个自选锁





#### CAS缺点

1. 循环时间长开销很大
2. 引出来ABA问题(在CAS篇章将详细说明)







## 5 LockSupport与线程中断





## 6 Java内存模型之JMM



## 7 volatile与JMM





## 8 CAS





## 9 原子操作类AtomicLong、LongAdder、LongAccumulator





## 10 ThreadLocal



## 11 Java对象内存布局和对象头



## 12 synchronized与锁升级



## 13 AbstractQueuedSynchronizer之AQS



## 14 ReentrantLock、ReentrantReadWriteLock、StampedLock



