





https://github.com/fuzhengwei/itstack-demo-netty

BIO NIO AIO netty

Java NIO
-----

JAVA NIO有两种解释：一种叫非阻塞IO（Non-blocking I/O），另一种也叫新的IO（New I/O），其实是同一个概念。

### NIO VS IO

NIO（JDK1.4）模型是一种同步非阻塞IO，主要有三大核心部分：Channel(通道)，Buffer(缓冲区), Selector（多路复用器）。传统IO基于字节流和字符流进行操作，而NIO基于Channel和Buffer(缓冲区)进行操作，数据总是从通道读取到缓冲区中，或者从缓冲区写入到通道中。Selector(多路复用器)用于监听多个通道的事件（比如：连接打开，数据到达）。因此，单个线程可以监听多个数据通道。
 NIO和传统IO（一下简称IO）之间第一个最大的区别是，IO是面向流的，NIO是面向缓冲区的。 Java IO面向流意味着每次从流中读一个或多个字节，直至读取所有字节，它们没有被缓存在任何地方。此外，它不能前后移动流中的数据。如果需要前后移动从流中读取的数据，需要先将它缓存到一个缓冲区。NIO的缓冲导向方法略有不同。数据读取到一个它稍后处理的缓冲区，需要时可在缓冲区中前后移动。这就增加了处理过程中的灵活性。但是，还需要检查是否该缓冲区中包含所有您需要处理的数据。而且，需确保当更多的数据读入缓冲区时，不要覆盖缓冲区里尚未处理的数据。
 IO的各种流是阻塞的。这意味着，当一个线程调用read() 或 write()时，该线程被阻塞，直到有一些数据被读取，或数据完全写入。该线程在此期间不能再干任何事情了。 NIO的非阻塞模式，使一个线程从某通道发送请求读取数据，但是它仅能得到目前可用的数据，如果目前没有数据可用时，就什么都不会获取。而不是保持线程阻塞，所以直至数据变的可以读取之前，该线程可以继续做其他的事情。 非阻塞写也是如此。一个线程请求写入一些数据到某通道，但不需要等待它完全写入，这个线程同时可以去做别的事情。 线程通常将非阻塞IO的空闲时间用于在其它通道上执行IO操作，所以一个单独的线程现在可以管理多个输入和输出通道（channel）。

#### NIO优点：

1. 通过Channel注册到Selector上的状态来实现一种客户端与服务端的通信。
2. Channel中数据的读取是通过Buffer , 一种非阻塞的读取方式。
3. Selector 多路复用器  单线程模型，  线程的资源开销相对比较小。



https://www.zhihu.com/question/29005375

https://blog.csdn.net/forezp/article/details/88414741

https://www.cnblogs.com/lfs2640666960/p/9970353.html

https://tech.meituan.com/2016/11/04/nio.html

https://www.cnblogs.com/minikobe/p/12166280.html



使用场景



https://www.zhihu.com/question/67535292/answer/1248887503



https://segmentfault.com/a/1190000040458363
