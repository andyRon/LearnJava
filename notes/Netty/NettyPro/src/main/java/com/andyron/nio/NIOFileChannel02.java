package com.andyron.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author andyron
 **/
public class NIOFileChannel02 {
    public static void main(String[] args) throws IOException {
        // 创建文件的输入流
        File file = new File("file01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);

        // 通过FileInputStream获取对应的FileChannel
        FileChannel fileChannel = fileInputStream.getChannel();

        // 创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

        // 将通道的数据读入到Buffer
        fileChannel.read(byteBuffer);

        // 将byteBuffer的字节数据转换为String
        System.out.println(new String(byteBuffer.array()));

        fileInputStream.close();
    }
}
