Java编程的逻辑
-------



# 一、编程基础与二进制

## 1 编程基础

操作系统将时间分成很多细小的时间片，一个时间片给一个程序用，另一个时间片给另一个程序用，并频繁地在程序间切换。不过，在应用程序看来，整个机器资源好像都归它使用，操作系统给它制造了这种假象。对程序员而言，编写程序时基本不用考虑其他应用程序，做好自己的事就可以了。

应用程序看上去能做很多事情，能读写文档、能播放音乐、能聊天、能玩游戏、能下围棋等，但本质上，计算机只会<font color=#FF8C00>执行预先写好的指令</font>（操作数据或者设备）而已。

所谓程序，基本上就是告诉计算机要操作的数据和执行的**指令序列**，即对什么数据做什么操作，比如：

1. 读文档，就是将数据从磁盘加载到内存，然后输出到显示器上；
2. 写文档，就是将数据从内存写回磁盘；
3. 播放音乐，就是将音乐的数据加载到内存，然后写到声卡上；
4. 聊天，就是从键盘接收聊天数据，放到内存，然后传给网卡，通过网络传给另一个人的网卡，再从网卡传到内存，显示在显示器上。

数据在计算机内部都是二进制表示的，不方便操作，为了方便操作数据，高级语言引入了**数据类型**和**变量**的概念；

对数据进行的第一个操作：**赋值**；

数据有了初始值之后，可以对数据进行的一些**基本运算**；

为了编写有实用功能的程序，需要对操作的过程进行**流程控制**；

为了减少重复代码和分解复杂操作，计算机程序引入了**函数**和**子程序**的概念。

### 1.1 数据类型和变量

8中基本数据类型：

1. 整数类型：有4种整型byte/short/int/long（分别占1、2、4、8个字节），分别有不同的取值范围；
2. 小数类型：有两种类型float/double（4和8字节），有不同的取值范围和精度；
3. 字符类型：char，表示单个字符（2字节）；
4. 真假类型：boolean，表示真假。

>  世界万物都是由元素周期表中的基本元素组成的，基本数据类型就相当于化学中的基本元素，而对象就相当于世界万物。

所谓内存在程序看来就是一块**有地址编号的连续的空间**，数据放到内存中的某个位置后，为了方便地找到和操作这个数据，需要给这个位置起一个名字。编程语言通过<font color=#FF8C00>变量</font>这个概念来表示这个过程。

变量就是给数据起名字，方便找不同的数据，它的值可以变，但含义不应变。

### 1.2 赋值

赋值声明变量之后，就在内存分配了一块位置，但这个位置的内容是未知的，赋值就是把这块位置的内容设为一个确定的值。

#### 数组类型

3中赋值方式：

```java
int[] arr = {1,2,3};

int[] arr = new int[]{1,2,3};

int[] arr = new int[3];
arr[0] = 1;
arr[1] = 2;
arr[2] = 3;
```

动态确定数组长度：

```java
int length = ...; // 根据一些条件动态计算
int[] arr = nwe int[length];
```

数组组长度虽然可以动态确定，但定了之后就不可以变。

数组类型和基本类型是有明显不同的，一个基本类型变量，内存中只会有一块对应的内存空间。但数组有两块：一块用于存储数组内容本身，另一块用于存储内容的位置。

![image-20220309142725779](images/image-20220309142725779.png)

### 1.3 基本运算

### 1.4 条件执行

if/else

三元运算

switch

#### 实现原理

CPU的<font color=#FF8C00>指令指示器</font>，**跳转**指令，有两种：

1. 条件跳转（满足某个条件则进行跳转）
2. 无条件跳转（直接跳转）

if/else就是转换为跳转指令：

```java
        1 int a=10;
        2 if(a%2==0)
        3 {
        4     System.out.println("偶数");
        5 }
        6 //其他代码
```

转换为：

```java
        1 int a=10;
        2 条件跳转：如果a%2==0，跳转到第4行
        3 无条件跳转：跳转到第7行
        4 {
        5     System.out.println("偶数");
        6 }
        7 //其他代码
```

switch的转换和具体系统实现有关。如果分支比较少，可能会转换为跳转指令。如果分支比较多，使用条件跳转会进行很多次的比较运算，效率比较低，可能会使用一种更为高效的方式，叫<font color=#FF8C00>跳转表</font>。跳转表是一个映射表，存储了可能的值以及要跳转到的地址：

![image-20220309174525727](images/image-20220309174525727.png)

跳转表为什么会更为高效呢？因为其中的值必须为整数，且按大小顺序排序。按大小排序的整数可以使用高效的二分查找。

🔖程序源代码中的case值排列不要求是排序的，编译器会自动排序。

### 1.5 循环

计算机程序运行时大致只能**顺序执行、条件执行和循环执行**。顺序和条件其实没什么特别的，而循环大概才是程序强大的地方。

循环的4中形式：

while

do/while

for

foreach  对于不需要使用索引变量，只是简单遍历的情况，foreach语法上更为简洁。

循环控制：

break

continue

**虽然循环看起来只是重复执行一些类似的操作而已，但它其实是计算机程序解决问题的一种基本思维方式。**

解决复杂问题的基本策略是**分而治之**，将复杂问题分解为若干相对简单的子问题，然后子问题再分解为更小的子问题……程序由数据和指令组成，大程序可以分解为小程序，小程序接着分解为更小的程序。

### 1.6 函数的用法

<font color=#FF8C00>使用函数来减少重复代码和分解复杂操作。</font>

Java中，任何函数都需要放在一个类中。Java中的函数一般叫做方法。

```java
修饰符 返回值类型 函数名字(参数类型 参数名字，...) {
	操作
  return 返回值;
}
```

Java中函数有大量修饰符，如：public、private、static、final、synchronized、abstract等。

定义函数时声明参数，实际上就是定义变量，只是这些变量的值是未知的，调用函数时传递参数，实际上就是给函数中的变量赋值。

### 1.7 函数调用的基本原理

序执行的基本原理：CPU有一个指令指示器，指向下一条要执行的指令，要么顺序执行，要么进行跳转（条件跳转或无条件跳转）。

程序从main函数开始顺序执行，函数调用可以看作一个无条件跳转，跳转到对应函数的指令处开始执行，碰到return语句或者函数结尾的时候，再执行一次无条件跳转，跳转回调用方，执行调用函数后的下一条指令。

🔖

函数调用主要是通过栈来存储相关的数据，系统就函数调用者和函数如何使用栈做了约定，返回值可以简单认为是通过一个专门的返回值存储器存储的。

从函数调用的过程可以看出，调用是有成本的，每一次调用都需要分配额外的栈空间用于存储参数、局部变量以及返回地址，需要进行额外的入栈和出栈操作。



## 2 理解数据背后的二进制

### 2.1 整数的二进制表示与位运算

位权

#### 正整数的二进制表示



#### 负整数的二进制表示

二进制使用最高位表示符号位，用1表示负数，用0表示正数。

**原码表示法**

但负数表示不是简单地将最高位变为1，而是用**补码表示法**，是在原码表示的基础上**取反然后加1**。

- `-1`: 1的原码表示是00000001，取反是11111110，然后再加1，就是11111111。
- `-2`: 2的原码表示是00000010，取反是11111101，然后再加1，就是11111110。
- `-127`: 127的原码表示是01111111，取反是10000000，然后再加1，就是10000001。

给定一个负数的二进制表示，要等到它的十进制，也可以使用补码运行，**取反然后加1**（注意不是减1），比如：

- `100100101` ，取反是`01101101`，加1是`01101110`，就是十进制110，所以原值的十进制表示就是-110。

对负数的补码表示做补码运算就可以得到其对应正数的原码。🔖

#### 十六进制



#### 位运算

### 2.2 小数的二进制表示

十进制也只能表示那些可以表述为10的多少次方和的数。

二进制只能表示那些可以表述为2的多少次方和的数。

> 为什么计算机使用二进制而不是十进制？
>
> 在最底层，计算机使用的电子元器件只能表示两个状态，通常是低压和高压，对应0和1，使用二进制容易基于这些电子元器件构建硬件设备和进行运算。如果非要使用十进制，则这些硬件就会复杂很多，并且效率低下。

如果真的需要比较高的精度，一种方法是将小数转化为整数进行运算，运算结束后再转化为小数；另一种方法是使用十进制的数据类型，这个并没有统一的规范。在Java中是BigDecimal，运算更准确，但效率比较低。



小数运算被称为浮点运算。

> 为什么要叫浮点数呢？
>
> 这是由于小数的二进制表示中，表示那个小数点的时候，点不是固定的，而是浮动的。

32位格式(float)中，1位表示符号，23位表示尾数，8位表示指数。64位格式(double)中，1位表示符号，52位表示尾数，11位表示指数。

查看浮点数的具体二进制形式：

```java
System.out.println(Integer.toBinaryString(Float.floatToIntBits(1.2345f)));
System.out.println(Long.toBinaryString(Double.doubleToLongBits(1.2345f)));
```

```java
111111100111100000010000011001
11111111110011110000001000001100100000000000000000000000000000
```

### 2.3 字符的编码与乱码 

编码分两大类：非Unicode编码，Unicode编码。

#### 常见非Unicode编码

##### 1 ASCII

ASCII编码，全称是American Standard Codefor InformationInterchange，即美国信息互换标准代码。

数字32～126表示的字符都是可打印字符，0～31和127表示一些不可以打印的字符，这些字符一般用于控制目的。

各个国家的各种计算机厂商就发明了各种各种的编码方式以表示自己国家的字符，为了保持与ASCII码的兼容性，一般都是将最高位设置为1。也就是说，**当最高位为0时，表示ASCII码，当为1时就是各个国家自己的字符**。

##### 2 ISO 8859-1

##### 3 Windows-1252

##### 4 GB2312

针对简体中文常见字符，包括约7000个汉字和一些罕用词和繁体字。

GB2312固定使用两个字节表示汉字。

##### 5 GBK

GBK建立在GB2312的基础上，向下兼容GB2312，增加了14 000多个汉字，共计约21 000个汉字，其中包括繁体字。

##### 6 GB18030

GB18030向下兼容GBK，增加了55 000多个字符，共76 000多个字符，包括了很多少数民族字符，以及中日韩统一字符。

有的字符是两个字节，有的是四个字节。

##### 7 Big5

繁体中文



#### Unicode编码

Unicode 做了一件事，就是给世界上所有字符都分配了一个唯一的数字编号，这个编号范围从0x000000～0x10FFFF，包括110多万。但大部分常用字符都在0x0000～0xFFFF之间，即65 536个数字之内。

一般写成十六进制，在前面加`U+`。大部分中文的编号范围为U+4E00～U+9FFF。

并没有规定这个**编号怎么对应到二进制表示**，产生几种方案：

##### 1 UTF-32



##### 2 UTF-16



##### 3 UTF-8

编号小的使用的字节就少，编号大的使用的字节就多，使用的字节个数为1～4不等。

![](images/image-20220310000148347.png)

小于128的，编码与ASCII码一样，最高位为0。其他编号的第一个字节有特殊含义，<u>最高位有几个连续的1就表示用几个字节表示，而其他字节都以10开头。</u>



#### 编码转换



#### 乱码的原因



#### 从乱码中恢复



### 2.4 char的真正含义🔖

UTF-16BE

由于**固定占用两个字节**，char只能表示Unicode编号在65 536以内的字符，而不能表示超出范围的字符。。由于固定占用两个字节，char只能表示Unicode编号在65 536以内的字符，而不能表示超出范围的字符。超出范围的字符怎么表示呢？使用两个char。



# 二、面向对象

## 3 类的基础

程序主要就是**数据以及对数据的操作**，为方便理解和操作，高级语言使用了**数据类型**这个概念。

### 3.1 类的基础概念

某些情况下，类也确实只是函数的容器，但类更多表示的是自定义数据类型。

#### 函数容器

`Math`

`Arrays`

static表示类方法，也叫静态方法，与类方法相对的是实例方法。实例方法没有static修饰符，必须通过实例或者对象调用，而类方法可以直接通过类名进行调用，不需要创建实例。

**通过private封装和隐藏内部实现细节，避免被误操作，是计算机程序的一种基本思维方式。**

Math和Arrays也可以看作自定义数据类型，分别表示数学和数组类型，其中的public static函数可以看作类型能进行的操作。

#### 自定义数据类型

一个数据类型由其包含的<font color=#FF8C00>**属性**</font>以及该类型可以进行的<font color=#FF8C00>**操作**</font>组成，属性又可以分为是类型本身具有的属性（**类变量**/静态变量/静态成员变量），还是一个具体实例具有的属性（**实例变量**），同样，操作也可以分为是类型本身可以进行的操作（**类方法**/静态方法），还是一个具体实例可以进行的操作（**实例方法**）。

类变量和实例变量都叫**成员变量**；类方法和实例方法都叫**成员方法**。

实例方法中，有一个隐含的参数，这个参数就是当前操作的实例自己，直接操作实例变量，实际也需要通过参数进行。

实例方法和类方法的区别：

1. 类方法只能访问类变量，不能访问实例变量，可以调用其他的类方法，不能调用实例方法。
2. 实例方法既能访问实例变量，也能访问类变量，既可以调用实例方法，也可以调用类方法。



**引用类型**的变量都有两块内存：一块存放实际内容，一块存放实际内容的位置。声明变量本身只会分配存放位置的内存空间，这块空间还没有指向任何实际内容。

在创建对象的时候，所有的实例变量都会分配一个**默认值**，这与创建数组的时候是类似的，数值类型变量的默认值是0, boolean是false, char是“\u0000”，引用类型变量都是null。

**通过对象来访问和操作其内部的数据是一种基本的面向对象思维。**

一般而言，**不应该将实例变量声明为public，而只应该通过对象的方法对实例变量进行操作**。这也是为了减少误操作，直接访问变量没有办法进行参数检查和控制，而通过方法修改，可以在方法中进行检查。

#### 构造方法

1. 名称是固定的，与类名相同
2. 没有返回值，也不能有返回值。构造方法隐含的返回值就是实例本身。

构造方法中的this调用必须放在第一行，这个规定也是为了避免误操作。构造方法是用于初始化对象的，如果要调用别的构造方法，先调别的，然后根据情况自己再做调整，而如果自己先初始化了一部分，再调别的，自己的修改可能就被覆盖了。

默认构造方法，私有构造方法。

#### 类和对象的生命周期

在程序运行的时候，当第一次通过new创建一个类的对象时，或者直接通过类名访问类变量和类方法时，Java会将类加载进内存，为这个类分配一块空间，这个空间会包括**类的定义、它的变量和方法信息，同时还有类的静态变量，并对静态变量赋初始值**。

类加载进内存后，一般不会释放，直到程序结束。一般情况下，类只会加载一次，所以静态变量在内存中只有一份。



当通过new创建一个对象的时候，对象产生，在内存中，会存储这个对象的实例变量值，每做new操作一次，就会产生一个对象，就会有一份独立的实例变量。

每个对象除了保存实例变量的值外，可以理解为还保存着对应类型即类的地址，这样，通过对象能知道它的类，访问到类的变量和方法代码。

<u>实例方法可以理解为一个静态方法，只是多了一个参数this。通过对象调用方法，可以理解为就是调用这个静态方法，并将对象作为参数传给this。</u>

对象和数组一样，有两块内存，保存地址的部分分配在栈中，而保存实际内容的部分分配在堆中。栈中的内存是自动管理的，函数调用入栈就会分配，而出栈就会释放。

### 3.2 类的组合

<font color=#FF8C00>程序是用来解决现实问题的，将现实中的概念映射为程序中的概念，是初学编程过程中的一步跨越。</font>

#### String和Date

`String`表示多个字符，即一段文本或字符串，它内部是一个char的数组，提供了若干方法用于操作字符串。

`Date`表示日期和时间，它内部是一个long类型的值。

#### 图形类

**在设计线时，我们考虑的层次是点，而不考虑点的内部细节。每个类封装其内部细节，对外提供高层次的功能，使其他类在更高层次上考虑和解决问题，是程序设计的一种基本思维方式。**

```java
    public static void main(String[] args) {
        Point start = new Point(2, 3);
        Point end = new Point(5, 9);
        Line line = new Line(start, end);
        System.out.println(line.length());
    }
```

![image-20220311170836304](images/image-20220311170836304.png)

start、end、line三个引用型变量分配在栈中，保存的是实际内容的地址，实际内容保存在堆中，line的两个实例变量line.start和line.end还是引用，同样保存的是实际内容的地址。

#### 用类描述电商概念

电商系统中最基本的有产品、用户和订单。

想想现实问题有哪些概念，这些概念有哪些属性、哪些行为，概念之间有什么关系，然后定义类、定义属性、定义方法、定义类之间的关系。概念的属性和行为可能是非常多的，但定义的类只需要包括那些与现实问题相关的就行了。

#### 用类描述人之间的血缘关系



#### 目录和文件

两个类之间可以互相引用，MyFile引用了MyFolder，而MyFolder也引用了MyFile。



类中应该定义哪些变量和方法，这是与要解决的问题密切相关的。

类之间的组合关系在Java中实现的都是引用，但在逻辑关系上，有两种明显不同的关系，一种是**包含**，另一种是**单纯引用**。比如，在订单类Order中，Order与User的关系就是单纯引用，User是独立存在的；而Order与OrderItem的关系就是包含，OrderItem总是从属于某一个Order。



**分解现实问题中涉及的概念以及概念间的关系，将概念表示为多个类，通过类之间的组合来表达更为复杂的概念以及概念间的关系，是计算机程序的一种基本思维方式。**



### 3.3 代码的组织机制

使用任何语言进行编程都有一个类似的问题，那就是**如何组织代码？** 具体就是：<u>如何避免命名冲突？如何合理组织各种源文件？如何使用第三方库？各种代码和依赖库如何编译链接为一个完整的程序？</u>

#### 包的概念

**完全限定名**

Java API中所有的类和接口都位于包java或javax下，java是标准包，javax是扩展包。

包声明语句应该位于源代码的最前面，前面不能有注释外的其他语句。

包名和文件目录结构必须匹配。

如果代码需要公开给其他人用，最好有一个域名以确保唯一性。

包可以方便模块化开发，不同功能可以位于不同包内，不同开发人员负责不同的包。包也可以方便封装，供外部使用的类可以放在包的上层，而内部的实现细节则可以放在比较底层的子包内。

