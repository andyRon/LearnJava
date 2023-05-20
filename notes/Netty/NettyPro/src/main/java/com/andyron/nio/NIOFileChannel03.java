package com.andyron.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author andyron
 **/
public class NIOFileChannel03 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("1.txt");
        FileChannel fileChannel01 = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");
        FileChannel fileChannel02 = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while (true) { // 循环读取
            // 清空buffer（重置一些数据）
            // 如果没有这一步，就会出现postion和limit相同的情况，然后read就一直等于0，不会出现-1的情况
            byteBuffer.clear();

            int read = fileChannel01.read(byteBuffer);
            System.out.println("read = " + read);
            if (read == -1) { // 表示读完
                break;
            }
            // 将buffer中数据写入到 fileChannel02  -- 2.txt
            byteBuffer.flip();
            fileChannel02.write(byteBuffer);

        }

        fileInputStream.close();
        fileOutputStream.close();
    }
}
