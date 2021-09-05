# 《Java核心技术卷二第10版》笔记


## 1 Java SE 8的流库

### 1.1 从迭代到流

`java.util.Collection`的  `stream()`，`parallelStream()`

流遵循了“**做什么而非怎么做**”的原则。

流与集合的差异：

1. 流并不存储其元素。
2. 流的操作不会修改其数据源。
3. 流的操作是尽可能**惰性执行**的。也就是需要结果时，操作才执行。

```java
			// 当前目录是项目的根目录，不是.java文件所在目录
        String contents = new String(Files.readAllBytes(Paths.get("../../gutenberg/alice30.txt")),
                StandardCharsets.UTF_8);
        // \\PL+ 表示非字母字符
        List<String> words = Arrays.asList(contents.split("\\PL+"));

        long count = 0;
        for (String w : words) {
            if (w.length() > 12) {
                count++;
            }
        }
        System.out.println(count);

        count = words.stream().filter(w -> w.length() > 12).count();
        System.out.println(count);

        count = words.parallelStream().filter(w -> w.length() > 12).count();
        System.out.println(count);
```

stream() 和 parallelStream() 分别获得顺序流和并行流，当然此处结果没有多大影响。

正则中P表示”非“，L表示字母。”\PL+“ 表示非字母字符，"\\PN+" 非数字，"\\PZ+" 非分隔符，"\\PS+" 非符号等等。

### 1.2 流的创建

```java
// 静态方法`Stream.of()`
Stream<String> words = Stream.of(contents.split("\\PL+"));
Stream<String> song = Stream.of("gently", "down", "the", "stream");

// 创建不包含任何元素的流：
Stream<String> silence = Stream.empty();

Stream<String> echos = Stream.generate(() -> "Echo");

Stream<Double> randoms = Stream.generate(Math::random);

Stream<BigInteger> integers = Stream.iterate(BigInteger.ONE, n -> n.add(BigInteger.ONE));

Stream<String> wordsAnotherWay = Pattern.compile("\\PL+").splitAsStream(contents);

try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)){
}
```





### 1.3 filter, map, flatMap

**流的转换会产生一个新的流，它的元素派生自另一个流中的元素。**

将一个字符串流转换为只包含长单词的另一个流：

```java
List<String> wordlist = ...;
Stream<String> longWords = wordlist.stream().filter(w -> w.length() > 12);
```

将所有单词都转换为小写：(使用方法引用)

```java
Stream<String> lowercaseWords = words.stream().map(String::toLowerCase);

```

包含所有单词首字母的流：（使用lambda表达式）

```java
Stream<String> firstLetters = words.stream().map(s -> s.substring(0, 1))
```

把字符串转换为字符流：

```java
public static Stream<String> letters(String s) {
  List<String> result = new ArrayList<>();
  for (int i = 0; i < s.length(); i++) {
    result.add(s.substring(i, i + 1));
  }
  return result.stream();
}
// letters("boat") 返回值是流["b", "o", "a", "t"]
```

包含流的流：

```java
Stream<Stream<String>> result = words.stream().map(w -> letters(w));
// [...["y","o","u","r"],["b","o","a","t"],...]
```

如果换成`flatMap`结果就变成：

```java
[..."y","o","u","r","b","o","a","t",...]
```



### 1.4 抽取子流和连接流

`stream.limit(n)`，产生一个包含100个随机数的流：

```java
Stream<Double> randoms = Stream.generate(Math::random).limit(100);
```

`stream.skip(n)`，跳过前n个元素产生流：

```java
Stream<String> words = Stream.of(contents.split("\\PL+")).skip(1);
```

`stream.concat()`，连接两个流：

```java
Stream<String> combined = Stream.concat(letters("Andy"), letters("Ron"));
```



### 1.5 其他的流转换

删除流中重复的元素：

```java
Stream<String> uniqueWords = Stream.of("apple", "orange", "apple");
```

流的排序：

```java
Stream<String> longestFirst = words.stream().sorted(Comparator.comparing(String::length).reversed());
```

peek方法会产生于原来流元素相同的流，不过每一次获取一个元素时，都会调用一个函数：

