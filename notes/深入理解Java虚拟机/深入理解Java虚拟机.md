 《深入理解Java虚拟机（第3版）： JVM高级特性与最佳实践》笔记
------------------

[勘误](https://github.com/fenixsoft/jvm_book)

[Java虚拟机规范 和 Java语言规范](https://docs.oracle.com/javase/specs/index.html)

第3版出版于：2019-12

## 前言

Java的技术体系主要由支撑Java程序运行的**==虚拟机==**、提供各开发领域接口支持的**==Java类库==**、**==Java编程语言==**及许许多多的**==第三方Java框架==**（如Spring、MyBatis等）构成。

虚拟机层面隐藏了底层技术的==复杂性==以及机器与操作系统的==差异性==。

程序员可以把主要精力放在<u>具体业务逻辑</u>，而不是放在保障<u>物理硬件的兼容性</u>上。

绝大多数情况下，提升硬件性能无法等比例提升程序的运行性能和并发能力，甚至有可能对程序运行状况没有任何改善。两个原因：

- 一是JVM为了达到“所有硬件提供一致的虚拟平台”的目的，<u>牺牲了一些硬件相关的性能特性</u>。
- 二是人为原因，如果开发人员不了解虚拟机诸多技术特性的运行原理，就无法写出最适合虚拟机运行和自优化的代码。

商用的高性能JVM都提供了相当多的**==优化参数和调节手段==**，用于满足应用程序在实际生产环境中对性能和稳定性的要求。

# 一、走近Java

## 1 走近Java

> 世界上并没有完美的程序，但我们并不因此而沮丧，因为写程序本来就是一个**不断追求完美**的==过程==。

Java不可忽视的优点：

- 它**摆脱了硬件平台的束缚**，实现了“一次编写，到处运行”的理想；

- 它提供了一种**相对安全的内存管理和访问机制**，避免了绝大部分**内存泄漏和指针越界**问题；

- 它实现了**热点代码检测和运行时编译及优化**，这使得Java应用能随着运行时间的增长而获得更高的性能；

- 它有一套**完善的应用程序接口**，还有无数来自商业机构和开源社区的**第三方类库**来帮助用户实现各种各样的功能……



### Java技术体系

JCP（Java Community Process，也就是“Java社区”) 官方定义的Java技术体系：

- Java程序设计语言
- 各种硬件平台上的**Java虚拟机**实现
- Class文件格式
- Java类库API
- 来自商业机构和开源社区的**第三方Java类库**



**JDK**（Java Development Kit）：Java程序设计语言、Java虚拟机、Java类库。JDK是支持Java程序开发的最小环境，有时JDK就代指Java技术体系。

**JRE**（Java Runtime Environment）：类库API中的**Java SE API**子集和Java虚拟机。JRE是支持Java程序运行的标准环境。

![](images/java-035.jpg)

按照技术所服务的领域，Java技术体系可分为四条**产品线**：

1. Java Card：支持Java小程序（Applets）运行在小内存设备（如智能卡）上的平台。
2. Java ME（Micro Edition）：支持Java程序运行在移动终端（手机、PDA）上的平台，对Java API有所精简，并加入了移动终端的针对性支持，这条产品线在JDK 6以前被称为J2ME。有一点读者请勿混淆，现在在智能手机上非常流行的、主要使用Java语言开发程序的Android并不属于Java ME。
3. Java SE（Standard Edition）：支持面向桌面级应用（如Windows下的应用程序）的Java平台，提供了完整的Java核心API，这条产品线在JDK 6以前被称为J2SE。
4. Java EE（Enterprise Edition）：支持使用多层架构的企业应用（如ERP、MIS、CRM应用）的Java平台，除了提供Java SE API外，还对其做了大量有针对性的扩充，并提供了相关的部署支持，这条产品线在JDK 6以前被称为J2EE，在JDK 10以后被Oracle放弃，捐献给Eclipse基金会管理，此后被称为<u>Jakarta EE</u>。

### Java发展史

![Java技术发展的时间线](images/java-059.jpg)

- 1991年4月，**Oak**。

- 1995年5月23日，Oak语言改名为Java，提出“Write Once，Run Anywhere”。

- 1996年1月23日，JDK 1.0发布，提供了一个纯解释执行的Java虚拟机实现（**Sun Classic VM**）。

- 1997年2月19日，Sun公司发布了JDK 1.1，JAR文件格式、JDBC、JavaBeans、RMI，内部类（Inner Class）和反射（Reflection）等。

- 1998年12月4日，JDK 1.2，拆分为三个方向，内置了**JIT（Just In Time）**即时编译器。

- 1999年4月27日，**HotSpot**虚拟机诞生。

- 2000年5月8日，JDK 1.3，改进Java类库上（如数学运算和新的Timer API等），JNDI。

- 2002年2月13日，JDK 1.4，标志着Java真正**走向成熟**，正则表达式、异常链、NIO、日志类、XML解析器和XSLT转换器等。

- 2004年9月30日，JDK 5，在Java语法易用性上做出了非常大的改进如：自动装箱、泛型、动态注解、枚举、可变长参数、遍历循环（foreach循环）等。

  JDK5改进了Java的内存模型（Java Memory Model，JMM）、提供了`java.util.concurrent`并发包等。

- 2006年12月11日，JDK 6，启用Java EE 6、Java SE 6、Java ME 6的新命名，提供初步的动态语言支持（通过内置Mozilla JavaScript Rhino引擎实现）、提供编译期注解处理器和微型HTTP服务器API，等。

  对Java虚拟机内部做了大量改进，包括锁与同步、垃圾收集、类加载等方面的实现都有相当多的改动。

- 2006年11月13日，Java开源，建立了==OpenJDK==组织

- 2009年4月20日，Oracle收购Sun。

  Java商标正式划归Oracle所有（Java语言本身并不属于哪间公司所有，它由**JCP**组织进行管理，尽管在JCP中Sun及后来的Oracle的话语权很大）。

- 2014年3月18日，JDK 8，Oracle启用**JEP（JDK Enhancement Proposals）**来定义和管理纳入新版JDK发布范围的功能特性。

  JEP 126：对Lambda表达式的支持，这让Java语言拥有了流畅的函数式表达能力。

  JEP 104：内置Nashorn JavaScript引擎的支持。

  JEP 150：新的时间、日期API。

  JEP 122：彻底移除HotSpot的永久代。

  ...

- 2017年9月21日，JDK 9，**Jigsaw**模块化功能。

  Java确实有模块化的刚需，不论是JDK自身（例如拆分出Java SE Embedded这样规模较小的产品）抑或是Java应用都需要用到模块化。

  JDK 9发布后，Oracle随即宣布Java将会<u>以持续交付的形式和更加敏捷的研发节奏向前推进</u>，以后JDK将会在每年的3月和9月各发布一个大版本，目的就是为**避免众多功能特性被集中捆绑到一个JDK版本上而引发交付风险**。

- 2018年3月20日，JDK 10，内部重构，诸如<u>统一源仓库、统一垃圾收集器接口、统一即时编译器接口</u>（JVMCI在JDK 9已经有了，这里是引入新的**Graal即时编译器**）等。

- 2018年9月25日，JDK 11，Oracle同时调整了JDK的授权许可证。【提供长期支持服务（==LTS==, Long-Term-Support）】

  首先，Oracle从JDK 11起把以前的商业特性全部开源给OpenJDK，这样OpenJDK 11和OracleJDK 11的代码和功能，在**本质上就是完全相同的**（官方原文是Essentially Identical）。

  然后，Oracle宣布以后将会同时发行两个JDK：一个是以GPLv2+CE协议下由Oracle发行的OpenJDK（本书后面章节称其为Oracle OpenJDK），另一个是在新的OTN协议下发行的传统的OracleJDK，这两个JDK共享绝大部分源码，在功能上是几乎一样的，核心差异是前者<u>可以免费在开发、测试或生产环境中使用，但是只有半年时间的更新支持</u>；后者<u>个人依然可以免费使用，但若在生产环境中商用就必须付费，可以有三年时间的更新支持</u>。

  如果说由此能得出“Java要收费”的结论，那是纯属标题党，最多只能说Oracle在迫使商业用户要么不断升级JDK的版本，要么就去购买商业支持。

​	![](images/java-11.jpg)

- 2019年3月20日，JDK 12，Switch表达式、Java微测试套件（JMH）等。

- 2019年9月17日，JDK13
- Java 14，2020 年 3 月 17 日
- JDK 15，2020 年 9 月 15 号
- JDK 16，2021 年 3 月 16 号
- JDK 17，2021 年 9 月 14 号。==LTS==

​	![](images/java-17.png)

在Sun掌舵的前十几年里，Java获得巨大成功，同时也渐渐显露出来语言演进的缓慢与社区决策的老朽；而在Oracle主导Java后，引起竞争的同时也带来新的活力，Java发展的速度要显著高于Sun时代。

### Java虚拟机家族

#### 1 虚拟机始祖：Sun Classic/Exact VM

只能使用纯解释器方式来执行Java代码软件

#### 2 武林盟主：HotSpot VM

==热点代码探测技术==，可以通过执行计数器找出最具有编译价值的代码，然后通知即时编译器以方法为单位进行编译。

如果一个方法被频繁调用，或方法中有效循环次数很多，将会分别触发**标准即时编译**和**栈上替换编译（On-Stack Replacement，OSR）**行为。通过编译器与解释器恰当地协同工作，可以在最优化的程序响应时间与最佳执行性能中取得平衡，而且无须等待本地代码输出才能执行程序，即时编译的时间压力也相对减小，这样有助于引入更复杂的代码优化技术，输出质量更高的本地代码。

#### 3 小家碧玉：Mobile/Embedded VM



#### 4 天下第二：BEA JRockit/IBM J9 VM



#### 5 软硬合璧：BEA Liquid VM/Azul VM



#### 6 挑战者：Apache Harmony/Google Android Dalvik VM



#### 7 没有成功，但并非失败：Microsoft JVM及其他



#### 8 百家争鸣

KVM

Java Card VM

Squawk VM

JavaInJava

...

### 展望Java技术的未来

#### 无语言倾向

Java“天下第一”的底气不在于语法多么先进好用，而是来自它**==庞大的用户群和极其成熟的软件生态==**，这在朝夕之间难以撼动。

**Graal VM**

![](images/java-060.jpg)

[Graal VM](https://www.graalvm.org/)被官方称为“Universal VM”和“Polyglot VM”，这是一个在HotSpot虚拟机基础上增强而成的**跨语言全栈虚拟机**，可以作为“任何语言”的运行平台使用，这里“任何语言”包括了Java、Scala、Groovy、Kotlin等==基于Java虚拟机之上的语言==，还包括了==C、C++、Rust等基于LLVM的语言==，同时支持其他像JavaScript、Ruby、Python和R语言等。

Graal VM的基本工作原理是将这些语言的源代码（例如JavaScript）或源代码编译后的==中间格式==（例如LLVM字节码）通过==解释器==转换为能被Graal VM接受的中间表示（Intermediate Representation，IR），譬如设计一个解释器专门对LLVM输出的字节码进行转换来支持C和C++语言，这个过程称为**==程序特化==**（Specialized，也常被称为PartialEvaluation）。

Graal VM提供了**Truffe**工具集来快速构建面向一种新语言的解释器，并用它构建了一个称为Sulong的高性能LLVM字节码解释器。

从更严格的角度来看，Graal VM才是**真正意义上与物理计算机相对应的高级语言虚拟机**，理由是它与物理硬件的指令集一样，做到了只与机器特性相关而不与某种高级语言特性相关。

#### 新一代即时编译器

HotSpot虚拟机中含有两个即时编译器，分别是编译耗时短但输出代码优化程度较低的==客户端编译器==（简称为**C1**）以及编译耗时长但输出代码优化质量也更高的==服务端编译器==（简称为**C2**）。

自JDK10起，引入新的**Graal即时编译器**。

#### 向Native迈进🔖

对不需要长时间运行的，或者小型化的应用而言，Java天生就带有一些劣势。Java的启动时间相对较长，需要预热才能达到最高性能。

AWS Lambda

提前编译（Aheadof Time Compilation，AOT）

Substrate VM

#### 灵活的胖子

HotSpot的定位是**面向各种不同应用场景的全功能Java虚拟机**。

近几年，HotSpot开发团队正在持续地重构着HotSpot的架构，让它具有**模块化的能力和足够的开放性**。

#### 语言语法持续增强

语言的功能特性和语法是相对最不重要的改进点，毕竟连JavaScript这种“反人类”的语法都能获得如此巨大的成功，而比Java语法先进优雅得多的挑战者C#现在已经“江湖日下”，成了末路英雄。



### 实战：自己编译JDK

想要窥探Java虚拟机内部的实现原理，最直接的一条路径就是<u>编译一套自己的JDK，通过阅读和跟踪调试JDK源码来了解Java技术体系的运作</u>，虽然这样门槛会比阅读资料更高一点，但肯定也会比阅读各种文章、书籍来得更加贴近本质。

此外，Java类库里的很多底层方法都是<u>Native的，在了解这些方法的运作过程，或对JDK进行Hack（根据需要进行定制微调）的时候，都需要有能自行编译、调试虚拟机代码的能力。</u>

#### 获取源码

OpenJDK和OracleJDK之间的关系：

![](images/image-20230310122308775.png)



[OpenJDK](http://openjdk.java.net/ )

 

[OpenJDK 12](https://hg.openjdk.java.net/jdk/jdk12/)

![](images/image-20220423165855173.png)

https://jdk.java.net/

#### 系统需求

认真阅读一遍doc/building.html

OpenJDK源码的大小只有不到600MB，要完成编译，过程中会产生大量的中间文件，并且编译出不同优化级别（Product、FastDebug、SlowDebug）的HotSpot虚拟机可能要重复生成这些中间文件，这都会占用大量磁盘空间。

所有的文件，包括源码和依赖项目，都不要放在包含中文的目录里面。

#### 构建编译环境

MacOS，需要MacOS X 10.13以上，并安装好最新版本的XCode和Command Line Tools for XCode，提供OpenJDK所需的**CLang编译器以及Makefile中用到的其他外部命令**。

![](images/image-20230310124801322.png)

#### 进行编译



#### 在IDE工具中进行源码调试



# 二、自动内存管理

## 2 Java内存区域与内存溢出异常

Java与C++之间有一堵由==内存动态分配和垃圾收集==技术所围成的高墙，墙外面的人想进去，墙里面的人却想出来。

C/C++开发者，在内存管理领域，既拥有每一个对象的“所有权”，又担负着每一个对象生命从开始到终结的维护责任。

Java开发者把控制内存的权力交给了Java虚拟机，JVM的==自动内存管理机制==帮助开发者减轻负担，<u>不再需要为每一个new操作去写配对的delete/free代码，不容易出现内存泄漏和内存溢出问题</u>；

但是，<u>一旦出现内存泄漏和溢出方面的问题，如果不了解虚拟机是怎样使用内存的，那排查错误、修正问题将会成为一项异常艰难的工作</u>。

### 2.2 运行时数据区域

Java虚拟机在执行Java程序的过程中会把它所管理的内存划分为若干个不同的数据区域。

这些区域有各自的==用途==，以及==创建和销毁的时间==，有的区域随着虚拟机进程的启动而一直存在，有些区域则是依赖用户线程的启动和结束而建立和销毁。

![](images/image-20220502111048298.png)

#### 程序计数器（线程私有）

**程序计数器（Program Counter Register）**是一块较小的内存空间，它可以看作是当前线程所执行的**==字节码的行号==**指示器。

字节码解释器工作时就是通过改变这个计数器的值来选取下一条需要执行的字节码指令。

由于Java虚拟机的多线程是通过**==线程轮流切换、分配处理器执行时间==**的方式来实现的，在任何一个确定的时刻，一个处理器（对于多核处理器来说是一个内核）都只会执行一条线程中的指令。因此，为了线程切换后能恢复到正确的执行位置，**每条线程都需要有一个独立的程序计数器**，各条线程之间计数器互不影响，独立存储。

如果线程正在执行的是一个Java方法，这个计数器记录的是正在执行的虚拟机**==字节码指令的地址==**；如果正在执行的是本地（Native）方法，这个计数器值则应为**==空（Undefined）==**。



#### Java虚拟机栈（线程私有）

Java虚拟机栈（Java Virtual Machine Stack）的生命周期与线程相同。

虚拟机栈描述的是Java方法执行的线程内存模型：每个方法被执行的时候，Java虚拟机都会同步创建一个==栈帧（Stack Frame）==用于存储**==局部变量表、操作数栈、动态连接、方法出口==**等信息。<u>每一个方法被调用直至执行完毕的过程，就对应着一个栈帧在虚拟机栈中从入栈到出栈的过程。</u>

![](images/image-20220516102318889.png)

==局部变量表==存放了编译期可知的各种Java虚拟机**==基本数据类型==**（boolean、byte、char、short、int、foat、long、double）、**==对象引用==**（reference类型，它并不等同于对象本身，可能是一个指向对象起始地址的引用指针，也可能是指向一个代表对象的句柄或者其他与此对象相关的位置）和**==returnAddress类型==**（指向了一条字节码指令的地址）。它们在局部变量表中存储以**==局部变量槽（Slot）==**来表示，64位的long和double占用两个变量槽，其它只占用一个。

一个方法需要在栈帧中分配多大的局部变量空间是完全确定的，在方法运行期间不会改变。（这里的”大小“是指局部变量槽的数量，但一个变量槽分配32个bit或64个或者更多，完全有具体的虚拟机实现决定）

Java虚拟机栈两类异常状况：

- <u>线程请求的栈深度大于虚拟机所允许的深度</u>，`StackOverflowError`；
- 如果Java虚拟机栈容量可以动态扩展，当栈扩展时无法申请到足够的内存会抛出`OutOfMemoryError`异常。

#### 本地方法栈（线程私有）

本地方法栈（Native Method Stacks）与虚拟机栈所发挥的作用是非常相似的，其区别只是虚拟机栈为虚拟机执行Java方法（也就是字节码）服务，而本地方法栈则是为虚拟机使用到的**本地（Native）方法**服务。

本地方法栈也会在栈深度溢出或者栈扩展失败时分别抛出StackOverflowError和OutOfMemoryError异常。

#### Java堆（线程共享）

Java堆（Java Heap）是虚拟机所管理的内存中==最大==的一块，所有线程共享，虚拟机启动时创建，唯一目的就是**==存放对象实例==**和**==数组==**。

Java堆是垃圾收集器管理的内存区域，有时也被称作“**==GC堆==**”（Garbage Collected Heap）。

由于现代垃圾收集器大部分都是基于**==分代收集理论==**设计的，所以Java堆中经常会出现“新生代”“老年代”“永久代”“Eden空间”“From Survivor空间”“ToSurvivor空间”等名词，但这些并非Java虚拟机具体实现的固有内存布局，更不是《Java虚拟机规范》里对Java堆的进一步细致划分。

如果从分配内存的角度看，所有线程共享的Java堆中可以划分出多个线程私有的==分配缓冲区（Thread Local Allocation Buffer，TLAB）==，以提升对象分配时的效率。

将Java堆细分的目的**只是为了更好地回收内存，或者更快地分配内存**。

Java堆可以处于**物理上不连续的内存空间中，但在逻辑上它应该被视为连续的**。

Java堆大小可通过参数`-Xms`（初始化堆内存大小）和`-Xmx`（最大可分配堆内存大小）设定来扩展。

> `OutOfMemoryError` <- Java堆中没有内存完成实例分配，并且堆也无法再扩展时



#### 方法区（线程共享）

方法区用于存储已被虚拟机加载的**==类信息、常量、静态变量、即时编译器编译后的代码缓存==**等数据。

`OutOfMemoryError`

#### 运行时常量池（方法区的一部分）

Class文件中除了有类的版本、字段、方法、接口等描述信息外，还有一项信息是常量池表（Constant Pool Table），用于存放编译期生成的**各种字面量与符号引用**，这部分内容将在类加载后存放到方法区的运行时常量池（Runtime Constant Pool）中。

Java虚拟机对于Class文件每一部分（自然也包括常量池）的格式都有严格规定。

因为Java语言<u>不要求常量一定只有编译器才能产生</u>，运行期间也可以将新的常量放入池中，所以==运行时常量池== 相比于 ==Class文件常量池==有动态性。（String的`inter()`）

#### 直接内存

直接内存（Direct Memory）<u>并不是虚拟机运行时数据区的一部分</u>，也不是《Java虚拟机规范》中定义的内存区域。但是这部分内存也被频繁地使用，而且也可能导致`OutOfMemoryError`异常出现。

在JDK 1.4中新加入了NIO（New Input/Output）类，引入了一种基于通道（Channel）与缓冲区（Buffer）的I/O方式，它可以使**用Native函数库直接分配堆外内存**，然后通过一个存储在Java堆里面的`DirectByteBuffer`对象作为这块内存的引用进行操作。这样能在一些场景中显著提高性能，因为避免了在Java堆和Native堆中来回复制数据。

显然，本机直接内存的分配不会受到Java堆大小的限制，但是，既然是内存，则肯定还是会受到<u>本机总内存（包括物理内存、SWAP分区或者分页文件）大小以及处理器寻址空间</u>的限制，一般服务器管理员配置虚拟机参数时，会根据实际内存去设置-Xmx等参数信息，但经常忽略掉直接内存，使得各个内存区域总和大于物理内存限制（包括物理的和操作系统级的限制），从而导致动态扩展时出现OutOfMemoryError异常。



### 2.3 HotSpot虚拟机对象探秘

深入探讨一下HotSpot虚拟机在Java堆中对象分配、布局和访问的全过程。

#### 对象的创建

在语言层面上，创建对象通常（例外：<u>复制、反序列化</u>）仅仅是一个==new==关键字而已，而在虚拟机中，对象（文中讨论的对象限于普通Java对象，不包括数组和Class对象等）的创建又是怎样一个过程呢？

当JVM遇到一条字节码new指令时，首先将去检查这个指令的参数是否能在常量池中定位到一个**类的符号引用**，并且检查这个符号引用代表的类是否已被<u>加载、解析和初始化过</u>。如果没有，那必须先执行相应的类加载过程（[7 虚拟机类加载机制](#7 虚拟机类加载机制)）。

> 1.如何划分可用空间

在类加载检查通过后，JVM将为新生对象分配内存，这个过程相当于<u>把一块确定大小的内存块从Java堆中划分出来</u>。

Java堆内存绝对规整的分配方式 -> 🔖“==指针碰撞==”（Bump The Pointer）；

不规整就需要维护一个记录那些内存块可用的列表，这种分配方式叫“==空闲列表==”（Free List）。

分配方式由Java堆是否规整决定，而是否规整又由所采用的**垃圾收集器**是否带有**空间压缩整理（Compact）**的能力决定：

- 使用Serial、ParNew等带压缩整理过程的收集器  —>  指针碰撞
- 使用CMS这种基于清除（Sweep）算法的收集器  ->  空闲列表 

> 2.对象创建在虚拟机中是非常频繁的行为，即使仅仅修改一个指针所指向的位置，在并发情况下也并不是线程安全的，<u>可能出现正在给对象A分配内存，指针还没来得及修改，对象B又同时使用了原来的指针来分配内存的情况</u>。🔖

两种可选解决方案：

- 对分配内存空间的动作进行同步处理——实际上虚拟机是采用CAS配上失败重试的方式保证更新操作的原子性；

- 把内存分配的动作按照线程划分在不同的空间之中进行，即每个线程在Java堆中预先分配一小块内存，叫==本地线程分配缓冲（Thread Local Allocation Buffer，TLAB）==。

  配置是否使用TLAB： `-XX：+/-UseTLAB`。

> 3.（虚拟机）将分配到内存空间（不包括对象头）都初始化为零值。



> 4.对对象进行必要的配置，比如这个<u>对象是哪个类的实例、如何才能找到类的元数据信息、对象的哈希码</u>（实际上对象的哈希码会延后到真正调用Object::hashCode()方法时才计算）、<u>对象的GC分代年龄</u>等信息。
>
> 这些信息存放在对象的==对象头（Object Header）==之中。



> 5.从虚拟机视角看，一个新的对象已经产生；但从Java成视角看，对象创建才刚刚开始 —— 构造函数，即Class文件中的`<init>()`方法还没有执行，所有的字段都为默认的零值，对象需要的其他资源和状态信息也还没有按照预定的意图构造好。



HotSpot虚拟机字节码解释器（bytecodeInterpreter.cpp）中的代码片段：

```c++
// 确保常量池中存放的是已解释的类
if (!constants->tag_at(index).is_unresolved_klass()) {
    // 断言确保是klassOop和instanceKlassOop（这部分下一节介绍）
    oop entry = (klassOop) *constants->obj_at_addr(index);
    assert(entry->is_klass(), "Should be resolved klass");
    klassOop k_entry = (klassOop) entry;
    assert(k_entry->klass_part()->oop_is_instance(), "Should be instanceKlass");
    instanceKlass* ik = (instanceKlass*) k_entry->klass_part();
    // 确保对象所属类型已经经过初始化阶段
    if ( ik->is_initialized() && ik->can_be_fastpath_allocated() ) {
        // 取对象长度
        size_t obj_size = ik->size_helper();
        oop result = NULL;
        // 记录是否需要将对象所有字段置零值
        bool need_zero = !ZeroTLAB;
        // 是否在TLAB中分配对象
        if (UseTLAB) {
            result = (oop) THREAD->tlab().allocate(obj_size);
        }
        if (result == NULL) {
            need_zero = true;
            // 直接在eden中分配对象
retry:
            HeapWord* compare_to = *Universe::heap()->top_addr();
            HeapWord* new_top = compare_to + obj_size;
            // cmpxchg是x86中的CAS指令，这里是一个C++方法，通过CAS方式分配空间，并发失败的
               话，转到retry中重试直至成功分配为止
            if (new_top <= *Universe::heap()->end_addr()) {
                if (Atomic::cmpxchg_ptr(new_top, Universe::heap()->top_addr(), compare_to) != compare_to) {
                    goto retry;
                }
                result = (oop) compare_to;
            }
        }
        if (result != NULL) {
            // 如果需要，为对象初始化零值
            if (need_zero ) {
                HeapWord* to_zero = (HeapWord*) result + sizeof(oopDesc) / oopSize;
                obj_size -= sizeof(oopDesc) / oopSize;
                if (obj_size > 0 ) {
                    memset(to_zero, 0, obj_size * HeapWordSize);
                }
            }
            // 根据是否启用偏向锁，设置对象头信息
            if (UseBiasedLocking) {
                result->set_mark(ik->prototype_header());
            } else {
                result->set_mark(markOopDesc::prototype());
            }
            result->set_klass_gap(0);
            result->set_klass(k_entry);
            // 将对象引用入栈，继续执行下一条指令
            SET_STACK_OBJECT(result, 0);
            UPDATE_PC_AND_TOS_AND_CONTINUE(3, 1);
        }
    }
}
```



#### 对象的内存布局

```mermaid
flowchart LR
a[堆内存] --> b[对象头]
a --> 实例数据
a -.-> 对齐填充
b --> d[存储对象自身的运行时数据<span> Mark Word</span>]
b --> 类型指针
```

##### 对象头

HotSpot虚拟机对象的**对象头**部分包括两类信息。

1. 用于**存储对象自身的运行时数据**，如<u>哈希码（HashCode）、GC分代年龄、锁状态标志、线程持有的锁、偏向线程ID、偏向时间戳</u>等。官方成为==Mark Word==，长度根据位数分别为32个比特和64个比特。🔖

​	

2. **类型指针**，即对象指向它的类型元数据的指针。JVM通过这个指针来确定该对象是哪个类的实例。

##### **实例数据**

即我们在程序代码里面所<u>定义的各种类型的字段内容</u>，无论是从父类继承下来的，还是在子类中定义的字段都必须记录起来。**存储顺序**会受到虚拟机==分配策略==参数（`-XX：FieldsAllocationStyle`参数）和字段在Java源码中**定义顺序**的影响。

HotSpot虚拟机默认的分配顺序为<u>longs/doubles、ints、shorts/chars、bytes/booleans、oops（Ordinary Object Pointers，OOPs）</u>。**相同宽度的字段总是被分配到一起存放**，在满足这个前提条件的情况下，在父类中定义的变量会出现在子类之前。

果HotSpot虚拟机的`+XX：CompactFields`参数值为true（默认就为true），那子类之中较窄的变量也允许插入父类变量的空隙之中，以节省出一点点空间。

##### **对齐填充**

不是必然存在，占位符

#### 对象的访问定位

Java程序会通过栈上的**reference**数据来操作堆上的具体对象。

由于reference类型在《Java虚拟机规范》里面只规定了它是一个指向对象的引用，并<u>没有定义这个引用应该通过什么方式去定位、访问到堆中对象的具体位置</u>，所以对象访问方式也是由虚拟机实现而定的。

主流的访问方式主要有两种：

1. ==句柄==。句柄池，reference中存储的就是对象的句柄地址，而句柄中包含了<u>对象实例数据与类型数据各自具体的地址信息</u>。

   好处：reference中存储的是稳定句柄地址，在对象被移动（垃圾收集时移动对象是非常普遍的行为）时只会改变句柄中的实例数据指针，而**reference本身不需要被修改**。

![](images/image-20220503213233205.png)

2. 直接指针。reference中存储的直接就是对象地址。需要另外考虑如何放置访问类型数据的相关信息。

​	好处：速度更快。

![](images/image-20220503213244945.png)

HotSpot主要使用第二种方式。

### 2.4 实战：OutOfMemoryError异常

在《Java虚拟机规范》的规定里，除了程序计数器外，虚拟机内存的其他几个运行时区域都有发生OutOfMemoryError（下文称**OOM**）异常的可能。

<u>能根据异常的提示信息迅速得知是哪个区域的内存溢出，知道怎样的代码可能会导致这些区域内存溢出，以及出现这些异常后该如何处理。</u>

[jvm参数官方](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/java.html)

新版的IDEA设置的虚拟机启动参数有点变化：

![](images/iShot_2022-05-16_11.26.59.jpg)

#### Java堆溢出

`-Xms20m -Xmx20m`：将堆的最小值-Xms参数与最大值-Xmx参数设置成一样的20MB，可避免堆自动扩展。

`-XX:+HeapDumpOnOutOfMemoryError`：让虚拟机在出现内存溢出异常的时候Dump出当前的内存堆转储快照以便进行事后分析。

```java
/**
 * VM Args：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {

    static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();

        while (true) {
            list.add(new OOMObject());
        }
    }
}
```

没有设置虚拟机启动参数时异常：

```
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at java.base/java.util.Arrays.copyOf(Arrays.java:3720)
	at java.base/java.util.Arrays.copyOf(Arrays.java:3689)
	at java.base/java.util.ArrayList.grow(ArrayList.java:238)
	at java.base/java.util.ArrayList.grow(ArrayList.java:243)
	at java.base/java.util.ArrayList.add(ArrayList.java:486)
	at java.base/java.util.ArrayList.add(ArrayList.java:499)
	at HeapOOM.main(HeapOOM.java:16)
```

设置后多了：

```
java.lang.OutOfMemoryError: Java heap space
Dumping heap to java_pid52263.hprof ...
Heap dump file created [29082846 bytes in 0.170 secs]
```

生成**java_pid52263.hprof**，IDEA可直接打开查看分析，也可通过专业的分析工具（如JProfiler）查看

1. 通过内存映像分析工具（如JProfiler）对Dump出来的堆转储快照进行分析，确认内存中导致OOM的对象是否是必要的，也就是分清是内存泄漏还是内存溢出。

   > ==内存泄漏（Memory Leak）==：malloc或new申请了一块内存，但是没有通过free或delete将内存释放，导致这块内存一直处于占用状态。
   >
   > ==内存溢出（Memory Overflow）==：申请了10个字节的空间，但是你在这个空间写入11或以上字节的数据，就是溢出。

2. 如果是内存泄漏，可进一步通过工具查看泄漏对象到GC Roots的引用链，找到泄漏对象是通过怎样的引用路径、与哪些GC Roots相关联，才导致垃圾收集器无法回收它们，根据泄漏对象的类型信息以及它到GC Roots引用链的信息，一般可以比较准确地定位到这些对象创建的位置，进而找出产生内存泄漏的代码的具体位置。

3. 如果不是内存泄漏，换句话说就是内存中的对象确实都是必须存活的，那就应当检查Java虚拟机的堆参数（-Xmx与-Xms）设置，与机器的内存对比，看看<u>是否还有向上调整的空间</u>。再从代码上检查是<u>否存在某些对象生命周期过长、持有状态时间过长、存储结构设计不合理等情况，尽量减少程序运行期的内存消耗</u>。

#### 虚拟机栈和本地方法栈溢出🔖

HotSpot虚拟机中并<u>不区分虚拟机栈和本地方法栈</u>。`-Xoss`（设置本地方法栈大小）没有效果，栈容量只能由`-Xss`参数来设定。



```java
/**
 * VM Args：-Xss128k
 * @author zzm
 */
public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }
}
```

```shell
stack length:37743
Exception in thread "main" java.lang.StackOverflowError
	at ch2_4_2.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:13)
	at ch2_4_2.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:13)
	at ch2_4_2.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:13)
	...
```





#### 方法区和运行时常量池溢出🔖

`-XX:PermSize`

`-XX:MaxPermSize`

#### 本机直接内存溢出🔖

`-XX:MaxDirectMemorySize`





## 3 垃圾收集器与内存分配策略

> Java与C++之间有一堵由**内存动态分配和垃圾收集技术**所围成的高墙，墙外面的人想进去，墙里面的人却想出来。

### 3.1　概述

垃圾收集（Garbage Collection，GC）

1960，Lisp，内存动态分配和垃圾收集技术，John McCarthy思考垃圾收集要完成的三件事：

- 哪些内存需要回收？
- 什么时候回收？
- 如何回收？

> 为什么我们还要去了解垃圾收集和内存分配？
>
> 需要排查各种内存溢出、内存泄漏问题时，当垃圾收集成为系统达到更高并发量的==瓶颈==时，我们就必须对这些“自动化”的技术实施必要的==监控和调节==。

Java堆和方法区有着很显著的不确定性：**一个接口的多个实现类需要的内存可能会不一样，一个方法所执行的不同条件分支所需要的内存也可能不一样**，只有处于运行期间，我们才能知道程序究竟会创建哪些对象，创建多少个对象，这部分内存的分配和回收是动态的。

### 3.2 对象已死？

在堆里面存放着Java世界中几乎所有的对象实例，垃圾收集器在对堆进行回收前，第一件事情就是要确定这些对象之中==哪些还“存活”着，哪些已经“死去”==（“死去”即不可能再被任何途径使用的对象）了。

#### 引用计数算法

引用计数（Reference Counting）

优点：原理简单，判定效率高

缺点：需要额外的内存空间；有很多==例外==情况要考虑，必须要配合==大量额外处理==才能保证正确地工作。

应用案例：微软COM（Component Object Model）技术、使用ActionScript 3的FlashPlayer、Python语言以及在游戏脚本领域。

主流的Java虚拟机都没有选用引用计数算法来管理内存。

```java
/**
 * 引用计数算法的缺陷 缺陷测试
 * testGC()方法执行后，objA和objB会不会被GC呢？
 *
 * vm options： -verbose:gc -XX:+PrintGCDetails
 * @Author: andyron
 **/
public class ReferenceCountingGC {

    public Object instance = null;

    private static final int _1MB = 1024 * 1024;

    /**
     * 这个成员属性的唯一意义就是占点内存，以便能在GC日志中看清楚是否有回收过
     */
    private byte[] bigSize = new byte[2 * _1MB];

    public static void testGC() {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        // 假设在这行发生GC，objA和objB是否能被回收？
        System.gc();
    }
  
  	public static void main(String[] args) {
    		testGC();
    }
}
```

运行结果（vm options： `-verbose:gc -XX:+PrintGCDetails`）：

```shell
[GC (System.gc()) [PSYoungGen: 8028K->480K(76288K)] 8028K->488K(251392K), 0.0009473 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[Full GC (System.gc()) [PSYoungGen: 480K->0K(76288K)] [ParOldGen: 8K->348K(175104K)] 488K->348K(251392K), [Metaspace: 3281K->3281K(1056768K)], 0.0026178 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
Heap
 PSYoungGen      total 76288K, used 3277K [0x000000076ab00000, 0x0000000770000000, 0x00000007c0000000)
  eden space 65536K, 5% used [0x000000076ab00000,0x000000076ae334e8,0x000000076eb00000)
  from space 10752K, 0% used [0x000000076eb00000,0x000000076eb00000,0x000000076f580000)
  to   space 10752K, 0% used [0x000000076f580000,0x000000076f580000,0x0000000770000000)
 ParOldGen       total 175104K, used 348K [0x00000006c0000000, 0x00000006cab00000, 0x000000076ab00000)
  object space 175104K, 0% used [0x00000006c0000000,0x00000006c00572f0,0x00000006cab00000)
 Metaspace       used 3290K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 350K, capacity 388K, committed 512K, reserved 1048576K
```

**8028K->488K**，意味着虚拟机并没有因为这两个对象互相引用就放弃回收它们，也说明JVM并不是通过引用计数算法来判断对象是否存活的。



#### 可达性分析算法

当前主流的商用程序语言（Java、C#，上溯至前面提到的古老的Lisp）的内存管理子系统，都是通过==可达性分析算法==（Reachability Analysis）来判定对象是否存活的。

基本思路：通过一系列称为“**==GC Roots==**”的根对象作为起始节点集，从这些节点开始，根据引用关系向下搜索，搜索过程所走过的路径称为“**==引用链==**”（Reference Chain），如果某个对象到GC Roots间没有任何引用链相连，或者用图论的话来说就是从GC Roots到这个对象==不可达时==，则证明此对象是不可能再被使用的。

![](images/image-20220517110028202.png)

对象object 5、object 6、object 7虽然互有关联，但是它们到GC Roots是不可达的，因此它们将会被判定为可回收的对象。

在Java技术体系里面，固定可作为GC Roots的对象包括以下几种：

- 在虚拟机栈（栈帧中的本地变量表）中引用的对象，譬如各个线程被调用的方法堆栈中使用到的参数、局部变量、临时变量等。
- 在方法区中类静态属性引用的对象，譬如Java类的引用类型静态变量。
- 在方法区中常量引用的对象，譬如字符串常量池（String Table）里的引用。
- 在本地方法栈中JNI（即通常所说的Native方法）引用的对象。
- **Java虚拟机内部的引用**，如基本数据类型对应的Class对象，一些常驻的异常对象（比如NullPointExcepiton、OutOfMemoryError）等，还有系统类加载器。
- 所有被**同步锁**（`synchronized`）持有的对象。
- 反映Java虚拟机内部情况的==JMXBean==、==JVMTI==中注册的回调、本地代码缓存等。

#### 再谈==引用==

> 引用计数算法和可达性分析算法，判定对象是否存活都和“引用”离不开关系。

JDK 1.2版之前，传统有点狭隘的**引用**的定义：如果reference类型的数据中存储的数值代表的是另外一块内存的起始地址，就称该reference数据是代表某块内存、某个对象的引用。也就是“被引用”或者“未被引用”两种状态。

JDK 1.2版之后，扩充引用的定义，引用强度由强到弱分为：

- **强引用（StronglyRe-ference）**：传统引用

- **软引用（Soft Reference）**：描述一些还有用，但非必须的对象。`SoftReference`

  只被**软引用**关联着的对象，在系统将要发生内存溢出异常前，会把这些对象列进回收范围之中进行第二次回收，如果这次回收还没有足够的内存，才会抛出内存溢出异常。

- **弱引用（Weak Reference）** `WeakReference`

  被弱引用关联的对象只能生存到下一次垃圾收集发生为止。当垃圾收集器开始工作，无论当前内存是否足够，都会回收掉只被弱引用关联的对象。

- **虚引用（Phantom Reference）** 也称为“幽灵引用”或者“幻影引用”。 `PhantomReference`

  一个对象是否有虚引用的存在，完全不会对其生存时间构成影响，也无法通过虚引用来取得一个对象实例。为一个对象设置虚引用关联的唯一目的<u>只是为了能在这个对象被收集器回收时收到一个系统通知</u>。

#### 生存还是死亡？🔖

标记过程

finalize()

F-Queue

```java
/**
 * 此代码演示了两点：
 * 1.对象可以在被GC时自我拯救。
 * 2.这种自救的机会只有一次，因为一个对象的finalize()方法最多只会被系统自动调用一次
 * @author zzm
 */
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("yes, i am still alive :)");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws Throwable {
        SAVE_HOOK = new FinalizeEscapeGC();

        //对象第一次成功拯救自己
        SAVE_HOOK = null;
        System.gc();
        // 因为Finalizer方法优先级很低，暂停0.5秒，以等待它
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead :(");
        }

        // 下面这段代码与上面的完全相同，但是这次自救却失败了
        SAVE_HOOK = null;
        System.gc();
        // 因为Finalizer方法优先级很低，暂停0.5秒，以等待它
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead :(");
        }
    }
}
```



finalize()方法是对象逃脱死亡命运的最后一次机会。



建议尽量避免使用finalize()，因为并不能等同于C和C++语言中的析构函数。

#### 回收方法区

方法区垃圾收集的“性价比”通常也是比较低的：在Java堆中，尤其是在新生代中，对常规应用进行一次垃圾收集通常可以回收70%至99%的内存空间，相比之下，方法区回收囿于苛刻的判定条件，其区域垃圾收集的回收成果往往远低于此。

方法区的垃圾收集主要回收两部分内容：==废弃的常量和不再使用的类型==。

回收废弃常量与回收Java堆中的对象非常类似。常量池中其他类（接口）、方法、字段的符号引用也与此类似。

判定一个类型是否属于“不再被使用的类”的条件就比较苛刻了。需要同时满足：

- 该类所有的实例都已经被回
- 加载该类的类加载器已经被回收
- 该类对应的java.lang.Class对象没有在任何地方被引用，无法在任何地方通过反射访问该类的方法

🔖

`-Xnoclassgc`

`-verbose：class`

`-XX：+TraceClassLoading`

`-XX：+TraceClassUnLoading`

在大量使用反射、动态代理、CGLib等字节码框架，动态生成JSP以及OSGi这类频繁自定义类加载器的场景中，通常都需要Java虚拟机具备类型卸载的能力，以保证不会对方法区造成过大的内存压力。

### 3.3 垃圾收集算法

介绍分代收集理论和几种算法思想及其发展过程。

> 垃圾收集算法的实现涉及大量的程序细节，且各个平台的虚拟机操作内存的方法都有差异，可参考[《垃圾回收算法手册》](https://book.douban.com/subject/26740958/)第2-4章。

从如何判定对象消亡的角度出发，垃圾收集算法可以划分为“==引用计数式垃圾收集==”（Reference Counting GC）和“==追踪式垃圾收集==”（Tracing GC）两大类，这两类也常被称作“==直接垃圾收集==”和“==间接垃圾收集==”。

前者在主流JVM中位涉及，因而主要介绍后者。

#### 分代收集理论

一致的设计**原则**：收集器应该将Java堆<u>划分出不同的区域</u>，然后将回收对象依据其年龄（年龄即对象熬过垃圾收集过程的次数）分配到不同的区域之中存储。

如果一个区域中大多数对象都是朝生夕灭，难以熬过垃圾收集过程的话，那么把它们集中放在一起，每次回收时**只关注如何保留少量存活而不是去标记那些大量将要被回收的对象**，就能以较低代价回收到大量的空间；如果剩下的都是难以消亡的对象，那把它们集中放在一块，虚拟机便可以使用较低的频率来回收这个区域，这就同时兼顾了垃圾收集的时间开销和内存的空间有效利用。

设计者一般至少会把Java堆划分为==新生代（Young Generation）==和==老年代（Old Generation）==两个区域。

🔖

#### 标记-清除算法

1960，最早出现也是最基础的垃圾收集算法是==“标记-清除”（Mark-Sweep）==算法。

算法分为“标记”和“清楚”两个阶段：首先标记出所有需要回收的对象，在标记完成后，统一回收掉所有被标记的对象。

也可以反过来，标记存活的对象，统一回收所有未被标记的对象。**标记过程就是对象是否属于垃圾的判定过程**。

两个缺点：

- 执行效率不稳定
- 内存空间的碎片化问题

![](images/image-20230311170618229.png)

#### 标记-复制算法

为了了解决标记-清除算法面对大量可回收对象时执行效率低的问题，1969年Fenichel提出了一种称为**“半区复制”（Semispace Copying）**的垃圾收集算法，它**将可用内存按容量划分为大小相等的两块，每次只使用其中的一块。当这一块的内存用完了，就将还存活着的对象复制到另外一块上面，然后再把已使用过的内存空间一次清理掉。**

🔖

![](images/image-20230311170639490.png)

#### 标记-整理算法

针对老年代对象的存亡特征，1974年Edward Lueders提出了另外一种有针对性的==“标记-整理”（Mark-Compact）==算法，其中的标记过程仍然与“标记-清除”算法一样，但后续步骤不是直接对可回收对象进行清理，而是**让所有存活的对象都向内存空间一端移动**，然后直接清理掉边界以外的内存。

![](images/image-20230311170653614.png)

🔖

### 3.4 HotSpot的算法细节实现🔖

Java虚拟机实现这些算法时，必须==对算法的执行效率有严格的考量==，才能保证虚拟机高效运行。

#### 根节点枚举



#### 安全点



#### 安全区域



#### 记忆集与卡表



#### 写屏障



#### 并发的可达性分析



### 3.5 经典垃圾收集器

收集算法是内存回收的方法论，垃圾收集器就是内存回收的实践者。

各款经典收集器之间的关系：

![](images/image-20220503215445077.png)

如果两个收集器之间存在连线，就说明它们可以==搭配==使用。

学习各种收集器，==并非为了挑选一个最好的收集器出来，而是为了选择对具体应用最合适的收集器==。

#### Serial收集器

最早的单线程串行垃圾收集器。“单线程”两层意义：

- 只会使用一个处理器或一条收集线程去完成垃圾收集工作

- 它进行垃圾收集时，必须暂停其他所有工作线程，直到它收集结束。

![](images/image-20230311172845493.png)

目前Serial收集器依然是HotSpot虚拟机运行在客户端模式下的==默认新生代收集器==，简单而高效。

#### ParNew收集器

ParNew收集器实质上是Serial收集器的==多线程并行版本==，除了同时使用多条线程进行垃圾收集之外，其余的行为包括Serial收集器可用的所有控制参数（例如：`-XX:SurvivorRatio`、`-XX:PretenureSizeThreshold`、`-XX：HandlePromotionFailure`等）、收集算法、Stop The World、对象分配规则、回收策略等都与Serial收集器完全一致。

![](images/image-20230311173151124.png)

#### Parallel Scavenge收集器

基于标记-复制算法实现的收集器，也是能够并行收集的多线程收集器。表上类似ParNew。

Parallel Scavenge收集器的目标则是达到一个可控制的==吞吐量（Throughput）==。

> 吞吐量就是处理器用于运行用户代码的时间与处理器总消耗时间的比值：
>
> ![](images/image-20230311174823465.png)

#### Serial Old收集器

Serial收集器的老年代版本，使用标记-整理算法

![](images/image-20230311175003659.png)



#### Parallel Old收集器

Parallel Scavenge收集器的老年代版本，支持多线程并发收集，基于标记-整理算法实现。

![](images/image-20230311175047267.png)

#### CMS收集器🔖

==CMS（Concurrent Mark Sweep）==收集器是一种以获取==最短回收停顿时间==为目标的收集器。

目前很大一部分的Java应用集中在互联网网站或者基于浏览器的B/S系统的服务端上，这类应用通常都会较为关注服务的响应速度，希望系统停顿时间尽可能短，以给用户带来良好的交互体验。CMS收集器就非常符合这类应用的需求。

CMS收集器，四个步骤：

1. 初始标记（CMS initial mark）
2. 并发标记（CMS concurrent mark）
3. 重新标记（CMS remark）
4. 并发清除（CMS concurrent sweep）

![](images/image-20230311175318794.png)

#### Garbage First收集器🔖

Garbage First（简称==G1==）收集器是垃圾收集器技术发展历史上的里程碑式的成果，它开创了收集器==面向局部收集的设计思路==和==基于Region的内存布局==形式。

![](images/image-20230311175514033.png)

### 3.6 低延迟垃圾收集器🔖

衡量垃圾收集器的三项最重要的指标是：==内存占用（Footprint）、吞吐量（Throughput）和延迟（Latency）==。

随着计算机硬件的发展，内存和吞吐量不是那么重要，延迟的重要性日益凸显。

![](images/image-20230311175925756.png)

浅色阶段表示必须挂起用户线程，深色表示收集器线程与用户线程是并发工作的。



#### Shenandoah收集器



#### ZGC收集器



### 3.7 选择合适的垃圾收集器

#### Epsilon收集器

G1、Shenandoah或者ZGC这些越来越复杂、越来越先进。

Epsilon“反其道而行”

#### 收集器的权衡



#### 虚拟机及垃圾收集器日志

阅读分析虚拟机和垃圾收集器的日志是处理Java虚拟机内存问题必备的基础技能。

JDK 9后，HotSpot所有功能的日志都收归到了“-Xlog”参数上：

```
-Xlog[:[selector][:[output][:[decorators][:output-options]]]]
```

命令行中最关键的参数是==选择器（Selector）==，它由==标签（Tag）==和==日志级别（Level）==共同组成。

标签可理解为虚拟机中某个功能模块的名字，它告诉日志框架用户希望得到虚拟机哪些功能的日志输出。垃圾收集器的标签名称为“gc”，由此可见，垃圾收集器日志只是HotSpot众多功能日志的其中一项，全部支持的功能模块标签名如下所示：

```
add，age，alloc，annotation，aot，arguments，attach，barrier，biasedlocking，blocks，bot，breakpoint，bytecode，census，class，classhisto，cleanup，compaction，comparator，constraints，constantpool，coops，cpu，cset，data，defaultmethods，dump，ergo，event，exceptions，exit，fingerprint，freelist，gc，hashtables，heap，humongous，ihop，iklass，init，itables，jfr，jni，jvmti，liveness，load，loader，logging，mark，marking，metadata，metaspace，method，mmu，modules，monitorinflation，monitormismatch，nmethod，normalize，objecttagging，obsolete，oopmap，os，pagesize，parser，patch，path，phases，plab，preorder，promotion，protectiondomain，purge，redefine，ref，refine，region，remset，resolve，safepoint，scavenge，scrub，setting，stackmap，stacktrace，stackwalk，start，startuptime，state，stats，stringdedup，stringtable，subclass，survivor，sweep，system，task，thread，time，timer，tlab，unload，update，verification，verify，vmoperation，vtables，workgang
```

🔖

![](images/image-20230311175927756.png)

#### 垃圾收集器参数总结

![垃圾收集相关的常用参数](images/垃圾收集相关的常用参数.png)

### 3.8 实战：内存分配与回收策略🔖🔖

#### 对象优先在Eden分配



#### 大对象直接进入老年代



#### 长期存活的对象将进入老年代



#### 动态对象年龄判定



#### 空间分配担保



## 4 虚拟机性能监控、故障处理工具

### 4.1 概述

==给一个系统定位问题的时候，知识、经验是关键基础，数据是依据，工具是运用知识处理数据的手段。==

这里说的数据包括但不限于**==异常堆栈、虚拟机运行日志、垃圾收集器日志、线程快照==（threaddump/javacore文件）、==堆转储快照==（heapdump/hprof文件）**等。

==恰当地==使用虚拟机故障处理、分析的工具可以提升分析数据、定位并解决问题的效率。

==工具永远都是知识技能的一层包装==，没有什么工具是“秘密武器”，拥有了就能“包治百病”。

### 4.2 基础故障处理工具

根据软件可用性和授权的不同，划分三类

- 商业授权工具
- 正式支持工具
- 实验性工具

这些工具体积比较小，真正的功能代码是实现在JDK的工具类库中(`jdk-home/jmods/`)。

JDK开发团队选择采用Java语言本身来实现这些故障处理工具是有特别用意的🔖

#### jps：虚拟机进程状况工具

JDK的很多小工具的名字都参考了UNIX命令的命名方式。

**jps（JVM ProcessStatus Tool）**，功能类似`ps`命令：列出正在运行的虚拟机进程，并显示虚拟机执行主类（Main Class，main()函数所在的类）名称以及这些进程的==本地虚拟机唯一ID（LVMID==，LocalVirtual Machine Identifier）。

> 对于本地虚拟机进程来说，LVMID与操作系统的进程ID（PID，Process Identifier）是一致的。

```
jps [options] [hostid]


-q	只输出LVMID，省略主类的名称
-m	输出虚拟机进程启动时传递给主类 main()函数的參数
-l	输出主类的全名，如果进程执行的是JAR 包，则输出 JAR 路径
-v 	输出虚拟机进程启动时的 JVM 参数
```




#### jstat：虚拟机统计信息监视工具

jstat（JVM Statistics Monitoring Tool），用于监视虚拟机==各种运行状态信息==。可以显示本地或者远程虚拟机进程中的**类加载、内存、垃圾收集、即时编译**等运行时数据。

格式：

```
jstat [ option vmid [interval[s|ms] [count]] ]
```

> interval和count代表查询间隔和次数，如果省略这2个参数，说明只查询一次。
>
> 本地虚拟机进程，VMID与LVMID是一致的；如果是远程虚拟机进程，那VMID的格式应当是：
>
> ```
> [protocol:][//]lvmid[@hostname[:port]/servername]
> ```

每250毫秒查询一次进程78335的垃圾收集状况，总共查询10次：

```
jstat -gc 78325 250 10
```

option代表用户希望查询的虚拟机信息，主要分为三类：**类加载、垃圾收集、运行期编译状况**。主要选项有：

- -class	监视类装载、卸载数量、总空间及类装载所耗费的时间
- -gc  监视Java堆状况，包括Eden区、2个Survivor区、老年代、永久代等的容量，已用空间，垃圾收集时间合计等信息

- -gccapacity  监视内容与-gc基本相同，但输出主要关注Java堆各个区域使用到的最大和最小空间

- -gcutil  监视内容与-gc基本相同，但输出主要关注已使用空间占总空间的百分比

- -gccause   与-gcutil功能一样，但是会额外输出导致上一次GC产生的原因

- -gcnew   监视新生代GC的状况

- -gcnewcapacity  监视内容与-gcnew基本相同，输出主要关注使用到的最大和最小空间

- -gcold  监视老年代GC的状况

- -gcoldcapacity  监视内容与——gcold基本相同，输出主要关注使用到的最大和最小空间

- -gcpermcapacity  输出永久代使用到的最大和最小空间

- -compiler  输出JIT编译器编译过的方法、耗时等信息

- -printcompilation  输出已经被JIT编译的方法



```shell
$ jstat -gcutil 78325
  S0     S1     E      O      M     CCS    YGC     YGCT    FGC    FGCT    CGC    CGCT     GCT
  0.00   0.00   3.02   4.86  97.68  94.19      2    0.032     1    0.014     -        -    0.046
```

- 2个Survivor区（S0、S1，表示Survivor0、Survivor1）里面都是空的
- 新生代Eden区（E，表示Eden）使用了3.02%的空间

- 老年代（O，表示Old）和元空间（M 代表Metaspace）则分别使用了4.86%和97.68%的空间。永久代（P，表示Permanent）jdk8之后移除。

- 程序运行以来共发生Minor GC（==YGC==，表示Young GC）2次，总耗时0.032秒；

  发生Full GC（==FGC==，表示Full GC）1次，总耗时（FGCT，表示Full GC Time）为0.014秒；

  所有GC总耗时（==GCT==，表示GC Time）为0.046秒。

> 可视化的监视工具：JMC、VisualVM等

#### jinfo：Java配置信息工具

jinfo（Configuration Info for Java）的作用是==实时查看和调整虚拟机各项参数==。

```
jinfo [ option ] pid
```

`-v`参数可以查看虚拟机启动时**显式指定的参数列表**。

```
jinfo -v 78325
```

`-flag`，如果想知道未被显式指定的参数的系统默认值，除了去找资料外，就只能通过jinfo的-flag选项查询。也可以使用-flag[+|-]name或者-flag name=value在运行期修改一部分运行期可写的虚拟机参数值

```shell
$ jinfo -flag CMSInitiatingOccupancyFraction 78325
-XX:CMSInitiatingOccupancyFraction=-1
```

`-sysprops`选项把虚拟机进程的System.getProperties()的内容打印出来

```
jinfo -sysprops 78325
```



#### jmap：Java内存映像工具

jmap（Memory Map for Java）命令用于生成==堆转储快照==（一般称为heapdump或dump文件），还可以查询==finalize执行队列、Java堆和方法区的详细信息==，如空间使用率、当前用的是哪种收集器等。

```
jmap [option] vmid
```

主要选项：

- -dump  生成Java堆转储快照。格式为`-dump:[live,]format=b,file=<filename>`，几种live自参数说明是否值dump出存活对象
- -finalizerinfo  显示在F-Queve 中等待 Finalizer 线程执行 finalize 方法的对象。只在Linux/Solaris平台下有效
- `-heap`  显示 Java 堆详细信息，如使用哪种回收器、参数配置、分代状况等。只在Linux/Solaris 平台下有效
- `-histo`  显示堆中对象统计信息，包括类、实例数量、合计容量
- `-permstat`  以 ClassLoader 为统计口径显示永久代内存状态。只在Linux/Solaris 平台下有效
- -F  当虚拟机进程对 -dump 选项没有响应时，可使用这个选项强制生成 dunp 快照。只在Linux/Solaris 平台下有效

```shell
$ jmap -dump:format=b,file=IDEA.bin  78325
```

```shell
$ jmap -histo  78325
 num     #instances         #bytes  class name
----------------------------------------------
   1:          1736        7333952  [I
   2:         19378        1933480  [C
   3:          1779         738944  [B
   4:          3711         413816  java.lang.Class
   5:         16818         403632  java.lang.String
   6:          4084         315160  [Ljava.lang.Object;
   7:          7814         250048  java.util.concurrent.ConcurrentHashMap$Node
   8:          7152         114432  java.lang.Object
   9:          1011          88968  java.lang.reflect.Method
  10:            64          66400  [Ljava.util.concurrent.ConcurrentHashMap$Node;
  11:          1678          53696  java.util.HashMap$Node
  12:          1204          47808  [S
  13:           635          45720  java.lang.reflect.Field
  14:            80          43520  io.netty.util.internal.shaded.org.jctools.queues.atomic.MpscAtomicArrayQueue
  15:           927          37080  java.util.LinkedHashMap$Entry
  16:           325          36560  [Ljava.util.HashMap$Node;
  
  ....
  
  1382:             1             16  sun.util.locale.provider.SPILocaleProviderAdapter
  1383:             1             16  sun.util.locale.provider.TimeZoneNameUtility$TimeZoneNameGetter
  1384:             1             16  sun.util.resources.LocaleData
  1385:             1             16  sun.util.resources.LocaleData$LocaleDataResourceBundleControl
  Total         89736       12745656
```



#### jhat：虚拟机堆转储快照分析工具

jhat（JVM Heap Analysis Tool），搭配jmap，分析jmap生成的堆转储快照。

jhat内置了一个微型的HTTP/Web服务器，生成堆转储快照的分析结果后，可以在浏览器中查看。

> 用得少，给为其他专业可视化分析工具

#### jstack：Java堆栈跟踪工具🔖

jstack（Stack Trace for Java）命令用于生成虚拟机**==当前时刻的线程快照==**（一般称为threaddump或者javacore文件）。

线程快照就是当前虚拟机内==每一条线程正在执行的方法堆栈的集合==。

生成线程快照的目的通常是定位线程出现**长时间停顿的原因**，如线程间死锁、死循环、请求外部资源导致的长时间挂起等，都是导致线程长时间停顿的常见原因。

```
jstack [ option ] vmid
```

主要选项：

- -F  当正常输出的请求不被响应时，强制输出线程堆栈
- -l  除堆栈外，显示关于锁的附加信息
- -m  如果调用到本地方法的话、可以显示C/C++的堆栈

从JDK 5起，java.lang.Thread类新增了一个getAllStackTraces()方法用于获取虚拟机中所有线程的`StackTraceElement`对象。使用这个方法可以通过简单的几行代码完成jstack的大部分功能。

#### 基础工具总结

- 基础工具：用于支持基本的程序创建和运行

![](images/image-20220504095216955.png)

- 安全：用于程序签名、设置安全测试等

![](images/image-20220504095239198.png)

- 国际化：用于创建本地语言文件

![](images/image-20220504095306354.png)

- 远程方法调用：用于跨Web或网络的服务交互

![](images/image-20220504095423436.png)

- 部署工具：用于程序打包、发布和部署

![](images/image-20220504095454367.png)

- REPL和脚本工具

![](images/image-20220504095555724.png)

- 性能监控和故障处理：用于监控分析Java虚拟机运行信息，排查问题

### 4.3 可视化故障处理工具

JDK提供了四个可视化工具：JConsole、JHSDB、VisualVM和JMC

#### JHSDB：基于服务性代理的调试工具



![](images/image-20230313105651643.png)

JHSDB是一款基于==服务性代理==（Serviceability Agent，SA）实现的进程外调试工具。

服务性代理是HotSpot虚拟机中一组用于**映射Java虚拟机运行信息的、主要基于Java语言（含少量JNI代码）实现的API集合**。





```
-Xmx10m -XX:+UseSerialGC -XX:-UseCompressedOops
```



🔖 Mac 无法进入JHSDB的图形化模式

```
Unable to connect to process ID 64316:
sun.jvm.hotspot.debugger.DebuggerException: Can't attach to the process. Could be caused by an incorrect pid or lack of privileges.
```





#### JConsole：Java监视与管理控制台

JConsole（Java Monitoring and Management Console）是一款基于**JMX**（Java Manage-ment Extensions）的可视化监视、管理工具。

```java
jconsole
```



![](images/image-20230313110711612.png)

##### 内存监控

“内存”页签相当于jstat命令，用于监==视被收集器管理的虚拟机内存（被收集器直接管理的Java堆和被间接管理的方法区）的变化趋势==。

```
-Xms100m -Xmx100m -XX:+UseSerialGC
```

```java
public class JconsoleTest {
    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<OOMObject>();
        for (int i = 0; i < num; i++) {
            // 稍作延时，令监视曲线的变化更加明显
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws Exception {
        fillHeap(1000);
    }

    /**
     * 内存占位符对象，一个OOMObject大约占64KB
     */
    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }
}
```

##### 线程监控🔖

“线程”页签相当于jstack命令，遇到线程停顿的时候可以使用这个页签的功能进行分析。



#### VisualVM：多合-故障处理工具

VisualVM（All-in-One Java Troubleshooting Tool）是功能最强大的运行监视和故障处理程序之一。

VisualVM的性能分析功能比起JProfiler、YourKit等专业且收费的Profiling工具都不遑多让。

优点：不需要被监视的程序基于特殊Agent去运行，因此它的通用性很强，对应用程序实际性能的影响也较小，使得它可以直接应用在生产环境中。

##### 生成、浏览堆转储快照





##### 分析程序性能



##### BTrace动态日志跟踪





#### Java Mission Control：可持续在线的监控工具



#### 4.4 HotSpot虚拟机插件及工具



## 5 调优案例分析与实战

### 5.2　案例分析

#### 大内存硬件上的程序部署策略



#### 集群间同步导致的内存溢出



#### 堆外内存导致的溢出错误



#### 外部命令导致系统缓慢

通过Java的`Runtime.getRuntime().exec()`方法来执行Shell脚本获得系统的一些信息，在Java虚拟机中是非常消耗资源的操作（需要复制一个和当前虚拟机拥有一样环境变量的进程去执行外部命令）。

改为使用Java的API去获取这些信息。



#### 服务器虚拟机进程崩溃



#### 不恰当数据结构导致内存占用过大



#### 由Windows虚拟内存导致的长时间停顿



#### 由安全点导致长时间停顿



### 5.3 实战：Eclipse运行速度调优

#### 调优前的程序运行状态



#### 升级JDK版本的性能变化及兼容问题



#### 编译时间和类加载时间的优化



#### 调整内存设置控制垃圾收集频率



# 三、虚拟机执行子系统

## 6 类文件结构

代码编译的结果从**==本地机器码==**（Native Code）转变为**==字节码==**，是**存储格式**发展的一小步，却是编程语言发展的一大步。

### 6.1 概述

越来越多的程序语言选择了<u>与操作系统和机器指令集无关的、平台中立的格式作为程序编译后的存储格式</u>。

### 6.2 无关性的基石

各种不同平台的Java虚拟机，以及==所有平台都统一支持的程序存储格式==——字节码（Byte Code）是构成平台无关性的基石。

**语言无关性**正在越来越被开发者所重视。

刻意把Java的规范拆分成了《Java语言规范》（The Java Language Specification）及《Java虚拟机规范》（The Java Virtual Machine Specification）两部分（https://docs.oracle.com/javase/specs/）。

实现语言无关性的基础仍然是**虚拟机和字节码存储格式**。

Class文件中包含了Java虚拟机**==指令集、符号表以及若干其他辅助信息==**。

基于安全方面的考虑，《Java虚拟机规范》中要求在Class文件必须应用许多**强制性的语法和结构化约束**，但**==图灵完备==的字节码格式**，保证了任意一门**功能性语言**都可以表示为一个能被Java虚拟机所接受的有效的Class文件。

以Class文件为产品的**==交付媒介==**。

![](images/image-20220706192259956.png)

Java语言中的各种语法、关键字、常量变量和运算符号的语义最终都会由多条字节码指令组合来表达，这决定了**==字节码指令==所能提供的语言描述能力必须比Java语言本身更加强大才行**。

### 6.3 Class类文件的结构

Java技术能够一直保持着非常良好的向后兼容性，**==Class文件结构的稳定==**功不可没。

尽管不同版本的《Java虚拟机规范》对Class文件格式进行了几次更新，但基本上**只是在原有结构基础上新增内容、扩充功能，并未对已定义的内容做出修改**。

**任何一个Class文件都对应着==唯一的一个类或接口的定义信息==**，但是反过来说，类或接口并不一定都得定义在文件里（譬如类或接口也可以动态生成，直接送入类加载器）。

> 其实也有反例，譬如`package-info.class`、`module-info.class`这些文件就属于**完全描述性**的。

Class文件是一组以**==8个字节==**为基础单位的二进制流，各个数据项目**==严格按照顺序==**紧凑地排列在文件之中，中间没有添加任何分隔符。

当遇到需要占用8个字节以上空间的数据项时，则会按照**高位在前**的方式分割成若干个8个字节进行存储。

> **高位在前**，这种顺序称为“==Big-Endian==”，具体顺序是指按高位字节在地址最低位，最低字节在地址最高位来存储数据，它是SPARC、PowerPC等处理器的默认多字节存储顺序，而x86等处理器则是使用了相反的“==Little-Endian==”顺序来存储数据。

Class文件格式采用一种类似于C语言结构体的伪结构来存储数据，只有两种数据类型：

- ==无符号数==属于基本的数据类型，以==u1、u2、u4、u8==来分别代表1个字节、2个字节、4个字节和8个字节的无符号数。可以用来描述==数字、索引引用、数量值或者按照UTF-8编码构成字符串值==。

- ==表==是由多个无符号数或者其他表作为数据项构成的复合数据类型。

  有表的命名都习惯性地以“`_info`”结尾。

  整个Class文件本质上也可以视作是一张表。

  

![数据项按严格顺序排列](images/image-20220504103119099.png)

容量计数器

字节序（Byte Ordering）

哪个字节代表什么含义，长度是多少，先后顺序如何，全部都不允许改变。

#### 魔数与Class文件的版本

每个Class文件的头4个字节被称为**==魔数（Magic Number）==**，它的作用是确定这个文件是否为一个能被虚拟机接受的Class文件。Class文件的魔数是**==0xCAFEBABE==**（咖啡宝贝？）。

> 很多文件格式标准中都有使用魔数来进行身份识别的习惯。

紧接着魔数的4个字节存储的是**Class文件的版本号**：第5和第6个字节是次版本号（Minor Version），第7和第8个字节是主版本号（Major Version）。

![](images/image-20220504103327822.png)

示例（本章之后的也用这个）

```java
package org.fenixsoft.clazz;

public class TestClass {

    private int m;

    public int inc() {
        return m + 1;
    }
}
```

它的Class文件用十六进制编辑器查看（vscode配合插件）：

![](images/image-20220706234826431.png)

前四个字节为0xCAFEBABE，表示次版本号的第5、6个字节值为0x0000，表示主版本号的第7、8字节值为0x0037，也就是十进制55，对应JDK11。

#### 常量池🔖🔖

紧接着主、次版本号之后的是**常量池入口**，常量池可以比喻为Class文件里的**==资源仓库==**，它是Class文件结构中<u>与其他项目关联最多的数据</u>，通常也是<u>占用Class文件空间最大的数据项目之一</u>，另外，它还是在Class文件中第一个出现的表类型数据项目。

由于常量池中常量的数量是<u>不固定</u>的，所以在常量池的入口需要放置一项u2类型的数据，代表**==常量池容量计数值（constant_pool_count）==**。就是第9、10字节值，为0x0013，也就十进19，表示常量池中有19项常量，索引值范围为1~19。

> 常量池容量计数值的计数设计与平常习惯不同，从1开始，这样之后就可以把索引值设置为0用来表示“不引用任何一个常量池项目”的含义。
>
> 对于其他集合类型，包括接口索引集合、字段表集合、方法表集合等的容量计数都是从0开始的。

常量池中主要存放两大类常量：**字面量（Literal）**和**符号引用（SymbolicReferences）**。

字面量比较接近于Java语言层面的常量概念，如**文本字符串、被声明为final的常量值**等。

符号引用则属于编译原理方面的概念，主要包括下面几类常量：

- 被模块导出或者开放的包（Package）
- 类和接口的全限定名（Fully Qualified Name）
- 字段的名称和描述符（Descriptor）
- 方法的名称和描述符
- 方法句柄和方法类型（Method Handle、Method Type、Invoke Dynamic）
- 动态调用点和动态常量（Dynamically-Computed Call Site、Dynamically-Computed Constant）

Java代码在进行Javac编译的时候，并不像C和C++那样有“连接”这一步骤，而是在虚拟机加载Class文件的时候进行**==动态连接==**。

也就是说，在Class文件中不会保存各个方法、字段最终在内存中的布局信息，这些字段、方法的符号引用不经过虚拟机在运行期转换的话是无法得到真正的**内存入口地址**，也就无法直接被虚拟机使用的。当虚拟机做类加载时，将会从常量池获得对应的符号引用，再在类创建时或运行时解析、翻译到具体的内存地址之中。

常量池中每一项常量都是一个表，最初只有11种结构，为了更好地支持动态语言调用添加4种，为了支持java模块化系统提添加2种（CONSTANT_Module_info和CONSTANT_Package_info），截止Jdk13共17种。

![](images/image-20220504103920852.png)



常量池这17种常量类型<u>各自有着完全独立的数据结构</u>，两两之间并没有什么共性和联系。



1. 常量池的第一项常量，它的标志位（偏移地址：0x0000000A）是`0x07`，也就是类型为`CONSTANT_Class_info`，这种类型只有tag、和name_index;

![](images/image-20230313192540405.png)

2. 第二项常量，标志位（偏移地址：0x0000000D）是0x01，类型为`CONSTANT_Utf8_info`。

![](images/image-20220707093816372.png)

第二项常量的length就是 0x001D，也就是29个字节，往后的29个字节表示的字符串内容为：`org/fenixsoft/clazz/TestClass`。

![](images/image-20220707092831829.png)

其余常量，可通过工具javap来分析：

```shell
>javap -verbose TestClass
Compiled from "TestClass.java"
public class org.fenixsoft.clazz.TestClass extends java.lang.Object
    SourceFile: "TestClass.java"
    minor version: 0
    major version: 50
    Constant pool:
const #1 = class        #2;     //  org/fenixsoft/clazz/TestClass
const #2 = Asciz        org/fenixsoft/clazz/TestClass;
const #3 = class        #4;     //  java/lang/Object
const #4 = Asciz        java/lang/Object;
const #5 = Asciz        m;
const #6 = Asciz        I;
const #7 = Asciz        <init>;
const #8 = Asciz        ()V;
const #9 = Asciz        Code;
const #10 = Method      #3.#11; //  java/lang/Object."<init>":()V
const #11 = NameAndType #7:#8;//  "<init>":()V
const #12 = Asciz       LineNumberTable;
const #13 = Asciz       LocalVariableTable;
const #14 = Asciz       this;
const #15 = Asciz       Lorg/fenixsoft/clazz/TestClass;;
const #16 = Asciz       inc;
const #17 = Asciz       ()I;
const #18 = Field       #1.#19; // org/fenixsoft/clazz/TestClass.m:I
const #19 = NameAndType #5:#6;  // m:I
const #20 = Asciz       SourceFile;
const #21 = Asciz       TestClass.java;
```



![常量池中的17种数据类型的结构总表](images/常量池中的17种数据类型的结构总表.png)

UTF-8缩略编码



#### 访问标志

常量池后紧接着的2个字节是**访问标志（access_flags）**，这个标志用于**识别一些类或者接口层次的访问信息**，包括：这个Class是类还是接口；是否定义为public类型；是否定义为abstract类型；如果是类的话，是否被声明为final等等。

![](images/image-20220504104825861.png)

access_flags中一共有16个标志位可以使用，当前只定义了其中9个，没有使用到的标志位要求一律为零。

TestClass是一个普通Java类，不是接口、枚举、注解或者模块，被public关键字修饰但没有被声明为final和abstract，因此它的ACC_PUBLIC、ACC_SUPER标志应当为真，而ACC_FINAL、ACC_INTERFACE、ACC_ABSTRACT、ACC_SYNTHETIC、ACC_ANNOTATION、ACC_ENUM、ACC_MODULE这七个标志应当为假，因此它的access_flags的值应为：`0x0001|0x0020=0x0021`。

![](images/image-20230313194418492.png)

#### 类索引、父类索引与接口索引集合

**类索引（this_class）**和**父类索引（super_class）**都是一个u2类型的数据，而**接口索引集合（interfaces）**是一组u2类型的数据的集合，Class文件中由这三项数据来确定该类型的==继承关系==。

类索引用于确定这个<u>类的全限定名</u>，父类索引用于确定这个类的<u>父类的全限定名</u>。

接口索引集合就用来描述这个类<u>实现了哪些接口</u>，这些被实现的接口将按implements关键字（如果这个Class文件表示的是一个接口，则应当是extends关键字）后的接口顺序从左到右排列在接口索引集合中。

![](images/image-20230313195000888.png)

接口计数器（interfaces_count）

![](images/image-20230313195029488.png)



```java
// 部分常量池内容
const #1 = class        #2;     //  org/fenixsoft/clazz/TestClass
const #2 = Asciz        org/fenixsoft/clazz/TestClass;
const #3 = class        #4;     //  java/lang/Object
const #4 = Asciz        java/lang/Object;
```



#### 字段表集合

字段表（field_info）用于描述**接口或者类中声明的变量**。

Java语言中的“字段”（Field）包括类级变量以及实例级变量，但不包括在方法内部声明的局部变量。

字段可以包括的修饰符<u>有字段的作用域（public、private、protected修饰符）、是实例变量还是类变量（static修饰符）、可变性（final）、并发可见性（volatile修饰符，是否强制从主内存读写）、可否被序列化（transient修饰符）、字段数据类型（基本类型、对象、数组）、字段名称</u>。

上述这些信息中，各个修饰符都是**布尔值**，要么有某个修饰符，要么没有，很适合使用标志位来表示。

![](images/image-20220504105301760.png)



![](images/image-20220707095850332.png)



![](images/image-20220707095943460.png)



![](images/image-20230313195744890.png)

#### 方法表集合

Class文件存储格式中**==对方法的描述与对字段的描述采用了几乎完全一致==**的方式，方法表的结构如同字段表一样，依次包括访问标志（access_flags）、名称索引（name_index）、描述符索引（descriptor_index）、属性表集合（attributes）。

![](images/image-20230313195851338.png)





#### 属性表集合

属性表（attribute_info）

Class文件、字段表、方法表都可以携带自己的属性表集合，以描述某些场景专有的信息。

![虚拟机规范预定义的属性](images/虚拟机规范预定义的属性.png)

##### 1.Code属性

Java程序方法体里面的代码经过Javac编译器处理之后，最终变为字节码指令存储在Code属性内。

![](images/image-20220707100208332.png)

##### 2.Exceptions属性

![](images/image-20220707100344773.png)

##### 3.LineNumberTable属性

描述Java源码行号与字节码行号（字节码的偏移量）之间的对应关系。

![](images/image-20220707100449061.png)

##### 4.LocalVariableTable及LocalVariableTypeTable属性

LocalVariableTable属性用于**描述栈帧中局部变量表的变量与Java源码中定义的变量之间的关系**。

LocalVariableTypeTable与LocalVariableTable类似，仅仅是把**记录的字段描述符的descriptor_index替换成了字段的特征签名（Signature）**。

![](images/image-20230313200153557.png)



![](images/image-20230313200205881.png)

##### 5.SourceFile及SourceDebugExtension属性

SourceFile属性用于记录生成这个Class文件的源码文件名称。

![](images/image-20230313200230208.png)



![](images/image-20230313200249594.png)

##### 6.ConstantValue属性

ConstantValue属性的作用是通知虚拟机自动为静态变量赋值。

![](images/image-20230313200305532.png)

##### 7.InnerClasses属性

InnerClasses属性用于记录内部类与宿主类之间的关联。

![](images/image-20230313200329562.png)

![](images/image-20230313200340299.png)

inner_class_access_flags是内部类的访问标志，类似于类的access_flags。



##### 8.Deprecated及Synthetic属性

![](images/image-20230313200423310.png)

##### 9.StackMapTable属性

![](images/image-20230313200457685.png)

##### 10.Signature属性

![](images/image-20230313200536017.png)

##### 11.BootstrapMethods属性

![](images/image-20230313200559770.png)

![](images/image-20230313200613811.png)

##### 12.MethodParameters属性

![](images/image-20230313200641751.png)

![](images/image-20230313200658754.png)

##### 13.模块化相关属性

因为模块描述文件（module-info.java）最终是要编译成一个独立的Class文件来存储的，所以，Class文件格式也扩展了`Module`、`ModulePackages`和`ModuleMainClass`三个属性用于支持Java模块化相关功能。

![](images/image-20230313200850801.png)



![](images/image-20230313200916023.png)



![](images/image-20230313200934137.png)

##### 14.运行时注解相关属性



### 6.4 字节码指令简介

Java虚拟机的指令由一个字节长度的、代表着某种特定操作含义的数字（称为**==操作码==**，Opcode）以及跟随其后的零至多个代表此操作所需的参数（称为**==操作数==**，Operand）构成。

#### 字节码与数据类型

![](images/image-20220504111146185.png)

#### 加载和存储指令

加载和存储指令用于将数据在栈帧中的局部变量表和操作数栈之间来回传输，这类指令包括：

- 将一个局部变量加载到操作栈：

  ```
  iload、iload_<n>、lload、lload_<n>、fload、fload_<n>、dload、dload_<n>、aload、aload_<n>
  ```

- 将一个数值从操作数栈存储到局部变量表：

  ```
  istore、istore_<n>、lstore、lstore_<n>、fstore、fstore_<n>、dstore、dstore_<n>、astore、astore_<n>
  ```

- 将一个常量加载到操作数栈：

  ```
  bipush、sipush、ldc、ldc_w、ldc2_w、aconst_null、iconst_m1、iconst_<i>、lconst_<l>、fconst_<f>、dconst_<d>
  ```

- 扩充局部变量表的访问索引的指令：wide



#### 运算指令

运算指令可以分为两种：对整型数据进行运算的指令与对浮点型数据进行运算的指令。

- 加法指令：iadd、ladd、fadd、dadd
- 减法指令：isub、lsub、fsub、dsub
- 乘法指令：imul、lmul、fmul、dmul
- 除法指令：idiv、ldiv、fdiv、ddiv
- 求余指令：irem、lrem、frem、drem
- 取反指令：ineg、lneg、fneg、dneg
- 位移指令：ishl、ishr、iushr、lshl、lshr、lushr
- 按位或指令：ior、lor
- 按位与指令：iand、land
- 按位异或指令：ixor、lxor
- 局部变量自增指令：iinc
- 比较指令：dcmpg、dcmpl、fcmpg、fcmpl、lcmp



#### 类型转换指令

类型转换指令可以将两种不同的数值类型相互转换

#### 对象创建与访问指令

虽然类实例和数组都是对象，但Java虚拟机对类实例和数组的创建与操作使用了不同的字节码指令。

- 创建类实例的指令：new
- 创建数组的指令：newarray、anewarray、multianewarray
- 访问类字段（static字段，或者称为类变量）和实例字段（非static字段，或者称为实例变量）的指令：getfield、putfield、getstatic、putstatic
- 把一个数组元素加载到操作数栈的指令：baload、caload、saload、iaload、laload、faload、daload、aaload
- 将一个操作数栈的值储存到数组元素中的指令：bastore、castore、sastore、iastore、fastore、dastore、aastore
- 取数组长度的指令：arraylength
- 检查类实例类型的指令：instanceof、checkcast



#### 操作数栈管理指令

- 将操作数栈的栈顶一个或两个元素出栈：pop、pop2
- 复制栈顶一个或两个数值并将复制值或双份的复制值重新压入栈顶：dup、dup2、dup_x1、dup2_x1、dup_x2、dup2_x2
- 将栈最顶端的两个数值互换：swap



#### 控制转移指令

- 条件分支：ifeq、iflt、ifle、ifne、ifgt、ifge、ifnull、ifnonnull、if_icmpeq、if_icmpne、if_icmplt、if_icmpgt、if_icmple、if_icmpge、if_acmpeq和if_acmpne
- 复合条件分支：tableswitch、lookupswitch
- 无条件分支：goto、goto_w、jsr、jsr_w、ret



#### 方法调用和返回指令

- invokevirtual指令：用于调用对象的实例方法，根据对象的实际类型进行分派（虚方法分派），这也是Java语言中最常见的方法分派方式。
- invokeinterface指令：用于调用接口方法，它会在运行时搜索一个实现了这个接口方法的对象，找出适合的方法进行调用。
- invokespecial指令：用于调用一些需要特殊处理的实例方法，包括实例初始化方法、私有方法和父类方法。
- invokestatic指令：用于调用类静态方法（static方法）。
- invokedynamic指令：用于在运行时动态解析出调用点限定符所引用的方法。并执行该方法。前面四条调用指令的分派逻辑都固化在Java虚拟机内部，用户无法改变，而invokedynamic指令的分派逻辑是由用户所设定的引导方法决定的。



#### 异常处理指令

`athrow`

#### 同步指令

Java虚拟机可以支持**方法级的同步**和**方法内部一段指令序列的同步**，这两种同步结构都是使用**管程（Monitor**，更常见的是直接将它称为“**锁**”）来实现的。

monitorenter和monitorexit

### 6.5 公有设计，私有实现

理解公有设计与私有实现之间的分界线是非常有必要的，**任何一款Java虚拟机实现都必须能够读取Class文件并精确实现包含在其中的Java虚拟机代码的语义**。



虚拟机实现的方式主要有以下两种：

- 将输入的Java虚拟机代码在加载时或执行时翻译成另一种虚拟机的指令集；
- 将输入的Java虚拟机代码在加载时或执行时翻译成宿主机处理程序的本地指令集（即即时编译器代码生成技术）。

### 6.6 Class文件结构的发展

直处于一个相对比较稳定的状态，Class文件的主体结构、字节码指令的语义和数量几乎没有出现过变动，所有对Class文件格式的改进，都集中在访问标志、属性表这些设计上原本就是可扩展的数据结构中添加新内容。

Class文件格式所具备的平台中立（不依赖于特定硬件及操作系统）、紧凑、稳定和可扩展的特点，是Java技术体系实现平台无关、语言无关两项特性的重要支柱。

## 7 虚拟机类加载机制

### 7.1　概述

**Java虚拟机把描述类的数据从Class文件加载到内存，并对数据进行==校验==、==转换解析==和==初始化==，最终形成可以被虚拟机直接使用的Java类型**，这个过程被称作**==虚拟机的类加载机制==**。

在Java语言里面，编译时不需要进行连接，类型的加载、连接和初始化过程都是在程序运行期间完成的。

这种==运行期动态加载和动态连接==的策略让Java语言进行提前编译会面临额外的困难，也会让类加载时稍微增加一些性能开销，但是却为Java应用提供了极高的==扩展性和灵活性==。

### 7.2　类加载的时机

一个类型从被加载到虚拟机内存中开始，到卸载出内存为止，它的整个生命周期将会经历==加载（Loading）、验证（Verification）、准备（Preparation）、解析（Resolution）、初始化（Initialization）、使用（Using）和卸载（Unloading）==七个阶段。

![](images/image-20220504112753815.png)

动态绑定

互相交叉地混合进行的

对于初始化阶段，《Java虚拟机规范》则是严格规定了**==有且只有==**六种情况必须立即对类进行“初始化”（而加载、验证、准备自然需要在此之前开始）：

1. 遇到==new、getstatic、putstatic或invokestatic==这四条字节码指令时，如果类型没有进行过初始化，则需要先触发其初始化阶段。能够生成这四条指令的典型Java代码场景有：
   - 使用new关键字实例化对象的时候。
   - 读取或设置一个类型的静态字段（被final修饰、已在编译期把结果放入常量池的静态字段除外）的时候。
   - 调用一个类型的静态方法的时候。
2. 使用java.lang.reflect包的方法对类型进行反射调用的时候，如果类型没有进行过初始化，则需要先触发其初始化。
3. 当初始化类的时候，如果发现其父类还没有进行过初始化，则需要先触发其父类的初始化。
4. 当虚拟机启动时，用户需要指定一个要执行的主类（包含main()方法的那个类），虚拟机会先初始化这个主类。
5. 当使用JDK 7新加入的动态语言支持时，如果一个`java.lang.invoke.MethodHandle`实例最后的解析结果为REF_getStatic、REF_putStatic、REF_invokeStatic、REF_newInvokeSpecial四种类型的方法句柄，并且这个方法句柄对应的类没有进行过初始化，则需要先触发其初始化。
6. 当一个接口中定义了JDK 8新加入的默认方法（被default关键字修饰的接口方法）时，如果有这个接口的实现类发生了初始化，那该接口要在其之前被初始化。

这六种场景中的行为称为对一个类型进行==主动引用==。除此之外，所有引用类型的方式都不会触发初始化，称为==被动引用==。

🔖

> 一个接口在初始化时，并不要求其父接口全部都完成了初始化，只有在真正使用到父接口的时候（如引用接口中定义的常量）才会初始化。

### 7.3　类加载的过程

#### 加载

==“加载”（Loading）==阶段是整个==“类加载”（Class Loading）==过程中的一个阶段。

加载阶段，JVM完成三件事：

1. 通过一个类的全限定名来获取定义此类的二进制字节流。
2. 将这个字节流所代表的静态存储结构转化为方法区的运行时数据结构。
3. 在内存中生成一个代表这个类的java.lang.Class对象，作为方法区这个类的各种数据的访问入口。

《Java虚拟机规范》并没有指明二进制字节流必须得从某个Class文件中获取，确切地说是根本没有指明要从哪里获取、如何获取。

在Java发展历程中，开发人员凭借这一点空隙，创造出许多举足轻重的Java技术：

- 从ZIP压缩包中读取，这很常见，最终成为日后JAR、EAR、WAR格式的基础。
- 从网络中获取，这种场景最典型的应用就是Web Applet。
- 运行时计算生成，这种场景使用得最多的就是动态代理技术，在java.lang.reflect.Proxy中，就是用了ProxyGenerator.generateProxyClass()来为特定接口生成形式为“*$Proxy”的代理类的二进制字节流。
- 由其他文件生成，典型场景是JSP应用，<u>由JSP文件生成对应的Class文件</u>。
- 从数据库中读取，这种场景相对少见些，例如有些中间件服务器（如SAP Netweaver）可以选择把程序安装到数据库中来完成程序代码在集群间的分发。
- 可以从加密文件中获取，这是典型的防Class文件被反编译的保护措施，通过加载时解密Class文件来保障程序运行逻辑不被窥探。
- ……

相对于类加载过程的其他阶段，非数组类型的加载阶段（准确地说，是加载阶段中获取类的二进制字节流的动作）是**开发人员可控性最强的阶段**。

<u>数组类本身不通过类加载器创建，它是由Java虚拟机直接在内存中动态构造出来的。</u>

但数组类与类加载器仍然有很密切的关系，因为==数组类的元素类型==（Element Type，指的是数组去掉所有维度的类型）最终还是要靠类加载器来完成加载。一个数组类（下面简称为C）创建过程遵循以下规则：

- 如果==数组的组件类型==（Component Type，指的是数组去掉一个维度的类型，注意和前面的元素类型区分开来）是引用类型，那就递归采用本节中定义的加载过程去加载这个组件类型，数组C将被标识在加载该组件类型的类加载器的类名称空间上（这点很重要，在7.4节会介绍，一个类型必须与类加载器一起确定唯一性）。
- 如果数组的组件类型不是引用类型（例如int[]数组的组件类型为int），Java虚拟机将会把数组C标记为与引导类加载器关联。
- 数组类的可访问性与它的组件类型的可访问性一致，如果组件类型不是引用类型，它的数组类的可访问性将默认为public，可被所有的类和接口访问到。

#### 验证

验证的目的：确保Class文件的字节流中包含的信息符合《Java虚拟机规范》的全部约束要求，保证这些信息被当作代码运行后不会危害虚拟机自身的安全。

整体上看，验证阶段大致分为四个阶段：

##### 1.文件格式验证

- 是否以魔数0xCAFEBABE开头。
- 主、次版本号是否在当前Java虚拟机接受范围之内。
- 常量池的常量中是否有不被支持的常量类型（检查常量tag标志）。
- 指向常量的各种索引值中是否有指向不存在的常量或不符合类型的常量。
- CONSTANT_Utf8_info型的常量中是否有不符合UTF-8编码的数据。
- Class文件中各个部分及文件本身是否有被删除的或附加的其他信息。
- ……

主要目的是**保证输入的字节流能正确地解析并存储于方法区之内，格式上符合描述一个Java类型信息的要求**。

##### 2.元数据验证

- 这个类是否有父类（除了java.lang.Object之外，所有的类都应当有父类）。
- 这个类的父类是否继承了不允许被继承的类（被final修饰的类）。
- 如果这个类不是抽象类，是否实现了其父类或接口之中要求实现的所有方法。
- 类中的字段、方法是否与父类产生矛盾（例如覆盖了父类的final字段，或者出现不符合规则的方法重载，例如方法参数都一致，但返回值类型却不同等）。
- ……

主要目的是**对类的元数据信息进行语义校验，保证不存在与《Java语言规范》定义相悖的元数据信息**。

##### 3.字节码验证

主要目的是**通过数据流分析和控制流分析，确定程序语义是合法的、符合逻辑的**。

##### 4.符号引用验证

- 符号引用中通过字符串描述的全限定名是否能找到对应的类。
- 在指定类中是否存在符合方法的字段描述符及简单名称所描述的方法和字段。
- 符号引用中的类、字段、方法的可访问性（private、protected、public、`<package>`）是否可被当前类访问。
- ……

主要目的是**确保解析行为能正常执行**，如果无法通过符号引用验证，Java虚拟机将会抛出一个java.lang.IncompatibleClassChangeError的子类异常，典型的如：java.lang.IllegalAccessError、java.lang.NoSuchFieldError、java.lang.NoSuchMethodError等。



`-Xverify：none`

#### 准备

准备阶段是正式为类中定义的变量（即静态变量，被static修饰的变量）分配内存并设置类变量初始值的阶段

#### 解析🔖

解析阶段是Java虚拟机**将常量池内的符号引用替换为直接引用的过程**。



解析动作主要针对<u>类或接口、字段、类方法、接口方法、方法类型、方法句柄和调用点限定符</u>这7类符号引用进行，分别对应于常量池的CONSTANT_Class_info、CON-STANT_Fieldref_info、CONSTANT_Methodref_info、CONSTANT_InterfaceMethodref_info、CONSTANT_MethodType_info、CONSTANT_MethodHandle_info、CONSTANT_Dyna-mic_info和CONSTANT_InvokeDynamic_info 8种常量类型。

##### 1.类或接口的解析



##### 2.字段解析



##### 3.方法解析



##### 4.接口方法解析





#### 初始化

![](images/image-20230316173512577.png)

类的初始化阶段是类加载过程的最后一个步骤，之前介绍的几个类加载的动作里，**除了在加载阶段用户应用程序可以通过自定义类加载器的方式局部参与外，其余动作都完全由Java虚拟机来主导控制**。直到初始化阶段，Java虚拟机才真正开始执行类中编写的Java程序代码，将主导权移交给应用程序。

### 7.4 类加载器

Java虚拟机设计团队有意把类加载阶段中的“通过一个类的全限定名来获取描述该类的二进制字节流”这个动作放到Java虚拟机外部去实现，以便让应用程序自己决定如何去获取所需的类。实现这个动作的代码被称为==“类加载器”（ClassLoader）==。

#### 类与类加载器

对于任意一个类，都必须由加载它的类加载器和这个类本身一起共同确立其在Java虚拟机中的唯一性，每一个类加载器，都拥有一个独立的类名称空间。也就是说，<u>比较两个类是否“相等”，只有在这两个类是由同一个类加载器加载的前提下才有意义</u>。

#### 双亲委派模型

从JVM角度看，只有两种类加载器：

1. 启动类加载器（Bootstrap ClassLoader）。这个类加载器使用C++语言实现，是虚拟机自身的一部分。
2. 其他所有的类加载器，这些类加载器都由Java语言实现，独立存在于虚拟机外部，并且全都继承自抽象类`java.lang.ClassLoader`。

从Java开发者，分3类：

1. 启动类加载器（Bootstrap Class Loader），负责加载存放在`<JAVA_HOME>\lib`目录，或者被`-Xbootclasspath`参数所指定的路径中存放的。

2. 扩展类加载器（Extension Class Loader），在类`sun.misc.Launcher$ExtClassLoader`中以Java代码的形式实现的。它负责加载`<JAVA_HOME>\lib\ext`目录中，或者被`java.ext.dirs`系统变量所指定的路径中所有的类库。🔖
3. 应用程序类加载器（Application Class Loader）：由`sun.misc.Launcher$AppClassLoader`来实现。

![](images/image-20230316174921143.png)

JDK 9之前的Java应用都是由这三种类加载器互相配合来完成加载的，如果用户认为有必要，还可以加入自定义的类加载器来进行拓展。

#### 破坏双亲委派模型

双亲委派模型并不是一个具有强制性约束的模型，而是Java设计者推荐给开发者们的类加载器实现方式。

### 7.5 Java模块化系统

JDK 9 引入 **Java模块化系统（Java Platform Module System，JPMS）**，为了能够实现模块化的关键目标——==可配置的封装隔离机制==，Java虚拟机对类加载架构也做出了相应的变动调整，才使模块化系统得以顺利地运作。

JDK 9的模块不仅仅像之前的JAR包那样只是简单地充当代码的容器，除了代码外，Java的模块定义还包含以下内容：

- 依赖其他模块的列表。
- 导出的包列表，即其他模块可以使用的列表。
- 开放的包列表，即其他模块可反射访问模块的列表。
- 使用的服务列表。
- 提供服务的实现列表。

#### 模块的兼容性

==“模块路径”（ModulePath）==

简单来说，就是<u>某个类库到底是模块还是传统的JAR包，只取决于它存放在哪种路径上。只要是放在类路径上的JAR文件，无论其中是否包含模块化信息（是否包含了module-info.class文件），它都会被当作传统的JAR包来对待；相应地，只要放在模块路径上的JAR文件，即使没有使用JMOD后缀，甚至说其中并不包含module-info.class文件，它也仍然会被当作一个模块来对待。</u>

#### 模块化下的类加载器



扩展类加载器（Extension Class Loader）被平台类加载器（Platform Class Loader）取代。

原来的rt.jar和tools.jar被拆分成数十个JMOD文件

![](images/image-20230316175926731.png)

启动类加载器负责加载的模块：

```java
java.base                        java.security.sasl
java.datatransfer                java.xml
java.desktop                     jdk.httpserver
java.instrument                  jdk.internal.vm.ci
java.logging                     jdk.management
java.management                  jdk.management.agent
java.management.rmi              jdk.naming.rmi
java.naming                      jdk.net
java.prefs                       jdk.sctp
java.rmi                         jdk.unsupported
```

平台类加载器负责加载的模块：

```java
java.activation*                jdk.accessibility
java.compiler*                  jdk.charsets
java.corba*                     jdk.crypto.cryptoki
java.scripting                  jdk.crypto.ec
java.se                         jdk.dynalink
java.se.ee                      jdk.incubator.httpclient
java.security.jgss              jdk.internal.vm.compiler*
java.smartcardio                jdk.jsobject
java.sql                        jdk.localedata
java.sql.rowset                 jdk.naming.dns
java.transaction*               jdk.scripting.nashorn
java.xml.bind*                  jdk.security.auth
java.xml.crypto                 jdk.security.jgss
java.xml.ws*                    jdk.xml.dom
java.xml.ws.annotation*         jdk.zipfs
```

应用程序类加载器负责加载的模块：

```java
jdk.aot                         jdk.jdeps
jdk.attach                      jdk.jdi
jdk.compiler                    jdk.jdwp.agent
jdk.editpad                     jdk.jlink
jdk.hotspot.agent               jdk.jshell
jdk.internal.ed                 jdk.jstatd
jdk.internal.jvmstat            jdk.pack
jdk.internal.le                 jdk.policytool
jdk.internal.opt                jdk.rmic
jdk.jartool                     jdk.scripting.nashorn.shell
jdk.javadoc                     jdk.xml.bind*
jdk.jcmd                        jdk.xml.ws*
jdk.jconsole
```



## 8 虚拟机字节码执行引擎

![](http://assets.processon.com/chart_image/6174a0870e3e7416bde14735.png)



### 8.1 概述

执行引擎是Java虚拟机核心的组成部分之一。

“虚拟机”是一个相对于“物理机”的概念，它们都有**代码执行能力**，它们的区别：

- 物理机的执行引擎是**直接建立在处理器、缓存、指令集和操作系统层面上的**；
- 虚拟机的执行引擎则是**由软件自行实现的**，因此可以**不受物理条件制约**地定制指令集与执行引擎的结构体系，能够执行那些不被硬件直接支持的指令集格式。

执行引擎在执行字节码的时候，通常会有解释执行（通过解释器执行）和编译执行（通过即时编译器产生本地代码执行）两种选择，也可能两者兼备，还可能会有同时包含几个不同级别的即时编译器一起工作的执行引擎。

但整体上看，JVM的执行引擎输入、输出都是一致的：**输入的是字节码二进制流，处理过程是字节码解析执行的等效过程，输出的是执行结果**。

### 8.2 运行时栈帧结构

Java虚拟机以**方法**作为最基本的执行单元，**==“栈帧”（Stack Frame）==**则是用于支持虚拟机进行**方法调用和方法执行**背后的数据结构，它也是虚拟机运行时数据区中的虚拟机栈的栈元素。

每一个方法从调用开始至执行结束的过程，都对应着一个栈帧在虚拟机栈里面从入栈到出栈的过程。

“当前栈帧”（Current Stack Frame）

“当前方法”（Current Method）

#### 局部变量表

局部变量表（Local Variables Table）是一组变量值的存储空间，用于存放==方法参数==和方法内部定义的==局部变量==。

`max_locals`

![](images/image-20220505090254482.png)

#### 操作数栈

操作数栈（Operand Stack）也常被称为操作栈。

操作数栈的最大深度在编译的时候被写入到Code属性的`max_stacks`数据项之中。

![](images/image-20220505090528474.png)

#### 动态连接

每个栈帧都包含一个指向运行时常量池中该栈帧所属方法的引用，持有这个引用是为了支持方法调用过程中的**==动态连接（Dynamic Linking）==**。

#### 方法返回地址

当一个方法开始执行后，只有两种方式退出这个方法：

1. 第一种方式是执行引擎遇到任意一个方法返回的字节码指令，这时候可能会有返回值传递给上层的方法调用者（调用当前方法的方法称为**调用者或者主调方法**），方法是否有返回值以及返回值的类型将根据遇到何种方法返回指令来决定，这种退出方法的方式称为==“正常调用完成”（Normal Method Invocation Completion）==。

2. 另外一种退出方式是在方法执行的过程中遇到了异常，并且这个异常没有在方法体内得到妥善处理。无论是Java虚拟机内部产生的异常，还是代码中使用athrow字节码指令产生的异常，只要在本方法的==异常表==中没有搜索到匹配的异常处理器，就会导致方法退出，这种退出方法的方式称为==“异常调用完成（Abrupt Method Invocation Completion）”==。

无论采用何种退出方式，在方法退出之后，都必须返回到==最初方法被调用时的位置==，程序才能继续执行，方法返回时可能需要在栈帧中保存一些信息，用来帮助==恢复它的上层主调方法的执行状态==。

🔖



### 8.3 方法调用

方法调用并不等同于方法中的代码被执行，方法调用阶段唯一的任务就是<u>确定被调用方法的版本（即调用哪一个方法）</u>，暂时还未涉及方法内部的具体运行过程。

#### 解析

**所有方法调用的目标方法在Class文件里面都是一个常量池中的符号引用**，在类加载的解析阶段，会将其中的一部分符号引用转化为直接引用，这种解析能够成立的前提是：方法在程序真正运行之前就有一个可确定的调用版本，并且这个方法的调用版本在运行期是不可改变的。

调用不同类型的方法，字节码指令集里设计了不同的指令。5条方法调用字节码指令：

- invokestatic。用于调用静态方法。
- invokespecial。用于调用实例构造器`<init>()`方法、私有方法和父类中的方法。
- invokevirtual。用于调用所有的虚方法。
- invokeinterface。用于调用接口方法，会在运行时再确定一个实现该接口的对象。
- invokedynamic。先在运行时动态解析出调用点限定符所引用的方法，然后再执行该方法。

#### 分派

##### 1.静态分派



##### 2.动态分派



##### 3.单分派与多分派



##### 4.虚拟机动态分派的实现

接口方法表——Interface Method Table，简称itable

![](images/image-20230317005210635.png)





### 8.4　动态类型语言支持

#### 动态类型语言

==动态类型语言==的关键特征是它的**类型检查的主体过程是在运行期而不是编译期进行的**。

在编译期就进行类型检查过程的语言，譬如C++和Java等就是最常用的==静态类型语言==。

#### Java与动态类型



#### java.lang.invoke包

“方法句柄”（Method Handle）

#### invokedynamic指令



#### 实战：掌控方法分派规则



### 8.5　基于栈的字节码解释执行引擎

#### 解释执行

大部分的程序代码转换成物理机的目标代码或虚拟机能执行的指令集之前，都需要经以下步骤：

![](images/image-20220505092409635.png)

抽象语法树（Abstract Syntax Tree，AST）

在Java语言中，Javac编译器完成了程序代码经过词法分析、语法分析到抽象语法树，再遍历语法树生成线性的字节码指令流的过程。

#### 基于栈的指令集与基于寄存器的指令集



#### 基于栈的解释器执行过程🔖





## 9  类加载及执行子系统的案例与实战

用户的程序能直接参与的，主要是**字节码生成与类加载器**这两部分的功能。

### 9.2　案例分析

#### Tomcat：正统的类加载器架构

主流的Java Web服务器：Tomcat、Jetty、WebLogic、WebSphere等。

功能健全的Web服务器，都要解决的问题：

- 部署在同一个服务器上的两个Web应用程序所使用的Java类库可以实现相互隔离。
- 部署在同一个服务器上的两个Web应用程序所使用的Java类库可以互相共享。
- 服务器需要尽可能地保证自身的安全不受部署的Web应用程序影响。
- 支持JSP应用的Web服务器，十有八九都需要支持HotSwap功能。

于存在上述问题，在部署Web应用时，**单独的一个ClassPath就不能满足需求了，所以各种Web服务器都不约而同地提供了好几个有着不同含义的ClassPath路径供用户存放第三方类库，这些路径一般会以“lib”或“classes”命名**。

> Tomcat具体是如何规划用户类库结构和类加载器的？

- 放置在/common目录中。类库可被Tomcat和所有的Web应用程序共同使用。
- 放置在/server目录中。类库可被Tomcat使用，对所有的Web应用程序都不可见。
- 放置在/shared目录中。类库可被所有的Web应用程序共同使用，但对Tomcat自己不可见。
- 放置在/WebApp/WEB-INF目录中。类库仅仅可以被该Web应用程序使用，对Tomcat和其他Web应用程序都不可见。

![](images/image-20230317011610578.png)

#### OSGi：灵活的类加载器架构

OSGi（Open Service Gateway Initiative）是OSGi联盟（OSGi Alliance）制订的一个==基于Java语言的动态模块化规范==。

Java世界中“事实上”的动态模块化标准



#### 字节码生成技术与动态代理的实现



动态代理的优势是可以**在原始类和接口还未知的时候，就确定代理类的代理行为，当代理类与原始类脱离直接联系后，就可以很灵活地重用于不同的应用场景之中**。



#### Backport工具：Java的时光机器

Retrotranslator

### 9.3　实战：自己动手实现远程执行功能



[BTrace](https://github.com/btraceio/btrace)

[Arthas](https://github.com/alibaba/arthas)

# 四、程序编译与代码优化

## 10 前端编译与优化

从计算机程序出现的第一天起，对效率的追逐就是程序员天生的坚定信仰，这个过程犹如一场没有终点、永不停歇的F1方程式竞赛，程序员是车手，技术平台则是在赛道上飞驰的赛车。

### 10.1 概述

- 前端编译器（“编译器的前端”，把`*.java`文件转变成`*.class`文件的过程）：JDK的Javac、Eclipse JDT中的增量式编译器（ECJ）。
- 即时编译器（常称==JIT==编译器，Just In Time Compiler，**运行期把字节码转变成本地机器码的过程**）：HotSpot虚拟机的C1、C2编译器，Graal编译器。
- 提前编译器（常称==AOT==编译器，Ahead Of Time Compiler，**直接把程序编译成与目标机器指令集相关的二进制代码**）：JDK的Jaotc、GNU Compiler for the Java（GCJ）、Excelsior JET。



Java中即时编译器在运行期的优化过程，支撑了==程序执行效率==的不断提升；而前端编译器在编译期的优化过程，则是支撑着程序员的==编码效率和语言使用者的幸福感==的提高。

### 10.2 Javac编译器

Javac编译器不像HotSpot虚拟机那样使用C++语言（包含少量C语言）实现，它本身就是一个由Java语言编写的程序。

#### Javac的源码与调试

![](images/image-20220505094812262.png)

![](images/image-20230317022729285.png)

`com.sun.tools.javac.main.JavaCompiler`

![](images/image-20230317022747732.png)





#### 解析与填充符号表

##### 1.词法、语法分析



##### 2.填充符号表



#### 注解处理器

**==插入式注解处理器==**可以看作是一组编译器的插件，当这些插件工作时，**允许读取、修改、添加抽象语法树中的任意元素**。

有了编译器注解处理的标准API后，**程序员的代码才有可能干涉编译器的行为**。

> Lombok通过注解来实现自动产生getter/setter方法、进行空置检查、生成受查异常表、产生equals()和hashCode()方法，等等。

#### 语义分析与字节码生成

语义分析的主要任务则是**对结构上正确的源程序进行上下文相关性质的检查**，譬如进行<u>类型检查、控制流检查、数据流检查</u>，等等。

##### 1.标注检查

##### 2.数据及控制流分析

##### 3.解语法糖

##### 4.字节码生成



### 10.3 Java语法糖的味道

虽然不会提供实质性的功能改进，但是它们或能提高效率，或能提升语法的严谨性，或能减少编码出错的机会。

#### 泛型

##### 1.Java与C#的泛型

##### 2.泛型的历史背景

##### 3.类型擦除

##### 4.值类型与未来的泛型



#### 自动装箱、拆箱与遍历循环



#### 条件编译



### 10.4 实战：插入式注解处理器



## 11 后端编译与优化

### 11.1 概述

如果把字节码看作是程序语言的一种**中间表示形式（Intermediate Representation，IR）**的话，那编译器无论在何时、在何种状态下把Class文件转换成与本地基础设施（硬件指令集、操作系统）相关的二进制机器码，它都可以视为整个**编译过程的后端**。

后端编译器编译性能的好坏、代码优化质量的高低却是衡量一款商用虚拟机优秀与否的关键指标之一。

### 11.2 即时编译器



Java程序最初都是通过解释器（Interpreter）进行解释执行的，当虚拟机发现某个方法或代码块的运行特别频繁，就会把这些代码认定为==“热点代码”（Hot Spot Code）==，为了提高热点代码的执行效率，在运行时，虚拟机将会把这些代码编译成本地机器码，并以各种手段尽可能地进行代码优化，运行时完成这个任务的后端编译器被称为==即时编译器==。

#### 解释器与编译器

目前主流的商用Java虚拟机，内部都==同时包含解释器与编译器==。

解释器与编译器两者各有优势：**当程序需要迅速启动和执行的时候，解释器可以首先发挥作用，省去编译的时间，立即运行。当程序启动后，随着时间的推移，编译器逐渐发挥作用，把越来越多的代码编译成本地代码，这样可以减少解释器的中间损耗，获得更高的执行效率。**

![](images/image-20220506121734363.png)

HotSpot中内置了两个（或三个）即时编译器：“客户端编译器”（ClientCompiler）和“服务端编译器”（Server Compiler），简称为==C1==编译器和==C2==编译器。Graal编译器，长期目标是代替C2。

分层编译（Tiered Compilation）

“混合模式”（MixedMode）

“解释模式”（Interpreted Mode）

“编译模式”（Compiled Mode）

```shell
➜ java -version
java version "1.8.0_311"
Java(TM) SE Runtime Environment (build 1.8.0_311-b11)
Java HotSpot(TM) 64-Bit Server VM (build 25.311-b11, mixed mode)
➜ java  -Xint -version
java version "1.8.0_311"
Java(TM) SE Runtime Environment (build 1.8.0_311-b11)
Java HotSpot(TM) 64-Bit Server VM (build 25.311-b11, interpreted mode)
➜ java  -Xcomp -version
java version "1.8.0_311"
Java(TM) SE Runtime Environment (build 1.8.0_311-b11)
Java HotSpot(TM) 64-Bit Server VM (build 25.311-b11, compiled mode)
```



#### 编译对象与触发条件

热点代码：

- 被多次调用的方法。
- 被多次执行的循环体。

#### 编译过程

![](images/image-20220506122447466.png)

#### 实战：查看及分析即时编译结果



### 11.3 提前编译器

#### 提前编译的优劣得失



#### 实战：Jaotc的提前编译



### 11.4　编译器优化技术

#### 优化技术概览

![](images/Image00205.jpeg)

![](images/Image00206.jpeg)

#### 方法内联



#### 逃逸分析



#### 公共子表达式消除



#### 数组边界检查消除



### 11.5 实战：深入理解Graal编译器

#### 历史背景



#### 构建编译调试环境



#### JVMCI编译器接口



#### 代码中间表示



#### 代码优化与生成



# 五、高效并发

## 12 Java内存模型与线程

并发处理的广泛应用是**Amdahl定律**代替摩尔定律成为计算机性能发展源动力的根本原因，也是人类压榨计算机运算能力的最有力武器。

### 12.1 概述

让计算机同时做几件事情的两个原因：

- 计算机的运算能力强大
- 计算机的运算速度与它的存储和通信子系统的速度差距太大，大量的时间都花费在磁盘I/O、网络通信或者数据库访问上

### 12.2 硬件的效率与一致性

![](images/image-20220506123753170.png)

### 12.3 Java内存模型

“Java内存模型”（Java MemoryModel，JMM），来屏蔽各种硬件和操作系统的内存访问差异，以实现让Java程序在各种平台下都能达到一致的内存访问效果。

#### 主内存与工作内存

![](images/image-20220506124006607.png)

#### 内存间交互操作

主内存与工作内存之间的8种操作：

- lock（锁定）：作用于主内存的变量，它把一个变量标识为一条线程独占的状态。
- unlock（解锁）：作用于主内存的变量，它把一个处于锁定状态的变量释放出来，释放后的变量才可以被其他线程锁定。
- read（读取）：作用于主内存的变量，它把一个变量的值从主内存传输到线程的工作内存中，以便随后的load动作使用。
- load（载入）：作用于工作内存的变量，它把read操作从主内存中得到的变量值放入工作内存的变量副本中。
- use（使用）：作用于工作内存的变量，它把工作内存中一个变量的值传递给执行引擎，每当虚拟机遇到一个需要使用变量的值的字节码指令时将会执行这个操作。
- assign（赋值）：作用于工作内存的变量，它把一个从执行引擎接收的值赋给工作内存的变量，每当虚拟机遇到一个给变量赋值的字节码指令时执行这个操作。
- store（存储）：作用于工作内存的变量，它把工作内存中一个变量的值传送到主内存中，以便随后的write操作使用。
- write（写入）：作用于主内存的变量，它把store操作从工作内存中得到的变量的值放入主内存的变量中。

#### 对于volatile型变量的特殊规则



#### 针对long和double型变量的特殊规则



#### 原子性、可见性与有序性



#### 先行发生原则



### 12.4 Java与线程

#### 线程的实现



##### 1.内核线程实现



##### 2.用户线程实现



##### 3.混合实现



##### 4.Java线程的实现



#### Java线程调度

线程调度是指**系统为线程分配处理器使用权的过程**。两种：

- 协同式（Cooperative Threads-Scheduling）线程调度
- 抢占式（PreemptiveThreads-Scheduling）线程调度



#### 状态转换

![](images/image-20220506125324012.png)

### 12.5 Java与协程

#### 内核线程的局限



#### 协程的复苏



#### Java的解决方案



## 13 线程安全与锁优化

### 13.1 概述

面向过程，站在计算机的角度去抽象问题和解决问题。

面向对象，站在现实世界的角度去抽象和解决问题。

现实世界与计算机世界之间不可避免地存在一些差异



### 13.2 线程安全

> “当多个线程同时访问一个对象时，如果不用考虑这些线程在运行时环境下的调度和交替执行，也不需要进行额外的同步，或者在调用方进行任何其他的协调操作，调用这个对象的行为都可以获得正确的结果，那就称这个对象是线程安全的。”
>
> —— 《Java并发编程实战（Java Concurrency In Practice）》的作者BrianGoetz

#### Java语言中的线程安全



#### 线程安全的实现方法

##### 1.互斥同步



##### 2.非阻塞同步



##### 3.无同步方案



### 13.3 锁优化

#### 自旋锁与自适应自旋



#### 锁消除



#### 锁粗化



#### 轻量级锁



#### 偏向锁



# JVM参数说明

## JVM参数分类

以开头分三类：

### -

标准参数（`-`）：所有的JVM实现都必须实现这些参数的功能，而且向后兼容；

### -x

非标准参数（`-X`）：默认jvm实现这些参数的功能，但是并不保证所有jvm实现都满足，且不保证向后兼容

### -XX

非Stable参数（`-XX`）：此类参数各个jvm实现会有所不同，将来可能会随时取消，需要慎重使用；



## 关键参数

- -Xms20m ：设置jvm初始化堆大小为20m
- -Xmx20m：设置jvm最大可用内存大小为20m。
- -Xmn10m：设置新生代大小为20m。
- -Xss128k：设置每个线程的栈大小为128k。

| 参数 | 拆分         | 含义         |
| ---- | ------------ | ------------ |
| -Xms | memory、size | 堆内存初始化 |
| -Xmx | memory、max  | 堆内存最大   |
| -Xmn | memory、new  | 新生代内存   |
| -Xss | stack、size  | 栈大小       |

- -verbose:gc：可以输出每次GC的一些信息；

- -XX:-UseConcMarkSweepGC：使用CMS收集器；

- -XX:-UseParallelGC ；

- -XX:-UseSerialGC；

- -XX:CMSInitiatingOccupancyFraction=80 CMS gc，表示在老年代达到80%使用率时马上进行回收；

- -XX:+printGC；

- -XX:+PrintGCDetails：打印GC详情；

- -XX:+PrintGCTimeStamps：打印时间戳；