同一个包下的类之间互相引用是不需要包名的。不同包，两种方式使用：**一种是通过类的完全限定名；另外一种是将用到的类引入当前类**。只有一个例外，<u>java.lang包下的类可以直接使用，不需要引入</u>，也不需要使用完全限定名，比如String类、System类，其他包内的类则不行。

`import java.util.*`，这个引入不能递归，它只会引入java.util包下的直接类，而不会引入java.util下嵌套包内的类。

静态导入不应过度使用，否则难以区分访问的是哪个类的代码。

同一个包指的是同一个直接包，子包下的类并不能访问。

#### jar包

各种程序语言大多有打包的概念，打包的一般不是源代码，而是编译后的代码。

在Java中，编译后的一个或多个包的Java class文件可以打包为一个文件，Java中打包命令为jar，打包后的文件扩展名为`.jar`，一般称之为jar包。jar包其实就是一个压缩文件。

Java类库、第三方类库都是以jar包形式提供的。如何使用jar包呢？将其加入类路径（classpath）中即可。



#### 程序的编译与链接

从Java源代码到运行的程序，有编译和链接两个步骤。编译是将源代码文件变成扩展名是.class的一种字节码，这个工作一般是由`javac`命令完成的。链接是在运行时动态执行的，.class文件不能直接运行，运行的是Java虚拟机，虚拟机听起来比较抽象，执行的就是`java`命令，这个命令解析.class文件，转换为机器能识别的二进制代码，然后运行。所谓链接就是**根据引用到的类加载相应的字节码并执行**。

Java编译和运行时，都需要以参数指定一个classpath，即<font color=#FF8C00>类路径</font>。类路径可以有多个，对于直接的class文件，路径是class文件的根目录；对于jar包，路径是jar包的完整名称（包括路径和jar包名）。在Windows系统中，多个路径用分号“; ”分隔；在其他系统中，以冒号“:”分隔。

import是编译时概念，用于确定完全限定名，在运行时，只根据完全限定名寻找并加载类。

> 在Java 9中，引入了**模块**的概念，JDK和JRE都按模块化进行了重构，传统的组织机制依然是支持的，但新的应用可以使用模块。**一个应用可由多个模块组成，一个模块可由多个包组成。模块之间可以有一定的依赖关系，一个模块可以导出包给其他模块用，可以提供服务给其他模块用，也可以使用其他模块提供的包，调用其他模块提供的服务。对于复杂的应用，模块化有很多好处，比如更强的封装、更为可靠的配置、更为松散的耦合、更动态灵活等。**



## 4 类的继承

<font color=#FF8C00>分类</font>

计算机程序经常使用类之间的继承关系来表示对象之间的分类关系。

使用继承一方面可以复用代码，公共的属性和行为可以放到父类中，而子类只需要关注子类特有的就可以了；另一方面，不同子类的对象可以更为方便地被统一处理。

### 4.1 基本概念

根父类`Object`：在Java中，即使没有声明父类，也有一个隐含的父类。

每个类有且只有一个父类，没有声明父类的，其父类为Object，子类继承了父类非private的属性和方法，可以增加自己的属性和方法，以及重写父类的方法实现。

new过程中，父类先进行初始化，可通过super调用父类相应的构造方法，没有使用super的情况下，调用父类的默认构造方法（super必须放在第一行）。

子类变量和方法与父类重名的情况下，可通过super强制访问父类的变量和方法。

super的使用与this有点像，但super和this是不同的，this引用一个对象，是实实在在存在的，可以作为函数参数，可以作为返回值，但super只是一个关键字，不能作为参数和返回值，它只是用于告诉编译器访问父类的相关变量和方法。

```java
public class ShapeManager {
    private static final int MAX_NUM = 100;
    private Shape[] shapes = new Shape[MAX_NUM];
    private int shapeNum = 0;

    public void addShape(Shape shape) {
        if (shapeNum < MAX_NUM) {
            shapes[shapeNum++] = shape;
        }
    }

    public void draw() {
        for (int i = 0; i < shapeNum; i++) {
            shapes[i].draw();
        }
    }

    public static void main(String[] args) {
        ShapeManager shapeManager = new ShapeManager();
        shapeManager.addShape(new Line(new Point(1, 2), new Point(3, 5), "red"));
        shapeManager.addShape(new Circle(new Point(5, 9), 2));
        shapeManager.addShape(new ArrowLine(new Point(6, 7), new Point(8, 11), "blue", true, false));

        shapeManager.draw();
    }
}
```

继承的一个好处是**可以统一处理不同子类型的对象**。

子类对象赋值给父类引用变量，这叫**向上转型**。

变量shape可以引用任何Shape子类类型的对象，这叫**多态**，**即一种类型的变量，可引用多种实际类型对象**。

这样，对于变量shape，它就有两个类型：类型Shape，我们称之为shape的**静态类型**；类型Circle/Line/ArrowLine，我们称之为shape的**动态类型**。在ShapeManager的draw方法中，shapes[i].draw()调用的是其对应动态类型的draw方法，这称之为方法的**动态绑定**。

> 为什么要有多态和动态绑定呢？
>
> 创建对象的代码（ShapeManager以外的代码）和操作对象的代码（ShapeManager本身的代码），经常不在一起，操作对象的代码往往只知道对象是某种父类型，也往往只需要知道它是某种父类型就可以了。

<font color=#FF8C00>**多态和动态绑定是计算机程序的一种重要思维方式，使得操作对象的程序不需要关注对象的实际类型，从而可以统一处理不同对象，但又能实现每个对象的特有行为。**</font>

总结：子类对象可以赋值给父类引用变量，这叫多态；实际执行调用的是子类实现，这叫动态绑定。

### 4.2 继承的细节

#### 构造方法

父类只有一个带参数的构造方法，没有默认构造方法的时候，它的任何子类都必须在构造方法中通过super调用父类的带参数构造方法。

> 在父类构造方法中调用可被子类重写的方法，是一种不好的实践，容易引起混淆，应该只调用private的方法。

#### 重名与静态绑定

> 子类与父类中实例变量、静态方法和静态变量可以重名吗？如果重名，访问的是哪一个呢？
>
> 重名是可以的，重名后实际上有两个变量或方法。private变量和方法只能在类内访问，访问的也永远是当前类的，即：在子类中访问的是子类的；在父类中访问的是父类的，它们只是碰巧名字一样而已，没有任何关系。
>
> public变量和方法，则要看如何访问它。在类内，访问的是当前类的，但子类可以通过super．明确指定访问父类的。在类外，则要看访问变量的静态类型：静态类型是父类，则访问父类的变量和方法；静态类型是子类，则访问的是子类的变量和方法。

```java
public class Base {
    public static String s = "static_base";
    public String m = "base";
    public static void staticTest(){
        System.out.println("base static: "+s);
    }
}
public class Child extends Base {
    public static String s = "child_base";
    public String m = "child";
    public static void staticTest(){
        System.out.println("child static: "+s);
    }
}
```

```java
    public static void main(String[] args) {
        Child c = new Child();
        Base b = c;
        System.out.println(b.s);
        System.out.println(b.m);
        b.staticTest();
        System.out.println(c.s);
        System.out.println(c.m);
        c.staticTest()
    }
```

结果为

```
        static_base
        base
        base static: static_base
        child_base
        child
        child static: child_base
```

当通过b（静态类型Base）访问时，访问的是Base的变量和方法，当通过c（静态类型Child）访问时，访问的是Child的变量和方法，这称之为**静态绑定**，即访问绑定到变量的静态类型。静态绑定在程序编译阶段即可决定，而动态绑定则要等到程序运行时。**实例变量、静态变量、静态方法、private方法，都是静态绑定的**。

#### 重载和重写

重载是指方法名称相同但参数签名不同（参数个数、类型或顺序不同），重写是指子类重写与父类相同参数签名的方法。

当有多个重名函数的时候，在决定要调用哪个函数的过程中，首先是按照参数类型进行匹配的，换句话说，寻找在所有重载版本中最匹配的，然后才看变量的动态类型，进行动态绑定。

#### 父子类型转换

```java
Base b = new Base();
Child c = (Child)b;
```

语法上Java不会报错，但运行时会抛出错误，错误为类型转换异常。

一个父类的变量能不能转换为一个子类的变量，取决于这个父类变量的动态类型（即引用的对象类型）是不是这个子类或这个子类的子类。

可通过`instanceof`来判断一个父类的变量是不是某个子类的对象：

```java
public boolean canCast(Base b) {
  return b instanceof Child;
}
```

#### 继承访问权限protected

模板方法在很多框架中有广泛的应用，这是使用protected的一种常见场景。🔖

#### 可见性重写

重写方法时，一般并不会修改方法的可见性；如果要修改，子类方法不能降低父类方法的可见性。

> 为什么要这样规定呢？
>
> 继承反映的是“is-a”的关系，即子类对象也属于父类，子类必须支持父类所有对外的行为，将可见性降低就会减少子类对外的行为，从而破坏“is-a”的关系，但子类可以增加父类的行为，所以提升可见性是没有问题的。

#### 防止继承final

一个Java类，默认情况下都是可以被继承的，但加了final关键字之后就不能被继承了。另外final实例方法不能被重写。

### 4.3 继承实现的基本原理

#### 示例

```java
public class Base {
    public static int s;
    private int a;
    static {
        System.out.println("基类静态代码块，s：" + s);
        s = 1;
    }
    {
        System.out.println("基类实例代码块，a：" + a);
        a = 1;
    }

    public Base() {
        System.out.println("基类构造方法，a：" + a);
        a = 2;
    }

    protected void step() {
        System.out.println("base s: " + s + ", a:" + a);
    }

    public void action() {
        System.out.println("start");
        step();
        System.out.println("end");
    }
}


public class Child extends Base {
    public static int s;
    private int a;
    static {
        System.out.println("子类静态代码块，s：" + s);
        s = 10;
    }
    {
        System.out.println("子类实例代码块，a：" + a);
        a = 10;
    }

    public Child() {
        System.out.println("子类构造方法，a：" + a);
        a = 20;
    }

    protected void step() {
        System.out.println("child s: " + s + ", a:" + a);
    }
}
```

```java
    public static void main(String[] args) {
        System.out.println("---- new Child()");
        Child c = new Child();
        System.out.println("\n---- c.action()");
        c.action();
        Base b = c;
        System.out.println("\n---- b.action()");
        b.action();
        System.out.println("\n---- b.s: " + b.s);
        System.out.println("\n---- c.s: " + c.s);
    }
```

结果：

```
基类静态代码块，s：0
---- new Child()
子类静态代码块，s：0
基类实例代码块，a：0
基类构造方法，a：1
子类实例代码块，a：0
子类构造方法，a：10

---- c.action()
start
child s: 10, a:20
end

---- b.action()
start
child s: 10, a:20
end

---- b.s: 1

---- c.s: 10

```



#### 类加载过程

Java中，所谓类的加载是指将类的相关信息加载到内存。在Java中，类是动态加载的，当第一次使用这个类的时候才会加载，加载一个类时，会查看其父类是否已加载，如果没有，则会加载其父类。

1. 一个类的信息主要包括以下部分：
   - 类变量（静态变量）；
   - 类初始化代码；
   - 类方法（静态方法）；
   - 实例变量；
   - 实例初始化代码；
   - 实例方法；
   - 父类信息引用
2. 类初始化代码包括：
   - 定义静态变量时的赋值语句；
   - 静态初始化代码块。
3. 实例初始化代码包括：
   - 定义实例变量时的赋值语句；
   - 实例初始化代码块；
   - 构造方法。
4. 类加载过程包括：
   - 分配内存保存类的信息；
   - 给类变量赋默认值；
   - 加载父类；
   - 设置父子关系；
   - 执行类初始化代码。

栈存放函数的局部变量；而堆存放动态分配的对象；存放类的信息，这个区在Java中称为**方法区**。

上面的示例，加载后，Java方法区有3份类信息，分别是Child、Base、Object。

![](images/image-20220313100828456.png)

#### 对象创建的过程

在类加载之后，new Child()就是创建Child对象，创建对象过程包括：

1. 分配内存；
2. 对所有实例变量赋默认值；
3. 执行实例初始化代码。

分配的内存包括本类和所有父类的实例变量，但不包括任何静态变量。实例初始化代码的执行从父类开始，再执行子类的。但在任何类执行初始化代码之前，所有实例变量都已设置完默认值。

每个对象除了保存类的实例变量之外，还保存着实际类信息的引用。

`Child c = new Child();`会将新创建的Child对象引用赋给变量c，而`Base b = c;`会让b也引用这个Child对象。

![image-20220313103844791](images/image-20220313103844791.png)

引用型变量c和b分配在栈中，它们指向相同的堆中的Child对象。Child对象存储着方法区中Child类型的地址，还有Base中的实例变量a和Child中的实例变量a

#### 方法调用的过程

`c.action();` 执行过程：

1. 查看c的对象类型，找到Child类型，在Child类型中找action方法，发现没有，到父类中寻找；
2. 在父类Base中找到了方法action，开始执行action方法；
3. action先输出了start，然后发现需要调用step()方法，就从Child类型开始寻找step()方法；
4. 在Child类型中找到了step()方法，执行Child中的step()方法，执行完后返回action方法；
5. 继续执行action方法，输出end。

寻找要执行的实例方法的时候，是从对象的实际类型信息开始查找的，找不到的时候，再查找父类类型信息。

`b.action()`和`c.action()`的输出是一样的，这称为动态绑定，而动态绑定实现的机制就是**根据对象的实际类型查找要执行的方法，子类型中找不到的时候再查找父类。**

如果继承的层次比较深，要调用的方法位于比较上层的父类，则调用的效率是比较低的，因为每次调用都要进行很多次查找。大多数系统使用一种称为虚方法表的方法来优化调用的效率。

**虚方法表**，就是在类加载的时候为每个类创建一个表，记录该类的对象所有动态绑定的方法（包括父类的方法）及其地址，但一个方法只有一条记录，子类重写了父类方法后只会保留子类的。

![image-20220313104421843](images/image-20220313104421843.png)

#### 变量访问的过程

**对变量的访问是静态绑定的，无论是类变量还是实例变量。**代码中演示的是类变量：b.s和c.s，通过对象访问类变量，系统会转换为直接访问类变量Base.s和Child.s。

### 4.4 为什么说继承是把双刃剑

> 继承为什么会有破坏力呢？
>
> 因为继承可能破坏封装，而封装可以说是程序设计的第一原则；另外，继承可能没有反映出is-a关系。

#### 继承破坏封装

> **封装**就是隐藏实现细节，提供简化接口。实现细节可以随时修改，而不影响使用者。
>
> 函数是封装，类也是封装。
>
> 通过封装，才能在更高的层次上考虑和解决问题。
>
> 没有封装，代码之间会到处存在着实现细节的依赖，则构建和维护复杂的程序是难以想象的。

继承可能破坏封装是因为**子类和父类之间可能存在着实现细节的依赖**。子类在继承父类的时候，往往不得不关注父类的实现细节，而父类在修改其内部实现的时候，如果不考虑子类，也往往会影响到子类。

#### 封装是如何被破坏的

```java
public class Base {
  private static final init MAX_NUM = 1000;
  private int[] arr = new int[MAX_NUM];
  private int count;
  
  public void add(int number) {
    if (count < MAX_NUM) {
      arr[count++] = number;
    }
  }
  
  public void addAll(int[] numbers) {
    for(int num : numbers) {
      add(num);
    } 
  }
}
```



```java
public class Child extends Base {
  private long sum;
  @Override
  public void add(int number) {
    super.add(number);
    sum += number;
  }
  
  @Override
  public void addAll(int[] numbers) {
    super.addAll(numbers);
    for (int i=0; i<numbers.length; i++) {
      sum += numbers[i];
    }
  }
  
  public long getSum() {
    return sum;
  }
}
```



子类和父类之间是细节依赖，子类扩展父类，仅仅知道父类能做什么是不够的，还需要知道父类是怎么做的，而父类的实现细节也不能随意修改，否则可能影响子类。

父类不能随意增加公开方法，因为给父类增加就是给所有子类增加，而子类可能必须要重写该方法才能确保方法的正确性。

小结：<u>对于子类而言，通过继承实现是没有安全保障的，因为父类修改内部实现细节，它的功能就可能会被破坏；而对于基类而言，让子类继承和重写方法，就可能丧失随意修改内部实现的自由。</u>

#### 继承没有反映is-a关系

现实中，设计完全符合is-a关系的继承关系是困难的。

但对于通过父类引用操作子类对象的程序而言，它是把对象当作父类对象来看待的，期望对象符合父类中声明的属性和行为。如果不符合，结果是什么呢？混乱。

#### 如何应对继承的双面性

两个思路：1 避免使用继承；2 正确使用继承。

避免继承的三种方法：

1. 使用final；
2. 优先使用组合而非继承；
3. 使用接口

使用继承的三种主要场景：

1. 基类是别人写的，我们写子类。主要是Java API、其他框架或类库中的类。注意
   - 重写方法不要改变预期的行为；
   - 阅读文档说明，理解可重写方法的实现机制，尤其是方法之间的依赖关系；
   - 在基类修改的情况下，阅读其修改说明，相应修改子类。
2. 我们写基类，别人可能写子类
   - 使用继承反映真正的is-a关系，只将真正公共的部分放到基类；
   - 对不希望被重写的公开方法添加final修饰符；
   - 写文档，说明可重写方法的实现机制，为子类提供指导，告诉子类应该如何重写；
   - 在基类修改可能影响子类时，写修改说明。
3. 基类、子类都是我们写的。





## 5 类的扩展

类相当于是自定义数据类型，通过类的组合和继承可以表示和操作各种事物或者说对象。

### 5.1 接口的本质

只是将对象看作属于某种数据类型，并按该类型进行操作，在一些情况下，并不能反映对象以及对对象操作的本质。

对象的类型

对象的**能力**

#### 接口的概念

接口声明了一组能力，但它自己并没有实现这个能力，它只是一个约定。

**双方对象并不直接互相依赖，它们只是通过接口间接交互**。

![](images/image-20220313105258399.png)

#### 定义接口

> ”比较“，很多时候不关心对象的类型，而是关系对象有没有可比较的能力。
>
> Java API中提供了`Comparable接口`，以表示可比较的能力。

```java
public interface MyComparable {
  int compareTo(Object other);
}
```

接口与类不同，它的方法没有实现代码。定义一个接口本身并没有做什么，也没有太大的用处，它还需要至少两个参与者：一个需要实现接口，另一个使用接口。

#### 实现接口