```java
Object[] powers = Stream.iterate(1.0, p -> p * 2)
                .peek(e -> System.out.println("Fetcting " + e))
                .limit(10).toArray();
```

结果为：

```
Fetcting 1.0
Fetcting 2.0
Fetcting 4.0
Fetcting 8.0
Fetcting 16.0
Fetcting 32.0
Fetcting 64.0
Fetcting 128.0
Fetcting 256.0
Fetcting 512.0
```



### 1.6 简单约简🔖



### 1.7 Optional类型



#### 如何使用Optional值

有效地使用Optional的关键是：**它的值不存在时会有可替代值，存在时就使用这个值。**

```java
// Optional不存在就使用默认值。orElse()
Optional<String> optionalValue = wordList.stream().filter(s -> s.contains("fred")).findFirst();
System.out.println(optionalValue.orElse("No word") + " contains fred");

Optional<String> optionalString = Optional.empty();
String result = optionalString.orElse("N/A");
System.out.println("result: " + result);

// Optional不存在，计算默认值。orElseGet
result = optionalString.orElseGet(() -> Locale.getDefault().getDisplayName());
System.out.println("result: " + result);

// Optional不存在，就抛出异常。orElseThrow
try {
    result = optionalString.orElseThrow(IllegalStateException::new);
    System.out.println("result: " + result);
} catch (Throwable t) {
    t.printStackTrace();
}

// Optional存在就把它传递给一个函数，否则不发生任何事。ifPresent()
optionalValue = wordList.stream().filter(s -> s.contains("red")).findFirst();
optionalValue.ifPresent(s -> System.out.println(s + " contains red"));
```

#### 不适合使用Optional值的方式



#### 创建Optional值

```java
Optional.of(result);

Optional.empty();
```



#### 用flatMap来创建Optional值的函数



### 1.8 收集结果

当处理完流之后，通常会想要查看其元素。



### 1.9 收集到映射表中



```java
 				Map<Integer, String> idToName = people().collect(Collectors.toMap(Person::getId, Person::getName));
        System.out.println("idToName: " + idToName);

        Map<Integer, Person> idToPerson = people().collect(Collectors.toMap(Person::getId, Function.identity()));
        System.out.println("idToPerson: " + idToPerson.getClass().getName() + idToPerson);

        idToPerson = people().collect(Collectors.toMap(Person::getId, Function.identity(),
                (existingValue, newValue) -> {throw new IllegalStateException();}, TreeMap::new));
        System.out.println("idToPerson: " + idToPerson.getClass().getName() + idToPerson);

        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        Map<String, String> languageNames = locales.collect(Collectors.toMap(Locale::getDisplayLanguage,
                l -> l.getDisplayLanguage(l), (existingValue, newValue) -> existingValue));
        System.out.println("languageNames: " + languageNames);

```





### 1.10 群组和分区

```java
Map<String, List<Locale>> countryToLocales = locales.collect(Collectors.groupingBy(Locale::getCountry));

List<Locale> swissLocales = countryToLocales.get("CH");
```



```java
Map<Boolean, List<Locale>> englishAndOtherLocales = locales.collect(Collectors.partitioningBy(l -> l.getLanguage().equals("en")));

List<Locale> englishLocales = englishAndOtherLocales.get(true);
```





### 1.11 下游收集器



### 1.12 约简操作



### 1.13 基本类型流

`IntStream`  `LongStream`  `DoubleStream`



```java
				IntStream is1 = IntStream.generate(() -> (int) (Math.random() * 100));
        show("is1", is1);
        IntStream is2 = IntStream.range(5, 10);
        show("is2", is2);
        IntStream is3 = IntStream.rangeClosed(5, 10);
        show("is3", is3);

        Path path = Paths.get("../../gutenberg/alice30.txt");
        String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);

        Stream<String> words = Stream.of(contents.split("\\PL+"));
        IntStream is4 = words.mapToInt(String::length);
        show("is4", is4);
        
        String sentence = "\uD835\uDD46 is the set of octonions.";
        System.out.println(sentence);
        IntStream codes = sentence.codePoints();
        System.out.println(codes.mapToObj(c -> String.format("%X ", c)).collect(Collectors.joining()));

        Stream<Integer> integers = IntStream.range(0, 100).boxed();
        IntStream is5 = integers.mapToInt(Integer::intValue);
        show("is5", is5);
```



