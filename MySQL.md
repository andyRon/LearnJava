# 《MySQL必知必会》笔记


### 1.了解SQL

数据库是通过数据库软件**DBMS**(数据库管理系统)创建和操纵的容器。这个容器可以是文件，也可以不是。使用者通过DBMS访问数据库。

**表**是一种结构化的文件。表的特性定义了数据在表中如何存储（可以存储什么样的数据，数据如何分解，各部分信息如何命名）。

**主键（primary key）** ： 一列（或一组列），其值能够唯一区分表中的每一行。 

### 3.MySQL简介

DBMS分两类：基于共享文件系统（Microsoft Access、FileMaker）；基于客户机-服务器（mysql,oracle）。  

<u>服务器部分</u>是负责所有数据访问和处理的一个软件（运行在叫做**数据库服务器**的计算机上）；<u>客户机</u>是与用户打交道的软件，可以是mysql提供的工具（命令行，mysql administrator等），脚本语言（如perl），web应用开发（如ASP，php），程序设计语言（如C，C++，Java）等。

客户机和服务器可以在一台或两台计算机上。

#### MySQL工具

以`\G`结束，会使原本横向输出的变成纵向输出，易于观看。

```bash
mysql> select * from customers\G
*************************** 1. row ***************************
     cust_id: 10001
   cust_name: Coyote Inc.
cust_address: 200 Maple Lane
   cust_city: Detroit
  cust_state: MI
    cust_zip: 44444
cust_country: USA
cust_contact: Y Lee
  cust_email: ylee@coyote.com
*************************** 2. row ***************************
     cust_id: 10002
   cust_name: Mouse House
cust_address: 333 Fromage Lane
   cust_city: Columbus
  cust_state: OH
    cust_zip: 43333
cust_country: USA
cust_contact: Jerry Mouse
  cust_email: NULL
```

### 3.使用MySQL

`$mysql -u root -p` 	连接

`show database;`  		查看所有数据库

`use 数据库名;`  			选择（进入） 某个数据库

`show tables;`			查看当前数据库所有的表

`show engines\G;` 		查看目前数据库引擎状态。

`show table status from test [like 'y%']\G;`   
查看test数据库中所有表[以y开头的表]信息

`show columns from 表名;` 等价于`describe 表名;`
显示某个表的所有列的详细信息(表结构)

`show status;`    显示mysql服务器大部分状态

`show create table tablename;`和`show create database;` 
分别显示创建特定表和数据库的mysql语句

`show grants [for user]\G;`     显示授权用户的安全权限。 

`show errors;` 和`show warnings;`

 `help show;`

`create  database  数据库名;`  		新建数据库   

### 4.检索数据注意点

*   `select distinct vend_id,prod_price from products;` [DISTINCT 用来选择显示不同的行，两个列中的数据不同]
*   `limit  3` [限制少于三行]  
    `limit  0,3` [从0行开始的三行]
*   `select pro_name from products order by prod_name;` [order by 排序，order by子句中可以是非检索列]
*   `select prod_id, prod_price, prod_name from products order by prod_price, prod_name;` [多个列排序，仅在多个行具有相同的prod_price值时才对产品按prod_name进行排序]
*   `select prod_id, prod_price, prod_name from products order by prod_price DESC;` [指定排序方向]
*   order by 位于 where之后

### 过滤数据

*   `select prod_name from products where prod_price IS NULL;` 空值检查 
*   `select prod_name, prod_price from products where (vend_id = 1002 or vend_id = 1003) and prod_price >= 10;` [注意and计算顺序高， 前面要加括号]
*   `select prod_name, prod_price from products where vend_id in (1002,1003) order by prod_name;` 
*   `select prod_name, prod_price from products where prod_price between 5 and 10;` 在5到10之间 IN比OR快，计算次序易管理，in还可以包括其他select语句  
    MYSQL支持使用NOT对IN，BETWEEN和EXISTS子句取反。



### 通配符过滤

*   like语句一般都要用通配符，不然没有多大意义 
*   %表示任意字符出现任意多次(不能匹配NULL)
*   _只匹配单个字符
*   慎用通配符；尽量不要在模式的开始使用；
*







## 正则匹配

*   mysql正则仅为正则表达式的一个很小的子集，不区分大小写。
*   `select prod_name from products where prod_name regexp '1000' order by prod_name;`
*   匹配几个字符之一

<pre>mysql> select prod_name from products where prod_name REGEXP '[123] Ton';
+-------------+
| prod_name   |
+-------------+
| 1 ton anvil |
| 2 ton anvil |
+-------------+
</pre>

*   `REGEXP '[1-5] Ton'` 匹配范围
*   `regexp '1000|2000'` or匹配
*   `REGEXP '\\.'` 匹配含.等(\f 换页，\n 换行，\r 回车)特殊字符
*   匹配字符类（预先定义的常用的字符集合）

