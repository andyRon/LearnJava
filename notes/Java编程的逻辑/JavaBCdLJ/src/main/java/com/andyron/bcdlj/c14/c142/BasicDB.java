package com.andyron.bcdlj.c14.c142;

import java.io.*;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * @author andyron
 **/
public class BasicDB {
    private static final int MAX_DATA_LENGTH = 1020;
    /**
     *
     */
    private static final byte[] ZERO_BYTES = new byte[MAX_DATA_LENGTH];
    /**
     * 数据文件扩展名
     */
    private static final String DATA_SUFFIX = ".data";
    /**
     * 元数据文件扩展名，包括索引和空白空间数据
     */
    private static final String META_SUFFIX = ".meta";
    /**
     * 索引信息，键->值在.data文件中的位置
     */
    Map<String, Long> indexMap;
    /**
     *
     */
    Queue<Long> gaps;
    /**
     * 值数据文件
     */
    RandomAccessFile db;
    /**
     * 元数据文件
     */
    File metaFile;

    public BasicDB(String path, String name) throws IOException {
        File dataFile = new File(path + name + DATA_SUFFIX);
        metaFile = new File(path + name + META_SUFFIX);
        db = new RandomAccessFile(dataFile, "rw");
        if (metaFile.exists()) {
            // 元数据文件存在时，会调用loadMeta将元数据加载到内存
            loadMeta();
        } else {
            indexMap = new HashMap<>();
            gaps = new ArrayDeque<>();
        }
    }
    
    public void put(String key, byte[] value) throws IOException {
       Long index = indexMap.get(key);
       if (index == null) {
           // 键如果不存在，调用nextAvailablePos方法为值找一个存储位置，并将键和存储位置保存到索引中
           index = nextAvailablePos();
           indexMap.put(key, index);
       }
       // 将值写到数据文件中
       writeData(index, value);
    }

    private void writeData(Long pos, byte[] data) throws IOException {
        // 先检查长度，长度满足的情况下，定位到指定位置，写实际数据的长度、写内容、最后补白。
        if (data.length > MAX_DATA_LENGTH) {
            throw new IllegalArgumentException("maximum allowed length is "
                    + MAX_DATA_LENGTH + ", data length is " + data.length);
        }
        db.seek(pos);
        db.writeInt(data.length);
        db.write(data);
        db.write(ZERO_BYTES, 0, MAX_DATA_LENGTH - data.length);
    }

    private Long nextAvailablePos() throws IOException {
        // 首先查找空白空间，如果有，则重用，否则定位到文件末尾
        if (!gaps.isEmpty()) {
            return gaps.poll();
        } else {
            return db.length();
        }
    }

    public byte[] get(String key) throws IOException {
        Long index = indexMap.get(key);
        if (index != null) {
            return getData(index);
        }
        return null;
    }

    private byte[] getData(Long pos) throws IOException {
        db.seek(pos);
        int length = db.readInt();
        byte[] data = new byte[length];
        db.readFully(data);
        return data;
    }

    public void remove(String key) {
        Long index = indexMap.remove(key);
        if (index != null) {
            gaps.offer(index);
        }
    }

    public void flush() throws IOException {
        saveMeta();
        db.getFD().sync();
    }

    public void saveMeta() throws IOException {
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(metaFile)));
        try {
            saveIndex(out);
            saveGaps(out);
        } finally {
            out.close();
        }
    }

    private void saveIndex(DataOutputStream out) throws IOException {
        out.writeInt(indexMap.size());
        for (Map.Entry<String, Long> entry : indexMap.entrySet()) {
            out.writeUTF(entry.getKey());
            out.writeLong(entry.getValue());
        }
    }

    private void saveGaps(DataOutputStream out) throws IOException {
        out.writeInt(gaps.size());
        for (Long pos : gaps) {
            out.writeLong(pos);
        }
    }
    private void loadMeta() throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(metaFile)));
        try {
            loadIndex(in);
            loadGaps(in);
        } finally {
            in.close();
        }
    }

    private void loadIndex(DataInputStream in) throws IOException {
        int size = in.readInt();
        indexMap = new HashMap<String, Long>((int) (size / 0.75f) + 1, 0.75f);
        for (int i = 0; i < size; i++) {
            String key = in.readUTF();
            long index = in.readLong();
            indexMap.put(key, index);
        }
    }

    private void loadGaps(DataInputStream in) throws IOException {
        int size = in.readInt();
        gaps = new ArrayDeque<>(size);
        for (int i = 0; i < size; i++) {
            Long index = in.readLong();
            gaps.add(index);
        }
    }

    public void close() throws IOException {
        flush();
        db.close();
    }
}
