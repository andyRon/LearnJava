package com.andyron.java1;

/**
 * 测试堆空间常用的jvm参数：
 * `-XX:+PrintFlagsInitial`：查看所有的参数的默认初始值
 * `-XX:+PrintFlagsFinal`：查看所有的参数的最终值（<u>可能会存在修改，不再是初始值</u>）
 *      可用命令行工具查看某个参数的值： jps， `jinfo -flag SurvivorRatio 进程id`
 * `-Xms`：初始堆空间内存 （默认为物理内存的1/64）
 * `-Xmx`：最大堆空问内存（默认为物理内存的1/4）
 * `-Xmn`：设置新生代的大小。（初始值及最大值）
 * `-XX:NewRatio`：配置新生代与老年代在堆结构的占比
 * `-XX:SurvivorRatio`：设置新生代中Eden和so/S1空间的比例
 * `-XX:MaxTenuringThreshold`：设置新生代垃圾的最大年龄
 * `-XX:+PrintccDetails`：输出详细的GC处理日志
 *      打印gc简要信息：①`-XX:+PrintGC` ②`-verbose:gc`
 * `-XX:HandlePromotionFailure`：是否设罝空间分配担保
 * @author andyron
 **/
public class HeapArgsTest {
    public static void main(String[] args) {

    }
}
