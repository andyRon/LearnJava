package com.andyron.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 说明
 * MappedByteBuffer可以让文件直接在内存(堆外的内存)中进行修改，操作系统不需要拷贝一次
 *
 * 查看文件修改，需要用另外的编辑器打开查看
 * @author andyron
 **/
public class MappedByteBufferTest {
    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("1.txt", "rw");

        FileChannel channel = randomAccessFile.getChannel();

        /*
            参数1：使用的读写模式
            参数2：可以直接修改的起始位置
            参数3：映射到内存的大小（即将1.txt中5个字节映射到内存，也就是说可以直接修改的范围是[0-50)）
         */
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        mappedByteBuffer.put(0, (byte) 'W');
        mappedByteBuffer.put(3, (byte) '7');

        randomAccessFile.close();
    }
}
