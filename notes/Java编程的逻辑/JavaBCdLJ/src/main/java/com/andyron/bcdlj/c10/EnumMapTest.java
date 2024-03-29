package com.andyron.bcdlj.c10;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class EnumMapTest {

    public static void main(String[] args) {
        List<Clothes> clothes = Arrays.asList(new Clothes[]{
                new Clothes("C001", Size.SMALL), new Clothes("C002", Size.LARGE),
                new Clothes("C003", Size.LARGE), new Clothes("C004", Size.MEDIUM),
                new Clothes("C005", Size.SMALL), new Clothes("C006", Size.SMALL),
        });
        System.out.println(countBySize(clothes));
    }

    static Map<Size, Integer> countBySize(List<Clothes> clothes) {
        Map<Size, Integer> map = new EnumMap<Size, Integer>(Size.class);
        for (Clothes c : clothes) {
            Size size = c.getSize();
            Integer count = map.get(size);
            if (count != null) {
                map.put(size, count + 1);
            } else {
                map.put(size, 1);
            }
        }
        return map;
    }
}

enum Size {
    SMALL, MEDIUM, LARGE
}

/**
 * 衣服
 */
class Clothes {
    String id;
    Size size;

    public Clothes(String id, Size size) {
        this.id = id;
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}