| 类         | 意义                                 |
| ---------- | ------------------------------------ |
| [:alnum:]  | 任意字母和数字([a-zA-Z0-9])          |
| [:alpha:]  | [a-zA-Z]                             |
| [:blank:]  | 空格和制表                           |
| [:cntrl:]  | ASCII控制字符(ASCII值为0-31和127)    |
| [:digit:]  | [0-9]                                |
| [:print:]  | 任意可打印字符                       |
| [:graph:]  | 除去空格的[:print:]                  |
| [:lower:]  | [a-z]                                |
| [:upper:]  | [A-Z]                                |
| [:punct:]  | 既不在[:alnum:]有不在[:cntrl:]的字符 |
| [:space:]  | 任意空格符                           |
| [:xdigit:] | 任意十六进制数字([a-fA-F0-9])        |

例如：

<pre>mysql> select prod_name from products where prod_name regexp '[:upper:]' ;
+--------------+
| prod_name    |
+--------------+
| Detonator    |
| Bird seed    |
| Carrots      |
| Fuses        |
| JetPack 1000 |
| JetPack 2000 |
| Safe         |
+--------------+
</pre>

*   匹配多个实例 
    *   *   *   \? {n} {n,} {n,m}

<pre>mysql> select prod_name from products where prod_name regexp '\\([0-9] sticks?\\
)';
+----------------+
| prod_name      |
+----------------+
| TNT (1 stick)  |
| TNT (5 sticks) |
+----------------+</pre>

<pre>mysql> select prod_name from products where prod_name regexp '[[:digit:]]{4}';
+--------------+
| prod_name    |
+--------------+
| JetPack 1000 |
| JetPack 2000 |
+--------------+</pre>

`regexp '^[0-9\\.]'` 匹配以数字或者.开头的。

## 创建计算字段

*   字段的计算（总数，平均数等），拼接等操作，DBMS能够快速有效地完成。
*   拼接字段

<pre>mysql> select Concat(RTrim(vend_name), '(', RTrim(vend_country), ')') as vend_ti
tle from vendors order by vend_name;
+------------------------+
| vend_title             |
+------------------------+
| ACME(USA)              |
| Anvils R Us(USA)       |
| Furball Inc.(USA)      |
| Jet Set(England)       |
| Jouets Et Ours(France) |
| LT Supplies(USA)       |
+------------------------+</pre>

Concat()和RTrim()函数，别名

*   算术计算

<pre>mysql> select prod_id, quantity, item_price,
    -> quantity*item_price as expanded_price from orderitems
    -> where order_num=20005;
+---------+----------+------------+----------------+
| prod_id | quantity | item_price | expanded_price |
+---------+----------+------------+----------------+
| ANV01   |       10 |       5.99 |          59.90 |
| ANV02   |        3 |       9.99 |          29.97 |
| TNT2    |        5 |      10.00 |          50.00 |
| FB      |        1 |      10.00 |          10.00 |
+---------+----------+------------+----------------+
</pre>

*   测试计算（没有from的select语句） `select 3*2;` `select now();`

## 使用数据处理函数

*   函数没有SQL的可移植性强
*   文本处理函数 left() right()  
    length()  
    locate()  
    lower() upper()  
    ltrim() rtrim()  
    sounder()  
    substring()

### 日期和时间处理函数

*   datatime数据类型是`2014-06-30 11:40:12`这样的 

<pre>mysql> select order_date from orders;
+---------------------+
| order_date          |
+---------------------+
| 2014-06-30 11:40:12 |
| 2005-09-01 00:00:00 |
| 2005-09-12 00:00:00 |
| 2005-09-30 00:00:00 |
| 2005-10-03 00:00:00 |
| 2005-10-08 00:00:00 |
+---------------------+</pre>

*   date()和time()分别是 

<pre>mysql> select date(order_date) from orders;
+------------------+
| date(order_date) |
+------------------+
| 2014-06-30       |
| 2005-09-01       |
| 2005-09-12       |
| 2005-09-30       |
| 2005-10-03       |
| 2005-10-08       |
+------------------+

mysql> select time(order_date) from orders;
+------------------+
| time(order_date) |
+------------------+
| 11:40:12         |
| 00:00:00         |
| 00:00:00         |
| 00:00:00         |
| 00:00:00         |
| 00:00:00         |
+------------------+
</pre>

*   `mysql> select * from orders where date(order_date) between '2005-09-01' and '2005-11-01';`
*   常用日期和时间处理函数 now() 返回当前日期和时间  
    curdate()  
    curtime() 以上三个函数不需要参数  
    date() 返回datatime数据类型（日期时间）的日期部分  
    time() 返回datatime数据类型（日期时间）的时间部分  
    day()  
    dayofweek()  
    hour()  
    minute()  
    month()  
    second()  
    year() 

### 数值处理函数

pi() 不需要参数  
rand() 不需要参数  
abs()  
cos() sin()  
exp() mod() sqrt()  
tan()

## 汇总数据

### 聚集函数

| 函数    | 说明                                           |
| ------- | ---------------------------------------------- |
| avg()   | 列名作为参数，忽略null行                       |
| count() | 列名或*作为参数，*作为参数时不忽略包含null的行 |
| max()   | 列名作为参数，忽略null行                       |
| min()   | 列名作为参数，忽略null行                       |
| sum()   | 列名作为参数，忽略null行                       |

所有聚集函数都可以用来执行多个列上的计算。

<pre>mysql> select sum(item_price*quantity) as total from orderitems;
+---------+
| total   |
+---------+
| 1368.34 |
+---------+
</pre>

