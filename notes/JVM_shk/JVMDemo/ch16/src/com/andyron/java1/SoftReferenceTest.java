package com.andyron.java1;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

/**
 * 软引用测试：内存不足即回收
 * -Xms10m -Xmx10m
 * @author andyron
 **/
public class SoftReferenceTest {
    public static class User {
        public int id;
        public String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "[id=" + id + ", name=" + name + "]";
        }
    }

    public static void main(String[] args) {
        SoftReference<User> userSoftRef = new SoftReference<>(new User(18, "andyron"));

        // 从软引用中重新获得强引用对象
        System.out.println(userSoftRef.get());

        System.gc();
        System.out.println("After GC:");
        // 垃圾回收后获得软引用中的对象，（内存够的时候，不会回收软引用）
        System.out.println(userSoftRef.get());

        try {
            // 然系统内存资源紧张
            byte[] b = new byte[1024 * 1024 * 70];
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            // 再次从软引用中获取对象
            System.out.println(userSoftRef.get()); // 在报OOM之前，垃圾回收器会回收软引用的可达对象。
        }
    }
}
