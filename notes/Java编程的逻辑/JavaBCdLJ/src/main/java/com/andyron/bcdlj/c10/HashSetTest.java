package com.andyron.bcdlj.c10;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class HashSetTest {

    public static void main(String[] args) {
//        ex1();
        ex2();
    }

    static void ex1() {
        Set<String> set = new HashSet<>();
        set.add("hello");
        set.add("world");
        set.addAll(Arrays.asList(new String[]{"hello", "andy"}));
        System.out.println(set);
        for (String s : set) {
            System.out.print(s + " ");
        }
    }

    static void ex2() {
        Set<Spec> set = new HashSet<>();
        set.add(new Spec("m", "blue"));
        set.add(new Spec("m", "blue"));
        System.out.println(set);
    }
}

class Spec {
    String size;
    String color;

    @Override
    public String toString() {
        return "Spec{" +
                "size='" + size + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    public Spec(String size, String color) {
        this.size = size;
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Spec spec = (Spec) o;
        return size.equals(spec.size) && color.equals(spec.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, color);
    }
}
