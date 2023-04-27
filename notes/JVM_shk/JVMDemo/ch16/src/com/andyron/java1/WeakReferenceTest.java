package com.andyron.java1;

import java.lang.ref.WeakReference;

/**
 * @author andyron
 **/
public class WeakReferenceTest {
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
        WeakReference<User> wrUser = new WeakReference<>(new User(18, "andyron"));
        // 从弱引用中获取对象
        System.out.println(wrUser.get());

        System.gc();  // 不管当前内存空间是否足够，都会回收
        System.out.println("After GC: ");
        System.out.println(wrUser.get());
    }
}