### 聚集不同值 DISTINCT

<pre>mysql> select avg(distinct prod_price) as avg_price from products where vend_id=
1003;
+-----------+
| avg_price |
+-----------+
| 15.998000 |
+-----------+
</pre>

distinct 不能用于count(*),并且用于max()和min()没有多大意义。

### 组合聚集函数

<pre>mysql> select count(*) as num_items,
    -> min(prod_price) as price_min,
    -> max(prod_price) as price_max,
    -> avg(prod_price) as price_avg
    -> from products;
+-----------+-----------+-----------+-----------+
| num_items | price_min | price_max | price_avg |
+-----------+-----------+-----------+-----------+
|        14 |      2.50 |     55.00 | 16.133571 |
+-----------+-----------+-----------+-----------+
</pre>





## 分组数据

### group by

*   group by子句可以包含任意数目的列
*   null算分为一组

### 过滤分组 HAVING

*   having类似where，where过滤行，having过滤分组
*   having支持所有where操作符
*   where在数据分组前进行过滤，having在数据分组后进行过滤
*   group by以后的数据顺序是不值得依赖的，如需排序，要通过order by

<pre>mysql> select vend_id, count(*) as num_prods
    -> from products
    -> where prod_price >=10
    -> group by vend_id
    -> having count(*) >=2;
+---------+-----------+
| vend_id | num_prods |
+---------+-----------+
|    1003 |         4 |
|    1005 |         2 |
+---------+-----------+
</pre>

### select子句顺序

<pre>SELECT
FROM
WHERE
GROUP BY
HAVING
ORDER BY
LIMIT
</pre>

## 子查询

### 使用子查询进行过滤

<pre>mysql> select cust_name, cust_contact from customers
    -> where cust_id in(select cust_id from orders
    ->                  where order_num in(select order_num from orderitems
    ->                                  where prod_id='TNT2'));
+----------------+--------------+
| cust_name      | cust_contact |
+----------------+--------------+
| Coyote Inc.    | Y Lee        |
| Yosemite Place | Y Sam        |
+----------------+--------------+
2 rows in set (0.28 sec)
</pre>

*   子查询总是从内向外处理。
*   在where子句中使用子查询，应该保证select语句具有与where子句中相同数目的列。（如上述代码中第二行中的cust_id和第三行的order_num）

### 作为计算字段使用子查询

<pre>mysql> select cust_name,
    ->        (select count(*) from orders
    ->         where orders.cust_id = customers.cust_id)  as order_num
    ->         from customers order by cust_name;
+----------------+-----------+
| cust_name      | order_num |
+----------------+-----------+
| 1232           |         1 |
| Coyote Inc.    |         3 |
| Mouse House    |         0 |
| Wascals        |         1 |
| Yosemite Place |         1 |
+----------------+-----------+
5 rows in set (0.00 sec)
</pre>

*   该子查询对检索出来的每个客户执行一次。上述子查询执行了五次。
*   **相关子查询**(correlated subquery):涉及外部查询的子查询。(orders.cust_id = customers.cust_id) 有点来消除歧义。

## 联结表

*   SQL最强大的功能之一就是能在数据检索查询的执行中联结(join). 
*   相同数据出现多次决不是一件好事（关系数据库设计的基础）。关系表的设计就是要保证吧信息分解成多个表，一类数据一个表。各表通过某些常用的值（即关系）互相关联。
*   **外键**(foreign key) 某个表中的一列，它包含另一个表的主键值，定义了两个表之间的关系。 联结仅存在与查询的执行当中。
*   **可伸缩性**(scale) 能够适应不断增加的工作量而不失败。
*   关系数据库的可伸缩性远比非关系数据库要好。
*   **联结**仅存在与查询的执行当中，联结时利用SQL的select能执行的最重要的操作。

### 创建联结

<pre>mysql> select vend_name, prod_name, prod_price
    -> from vendors, products
    -> where vendors.vend_id=products.vend_id
    -> order by vend_name, prod_name;
+-------------+----------------+------------+
| vend_name   | prod_name      | prod_price |
+-------------+----------------+------------+
| ACME        | Bird seed      |      10.00 |
| ACME        | Carrots        |       2.50 |
| ACME        | Detonator      |      13.00 |
| ACME        | Safe           |      50.00 |
| ACME        | Sling          |       4.49 |
| ACME        | TNT (1 stick)  |       2.50 |
| ACME        | TNT (5 sticks) |      10.00 |
| Anvils R Us | .5 ton anvil   |       5.99 |
| Anvils R Us | 1 ton anvil    |       9.99 |
| Anvils R Us | 2 ton anvil    |      14.99 |
| Jet Set     | JetPack 1000   |      35.00 |
| Jet Set     | JetPack 2000   |      55.00 |
| LT Supplies | Fuses          |       3.42 |
| LT Supplies | Oil can        |       8.99 |
+-------------+----------------+------------+mysql> select vend_name, prod_name, prod_price
    -> from vendors, products
    -> where vendors.vend_id=products.vend_id
    -> order by vend_name, prod_name;
