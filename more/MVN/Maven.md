## Maven 教程

[Maven 教程](https://www.runoob.com/maven/maven-tutorial.html)



### 介绍

Maven是Apache下的一个纯 Java开发的、开源的项目管理工具，可以对 Java 项目进行构建、依赖管理。基于项目对象模型（缩写：**POM**）概念，利用一个中央信息片断能管理一个项目的构建、报告和文档等步骤。



#### Maven功能

构建、文档生成、报告、依赖、SCMs、发布、分发、邮件列表

#### 约定配置

| 目录                               | 目的                                                         |
| :--------------------------------- | :----------------------------------------------------------- |
| ${basedir}                         | 存放pom.xml和所有的子目录                                    |
| ${basedir}/src/main/java           | 项目的java源代码                                             |
| ${basedir}/src/main/resources      | 项目的资源，比如说property文件，springmvc.xml                |
| ${basedir}/src/test/java           | 项目的测试类，比如说Junit代码                                |
| ${basedir}/src/test/resources      | 测试用的资源                                                 |
| ${basedir}/src/main/webapp/WEB-INF | web应用文件目录，web项目的信息，比如存放web.xml、本地图片、jsp视图页面 |
| ${basedir}/target                  | 打包输出目录                                                 |
| ${basedir}/target/classes          | 编译输出目录                                                 |
| ${basedir}/target/test-classes     | 测试编译输出目录                                             |
| Test.java                          | Maven只会自动运行符合该命名规则的测试类                      |
| ~/.m2/repository                   | Maven默认的本地仓库目录位置                                  |

#### Maven特点



### 环境配置

JDK

下载、解压对应目录即可

设置Maven环境变量`MAVEN_HOME`

`mvn -v`

### POM

POM( Project Object Model，项目对象模型 ) 是 Maven 工程的基本工作单元，是一个XML文件，包含了项目的基本信息，用于描述项目如何构建，声明项目依赖，等等。

所有 POM 文件都需要 project 元素和三个必需字段：groupId，artifactId，version。

```xml
<project xmlns = "http://maven.apache.org/POM/4.0.0"
    xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation = "http://maven.apache.org/POM/4.0.0
    http://maven.apache.org/xsd/maven-4.0.0.xsd">
 
    <!-- 模型版本 -->
    <modelVersion>4.0.0</modelVersion>
    <!-- 公司或者组织的唯一标志，并且配置时生成的路径也是由此生成， 如com.companyname.project-group，maven会将该项目打成的jar包放本地路径：/com/companyname/project-group -->
    <groupId>com.companyname.project-group</groupId>
 
    <!-- 项目的唯一ID，一个groupId下面可能多个项目，就是靠artifactId来区分的 -->
    <artifactId>project</artifactId>
 
    <!-- 版本号 -->
    <version>1.0</version>
</project>
```



#### 父POM

父（Super）POM是 Maven 默认的 POM。所有的 POM 都继承自一个父 POM（无论是否显式定义了这个父 POM）。父 POM 包含了一些可以被继承的默认设置。

#### POM标签详解



### 构建生命周期

![](https://www.runoob.com/wp-content/uploads/2018/09/7642256-c967b2c1faeba9ce.png)

| 阶段          | 处理     | 描述                                                     |
| :------------ | :------- | :------------------------------------------------------- |
| 验证 validate | 验证项目 | 验证项目是否正确且所有必须信息是可用的                   |
| 编译 compile  | 执行编译 | 源代码编译在此阶段完成                                   |
| 测试 Test     | 测试     | 使用适当的单元测试框架（例如JUnit）运行测试。            |
| 包装 package  | 打包     | 创建JAR/WAR包如在 pom.xml 中定义提及的包                 |
| 检查 verify   | 检查     | 对集成测试的结果进行检查，以保证质量达标                 |
| 安装 install  | 安装     | 安装打包的项目到本地仓库，以供其他项目使用               |
| 部署 deploy   | 部署     | 拷贝最终的工程包到远程仓库中，以共享给其他开发人员和工程 |



Maven 有以下三个标准的生命周期：

- **clean**：项目清理的处理
- **default(或 build)**：项目部署的处理
- **site**：项目站点文档创建的处理



#### Clean 生命周期

`mvn post-clean`







#### Default (Build) 生命周期





#### Site 生命周期



`mvn stie`





### 构建配置文件

构建配置文件是一系列的配置项的值，可以用来设置或者覆盖 Maven 构建默认值。



#### 构建配置文件的类型

| 类型                  | 在哪定义                                                     |
| :-------------------- | :----------------------------------------------------------- |
| 项目级（Per Project） | 定义在项目的POM文件pom.xml中                                 |
| 用户级 （Per User）   | 定义在Maven的设置xml文件中 (%USER_HOME%/.m2/settings.xml)    |
| 全局（Global）        | 定义在 Maven 全局的设置 xml 文件中 (%M2_HOME%/conf/settings.xml) |





#### 配置文件激活



### Maven仓库

在 Maven 中，任何一个依赖、插件或者项目构建的输出，都可以称之为**构件**。

Maven 仓库能帮助我们管理构件（主要是JAR），它就是放置所有JAR文件（WAR，ZIP，POM等等）的地方。

**本地（local）**、**中央（central）**、**远程（remote）**

#### 本地仓库

默认在` .m2/respository/ `

#### 中央仓库

Maven 社区提供的仓库

http://search.maven.org/#browse

#### 远程仓库

如果 Maven 在中央仓库中也找不到依赖的文件，它会停止构建过程并输出错误信息到控制台。为避免这种情况，Maven 提供了远程仓库的概念，它是开发人员自己定制仓库，包含了所需要的代码库或者其他工程中用到的 jar 文件。



### 插件

Maven 生命周期的每一个阶段的具体实现都是由 Maven 插件实现的。



### 构建 Java 项目



### 构建 & 项目测试



### 引入外部依赖



### 项目模板

archetype 也就是原型，是一个 Maven 插件，准确说是一个项目模板，它的任务是根据模板创建一个项目结构。



### 项目文档



### 快照(SNAPSHOT)



### 自动化构建



### 依赖管理



### 自动化部署



### Web 应用



### Maven IntelliJ