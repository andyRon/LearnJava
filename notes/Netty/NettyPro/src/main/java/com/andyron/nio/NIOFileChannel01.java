package com.andyron.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author andyron
 **/
public class NIOFileChannel01 {
    public static void main(String[] args) throws IOException {
        String str = "hello，中国";
        // 创建一个输出流
        FileOutputStream fileOutputStream = new FileOutputStream("file01.txt");

        // 通过fileOutputStream获取对应的FileChannel。这个FileChannel真实类型是FileChannelImpl
        FileChannel fileChannel = fileOutputStream.getChannel();

        // 创建一个缓冲区ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 将str放入byteBuffer
        byteBuffer.put(str.getBytes());

        // 对byteBuffer进行flip
        byteBuffer.flip();

        // 将byteBuffer数据写入到fileChannel
        fileChannel.write(byteBuffer);

        fileOutputStream.close();
    }
}
