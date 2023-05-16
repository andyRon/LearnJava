package com.andyron.bcdlj.c16.c161;

/**
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

    @Override
    public String toString() {
        return "Pair{" +
                "item=" + item +
                ", weight=" + weight +
                '}';
    }
}
