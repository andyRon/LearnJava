尚硅谷JVM
----

[尚硅谷宋红康JVM全套教程（详解java虚拟机）](https://www.bilibili.com/video/BV1PJ411n7xZ)

《内存与垃圾回收篇》p1-p203

《字节码与类的加载篇》p204-p301

《性能监控与调优篇》三个篇章 p302-p381

[JVM从入门到精通 · 语雀 ](https://www.yuque.com/u21195183/jvm)

Java8 本课程基于

Java11 LTS



# 一、内存与垃圾回收篇

## 1 JVM与Java体系结构

**你是否也遇到过这些问题？**

- 运行着的线上系统突然卡死，系统无法访问，甚至直接OOM!
- 想解决线 上JVM GC问题，但却无从下于。
- 新项目上线，对各种JVM参数设置一脸茫然，直接默认吧，然后就GG了
- 每次面试之前都要重新背一遍JVM的一些原理概念性的东西，然而面试官却经常问你在实际项目中如何调优JVM参数I 如何解决GC、OON等问题，一脸懵逼。

**架构师每天都在思考什么？**

- 应该如何让我的系统更快？
- 如何避免系统出现瓶颈？

### 参考书籍

[The Java Virtual Machine Specification, Java SE 8 Edition](https://docs.oracle.com/javase/specs/jvms/se8/html/index.html)

[The Java Virtual Machine Specification, Java SE 11 Edition](https://docs.oracle.com/javase/specs/jvms/se11/html/index.html)

《深入理解Java虚拟机》第三版

### Java与JVM简介

世界上没有最好的编程语言，只有最适用于具体应用场景的编程语言。

JS

人工智能 Python

微服务 GO

Java生态圈

####  Java虚拟机规范

The Java Virtual Machine is the cornerstone of the Java platform. It is the component of the technology responsible for its hardware- and operating system- independence, the small size of its compiled code, and its ability to protect users from malicious programs.

The Java Virtual Machine is an abstract computing machine. Like a real computing machine, it has an instruction set and manipulates various memory areas at run time. It is reasonably common to implement a programming language using a virtual machine; the best-known virtual machine may be the P-Code machine of UCSD Pascal.

#### JVM：跨语言的平台

Java：跨平台的语言

![](images/image-20211203122658947.png)

（上面的编译器可以称为编译器的前端，Java虚拟机中的解释器、JIT等可称为编译器的后端）

Java虚拟机根本不关心运行在其内部的程序到底是使用何种编程语言编写的，**它只关心 “字节码”文件**。也就是说Java虚拟机拥有语言无关性，并不会单纯地与Java语言 “终身綁定”，只要其他编程语言的编译结果满足并包含Java虚拟机的内部指令集、符号表以及其他的铺助信息，它就是一个有效的字节码文件，就能够被虚拟机所识别并装载运行。

<font color=#FF263D>Java不是最强大的语言，但是JVM是最强大的虚拟机。</font>

> IT领域三大难题：CPU、操作系统、编译器

#### 字节码

- 我们平时说的java字节码，指的是用iava语言编译成的字节码。准确的说任何能在ivm平台上执行的字节码格式都是一样的。所以应该统称为：**jvm字节码**。
- 不同的编译器，可以编译出相同的字节码文件，字节码文件也可以在不同的JVM上运行。
- Java 虚拟机与 Java 语言并没有必然的联系，它只与特定的二进制文件格式—Class文件格式所关联，Class 文件中包含了 Java 虚拟机指令集（或者称为字节码、Bytecodes）和符号表，还有一些其他辅助信息。

####  多语言混合编程

**Java平台上的多语言混合编程正成为主流，通过特定领域的语言去解决特定领域的问题是当前钟件开发应对日趋复杂的项目需求的一个方向。**

试想一下，在一个项目之中，并行处理用Clojure语言编写，展示层使用JRuby/Rails，中间层则是Java，每个应用层都将使用不同的编程语言来完成，而且，接口对每一层的开发者都是透明的，**各种语言之间的交互不存在任何困难，就像使用自己语言的原生API一样方便，因为它们最终都运行在一个虛拟机之上。**

对这些运行于Java虛拟机之上、Java之外的语言，来自系统级的、底层的支持正在迅速增强，以JSR-292为核心的一系列项目和功能改进（如DaVinci Machine 项目、Nashorn引擎、InvokeDynamic指令、java. lang .invoke包等），**推动Java虛拟机从“ Java语言的虚拟机”向“多语言虚拟机”的方向发展**。

#### 如何真正搞懂JVM？

自己动手写Java虚拟机

天下事有难易乎？

为之，则难者亦易矣；不为，则易者亦难矣。



### Java发展的重大事件

- 1990年，在Sun 计算机公司中，由 Patrick Naughton、Mikesheridan 及James Gosling 领导的小组Green ream，开发出的新的程序语言，命名为oak,后期命名为Java
- 1995年，Sun正式发布Java和HotJava产品，Java首次公开完相。
- 1996年1月23日Sun Microsystems发布了JDK 1.0。
- 1998年，JDK 1.2版本发布。同时，sun发布了 JSP/Servlet、EJB规范，以及将Java分成了
  J2EE、J2SE和J2ME。这表明了 Java开始向企业、桌面应用和移动设备应用3大领域挺进。
- 2000年.，JDR 1.3发后.，**Java HotSote virtuaI machin**e正式发布，成为Java的默认虚拟机。
- 2002年，JDK 1.4发布，古老的classic虚拟机退出历史舞台。
- 2003年年底，Java平台的Scala正式发布，同年Groovy也加入了 Java阵营。
- 2004年，JDK 1.5发布。同时JDK 1.5改名为JavaSE 5.0（命名方式的修改，5.0里程碑版本，很多新特性）。
- 2006年，JDK 6发布。同年，Java开源并建立了 **OpenJDK**。顺理成章，Hotspot虚拟机也成为了 OpenJDK中的默认虚拟机。

- 2007年，Java平台迎来了新伙伴**Clojure**。
- 2008 年，oracle 收购了 BEA，得到了 **JRockit** 虚拟机。
- 2009年，Twitter宣布把后台大部分程序从Ruiby迁移到Scala，这是Java平台的又一次
  大规模应用。
- 2010年，Oracle收购了sun，**获得Java商标和最具价值的Hotspot虚拟机**。此时，oracle拥有市场占用率最高的两款虚拟机HotSpot和JRockit，并计划在术来对它们进行整合：HotRockit
- 2011年，JDR7发布。在JDK 1.7u4中，正式启用了**新的垃圾回收器G1**。
- 2017年，JDK9发布。将G1设置为默认GC，替代**CMS**
- 同年，IBM的**J9虚拟机**开源，形成了现在的Open J9社区
- 2018年，Android的Java侵权案判决，Google赔偿oracle计88亿美元
- 同年，oracle宣告JavaEE成为历史名词，JDBC、JMS、Servlet赠了Eclipse基金会
- 同年，JDK11发布，LTS版本的JDK，发布革命性的**ZGC**，调整JDK授权许可
- 2019年，JDK12发布，加入RedHat领导开发的**Shenandoah GC**

###  虚拟机与Java虚拟机

#### 虛拟机

所谓虛拟机 (Virtual Machine)，就是一台虚拟的计算机。它是一款软件，用来执行一系列虚拟计算机指令。大体上，虛拟机可以分为**系统虚拟机**和**程序虚拟机。**

- 大名鼎鼎的Visual Box， VMware就属于系统虚拟机，它们**完全是对****物理计算机的仿真**，提供了一个可运行完整操作系统的软件平台。
- 程序虚拟机的典型代表就是Java虛拟机，它**专门为执行单个计算机程****序而设计**，在Java虚拟机中执行的指令我们称为Java字节码指令。

无论是系统虚拟机还是程序虛拟机，在上面运行的软件都被限制于虚拟机提供的资源中。

#### JVM的位置

JVM是运行在操作系统之上的，它与硬件没有直接的交互。

![](images/image-20211203180307981.png)

![](images/image-20211203180359844.png)

`.java` 通过javac（前端编译器）生成`.class`。

#### Google的Android系统结构

![](images/image-20211203180614734.png)

### JVM的整体结构

P11🔖，整体讲解

JVM结构简图：

<img src="images/image-20211203180724786.png"  />



### Java代码执行流程

![](images/image-20211203181630054.png)

热点代码（可以缓存起来，JIT）

### JVM的架构模型

Java编译器输入的指令流基木上是一种**==基于栈的指令集架构==**（HotSpot），另外一种指令集架构则是**==基于寄存器的指令集架构==**。

具体来说，这两种架构之间的区别：
基于栈式架构的特点

- 设计和实现更简单，适用于资源受限的系统；(每执行一个方法就可以理解为一个入栈操作)
- 避开了寄存器的分配难题：使用**零地址指令**方式分配。
- 指令流中的指令大部分是零地址指令，其执行过程依赖于操作栈。**指令集更小**（指令多），编译器容易实现。
- 不需要硬件支持，可移植性更好，更好实现跨平台

基于寄存器架构的特点

- 典型的应用是x86的二进制指令集：比如传统的x86PC以及Android的Davlik虚拟机。
- 指令集架构则完全依赖硬件，可移植性差
- 性能优秀和执行更高效：
- 花费更少的指令去完成一项操作。
- 在大部分情况下，基于寄存器架构的指令集往往都以**一地址指令、二地址指令和三地址指令**为主，而基于栈式架构的指令集却是以零地址指令为主。

#### 举例

![](images/image-20230409222444146.png)

```java
public class StatckStruTest {
    public static void main(String[] args) {
        int i = 2 + 3;
    }
}
```

反编译命令`javap -v StatckStruTest`

上面的结果编译直接得到结果：

![](images/image-20230409223821485.png)

如果代码是：

```java
        int i = 2;
        int j = 3;
        int k = i + j;
```

反编译结果：

![](images/image-20230409224257099.png)

![](images/image-20230409224442723.png)

![](images/image-20230409224508192.png)

![](images/image-20230409224600939.png)

![](images/image-20230409224617087.png)

#### 总结

由于跨平台性的设计，Java的指令都是根据栈来设计的。不同平台CPU架构不同，所以不能设计为基于寄存器的。优点是跨平台，指令集小，编译器容易实现，缺点是性能下降，实现同样的功能需要更多的指令。

**栈：==跨平台性、指令集小、指令多；执行性能比寄存器差==。**

时至今日，尽管嵌入式平台已经不是Java程序的主流运行平台了（准确来说应该是HotSpotvM 的宿主环境己经不局限于嵌入式平台了），那么为什么不将架构更换为基于寄存器的架构呢？

<u>基于栈的架构设计和实现上都比较==简单==；其次是在非资源受限的场景中也是可以用的，也就没有必要更换了。</u>



### JVM的生命周期

#### 虛拟机的启动

Java虛拟机的启动是通过==引导类加载器== (bootstrap class loader)创建个==初始类== (initial class）来完成的，这个类是由虛拟机的具体实现指定的。

#### 虛拟机的执行

- 一个运行中的Java虛拟机有着一个清晰的任务：**==执行Java程序==**。
- 程序开始执行时他才运行，程序结束时他就停止。
- 执行一个所谓的Java程序的时候，真真正正在执行的是一个叫做**==Java虚拟机的进程==**。

#### 虚拟机的退出

有如下的几种情顶：

- 程序正常执行结束
- 程序在执行过程中遇到了异常或错误而异常终止
- 由于操作系统出现错误而导致Java虛拟机进程终止
- 某线程调用Runtime类或System类的exit方法，或 Runtime类的halt方法，并且Java安全管理器也允许这次exit或halt操作。
- 除此之外，==JNI==(Java Native Interface)规范描述了用JNI Invocation API来加载或卸载 Java虚拟机时，Java虚拟机的退出情况。

### JVM的发展历程

#### Sun Classic VM

- 早在1996年Java1.0版本的时候，sun公司发布了一款名为sun classic VM的Java虚拟机，它同时也是**==世界上第一款商用Java虚拟机==**，JDK1.4时完全被淘汰。
- 这款虚拟机内部**只提供解释器**。（解释器逐行解释，效率比较低下）
- 如果使用JIT编译器，就需要进行外挂。但是一旦使用了JIT编译器，JIT就会接管虛拟机的执行系统。解释器就不再工作。解释器和编译器不能配合工作。
- 现在hotspot内置了此虚拟机。

比喻：

步行   解释器

公交车  编译器

![](images/image-20230410092757326.png)

#### Exact VM

- 为了解决上一个虚拟机问题，jak1.2时，sun提供了此虚拟机。

- Exact Memory Management：**准确式内存管理**，也可以叫Non-Conservative/Accurate Memory Management，虛拟机可以知道内存中某个位置的数据具体是什么类型
  
- 具备现代高性能虛拟机的雏形
  热点探测

  编译器与解释器混合工作模式

- 只在Solaris平台短暂使用，其他平台上还是classic vm
  英雄气短，终被Hotspot虚拟机替换

#### SUN公司的Hotspot VM

- Hotspot历史
  + 最初由一家名为“Longview Technologies"的小公司设计
  + 1997年，此公司被sun收购;2009年，sun公司被甲骨文收购。
  + JDK1 .3时，Hotspot V成为默认虚拟机
  
- **目前Hotspot占有绝对的市场地位，称霸武林。**
  + 不管是现在仍在广泛使用的JDK6，还是使用比例较多的JDK8中，默认的虚拟机都是HotSpot
  + Sun/oracle JDK 和openJDK的默认虚拟机
  + 因此本课程中默认介绍的虛拟机都是Hotspot，相关机制也主要是指Hotspot的GC机制。(比如其他两个商用虚拟机都没有**方法区**的概念）
  
- 从服务器、桌面到移动端、嵌入式都有应用。

- 名称中的HotSpot指的就是它的**热点代码探测技术**。
  + 通过计数器找到最具编译价值代码，触发即时编译或栈上替换
  
  + 通过编译器与解释器协同工作，在最优化的最佳执行性能与程序响应时间中取得平衡
  
    编译器 - 最佳执行性能
  
    解释器 - 响应时间

#### BEA的JRockit

- **专注于==服务器端==应用**
  它可以不太关注程序启动速度，因此JRockit内部**不包含解析器实现**，全部代码都靠即时编译器编译后执行。
  
- 大量的行业基准测试显示，**JRockit JVM是世界上最快的JVM**。
  使用JRockit产品，客户己经体验到了显著的性能提高（一些超过了70%）和硬件成木的减少（达50%）
  
- 优势：全面的Java运行时解决方案组合
  JRockit面向延迟敏感型应用的解决方案JRockit Real Time提供以毫秒或微秒级的JVM响应时间，适合财务、军事指挥、电信网络的需要
  
  **Mission Control**服多套件，它是一组以极低的开销来监控、管理和分析生产环境中的应用程序的工具。[JDK Mission Control（JMC）](https://www.oracle.com/java/technologies/downloads/tools/#JMC)
  
- 2008年，BEA被oracle收购。

- Oracle表达了整合两大优秀虚拟机的工作，大致在JDK 8中完成。整合的方式是在Hotspot的基础上，移植JRockit的优秀特性。

- 高斯林：目前就职于谷歌，研究人工智能和水下机器人

#### IBM 的 J9

- 全称：IBM Technology for Java Virtual Machine， 简称IT4J，内部代号：J9
- 市场定位与Hotspot接近，服务器端、桌面应用、嵌入式等多用途VM
- 广泛用于IBM的各种Java产品。
- 目前，有影响力的==三大商用虚拟机==（J9、JRockit、Hotspot）之一，也号称是世界上最快的Java虛拟机。
- 2017年左右，IBM发布了开源J9 VM，命名为OpenJ9，交给EClipse基金会管理，也称为 [Eclipse OpenJ9](https://github.com/eclipse-openj9/openj9)

#### KVM和CDC/CLDC Hotspot

- oracle在Java ME产品线上的两款虛拟机为： CDC/CLDC Hotspot Implementation VM
- KVM (Kilobyte）是CLDC-HI早期产品
- 日前移动领域地位尴尬，智能于机被Android和ios二分天下。
- KVM简单、轻量、高度可移植，而向更低端的设各上还维持自己的一片市场智能控制器、传感器，老人手机、经济欠发达地区的功能手机
- 所有的虚拟机的原则：一次编译，到处运行。

#### Azul VM

- 前面三大 “高性能Java虛拟机”使用在通用硬件平台上
- 这里AZuI VN和BEA Liguid VM是**与特定硬件平台鄉定、软硬件配合的专有虚拟机**，高性能Java虚拟机中的战斗机。
- Azul VM是Azul systems公司在Hotspot基础上进行大量改进，运行于Azul systems公司的专有硬件Vega系统上的Java虛拟机。
- **每个AzuL VM实例都可以管理至少数十个CPU和数百GB内存的硬件资源，并提供在巨大内存范围内实现可控的GC时间的坟圾收集器、专有硬件优化的线程调度等优秀特性**
- 2010年，Azul systems公司开始从硬件转向软件，发布了自己的**Zing JVM**（==低延迟==、快速预热），可以在通用x86平台上提供接近于Vega系统的特性。

#### Liquid VM

- 高性能Java虛拟机中的战斗机。
- BEA公司开发的，直接运行在自家Hypervisor系统上
- Liquid VM即是现在的JRockit VE (Virtual Edition) ，**Liguid VM不需要操作系统的支持，或者说它自己本身实现了一个专用操作系统的必要功能，如线程调度、文件系统、网络支持等**。
- 随着JRockit虚拟机终止开发，Liquid VM项目也停止了。

#### Apache Harmony

- Apache也曾经推出过与JDK 1.5和JDR 1.6兼容的Java运行平台Apache Harmony。
- 它是IBM和Intel联合开发的开源JVM，受到同样开源的openJDK的压制，Sun坚决不让Harmony获得==JCP==认证，最终于2011年退役，IBN转而参与openJDK
- 虽然目前并没有Apache Harmony被大规模商用的案例，但是**它的Java类库代码吸纳进了==Android SDK==**。

#### Microsoft JVM

- 微软为了在IE3浏览器中支持Java Applets， 开发了Microsoft JVM。
- 只能在window平台下运行。但确是当时windows下性能最好的Java VM。
- 1997年，sun以侵犯商标、不正当竞争罪名指控微软成功，赔了sun很多钱。微软在WindowsXP SP3中抹掉了其VM。现在windows上安装的jdk都是HotSpot。

#### TaobaoJVM

- 由AliJVM团队发布。阿里，国内使用Java最强大的公司，覆盖云计算、金融、物流、电商等众多领域，需要解决高并发、高可用、分布式的复合问题。有大量的开源产品。
- **基于openJDK 开发了自己的定制版本AlibabaJDK**，简称==AJDK==。是整个阿里Java体系的基石。
- 基于OpenJDK Hotspot VM 发布的国内第一个优化、**深度定制且开源的高性能服务器版Java虚拟机**。
  + 创新的GCIH (GC invisible heap）技术实现了==off-heap==，即**将生命周期较长的Java对象从heap中移到heap之外，并且GC不能管理GCIH内部的Java对象，以此达到降低GC的回收频率和提升GC的回收效率的目的。**
  + GCIH 中的**对象还能够在多个Java虚拟机进程中实现共享**
  + 使用crc32指令实现 JVM intrinsic 降低JNI 的调用开销
  + PMU hardware 的Java profiling tool 和诊断协助功能
  + 针对大数据场景的ZenGC
- taobao vm应用在阿里产品上性能高，硬件严重依赖intel的cpu，损失了兼容性，但提高了性能
  目前己经在淘宝、天猫上线，把oracle 官方JVM 版本全部替换了。

#### Dalvik VM

- 谷歌开发的，应用于Android系统，并在Android2.2中提供了JIT，发展迅猛。

- **Dalvik VM 只能称作虚拟机，而不能称作 “Java 虚拟机”**，它没有遵循 Java虛拟机规范

- 不能直接执行 Java 的 class文件

- 基于==寄存器架构==，不是jvm的栈架构。

- 执行的是编译以后的**==dex== (Dalvik Executable）**（类似class文件，由其转化过来的）文件。执行效率比较高。
  它执行的dex (Dalvik Executable）文件可以通过class文件转化市来，使用Java语法编写应用程序，可以直接使用大部分的Java AFI等。
  
- Android 5.0 使用支持**提前编译** (Ahead Of Time compilation, ==AOT==)的==ART VM==替换Dalvik VM

  AOT的意思是java源代码不经过字节码文件，直接编译成机器指令。

> 具体JVM的内存结构，其实取决于其实现，不同厂商的JVM，或者同一厂商发布的不同版本，都有可能存在一定差异。**本套课程主要以Oracle Hotspot VM为默认虚拟机。**

#### Graal VM

- 2018年4月，Oracle Labs公开了Graal VM, 号称"**Run Programs Faster Anywhere**"，勃勃野心。与1995年java的”write once, run anywhere"遥相呼应。
- Graal VM在Hotspot VM基础上增强而成的**跨语言全栈虚拟机，可以作为“任何语言”的运行平台使用**。语言包括：Java、scala、 Groovy、Kotlin；C、 C++、Javascript、 Raby、 Python、 R等。
- 支持不向语言中混用对方的校口和对象，支持这些语言使用已经编写好的本地库文件
- 工作原理是将这些语言的源代码或源代码编译后的中间格式，通过解释器转换为能被Graal VM接受的中间表示。Graal VM 提供Truffle工具集快速构建面问一种新语言的解释器。在运行时还能进行即时编译优化，获得比原生编译器更优秀的执行效率。
- **如果说Hotspot有一天真的被取代，Graal VM希望最大**。但是Java的软件生态没有丝毫变化。

https://github.com/oracle/graal

https://www.graalvm.org/

## 2 类加载子系统

🔖P26 整体讲解过程

字节码文件之后虚拟机负责

方法区是HopSpot特有的

![](images/image-20220131122757463.png)

![](images/image-20220131122930914.png)

如果手写一个Java虚拟机，主要考虑哪些结构呢？

**类加载器和执行引擎**

> 章节内容
>
> 2 类加载子系统
>
> 3-10 运行时数据区

### 类加载器子系统作用

- 类加载器子系统负责从文件系统或网络中加载Class文件，class文件在文件开头有**==特定的文件标识==**。
- ClassLoader只负责class文件的加载，至于它是否可以运行，则有**Execution Engine**决定。
- 加载的类信息存放于一块称为方法区的内存空间。除了类信息外，方法区中还会存放**运行时常量池**信息，可能还包括<u>字符串面量和数字常量</u>（Class文件中常量池部分的内存映射，也就是<u>class文件中常量池加载到内存中就是运行时常量池</u>）。

举例说明：

![](images/image-20220307095410512.png) 

1. class file存在于本地硬盘上，可以理解为设计师在纸上的模板，而最终这个模板在执行的时候是要加载到JVM当中来根据这个文件实例化n个一模一样的实例。
2. class file 加载到JVM中，被称为**DNA元数据模板**，放在方法区。
3. 在.class文件 -> JVM -> 最终成为元数据模板，此过程就要一个运输工具（类装载器Class Loader），扮演一个快递员的角色。

```java
public class HelloLoader {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
```

用流程图表示上述示例代码：

![](images/image-20220307100714192.png)

**类的加载过程**分为三个环节：加载 -> 链接 -> 初始化

![](images/image-20220307100808445.png)

#### 类的加载过程一：加载（loading）

> 【类的加载过程】是一个大的概览，其中第一步也叫加载。

1. 通过一个类的**全限定名**获取定义此类的**二进制字节流**
2. 将这个字节流所代表的静态存储结构转化为方法区的运行时数据结构（7及其之前叫**永久代**，之后叫**元空间**）
3. **在内存中生成一个代表这个类的java.lang.Class对象**，作为方法区这个类的各种数据的访问入口

补充：加载.class文件的方式

- 从本地系统中直接加载
- 通过网络获取，典型场景：Web Applet
- 从zip压缩包中读取，成为日后jar、war格式的基础
- 运行时计算生成，使用最多的是：动态代理技术
- 由其它文件生成，典型场景：JSP应用
- 从专有数据库中提取.class文件，比较少见
- 从加密文件中获取，典型的防Class文件被反编译的保护措施

#### 类的加载过程二：链接（Linking）

**jclasslib bytecode viewer**，查看.class文件的工具

**Binary Viewer**   **UltraEdit**

都有IDEA插件

主要优点：

1 不需要使用javap指令，使用简单

2 点击字节码指令可以跳转到 java虚拟机规范对应的章节



魔术，所有能够被Java虚拟机识别字节码文件的有效开头都是（“咖啡宝贝”）：

```
CA FE BA BE
```



1. 验证（Verify）

   目的在于确保Class文件的字节流中包含信息符合当前虚拟机**要求**，保证被加载类的**正确性**，不会危害虚拟机自身安全。

   主要包括四种验证：**文件格式验证，元数据验证，字节码验证，符号引用验证**。

2. 准备（Prepare）：

   为类变量分配内存并且设置该类==变量==的默认初始值，即零值。

   ```JAVA
   private static int a = 1;  // prepare: a = 0  --> initial: a = 1
   ```

   **这里不包含用final修饰的static，因为final在编译的时候就会分配了（常量），准备阶段会显示初始化**；

   **这里不会为实例变量分配初始化**，类变量会分配在方法区，而实例变量是会随着对象一起分配到Java堆中。

3. 解析（Resolve）：

   将常量池内的**符号引用**转换为**直接引用**的过程。

   事实上，解析操作往往会伴随着JVM在执行完<u>初始化之后再执行</u>。

   **符号引用**就是一组符号来描述所引用的目标。符号引用的字面量形式明确定义在《java虚拟机规范》的Class文件格式中。（理解为字符串，能根据这个字符串定位到指定的数据，比如java/lang/StringBuilder）

   ![](images/image-20230410112306113.png)

   直接引用就是直接指向目标的指针、相对偏移量或一个间接定位到目标的句柄。（内存地址）
   
   解析动作主要针对<u>类或接口、字段、类方法、接口方法、方法类型</u>等。对应常量池中的CONSTANT_Class_info、CONSTANT_Fieldref_info、CONSTANT_Methodref_info等。



#### 类的加载过程三：初始化（initialization）

- 初始化阶段就是执行**==类构造器方法==**`<clinit>()`的过程。（注意**==实例构造器==**`＜init＞()`区分）

- 此方法不需定义，是javac编译器自动收集类中的所有类变量的赋值动作和静态代码块中的语句合并而来。（如果没有类变量和静态块，就没有类构造器方法）

  ![](images/image-20230410120159579.png)

- 构造器方法中指令按语句在<u>源文件中出现的顺序执行</u>。

- `<clinit>()`不同于类的构造器。（关联：构造器是虛拟机视角下的`<init>()`)

- 若该类具有父类，JVM会保证子类的`<clinit>()`执行前，父类的`<clinit>()`己经执行完毕。

​	![](images/image-20230410121806130.png)

- 虚拟机必须保证一个类的`<clinit>()`方法在多线程下被同步加锁。

  ```java
  /**
   * 只有一个线程执行DeadThread的类构造器<clinit>()
   * @author andyron
   **/
  public class DeadThreadTest {
      public static void main(String[] args) {
          Runnable r = () -> {
              System.out.println(Thread.currentThread().getName() + "开始");
              DeadThread dead = new DeadThread();
              System.out.println(Thread.currentThread().getName() + "结束");
          };
          Thread t1 = new Thread(r, "线程1");
          Thread t2 = new Thread(r, "线程2");
          t1.start();
          t2.start();
      }
  }
  class DeadThread {
      static {
          if (true) {
              System.out.println(Thread.currentThread().getName() + "初始化当前类");
              while (true) {
              }
          }
      }
  }
  ```

  



![](images/image-20230410113901043.png)

![](images/image-20230410114111413.png)

![](images/image-20230410114746813.png)

### 类加载器的分类

- JVM支持两种类型的类加载器，分别为**引导类加载器 (Bootstrap ClassLoader）**和**自定义类加载器 (User-Defined classLoader)**。

- 从概念上来讲，自定义类加载器一般指的是程序中由开发人员自定义的一类类加载器，但是Java虚拟机规范却没有这么定义，而是将所有派生于抽象类`Classloader`的类加载器都划分为自定义加载器。

- 无论类加载器的类型如何划分，在程序中我们最常见的类加载器始终只有3个，如下所示：

![](images/image-20230410141735170.png)

```java
sun.misc.Launcher$ExtClassLoader
sun.misc.Launcher$AppClassLoader
```

```java
public class ClassLoaderTest {
    public static void main(String[] args) {
        // 获取启动类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);  // sun.misc.Launcher$AppClassLoader@18b4aac2

        // 获取其上层：扩展类加载器
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader);  // sun.misc.Launcher$ExtClassLoader@6bc7c054

        // 获取其上层：获取不到引导类加载器
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println(bootstrapClassLoader);  // null

        // 对于用户自定义来说：默认使用系统类加载器进行加载
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);  // sun.misc.Launcher$AppClassLoader@18b4aac2

        // String类使用引导类加载器进行加载的  ---> Java的核心类库是使用引导类加载器进行加载的
        ClassLoader classLoader1 = String.class.getClassLoader();
        System.out.println(classLoader1);   // null
    }
}
```



#### 启动类加载器（引导类加载器，Bootstrap ClassIoader)

- 这个类加载使用C/C++语言实现的，嵌套在JVM内部。

- 它用来加载Java的校心库 (JAVA HOME/jre/lib/rt.jar、resources.jar或sun.boot.ciass.path路径下的内容)，用于提供JVM自身需要的类

- 并不继承自java.lang.classLoader，没有父加载器。

- 加载扩展类和应用程序类加载器，并指定为他们的父类加载器。

- 出于交全考虑，Bootstrap启动类加载器只加载包名为java、javax、sun等开头的类

#### 扩展类加载器 (Extension ClassLoader)

- Java语言编写，由`sun.misc.Launcher$ExtclassLoader`实现。

- 派生于ClassLoader类

- 父类加载器为启动类加载器

- 从`java.ext.dirs`系统属性所指定的目录中加载类库，或从JDK的安装目录的`jre/lib/ext`子目录（扩展目录）下加载类库。<u>如果用户创建的JAR放在此目录下，也会自动由扩展类加载器加载</u>。

#### 应用程序类加载器（系统类加载器，AppClassLoader）

- Java语言编写，由`sun.misc.Launcher$AppclassLoader`实现

- 派生于ClassLoader头

- 父类加载器为扩展类加载器

- 它负责加载环境变量classpath或系统属性`java.class.path`指定路径下的类库

- 该类加载是==程序中默认的类加载器==，一般来说，Java应用的类都是由它来完成加载

- 通过`ClassLoader#getsystemclassLoader()`方法可以获取到该类加载器

#### 用户自定义类加载器

- ﻿在Java的日常应用程序开发中，类的加载几乎是由上述3种类加载器相互配合执行的，在必要时，我们还可以自定义类加载器，来定制类的加载方式。
- ﻿为什么要自定义类加裁器？
  + 隔离加载类
  + 修改类加载的方式
  + 扩展加载源
  + 防止源码泄露。加密后再自定义加载器中实现解密

用户自定义类加载器实现步骤：

1. 开发人员可以通过继承抽象类`java.lang.ClassLoader`类的方式，实现自己的类加载器，以满足一些特殊的需求；

2. 在JDK1.2之前，在自定义类加载器时，总会去继承CLassLoader类并重写loadClass()方法，从而实现自定义的类加载类，但是在JDK1.2之后己不再建议用户去覆盖loadClass()方法，而是建议把自定义的类加载逻辑写在`findclass()`方法中；

3. 在编写自定义类加载器时，如果没有太过于复杂的需求，可以直接继承`URLClassLoader`类，这样就可以避免自己去编写findClass()方法及其获取字节码流的方式，使自定义类加载器编写更加简洁。

### 关于ClassLoader

ClassLoader，抽象类，除了启动类加载器的所有类加载器都是继承自它。

![](images/image-20230410153147055.png)

![](images/image-20230410153258156.png)

`sun.misc.Laucher`是一个Java虚拟机的入口应用。

### 获取ClassLoader的途径

![](images/image-20230410153540866.png)

### 双亲委派机制

Java虛拟机对class文件采用的是按需加载的方式，也就是说当需要使用该类时才会将它的class文件加载到内存生成class对象。而且加载某个类的class文件时，Java虚拟机来用的是双亲委派模式，即把请求交由父类处理，它是一种任务委派模式。

工作原理：

1. 如果一个类加载器收到了类加载请求，它并不会自己先去加载，而是把这个请求委托给父类的加载器去执行；

2. 如果父类加载器还存在其父类加载器，则进一步向上委托，依次递归，请求最终将到达顶层的启动类加载器；

3. 如果父类加载器可以完成类加载任务，就成功返回，倘若父类加载器无法完成此加载任务，子加载器才会尝试自己去加载，这就是==双亲委派模式==（parents delegation model）。

![](images/image-20230410154427375.png)

![](images/image-20230410160043948.png)

虽然自定了`java.lang.String`，但最终还是由引导类加载器加载jdk中的。

![](images/image-20230410155758566.png)

```java
 				/*
        默认你系统类加载器询问上一级扩展类加载器：你需要加载StringTest；
        扩展类加载器询问上一级启动类加载器：你需要加载StringTest；
        启动类加载器说不需要 -> 扩展类加载器也说不需要 -> 系统类加载器说那还是我来吧
         */
        StringTest test = new StringTest();
        System.out.println(test.getClass().getClassLoader());
```



![](images/image-20230410160936191.png)

spi相关接口有引导类加载器加载，实现类有系统类加载器加载。

#### 优势

- 避免类的重复加载
- 保护程序安全，防止核心API被随意篡改

```
java.lang.SecurityException: Prohibited package name: java.lang
```



### 沙箱安全机制

自定义String类，但是在加载自定义String类的时候会率先使用引导类加载器加载，而引导类加载器在加载的过程中会先加载jdk自带的文件(rt.jar包中`java\lang\String.class`)，报错信息说没有main方法，就是因为加载的是rt.jar包中的String类。这样可以保证对java核心源代码的保护，这就是==沙箱安全机制==。

### 其它

- ﻿在JVM中表示两个class对象是否为同一个类存在两个必要条件：
  - 类的完整类名必须一致，包括包名。
  - 加载这个类的ClassLoader (指CLassLoader实例对象）必须相同。
- ﻿换句话说，在JVM中，即使这两个类对象(class对象)来源同一个Class文件，被同一个虚拟机所加载，但只要加载它们的ClassLoader实例对象不同，那么这两个类对象也是不相等的。



JVM必须知道一个类型是由启动加载器加载的还是由用户类加载器加载的。如果一个类型是由用户类加载器加载的，那么JVM会**将这个类加载器的一个引用作为类型信息的一部分保存在方法区中**。<u>当解析一个类型到另一个类型的引用的时候，JVM需要保证这两个类型的类加载器是相同的。</u>



Java程序对类的使用方式分为：主动使用和被动使用。

主动使用（就是进行初始化了，类变量和静态类块运行了），又分为七种情况：

- 创建类的实例
- 访问某个类或接口的静态变量，或者对该静态变量赋值
- 调用类的静态方法
- 反射（比如： `Class.forName ("com.andyron.Test")`)
- 初始化一个类的子类
- Java虚拟机启动时被标明为启动类的类
- JDK 7开始提供的动态语言支持：
   `java.lang.invoke.MethodHandle`实例的解析结果
   REF_getstatic、 REF_putstatic、 REE_invokestatic句柄对应的类没有初始化，则初始化

除了以上七种情况，其他使用Java类的方式都被看作是对类的被动使用，都不会导致类的初始化。

## 3 运行时数据区概述及线程

内存是非常重要的系统资源，是硬盘和CPU的中间仓库及桥梁，承载着操作系统和应用程序的实时运行。JVM内存布局规定了Java在运行过程中内存申请、分配、管理的策略，保证了JVM的高效稳定运行。不同的JVM对于内存的划分方式和管理机制存在着部分差异。结合JVM虚拟机规范，来探讨下经典的JVM内存布局。

![](images/image-20230410195822828.png)

Java虚拟机定义了若干种程序运行期间会使用到的运行时数据区，其中有一些会随者虛拟机启动而创建，随着虛拟机退出而销毁。另外一些则是与线程一一对应的，这些与线程对应的数据区域会随着线程开始和结束而创建和销毀。

灰色的为单独线程私有的，红色的为多个线程共享的。即：

- 每个线程：独立包括程序计数器、栈、本地栈。
- 线程间共享：堆、堆外内存（永久代或元空间、代码缓存）

![](images/image-20230410200630842.png)

垃圾回收95%在堆区，5%在方法区。

一个JVM实例就对应一个`java.lang.Runtime`实例。

### 线程

- 线程是一个程序里的运行单元。JVM允许一个应用有多个线程并行的执行。

- 在Hotspot JVM里，<u>每个线程都与操作系统的本地线程直接映射</u>。

  当一个Java线程准备好执行以后，此时一个操作系统的本地线程也同时创建。Java线程执行终止后，本地线程也会回收。

- 操作系统负责所有线程的安排调度到任何一个可用的CPU上。一旦本地线程初始化成功，它就会调用Java线程中的run()方法。

守护线程、普通线程



- 如果你使用jconsole或者是任何一个调试工具，都能看到在后台有许多线程在运行。这些后台线程不包括调用`public static void main (string [])`的main线程以及所有这个main线程自己创建的线程。

- 这些主要的后台系统线程在Hotspot JVM里主要是以下儿个：
  + 虚拟机线程：这种线程的操作是需要JVM达到安全点才会出现。这些操作必须在不同的线程中发生的原因是他们都需要JVM达到安全点，这样堆才不会变化。这种线程的执行类型包括“stop-the-world的垃圾收集，线程栈收集，线程挂起以及偏向锁撒销。
  + 周期任务线程：这种线程是时间周期事件的体现（比如中断），他们一般用于周期性操作的调度执行。
  + GC线程：这种线程对在JVM里不同种类的垃圾收集行为提供了支持。
  + 编译线程：这种线程在运行时会将字节码编译成到本地代码。
  + 信号调度线程：这种线程接收信号并发送给JVM，在它内部通过调用适当的方法进行处理。

## 4 程序计数器（PC寄存器）

>  GC、OOM都没有  

### 4.1 介绍

[The pc Register](https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-2.html#jvms-2.5.1)

JVM中的程序计数寄存器 (Program counter Register）中， Register的命名源于CPU的寄存器，寄存器存储指令相关的现场信息。CPU只有把数据装载到寄存器才能够运行。

这里，并非是广义上所指的物理奇存器，或许将其翻译为**PC计数器**(或指令计数器）会更加贴切(也称为**程序钩子**），并且也不容易引起一些不必要的误会。JVM中的PC寄存器是对物理PC寄存器的一种抽象模拟。



作用：PC寄存器用于存储指向下一条指令的地址，也即将要执行的指令代码。由执行引擎读取下一条指令。

![](images/image-20230410202613901.png)

- 它是一块很小的内存空间，几乎可以忽略不记。也是运行速度最快的存储区域。

- 在JVM规范中，每个线程都有它自己的程序计数器，是线程私有的，生命周期与线程的生命周期保持一致。

- 任何时间一个线程都只有一个方法在执行，也就是所谓的**当前方法**。程序计数器会存储当前线程正在执行的Java方法的JVM指令地址；或者，如果是在执行native方法，则是未指定值 (undefined)。
- 它是程序控制流的指示器。分支、循环、跳转、异常处理、线程恢复等基础功能都需要依赖这个计数器来完成。
- 字节码解释器工作时就是通过改变这个计数器的值来选取下一条需要执行的字节码指令。
- 它是唯一一个在Java虚拟机规范中没有规定任何OutOfMemoryError情况的区域。

```java
    public static void main(String[] args) {
        int i = 10;
        int j = 20;
        int k = i + j;

        String s = "abc";
        System.out.println(i);
        System.out.println(k);
    }
```

![](images/image-20230410204228502.png)



### 两个常见问题

1. 使用PC寄存器存储字节码指令地址有什么用呢？

   为什么使用PC寄存器记录当前线程的执行地址呢？

因为CPU需要不停的切换各个线程，这时候切换回来以后，就得知道接着从哪开始继续

执行。

JVM的字节码解释器就需要通过改变PC奇存器的值来明确下一条应该执行什么样的字节

码指令。

2. PC寄存器为什么会被设定为线程私有？

我们都知道所谓的多线程在一个特定的时间段内只会执行其中某一个线程的方法，CPU会不停地做任务切换，这样必然导致经常中断或恢复，如何保证分毫无差呢？<u>为了能够准确地记录各个线程正在执行的当前字节码指令地址，最好的办法自然是为每一个线程都分配一个PC奇存器</u>，这样一来各个线程之间便可以进行独立计算，从而不会出现相互干扰的情況。

由于CPU时间片轮限制，众多线程在并发执行过程中，任何一个确定的时刻，一个处理器或者多校处理器中的一个内核，只会执行某个线程中的一条指令。

这样必然导致经常中断或恢复，如何保证分毫无差呢？每个线程在创建后，都会产生自己的程序计数器和栈帧，程序计数器在各个线程之间互不影响。

![](images/image-20230410204633163.png)

并行：vs串行

并发：

> CPU时间片
>
> CPU 时间片即 CPU 分配给各个程序的时间，每个线程被分配一个时间段，称作它的时间片。
>
> 在宏观上：我们可以同时打开多个应用程序，每个程序并行不悖，同时运行。
>
> 但在微观上：由于只有一个CPU，一次只能处理程序要求的一部分，如何处理公平，一种方法就是引入时间片，每个程序轮流执行。
>
> ![](images/image-20230410205319704.png)



## 5 虚拟机栈❤️

### 5.1 概述

虚拟机栈出现的背景：

由于跨平台性的设计，Java的指令都是根据栈来设计的。不同平台CPU架构不同，所以不能设计为基于寄存器的。**优点是跨半台，指令集小，编译器容易实现，缺点是性能下降，实现同样的功能需要更多的指令。**



内存中的栈与堆：

**栈是运行时的单位，而堆是存储的单位。**

即：栈解决程序的运行问题，即程序如何执行，或者说如何处理数据。堆解决的是数据存储的问题，即数据怎么放、放在哪儿。



虚拟机栈基本内容

- Java虚拟机栈是什么？

  Java虚拟机栈 (Java Virtual Machine stack)，早期也叫Java栈。每个线程在创建时都会创建一个虛拟机栈，其内部保存一个个的**栈帧(stack Frame）**，对应着一次次的Java方法调用。

  是线程私有的

- ﻿生命周期
  生命周期和线程一致。

- ﻿作用
  主管Java程序的运行，它保存方法的局部变量（8种基本数据类型、对象的引用地址）、部分结果，并参与方法的调用和返回。

  局部变量 vs 成员变量（或属性）

  基本数据类型 vs 引用类型变量（类、数组、接口）

![](images/image-20230411083605659.png)

- 栈的特点（优点）
  + 栈是一种快速有效的分配存储方式，访问速度仅次于程序计数器
  + JVM直接堆Java栈的操作只有两个：每个方法执行，伴随着进栈（入栈、压栈）；执行结束后的出栈工作
  + 不存在垃圾回收问题



栈中可能出现的异常

Java 虚拟机规范允许Java栈的大小是动态的或者是固定不变的。

- 如果采用固定大小的Java虚拟机栈，那每一个线程的Java虚拟机栈容量可以在线程创建的时候独立选定。如果线程请求分配的栈容量超过Java虚拟机栈允许的最大容量，Java虚拟机将会抛出一个`StackoverflowError` 异常。

- 如果Java虚拟机栈可以动念扩展，并且在尝试扩展的时候无法申请到足够的内存，或者在创建新的线程时没有足够的内存去创建对应的虚拟机栈，那Java虛拟机将会抛出一个`OutOfMemoryError` 异常。

- 通过参数`-Xss`选项来设置线程的最大栈空间，栈的大小直接决定了函数调用的最大可达深度

```java
/**
 * 演示栈中的异常：StackOverflowError
 * @author andyron
 *
 * 默认情况下：count 19600
 * 设置栈的大小：-Xss640k ，count缩小为为3221
 **/
public class StatckErrorTest2 {
    private static int count = 1;
    public static void main(String[] args) {
        System.out.println(count);
        count++;
        main(args);
    }
}
```

> - 在单个线程下，栈帧太大，或者虚拟机栈容量太小，当内存无法分配的时候，虚拟机抛出StackOverflowError 异常。
> - 不断地建立线程的方式会导致OOM。
> - 如果是StackOverflowError，检查代码是否递归调用方法等
> - 如果是OutOfMemoryError，检查是否有死循环创建线程等

### 5.2 栈的存储单位

#### 栈中存储什么？

每个线程都有自己的栈，栈中的数据都是以栈帧(Stack Frame）的格式存在。在这个线程上正在执行的每个方法都各自对应一个栈帧 (stack Frame）。

栈帧是一个内存区块，是一个数据集，维系着方法执行过程中的各种数据信息。

#### 栈运行原理

- JVM直按对Java栈的操作只有两个，就是对栈帧的压栈和出栈，遵循“先进后出”，“后进先出” 原则。

- 在一条活动线程中，一个时间点上，只会有一个活动的栈帧。即只有当前正在执行的方法的栈帧（栈顶栈帧）是有效的，这个栈帧被称为==当前栈帧(current Frame）==，与当前栈帧相对应的方法就是==当前方法 (Current Method）==，定义这个方法的类就是==当前类(Current Class)==。

- 执行引擎运行的所有字节码指令只针对当前栈帧进行操作。

- 如果在该方法中调用了其他方法，对应的新的栈帧会被创建出来，放在栈的顶端，成为新的当前帧。

![](images/image-20230411100254825.png)

- 不同线程中所包含的栈帧是不允许存在相互引用的，即不可能在一个栈帧之中引用另外一个线程的栈帧。
- 如果当前方法调用了其他方法，方法返回之际，兰前栈帧会传口此方法的技行結果给前一个栈帧，接着，虛拟机会丢弃当前栈帧，使得前一个栈帧重新成为当前栈帧。
- Java方法有两种返回函数的方式，==一种是正常的函数返回，使用return指令；另外一种是抛出异常。不管使用哪种方式，都会导致栈帧被弹出。==



> 返回不同的类型在字节码有不同的返回指令：
>
> ```
> 46: return
> 28: ireturn
> 21: dreturn
> ```

#### 栈帧的内部结构

每个栈帧中存储着：

- ﻿==局部变量表 (Local Variables)==
- ﻿==操作数栈 (operand stack）（或表达式栈）==
- ﻿动态链接 (Dynamic Linking）（或指向运行时常量池的方法引用）
- 方法返回地址(Return Address）（或方法正常退出或异常退出的定义)
- 一些附加信息

![](images/image-20230411104858445.png)

栈帧的大小决定了，栈中可以存放多少栈帧。

> 有的地方把后三个统称为**帧数据区**。

### 5.3 局部变量表

- 局部变量表(Local Variables)也被称之为局部变量数组或本地变量表

- 定义为一个==数字数组==，主要用于存储方法参数和定义在方法体内的局部变量，这些数据类型包括各类基本数据类型、对象引用(reference），以及returnAddress类型。

- 由于局部变量表是建立在线程的栈上，是线程的私有数据，因此**不存在数据安全问题**

- 局部变量表所需的容量大小是在**编译期确定**下来的，并保存在方法的Code属性的maximum local variables数据项中。**在方法运行期间是不会改变**局部变量表的大小的。

```java
    public static void main(String[] args) {
        LocalVariablesTest test = new LocalVariablesTest();
        int num = 10;
        test.test1();
    }
```

`javap -v `

![](images/image-20230411112902872.png)

jclasslib插件查看

![](images/image-20230411113549755.png)

- 方法嵌套调用的次数由栈的大小决定。一般来说，==栈越大，方法嵌套调用次数越多==。对一个函数而言，它的参数和局部变量越多，使得局部变量表膨胀，它的栈帧就越大，以满足方法调用所需传递的信息增大的需求。进而函数调用就会占用更多的栈空间，导致其嵌套调用次数就会减少。

- ==局部变量表中的变量只在当前方法调用中有效==。在方法执行时，虚拟机通过使用局部变量表完成参数值到参数变量列表的传递过程。==当方法调用结束后，随着方法栈帧的销毁，局部变量表也会随之销毁==。



![](images/image-20230411114546973.png)



字节码指令的行号与Java代码行号的对应关系

![](images/image-20230411115759978.png)

#### 关于slot的理解

- 参数值的存放总是在局部变量数组的index0开始，到数组长度-1的索引结束。

- 局部变量表，最基本的存储单元是==Slot（变量槽）==

- 局部变最表中存放编译期可知的各种基本数据类型（8种），引用类型(reference)，returnAddress类型的变量。

- 在局部变量表里，32位以内的类型只占用一个slot（包括returnAddress类型），64位的类型（long和double）占用两个slot。

  byte、short、char 在存储前被转换为int，boolean也被转换为int， 0表示false，非0表示true。

  long 和double 则占据两个slot。

![](images/image-20230411181447659.png)

- JVM会为局部变量表中的每一个Slot都分配一个访问素引，通过这个素引即可成功访问到局部变量表中指定的局部变量值。

- 当一个实例方法被调用的时候，它的方法参数和方法体内部定义的局部变量将会按照顺序被复制到局部变量表中的每一个Slot上。
- 如果需要访问局部变量表中一个64bit的局部变量值时，只需要使用前一个索引即可。（比如：访问long或double类型变量）
- 如果当前帧是由构造方法或者实例方法创建的，那么该对象引用this将会存放在index为0的slot处（静态方法不会），其余的参数按照参数表顺序继续排列。

![](images/image-20230411182028160.png)

![](images/image-20230411182315478.png)

##### Slot的重复使用

栈帧中的局部变量表中的槽位是可以重用的，如果一个局部变量过了其作用域，那么在其作用域之后申明的新的局部变量就很有可能会复用过期局部变量的槽位，从而达到节省资源的目的。

![](images/image-20230411182856166.png)



> java中变量的分类
>
> 按照数据类型分： 
>
> 1. 基本数据类型 
> 2. 引用类型数据
>
> 按照在类中声明的位置分：
>
> 1. 成员变量（在使用前，都经历过**==默认初始化赋值==**）
>
>    - 类变量（静态变量）：linking的prepare阶段：给类变量默认赋值  -->  inital阶段：给类变量显示赋值及静态代码块赋值
>
>    - 实例变量：随着对象的创建，会在堆空间中分配实例变量空间，并进行默认赋值
>
> 2. 局部变量：在使用前，必须进行显示赋值！否则编译不通过。



补充说明

- 在栈帧中，与性能调优关系最为密切的部分就是前面提到的局部变量表。在方法执行时，虚拟机使用局部变量表完成方法的传递。
- ﻿﻿<u>局部变量表中的变量也是重要的**垃圾回收根节点**，只要被局部变量表中直接或间接引用的对象都不会被回收。</u>

### 5.4 操作数栈（Operand stack）

> 栈：可以使用数组或链表来实现。
>
> 操作数栈是用数组实现的。

- 每一个独立的栈帧中除了包含局部变量表以外，还包含一个==后进先出==(Last-In-First-out）的操作数栈，也可以称之为==表达式栈==(Expression stack)。

- 操作数栈，在方法执行过程中，根据字节码指令，往栈中写入数据或提取数据，即入栈 (push)/出栈 (pop)。

  某些字节码指令将值压入操作数栈，其余的字节码指令将操作数取出栈。使用它们后再把结果压入栈

  比如：执行复制、交换、求和等操作

![](images/image-20230411215043308.png)

> 执行引擎会将字节码指令翻译成机器指令。

- 操作数栈主要用于保存计算过程的==中间结果==，同时作为计算过程中变量临时的存储空间。

- 操作数栈就是JVM执行引擎的一个工作区，当一个方法刚开始执行的时候，一个新的栈帧也会随之被创建出来，**这个方法的操作数栈是空的**。

- 每一个操作数栈都会拥有一个明确的**栈深度**（数组的长度）用于存储数值，其所需的最大深度在编译期就定义好了，保存在方法的code属性中，为max_stack的值。

- 栈中的任何一个元素都是可以任意的Java数据类型。
  - 32bit的类型占用一个栈单位深度
  - 64bit的类型占用两个栈单位深度

- 操作数栈==并非采用访问索引的方式来进行数据访问的==，而是只能通过标准的入栈（push）和出栈(pop）操作来完成一次数据访问。

- <u>如果被调用的方法带有返回值的话，其返回值将会被压入当前栈帧的操作数栈中</u>，并更新PC寄存器中下一条需要执行的字节码指令。

- 操作数栈中元素的数据类型必须与字节码指令的序列严格匹配，这由编译器在编译器期间进行验证，同时在类加载过程中的类检验阶段的数据流分析阶段要再次验证。

- 另外，我们说Java虚拟机的==解释引擎是基于栈的执行引擎==，其中的栈指的就是操作数栈。

### 5.5 代码追踪

```java
public class OperandStackTest {
    public void testAddOperation() {
        // byte, short, char, boolean 都是以int存储的
        byte i = 15;
        int j = 8;
        int k = i + j;
    }
}
```



![](images/image-20230411221015160.png)

`istore_1`表示局部变量表1的位置，0的位置是自带this（非静态方法）。

![](images/image-20230411221121492.png)

![](images/image-20230411221348648.png)

`iload_1`和`iload_2`是从局部变量表中1和2位置的数据依次取出放入操作数栈。

![](images/image-20230411221807863.png)

`iadd`操作经过执行引擎把字节码指令翻译成机器指令，经过CPU运算，得到结果23再放入操作数栈顶；然后`istore_3`把23存储到局部变量表23的位置。

![](images/image-20230411222227385.png)

![](images/image-20230411222549790.png)

`bipush`和`sipush`分别表示以byte和short压入，但之后的存储都是以int（`istore`）存储的。



![](images/image-20230411223139316.png)

### 5.6 栈顶缓存技术

前而提过，基于栈式架构的虚拟机所使用的零地址指令更加紧湊，但完成一项操作的时候必然需要使用更多的入栈和出栈指令，这同时也就意味者将需要更多的指令分派 (instruction dispatch）次数和内存读/写次数。

由于操作数是存储在内存中的，因此频繁地执行内存读/写操作必然会影响执行速度。为了解决这个问题，Hotspot JVM的设计者们提出了==栈顶缓存(ToS, Top-of-Stack cashing）技术==，**将栈顶元素全部级存在物理CPU的寄存器中，以此降低对内存的谢/写次数，提升执行引擎的执行效率。**

### 5.7 动态连接（或指向运行时常量池的方法引用）

- 每一个栈帧内部都包含一个指向**运行时常量池**中==该栈帧所属方法的引用==。包含这个引用的目的就是为了支持当前方法的代码能够实现动态链接(Dynamic Linking）。比如：invokedynamic指令。

- 在Java源文件被编译到字节码文件中时，所有的变量和方法引用都作为符号引用 (Symbolic Reference）保存在class文件的常量池里。

  比如：描述一个方法调用了另外的其他方法时，就是通过常量池中指向方法的符号引用来表示的，那么动念链接的作用就是==为了将这些符号引用转换为调用方法的直接引用==。

```java
public class DynamicLinkingTest {
    int num = 10;
    public void methodA() {
        System.out.println("methodA().....");
    }
    public void methodB() {
        System.out.println("methodB().....");
        methodA();
        num++;
    }
}
```



![image-20230411230311468](images/image-20230411230311468.png)

![](images/image-20230411230420882.png)

![](images/image-20230411230903449.png)

> 为什么需要常量池呢？
>
> 为了提供一些符号和常量，便于指令的识别。

🔖p55 字节码文件中需要很多数据支持（父类、int类型、System类型等等），但不能在字节码文件中都写出来，通过符号引用的方式去引用相关结构。

### 5.8 方法的调用





## 6 本地方法接口

P63

## 7 本地方法栈



## 8 堆❤️

P66



## 9 方法区❤️

P87



## 10 对象的实例化内存布局与访问定位

P102

## 11 直接内存

P107

## 12 执行引擎

p110



## 13 StringTable

P118



## 14 垃圾回收概述

p134



## 15 垃圾回收相关算法

P138

## 16 垃圾回收相关概念

P154



## 17 垃圾回收器

P169



# 二、字节码与类的加载篇

p204

## 1.class文件结构



## 2.字节码指令集与解析举例

P238

## 3.类的加载过程（类的生命周期）

P266

## 4.再谈类的加载器



# 三、性能监控与调优篇

P302 - p381

1 概述



2 JVM监控及诊断工具-命令行



3 JVM监控及诊断工具-GUI



4 JVM运行时参数



5 分析GC日志



6 OOM常见各种场景及解决方案



7 性能优化案例



8 Java代码层及其它层面调优



# 四、大厂面试篇

未更新