### 1.14 并行流





## 2 输入与输出



### 2.1 输入/输出流

与上一章中的流没有任何关系。

**输入流**：可以读入一个字节序列的对象

**输出流**：可以写入一个字节序列的对象

#### 读写字节

```java
// java.io.InputStream

abstract int read()
  
int read(byte[] b)

int read(byte[] b, int off, int len)
  
long skip(long n)
  
int available()
  
void close()
  
void mark(int readlimit)
  
void reset()
  
boolean markSupported()
```



```java
// java.io.OutputStream

abstract void wirte(int n)
  
void write(byte[] b)
  
void write(byte[] b, int off, int len)
  
void close()
  
void flush()
```



#### 完整的流家族

输入流与输出流的层次结构：

![输入流与输出流的层次结构](../../images/java-029.jpg)

`InputStream`和`OutputStream`用于读写单个字节或字节数组。

`DataInputStream`和 `DataOutputStream`用于读写字符串和数字。

对于Unicode文本，可用抽象类`Reader`和`Writer`的子类。

Reader和Writer的层次结构：

![Reader和Writer的层次结构](../../images/java-030.jpg)



Closeable,Flushable,Readable,Appendable接口：

![Closeable,Flushable,Readable,Appendable接口](../../images/java-031.jpg)

#### 组合输入/输入流过滤器

`FileInputStream`

`FileOutputStream`



### 2.2 文本输入与输出



#### 如何写出文本输出

`PrintWriter`

#### 如何读入文本输入

`Scanner`

`BufferedReader`

#### 以文本格式存储对象



#### 字符编码方式



### 2.3 读写二进制数据

#### DataInput和DataOutput接口

```java
// java.io.DataInput
boolean readBoolean()
byte readByte()
char readChar()
double readDouble()
float readFloat()
int readInt()
long readLong()
short readShort()
void readFully(byte[] b)
void readFully(byte[] b, int off, int len)
String readUTF()
int skipBytes(int n)
```

```java
void writeBoolean(boolean b)
void writeByte(int b)
void writeChar(int c)
void writeDouble(double d)
void writeFloat(float f)
void writeInt(int i)
void writeLong(long l)
void writeShort(int s)
void writeUTF(String s)
```

#### 随机访问文件

`java.io.RandomAccessFile`



#### ZIP文档

`java.util.zip.ZipInputStream`

`java.util.zip.ZipOutputStream`



### 2.4 对象输入/输出流与序列化

#### 保存和加载序列化对象

`ObjectOutputStream`

`ObjectIputStream`



#### 理解对象序列化的文件格式!!



#### 修改默认的序列化机制



#### 序列化单例和类型安全的枚举



#### 版本管理



#### 为克隆使用序列化





### 2.5 操作文件

#### Path



#### 读写文件

```java
// java.nio.file.Files

static byte[] readAllBytes(Path path) 
static List<String> readAllLines(Path path, Charset cs) 

static Path write(Path path, byte[] bytes, OpenOption... options)
static Path write(Path path, Iterable<? extends CharSequence> lines, OpenOption... options)

static InputStream newInputStream(Path path, OpenOption... options)
static OutputStream newOutputStream(Path path, OpenOption... options)

static BufferedReader newBufferedReader(Path path, Charset cs)
static BufferedWriter newBufferedWriter(Path path, Charset cs, OpenOption... options)
```



#### 创建文件和目录

```java
Files.createDirectory(path);
Files.createDirectories(path);
Files.createFile(path);
```



#### 复制、移动和删除文件



#### 获取文件信息



#### 访问目录中的项



```java
Files.list()
Files.walk()
```



#### 使用目录流



#### ZIP文件系统

```java
java.nio.file.FileSystems
  
java.nio.file.FileSystem
```





### 2.6 内存映射文件

#### 内存映射文件的性能

```java
java.nio.channels.FileChannel
  
java.nio.Buffer
  
java.nio.ByteBuffer
  
java.ni.CharBuffer
```



#### 缓存区数据结构



#### 文件加锁机制

```java
java.nio.channels.FileLock
```



### 2.7 正则表达式



