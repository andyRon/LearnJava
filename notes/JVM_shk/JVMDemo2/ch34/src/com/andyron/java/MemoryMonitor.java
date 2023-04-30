package com.andyron.java;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

/**
 * 监控我们的应用服务器的堆内存使用情况，设置一些阈值进行报警等处理。
 * @author andyron
 **/
public class MemoryMonitor {
    public static void main(String[] args) {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage usage = memoryMXBean.getHeapMemoryUsage();
        System.out.println("Init Heap: " + usage.getInit()/1024/1024 + "m");
        System.out.println("Max Heap: " + usage.getMax()/1024/1024 + "m");
        System.out.println("Use Heap: " + usage.getUsed()/1024/1024 + "m");
        System.out.println("\nFull Information:");
        System.out.println("Heap Memory Usage: " + memoryMXBean.getHeapMemoryUsage());
        System.out.println("Non-Heap Memory Usage: " + memoryMXBean.getNonHeapMemoryUsage());

        System.out.println("================通过Java获取相关系统状态====================");
        System.out.println("当前堆内存的大小：" + (int) Runtime.getRuntime().totalMemory()/1024/1024 + "m");
        System.out.println("空闲堆内存的大小：" + (int) Runtime.getRuntime().freeMemory()/1024/1024 + "m");
        System.out.println("最大可用总堆内存的大小：" + Runtime.getRuntime().maxMemory()/1024/1024 + "m");
    }
}