+-------------+----------------+------------+
| vend_name   | prod_name      | prod_price |
+-------------+----------------+------------+
| ACME        | Bird seed      |      10.00 |
| ACME        | Carrots        |       2.50 |
| ACME        | Detonator      |      13.00 |
| ACME        | Safe           |      50.00 |
| ACME        | Sling          |       4.49 |
| ACME        | TNT (1 stick)  |       2.50 |
| ACME        | TNT (5 sticks) |      10.00 |
| Anvils R Us | .5 ton anvil   |       5.99 |
| Anvils R Us | 1 ton anvil    |       9.99 |
| Anvils R Us | 2 ton anvil    |      14.99 |
| Jet Set     | JetPack 1000   |      35.00 |
| Jet Set     | JetPack 2000   |      55.00 |
| LT Supplies | Fuses          |       3.42 |
| LT Supplies | Oil can        |       8.99 |
+-------------+----------------+------------+
</pre>

*   当没有where子句时就会产生笛卡尔积。
*   **笛卡尔积**(cartesian product) 有没有联结条件的表关系返回的结果为笛卡尔积。检索出的行的数目将是第一个表的行数乘以第二表的行数。
*   上面的联结也叫**等值联结**(equijoin)或者**内部联结**，可用另外形式表示：(传递给ON的实际条件与where相同)

<pre>select vend_name, prod_name,prod_price 
from vendors inner join products 
on vendors.vend_id = products.vend_ id；</pre>

*   联结多个表

<pre>mysql> select cust_name, cust_contact
    -> from customers, orders, orderitems
    -> where customers.cust_id = orders.cust_id
    -> and orderitems.order_num = orders.order_num
    -> and prod_id = 'TNT2';
+----------------+--------------+
| cust_name      | cust_contact |
+----------------+--------------+
| Coyote Inc.    | Y Lee        |
| Yosemite Place | Y Sam        |
+----------------+--------------+
2 rows in set (0.00 sec)
</pre>

> 此处的执行时间约为0.00sec，对比子联结的0.28sec，此处明显多表联结性能更好。

<pre>mysql> select cust_name, cust_contact from customers
    -> where cust_id in(select cust_id from orders
    ->                  where order_num in(select order_num from orderitems
    ->                                  where prod_id='TNT2'));
+----------------+--------------+
| cust_name      | cust_contact |
+----------------+--------------+
| Coyote Inc.    | Y Lee        |
| Yosemite Place | Y Sam        |
+----------------+--------------+
2 rows in set (0.28 sec)
</pre>

> 性能可能会受操作类型、表中数据量、是否存在索引或键以及其他一些条件的影响。

## 高级联结

### 表别名

*   表别名能用于where子句，select列表，order by子句以及语句的其他部分。
*   与列别名不一样，表别名只在查询执行中使用，不返回到客户机。

### 自联结

自联结和同一张表中的子查询对比

<pre>mysql> SELECT prod_id, prod_name
    -> FROM products
    -> WHERE vend_id = (SELECT vend_id
    ->                  FROM products
    ->                  WHERE prod_id = 'DTNTR');
+---------+----------------+
| prod_id | prod_name      |
+---------+----------------+
| DTNTR   | Detonator      |
| FB      | Bird seed      |
| FC      | Carrots        |
| SAFE    | Safe           |
| SLING   | Sling          |
| TNT1    | TNT (1 stick)  |
| TNT2    | TNT (5 sticks) |
+---------+----------------+
7 rows in set (0.00 sec)

mysql> SELECT p1.prod_id, p1.prod_name
    -> FROM products AS p1, products AS p2
    -> WHERE p1.vend_id = p2.vend_id
    ->   AND p2.prod_id = 'DTNTR';
+---------+----------------+
| prod_id | prod_name      |
+---------+----------------+
| DTNTR   | Detonator      |
| FB      | Bird seed      |
| FC      | Carrots        |
| SAFE    | Safe           |
| SLING   | Sling          |
| TNT1    | TNT (1 stick)  |
| TNT2    | TNT (5 sticks) |
+---------+----------------+
7 rows in set (0.00 sec)
</pre>

### 自然联结

*   被联结的列：表联结时至少有一个列出现在不止一个表中
*   一般内部联结都是自然联结

<pre>select c.*, o.order_num, o.order_date,
       oi.prod_id, oi.quantity, oi.item_price
from customers as c, orders as o, orderitems as oi
where c.cust_id = o.cust_id
and oi.order_num = o.order_num
and prod_id = 'FB';
</pre>

### 外部联结

对比内部联结和外部联结

<pre>mysql> select customers.cust_name, orders.order_num 
from customers inner join orders 
on customers.cust_id=orders.cust_id;
+----------------+-----------+
| cust_name      | order_num |
+----------------+-----------+
| Coyote Inc.    |     20004 |
| Coyote Inc.    |     20005 |
| Coyote Inc.    |     20009 |
| Wascals        |     20006 |
| Yosemite Place |     20007 |
| 1232           |     20008 |
+----------------+-----------+

mysql> select customers.cust_name, orders.order_num 
from customers left outer join orders 
on customers.cust_id = orders.cust_id;
+----------------+-----------+
| cust_name      | order_num |
+----------------+-----------+
| Coyote Inc.    |     20004 |
| Coyote Inc.    |     20005 |
| Coyote Inc.    |     20009 |
| Mouse House    |      NULL |
| Wascals        |     20006 |
| Yosemite Place |     20007 |
| 1232           |     20008 |
+----------------+-----------+
</pre>