```java
java.util.regex.Pattern

java.util.regex.Matcher
```











## 4 网络



### 4.1 连接到服务器

```
telnet time-a.nist.gov 13
```

获得铯原子钟的计量时间：

```
58929 20-03-21 08:26:12 50 0 0 351.8 UTC(NIST) *
```



```
telnet horstmann.com 80
```



##### 用Java连接到服务器

```java
				try (Socket s = new Socket("time-a.nist.gov", 13);
             Scanner in = new Scanner(s.getInputStream(), "UTF-8")){

            while (in.hasNextLine()) {
                String line = in.nextLine();
                System.out.println(line);
            }
        }
```

#### socket超时

对于不同的应用，应该确定合理的超时值。

```java
Socket s = new Socket(...);
s.setSoTimeout(10000); // 单位是毫秒  
```

超时会抛出`SocketTimeoutException`异常。

另一种创建方式：

```java
Socket s = new Socket();
s.connect(new InetSocketAddress(host, port), timeout);
```



#### IP地址

可通过`InetAddress`类把域名转换为IP地址。

获得单个主机IP地址：

```java
InetAddress address = InetAddress.getByName("www.baidu.com");
```

获取所有主机IP地址: 

```java
InetAddress[] addresses = InetAddress.getAllByName(host);
for (InetAddress a : addresses) {
  System.out.println(a);
}
```

获得本机IP地址：

```java
InetAddress localHostAddress = InetAddress.getLocalHost();
```



### 4.2 实现服务器

#### 服务器套接字

客服端程序的输出是服务器输入流，同样服务器的输出流就成为客服端的输入。

每一个服务器程序（比如这边的socket或HTTP Web服务器等），都会不间断地执行下面这个循环：

1. 通过输入数据流从客户端接收一个命令
2. 解码这个客户端命令
3. 收集客户端所请求的信息
4. 通过输出数据流发送信息给客户端



```java
// server/EchoServer.java

		public static void main(String[] args) throws IOException {

        // ServerSocket用于创建服务器套接字
        try (ServerSocket s = new ServerSocket(8189)){
            // 创建监控端口8189的等待程序Socket对象
            try (Socket incoming = s.accept()){
                // 通过Socket对象获得输入流和输出流
                InputStream inStream = incoming.getInputStream();
                OutputStream outStream = incoming.getOutputStream();

                try (Scanner in = new Scanner(inStream, "UTF-8")){
                    // 服务器的输出流就成为客服端的输入
                    PrintWriter out = new PrintWriter(
                            new OutputStreamWriter(outStream, "UTF-8"), true);
                    // 向客服端打印...（也就是客服端的输入）
                    out.println("Hello! Enter BYE to exit.");
                    boolean done = false;
                    while (!done && in.hasNextLine()) {
                        String line = in.nextLine();
                        out.println("Echo: " + line);
                        if (line.trim().equals("BYE")) {
                            done = true;
                        }
                    }
                }
            }
        }
    }

```

启动上面的服务器程序，用Telnet访问：

```shell
$ telnet localhost 8189
Trying ::1...
Connected to localhost.
Escape character is '^]'.
Hello! Enter BYE to exit.
hello!
Echo: hello!
bye
Echo: bye
BYE
Echo: BYE
Connection closed by foreign host.
$ 
```



#### 为多个客户端服务

```java
public class ThreadedEchoServer {

    public static void main(String[] args) {
        try (ServerSocket s = new ServerSocket(8189)) {
            int i = 1;
            // 为什么没有进入死循环
            while (true) {  
                Socket incoming = s.accept();
                System.out.println("Spawning " + i);
                Runnable r = new ThreadEchoHandler(incoming);
                Thread t = new Thread(r);
                t.start();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ThreadEchoHandler implements Runnable {
    private Socket incoming;

    public ThreadEchoHandler(Socket incomingSocket) {
        incoming = incomingSocket;
    }

    @Override
    public void run() {
        try (InputStream inStream = incoming.getInputStream();
             OutputStream outStream = incoming.getOutputStream()) {
            Scanner in = new Scanner(inStream, "UTF-8");
            PrintWriter out = new PrintWriter(
                    new OutputStreamWriter(outStream, "UTF-8"), true);

            out.println("Hello! Enter BYE to exit.");

            boolean done = false;
            while (!done && in.hasNextLine()) {
                String line = in.nextLine();
                out.println("Echo: " + line);
                if (line.trim().equals("BYE")) {
                    done = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```