```java
public class Point implements MyComparable{
    ...
      
    @Override
    public int comparableTo(Object other) {
        if (!(other instanceof Point)) {
            throw new IllegalArgumentException();
        }
        Point otherPoint = (Point) other;
        double delta = distance() - otherPoint.distance();

        if (delta < 0) {
            return -1;
        } else if (delta > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
```



#### 使用接口

与类不同，接口不能new，不能直接创建一个接口对象，对象只能通过类来创建。但可以声明接口类型的变量，引用实现了接口的类对象。

```java
public class CompUtil {
    public static Object max(MyComparable[] objs){
        if(objs==null||objs.length==0){
            return null;
        }
        MyComparable max = objs[0];
        for(int i=1; i<objs.length; i++){
            if(max.compareTo(objs[i])<0){
                max = objs[i];
            }
        }
        return max;
    }
    public static void sort(MyComparable[] objs){
        for(int i=0; i<objs.length; i++){
            int min = i;
            for(int j=i+1; j<objs.length; j++){
                if(objs[j].compareTo(objs[min])<0){
                    min = j;
                }
            }
            if(min!=i){
                 Comparable temp = objs[i];
                 objs[i] = objs[min];
                 objs[min] = temp;
            }
        }
    }
}

```



#### 接口的细节

1. 接口中的变量。修饰符自动是`public static final`，可以不写，通过`接口名.变量名`方式使用。
2. 接口的继承，可以有多个父接口。
3. 类的继承与接口可以共存，关键字extends要放在implements之前。
4. instanceof也可用来判断一个对象是否实现了某接口。

#### 使用接口替代继承

继承至少有两个好处：一个是复用代码；另一个是利用多态和动态绑定统一处理多种不同子类的对象。

使用组合替代继承，可以复用代码，但不能统一处理。

使用接口替代继承，针对接口编程，可以实现统一处理不同类型的对象，但接口没有代码实现，无法复用代码。

将组合和接口结合起来替代继承，就既可以统一处理，又可以复用代码了。

```java
public interface IAdd {
  void add (int number);
  void addAll(int[] numbers);
}

public class Base implements IAdd {
  ...
  // 主体代码，与代码清单4-10一样
}

public class Child implements IAdd {
  // 主体代码，组合使用Base，与代码清单4-12一样
}
```

🔖怎么组合使用Base？



#### Java 8和Java 9对接口的增强

在Java 8之前，接口中的方法都是抽象方法，都没有实现体，Java 8允许在接口中定义两类新方法：**静态方法和默认方法**，它们有实现体。

```java
public interface IDemo {
  void hello();
  public static void test() {
    ...
  }
  default void hi() {
    ...
  }
}
```

在接口不能定义静态方法之前，相关的静态方法往往定义在单独的类中，比如，Java API中，Collection接口有一个对应的单独的类Collections，在Java 8中，就可以直接写在接口中了，比如Comparator接口就定义了多个静态方法。

默认方法有默认的实现，实现类可以改变它的实现，也可以不改变。**引入默认方法主要是函数式数据处理的需求，是为了便于给接口增加功能。**

在没有默认方法之前，Java是很难给接口增加功能的，比如List接口（第9章介绍），因为有太多非Java JDK控制的代码实现了该接口，如果给接口增加一个方法，则那些接口的实现就无法在新版Java上运行，必须改写代码，实现新的方法，这显然是无法接受的。

函数式数据处理需要给一些接口增加一些新的方法，所以就有了默认方法的概念，**接口增加了新方法，而接口现有的实现类也不需要必须实现**。

在Java 8中，静态方法和默认方法都必须是public的，Java 9去除了这个限制，它们都可以是private的，引入private方法主要是为了方便多个静态或默认方法复用代码：

```java
        public interface IDemoPrivate {
              private void common() {
                  System.out.println("common");
              }
              default void actionA() {
                  common();
              }
              default void actionB() {
                  common();
              }
          }
```

>  <font color=#FF8C00>针对接口而非具体类型进行编程，是计算机程序的一种重要思维方式。这种方式不仅可以复用代码，还可以降低耦合，提高灵活性，是分解复杂问题的一种重要工具。</font>

### 5.2 抽象类

抽象类就是抽象的类。**抽象是相对于具体而言的，一般而言，具体类有直接对应的对象，而抽象类没有，它表达的是抽象概念**，一般是具体类的比较上层的父类。比如，狗是具体对象，而动物则是抽象概念；樱桃是具体对象，而水果则是抽象概念；正方形是具体对象，而图形则是抽象概念。



#### 抽象方法和抽象类

定义了抽象方法的类必须被声明为抽象类，不过，抽象类可以没有抽象方法。抽象类和具体类一样，可以定义具体方法、实例变量等，它和具体类的核心区别是，**抽象类不能创建对象(比如，不能使用new Shape())，而具体类可以。**

一个类在继承抽象类后，<u>必须实现抽象类中定义的所有抽象方法</u>，除非它自己也声明为抽象类。

与接口类似，抽象类虽然不能使用new，但可以声明抽象类的变量，引用抽象类具体子类的对象：

```java
Shape shape = new Circle();
shape.draw();
```



#### 为什么需要抽象类

引入抽象方法和抽象类，是Java提供的一种语法工具，对于一些类和方法，引导使用者正确使用它们，减少误用。

使用抽象方法而非空方法体，子类就知道它必须要实现该方法，而不可能忽略，若忽略Java编译器会提示错误。使用抽象类，类的使用者创建对象的时候，就知道必须要使用某个具体子类，而不可能误用不完整的父类。

> 每个人都可能会犯错，减少错误不能只依赖人的优秀素质，还需要一些机制，使得一个普通人都容易把事情做对，而难以把事情做错。抽象类就是Java提供的这样一种机制。

#### 抽象类和接口

类似处：**都不能用于创建对象，接口中的方法其实都是抽象方法。**

不同处：接口中不能定义实例变量，而抽象类可以；一个类可以实现多个接口，但只能继承一个类。

抽象类和接口是配合而非替代关系，它们经常一起使用，**接口声明能力，抽象类提供默认实现**，实现全部或部分方法，一个接口经常有一个对应的抽象类。比如，在Java类库中，有：

- Collection接口和对应的AbstractCollection抽象类。
- List接口和对应的AbstractList抽象类。
- Map接口和对应的AbstractMap抽象类。

对于需要实现接口的具体类而言，有两个选择：

1. 实现接口，自己实现全部方法；
2. 继承抽象类，然后根据需要重写方法。

继承只重写部分代码，但如果具体类已经有父类，就只能选择实现接口。



```java
public abstract class AbstractAdder implements IAdd {
  @Override
  public void addAll(int[] numbers) {
    for(int num : numbers){
      add(num);
    }
  }
}


public class Base extends AbstractAdder {
  private static final int MAX_NUM = 1000;
  private int[] arr = new int[MAX_NUM];
  private int count;
  @Override
  public void add(int number){
    if(count<MAX_NUM){
      arr[count++] = number;
    }
  }
}
```



### 5.3 内部类的本质

内部类与包含它的外部类有比较密切的关系，而与其他类关系不大，定义在类内部，可以实现对外部完全隐藏，可以有更好的封装性，代码实现上也往往更为简洁。

内部类只是Java编译器的概念，对于Java虚拟机而言，它是不知道内部类这回事的，**每个内部类最后都会被编译为一个独立的类**，生成一个独立的字节码文件。

内部类可以方便地访问外部类的私有变量，可以声明为private从而实现对外完全隐藏，相关代码写在一起，写法也更为简洁，这些都是内部类的好处。

#### 静态内部类

静态内部类与静态变量和静态方法定义的位置一样，也带有static关键字。

```java
public class Outer {
  private static int shared = 100;
  public static class StaticInner {
    public void innerMethod() {
      System.out.println("inner " + shared);
    }
  }
  
  public void test() {
    StaticInner si = new StaticInner();
    si.innerMethod();
  }
}
```

静态内部类可以访问外部类的静态变量和方法，但不可以访问实例变量和方法。

public静态内部类可以被外部使用，只是需要通过“外部类.静态内部类”的方式使用。

静态内部类是怎么实现的？上面的代码实际生成两个类：`Outer`，`Outer$StaticInner`，大概代码：

```java
public class Outer {
  private static int shared = 100;
  public void test() {
    Outer$StaticInner si = new Outer$StaticInner();
    si.innerMethod();
  }
  
  static int access$0() {
    return shared;
  }
}

  public static class Outer$StaticInner {
    public void innerMethod() {
      System.out.println("inner " + Outer.access$0);
    }
  }
```

私有变量是不能被类外部访问的，内部类能访问的解决方法就是，**自动为Outer生成一个非私有访问法access$0，它返回这个私有静态变量shared**。

**静态内部类的使用场景是很多的，如果它与外部类关系密切，且不依赖于外部类实例，则可以考虑定义为静态内部类。**比如，一个类内部，如果既要计算最大值，又要计算最小值，可以在一次遍历中将最大值和最小值都计算出来，但怎么返回呢？可以定义一个类Pair，包括最大值和最小值，但Pair这个名字太普遍，而且它主要是类内部使用的，就可以定义为一个静态内部类。

Java API中使用静态内部类的例子：

- Integer类内部有一个私有静态内部类IntegerCache，用于支持整数的自动装箱。
- 表示链表的LinkedList类内部有一个私有静态内部类Node，表示链表中的每个节点。
- Character类内部有一个public静态内部类UnicodeBlock，用于表示一个Unicode block。

#### 成员内部类

```java
public class Outer {
  private int shared = 100;
  public  class Inner {
    public void innerMethod() {
      System.out.println("outer a " + a);
      Outer.this.action();
    }
    private void action() {
      System.out.println("action");
    }
  }
  
  public void test() {
    Inner si = new Inner();
    si.innerMethod();
  }
}
```

除了静态变量和方法，成员内部类还可以直接访问外部类的实例变量和方法(`Outer.this.action()`，如果没有重名，可以直接省略`Outer.this`)。

与静态内部类不同，**成员内部类对象总是与一个外部类对象相连**的，在外部使用时，它不能直接通过new Outer.Inner()的方式创建对象，而是要先将创建一个Outer类对象（`外部类对象.new 内部类()`）：

```java
Outer outer = new Outer();
Outer.Inner inner = outer.new Inner();
inner.innerMethod();
```

> 与静态内部类不同，成员内部类中**不可以定义静态变量和方法（final变量例外，它等同于常量）**，下面介绍的方法内部类和匿名内部类也都不可以。Java为什么要有这个规定呢？
>
> 可以这么理解，这些内部类是与外部实例相连的，不应独立使用，而静态变量和方法作为类型的属性和方法，一般是独立使用的，在内部类中意义不大，而如果内部类确实需要静态变量和方法，那么也可以挪到外部类中。

成员内部类示例的内部实现:

```java
public class Outer {
  private int a = 100;
  private void action() {
    System.out.println("action");
  }
  public void test() {
    Outer$Inner inner = new Outer$Inner(this);
    inner.innerMethod();
  }
  static int access$0(Outer outer) {
    return outer.a;
  }               
  static void access$1(Outer outer) {
    outer.action();
  }
}
public class Outer$Inner {
  final Outer outer;
  public Outer$Inner(Outer outer){
    ths.outer = outer;
  }
  public void innerMethod() {
    System.out.println("outer a " + Outer.access$0(outer));
    Outer.access$1(outer);
  }
}
```

Outer$Inner类有个实例变量outer指向外部类的对象，它在构造方法中被初始化，Outer在新建Outer$Inner对象时给它传递当前对象，由于内部类访问了外部类的私有变量和方法，外部类Outer生成了两个非私有静态方法：access$0用于访问变量a, access$1用于访问方法action。

应用场景：如果内部类与外部类关系密切，需要访问外部类的实例变量或方法，则可以考虑定义为成员内部类。外部类的一些方法的返回值可能是某个接口，为了返回这个接口，外部类方法可能使用内部类实现这个接口，这个内部类可以被设为private，对外完全隐藏。

比如，在Java API的类LinkedList中，它的两个方法listIterator和descendingIterator的返回值都是接口Iterator，调用者可以通过Iterator接口对链表遍历，listIterator和descendingIterator内部分别使用了成员内部类ListItr和DescendingIterator，这两个内部类都实现了接口Iterator。

#### 方法内部类

```java
public class Outer {
  private int a = 100;
  public void test(final int param){
    final String str = "hello";
    class Inner {
      public void innerMethod(){
        System.out.println("outer a " +a);
        System.out.println("param " +param);
        System.out.println("local var " +str);
      }
    }
    Inner inner = new Inner();
    inner.innerMethod();
  }
}
```

类Inner定义在外部类方法test中，方法内部类只能在定义的方法内被使用。

如果方法是实例方法，则除了静态变量和方法，内部类还可以直接访问外部类的实例变量和方法。

如果方法是静态方法，则方法内部类只能访问外部类的静态变量和方法。

方法内部类还可以直接访问方法的参数和方法中的局部变量，不过，这些变量必须被声明为final。

方法内部类的内部实现：

```java
public class Outer {
  private int a = 100;
  public void test(final int param) {
    final String str = "hello";
    OuterInner inner = new OuterInner(this, param);
    inner.innerMethod();
  }
  static int access$0(Outer outer){
    return outer.a;
  }
}
public class OuterInner {
  Outer outer;
  int param;
  OuterInner(Outer outer, int param){
    this.outer = outer;
    this.param = param;
  }
  public void innerMethod() {
    System.out.println("outer a " + Outer.access$0(this.outer));
    System.out.println("param " + param);
    System.out.println("local var " + "hello");
  }
}
```





#### 匿名内部类

匿名内部类没有单独的类定义，它在创建对象的同时定义类，语法如下：

```java
new 父类(参数列表) {
  // 匿名内部类实现部分
}
```

或者

```java
new 父接口() {
  // 匿名内部类实现部分
}
```

匿名内部类是与new关联的，在创建对象的时候定义类，new后面是父类或者父接口，然后是圆括号()，里面可以是传递给父类构造方法的参数，最后是大括号{}，里面是类的定义。

```java
public class Outer {
  public void test(final int x, final int y){
    Point p = new Point(2,3){
      @Override
      public double distance() {
        return distance(new Point(x, y));
      }
    };
    System.out.println(p.distance());
  }
}
```

创建Point对象的时候，定义了一个匿名内部类，这个类的父类是Point，创建对象的时候，给父类构造方法传递了参数2和3，重写了distance()方法，在方法中访问了外部方法final参数x和y。

**匿名内部类只能被使用一次，用来创建一个对象。它没有名字，没有构造方法，但可以根据参数列表，调用对应的父类构造方法。**

匿名内部类示例的内部实现:

```java
public class Outer {
  public void test(final int x, final int y){
    Point p = new Outer$1(this,2,3, x, y);
    System.out.println(p.distance());
  }
}
public class Outer$1 extends Point {
  int x2;
  int y2;
  Outer outer;
  Outer$1(Outer outer, int x1, int y1, int x2, int y2){
    super(x1, y1);
    this.outer = outer;
    this.x2 = x2;
    this.y2 = y2;
  }
  @Override
  public double distance() {
    return distance(new Point(this.x2, y2));
  }
}
```

> 内部类本质上都会被转换为独立的类，但一般而言，它们可以实现更好的封装，代码实现上也更为简洁。

### 5.4 枚举的本质

```java
public enum Size {
    SMALL, MEDIUM, LARGE
}
```

枚举类型可以定义为一个单独的文件，也可以定义在其他类内部。

枚举变量的toString方法返回其字面值，所有枚举类型也都有一个name()方法，返回值与toString()一样:

```java
Size size = Size.MEDIUM;
System.out.println(size.toString());
System.out.println(size.name());
// MEDIUM
```

枚举变量可以使用equals和==进行比较，结果是一样的。

枚举值是有顺序的，可以比较大小。枚举类型都有一个方法int ordinal()，表示枚举值在声明时的顺序，从0开始。枚举类型都实现了Java API中的Comparable接口，都可以通过方法compareTo与其他枚举值进行比较。比较其实就是比较ordinal的大小。

枚举变量可以用于和其他类型变量一样的地方，如方法参数、类变量、实例变量等，还可以用于switch语句。

枚举类型也都有一个静态的values方法，返回一个包括所有枚举值的数组，顺序与声明时的顺序一致。

```java
for (Size s : Size.values()) {
  System.out.println(s);
}
```



> 枚举类型本质上也是类，但由于编译器自动做了很多事情，因此它的使用更为简洁、安全和方便。

#### 典型场景



## 6 异常

出错的原因，**不可控的内部原因**，比如内存不够了、磁盘满了；**不可控的外部原因**，比如网络连接有问题；更多的可能是**程序的编写错误**，比如引用变量未初始化就直接调用实例方法。

### 6.1 初识异常

Java的默认异常处理机制是退出程序，异常发生点后的代码都不会执行。

return代表正常退出，throw代表异常退出；return的返回位置是确定的，就是上一级调用者，而throw后执行哪行代码则经常是不确定的，由异常处理机制动态确定。

异常处理机制会从当前函数开始查找看谁“捕获”了这个异常，当前函数没有就查看上一层，直到主函数，如果主函数也没有，就使用默认机制，即输出异常栈信息并退出，这正是我们在屏幕输出中看到的。

捕获异常后，程序就不会异常退出了，但try语句内异常点之后的其他代码就不会执行了，执行完catch内的语句后，程序会继续执行catch花括号外的代码。

异常是相对于return的一种退出机制，可以由系统触发，也可以由程序通过throw语句触发，异常可以通过try/catch语句进行捕获并处理，如果没有捕获，则会导致程序退出并输出异常栈信息。

### 6.2 异常类

Throwable类有两个主要参数：一个是message，表示异常消息；另一个是cause，表示触发该异常的其他异常。异常可以形成一个异常链，上层的异常由底层异常触发，cause表示底层异常。Throwable还有一个public方法用于设置cause：

```java
Throwable initCause(Throwable cause)
```

#### 异常类体系

![image-20220313115559942](images/image-20220313115559942.png)

Error表示系统错误或资源耗尽，由Java系统自己使用，应用程序不应抛出和处理，比如虚拟机错误（VirtualMacheError）及其子类内存溢出错误（OutOfMemory-Error）和栈溢出错误（StackOverflowError）。

Exception表示应用程序错误，它有很多子类，应用程序也可以通过继承Exception或其子类创建自定义异常，比如IOException（输入输出I/O异常）、RuntimeException（运行时异常）、SQLException（数据库SQL异常）。

RuntimeException比较特殊，它的名字有点误导，因为其他异常也是运行时产生的，它表示的实际含义是**未受检异常**（unchecked exception），相对而言，Exception的其他子类和Exception自身则是**受检异常**（checked exception）,Error及其子类也是未受检异常。