外部联结包括了没有订单的客户Mouse House。 - `LEFT OUTER JOIN` 和 `RIGHT OUTER JOIN`

### 使用带聚集函数的联结

<pre>mysql> select customers.cust_name,  
customers.cust_id, 
count(orders.order_num) as num_ord 
from customers left outer join orders 
on customers.cust_id = orders.cust_id 
group by customers.cust_id;
+----------------+---------+---------+
| cust_name      | cust_id | num_ord |
+----------------+---------+---------+
| Coyote Inc.    |   10001 |       3 |
| Mouse House    |   10002 |       0 |
| Wascals        |   10003 |       1 |
| Yosemite Place |   10004 |       1 |
| 1232           |   10005 |       1 |
+----------------+---------+---------+
5 rows in set (0.00 sec)
</pre>

## 组合查询

> **并**(union)或**组合查询**(compund query)：执行多个查询（多条select语句），并将结果作为单个查询结果集返回。

<pre>mysql> select vend_id, prod_id, prod_price  
from products where prod_price &lt;=5
nion 
select vend_id, prod_id, prod_price 
from products where vend_id in(1001,1002) 
order by vend_id, prod_price;
+---------+---------+------------+
| vend_id | prod_id | prod_price |
+---------+---------+------------+
|    1001 | ANV01   |       5.99 |
|    1001 | ANV02   |       9.99 |
|    1001 | ANV03   |      14.99 |
|    1002 | FU1     |       3.42 |
|    1002 | OL1     |       8.99 |
|    1003 | TNT1    |       2.50 |
|    1003 | FC      |       2.50 |
|    1003 | SLING   |       4.49 |
+---------+---------+------------+
8 rows in set (0.03 sec)
</pre>

*   组合查询和单个表中的多个where条件，工作是相同，但性能可能有差异。
*   UNION中的每个查询必须包含相同的列、表达式或聚集函数（不过各个列不需要以相同的次序列出）
*   UNION自动去除重复的行，UNOIN ALL则不。
*   组合查询中只能有一个order by子句对组合结果排序，不能对单个查询结果排序。
*   组合查询可以组合不同的表







## 全文搜索

*   MYISAM支持全文搜索，InnoDB不支持。
*   全文本搜索时，MySQL不需要分别查看每个行，不需要分别分析和处理每个词。

### 使用全文本搜索

*   启用全文本搜索支持 `FULLTEXT`
*   执行全文本搜索 select 与Match() ,Against()

<pre>Create Table: CREATE TABLE `productnotes` (
  `note_id` int(11) NOT NULL AUTO_INCREMENT,
  `prod_id` char(10) NOT NULL,
  `note_date` datetime NOT NULL,
  `note_text` text,
  PRIMARY KEY (`note_id`),
  FULLTEXT KEY `note_text` (`note_text`)
) ENGINE=MyISAM AUTO_INCREMENT=115 DEFAULT CHARSET=latin1
</pre>

*   在定义全文本搜索后，mysql自动维护该索引，在增加，更新或删除行时，索引随之自动更新。
*   不要在导入数据是使用FULLTEXT 

<pre>select note_text 
from productnotes 
where Match(note_text) Against('rabbit');

+-----------------------------------------------------------------------------------------------------------------------+
| note_text                                                                                                             |
+-----------------------------------------------------------------------------------------------------------------------+
| Customer complaint: rabbit has been able to detect trap, food apparently less effective now.                          |
| Quantity varies, sold by the sack load.
All guaranteed to be bright and orange, and suitable for use as rabbit bait. |
+-----------------------------------------------------------------------------------------------------------------------+
2 rows in set (0.14 sec)

</pre>

*   Match()指定搜索的列，Ggainst()指定要使用的搜索表达式。
*   传递给Matchy()的值必须与FULLTEXT()定义中相同。
*   全文本搜索的等级由MySQL根据行中词的数目，唯一词的数目，整个索引中的总数以及包含该词的行的数目计算出来。

*   使用查询扩展(放宽了所返回的全文本搜索结果的范围)

<pre>select note_text 
from productnotes 
where Match(note_text) Against('anvils' WITH QUERY EXPANSION);</pre>

布尔文本搜索： select note_text from productnotes where Match(note_text) Against('heavy -rope*' IN BOOLEAN MODE); [不包含以rope开头的单词] select note_text from productnotes where Match(note_text) Against('+safe +(<combination)' IN BOOLEAN MODE);





<<<<<<< HEAD

## 19 插入数据（INSERT 插入完整的行，）

*   编写依赖于特定列次序的SQL语句是很不安全的。
*   mysql用单条INSERT语句处理多个插入比使用多条INSERT语句快。

## * 插入检索出的数据（INSERT SELECT,两者的列名可以不一样）：

INSERT INTO customers (cust_id, cust_contact, cust_email, cust_name, cust_address, cust_city, cust_state, cust_zip, cust_country) SELECT cust_id, cust_contact, cust_email, cust_name, cust_address, cust_city, cust_state, cust_zip, cust_country

## FROM customers;

## 20 更新和删除数据

