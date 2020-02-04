# Eclipse 教程

[Eclipse 教程](https://www.runoob.com/eclipse/eclipse-tutorial.html)

Eclipse 附带了一个标准的插件集，包括Java开发工具（Java Development Kit，JDK）。

Eclipse 是基于 Java 的可扩展开发平台，所以安装 Eclipse 前你需要确保你的电脑已安装 JDK。



> `*` 表示需要回看





### Eclipse 修改字符集

**Window -> Preferences -> General -> Workspace -> Text file encoding**

UTF-8

### 窗口说明

工作台(Workbench)

### 菜单

通过 Eclipse 插件可以添加新的菜单和菜单项。



### Eclipse 视图



### Eclipse 透视图(Perspective)

透视图是一个包含一系列视图和内容编辑器的可视容器。

#### 操作透视图

通过"Window"菜单并选择"Open Perspective > Other"来打开透视图对话框。



### Eclipse 工作空间(Workspace)

工作空间包含：项目，文件，文件夹。

#### 管理工作空间(Workspace)

#### 工作空间（workspace）设置

"Window" => "preferences..." => "General"=>"Workspace"

#### 切换工作空间(workspace)

 "File" => "switch workspace"



### 创建 Java 项目

新建Java项目的三个方式：

-  File  >  New > Java Project

- 在项目浏览(Project Explorer)窗口中鼠标右击任一地方选择 New > Java Project
- 在工具条上点击新建按钮 (![new_button](https://www.runoob.com/wp-content/uploads/2014/12/new_button.jpg) ) 并选择 Java Project



### 创建 Java 包

![new_java_package_pe](https://www.runoob.com/wp-content/uploads/2014/12/new_java_package_pe.jpg)

### 创建 Java 类

### 创建 Java 接口



###  Java 构建路径(Java Build Path)

Java构建路径用于在编译Java项目时找到依赖的类，包括以下几项：

- 源码包
- 项目相关的 jar 包及类文件
- 项目引用的的类库



鼠标右击指定的 Java 项目并选择 Properties(属性) 菜单项来调用

![java_build_path](https://www.runoob.com/wp-content/uploads/2014/12/java_build_path.jpg)



### 运行配置(Run Configuration)*



### 生成jar包



File > Export



### 关闭项目

Eclipse 工作空间包含了多个项目。一个项目可以是关闭或开启状态。

如果项目不处于开发阶段，我们就可以先关闭项目。



### 编译项目



### Debug



### 首选项(Preferences)



### 内容辅助(Content Assist)

window->Preferences->Java->Editor->Content Assist



### 快速修复

Java 编辑器中使用 Java 语法来检测代码中的错误。当它发现错误或警告时：

- 使用红色波浪线突出错误
- 使用黄色的波浪线突出警告
- 在 Problem 视图中显示错误和警告
- 在垂直标尺上显示黄色小灯泡及警告和错误标识



### 悬浮提示

所有java编辑器中相关的悬浮提示可以通过 preference(首选项) 的 Hovers 页面来配置（搜索框中输入 "hover"）。



### 查找

 Ctrl + H



### 浏览(Navigate)菜单



### 重构菜单

右击Java类等选择Refactor



### 添加书签



### 任务管理*



### 安装插件



### 代码模板

快捷键(默认为Alt+/)

#### 自定义代码模板

Windows->Preferences->Java->Editor->Templates



### 快捷键