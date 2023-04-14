package com.andyron.java1;

/**
 * -Xms600m -Xmx600m
 * -XX:NewRatio   ： 设置新生代与老年代的比例，默认值2
 * -XX:SurvivorRatio ： 设置新生代中的Eden空间和另外两个Survivor空间的比例，默认是8，也就是8:1:1
 * 但系统有时有个自适应分配策略，不是默认的8:1:1，
 * -XX:-UseAdaptiveSizePolicy  ： 关闭自适应的内存分配策略（暂时用不到） -关闭 +开启
 * -Xmn ：设置新生代空间大小（一般不设置）
 * @author andyron
 **/
public class EdenSurvivorTest {
    public static void main(String[] args) {
        System.out.println("我只是打个酱油！");
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
