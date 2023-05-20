package com.andyron.nio;

import java.nio.IntBuffer;

/**
 * 举例说明Buffer的使用
 * @author andyron
 **/
public class BasicBuffer {
    public static void main(String[] args) {
        // 创建一个Buffer，大小为5，即可以存放5个int
        IntBuffer intBuffer = IntBuffer.allocate(5);

        // 向buffer存放数据
//        intBuffer.put(10);
//        intBuffer.put(11);
//        intBuffer.put(12);
//        intBuffer.put(13);
//        intBuffer.put(14);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);
        }

        // 如何从buffer中读取数据
        // 将buffer转换，读写切换
        intBuffer.flip();

        intBuffer.position(1);
        intBuffer.limit(3);

        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }
}
