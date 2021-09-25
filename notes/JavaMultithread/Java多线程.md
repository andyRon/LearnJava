Java多线程
------------

[【狂神说Java】多线程详解](https://www.bilibili.com/video/BV1V4411p7EF?spm_id_from=333.999.0.0)



## 一、线程简介

任务、进程、线程、多线程

多任务

多线程 

### 普通方法调用和多线程

![](../../images/java-067.jpg)

### 程序-进程-线程

Process Thread

 

### 本章核心概念

- 线程就是独立的执行路径；
- 在程序运行时，即使没有自己创建线程，后台也会有多个线程，如<u>主线程，gc线程</u>； 
- main()称之为主线程，为系统的入口，用于执行整个程序；
- 在一个进程中，如果开辟了多个线程，线程的运行由调度器安排调度，调度器是与操作系统紧密相关的，先后顺序是不能人为的干预的。
- 对同一份资源操作时，会存在资源抢夺的问题，需要加入**并发控制**；
- 线程会带来额外的开销，如**cpu调度时间，并发控制开销**。
- 每个线程在自己的**工作内存**交互，内存控制不当会造成数据不一致



> 注意：很多线程都是模拟出来的，真正的多线程是指由多个CPU，即多核，如服务器。如果是模拟出来的多线程，即在一个cpu的情况下，**在同一个时间点，cpu只能执行一个代码**，因为切换的很快，所以就有同时执行的错局。





## 二、线程实现【重点】

### 三种创建方式

![](../../images/java-068.jpg)



### Thread

步骤：

- 自定义线程类继承**Thread**类
- 重写run()方法，编写线程执行体
- 创建线程对象，调用start()方法启动线程

```java
public class TestThread1 extends Thread {

    @Override
    public void run() {
        // 线程体
        for (int i = 0; i < 20; i++) {
            System.out.println("我在看代码=----" + i);
        }
    }

    public static void main(String[] args) {
        // main线程，主线程

        // 创建一个线程对象
        TestThread1 testThread1 = new TestThread1();

        // 调用start()方法开启线程
        testThread1.start();

        for (int i = 0; i < 2000; i++) {
            System.out.println("我在学习多线程--" + i);
        }
    }
}
```

线程开启不一定立即执行，由CPU调度执行。

#### 案例，下载图片

先下载Apache Commons IO的jar，直接拷贝到项目中，然后右击**Add as Library**就可以使用。

### 实现Runnable接口

步骤：

- 定义MyRunnable类实现Runnable接口
- 实现run()方法，编写线程执行体
- 创建线程对象，调用start()方法启动线程

```java
public class TestThread3 implements Runnable {
    @Override
    public void run() {
        // 线程体
        for (int i = 0; i < 200; i++) {
            System.out.println("Runnable我在看代码=----" + i);
        }
    }

    public static void main(String[] args) {
        // 创建Runnable接口的实现类
        TestThread3 testThread3 = new TestThread3();

        // 创建线程对象，通过线程对象来开启我们的线程，代理
        new Thread(testThread3).start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("Runnable我在学习多线程--" + i);
        }
    }
}
```



### 继承Thread类 vs 实现Runnable接口

继承Thread类：

- 子类继承Thread类具备多线程能力
- 启动线程：子类对象.start()
- 不建议使用：避免OOP单继承局限性

实现Runnable接口：

- 实现接口Runnable具有多线程能力

- 启动线程：传入目标对象+Thread对象.start()

- 推荐使用：**避免单继承局限性，灵活方便，方便同一个对象被多个线程使用**

  ```java
  // 一份资源
  StartThread4 station = new StartThread4();
  
  // 多个代理
  new Thread(station, "小明").start();
  new Thread(station, "老是").start();
  new Thread(station, "小红").start();
  ```


#### 案例：买火车票

多个线程同时操作同一个对象

#### 案例：龟兔赛跑-Race



### 实现Callable接口(了解)

1. 实现Callable接口，需要返回值类型
2. 重写call方法，需要抛出异常
3. 创建目标对象
4. 创建执行服务：ExecutorService ser = Executors.newFixedThreadPool(1)； 
5. 提交执行：Future<Boolean> result1 = ser.submit(t1)；
6. 获取结果：boolean r1 = result1.get()
7. 关闭服务：ser.shutdownNow()；



callable的好处：

1. 可以定义返回值
2. 可以抛出异常

### 静态代理 

真实对象和代理对象都要实现同一个接口;

代理对象要代理真实角色;

静态搭理的好处：
 *      代理对象可以做很多真实对象做不了的事
 *      真实对象专注于自己的事情

```java
public class StaticProxy {
    public static void main(String[] args) {
        You you = new You();
        WeddingCompany weddingCompany = new WeddingCompany(you);
        weddingCompany.happyMarry();
    }
}

interface Marry {
    void happyMarry();
}

/**
 * 真实角色，你去结婚
 */
class You implements Marry {

    @Override
    public void happyMarry() {
        System.out.println("我要结婚了，好开心");
    }
}

/**
 * 代理角色，帮助你结婚
 */
class WeddingCompany implements Marry {

    // 代理谁？ --》 真实目标角色
    private Marry target;

    public WeddingCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void happyMarry() {
        before();
        this.target.happyMarry(); // 这是真实对象
        after();
    }

    private void after() {
        System.out.println("结婚之后收尾款");
    }

    private void before() {
        System.out.println("结婚之前布置现场");
    }
}
```



### Lambda表达式

- λ希腊字母表中排序第十一位的字母，英语名称为Lambda
- 避免匿名内部类定义过多
- 可以让代码看起来很简洁
- 去掉了一堆没有意义的代码，留下核心的逻辑
- 其实质属于函数式编程的概念

**Funtional Interface(函数式接口)**是学习Lambda表达式的关键。

任何接口，如果只包含唯一一个抽象方法，那么它就是一个函数式接口。可以通过lambda表达式来创建该接口的对象。 

Lambda表达式的演化过程：

```java
public class TestLambda1 {
    // 3. 静态内部类
    static class Like2 implements ILike {
        @Override
        public void lambda() {
            System.out.println("I like lambda2");
        }
    }

    public static void main(String[] args) {
        ILike like = new Like();
        like.lambda();

        like = new Like2();
        like.lambda();

        // 4. 局部内部类
        class Like3 implements ILike {
            @Override
            public void lambda() {
                System.out.println("I like lambda3");
            }
        }
        like = new Like3();
        like.lambda();

        // 5. 匿名内部类，没有类的名称，必须借助接口或者父类
        like = new ILike() {
            @Override
            public void lambda() {
                System.out.println("I like lambda4");
            }
        };
        like.lambda();

        // 6. 用lambda简化
        like = () -> {
            System.out.println("I like lambda5");
        };
        like.lambda();
    }

}

// 1. 定义函数式接口
interface ILike {
    void lambda();
}

// 2. 实现类
class Like implements ILike {
    @Override
    public void lambda() {
        System.out.println("I like lambda");
    }
}
```

Lambda表示的简化：

```java
public class TestLambda2 {
    public static void main(String[] args) {
        ILove love = null;

        love = (int a) -> {
            System.out.println("I love you -->" + a);
        };

        // 简化1：去掉参数类型，多个参数也可以去掉参数类型
        love = (a) -> {
            System.out.println("I love you -->" + a);
        };

        // 简化2：去掉括号
        love = a -> {
            System.out.println("I love you -->" + a);
            System.out.println("I love you too.");
        };

        // 简化3：去掉大括号（因为代码只有一行）
        love = a -> System.out.println("I love you -->" + a);

        love.love(520);
    }
}

interface ILove {
    void love(int a);
}
```

总结：

- 前提是接口为函数式接口
- Lambda表示只有一行代码事才能简化为一行
- 多个参数也可以去掉参数类型，要去掉就都去掉，必须加上括号

## 三、线程五大状态

![](../../images/java-069.jpg)



![](../../images/java-070.jpg)



### 线程方法



| 方法                           | 说明                                       |
| ------------------------------ | ------------------------------------------ |
| setPriority(int newPriority)   | 更改线程的优先级                           |
| static void sleep(long millis) | 在指定的毫秒数内让当前正在执行的线程休眠   |
| void join()                    | 等待该线程终止                             |
| static void yield()            | 暂停当前正在执行的线程对象，并执行其他线程 |
| void interrupt()               | 中断线程(不建议使用)                       |
| boolean isAlive()              | 测试线程是否处于活动状态                   |

### 停止线程

- 不推荐使用JDK提供的stop()、destroy()（已废弃）
- 建议控制线程自己停止下来，使用一个标志位

```java
public class TestStop implements Runnable{

    private boolean flag = true;
    @Override
    public void run() {
        int i = 0;
        while (flag) {
            System.out.println("run ... Thread " + i++);
        }
    }

    // 设置一个公开的方法停止线程，转换标志位
    public void stop() {
        this.flag = false;
    }

    public static void main(String[] args) {
        TestStop testStop = new TestStop();
        new Thread(testStop).start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("main " + i);
            if (i==900) {
                testStop.stop();
                System.out.println("线程该停止了");
            }
        }
    }
}
```



### 线程休眠

- sleep（时间）指定当前线程阻塞的毫秒数； 
- sleep存在异常InterruptedException； 
- sleep时间达到后线程进入就绪状态； 
- sleep可以模拟网络延时，倒计时等。
- **每一个对象都有一个锁，sleep不会释放锁**；





### 线程礼让（暂停）

- 礼让线程，让当前正在执行的线程暂停，但不阻塞
- 将线程从运行状态转为就绪状态
- 让CPU重新调度，礼让不一定成功，看CPU心情

```java
public class TestYield {
    public static void main(String[] args) {
        MyYield myYield = new MyYield();

        new Thread(myYield, "a").start();
        new Thread(myYield, "b").start();
    }

}

class MyYield implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"线程开始运行");
        Thread.yield();
        System.out.println(Thread.currentThread().getName()+"线程结束运行");
    }
}
```



### 线程强制执行Join

插队

```java
public class TestJoin implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("vip线程来了" + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 启动线程
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin);
        thread.start();

        // 主线程
        for (int i = 0; i < 500; i++) {
            if (i==200) {
                thread.join(); //插队
            }
            System.out.println("main" + i);
        }
    }
}
```

### 线程状态监测

`Thread.State`



### 线程优先级

- Java提供一个线程调度器来监控程序中启动后进入就绪状态的所有线程，线程调度器按照优先级决定应该调度哪个线程来执行。

- 线程的优先级用数字表示，范围从1~10。**数值大小不代表线程执行的先后顺序，只表示被CPU执行几率大小。**

  ```java
  Thread.MIN_PRIORITY = 1;
  Thread.MAX_PRIORITY = 10;
  Thread.NORM_PRIORITY = 5;
  ```

- 使用以下方式改变或获取优先级

  ```java
  getPriority()
  setPriority(int xxx)
  ```

  

### 守护（daemon）线程

- 线程分为**用户线程**和**守护线程**
- 虚拟机必须确保用户线程执行完毕
- 虚拟机不用等待守护线程执行完毕
- 如后台记录操作日志，监控内存，垃圾回收等待..





## 四、线程同步【重点】

多个线程操作同一个资源

并发：同一个对象被多个线程同时操作。

处理多线程问题时，多个线程访问同一个对象，并且某些线程还想修改这个对象.这时候我们就需要线程同步.线程同步其实就是一种**等待机制**，多个需要同时访问此对象的线程进入这个**对象的等待池**形成队列，等待前面线程使用完毕，下一个线程再使用。

### 队列和锁

排队打饭

排队上厕所



安全  性能

由于同一进程的多个线程共享同一块存储空间，在带来方便的同时，也带来了访问冲突问题，为了保证数据在方法中被访问时的正确性，在访问时加入**锁机制synchronized**，当一个线程获得对象的排它锁，独占资源，其他线程必须等待，使用后释放锁即可。存在以下问题：

- 一个线程持有锁会导致其他所有需要此锁的线程挂起；
- 在多线程竞争下，加锁，释放锁会导致比较多的上下文切换和调度延时，引起性能问题；
- 如果一个优先级高的线程等待一个优先级低的线程释放锁会导致优先级倒置，引起性能问题，

### 



### 三个不安全的案例

  

### 同步方法

- 由于我们可以通过`private`关键字来保证数据对象只能被方法访问，所以我们只需要针对方法提出一套机制，这套机制就是`synchronized`关键字，它包括两种用法：**synchronized方法**和**synchronized块**.

  > 同步方法：public synchronized void method（int args）{}

- synchronized方法控制对“对象”的访问，每个对象对应一把锁，每个synchronized方法都必须获得调用该方法的对象的锁才能执行，否则线程会阻塞，方法一旦执行，就独占该锁，直到该方法返回才释放锁，后面被阻塞的线程才能获得这个锁，继续执行。

  > 缺陷：若将一个大的方法申明为synchronized将会影响效率

### 同步块  

```
synchronized(Obj){}
```



🔖p20





## 五、线程通信问题

线程池