*   UPDATE DELETE不要忘记过滤条件where
*   错误发生前更新的所有行被恢复到它们原来的值，IGNORE？？ `UPDATE IGNORE customers
SET cust_email = elmer@fudd.com
WHERE cust_id = 10005;`
*   DELETE 只删除行，不能删除表本身
*   truncate tablename 更快的删除表中所有行（删除表后重新建）
*   引用完整性
*   mysql没有撤销

## 21 创建和操纵表

*   `CREATE TABLE productnotes
(
note_id int NOT NULL AUTO_INCREMENT,
prod_id char(10) NOT NULL,
note_date datetime NOT NULL,
note_text text NULL ,
PRIMARY KEY(note_id),
FULLTEXT(note_text)
) ENGINE=MyISAM;`
*   获得AUTO_INCREMENT时的值 `select last_insert_id()`
*   指定默认值 `quantity int NOT NULL DEFAULT 1 ,` ,默认值必须是常数不能是函数
*   引擎类型 
    *   InnoDB 支持事务，不支持全文本搜索；
    *   MyISAM与之相反；每个MyISAM表在磁盘生产三个文件：
    1.  tablename.frm --.frm文件是用来保存每个数据表的元数据(meta)信息，包括表结构的定义等，frm文件跟数据库存储引擎无关，也就是任何存储引擎的数据表都必须有.frm文件..frm文件可以用来在数据库崩溃时恢复表结构。
    2.  数据文件的扩展名为·MYD (MYData)
    3.  索引文件的扩展名是.MYI (MYIndex)
    
    *   MEMORY功能等价于MyISAM，但数据存储在内存中（适合临时表）；
*   外键不能跨引擎
*   ALTER TABLE vendors ADD vend_phone CHAR(20); [增加列]
*   ALTER TABLE vendors DROP vend_phone CHAR(20); [删除列]
*   ALTER TABLE 可以用来定义外键。
*   表的修改需要谨慎，备份。
*   DROP TABLE customers2;
*   RENAME　TABLE customers2 TO customers;

## 22 视图是虚拟的表

*   隐藏长的sql(一次性编写基础的sql，多次使用) `create view productcustomers as
select cust_name, cust_contact, prod_id
from customers, orders, orderitems
where customers.cust_id = order.cust_id
and orderitems.order_num = orders.order_num;`
*   重新格式化检索出的数据 `create view vendorlocations as
SELECT Concat(RTrim(vend_name), '(', RTrim(vend_country), ')') AS
vend_title
FROM vendors
ORDER BY vend_name;`
*   使用视图与计算字段 `create view orderitemsexpanded as select order_num, prod_id, quantity, item_price, quantity*item_price as expanded_price

# from orderitems;`

## 19 插入数据（INSERT 插入完整的行，）

*   编写依赖于特定列次序的SQL语句是很不安全的。
*   mysql用单条INSERT语句处理多个插入比使用多条INSERT语句快。
*   插入检索出的数据（INSERT SELECT,两者的列名可以不一样）： `INSERT INTO customers
(cust_id, cust_contact, cust_email, cust_name, cust_address, cust_city, cust_state, cust_zip, cust_country)
SELECT
cust_id, cust_contact, cust_email, cust_name, cust_address, cust_city, cust_state, cust_zip, cust_country
FROM custnew;`

## 20 更新和删除数据

*   UPDATE DELETE不要忘记过滤条件where
*   错误发生前更新的所有行被恢复到它们原来的值，IGNORE？？ `UPDATE IGNORE customers
SET cust_email = elmer@fudd.com
WHERE cust_id = 10005;`
*   DELETE 只删除行，不能删除表本身
*   truncate tablename 更快的删除表中所有行（删除表后重新建）
*   引用完整性
*   mysql没有撤销

## 21 创建和操纵表

*   `CREATE TABLE productnotes
(
note_id int NOT NULL AUTO_INCREMENT,
prod_id char(10) NOT NULL,
note_date datetime NOT NULL,
note_text text NULL ,
PRIMARY KEY(note_id),
FULLTEXT(note_text)
) ENGINE=MyISAM;`
*   获得AUTO_INCREMENT时的值 `select last_insert_id()`
*   指定默认值 `quantity int NOT NULL DEFAULT 1 ,` ,默认值必须是常数不能是函数
*   引擎类型 
    *   InnoDB 支持事务，不支持全文本搜索；
    *   MyISAM与之相反；每个MyISAM表在磁盘生产三个文件：
    1.  tablename.frm --.frm文件是用来保存每个数据表的元数据(meta)信息，包括表结构的定义等，frm文件跟数据库存储引擎无关，也就是任何存储引擎的数据表都必须有.frm文件..frm文件可以用来在数据库崩溃时恢复表结构。
    2.  数据文件的扩展名为·MYD (MYData)
    3.  索引文件的扩展名是.MYI (MYIndex)
    
    *   MEMORY功能等价于MyISAM，但数据存储在内存中（适合临时表）；
*   外键不能跨引擎
*   ALTER TABLE vendors ADD vend_phone CHAR(20); [增加列]
*   ALTER TABLE vendors DROP vend_phone CHAR(20); [删除列]
*   ALTER TABLE 可以用来定义外键。
*   表的修改需要谨慎，备份。
*   DROP TABLE customers2;
*   RENAME　TABLE customers2 TO customers;

