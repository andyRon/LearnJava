package com.andyron.nio;

import java.nio.ByteBuffer;

/**
 * @author andyron
 **/
public class NIOByteBufferPutGet {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);

        // 类型化方式放入数据
        buffer.putInt(100);
        buffer.putLong(9);
        buffer.putChar('上');
        buffer.putShort((short) 5);

        buffer.flip();

        // 这里取出方法和顺序要对应，否则取出结果不对（溢出等问题）或者BufferUnderflowException异常
        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());
    }
}
