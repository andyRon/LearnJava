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

`bin/`

`/conf`

`log/`

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