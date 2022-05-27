深入剖析Tomcat
---



## 前言

Tomcat 4.1.12  Tomcat 5.0.18





### servlet容器是如何工作的

三个操作：

1. 创建一个request对象。
2. 创建一个调用Servlet的response对象，用来向Web客户端发送响应。

3. 调用Servlet的service()方法，将request对象和response对象作为参数传入。



### Catalina

Catalina是servlet容器。
Catalina主要模块：连接器（connector）和容器（container）。

连接器负责将一个请求与容器相关联，包括为它接收到的每个HTTP请求创建一个request对象和一个response对象。容器从连接器中接收到request对象和response对象，并负责调用相应的Servlet的service()方法。



https://brainysoftware.com/home



## 1 一个简单的Web服务器

### 1.1 HTTP
RFC 2616

#### HTTP请求
```
请求方法 URI 协议/版本
请求头

实体
```
示例：
```
POST /index.html HTTP/1.1
Accept: text/html,application/json,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36 Hutool
Accept-Encoding: gzip, deflate
Accept-Language: zh-CN,zh;q=0.8
Content-Type: application/x-www-form-urlencoded;charset=UTF-8
Cache-Control: no-cache
Pragma: no-cache
Host: localhost:8080
Connection: keep-alive
Content-Length: 27

firstName=Andy&lastName=Ron
```

#### HTTP响应
```
协议 状态码 描述
响应头

响应实体段
```

### 1.2 Socket类
```
java.net.Socket
java.net.ServerSocket
```



## 2 一个简单的Servlet容器

### 2.1 javax.servlet.Servlet接口

Servlet编程需要使用到`javax.servlet`和`javax.servlet.http`两个包下的接口和类。其中，`javax.servlet.Servlet`接口最为重要。
所有Servlet程序都必须实现该接口或继承自实现了该接口的类。
```java
public interface Servlet {
    public void init(ServletConfig config) throws ServletException;
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException;
    public void destroy();

    public ServletConfig getServletConfig();
    public String getServletInfo();
}
```
init、service、destroy方法与servlet的生命周期相关。
1. 当实例化某个servlet类后，servlet容器会调用init方法进行初始化。
    执行一次
    载入数据库驱动程序、初始化默认值等。
2. 当servlet的一个客户端请求到达后，servlet容器就调用相应的servlet的service()方法，并将`javax.servlet.servletRequest`对象（客服端的Http请求信息）和`javax.servlet.servletResponse`对象（封装servlet的响应信息）作为参数传入。
    在servlet对象的生命周期内，service()方法会被多次调用。
3. 在讲servlet实例从服务中移除前，servlet容器会调用实例额的destroy()方法。
    一般当servlet容器关闭或servlet容器要释放内存时，才会将servlet实例移除。
    只有当servlet实例的service()方法中所有线程都退出或执行超时后，才会调用destroy方法。
    

### 2.2 app1

对每个HTTP请求，一个功能齐全的servlet容器要做：
- 当第一次调用某个servlet时，要载入该servlet类，并调用其init方法（仅此一次)； 
- 针对每个request请求，创建一个javax.servlet.ServletRequest实例和一个javax.servlet.ServletResponse实例； 
- 调用该servlet的service方法，将servletRequest 对象和 servletResponse 对象作为参数传人；
- 当关闭该servlet类时，调用其destroy方法，并卸载该servlet类。

HttpServer1的await()方法会等待HTTP请求，为接收的每个请求创建一个Request和Response对象，并根据请求的是静态资源或servlet，将请求分发给一个StaticResourceProcessor实例或ServletProcessor实例。
```
http://machineName:port/staticResource
http://machineName:port/servlet/servletClass
```
测试调用:
```
http://localhost:8080/index.html
http://localhost:8080/servlet/PrimitiveServlet
```


加载类使用的`java.net.URLClassLoader`是`java.lang.ClassLoader`的子类。

🔖 PrimitiveServlet 路径找不到什么。 java.lang.ClassNotFoundException: PrimitiveServlet

```java
    @Override
    public PrintWriter getWriter() throws IOException {
        // 第二个参数 autoFlush = true，表示对println()方法的任何调用都会刷新输出，但调用print()不会刷新输出。
        writer = new PrintWriter(output, true);
        return writer;
    }
    
```
如果在servlet的service()方法的最后一行调用print方法，则输出内容不会被发送给浏览器。



### 2.3 app2

使用外观类，增加安全性



## 3 连接器

建立一个连接器增强2章的功能，用一种更好的方法来创建request和response对象。


### 3.1 StringManager

`org.apache.catalina.util.StringManager`

Tomcat处理错误消息的方法是将错误消息存储在一个properties文件中，便于读取和编辑。
Tomcat将properties文件划分为到不同的包中。例如，org.apache.catalina.connector包下的properties属性文件包含改包中任何类可能抛出的所有异常信息。
每个properties文件都是用`org.apache.catalina.util.StringManager`类的一个实例来处理。

不同语言

StringManager是单例类，只有通过静态方法getManager()获得其实例，该方法需要指明一个包名作为参数。 每个StringManager实例都会以这个包名作为其键，存储在一个Hashtable中。

🔖

### 3.2 




## 4 Tomcat的默认连接器

### 4.1 HTTP 1.1 的新特性

### 4.2 Connector接口

### 4.3 HttpConnector类

### 4.4 HttpProcessor类

### 4.5 Request对象

### 4.6 Response对象

### 4.7 处理请求

### 4.8 简单的Container应用程序



## 5 servlet容器

### 5.1 Container接口

### 5.2 管道任务

### 5.3 Wrapper接口

### 5.4 Context接口

### 5.5 Wrapper应用程序

### 5.6 Context应用程序


## 6 生命周期

### 6.1 Lifecycle接口

### 6.2 LifecycleEvent类

### 6.3 LifecycleListener接口

### 6.4 LifecycleSupport类

### 6.5 


## 7 日志记录器

### 7.1 Logger接口

### 7.2 Tomcat的日志记录器

### 7.3 


## 8 载入器

### 8.1 Java的类载入器

### 8.2 Loader接口

### 8.3 Reloader接口

### 8.4 WebappLoader类

### 8.5 WebappClassLoader类


## 9 Session管理

### 9.1 Session对象

### 9.2 Manager

### 9.3 存储器

### 9.4


## 10 安全性

### 10.1 领域

### 10.2 GenericPrincipal类

### 10.3 LoginConfig类

### 10.4 Authenticator接口

### 10.5 安装验证器阀

### 10.6 


## 11 StandardWrapper

### 11.1 方法调用序列

### 11.2 SingleThreadModel

### 11.3 StandardWrapper

### 11.4 StandardWrapperFacade类

### 11.5 StandardWrapperValve类

### 11.6 FilterDef类

### 11.7 ApplicationFilterConfig类

### 11.8 ApplicationFilterChain类



## 12 StandardContext类

Context实例表示一个具体的Web应用程序，其中包含一个或多个Wrapper实例，每个Wrapper表示一个具体的servlet定义。

### 12.1 StandardContext的配置

### 12.2 StandardContextMapper类

### 12.3 对重载的支持

### 12.4 backgroundProcess方法







