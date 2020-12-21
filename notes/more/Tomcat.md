[Tomcat 8 权威指南](https://www.w3cschool.cn/tomcat/)

## Tomcat 8 权威指南



### Mac 下载安装Tomcat









### 修改默认端口







-------------



### 简介

实际上Tomcat是Apache 服务器的扩展，但运行时它是独立运行的，所以当你运行tomcat 时，它实际上作为一个与Apache 独立的进程单独运行的。

当配置正确时，Apache 为HTML页面服务，而Tomcat 实际上运行JSP 页面和Servlet。

Tomcat本身是JAVA程序

#### 目录与文件

 `$CATALINA_HOME`

` $CATALINA_BASE`

`bin/`     可执行程序

`conf/`   Tomcat的配置文件，其中server.xml为服务器的主配置文件；web.xml为所有Web应用的配置文件；tomcat-users.xml用于定义Tomcat配置用户的权限与安全。

`lib/`   存放Tomcat服务器和所有web应用都能访问的**JAR文件**。

`logs/` 

`temp`   临时数据

`work/`   Tomcat工作是的目录，Tomcat解析JSP生成的Servlet文件放在这个目录中，session钝化的目录。

`webapps/`   存放Web应用相关文件

`webapps/ROOT`   默认的服务器根目录

### 安装

安装详细信息可查看官方 [RUNNING.txt](http://tomcat.apache.org/tomcat-8.0-doc/RUNNING.txt)。

#### Mac下安装

下载解压即可，详细见[Mac下安装Tomcat及配置](https://www.jianshu.com/p/87121d912d10)

启动Tomcat，`bin/startup.sh`

关闭，`bin/shutdown.sh`

`~/java/Tomcat/apache-tomcat-9.0.30/bin`

端口配置相关信息在 `conf/server.xml`中

默认访问地址 http://localhost:8080/



### 应用部署

在Tomcat服务器上，部署Web应用有两种方式：

1. 静态部署
2. 动态部署。  **Tomcat Manager**



#### 关于上下文

**上下文(Context)**

**上下文描述符文件**（ Context Descriptor）其实就是一个 XML 文件，含有 Tomcat 与上下文相关的配置信息，例如命名资源或会话管理器配置信息。





### Tomcat Manager



### Realm配置

**Realm**（安全域）其实就是一个存储用户名和密码的“数据库”再加上一个枚举列表。“数据库”中的用户名和密码是用来验证 Web 应用（或 Web 应用集合）用户合法性的，而每一合法用户所对应的**角色**存储在枚举列表中。



### Tomcat安全管理



### JNDI资源

[JNDI](https://baike.baidu.com/item/JNDI/3792442?fr=aladdin)（Java Naming and Directory Interface，**Java命名和目录接口**）是SUN公司提供的一种标准的Java命名系统接口，JNDI提供统一的客户端API，通过不同的访问提供者接口JNDI服务供应接口(SPI)的实现，由管理者将JNDI API映射为特定的命名服务和目录系统，使得Java应用程序可以和这些命名服务和目录服务之间进行交互。

Tomcat 为每个在其上运行的 Web 应用都提供了一个 JNDI 的 `InitialContext` 实现实例



#### web.xml 配置

Web 应用的部署描述符文件（`/WEB-INF/web.xml`）



#### context.xml 配置



#### 全局配置





### JDBC 数据源



### 类加载机制



### JSPs



### SSL/TLS



### CGI



### 代理支持



### MBean 描述符

Tomcat 使用 JMX MBean 来实现自身的性能管理。

每个包里的 mbeans-descriptor.xml 是针对 Catalina 的 JMX MBean 描述。

为了避免出现 “ManagedBean is not found” 异常，你需要为自定义组件添加 MBean 描述。



### 默认 Servlet



### 集群



### Tomcat 负载均衡器



### 监控与管理

**JMX** 



### 日志机制



### 基于APR的原生库

Tomcat 可以使用 [Apache Portable Runtime](http://apr.apache.org/)（APR） 来增强可扩展性与性能，并能更好地与原生服务器技术相集成。APR 是一种具有高度可移植性的类库，是 Apache HTTP Server 2.x 的核心。APR 具有许多用途，包括访问高级 IO 功能（比如 sendfile、epoll 和 OpenSSL）、系统级功能（随机数生成、系统状态，等等）以及原生进程处理（共享内存、NT 管道、UNIX 套接字）。

这些特性能让 Tomcat 成为一种通用的 Web 服务器，更使其更好地与原生的 Web 技术相集成。从整体上来说，这使得 Java 越来越有望成为一个成熟的 Web 服务器平台，而不单纯是一种仅仅着重研究后端的技术。



### 虚拟主机



### 高级 IO 机制



### 附加组件



### 如何在 Maven 中使用 Tomcat 库



### Tomcat安全性注意事项



## Tomcat

https://www.bilibili.com/video/av91909529?p=99

### 1 Java Web概念

JavaWeb是指所有通过Java语言编写可以通过浏览器访问的程序总称。

### 2 **Web** 资源的分类

静态资源: html、css、js、txt、mp4 视频 , jpg 图片 

动态资源: jsp 页面、Servlet 程序

### 3 常见的Web服务器

**Tomcat**:由 Apache 组织提供的一种 Web 服务器，提供对 jsp 和 Servlet 的支持。它是一种轻量级的 javaWeb 容器(服务

器)，也是当前应用最广的 JavaWeb 服务器(免费)。

**Jboss**:是一个遵从 JavaEE 规范的、开放源代码的、纯 Java 的 EJB 服务器，它支持所有的 JavaEE 规范(免费)。

**GlassFish**: 由 Oracle 公司开发的一款 JavaWeb 服务器，是一款强健的商业服务器，达到产品级质量(应用很少)。

**Resin**:是 CAUCHO 公司的产品，是一个非常流行的服务器，对 servlet 和 JSP 提供了良好的支持， 性能也比较优良，resin 自身采用 JAVA 语言开发(收费，应用比较多)。

**WebLogic**:是 Oracle 公司的产品，是目前（在收费中）应用最广泛的 Web 服务器，支持 JavaEE 规范， 而且不断的完善以适应新的开发要求，适合大型项目(收费，用的不多，适合大公司)。

### 4 Tomcat与Servlet版本关系

![](/Users/andyron/Library/Application Support/typora-user-images/image-20200309075514069.png)

Servlet 程序从 2.5 版本是现在世面使用最多的版本(**xml** 配置) 

到了 Servlet3.0 之后。就是**注解**版本的 Servlet 使用。





### 5 Tomcat的使用

#### 安装

解压就好

#### 目录介绍



#### 如何启动Tomcat服务器

- `./bin/starup.bat`  

- `./bin/catalina.sh run`    可以看到启动信息

常见的启动失败情况， JAVA_HOME

#### 如何关闭Tomcat



#### 如何修改Tomcat的端口号

`conf/server.xml`

```
<Connector port=""   >
```

#### 如何部署web工程到Tomcat中

`webapps`下每一个目录就是一个web工程。

1. 第一种： 直接复制到webapps目录下即可。
2. 第二种：在`conf/Catalina/localhost/`目录下配置xml文件

```xml
<!--
Context表示一个工程上下文
path表示工程在浏览器中的访问路径
docBase表示工程的具体目录在那里
-->

<Context path="/abc" docBase="/User/andyron/Download/abc" />
```



#### Root的工程的访问

就是默认访问`http://localhost:8080/`



### 6 IDEA整合Tomcat

![image-20200308233949531](/Users/andyron/Library/Application Support/typora-user-images/image-20200308233949531.png)



### 7 IDEA中动态web工程的操作

#### **a)IDEA** 中如何创建动态 **web** 工程

1、创建一个新模块:

![](/Users/andyron/Library/Application Support/typora-user-images/image-20200309080452154.png)

2、选择你要创建什么类型的模块:

![](/Users/andyron/Library/Application Support/typora-user-images/image-20200309080518958.png)

3、输入你的模块名，点击【Finish】完成创建。

4、创建成功如下图:

![](/Users/andyron/Library/Application Support/typora-user-images/image-20200309080824982.png)

#### **b)Web** 工程的目录介绍

![](/Users/andyron/Library/Application Support/typora-user-images/image-20200309081125402.png)



#### 给Tomcat添加第三方jar包

