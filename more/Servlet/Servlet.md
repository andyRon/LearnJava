https://www.runoob.com/servlet/servlet-intro.html

## Servlet教程

Servlet 为创建基于 web 的应用程序提供了基于组件、独立于平台的方法，可以不受 CGI （Common Gateway Interface，公共网关接口）程序的性能限制。Servlet **有权限访问所有的 Java API，包括访问企业级数据库的 JDBC API**。

### 简介

Servlet 本身不能独立运行，需要在一个web应用（Tomcat）中运行。

Servlet是用Java编写的[服务器](https://baike.baidu.com/item/服务器/100571)端程序，具有独立于平台和[协议](https://baike.baidu.com/item/协议/13020269)的特性，主要功能在于交互式地浏览和生成数据，生成动态[Web](https://baike.baidu.com/item/Web/150564)内容。

![Servlet 架构](https://www.runoob.com/wp-content/uploads/2014/07/servlet-arch.jpg)



Servlet 相比于 CGIDE优势：

- 性能明显更好。
- Servlet 在 Web 服务器的地址空间内执行。这样它就没有必要再创建一个单独的进程来处理每个客户端请求。
- Servlet 是独立于平台的，因为它们是用 Java 编写的。
- 服务器上的 Java 安全管理器执行了一系列限制，以保护服务器计算机上的资源。因此，Servlet 是可信的。
- Java 类库的全部功能对 Servlet 来说都是可用的。它可以通过 sockets 和 RMI 机制与 applets、数据库或其他软件进行交互。

### Servlet 任务

- 读取客户端（浏览器）发送的显式的数据。这包括网页上的 HTML 表单，或者也可以是来自 applet 或自定义的 HTTP 客户端程序的表单。
- 读取客户端（浏览器）发送的隐式的 HTTP 请求数据。这包括 cookies、媒体类型和浏览器能理解的压缩格式等等。
- 处理数据并生成结果。这个过程可能需要访问数据库，执行 RMI 或 CORBA 调用，调用 Web 服务，或者直接计算得出对应的响应。
- 发送显式的数据（即文档）到客户端（浏览器）。该文档的格式可以是多种多样的，包括文本文件（HTML 或 XML）、二进制文件（GIF 图像）、Excel 等。
- 发送隐式的 HTTP 响应到客户端（浏览器）。这包括告诉浏览器或其他客户端被返回的文档类型（例如 HTML），设置 cookies 和缓存参数，以及其他类似的任务。

#### Servlet 包

Java Servlet 是运行在带有支持 Java Servlet 规范的解释器的 web 服务器上的 Java 类。 可以使用 **javax.servlet** 和 **javax.servlet.http** 包创建。

Java Servlet 就像任何其他的 Java 类一样已经被创建和编译。在您安装 Servlet 包并把它们添加到您的计算机上的 Classpath 类路径中之后，您就可以通过 JDK 的 Java 编译器或任何其他编译器来编译 Servlet。



### Servlet环境设置



#### 设置JDK



#### 设置Web服务器：Tomcat



#### 设置ClassPath

由于 Servlet 不是 Java 平台标准版的组成部分，所以您必须为编译器指定 Servlet 类的路径。



### Servlet 生命周期

Servlet 通过调用 **init ()** 方法进行初始化。

Servlet 调用 **service()** 方法来处理客户端的请求。

Servlet 通过调用 **destroy()** 方法终止（结束）。

最后，Servlet 是由 JVM 的垃圾回收器进行垃圾回收的。

#### init()

init 方法只调用一次，在第一次创建 Servlet 时被调用。

#### service() 

Servlet 容器（即 Web 服务器）调用 service() 方法来处理来自客户端（浏览器）的请求，并把格式化的响应写回给客户端。

```java
public void service(ServletRequest request, 
                    ServletResponse response) 
      throws ServletException, IOException{
}
```

service 方法在适当的时候调用 doGet、doPost、doPut、doDelete 等方法。

#### destroy() 方法

destroy() 只在 Servlet 生命周期结束时被调用一次。destroy() 方法可以让 Servlet **关闭数据库连接、停止后台线程、把 Cookie 列表或点击计数器写入到磁盘，并执行其他类似的清理活动**。

#### 架构图

![Servlet 生命周期](https://www.runoob.com/wp-content/uploads/2014/07/Servlet-LifeCycle.jpg)

### Servlet实例

> java和javax都是Java的API(Application Programming Interface)包，java是核心包，javax的x是extension的意思，也就是扩展包。java类库是java发布之初就确定了的基础库，而javax类库则是在上面增加的一层东西，就是为了保持版本兼容要保存原来的，但有些东西有了更好的解决方案，所以，就加上些，典型的就是awt(Abstract Windowing ToolKit) 和swing。



> 没有`javax.servlet.*`问题？ 解决，在IDEA中
>
> 1、选中项目（在IntelliJ中称为Module）；
> 2、点击右键，选择open modual settings；
> 3、在弹出的窗口左端选择Libraries；
> 4、点击顶端的一个类似加号“+”的图标，选择java；
> 5、在弹出的窗口中选择tomcat所在的目录，进入里面的lib目录，寻找servlet-api.jar这个jar包（如果JSP页面也有相关的JavaWeb对象，则还要寻找jsp-api.jar；如果只有Servlet，则只选择servlet-api.jar）；
> 6、选中上述jar包，依次点击OK。



?? 尝试不要用IDEA