受检（checked）和未受检（unchecked）的区别在于Java如何处理这两种异常。<u>对于受检异常，Java会强制要求程序员进行处理，否则会有编译错误，而对于未受检异常则没有这个要求。</u>

![](images/image-20220313115828549.png)

#### 自定义异常

如果父类是RuntimeException或它的某个子类，则自定义异常也是未受检异常；如果是Exception或Exception的其他子类，则自定义异常是受检异常。

### 6.3 异常处理

catch、throw、finally、try-with-resources和throws

#### catch匹配

catch可以有多条，每条对应一种异常类型。

需要注意的是，抛出的异常类型是catch中声明异常的子类也算匹配，所以需要将最具体的子类放在前面，如果基类Exception放在前面，则其他更具体的catch代码将得不到执行。

e.getMessage()获取异常消息，e.printStackTrace()打印异常栈到标准错误输出流。

#### 重新抛出异常

在catch块内处理完后，可以重新抛出异常，异常可以是原来的，也可以是新建的

> 为什么要重新抛出呢？
>
> 因为当前代码不能够完全处理该异常，需要调用者进一步处理。
>
> 为什么要抛出一个新的异常呢？
>
> 当然是因为当前异常不太合适。不合适可能是信息不够，需要补充一些新信息；还可能是过于细节，不便于调用者理解和使用，如果调用者对细节感兴趣，还可以继续通过getCause()获取到原始异常。

#### finally



#### try-with-resources



#### throws



#### 对比受检和未受检异常



无论是受检异常还是未受检异常，无论是否出现在throws声明中，都应该在合适的地方以适当的方式进行处理。

### 6.4 如何使用异常

#### 异常应该且仅用于异常情况



#### 异常处理的目标

异常三种来源：**用户、程序员、第三方**。

处理的目标可以分为**恢复和报告**。恢复是指通过程序自动解决问题。报告的最终对象可能是用户，即程序使用者，也可能是系统运维人员或程序员。报告的目的也是为了恢复，但这个恢复经常需要人的参与。

程序都不应该假定第三方是可靠的，应该有容错机制。

#### 异常处理的一般逻辑



在有了异常机制后，程序的正常逻辑与异常逻辑可以相分离，异常情况可以集中进行处理，异常还可以自动向上传递，不再需要每层方法都进行处理，异常也不再可能被自动忽略。



## 7 常用基础类

### 7.1 包装类

![image-20220313120736487](images/image-20220313120736487.png)

> 包装类有什么用呢？
>
> Java中很多代码（比如容器类）只能操作对象，为了能操作基本类型，需要使用其对应的包装类。另外，包装类提供了很多有用的方法，可以方便对数据的操作。

#### 基本用法

各个包装类都可以与其对应的基本类型相互转换，转换代码结构是类似的，每种包装类都有一个静态方法**valueOf()**，接受基本类型，返回引用类型，也都有一个实例方法**xxxValue()**返回对应的基本类型。

将基本类型转换为包装类的过程，一般称为“**装箱**”，而将包装类型转换为基本类型的过程，则称为“**拆箱**”。Java 5引入自动装箱和拆箱:

```java
Integer a = 100;
int b = a;
```

自动装箱/拆箱是Java编译器提供的能力，它会替换为调用对应的valueOf/xxx-Value方法，如：

```java
Integer a = Integer.valueOf(100);
int b = a.intValue();
```

每种包装类都有构造方法，可以通过new 创建（但建议使用valueOf方法）。

#### 共同点

##### 1 重写了Object的方法

1. equals

Object类的默认实现是比较地址，对于两个变量，<u>只有这两个变量指向同一个对象时</u>，equals才返回true，和比较运算符（==）的结果是一样的。

包装类比较直接比较地址是不适合的，所以所有包装类都重写了equals方法，如`Long`类的：

```java
    public boolean equals(Object obj) {
        if (obj instanceof Long) {
            return value == ((Long)obj).longValue();
        }
        return false;
    }
```

`Float`的：

```java
    public boolean equals(Object obj) {
        return (obj instanceof Float)
               && (floatToIntBits(((Float)obj).value) == floatToIntBits(value));
    }
```

静态方法floatToIntBits()，将float的二进制表示看作int。**只有两个float的二进制表示完全一样的时候，equals才会返回true**。但小数计算，就算把浮点数看作整数，计算机运算结果可能不同的。

```java
Float f1 = 0.01f;
Float f2 = 0.1f＊0.1f;
System.out.println(f1.equals(f2));
System.out.println(Float.floatToIntBits(f1));
System.out.println(Float.floatToIntBits(f2));
```

结果：

```java
false
1008981770
1008981771
```



2. hashCode

hashCode返回一个对象的哈希值（一个int类型的数），由对象中**一般不变的属性映射得来，用于快速对对象进行区分、分组等**。

一个对象的哈希值不能改变，相同对象的哈希值必须一样。不同对象的哈希值一般应不同，但这不是必需的，可以有对象不同但哈希值相同的情况。

**对两个对象，如果equals方法返回true，则hashCode也必须一样**。反之不要求，equal方法返回false时，hashCode可以一样，也可以不一样，但应该尽量不一样。

hashCode的默认实现一般是**将对象的内存地址转换为整数**，子类如果重写了equals方法，也必须重写hashCode。之所以有这个规定，是因为Java API中很多类依赖于这个行为，尤其是容器中的一些类。

包装类都重写了hashCode，根据包装的基本类型值计算hashCode，对于Byte、Short、Integer、Character, hashCode就是其内部值，代码类似：

```java
public int hashCode() {
  return (int)value;
}
```

Boolean的是两个质数（质数用于哈希时比较好，不容易冲突）：

```java
public int hashCode() {
  return value ? 1231 : 1237;
}
```

Long的是高32位与低32位进行位异或操作：

```java
public int hashCode() {
  return (int)(value ^ (value >>> 32));
}
```

Float、Double的与equals方法类似，分别将其二进制表示看做int、long：

```java
@Override
public int hashCode() {
  return Float.hashCode(value);
}
public static int hashCode(float value) {
  return floatToIntBits(value);
}

@Override
public int hashCode() {
  return Double.hashCode(value);
}
public static int hashCode(double value) {
  long bits = doubleToLongBits(value);
  return (int)(bits ^ (bits >>> 32));
}
```

3. toString

##### 2 Comparable

每个包装类都实现了Comparable接口。

各个包装类的实现基本都是根据基本类型值进行比较。注意Boolean，Float、Double。

##### 3 包装类和String

除了Character外，每个包装类都有一个静态的`valueOf(String)`方法，根据字符串表示返回包装类对象，如：

```java
Boolean b = Boolean.valueOf("true");
Float f = Float.valueOf("123.45f");
```

除了Character外，每个包装类都有一个静态的`parseXXX(String)`方法，根据字符串表示返回基本类型值，如：

```java
boolean b = Boolean.parseBoolean("true");
double d = Double.parseDouble("123.45");
```

对于整数类型，字符串表示除了默认的十进制外，还可以表示为其他进制，如二进制、八进制和十六进制，包装类有静态方法进行相互转换，比如：

```java
System.out.println(Integer.toBinaryString(12345));       //输出二进制
System.out.println(Integer.toHexString(12345));          //输出十六进制
System.out.println(Integer.parseInt("3039", 16));        //按十六进制解析
```

输出：

```java
11000000111001
3039
12345
```



##### 4 常用常量

Boolean：

```java
public static final Boolean TRUE = new Boolean(true);
public static final Boolean FALSE = new Boolean(false);

public static final int    MIN_VALUE = 0x80000000;
public static final int    MAX_VALUE = 0x7fffffff;

public static final double POSITIVE_INFINITY = 1.0 / 0.0; //正无穷
public static final double NEGATIVE_INFINITY = -1.0 / 0.0; //负无穷
public static final double NaN = 0.0d / 0.0; //非数值
```

所有数值类型的包装类都定义了MAⅩ_VALUE和MIN_VALUE，比如Float：

```java
public static final float MAX_VALUE = 0x1.fffffeP+127f; // 3.4028235e+38f
public static final float MIN_VALUE = 0x0.000002P-126f; // 1.4e-45f
```

Float和Double还定义了一些特殊数值，比如正无穷、负无穷、非数值。



##### 5 Number

6种数值类型包装类有一个共同的抽象父类Number。它定义6个抽象方法：

```java
byte byteValue()
short shortValue()
int intValue()
long longValue()
float floatValue()
double doubleValue()
```

包装类实例可以通过这些方法返回任意的基本数值类型。

##### 6 不可变性

包装类都是不可变类。所谓不可变是指实例对象一旦创建，就没有办法修改了。

通过如下方式强制实现的：

- 所有包装类都声明为了final，不能被继承。
- 内部基本类型值是私有的，且声明为了final。
- 没有定义setter方法。



#### 剖析Integer与二进制算法🔖



##### 1 位翻转

```java
public static int reverse(int i)
public static int reverseBytes(int i)
```

位翻转就是将int当作二进制，左边的位与右边的位进行互换，reverse是按位进行互换， reverseBytes是按byte进行互换。

```java
int a = 0x12345678;
System.out.println(Integer.toBinaryString(a));
int r = Integer.reverse(a);
System.out.println(Integer.toBinaryString(r));
int rb = Integer.reverseBytes(a);
System.out.println(Integer.toHexString(rb));
```



##### 2 循环移位

```java
public static int rotateLeft(int i, int distance)
public static int rotateRight(int i, int distance)
```

循环移位，是相对于普通的移位而言的，普通移位，比如左移2位，原来的最高两位就没有了，右边会补0，而如果是循环左移两位，则原来的最高两位会移到最右边，就像一个左右相接的环一样。

##### 3 valueOf的实现

享元模式



#### 剖析Character🔖

##### 1. Unicode基础

Unicode给世界上每个字符分配了一个编号，编号范围为0x000000～0x10FFFF。编号范围在0x0000～0xFFFF的字符为常用字符集，称**BMP（Basic MultilingualPlane）字符**。编号范围在0x10000～0x10FFFF的字符叫做**增补字符（supplementary character）**。

##### 2 检查code point和char



##### 3  code point与char的转换



##### 4 按code point处理char数组或序列



##### 5 字符属性



##### 6 字符转换



### 7.2 剖析String

#### 基本用法

```java
public boolean isEmpty() //判断字符串是否为空
public int length() //获取字符串长度
public String substring(int beginIndex) //取子字符串
public String substring(int beginIndex, int endIndex) //取子字符串
public int indexOf(int ch) //查找字符，返回第一个找到的索引位置，没找到返回-1
public int indexOf(String str) //查找子串，返回第一个找到的索引位置，没找到返回-1
public int lastIndexOf(int ch) //从后面查找字符
public int lastIndexOf(String str) //从后面查找子字符串
public boolean contains(CharSequence s) //判断字符串中是否包含指定的字符序列
public boolean startsWith(String prefix) //判断字符串是否以给定子字符串开头
public boolean endsWith(String suffix) //判断字符串是否以给定子字符串结尾
public boolean equals(Object anObject) //与其他字符串比较，看内容是否相同
public boolean equalsIgnoreCase(String anotherString) //忽略大小写比较是否相同
public int compareTo(String anotherString) //比较字符串大小
public int compareToIgnoreCase(String str) //忽略大小写比较
public String toUpperCase() //所有字符转换为大写字符，返回新字符串，原字符串不变
public String toLowerCase() //所有字符转换为小写字符，返回新字符串，原字符串不变
public String concat(String str) //字符串连接，返回当前字符串和参数字符串合并结果
public String replace(char oldChar, char newChar) //字符串替换，替换单个字符
//字符串替换，替换字符序列，返回新字符串，原字符串不变
public String replace(CharSequence target, CharSequence replacement)
public String trim() //删掉开头和结尾的空格，返回新字符串，原字符串不变
public String[] split(String regex) //分隔字符串，返回分隔后的子字符串数组
```



#### 🔖走进String内部

String类内部用一个**字符数组**表示字符串，实例变量定义为：

```java
private final char value[];
```

String有两个构造方法，可以根据char数组创建String变量：

```java
public String(char value[])
public String(char value[], int offset, int count)
```



#### 编码转换

String内部是按UTF-16BE处理字符的，对BMP字符，使用一个char，两个字节，对于增补字符，使用两个char，四个字节。



#### 不可变性

String类也声明为了final，不能被继承，内部char数组value也是final的，初始化后就不能再变了。

#### 常量字符串

```java
System.out.println("老马说编程".length());
System.out.println("老马说编程".contains("老马"));
System.out.println("老马说编程".indexOf("编程"));
```

这些常量就是String类型的对象，在内存中，它们被放在一个共享的地方，这个地方称为**字符串常量池**，它保存所有的常量字符串，每个常量只会保存一份，被所有使用者共享。**当通过常量的形式使用一个字符串的时候，使用的就是常量池中的那个对应的String类型的对象。**



#### hashCode



#### 正则表达式

### 7.3 剖析StringBuilder

StringBuilder和StringBuffer类，这两个类的方法基本是完全一样的，它们的实现代码也几乎一样，唯一的不同就在于**StringBuffer类是线程安全的，而StringBuilder类不是**。

#### 基本实现原理









#### String的+和+=运算符



### 7.4 剖析Arrays

数组是存储多个同类型元素的基本数据结构，数组中的元素在内存连续存放，可以通过数组下标直接定位任意元素，相比在其他容器而言效率非常高。

Arrays包含很多对数组操作的静态方法。

#### 用法

##### 1.toString

包括8个基本类型和1个对象类型总共9个重载方法：

```java
public static String toString(int[] a)
public static String toString(Object[] a)
```

##### 2.排序

类似toString，sort方法有除boolean外其它7种基本类型和对象类型的很多重载方法，对象类型的对象要实现Comparable接口。

```java
public static void sort(int[] a)
public static void sort(int[] a, int fromIndex, int toIndex)
public static void sort(Object[] a)
public static void sort(Object[] a, int fromIndex, int toIndex)
...
```

sort还有另外两个重载方法，可以接受一个**比较器**（Comparator）作为参数：

```java
public static <T> void sort(T[] a, Comparator<? super T> c)
public static <T> void sort(T[] a, int fromIndex, int toIndex, Comparator<? super T> c)
```

Comparator接口在Java7定义：

```java
public interface Comparator<T> {
  int compare(T o1, T o2);
  boolean equals(Object obj);
}
```

sort方法在排序的过程中需要对对象进行比较的时候，就调用比较器的compare方法。在Java8中国Comparator增加了几个静态方法和默认方法。

String类有一个public静态成员，是表示忽略大小的比较器：

```java
public static final Comparator<String> CASE_INSENSITIVE_ORDER = new CaseInsensitiveComparator();
```

使用方式：

```java
String[] arr = {"hello", "world", "Break", "abc"};
Arrays.sort(arr, String.CASE_INSENSITIVE_ORDER);
System.out.println(Arrays.toString(arr));
```

> 传递比较器Comparator给sort方法，体现了程序设计中一种重要的思维方式。将不变和变化相分离，排序的基本步骤和算法是不变的，但按什么排序是变化的，sort方法将不变的算法设计为主体逻辑，而将变化的排序方式设计为参数，允许调用者动态指定，这也是一种常见的设计模式，称为策略模式，不同的排序方式就是不同的策略。

##### 3.查找

二分查找

```java
public static int binarySearch(int[] a, int key)
public static int binarySearch(int[] a, int fromIndex, int toIndex, int key)

public static int binarySearch(Object[] a, Object key)
          
public static <T> int binarySearch(T[] a, T key, Comparator<? super T> c)
          
```

##### 4.更多方法

- 复制(newLength表示从开始的长度)：

```java
public static long[] copyOf(long[] original, int newLength)
public static <T> T[] copyOf(T[] original, int newLength)
```

- 判断数组是否相同（长度相同且每个元素相同）：

```java
public static boolean equals(boolean[] a, boolean[] a2)
public static boolean equals(Object[] a, Object[] a2)  
```

- fill方法，为数组每个元素设置同一个值，或者一个范围设置成同一个值：

```java
public static void fill(int[] a, int val)
public static void fill(int[] a, int val, int fromIndex, int toIndex, int val)
```

- 哈希值

```java
public static int hashCode(int a[])
```

Java 8和9对Arrays类又增加了将数组转换为流、并行排序、数组比较等方法。

#### 多维数组

在创建多维数组时，除了第一维的长度需要指定外，其他维的长度不需要指定，甚至第一维中每个元素的第二维的长度可以不一样：

```java
int[][] arr = new int[2][];
arr[0] = new int[3];
arr[0] = new int[5];
```

Arrays中的toString、equals、hashCode都有对应的针对多维数组的deepⅩⅩⅩ方法：

```java
public static String deepToString(Object[] a)
public static boolean deepEquals(Object[] a1, Object[] a2)
public static int deepHashCode(Object a[])
```

#### 实现原理

🔖

