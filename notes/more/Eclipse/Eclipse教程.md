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

[Mac下eclipse的快捷键](https://www.cnblogs.com/zjdeblog/p/7601663.html)

#### 一、Command类

| Command+1        | 快速修复                                                     |
| ---------------- | ------------------------------------------------------------ |
| Command+d        | 删除当前行                                                   |
| Command+Option+↓ | 复制当前行到下一行                                           |
| Command+Option+↑ | 复制当前行到上一行                                           |
| Command+←        | 移动光标至当前行的行首                                       |
| Command+→        | 移动光标至当前行的行尾                                       |
| Command+t        | 快速显示当前类的结构                                         |
| Command+w        | 关闭当前编辑页                                               |
| Command+Option+← | 前一个编辑的页面                                             |
| Command+Option+→ | 后一个编辑的页面                                             |
| Command+k        | 参考当前编辑页选中的关键字向下搜索                           |
| Command+e        | 显示当前编辑页面列表可选择页面进行切换                       |
| Command+/        | 注释或反注释当前行                                           |
| Command+Shift+e  | 显示Editor管理器，可选择切换editor                           |
| Command+j        | 正向查找，在当前编辑页中查找录入的字符，注意Eclipse状态栏的提示 |
| Command+Shift+J  | 反向查找，使用方式与正向查找类似                             |
| Command+Shift+W  | 关闭所有打开的Editor                                         |
| Command+Shift+P  | 定位匹配符，适用于代码规模比较大的场景，如在while(){}循环体的末尾}处，想要跳转到while(){处。 |
| Command+[        | 向后导航到上一个编辑的文件                                   |
| Command+]        | 向前导航到下一个编辑的文件                                   |

#### 二、Option类

| Option+↓         | 向下移动当前行         |
| ---------------- | ---------------------- |
| Option+↑         | 向上移动当前行         |
| Option+回车      | 显示当前选择资源的属性 |
| Option+/         | 代码助手“智能提示”     |
| Option+Command+R | 重命名                 |
| Option+Command+C | 修改函数结构，适用重构 |
| Option+Command+L | 抽取本地变量           |

#### 三、Control类

| Control+M | 最大化或还原当前editor或view |
| --------- | ---------------------------- |
|           |                              |

 

#### 四、Shift类

| Shift+Command+↑ | 选中光标至全部文本的开头 |
| --------------- | ------------------------ |
| Shift+Command+↓ | 选中光标至全部文本的结尾 |
| Shift+Command+→ | 选中光标至当前行的结尾   |
| Shift+Command+← | 选中光标至当前行的开头   |

 

#### 五、补充说明

Eclipse对于文本编辑跳转和选中跳转这块基本和Mac系统一致是通用的，以下内容是Mac系统对文本选中或中或跳转这块的支持。

1.文本位置跳转快捷键：

| 跳转到一行的开头：                     | Command+左箭头 |
| -------------------------------------- | -------------- |
| 跳转到一行的末尾：                     | Command+右箭头 |
| 跳转到当前单词的开头(适合英文、拼音)： | Option+左箭头  |
| 跳转到当前单词的末尾(适合英文、拼音)： | Option+右箭头  |
| 跳转到全部文本的开头：                 | Command+上箭头 |
| 跳转到全部文本的末尾：                 | Command+下箭头 |

2.文本选中快捷键

在以上快捷键中加入Shift，则可以扩展成为选中文本效果的快捷键

 

| 选中光标到本行开头的文本：                 | Shift+Command+左箭头 |
| ------------------------------------------ | -------------------- |
| 选中光标到本行末尾的文本：                 | Shift+Command+右箭头 |
| 选中光标到当前单词的开头(适合英文、拼音)： | Shift+Option+左箭头  |
| 选中光标到当前单词的末尾(适合英文、拼音)： | Shift+Option+右箭头  |
| 选中光标到全部文本的开头：                 | Shift+Command+上箭头 |
| 选中光标到全部文本的末尾：                 | Shift+Command+下箭头 |

 

