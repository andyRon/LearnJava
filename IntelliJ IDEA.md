## Intellij IDEA 学习

[IntelliJ-IDEA-Tutorial](https://github.com/judasn/IntelliJ-IDEA-Tutorial)



### 介绍

**IntelliJ IDEA 是目前所有 IDE 中最具备沉浸式的 JVM IDE，没有之一**。



### 安装

如果你是开发 Java Web 项目最好的方案是 8G 内存或是以上，硬盘能再用上固态是最好的，因为 IntelliJ IDEA 有大量的缓存、索引文件，把 IntelliJ IDEA 的缓存、索引文件放在固态上，IntelliJ IDEA 流畅度也会加快很多。



#### 设置目录

`~/Library/Preferences/IntelliJIdea2019.3/`

`~/Library/Preferences/IntelliJIdea2019.3/idea.vmoptions`  对应  **`Help -> Edit Custom VM Options`** 

删除掉整个目录之后，重新启动 IntelliJ IDEA 会再自动帮你再生成一个全新的默认配置，所以很多时候如果你把 IntelliJ IDEA 配置改坏了，没关系，删掉该目录，一切都会还原到默认



``~/Library/Caches/IntelliJIdeaXXXXXX`，用于保存缓存、日志、以及本地的版本控制信息`



### IntelliJ IDEA 缓存和索引介绍和清理方法

IntelliJ IDEA 的缓存和索引主要是用来加快文件查询，从而加快各种查找、代码提示等操作的速度。

#### 清除缓存和索引



### IntelliJ IDEA 编译方式介绍





### IntelliJ IDEA 项目相关的几个重要概念介绍

IntelliJ IDEA 没有类似 Eclipse 工作空间（workspace）的概念的。

一个 Project 打开一个 Window 窗口。



`command + ;`    **Project Structure**



#### language level 介绍



### Mac 必备快捷键对照表





### 获得激活



1. 先下载一个`jetbrains-agent.jar`，然后放到`/Users/andyron/Library/Preferences/IntelliJIdea2019.3/`目录下。
2. 在`~/Library/Preferences/IntelliJIdea2019.3/idea.vmoptions`文件中添加一行：

```
-javaagent:/Users/andyron/Library/Preferences/IntelliJIdea2019.3/jetbrains-agent.jar
```

3. 重启 IDEA