[Apache Commons](https://commons.apache.org/)是java工具包集合，其中[commons-lang](https://github.com/apache/commons-lang)的类ArrayUtils包含更多常用数组操作。

### 7.5 剖析日期和时间

日期和时间是一个比较复杂的概念，Java 8之前的设计有一些不足，业界有一个广泛使用的第三方类库Joda-Time, Java 8受Joda-Time影响，重新设计了日期和时间API，新增了一个包java.time，使用了Lambda表达式。

#### 基本概念

##### 1.时区

共有24个时区，英国格林尼治是0时区（GMT），北京是东八区（GMT+8:00）。

##### 2.时刻和纪元时

格林尼治标准时间1970年1月1日0时0分0秒也被称为Epoch Time（纪元时）。

距离格林尼治标准时间1970年1月1日0时0分0秒的毫秒数是时刻。

##### 3.年历

简单总结下，时刻是一个绝对时间，对时刻的解读，则是相对的，与年历和时区相关。

#### 日期和时间API

三个主要类：

- `Date`：表示时刻，即绝对时间，与年月无关

- `Calendar`：表示年历，一个抽象类，其中表示公历的子类是GregorianCalendar

- `DateFormat`：表示格式化，能够将日期和时间与字符串进行相互转换，DateFormat也是一个抽象类，其中最常用的子类是SimpleDateFormat。

两个相关类：

- `TimeZone`：表示时区。

- `Locale`：表示国家（或地区）和语言。

##### 1.Date

两个构造方法：

```java
public Date(long date) {
  fastTime = date;
}
public Date() {
  this(System.currentTimeMillis());
}
```

System.currentTimeMillis()

Date中的大部分方法都已经过时，没有过时的主要方法：

```java
public long getTime() //返回毫秒数
public boolean equals(Object obj) //主要就是比较内部的毫秒数是否相同
//与其他Date进行比较，如果当前Date的毫秒数小于参数中的返回-1，相同返回0，否则返回1
public int compareTo(Date anotherDate)
public boolean before(Date when) //判断是否在给定日期之前
public boolean after(Date when) //判断是否在给定日期之后
public int hashCode() //哈希值算法与Long类似
```

##### 2.TimeZone

抽象类，有静态方法用于获取其实例，表示当前默认时区。

```java
TimeZone aDefault = TimeZone.getDefault();
System.out.println(aDefault.getID);
```

Java中有一个系统属性user.timezone，保存的就是默认时区。系统属性可通过System.getProperty获取：

```java
System.out.println(System.getProperty("user.timezone"));
```

系统属性可以在Java启动的时候传入参数进行更改:

```java
java -Duser.timezone=Asia/Shanghai xxxx
```

TimeZone也有静态方法，可以获得任意给定时区的实例:

```java
TimeZone tz = TimeZone.getTimeZone("US/Eastern");
TimeZone tz = TimeZone.getTimeZone("GMT+08:00");
```

##### 3.Locale



##### 4.Calendar



##### 5.DateFormat





#### 局限性

##### 1. Date中的过时方法



##### 2. Calendar操作比较烦琐



##### 3. DateFormat的线程安全性

DateFormat/SimpleDateFormat不是线程安全的。



解决方案：

- 每次使用DateFormat都新建一个对象。
- 用线程同步（第15章介绍）。
- 使用ThreadLocal（第19章介绍）。
- 使用Joda-Time或Java 8的API，它们是线程安全的。



### 7.6 随机

#### Math.random

静态方法，获得[0~1)的Double

#### Random

```java
Random rnd = new Random();
System.out.println(rnd.nextInt());
System.out.println(rnd.nextInt(100));
```

nextInt()产生一个随机的int，可能为正数，也可能为负数，nextInt(100)产生一个随机int，范围是0～100，包括0不包括100。

其它基本类型，也有类似的方法。

Random类还有一个构造方法，可以接受一个long类型的种子参数：

```java
public Random(long seed)
```

**种子决定了随机产生的序列，种子相同，产生的随机数序列就是相同的。**

```java
Random rnd2 = new Random(20220101);
for (int i = 0; i < 5; i++) {
  System.out.print(rnd2.nextInt(100) + " ");
}
```

程序的结构总是：

```
69 90 90 2 90 
```

**指定种子是为了实现可重复的随机。**

#### 随机的基本原理

Random产生的随机数不是真正的随机数，相反，它产生的随机数一般称为**伪随机数**。

🔖

基本原理是：**随机数基于一个种子，种子固定，随机数序列就固定，默认构造方法中，种子是一个真正的随机数。**

#### 随机密码



#### 洗牌

🔖

#### 带权重的随机选择



#### 抢红包算法



#### 北京购车摇号算法



Random类是线程安全的，也就是说，多个线程可以同时使用一个Random实例对象；

不过，如果并发性很高，会产生竞争，这时，可以考虑使用多线程库中的**ThreadLocalRandom**类。

另外，Java类库中还有一个随机类**SecureRandom**，可以产生安全性更高、随机性更强的随机数，用于安全加密等领域。



其它基础类，UUID、Math和Objects等。

# 三、泛型与容器

## 8 泛型

虽然泛型的基本思维和概念是比较简单的，但它有一些非常令人费解的语法、细节，以及局限性。

容器类是基于泛型的，不理解泛型，就难以深刻理解容器类。

### 8.1 基本概念和原理

泛型将接口的概念进一步延伸，“泛型”的字面意思就是**广泛的类型**。类、接口和方法代码可以应用于非常广泛的类型，<u>代码与它们能够操作的数据类型不再绑定在一起</u>，同一套代码可以用于多种数据类型，这样，不仅可以复用代码，降低耦合，而且可以提高代码的可读性和安全性。

#### 一个简单泛型类

##### 1.基本概念

```java
public class Pair<T> {
  T first;
  T second;
  public Pair(T first, T second) {
    this.first = first;
    this.second = second;
  }
  public T getFirst() {
    return first;
  }
  public T getSecond() {
    return second;
  }
}
```

泛型就是**类型参数**化，处理的数据类型不是固定的，而是可以作为参数传入。

```java
public class Pair<U, V> {
  U first;
  V second;
  public Pair(U first, V second) {
    this.first = first;
    this.second = second;
  }
  public U getFirst() {
    return first;
  }
  public V getSecond() {
    return second;
  }
}
```

使用：

```java
Pair<String, Integer> pair = new Pair<String, Integer>("老马",100);
```

或者简化为：

```java
Pair<String, Integer> pair = new Pair("老马",100);
```



##### 2.基本原理

为什么一定要定义类型参数？定义普通类，直接使用Object不就行了吗？

```java
public class Pair {
  Object first;
  Object second;
  public Pair(Object first, Object second){
    this.first = first;
    this.second = second;
  }
  public Object getFirst() {
    return first;
  }
  public Object getSecond() {
    return second;
  }
}
```

```java
Pair minmax = new Pair(1,100);
Integer min = (Integer)minmax.getFirst();
Integer max = (Integer)minmax.getSecond();
Pair kv = new Pair("name", "老马");
String key = (String)kv.getFirst();
String value = (String)kv.getSecond();
```

实际上，Java泛型的内部原理就是这样的。

Java有**Java编译器**和**Java虚拟机**，编译器将Java源代码转换为.class文件，虚拟机加载并运行class文件。对于泛型类，Java编译器会将泛型代码转换为普通的非泛型代码，就像上面的普通Pair类代码及其使用代码一样，<u>将类型参数T擦除，替换为Object，插入必要的强制类型转换</u>。**Java虚拟机实际执行的时候，它是不知道泛型这回事的，只知道普通的类及代码。**

##### 3.泛型的好处

- 更好的安全性
- 更好的可读性

> 语言和程序设计的一个重要目标是**将bug尽量消灭在摇篮里，能消灭在写代码的时候，就不要等到代码写完程序运行的时候**。

**类型安全**。通过使用泛型，开发环境（如Eclipse、IDEA）和编译器能确保不会用错类型，为程序多设置一道安全防护网。使用泛型，还可以省去烦琐的强制类型转换，再加上明确的类型信息，代码可读性也会更好。

#### 容器类

<font color=#FF8C00>容器类</font>，就是**容纳并管理多项数据的类**。

> 数组就是用来管理多项数据，但其限制比较多。
>
> **数据结构**，专门讨论管理数据的各种方式。这些数据结构在Java中的实现主要就是各种容器类。
>
> Java泛型的引入主要也是为了更好地支持Java容器。

```java
/**
 * 模拟一个简单的动态数组容器，类似ArrayList
 */
public class DynamicArray<E> {
  private static final int DEFAULT_CAPACITY = 10;
  private int size;
  private Object[] elementData;
  
  public DynamicArray() {
    this.elementData = new Object[DEFAULT_CAPACITY];
  }
  
  private void ensureCapacity(int minCapacity) {
    int oldCapacity = elementData.length;
    if (oldCapacity >= minCapacity) {
      return;
    }
    int newCapacity = oldCapacity * 2;
    if (newCapacity < minCapacity) {
      newCapacity = minCapacity;
    }
    elementData = Arrays.copyOf(elementData, newCapacity);
  }
  
  public void add(E e) {
    ensureCapacity(size + 1);
    elementData[size++] = e;
  }
  
  public E get(int index) {
    return (E)elementData[index];
  }
  
  public int size() {
    return size;
  }
  
  public E set(int index, E element) {
    E oldValue = get(index);
    elementData[index] = element;
    return oldValue;
  }
}
```

DynamicArray就是一个动态数组，它容纳的数据类型是作为参数传递过来的：

```java
DynamicArray<Double> arr = new DynamicArray<Double>();

DynamicArray<Pair<Integer, String>> arr = new DynamicArray<>()
```



#### 泛型方法

除了泛型类，方法也可以是泛型的，而且，一个方法是不是泛型的，与它所在的类是不是泛型没有什么关系。

与泛型类不同，调用方法时一般并不需要特意指定类型参数的实际类型。

#### 泛型接口

实现接口时，应该指定具体的类型。

#### 类型参数的限定

泛型的类型参数支持限定这个参数的一个<font color=#FF8C00>上界</font>，通过extens来表示。

##### 1.上界为某个具体的类

```java
public class NumberPair<U extends Number, V extends Number> extends Pair<U, V> {
  public NumberPair(U first, V second) {
    super(first, second);
  }
}
```

##### 2.上界为某个接口

```java
public static <T extends Comparable<T>> T max(T[] arr){
  T max = arr[0];
  for(int i=1; i<arr.length; i++){
    if(arr[i].compareTo(max)>0){
      max = arr[i];
    }
  }
  return max;
}
```

`<T extends Comparable<T>>`是一种令人费解的语法形式，这种形式称为递归类型限制，可以这么解读：T表示一种数据类型，必须实现Comparable接口，且必须可以与相同类型的元素进行比较。

##### 3.上界为其他类型参数

🔖



>  **泛型是计算机程序中一种重要的思维方式，它将数据结构和算法与数据类型相分离，使得同一套数据结构和算法能够应用于各种数据类型，而且可以保证类型安全，提高可读性。**

在Java中，泛型是通过**类型擦除**来实现的，它是Java编译器的概念，Java虚拟机运行时对泛型基本一无所知，理解这一点是很重要的，它有助于我们理解Java泛型的很多局限性。



### 8.2 解析通配符🔖

#### 更简洁的参数类型限定

```java
public void addAll(DynamicArray<? extends E> c) {
  for(int i = 0; i < c.size; i++) {
    add(c.get(i));
  }
}
```

`<? extends E>`表示**有限定通配符**。

`<T extends E>`和`<?extends E>`的关系：

1. `<T extends E>`用于定义类型参数，它声明了一个类型参数T，可放在泛型类定义中类名后面、泛型方法返回值前面。
2. `<? extends E>`用于实例化类型参数，它用于实例化泛型变量中的类型参数，只是这个具体类型是未知的，只知道它是E或E的某个子类型。

#### 理解通配符

泛型方法到底应该用通配符的形式还是加类型参数？

1. 通配符形式都可以用类型参数的形式来替代，通配符能做的，用类型参数都能做。
2. 通配符形式可以减少类型参数，形式上往往更为简单，可读性也更好，所以，能用通配符的就用通配符。
3. 如果类型参数之间有依赖关系，或者返回值依赖类型参数，或者需要写操作，则只能用类型参数。
4. 通配符形式和类型参数往往配合使用，比如，上面的copy方法，定义必要的类型参数，使用通配符表达依赖，并接受更广泛的数据类型。



#### 超类型通配符

`<? super E>`，称为**超类型通配符**，表示E的某个父类型。



#### 通配符比较

`<? >`、`<? super E>`和`<? extends E>`：

1. 它们的目的都是为了使方法接口更为灵活，可以接受更为广泛的类型。
2. `<? super E>`用于灵活写入或比较，使得对象可以写入父类型的容器，使得父类型的比较方法可以应用于子类对象，它不能被类型参数形式替代。
3. `<? >`和`<? extends E>`用于灵活读取，使得方法可以读取E或E的任意子类型的容器对象，它们可以用类型参数的形式替代，但通配符形式更为简洁。



### 8.3 细节和局限性

> 一项技术，往往只有理解了其局限性，才算是真正理解了它，才能更好地应用它。

#### 使用泛型类、方法和接口

- 基本类型不能用于实例化类型参数。因为类型参数会被替换为Object，所以Java泛型中不能使用基本数据类型。

  ```java
  // 不合法
  Pair<int> minmax = new Pair<int>(1,100);
  ```

- 运行时类型信息不适用于泛型。

- 类型擦除可能会引发一些冲突。

#### 定义泛型类、方法和接口

- 不能通过类型参数创建对象。
- 泛型类类型参数不能用于静态变量和方法。
- 了解多个类型限定的语法。

#### 泛型与数组

不能创建泛型数组。



## 9 列表和队列

### 9.1 剖析ArrayList

```java
  public boolean add(E e) //添加元素到末尾
  public boolean isEmpty() //判断是否为空
  public int size() //获取长度
  public E get(int index) //访问指定位置的元素
  public int indexOf(Object o) //查找元素， 如果找到，返回索引位置，否则返回-1
  public int lastIndexOf(Object o) //从后往前找
  public boolean contains(Object o) //是否包含指定元素，依据是equals方法的返回值
  public E remove(int index) //删除指定位置的元素， 返回值为被删对象
  public boolean remove(Object o) //删除指定对象，只删除第一个相同的对象，返回值表示是否删除了元素,如果o为null，则删除值为null的元素
  public void clear() //删除所有元素
  //在指定位置插入元素，index为0表示插入最前面，index为ArrayList的长度表示插到最后面
  public void add(int index, E element)
  public E set(int index, E element) //修改指定位置的元素内容
```

#### 基本原理

transient

#### 迭代

##### 1.迭代器接口

```java
public interface Iterable<T> {
  Iterator<T> iterator();
}
```

##### 2.ListIterator

##### 3.迭代的陷阱

##### 4.迭代器实现的原理

##### 5.迭代器的好处

#### ArrayList实现的接口

##### 1.Collection

Collection表示一个数据集合，数据间没有位置或顺序的概念。

##### 2.List

List表示有顺序或位置的数据集合，它扩展了Collection。

##### 3.RandomAccess

```java
public interface RandomAccess {
}
```

这种没有任何代码的接口在Java中被称为**标记接口**，用于声明类的一种属性。

#### ArrayList的其他方法



#### ArrayList特点分析



### 9.2 剖析LinkedList

LinkedList实现了List和Deque、Queue接口，可以按照队列、栈和双端队列的方式进行操作。

它的特点基本与ArrayList相反。

#### 用法

Queue扩展了Collection，它的主要操作有三个：

- 在尾部添加元素（add、offer）；
- 查看头部元素（element、peek），返回头部元素，但不改变队列；
- 删除头部元素（remove、poll），返回头部元素，并且从队列中删除。



<u>栈和队列只是双端队列的特殊情况，它们的方法都可以使用双端队列的方法替代，不过，使用不同的名称和方法，概念上更为清晰。</u>

#### 实现原理



#### LinkedList特点分析

### 9.3 剖析ArrayDeque

ArrayDeque是基于数组实现的双端队列。

#### 实现原理

#### ArrayDeque特点分析



## 10 Map和Set

ArrayList、LinkedList和ArrayDeque查找元素的效率都比较低，都需要逐个进行比较。

Map和Set接口的实现类查找效率就高得多。

### 10.1 剖析HashMap

Map表示映射关系，HashMap表示利用哈希（Hash）实现Map接口。

#### Map接口

一个键映射到一个值，Map按照键存储和访问值，**键不能重复**，即一个键只会存储一份，给同一个键重复设值会覆盖原来的值。

Map可以方便处理需要根据**键**访问对象的场景，比如：

- 一个词典应用，键可以为单词，值可以为单词信息类，包括含义、发音、例句等；
- 统计和记录一本书中所有单词出现的次数，可以以单词为键，以出现次数为值；
- 管理配置文件中的配置项，配置项是典型的键值对；
- 根据身份证号查询人员信息，身份证号为键，人员信息为值。

数组、ArrayList、LinkedList可以视为一种特殊的Map，键为索引，值为对象。

Java 7中Map接口的定义：

```java
public interface Map<K, V> { //K和V是类型参数，分别表示键(Key)和值(Value)的类型
  V put(K key, V value); //保存键值对，如果原来有key，覆盖，返回原来的值
  V get(Object key); //根据键获取值， 没找到，返回null
  V remove(Object key); //根据键删除键值对， 返回key原来的值，如果不存在，返回null
  int size(); //查看Map中键值对的个数
  boolean isEmpty(); //是否为空
  boolean containsKey(Object key); //查看是否包含某个键
  boolean containsValue(Object value); //查看是否包含某个值
  void putAll(Map<? extends K, ? extends V> m); //保存m中的所有键值对到当前Map
  void clear(); //清空Map中所有键值对
  Set<K> keySet(); //获取Map中键的集合
  Collection<V> values(); //获取Map中所有值的集合
  Set<Map.Entry<K, V>> entrySet(); //获取Map中的所有键值对
  interface Entry<K, V> { //嵌套接口，表示一条键值对
    K getKey(); //键值对的键
    V getValue(); //键值对的值
    V setValue(V value);
    boolean equals(Object o);
    int hashCode();
  }
  boolean equals(Object o);
  int hashCode();
}
boolean containsValue(Object value);
Set<K> keySet();
```

Java 8增加了一些默认方法，如<u>getOrDefault、forEach、replaceAll、putIfAbsent、replace、computeIfAbsent、merge</u>等，Java 9增加了多个重载的of方法，可以方便地根据一个或多个键值对构建不变的Map。

Set是一个接口，表示的是数学中的集合概念，即**没有重复的元素集合**。

#### HashMap

随机产生1000个0～3的数，统计每个数的次数：

```java
Random rnd = new Random();
Map<Integer, Integer> countMap = new HashMap<>();
for (int i = 0; i < 1000; i++) {
  int num = rnd.nextInt(4);
  Integer count = countMap.get(num);
  if (count == null) {
    countMap.put(num, 1);
  } else {
    countMap.put(num, count + 1);
  }
}

for (Map.Entry<Integer, Integer> kv : countMap.entrySet()) {
  System.out.println(kv.getKey() + ", " + kv.getValue());
}
```

构造方法：

```java
public HashMap(int initialCapacity)
public HashMap(int initialCapacity, float loadFactor)
public HashMap(Map<? extends K, ? extends V> m)  // 复制一个已有Map
```



#### 实现原理

🔖java8更新

##### 1.内部构成

threshold

loadFactor 负载因子

##### 2.默认构造方法

##### 3.保存键值对

基本步骤为：

1. 计算键的哈希值；
2. 根据哈希值得到保存位置（取模）；
3. 插到对应位置的链表头部或更新已有值；
4. 根据需要扩展table大小。

##### 4.查找方法



##### 5.根据键删除键值对



HashMap基本原理总结：

- 内部有一个哈希表，即数组table，每个元素table[i]指向一个单向链表，根据键存取值，用键算出hash值，取模得到数组中的索引位置buketIndex，然后操作table[buketIndex]指向的单向链表。
- 存取的时候依据键的hash值，只在对应的链表中操作，不会访问别的链表，在对应链表操作时也是先比较hash值，如果相同再用equals方法比较。

> 根据哈希值存取对象、比较对象是计算机程序中一种重要的思维方式，它使得存取对象主要依赖于自身Hash值，而不是与其他对象进行比较，存取效率也与集合大小无关，高达O(1)，即使进行比较，也利用Hash值提高比较性能。

### 10.2 剖析HashSet

HashSet表示利用哈希（Hash）实现Set接口。

Set表示的是没有重复元素、且不保证顺序的容器接口，它扩展了Collection，但没有定义任何新的方法，不过，对于其中的一些方法，它有自己的规范。

```java
public interface Set<E> extends Collection<E> {
  int size();
  boolean isEmpty();
  boolean contains(Object o);
  //迭代遍历时，不要求元素之间有特别的顺序
  //HashSet的实现就是没有顺序，但有的Set实现可能会有特定的顺序，比如TreeSet
  Iterator<E> iterator();
  Object[] toArray();
  <T> T[] toArray(T[] a);
  //添加元素， 如果集合中已经存在相同元素了，则不会改变集合，直接返回false，
  //只有不存在时，才会添加，并返回true
  boolean add(E e);
  boolean remove(Object o);
  boolean containsAll(Collection<? > c);
  //重复的元素不添加，不重复的添加，如果集合有变化，返回true，没变化返回false
  boolean addAll(Collection<? extends E> c);
  boolean retainAll(Collection<? > c);
  boolean removeAll(Collection<? > c);
  void clear();
  boolean equals(Object o);
  int hashCode();
}
```



#### 实现原理

HashSet内部是用HashMap实现的，它内部有一个HashMap实例变量。

```java
private transient HashMap<E,Object> map;
```



HashSet的特点：

1. 没有重复元素；
2. 可以高效地添加、删除元素、判断元素是否存在，效率都为O(1)；
3. 没有顺序。如果要保持添加的顺序，可以使用其子类LinkedHashSet。
4. 可以方便高效地实现去重、集合运算等功能。

### 10.3 排序二叉树

HashMap和HashSet的共同实现机制是哈希表，它们共同的限制是没有顺序；它们对应能保持顺序的是TreeMap和TreeSet，这两个类实现基础就是**排序二叉树**。

#### 基本概念

排序二叉树的每个节点：

- 如果左子树不为空，则左子树上的所有节点都小于该节点；
- 如果右子树不为空，则右子树上的所有节点都大于该节点。

#### 基本算法

##### 1.查找

##### 2.遍历

##### 3.插入

##### 4.删除

#### 平衡的排序二叉树

**高度平衡**，即任何节点的左右子树的高度差最多为一。

**AVL树**（名字来源发明者的名字）：满足高度平衡的排序二叉树。

**大致平衡**，指它确保任意一条从根到叶子节点的路径，没有任何一条路径的长度会比其他路径长过两倍。

**红黑树**：满足大致平衡的排序二叉树。

红黑树减弱了对平衡的要求，降低了保持平衡需要的开销，在实际应用中，统计性能高于AVL树。TreeMap就是通过红黑树实现的。



**与哈希表一样，树也是计算机程序中一种重要的数据结构和思维方式。为了能够快速操作数据，哈希和树是两种基本的思维方式，不需要顺序，优先考虑哈希，需要顺序，考虑树。除了容器类TreeMap/TreeSet，数据库中的索引结构也是基于树的（不过基于B树，而不是二叉树），而索引是能够在大量数据中快速访问数据的关键。**



### 10.4 剖析TreeMap

在TreeMap中，键值对之间**按键**有序。

#### 基本用法



#### 实现原理



### 10.5 剖析TreeSet

TreeSet是基于TreeMap。

#### 基本用法

排重和有序

#### 实现原理



TreeSet的特点：

1. 没有重复元素。
2. 添加、删除元素、判断元素是否存在，效率比较高，为O(log2(N)), N为元素个数。
3. 有序，TreeSet同样实现了SortedSet和NavigatableSet接口，可以方便地根据顺序进行查找和操作，如第一个、最后一个、某一取值范围、某一值的邻近元素等。
4. 为了有序，TreeSet要求元素实现Comparable接口或通过构造方法提供一个Comparator对象。

### 10.6 剖析LinkedHashMap

LinkedHashMap是HashMap的子类，但可以保持元素按插入或访问有序，这与TreeMap按键排序不同。

#### 基本用法



#### 实现原理



#### LinkedHashSet

LinkedHashSet是HashSet的子类，它内部的Map的实现类是LinkedHashMap，所以它也可以保持插入顺序。



### 10.7 剖析EnumMap

键的类型为枚举类型



### 10.8 剖析EnumSet

> **位向量**是计算机程序中解决问题的一种常用方式，我们有必要理解和掌握。

#### 基本用法

#### 应用场景

#### 实现原理



**对于只有两种状态，且需要进行集合运算的数据，使用位向量进行表示、位运算进行处理，是计算机程序中一种常用的思维方式。**

## 11 堆与优先级队列

前面介绍的每个容器类背后都有一种数据结构，ArrayList是动态数组，LinkedList是链表，HashMap/HashSet是哈希表，TreeMap/TreeSet是红黑树。

之前提到的堆指的是内存中的区域，保存动态分配的对象，与栈相对应。这里的堆是一种数据结构，与内存区域和分配无关。

堆可以非常高效方便地解决很多问题：

1. 优先级队列
2. 求前K个最大的元素
3. 求中值元素

### 11.1 堆的概念与算法

#### 基本概念

**满二叉树**是指除了最后一层外，每个节点都有两个孩子，而最后一层都是叶子节点，都没有孩子。

![image-20220314090214585](images/image-20220314090214585.png)

**完全二叉树**

堆是完全二叉树

#### 堆的算法

##### 1.添加元素

##### 2.从头部删除元素

##### 3.从中间删除元素

##### 4.构建初始堆

##### 5.查找和遍历



**堆是一种比较神奇的数据结构，概念上是树，存储为数组，父子有特殊顺序，根是最大值/最小值，构建/添加/删除效率都很高，可以高效解决很多问题。**

### 11.2 剖析PriorityQueue

PriorityQueue堆在Java中的具体实现类。PriorityQueue是优先级队列，它首先实现了队列接口（Queue）。

#### 基本用法

#### 实现原理



PriorityQueue特点：

1. 实现了优先级队列，最先出队的总是优先级最高的，即排序中的第一个。
2. 优先级可以有相同的，内部元素不是完全有序的，如果遍历输出，除了第一个，其他没有特定顺序。
3. 查看头部元素的效率很高，为O(1)，入队、出队效率比较高，为O(log2(N))，构建堆heapify的效率为O(N)。
4. 根据值查找和删除元素的效率比较低，为O(N)。

### 11.3 堆和PriorityQueue的应用

#### 求前K个最大的元素

#### 求中值



**PriorityQueue和ArrayDeque都是队列，都是基于数组的，但都不是简单的数组，通过一些特殊的约束、辅助成员和算法，它们都能高效地解决一些特定的问题，这大概是计算机程序中使用数据结构和算法的一种艺术吧。**



## 12 通用容器类和总结

为什么需要实现容器接口呢？

1. 容器类是一个大家庭，它们之间可以方便地协作，比如很多方法的参数和返回值都是容器接口对象，实现了容器接口，就可以方便地参与这种协作。
2. Java有一个类Collections，提供了很多针对容器接口的通用算法和功能，实现了容器接口，可以直接利用Collections中的算法和功能。

### 12.1 抽象容器类

![image-20220314090629179](images/image-20220314090629179.png)

虚线框表示接口，有Collection、List、Set、Queue、Deque和Map。

有6个抽象容器类

1. AbstractCollection
2. AbstractList
3. AbstractSequentialList
4. AbstractMap
5. AbstractSet
6. AbstractQueue

### 12.2 Collections

类Collections以静态方法的方式提供了很多通用算法和功能，这些功能大概可以分为两类。

1. 对容器接口对象进行操作。
   - 查找和替换。
   - 排序和调整顺序。
   - 添加和修改。
2. 返回一个容器接口对象。
   - 适配器：将其他类型的数据转换为容器接口对象。
   - 装饰器：修饰一个给定容器接口对象，增加某种性质。

#### 查找和替换

##### 1.二分查找

##### 2.查找最大值/最小值

##### 3.其他方法



#### 排序和调整顺序

##### 1.排序、交换位置与翻转

##### 2.随机化重排

##### 3.循环移位



#### 添加和修改



#### 适配器

适配器，就是将一种类型的接口转换成另一种接口。

##### 1.空容器方法

##### 2.单一对象方法



#### 装饰器

##### 1.写安全

##### 2.类型安全

##### 3.线程安全







### 12.3 容器类总结

#### 用法和特点



#### 数据结构和算法



#### 设计思维和模式



# 四、文件

## 13 文件基本技术

### 13.1 文件概述

#### 基本概念和常识

##### 1 二进制思维

所有文件（不可执行文件、图片文件、视频文件、Word文件、压缩文件、txt文件等等）都是以0和1的二进制形式保存的。我们所看到的图片、视频、文本，都是**应用程序对这些二进制的解析结果**。

##### 2 文件类型

每种文件类型都有一定的格式，代表着**文件含义和二进制之间的映射关系**。

文件类型粗略分为两类：**文本文件和二进制文件**。前者如文本文件（.txt）、程序源代码文件（.java等）、HTML文件（.html）等，后者如压缩文件（.zip）、PDF文件、MP3文件、Excel文件等。

文本文件里的每个二进制字节都是某个可打印字符的一部分，都可以用最基本的文本编辑器进行查看和编辑；二进制文件中，每个字节就不一定表示字符，可能表示颜色、字体、声音大小等，如果用基本的文本编辑器打开，一般都是满屏的乱码，需要专门的应用程序进行查看和编辑。

##### 3 文本文件的编码

三个特殊字节（0xEF 0xBB 0xBF）

BOM头，Byte Order Mark（即字节序标记）

##### 4 文件系统

不同的文件系统有不同的文件组织方式、结构和特点，但编程时不需要关心其细节，编程语言和类库为我们提供统一的API。

File.separator，路径分隔符

System.getProperty("user.dir")，运行Java程序的当前目录

文件都具有**元数据信息**，如文件名、创建时间、修改时间、文件大小等。

是否隐藏

访问权限

文件名大小写是否敏感

临时文件，Windows 7 是 `C:\Users\用户名\AppData\Local\Temp`，Linux是`/tmp`



##### 5 文件读写

Java封装了操作系统的功能，提供了统一的API。

基本常识：**硬盘的访问延时，相比内存，是很慢的。**

操作系统和硬盘一般是**按块批量传输**，而不是按字节，以摊销延时开销，块大小一般至少为512字节，即使应用程序只需要文件的一个字节，操作系统也会至少将一个块读进来。一般而言，应<u>尽量减少接触硬盘</u>，接触一次，就一次多做一些事情。对于网络请求和其他输入输出设备，原则都是类似的。

基本常识：一般读写文件需要**两次数据复制**，比如读文件，需要先从硬盘复制到操作系统内核，再从内核复制到应用程序分配的内存中。

操作系统所在的环境是**内核态**，应用程序是**用户态**，应用程序调用操作系统的功能，需要两次环境的切换，先从用户态切到内核态，再从内核态切到用户态。**这种用户态/内核态的切换是有开销的，应尽量减少这种切换。**

**缓冲区**

**打开**，**关闭**。打开文件操作系统会建立一个有关该文件的内存结构，这个结构通过一个整数索引来引用，这个索引叫做**文件描述符**。**关闭文件**一般会同步缓冲区内容到硬盘，并释放占据的内存。

**内存映射文件**



#### Java文件概述

##### 1 流 

在Java中（很多其他语言也类似），文件一般不是单独处理的，而是视为输入输出（Input/Output, IO）设备的一种。Java使用基本统一的概念（流）处理所有的IO，包括文件、键盘、显示终端、网络等。

Java IO的基本类大多位于java.io。类InputStream表示输入流，OutputStream表示输出流，而FileInputStream表示文件输入流，FileOutputStream表示文件输出流。

🔖



##### 2 装饰器设计模式

基本的流按字节读写，没有缓冲区，这不方便使用。Java解决这个问题的方法是使用装饰器设计模式。

很多装饰类，其中两个基类：过滤器输入流**FilterInputStream**和过滤器输出流**FilterOutputStream**。子类如：

- 对流起缓冲装饰的子类是BufferedInputStream和BufferedOutputStream。
- 可以按8种基本类型和字符串对流进行读写的子类是DataInputStream和DataOutputStream。
- 可以对流进行压缩和解压缩的子类有GZIPInputStream、ZipInputStream、GZIPOutput-Stream和ZipOutputStream。
- 可以将基本类型、对象输出为其字符串表示的子类有PrintStream。

##### 3 Reader/Writer

以InputStream/OutputStream为基类的流基本都是<u>以二进制形式处理数据的</u>，不能够方便地处理文本文件，没有编码的概念，能够方便地<u>按字符处理文本数据</u>的基类是Reader和Writer。子类：

- 读写文件的子类是FileReader和FileWriter。
- 起缓冲装饰的子类是BufferedReader和BufferedWriter。
- 将字符数组包装为Reader/Writer的子类是CharArrayReader和CharArrayWriter。
- 将字符串包装为Reader/Writer的子类是StringReader和StringWriter。
- 将InputStream/OutputStream转换为Reader/Writer的子类是InputStreamReader和OutputStreamWriter。
- 将基本类型、对象输出为其字符串表示的子类是PrintWriter。

##### 4 随机读写文件

RandomAccessFile

##### 5 File

文件路径、文件元数据、文件目录、临时文件、访问权限管理等

##### 6 NIO

java.nio，表示New IO。

NIO代表一种不同的看待IO的方式，它有**缓冲区**和**通道**的概念。更接近操作系统的概念，某些操作的性能也更高，比如，复制文件到网络。

通道可以利用操作系统和硬件提供的**DMA机制**（Direct Memory Access，直接内存存取），不用CPU和应用程序参与，直接将数据从硬盘复制到网卡。

NIO还支持一些比较底层的功能，如内存映射文件、文件加锁、自定义文件系统、非阻塞式IO、异步IO等。

##### 7 序列化和反序列化

**序列化就是将内存中的Java对象持久保存到一个流中，反序列化就是从流中恢复Java对象到内存。**

它们的作用：一是对象状态持久化；二是网络远程调用，用于传递和返回对象。

Java主要通过接口Serializable和类ObjectInputStream/ObjectOutputStream提供对序列化的支持。

Java的默认序列化的缺点：序列化后的形式比较大、浪费空间，序列化/反序列化的性能也比较低，Java特有不能与其他语言交互。

Java对象也可以序列化ⅩML和JSON，它们都是文本格式，人容易阅读，但占用的空间相对大一些。

在只用于网络远程调用的情况下，有很多流行的、跨语言的、精简且高效的对象序列化机制，如ProtoBuf、Thrift、MessagePack等。其中，<u>MessagePack是二进制形式的JSON，更小更快</u>。

### 13.2 二进制文件和字节流

#### InputStream/OutputStream

抽象基类

##### InputStream的基本方法

```java
public abstract int read() throws IOException;
```

read方法从流中读取下一个字节，返回类型为int，但取值为0～255，当读到流结尾的时候，返回值为-1，如果流中没有数据，read方法会阻塞直到数据到来、流关闭或异常出现。异常出现时，read方法抛出异常，类型为IOException，这是一个受检异常，调用者必须进行处理。

FileInputStream中实现会调用本地方法。

```java
public int read(byte b[]) throws IOException
public int read(byte b[], int off, int len) throws IOException
public void close() throws IOException
```

##### InputStream的高级方法

```java
public long skip(long n) throws IOException
public int available() throws IOException
public synchronized void mark(int readlimit)
public boolean markSupported()
public synchronized void reset() throws IOException
```



##### OutputStream

```java
public abstract void write(int b) throws IOException;
```

```java
public void write(byte b[]) throws IOException
public void write(byte b[], int off, int len) throws IOException
```

```java
public void flush() throws IOException
public void close() throws IOException
```



#### FileInputStream/FileOutputStream

输入源和输出目标是文件的流。

FileOutputStream的构造方法：

```java
public FileOutputStream(File file, boolean append) throws FileNotFoundException
public FileOutputStream(String name) throws FileNotFoundException
```

```java
@Test
public void test13_2() throws IOException {
  FileOutputStream output = new FileOutputStream("hello.txt");
  try {
    String data = "hello, world! I'm coming.";
    byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
    output.write(bytes);
  } finally {
    output.close();
  }
}
```

OutputStream只能以byte或byte数组写文件。

FileOutputStream两个额外的方法：

```java
public FileChannel getChannel()
public final FileDescriptor getFD()
```

FileInputStream的主要构造方法:

```java
public FileInputStream(String name) throws FileNotFoundException
public FileInputStream(File file) throws FileNotFoundException
```

参数是文件路径或File对象，必须是一个已经存在的文件，不能是目录。如果不存在，抛出FileNotFoundException，如果用户没有读的权限，抛出SecurityException。

```java
@Test
public void test13_2_() throws IOException {
    FileInputStream input = new FileInputStream("hello.txt");
    try {
        byte[] buf = new byte[1024];
        int bytesRead = input.read(buf);
        String data = new String(buf, 0, bytesRead, StandardCharsets.UTF_8);
        System.out.println(data);
    } finally {
        input.close();
    }
}
```



#### ByteArrayInputStream/ByteArrayOutputStream

输入源和输出目标是字节数组的流。



#### DataInputStream/DataOutputStream

装饰类，按基本类型和字符串而非只是字节读写流。



#### BufferedInputStream/BufferedOutputStream

装饰类，对输入输出流提供缓冲功能。

[Apache Commons IO](http://commons.apache.org/proper/commons-io/) 提供了很多简单易用的方法。

### 13.3 文本文件和字符流

#### 基本概论

##### 1.文本文件

🔖

##### 2.编码

在文本文件中，同一个字符不同编码方式对应的二进制形式可能是不一样的。

##### 3.字符流

字节流是按字节读取的，而**字符流则是按char读取的**，一个char在文件中保存的是几个字节与编码有关。

注意：**一个char不完全等同于一个字符**，对于绝大部分字符，一个字符就是一个char，但我们之前介绍过，对于增补字符集中的字符，需要两个char表示，对于这种字符，Java中的字符流是按char而不是一个完整字符处理的。

#### Reader/Writer

Reader/Writer类似字节流的InputStream/OutputStream，都是抽象类。

```java
public int read() throws IOException
public int read(char cbuf[]) throws IOException
abstract public void close() throws IOException
public long skip(long n) throws IOException
public boolean ready() throws IOException 
```

```java
public void write(int c)
public void write(char cbuf[])
public void write(String str) throws IOException
abstract public void close() throws IOException;
abstract public void flush() throws IOException;
```



#### InputStreamReader/OutputStreamWriter

适配器类，将字节流转换为字符流（将InputStream/OutputStream转换为Reader/Writer）；

#### FileReader/FileWriter

输入源和输出目标是文件的字符流；

#### CharArrayReader/CharArrayWriter

输入源和输出目标是char数组的字符流；

#### StringReader/StringWriter

输入源和输出目标是String的字符流；

#### BufferedReader/BufferedWriter

装饰类，对输入/输出流提供缓冲，以及按行读写功能；

#### PrintWriter

装饰类，可将基本类型和对象转换为其字符串形式输出的类。

#### Scanner

#### 标准流

#### 实用方法

> 小结：写文件时，可以优先考虑PrintWriter，因为它使用方便，支持自动缓冲、指定编码类型、类型转换等。读文件时，如果需要指定编码类型，需要使用InputStreamReader；如果不需要指定编码类型，可使用FileReader，但都应该考虑在外面包上缓冲类Buffered-Reader。

### 13.4 文件和目录操作

文件和目录操作最终是与操作系统和文件系统相关的，不同系统的实现是不一样的，但Java中的**java.io.File**类提供了统一的接口，底层会通过本地方法调用操作系统和文件系统的具体实现。

#### 构造方法

```java
//pathname表示完整路径，该路径可以是相对路径，也可以是绝对路径
public File(String pathname)
//parent表示父目录，child表示孩子
public File(String parent, String child)
public File(File parent, String child)
```

#### 文件元数据

```java
public String getName() //返回文件或目录名称，不含路径名
public boolean isAbsolute() //判断File中的路径是否是绝对路径
public String getPath() //返回构造File对象时的完整路径名，包括路径和文件名称
public String getAbsolutePath() //返回完整的绝对路径名
//返回标准的完整路径名，它会去掉路径中的冗余名称如".", ".."，跟踪软链接(Unix系统概念)等
public String getCanonicalPath() throws IOException
public String getParent() //返回父目录路径
public File getParentFile() //返回父目录的File对象
//返回一个新的File对象，新的File对象使用getAbsolutePath()的返回值作为参数构造
public File getAbsoluteFile()
//返回一个新的File对象，新的File对象使用getCanonicalPath()的返回值作为参数构造
public File getCanonicalFile() throws IOException
```

File有4个静态变量：

```java
public static final String separator
public static final char separatorChar
public static final String pathSeparator
public static final char pathSeparatorChar
```

前两个表示文件路径分隔符，Windows和Linux系统分别为：`\`， `/`；

后两个表示多个文件路径中的分隔符，比如，环境变量PATH中的分隔符，Java类路径变量classpath中的分隔符，Windows和Linux系统分别为：`;`， `:`。

获取文件或目录的基本信息：

```java
public boolean exists() //文件或目录是否存在
public boolean isDirectory() //是否为目录
public boolean isFile() //是否为文件
public long length() //文件长度，字节数，对目录没有意义
public long lastModified() //最后修改时间，从纪元时开始的毫秒数
public boolean setLastModified(long time) //设置最后修改时间，返回是否修改成功
```

> <font color=#FF8C00>注：</font>File对象没有返回创建时间的方法，因为创建时间不是一个公共概念， Linux/Unix就没有创建时间的概念。

File类中与安全和权限相关的主要方法有：

```java
public boolean isHidden() //是否为隐藏文件
public boolean canExecute() //是否可执行
public boolean canRead() //是否可读
public boolean canWrite() //是否可写
public boolean setReadOnly() //设置文件为只读文件
//修改文件读权限
public boolean setReadable(boolean readable, boolean ownerOnly)
public boolean setReadable(boolean readable)
//修改文件写权限
public boolean setWritable(boolean writable, boolean ownerOnly)
public boolean setWritable(boolean writable)
//修改文件可执行权限
public boolean setExecutable(boolean executable, boolean ownerOnly)
public boolean setExecutable(boolean executable)
```

#### 文件操作

新建一个File对象不会实际创建文件，但如下方法可以：

```java
public boolean createNewFile() throws IOException
```

```java
public static File createTempFile(String prefix, String suffix) throws IOException
public static File createTempFile(String prefix, String suffix, File directory) throws IOException
```

删除方法为：

```java
public boolean delete()
public boolean deleteOnExit()
```

重命名：

```java
public boolean renameTo(File dest)
```

#### 目录操作

创建：

```java
public boolean mkdir()
public bollean mkdirs()
```

访问一个目录下的子目录和文件：

```java
public String[] list()
public String[] list(FilenameFilter filter)
public File[] listFiles()
public File[] listFiles(FileFilter filter)
public File[] listFiles(FilenameFilter filter)
```



## 14 文件高级技术🔖

上一章中的字符流和字节流，都是以流的方式读写文件，它们有局限性：

1. 要么读、要么写，不能同时读和写。
2. 不能随机读写，只能从头读到尾，且不能重复读，虽然通过缓冲可以实现部分重读，但是有限制。

### 14.1 常见文件类型处理

#### 属性文件

属性文件是常见的配置文件，用于在不改变代码的情况下改变程序的行为。

`java.util.Properties`

#### CSV文件

CSV是Comma-Separated Values的缩写，表示逗号分隔值

Apache Commons CSV

#### Excel

[POI类库](http://poi.apache.org/)

#### HTML

HTML分析器:[jsoup](https://jsoup.org/)

#### 压缩文件

ava SDK支持两种压缩文件格式：gzip和zip，如果需要更多格式，可以使用[Apache Commons Compress](http://commons.apache.org/proper/commons-compress/)

### 14.2 随机读写文件

RandomAccessFile

#### 用法



#### 设计一个键值数据库BasicDB



### 14.3 内存映射文件

内存映射文件不是Java引入的概念，而是操作系统提供的一种功能，大部分操作系统都支持。

#### 基本概念

#### 用法

#### 设计一个消息队列BasicQueue

#### 实现消息队列



内存映射文件在日常普通的文件读写中，用到得比较少，但在**一些系统程序中，它却是经常被用到的一把利器**，可以高效地读写大文件，且能实现不同程序间的共享和通信。

### 14.4 标准序列化机制

简单来说，序列化就是将对象转化为字节流，反序列化就是将字节流转化为对象。

#### 基本用法



#### 复杂对象



#### 定制序列化



#### 序列化的基本原理



#### 版本问题



#### 序列化特点分析







### 14.5 使用Jackson序列化为JSON/XML/MessagePack



#### Jackson对XML支持的局限性



Jackson还支持很多其他格式，如YAML、AVRO、Protobuf、Smile等。

# 五、并发

## 15 并发基础知识

### 15.1 线程的基本概念

#### 创建线程

线程表示一条单独的执行流，它有自己的程序执行计数器，有自己的栈。

##### 1.继承Thread



##### 2.实现Runnable接口

#### 线程的基本属性和方法

##### 1.id和name



##### 2.优先级



##### 3.状态



##### 4.是否daemon线程



##### 5.sleep方法



##### 6.yield方法



##### 7.join方法



##### 8.过时方法



#### 共享内存及可能存在的问题

##### 1.竟态条件（race condition）



##### 2.内存可见性



#### 线程的优点及成本

优点：

1. 充分利用多CPU的计算能力，单线程只能利用一个CPU，使用多线程可以利用多CPU的计算能力。
2. 充分利用硬件资源，CPU和硬盘、网络是可以同时工作的，一个线程在等待网络IO的同时，另一个线程完全可以利用CPU，对于多个独立的网络请求，完全可以使用多个线程同时请求。
3. 在用户界面（GUI）应用程序中，保持程序的响应性，界面和后台任务通常是不同的线程，否则，如果所有事情都是一个线程来执行，当执行一个很慢的任务时，整个界面将停止响应，也无法取消该任务。
4. 简化建模及IO处理，比如，在服务器应用程序中，对每个用户请求使用一个单独的线程进行处理，相比使用一个线程，处理来自各种用户的各种请求，以及各种网络和文件IO事件，建模和编写程序要容易得多。

成本：

1. 操作系统会为每个线程创建必要的数据结构、栈、程序计数器等，创建也需要一定的时间。
2. 线程调度和切换也是有成本的。**上下文切换**

如果执行的任务都是CPU密集型的，即主要消耗的都是CPU，那创建超过CPU数量的线程就是没有必要的，并不会加快程序的执行。



### 15.2 理解synchronized

#### 用法和基本原理

##### 1.实例方法

##### 2.静态方法

##### 3.代码块



#### 进一步了解synchronized

##### 1.可重入性

##### 2.内存可见性

##### 3.死锁



#### 同步容器及其注意事项

##### 1.复合操作

##### 2.伪同步

##### 3.迭代

##### 4.并发容器



### 15.3 线程的基本协作机制

#### 协作场景



#### wait/notify



#### 生产者/消费者模式



#### 同时开始



#### 等待结束



#### 异步结果



#### 集合点



### 15.4 线程的中断

#### 取消/关闭的场景



#### 取消/关闭的机制



#### 线程对中断的反应

##### 1.RUNNABLE

线程在运行或具备运行条件只是在等待操作系统调度。

##### 2.WAITING/TIMED_WAITING

线程在等待某个条件或超时。

##### 3.BLOCKED

线程在等待锁，试图进入同步块。

##### 4.NEW/TERMINATED

线程还未启动或已结束。



#### 如何正确地取消/关闭线程





## 16 并发包的基石

java.util.concurrent

### 16.1 原子变量和CAS

Java并发包中的基本原子变量类型有很多种。

- AtomicBoolean：原子Boolean类型，常用来在程序中表示一个标志位。
- AtomicInteger：原子Integer类型。
- AtomicLong：原子Long类型，常用来在程序中生成唯一序列号。
- AtomicReference：原子引用类型，用来以原子方式更新复杂类型。

#### AtomicInteger

##### 1.基本用法

##### 2.基本原理和思维

##### 3.实现锁



#### ABA问题

假设当前值为A，如果另一个线程先将A修改成B，再修改回成A，当前线程的CAS操作无法分辨当前值发生过变化。



### 16.2 显式锁

`java.util.concurrent.locks`

#### 接口Lock



#### 可重入锁ReentrantLock



#### ReentrantLock的实现原理



#### 对比ReentrantLock和synchronized

synchronized代表一种**声明式编程思维**，程序员更多的是表达一种同步声明，由Java系统负责具体实现，程序员不知道其实现细节；显式锁代表一种**命令式编程思维**，程序员实现所有细节。

### 16.3 显式条件

#### 用法



#### 生产者/消费者模式



#### 实现原理





## 17 并发容器

Copy-On-Write即写时复制，或称写时拷贝，是解决并发问题的一种重要思路。

### 17.1 写时复制的List和Set

#### CopyOnWriteArrayList



#### CopyOnWriteArraySet



### 17.2 ConcurrentHashMap

#### 并发安全



#### 原子复合操作



#### 高并发的基本机制



#### 迭代安全



#### 弱一致性



### 17.3 基于跳表的Map和Set

#### 基本概念



#### 基本实现原理



### 17.4 并发队列



#### 无锁非阻塞并发队列

ConcurrentLinkedQueue、ConcurrentLinkedDeque



#### 普通阻塞队列

基于数组的ArrayBlockingQueue，基于链表的LinkedBlockingQueue和LinkedBlockingDeque。

#### 优先级阻塞队列

PriorityBlockingQueue

#### 延时阻塞队列

DelayQueue

#### 其它阻塞队列

SynchronousQueue和LinkedTransferQueue。



## 18 异步任务执行服务

**执行服务**，将“**任务的提交**”和“**任务的执行**”相分离。

“执行服务”封装了任务执行的细节，对于任务提交者而言，它可以关注于任务本身，如提交任务、获取结果、取消任务，而不需要关注任务执行的细节，如线程创建、任务调度、线程关闭等。

### 18.1 基本概念和原理

#### 基本接口

任务执行服务涉及的基本接口：

- Runnable和Callable：表示要执行的异步任务。
- Executor和ExecutorService：表示执行服务。
- Future：表示异步任务的结果。

#### 基本用法



#### 基本实现原理



### 18.2 线程池

ThreadPoolExecutor

#### 理解线程池

##### 1.线程池大小

- corePoolSize：核心线程个数。
- maximumPoolSize：最大线程个数。
- keepAliveTime和unit：空闲线程存活时间。

##### 2.队列

##### 3.任务拒绝策略

##### 4.线程工厂

##### 5.关于核心线程的特殊配置



#### 工厂类Executors



#### 线程池的死锁



### 18.3 定时任务的陷阱

定时任务的应用场景：

- 闹钟程序或任务提醒，指定时间叫床或在指定日期提醒还信用卡。
- 监控系统，每隔一段时间采集下系统数据，对异常事件报警。
- 统计系统，一般凌晨一定时间统计昨日的各种数据指标。

#### Timer和TimerTask



#### ScheduledExecutorService



## 19 同步和协作工具类

### 19.1 读写锁ReentrantReadWriteLock



### 19.2 信号量Semaphore



### 19.3 倒计时门栓CountDownLatch



### 19.4 循环栅栏CyclicBarrier



### 19.5 理解ThreadLocal



#### 使用场景

##### 1.日期处理

##### 2.随机数

##### 3.上下文信息



## 20 并发总结

多线程开发有两个核心问题：一个是**竞争**，另一个是**协作**。

### 20.1 线程安全的机制

使用synchronized；使用显式锁；❑ 使用volatile；❑ 使用原子变量和CAS；❑ 写时复制；❑ 使用ThreadLocal。

### 20.2 线程的协作机制

❑ wait/notify；❑ 显式条件；❑ 线程的中断；❑ 协作工具类；❑ 阻塞队列；❑ Future/FutureTask。

### 20.3 容器类

#### 同步容器

#### 并发容器

### 20.4 任务执行服务



# 六、动态与函数式编程

## 21 反射

Java的动态特性：反射、注解、动态代理、类加载器等。

利用这些特性，可以优雅地实现一些灵活通用的功能，它们经常用于各种框架、库和系统程序中，比如：

1. Jackson利用反射和注解实现了通用的序列化机制。
2. 有多种库（如Spring MVC、Jersey）用于处理Web请求，利用反射和注解，能方便地将用户的请求参数和内容转换为Java对象，将Java对象转变为响应内容。
3. 有多种库（如Spring、Guice）利用这些特性实现了对象管理容器，方便程序员管理对象的生命周期以及其中复杂的依赖关系。
4. 应用服务器（如Tomcat）利用类加载器实现不同应用之间的隔离，JSP技术利用类加载器实现修改代码不用重启就能生效的特性。
5. 面向方面的编程AOP（Aspect Oriented Programming）将编程中通用的关注点（如日志记录、安全检查等）与业务的主体逻辑相分离，减少冗余代码，提高程序的可维护性， AOP需要依赖上面的这些特性来实现。



### 21.1 Class类

每个已加载的类在内存都有一份类信息，每个对象都有指向它所属类信息的引用。

1. 所有类的根父类Object有一个方法getClass，用于获取对象的Class对象：

```
public final native Class<? > getClass()
```

Class是一个泛型类，有一个类型参数，getClass()并不知道具体的类型，所以返回Class<?>。

2. 获取Class对象不一定需要实例对象，如果在写程序时就知道类名，可以使用`<类名>.class`获取Class对象。

3. Class有一个静态方法forName（可能抛出ClassNotFoundException），可以根据类名直接加载Class，获取Class对象。

```java
Class<Date> cls = Date.class;

Class<?> aClass = Class.forName("java.util.Date");
```



接口也有Class对象：

```java
Class<Comparable> cls = Comparable.class;
```

基本类型也有对应的Class对象，类型参数为对应的包装类型：

```java
Class<Integer> intCls = int.class; 
```

对于数组，每种类型都有对应数组类型的Class对象，每个维度都有一个，即一维数组有一个，二维数组有一个不同的类型：

```java
String[] strArr = new String[10];
int[][] twoDimArr = new int[3][2];
int[] oneDimArr = new int[10];
Class<? extends String[]> strArrCls = strArr.getClass();
Class<? extends int[][]> twoDimArrCls = twoDimArr.getClass();
Class<? extends int[]> oneDimArrCls = oneDimArr.getClass();
```

枚举类型也有对应的Class：

```java
    enum Size {
        SMALL, MEDIUM, BIG
    }


		Class<Size> sCls = Size.class;
```



通过Class对象可以获得很多信息：

#### 1.名称信息

Class有四种与名称相关的方法。

 不同Class对象的各种名称方法的返回值：

![image-20220325115839644](images/image-20220325115839644.png)

getSimpleName：名称不带包信息；

getName：Java内部使用的真正的名称；

getCanonicalName：名称更为友好；

getPackage返回的是包信息。

> 数组类型的getName，`[`表示数组，几个就表示几维数组；
>
> 数组的类型用一个大写字符表示，L表示类或接口，I表示int，其它基本类型：boolean(Z)、byte(B)、char(C)、double(D)、float(F)、long(J)、short(S)；
>
> 对于引用类型的数组，注意最后有一个分号。

#### 2.字段信息

类中定义的静态和实例变量都被称为**字段**，用类**Field**表示，位于包<u>java.lang.reflect</u>下，后文涉及的反射相关的类都位于该包下。Class有4个获取字段信息的方法：

```java
//返回所有的public字段，包括其父类的，如果没有字段，返回空数组
public Field[] getFields()
//返回本类声明的所有字段，包括非public的，但不包括父类的
public Field[] getDeclaredFields()
//返回本类或父类中指定名称的public字段，找不到抛出异常NoSuchFieldException
public Field getField(String name)
//返回本类中声明的指定名称的字段，找不到抛出异常NoSuchFieldException
public Field getDeclaredField(String name)
```

Field也有很多方法，可以获取字段的信息，也可以通过Field访问和操作指定对象中该字段的值：

```java
//获取字段的名称
public String getName()
//判断当前程序是否有该字段的访问权限
public boolean isAccessible()
//flag设为true表示忽略Java的访问检查机制，以允许读写非public的字段
public void setAccessible(boolean flag)
//获取指定对象obj中该字段的值
public Object get(Object obj)
//将指定对象obj中该字段的值设为value
public void set(Object obj, Object value)
```

在get/set方法中，对于静态变量，obj被忽略，可以为null，如果字段值为基本类型， get/set会自动在基本类型与对应的包装类型间进行转换；对于private字段，直接调用get/set会抛出非法访问异常IllegalAccessException，应该先调用setAccessible(true)以关闭Java的检查机制：

```java
List<String> obj = Arrays.asList(new String[]{"Andy", "编程"});
Class<? > lcls = obj.getClass();
System.out.println(lcls.getName());
for(Field f : lcls.getDeclaredFields()){  // 返回的是Arrays静态内部类ArrayList中的两个字段
  f.setAccessible(true);
  System.out.println(f.getName()+" - "+f.get(obj) + " - " + Modifier.toString(f.getModifiers()));
}
```

结果：

```java
java.util.Arrays$ArrayList
serialVersionUID - -2764017481108945198 - private static final
a - [Ljava.lang.String;@20fa23c1 - private final
```

Field的其它方法：

```java
//返回字段的修饰符，结果int通过Modifier类的静态方法toString解读
public int getModifiers()
//返回字段的类型
public Class<? > getType()
//以基本类型操作字段
public void setBoolean(Object obj, boolean z)
public boolean getBoolean(Object obj)
public void setDouble(Object obj, double d)
public double getDouble(Object obj)
//查询字段的注解信息，下一章介绍注解
public <T extends Annotation> T getAnnotation(Class<T> annotationClass)
public Annotation[] getDeclaredAnnotations()    
```



#### 3.方法信息

类中定义的静态和实例方法都被称为**方法**，用类**Method**表示。

```java
//返回所有的public方法，包括其父类的，如果没有方法，返回空数组
public Method[] getMethods()
//返回本类声明的所有方法，包括非public的，但不包括父类的
public Method[] getDeclaredMethods()
//返回本类或父类中指定名称和参数类型的public方法，
//找不到抛出异常NoSuchMethodException
public Method getMethod(String name, Class<? >... parameterTypes)
//返回本类中声明的指定名称和参数类型的方法，找不到抛出异常NoSuchMethodException
public Method getDeclaredMethod(String name, Class<? >... parameterTypes)
```

> 注：`Class<? >... parameterTypes`表示Class的数组。

Method的基本方法：

```java
//获取方法的名称
public String getName()
//flag设为true表示忽略Java的访问检查机制，以允许调用非public的方法
public void setAccessible(boolean flag)
//在指定对象obj上调用Method代表的方法，传递的参数列表为args
public Object invoke(Object obj, Object... args) throws
    IllegalAccessException, Illegal-ArgumentException, InvocationTargetException
```

 对invoke方法，如果Method为静态方法，obj被忽略，可以为null, args可以为null，也可以为一个空的数组，方法调用的返回值被包装为Object返回，如果实际方法调用抛出异常，异常被包装为InvocationTargetException重新抛出，可以通过getCause方法得到原异常。

```java
Class<Integer> icls = Integer.class;
try {
  Method method = icls.getMethod("parseInt", new Class[]{String.class});
  System.out.println(method.invoke(null, "123"));
} catch (NoSuchMethodException e) {
  e.printStackTrace();
} catch (InvocationTargetException e) {
  e.printStackTrace();
}
```

Method还有很多方法，可以获取其修饰符、参数、返回值、注解等信息。

#### 4.创建对象和构造方法

Class有一个用来创建对象的方法：

```java
public T newInstance() throws InstantiationException, IllegalAccessException
```

它会调用类的默认构造方法（即无参public构造方法），如果类没有该构造方法，会抛出异常InstantiationException。

Class中获取所有构造方法的方法：

```java
//获取所有的public构造方法，返回值可能为长度为0的空数组
public Constructor<? >[] getConstructors()
//获取所有的构造方法，包括非public的
public Constructor<? >[] getDeclaredConstructors()
//获取指定参数类型的public构造方法，没找到抛出异常NoSuchMethodException
public Constructor<T> getConstructor(Class<? >... parameterTypes)
//获取指定参数类型的构造方法，包括非public的，没找到抛出异常NoSuchMethodException
public Constructor<T> getDeclaredConstructor(Class<? >... parameterTypes)
```

Constructor表示构造方法，它也有newInstance方法来创建对象：

```java
public T newInstance(Object ... initargs) throws InstantiationException,
        IllegalAccessException, IllegalArgumentException, InvocationTargetException
```

例子：

```java
// 通过指定参数类型，获取到特定构造方法
        Constructor<StringBuilder> constructor = StringBuilder.class.getConstructor(new Class[]{int.class});
        StringBuilder sb = constructor.newInstance(100);
```

Constructor还有很多获取关于构造方法信息（参数、修饰符、注解等）的方法。



#### 5.类型检查和转换

🔖

#### 6.Class的类型信息

```java
public native boolean isArray()  //是否是数组
public native boolean isPrimitive()  //是否是基本类型
public native boolean isInterface()  //是否是接口
public boolean isEnum()  //是否是枚举
public boolean isAnnotation()  //是否是注解
public boolean isAnonymousClass()  //是否是匿名内部类
public boolean isMemberClass()  //是否是成员类，成员类定义在方法外，不是匿名类
public boolean isLocalClass()  //是否是本地类，本地类定义在方法内，不是匿名类
```



#### 7.类的声明信息

类的声明信息，如修饰符、父类、接口、注解等：

```java
//获取修饰符，返回值可通过Modifier类进行解读
public native int getModifiers()
//获取父类，如果为Object，父类为null
public native Class<? super T> getSuperclass()
//对于类，为自己声明实现的所有接口，对于接口，为直接扩展的接口，不包括通过父类继承的
public native Class<? >[] getInterfaces();
//自己声明的注解
public Annotation[] getDeclaredAnnotations()
//所有的注解，包括继承得到的
public Annotation[] getAnnotations()
//获取或检查指定类型的注解，包括继承得到的
public <A extends Annotation> A getAnnotation(Class<A> annotationClass)
public boolean isAnnotationPresent(
        Class<? extends Annotation> annotationClass)
```



#### 8.类的加载

Class有两个静态方法，可以根据类名加载类：

```java
public static Class<? > forName(String className)
public static Class<? > forName(String name, boolean initialize,  ClassLoader loader)
```

ClassLoader表示类加载器；initialize表示加载后，是否执行类的初始化代码（如static语句块）。第一个方法中没有传这些参数，相当于：

```java
Class.forName(className, true, currentLoader)
```

className与Class.getName的返回值是一致的，是Java内部使用的名称。如对于String数组：

```java
String name = "[Ljava.lang.String; ";
Class cls = Class.forName(name);
System.out.println(cls == String[].class);
```



#### 9.反射与数组

对于数组类型，有一个专门的方法，可以获取它的元素类型：

```java
public native Class<? > getComponentType()
```

如：

```java
String[] arr = new String[]{};
System.out.println(arr.getClass().getComponentType()); // class java.lang.String
```

java.lang.reflect包中有一个针对数组的专门的类Array（注意不是java.util中的Arrays)，提供了对于数组的一些反射支持，以便于统一处理多种类型的数组，主要方法有：

```java
//创建指定元素类型、指定长度的数组
public static Object newInstance(Class<? > componentType, int length)
//创建多维数组
public static Object newInstance(Class<? > componentType, int... dimensions)
//获取数组array指定的索引位置index处的值
public static native Object get(Object array, int index)
//修改数组array指定的索引位置index处的值为value
public static native void set(Object array, int index, Object value)
//返回数组的长度
public static native int getLength(Object array)
```



#### 10.反射与枚举

获取所有的枚举常量:

```java
public T[] getEnumConstants()
```

### 21.2 应用示例：实现简单的通用序列化/反序列化



### 21.3 反射与泛型

泛型参数在运行时会被擦除，在类信息Class中依然有关于泛型的一些信息，可以通过反射得到。

Class有如下方法，可以获取类的泛型参数信息：

```java
public TypeVariable<Class<T>>[] getTypeParameters()
```

Field有：

```java
public Type getGenericType()
```

Method有：

```java
public Type getGenericReturnType() 
public Type[] getGenericParameterTypes()
public Type[] getGenericExceptionTypes()
```

Constructor有：

```java
public Type[] getGenericParameterTypes()
```

Type是一个接口，Class实现了Type, Type的其他子接口还有：

- TypeVariable：类型参数，可以有上界，比如T extends Number；
- ParameterizedType：参数化的类型，有原始类型和具体的类型参数，比如`List<String>`；
- WildcardType：通配符类型，比如？、? extends Number、? superInteger。

；🔖



**如果能用接口实现同样的灵活性，就不要使用反射。**原因：

1. 反射更容易出现运行时错误，使用显式的类和接口，编译器能帮我们做类型检查，减少错误，但使用反射，类型是运行时才知道的，编译器无能为力。
2. 反射的性能要低一些，在访问字段、调用方法前，反射先要查找对应的Field/Method，要慢一些。



## 22 注解

在Java中，注解就是给程序添加一些信息，用字符@开头，这些信息用于修饰它后面紧挨着的其他代码元素，比如类、接口、字段、方法、方法中的参数、构造方法等。注解可以被编译器、程序运行时和其他工具使用，用于增强或修改程序行为等。

### 22.1 内置注解

#### @Override

修饰方法，表示重写该方法。不写，也改变不了”重写“的本质，但还是加上，减少编程的错误。

#### @Deprecated

修饰范围很广，可以是类、方法、字段、参数等，表示代码过时，是一种警告，不是强制性的。

在声明元素为@Deprecated时，应该用Java文档注释的方式同时说明替代方案，优先使用替代方案。

从Java 9开始，@Deprecated多了两个属性：since和forRemoval。since是一个字符串，表示是从哪个版本开始过时的；forRemoval是一个boolean值，表示将来是否会删除。

#### @SuppressWarnings

表示压制Java的编译警告，它有一个必填参数，表示压制哪种类型的警告，它也可以修饰大部分代码元素，在更大范围的修饰也会对内部元素起效，比如，在类上的注解会影响到方法，在方法上的注解会影响到代码行。

### 22.2 框架和库的注解

#### 1.Jackson

Jackson是一个通用的序列化库，其中一些注解可以对序列化进行定制：

- 使用@JsonIgnore和@JsonIgnoreProperties配置忽略字段。
- 使用@JsonManagedReference和@JsonBackReference配置互相引用关系。
- 使用@JsonProperty和@JsonFormat配置字段的名称和格式等。

注解出现之前，同样的配置功能使用配置文件也同样能实现，但配置项和要配置的程序元素不在一个地方，难易管理和维护。

#### 2.依赖注入容器

现代Java开发经常利用某种框架**管理对象的生命周期及其依赖关系**。

#### 3.Servlet 3.0

Servlet是Java为Web应用提供的技术框架，早期的Servlet只能在web.xml中进行配置，而Servlet 3.0则开始支持注解，可以使用@WebServlet配置一个类为Servlet。

#### 4.Web应用框架



#### 5.神奇的注解

在声明式编程风格中，程序都由三个组件组成：

- 声明的关键字和语法本身
- 系统/框架/库，它们负责解释、执行声明式的语句
- 应用程序，使用声明式风格写程序

**在编程的世界里，访问数据库的SQL语言、编写网页样式的CSS，以及正则表达式、函数式编程都是这种风格，这种风格降低了编程的难度，为应用程序员提供了更为高级的语言，使得程序员可以在更高的抽象层次上思考和解决问题，而不是陷于底层的细节实现。**

### 22.3 创建注解

`@Override`的定义：

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Override {
}
```

<u>@Target和@Retention</u>是**元注解**，专门用于定义注解本身。

@Target表示注解的目标。ElementType是一个枚举，主要可选值有：

- TYPE：表示类、接口（包括注解），或者枚举声明；
- FIELD：字段，包括枚举常量；
- METHOD：方法；
- PARAMETER：方法中的参数；
- CONSTRUCTOR：构造方法；
- LOCAL_VARIABLE：本地变量；
- MODULE：模块（Java 9引入的）。

如果没有声明@Target，默认为适用于所有类型。

@Retention表示注解信息保留到什么时候，取值只能有一个，类型为RetentionPolicy，它是一个枚举，有三个取值。

- SOURCE：只在源代码中保留，编译器将代码编译为字节码文件后就会丢掉。
- CLASS：保留到字节码文件中，但Java虚拟机将class文件加载到内存时不一定会在内存中保留。
- UNTIME：一直保留到运行时。

没有声明@Retention，则默认为CLASS。

可以为注解定义一些参数，定义的方式是在注解内定义一些方法，返回值类型表示参数的类型，比如：

```java
// 定义
...
public @interface SuppressWarings {
  String[] value();
}

// 使用
@SuppressWarings(value={"deprecation", "unused"})
```

当只有一个参数，且名称为value时，使用时可简写：

```java
@SuppressWarings({"deprecation", "unused"})
```

注解内参数的类型，可以是基本类型、String、Class、枚举、注解，以及这些类型的数组。

参数定义时可以使用default指定一个默认值，比如：

```java
public @interface Inject {
  boolean optional() default false;
}
```

如果参数没有提供默认值，使用注解时必须提供具体的值，不能为null。

元注解<u>@Documented</u>，表示注解信息包含到生成的文档中。

注解不能继承。不过注解有一个与继承有关的元注解<u>@Inherited</u>：

```java
public class InheritDemo {
  @Inherited
  @Retention(RetentionPolicy.RUNTIME)
  static @interface Test {
  }
  
  @Test
  static class Base {
  }
  
  static class Child extends Base {
  }
  
  public static void main(String[] args) {
    System.out.println(Child.class.isAnnotationPresent(Test.class));  // true
  }
}
```

注解@Inherited会让Child继承了Base的注解。

### 22.4 查看注解信息

反射相关类中与注解相关的方法，Class、Field、Method、Constructor中都有：

```java
//获取所有的注解
public Annotation[] getAnnotations()
//获取所有本元素上直接声明的注解，忽略inherited来的
public Annotation[] getDeclaredAnnotations()
//获取指定类型的注解，没有返回null
public <A extends Annotation> A getAnnotation(Class<A> annotationClass)
//判断是否有指定类型的注解
public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass)
```

Annotation是一个接口，表示注解，具体定义：

```java
public interface Annotation {
    boolean equals(Object obj);
    int hashCode();
    String toString();
    //返回真正的注解类型
    Class<? extends Annotation> annotationType();
}
```

内部实现时，所有的注解类型都是扩展的Annotation。

对于Method和Contructor，它们都有方法参数，而参数也可以有注解，因而它们有另外的方法：

```java
public Annotation[][] getParameterAnnotations()
```

返回结果为一个二维数组，每个参数对应一个一维数组。



```java
public class MethodAnnotations {
    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.RUNTIME)
    static @interface QueryParam {
        String value();
    }
    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.RUNTIME)
    static @interface DefaultValue {
        String value() default "";
    }
    public void hello(@QueryParam("action") String action,
                      @QueryParam("sort") @DefaultValue("asc") String sort){
        //…
    }
    public static void main(String[] args) throws Exception {
        Class<? > cls = MethodAnnotations.class;
        Method method = cls.getMethod("hello",
                new Class[]{String.class, String.class});
        Annotation[][] annts = method.getParameterAnnotations();
        for(int i=0; i<annts.length; i++){
            System.out.println("annotations for paramter " + (i+1));
            Annotation[] anntArr = annts[i];
            for(Annotation annt : anntArr){
                if(annt instanceof QueryParam){
                    QueryParam qp = (QueryParam)annt;
                    System.out.println(qp.annotationType()
                            .getSimpleName()+":"+ qp.value());
                }else if(annt instanceof DefaultValue){
                    DefaultValue dv = (DefaultValue)annt;
                    System.out.println(dv.annotationType()
                            .getSimpleName()+":"+ dv.value());
                }
            }
        }
    }
}
```



定义了注解，通过反射获取到注解信息，但具体怎么利用这些信息呢？



### 22.5 注解的应用：定制序列化

🔖

### 22.6 注解的应用：DI容器



**注解提升了Java语言的表达能力，有效地实现了应用功能和底层功能的分离，框架/库的程序员可以专注于底层实现，借助反射实现通用功能，提供注解给应用程序员使用，应用程序员可以专注于应用功能，通过简单的声明式注解与框架/库进行协作。**



## 23 动态代理

在运行时动态创建一个类，实现一个或多个接口，可以在不修改原有类的基础上动态为通过该类获取的对象添加方法、修改行为。

动态代理是实现面向切面的编程AOP（Aspect OrientedProgramming）的基础。切面的例子有日志、性能监控、权限检查、数据库事务等。

动态代理有两种实现方式：一种是Java SDK提供的；另外一种是第三方库（如cglib）提供的。

### 23.1 静态代理



### 23.2 Java SDK动态代理



### 23.3 cglib动态代理



### 23.4 Java SDK代理与cglib代理比较



### 23.5 动态代理的应用：AOP



## 24 类加载机制

类加载器ClassLoader就是加载其他类的类，它负责将字节码文件加载到内存，创建Class对象。

ClassLoader一般是系统提供的，不需要自己实现，不过，通过创建自定义的ClassLoader，可以实现一些强大灵活的功能，比如：

1. 热部署
2. 应用的模块化和相互隔离
3. 从不同地方灵活加载

### 24.1 类加载的基本机制和过程



### 24.2 理解ClassLoadder



### 24.3 类加载的应用：可配置的策略



### 24.4 自定义ClassLoader

自定义ClassLoader是Tomcat实现应用隔离、支持JSP、OSGI实现动态模块化的基础。



### 24.5 自定义ClassLoader的应用：热部署



## 25 正则表达式

### 25.1 语法



### 25.2 Java API



### 25.3 模板引擎



### 25.4 剖析常见表达式



## 26 函数式编程

### 26.1 Lambda表达式

#### 通过接口传递代码



#### Lambda语法



### 函数式接口



### 预定义的函数接口



### 方法引用



### 函数的复合



### 26.2 函数式数据处理：基本用法

#### 基本示例



#### 中间操作



#### 终端操作



#### 构建流



#### 函数式数据处理思维



### 26.3 函数式数据处理：强大方便的收集器

#### 理解collect



#### 容器收集器



#### 字符串收集器



#### 分组



### 26.4 组合式异步编程 



#### 异步任务管理



#### 与Future/FutrueTask对比



#### 构建依赖单一阶段的任务流



#### 构建依赖两个阶段的任务流



#### 构建依赖多个阶段的任务流



### 26.5 Java 8 的日期和时间API

