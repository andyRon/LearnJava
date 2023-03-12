package com.andyron.bcdlj.c07.c76;

/**
 * 表示选项和权重的类
 * @author andyron
 **/
public class Pair {
    Object item;
    int weight;

    public Object getItem() {
        return item;
    }

    public int getWeight() {
        return weight;
    }

    public Pair(Object item, int weight) {
        this.item = item;
        this.weight = weight;
    }
}
