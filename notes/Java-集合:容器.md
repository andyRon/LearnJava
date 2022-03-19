Java容器/集合
------

Java容器/集合有两大接口：

一个是 `Collecton`接口，主要用于存放单一元素，另有三个主要的子接口：`List`、`Set` 和 `Queue`；

另一个是 `Map` 接口，主要用于存放键值对。

<img src="https://snailclimb.gitee.io/javaguide/docs/java/collection/images/java-collection-hierarchy.png" alt="img" style="zoom: 25%;" />

> 上图只列举了主要的继承派生关系，省略了`AbstractList`, `NavigableSet`等抽象类以及其他的一些辅助类。



<img src="https://uploadfiles.nowcoder.com/images/20170608/7010483_1496884090913_9553A95A1845661EB80E51191737B9FF" alt="img" style="zoom:50%;" />





| Map集合类     | key                | value        |
| ------------- | ------------------ | ------------ |
| HashMap       | 可为null，不重复   | 允许为null   |
| TreeMap       | 不可为null，不重复 | 允许为null   |
| ConcurrentMap | 不可为null         | 不允许为null |
| HashTable     | 不可为null，不重复 | 不允许为null |



### 线程安全

如果你的代码所在的进程中有多个线程在同时运行，而这些线程可能会同时运行这段代码。如果每次运行结果和单线程运行的结果是一样的，而且其他的变量的值也和预期的是一样的，就是**线程安全**的。线程安全问题都是由**全局变量**及**静态变量**引起的。

若每个线程中对全局变量、静态变量只有读操作，而无写操作，一般来说，这个全局变量是线程安全的；若有多个线程同时执行写操作，一般都需要考虑**<font color=#FF8C00>线程同步</font>**，否则的话就可能影响线程安全。



LinkedList 和 ArrayList 都是不同步的，线程不安全； 

Vector 和 Stack 都是同步的，线程安全； 

Set是线程不安全的； 

Hashtable的方法是同步的，线程安全； 

HashMap的方法不是同步的，线程不安全；



StringBuffer 是线程安全，而 StringBuilder 是线程不安全的



### HashMap HashTable

- 继承不同

  ```java
  public class HashMap<K,V> extends AbstractMap<K,V>
      implements Map<K,V>, Cloneable, Serializable
  
  public class Hashtable<K,V>
      extends Dictionary<K,V>
      implements Map<K,V>, Cloneable, java.io.Serializable
  ```

- Hashtable中的方法同步线程安全，HashMap需要自己同步线程不安全

- Hashtable中，key和value都不允许出现null值。 

  在HashMap中，null可以作为键，这样的键只有一个；可以有一个或多个键所对应的值为null。当get()方法返回null值时，即可以表示  HashMap中没有该键，也可以表示该键所对应的值为null。因此，在HashMap中不能由get()方法来判断HashMap中是否存在某个键，  而应该用containsKey()方法来判断。

- 遍历方式：Hashtable、HashMap都使用了 Iterator。而由于历史原因，Hashtable还使用了Enumeration的方式 。

- 哈希值的使用不同，HashTable直接使用对象的hashCode。而HashMap重新计算hash值。

- 初始大小和扩容的方式都不同





### 更多

collection类型的集合（ArrayList,LinkedList）只能装入对象类型的数据，该题中装入了0，是一个基本类型，但是JDK5以后提供了自动装箱与自动拆箱，所以int类型自动装箱变为了Integer类型。



https://www.nowcoder.com/questionTerminal/b41c88359c8a4d69a35c2f37400e49f0



https://snailclimb.gitee.io/javaguide/#/docs/java/collection/Java%E9%9B%86%E5%90%88%E6%A1%86%E6%9E%B6%E5%B8%B8%E8%A7%81%E9%9D%A2%E8%AF%95%E9%A2%98

https://snailclimb.gitee.io/javaguide/#/docs/java/collection/Java%E9%9B%86%E5%90%88%E4%BD%BF%E7%94%A8%E6%B3%A8%E6%84%8F%E4%BA%8B%E9%A1%B9%E6%80%BB%E7%BB%93