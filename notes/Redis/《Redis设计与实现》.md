《Redis设计与实现》笔记

-------------

🏷️微信读书



### 前言

“共同关注”功能，集合交集



www.RedisBook.com







## 一、数据结构与对象

### 2 简单动态字符串



#### 2.1 SDS的定义

```c
//sds.h/sdshdr

struct sdshdr {￼
  //记录buf数组中已使用字节的数量￼
  //等于SDS所保存字符串的长度￼
  int len;
  
  //记录buf数组中未使用字节的数量￼
  int free;￼
  
  //字节数组，用于保存字符串￼
  char buf[];￼
};

```



#### 2.2 SDS与C字符串的区别

##### 数复杂度获取字符串长度



##### 杜绝缓冲区溢出



##### 减少修改字符串时带来的内存重分配次数



##### 二进制安全



##### 兼容部分C字符串函数



### 2.3 SDS API

![](/Users/andyron/Library/Application Support/typora-user-images/image-20200620084115140.png)



### 3 链表



#### 3.1 链表和链表节点的实现



#### 3.2 链表和链表节点的API

![](/Users/andyron/Library/Application Support/typora-user-images/image-20200620084311739.png)



### 4 字典

字典，又称为**符号表（symbol table）**、**关联数组（associative array）**或**映射（map）**，是一种用于保存键值对（key-value pair）的抽象数据结构。



#### 4.1 字典的实现



#### 4.2 哈希算法



#### 4.3 解决键冲突



#### 4.4 rehash



#### 4.5 渐进式rehash



#### 4.6 字典API

![](/Users/andyron/Library/Application Support/typora-user-images/image-20200620084602610.png)

### 5 跳跃表（skiplist）



#### 5.1 跳跃表的实现



#### 5.2 跳跃表API

![](/Users/andyron/Library/Application Support/typora-user-images/image-20200620084738743.png)



### 6 整数集合（intset）



#### 6.1 整数集合的实现



#### 6.2 升级



#### 6.3 升级的好处

##### 提升灵活性



##### 节约内存



#### 6.4 降级





#### 6.5 整数集合API

![](/Users/andyron/Library/Application Support/typora-user-images/image-20200620085630144.png)



### 7 压缩列表（ziplist）

#### 7.1 压缩列表的构成



#### 7.2 压缩列表节点的构成

##### previous_entry_length



##### encoding



##### content



#### 7.3 连锁更新



#### 7.4 压缩列表API

![](/Users/andyron/Library/Application Support/typora-user-images/image-20200620085935988.png)



### 8 对象



#### 8.1 对象的类型与编码



#### 8.2 字符串对象

![](/Users/andyron/Library/Application Support/typora-user-images/image-20200620090233535.png)



#### 8.3 列表对象

![](/Users/andyron/Library/Application Support/typora-user-images/image-20200620090259643.png)



#### 8.4 哈希对象

![](/Users/andyron/Library/Application Support/typora-user-images/image-20200620090203929.png)



#### 8.5 集合对象

![](/Users/andyron/Library/Application Support/typora-user-images/image-20200620090357046.png)

#### 8.6 有序集合对象

![](/Users/andyron/Library/Application Support/typora-user-images/image-20200620090435727.png)

#### 8.7 类型检查与命令多态



#### 8.8 内存回收



#### 8.9 对象共享



#### 8.10 对象的空转时长



## 二、单机数据库的实现

### 9 数据库



#### 9.1 服务器中的数据库



#### 9.2 切换数据库



#### 9.3 数据库键空间



#### 9.4 设置键的生存时间或过期时间



#### 9.5 过期键删除策略



#### 9.6 Redis的过期键删除策略



#### 9.7 AOF、RDB和复制功能对过期键的处理



#### 9.8 数据库通知



### 10 RDB持久化



#### 10.1 RDB文件的创建与载入



#### 10.2 自动间隔性保存



#### 10.3 RDB文件结构



#### 10.4 分析RDB文件



### 11 AOF持久化



#### 11.1 AOF持久化的实现



#### 11.2 AOF文件的载入与数据还原



#### 11.3 AOF重写



### 12 事件

#### 12.1 文件事件



#### 12.2 时间事件



#### 12.3 事件的调度与执行



### 13 客户端

#### 13.1 客户端属性



#### 13.2 客户端的创建与关闭



### 14 服务器

#### 14.1 命令请求的执行过程



#### 14.2 serverCron函数



#### 14.3 初始化服务器



## 三、多机数据库的实现

### 15 复制



### 16 Sentinel

**Sentinel（哨岗、哨兵）**是Redis的高可用性（high availability）解决方案：由一个或多个Sentinel实例（instance）组成的Sentinel系统（system）可以监视任意多个主服务器，以及这些主服务器属下的所有从服务器，并在被监视的主服务器进入下线状态时，自动将下线主服务器属下的某个从服务器升级为新的主服务器，然后由新的主服务器代替已下线的主服务器继续处理命令请求。

![](/Users/andyron/Library/Application Support/typora-user-images/image-20200620091403984.png)



#### 16.1 启动并初始化Sentinel



#### 16.2 获取主服务器信息



#### 16.3 获取从服务器信息



#### 16.4 向主服务器和从服务器发送信息



#### 16.5 接收来自主服务器和从服务器的频道信息



#### 16.6 检测主观下线状态



#### 16.7 检查客观下线状态



#### 16.8 选举领头Sentinel



#### 16.9 故障转移



### 17 集群



## 四、独立功能部分



### 18 发布与订阅

#### 18.1 频道的订阅与退订



#### 18.2 模式的订阅与退订



#### 18.3 发送消息



#### 18.4 查看订阅信息



### 19 事务

#### 19.1 事务的实现



#### 19.2 WATCH命令的实现



#### 19.3 事务的ACID性质



### 20 Lua脚本

#### 20.1 创建并修改Lua环境



#### 20.2 Lua环境协作组件



#### 20.3 EVAL命令的实现



#### 20.4 EVALSHA命令的实现



#### 20.5 脚本管理命令的实现



#### 20.6 脚本复制



### 21 排序

#### 21.1 SORT＜key＞命令的实现



#### 21.2 ALPHA选项的实现



#### 21.3 ASC选项和DESC选项的实现



#### 21.4 BY选项的实现



#### 21.5 带有ALPHA选项的BY选项的实现



#### 21.6 LIMIT选项的实现



#### 21.7 GET选项的实现



#### 21.8 STORE选项的实现



#### 21.9 多个选项的执行顺序



### 22 二进制位数组



### 23 慢查询日志



#### 23.1 慢查询记录的保存



#### 23.2 慢查询日志的阅览和删除



#### 23.3 添加新日志



### 24 监视器