## 22 视图是虚拟的表

*   隐藏长的sql(一次性编写基础的sql，多次使用) `create view productcustomers as
select cust_name, cust_contact, prod_id
from customers, orders, orderitems
where customers.cust_id = order.cust_id
and orderitems.order_num = orders.order_num;`
*   重新格式化检索出的数据 `create view vendorlocations as
SELECT Concat(RTrim(vend_name), '(', RTrim(vend_country), ')') AS
vend_title
FROM vendors
ORDER BY vend_name;`
*   使用视图与计算字段 `create view orderitemsexpanded as
select order_num,
prod_id,
quantity,
item_price,
quantity*item_price as expanded_price
from orderitems;` >>>>>>> 65627119ace35cb7f9d70b59156dbf7e522e4145
*   一般视图用于检索（select），不用于更新（insert，update，delete）。视图更新有很多限制。

## 23 存储过程

*   `CALL productpricing(@pricelow,
 @pricehigh,
 @priceaverge);`
*   创建存储过程 (临时更改命令行实用程序的语句分隔符) `DELIMITER //
CREATE PROCEDURE productpricing()
BEGIN
SELECT Avg(prod_price) AS priceaverage
FROM products;
END //
DELIMITER ;`
*   调用存储过程： `CALL productpricing();`
*   删除（注意没有括号） `drop procedure productpricing;`
*   使用参数 
    *   `create procedure productpricing(
out pl decimal(8,2),
out ph decimal(8,2),
out pa decimal(8,2)
)
begin
select min(prod_price)
into pl
from products;
select max(prod_price)
into ph
from products;
select avg(prod_price)
into pa
from products;
end;` > 关键字 OUT(从存储过程传出)，IN(传递给存储过程)，INOUT > 一个参数不能返回多个列和行 > mysql变量以@开头 > 调用 `call productpricing(@pricelow,@pricehigh,@priceaverage);` 此 > 时存储过程的结果已经保存在这三个变量中。显示变量 `select @priceaverage;` `DELIMITER //
create procedure ordertatals(
IN onumber INT,
OUT ototal decimal(8,2)
)
begin
select sum(item_price*quantity)
from orderitems
where order_num = onumber
into ototal;
end //
delimiter ;` 调用 `CALL ordertotal(2005, @total);` , `select @total;`
*   建立智能存储过程 `-- Name: ordertotal
-- Parameters: onumber = oreder number
--    texable = 0 if not taxable, 1 if taxable
--    ototal = order total varible
create procedure ordertotal(
in onumber int,
in taxable boolean,
out ototal decimal(8, 2)
) comment 'Obtain order total, optionally adding tax'
begin
-- declare variable for total
declare total decimal(8,2);
-- declare tax percentage
declare taxrate int default 6;
-- get the order total
select sum(item_price*quantity)
from orderitems
where order_num = onumber
into total;
-- Is this taxable?
if taxable then
-- yes, so add taxrate to the total
select total+(total/100*taxrate) into total;
end if;
-- and finally,save to out variable
select total into ototal;
end;` `call ordertotal(20005, 0, @total);
select @total;` 
    *   DECLARE语句定义局部变量；
    *   COMMENT关键字非必须 ，结果在 `SHOW PROCEDURE STATUS;` 的结果中显示。
    *   控制流语句：IF ELSEIF ELSE
*   存储过程查询： 
    *   直接查询 `SELECT SPECIFIC_NAME FROM MYSQL.PROC WHERE DB = 'your_db_name' AND TYPE = 'PROCEDURE';`
    *   查看所有数据库里所有存储过程+内容 `SHOW PROCEDURE STATUS;`
    *   查询当前数据库的 `SELECT SPECIFIC_NAME FROM MYSQL.PROC;`
    *   查询某一个存储过程的具体内容 `SELECT BODY FROM MYSQL.PROC WHERE SPECIFIC_NAME = 'ordertotal'`
*   删除存储过程： `DROP PROCEDURE you_proc_name;`
*   创建存储过程： `SHOW CREATE PROCEDURE ordertotal;`

## 游标

1.  定义 
    *   cursor是select语句检索出来的结果集（浏览与滚动）
    *   主要应用交互式应用
    *   mysql游标只能用于存储过程（和函数）

