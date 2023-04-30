package com.andyron.jstack;

import java.util.Map;
import java.util.Set;

/**
 * java代码层面查看当前进程下所有线程的信息，比jstack简陋一点
 * @author andyron
 **/
public class AllStackTrace {
    public static void main(String[] args) {
        Map<Thread, StackTraceElement[]> all = Thread.getAllStackTraces();
        Set<Map.Entry<Thread, StackTraceElement[]>> entries = all.entrySet();
        for (Map.Entry<Thread, StackTraceElement[]> entry : entries) {
            Thread t = entry.getKey();
            StackTraceElement[] v = entry.getValue();
            System.out.println("【Thread name is : " + t.getName() + "】");
            for (StackTraceElement s : v) {
                System.out.println("\t" + s.toString());
            }
        }
    }
}