每个客户端请求都生成单独的线程，这种方法不能实现高的吞吐量，需要使用`java.nio`的一些特性。

#### 半关闭





### 4.3 可中断套接字

🔖



### 4.4 获取Web数

#### URL 和 URI



#### 使用URLConnection获取信息

```java
java.net.URL

java.net.URLConnection
```



#### 提交表单数据



```
java.net.HttpURLConnection
java.net.URLEncoder
java.net.URLDecoder
```



### 4.5 发送Email





## 5 数据库编程

### 5.1 JDBC的设计

ODBC模式    微软  C语言

JDBC

#### JDBC驱动程序类型



#### JDBC的典型用法

传统方式：

![](../../images/java-041.jpg)

三层模式：客户端不直接调用数据库，而是调用服务器上的**中间体层**，由中间体层完成查询数据库查询操作。

优点：将可视化表示（位于客户端）从**业务逻辑**（位于中间层）和**原始数据**（位于数据库）中分离出来。

达到的效果：可以从不同的客户端（Java桌面应用、浏览器或移动APP等）来访问相同的数据和相同的业务规则。

![](../../images/java-042.jpg)



### 5.2 结构化查询语言

JDBC包可以看作是一个用于**将SQL语句传递给数据库的应用编程接口**（API ）。

对表格进行连接操作的好处是，**能够避免在数据库表中出现不必要的重复数据**。

在关系模型中，将数据分步到多个表中，是为了使得所有信息都不会出现不必要的重复。



### 5.3 JDBC配置🔖

#### 连接数据库URL

一般语法：

*jdbc:subprotocol:other stuff*

**subprotocol**是连接到数据库的具体驱动程序。

**other stuff**不同的驱动程序有不动的格式。

举例：

```properties
jdbc:derby://localhost:1527/COREJAVA;crete=tre

jdbc:postgresql:COREJAVA
  
jdbc:mysql://localhost:3030/COREJAVA
```



https://www.runoob.com/java/java-mysql-connect.html





### 5.4 使用JDBC语句

#### 执行SQL语句



#### 管理连接、语句和结果集



#### 分析SQL异常



![SQL异常类型](../../images/java-040.jpg)



#### 组装数据库



### 5.5 执行查询操作

#### 预备语句（prepared statement）



#### 读写LOB



#### SQL转义



#### 多结果集



#### 获取自动生成的键



### 5.6 可滚动和可更新的结果集



### 5.7 行集



### 5.8 元数据



### 5.9 事物



### 5.10 高级SQL类型



## 6 日期和时间API



### 6.1 时间线



### 6.2 本地时间



### 6.3 日期调整器



### 6.4 本地时间



### 6.5 时区时间



### 6.6 格式化和解析



## 7 国际化



### 7.1 Locale对象



### 7.2 数字格式



### 7.3 货币



### 7.4 日期和时间



### 7.5 排序和范化



### 7.6 消息格式化



### 7.7 文本文件和字符集



### 7.8 资源包





## 8 脚本、编译与注解处理



### 8.1 Java平台的脚本



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



## 9 安全



### 9.1 类加载器



### 9.2 安全管理器与访问权限



### 9.3 用户认证



### 9.4 数字签名



### 9.5 加密





## 12 本地方法

**java方法：**是由java语言编写，编译成字节码，存储在class文件中的。java方法是与平台无关的。

**本地方法：**本地方法是由其他语言（如C、C++ 或其他汇编语言）编写，编译成和处理器相关的代码。本地方法保存在动态连接库中，格式是各个平台专用的，运行中的java程序调用本地方法时，虚拟机装载包含这个本地方法的动态库，并调用这个方法。



### 12.1 从Java程序中调用C函数



### 12.2 数值参数与返回值



### 12.3 字符串参数



### 12.4 访问域



### 12.5 编码签名



### 12.6 调用Java方法



### 12.7 访问数组元素



### 12.8 错误处理



### 12.9 使用调用API





### 12.10 完整的示例：访问Windows注册表