2.  使用 声明 》打开 》使用 》 关闭 `CREATE PROCEDURE processorders()
BEGIN
DECLARE ordernumbers CURSOR
FOR
SELCET order_num FROM order;
END;` `OPEN ordernumbers;` `CLOSE ordernumbers;` 释放内存和资源
  
    *   使用 `sql
CREATE PROCEDURE processorders()
BEGIN
-- Declare local variables
DECLARE o int;
-- Declare the curor
DECLARE ordernumbers CURSOR
FOR
SELECT order_num FROM orders;
-- Open the curor
OPEN ordernumbers;
-- Get order number
FETCH ordernumbers INTO o;
-- Close the cursor
CLOSE ordernumbers;
END;`
    *   FETCH 在逐行处理 `sql
CREATE PROCEDURE processorders()
BEGIN
-- Declare local variables
DECLARE done boolean default 0;
DECLARE o int;
-- Declare the cursor
DECLARE ordernumbers CURSOR
FOR
SELECT order_num FROM orders;
-- Declare continue handler
DECLARE CONTINUE HANDLER FOR SQLSTATE　'0200' SET done=1;
-- Open the cursor
OPEN ordernumbers;
-- Loop through all rows
REPEAT
-- Get order number
FETCH ordernumbers INTO　o;
-- End of loop
UNTIL done END REPEAT;
-- Close the cursor
CLOSE ordernumbers;
END;`
    *   CONTUE HANDLER ?
    *   SQLSTATE '02000' [mysql错误代码][1]
    *   DECLARE语句的次序 ? \``\`sql --对数据进行实际处理 CREATE PROCEDURE processorders() begin declare done boolean default 0; declare o int; declare t decimal(8,2); declare ordernumbers cursor for select order_num from orders; declare continue handler for sqlstate '02000' set done=1; create table if not exists ordertotals (order_num int, total decimal(8,2)); open ordernumbers;
  
    -- Loop through all rows repeat fetch ordernumbers into o; call ordertotal(o, 1, t); insert into ordertotals(order_num, total) values(o, t); until done end repeat; close ordernumbers; end; \``\` 调用这个存储过程就会建立一张表

## 25 触发器

1.  触发器是mysql响应delete，insert，update语句而自动执行的一条或一组(begin和end之间)语句。
2.  名，关联的表，响应的语句，前后 （尽表支持触发器，视图和临时表不支持） `create TRIGGER newproduct AFTER insert on products FOR EACH ROW select 'Product added'` product added 是每次插入后的提示语 
    *   每张表最多只能有6个触发器
3.  `drop trigger newproduct;`

## `create trigger neworder after insert on orders for each row select new.order_num;`

[Err] 1415 - Not allowed to return a result set from a trigger

## 26 事务

1.  设计良好的数据库模式都是关联的。 事务处理要来保证数据库不包含不完整的操作结果。 
    *   事务(transaction)
    *   回退(rollback)
    *   提交(commit)
    *   保留点(savepoint)
2.  控制事务处理

## 关键在于将SQL语句组分解为逻辑块，并明确规定数据何时应该回退，何时不应该回退。

select * from ordertotals; START TRANSACTION; delete from ordertotals; select * from ordertotals; ROLLBACK;

## select * from ordertotals;

事务处理用来管理insert,update,delete.不能回退create，drop，select。

## 27 全球化和本地化

1.  字符集和校对顺序

- *字符集* 字母和符号的集合 - *编码* 某个字符集成员的内部表示 - *校对* 规定字符如何比较的指令（在order by，group by ，having等数据排序起作用） 2. 使用 - 查看字符集完整列表 `show character set;` - 查看校对 `show collation;` - 查看目前所用字符集和校对 show variables like 'character%'; show variables like 'collation%';

## - 给表指定字符集和校对

create table mytable ( columnn1 int, columnn2 varchar(10) )default character set hebrew

## collate hebrew_general_ci;

*   也可指定特定列
*   指定特定的校对用于排序（下面是为了区分大小写） `select * from customers order by lastname, firstname collate latin1_general_cs;`

## 28 安全管理

1.  访问控制
2.  管理用户

- 获得所有用户(尽量不要直接操作*mysql*表) `use mysql; select user form user;` - 创建删除用户 `create user ben identified by '123456';` `rename user ben to bens;` `drop user ben;` - 设置访问权限(grants, revoke) * 看某用户的权限：

## `shwo grants for ben;` (usage表示没有权限)

+------------------------------------------------------------------------------- ---------------------+ | Grants for ben@% | +------------------------------------------------------------------------------- ---------------------+ | GRANT USAGE ON *.* TO 'ben'@'%' IDENTIFIED BY PASSWORD '*6BB4837EB74329105EE45 68DDA7DC67ED2CA2AD9' | +-------------------------------------------------------------------------------

## ---------------------+

*   grant用法需要：*要授权的权限* *被授予访问权限的数据库或表* *用户名* `grant select on crashcourse.* to ben;` (ben在crashcourse数据库的所有表上有select权限)
*   撤销权限 `revoke select on crashcourse.* from ben;`
*   grant,revoke 可在几个层次上控制权限： 
    *   整个服务器(grant all, revoke all)
    *   整个数据库(on database.*)
    *   特定表(on database.table)
    *   特定列
    *   特定存储过程
*   更改用户口令 `set password for ben=Password('newpasswd');`

## 29 数据库维护

1.  备份数据(由于mysql数据文件正常都是出于打开和使用状态，不能简单的复制需要使用特定工具) 
    *   mysqldump
    *   mysqlhotcopy
    *   BACKUP TABLE SELECT INTO OUTFILE
2.  数据库维护 
    *   `ANALYZE TABLE orders;`
    *   `CHECK TABLE　orders, orderitems;`
    *   `OPTIMIZE TABLE`
3.  诊断启动问题
4.  查看日志文件 *错误日志* *查询日志* *二进制日志* *缓存查询日志* `show variables like 'log_%';` 查看日志开启情况

## 30 改善性能

[1]: dev.mysql.com/doc/mysql/en/error-handling.html