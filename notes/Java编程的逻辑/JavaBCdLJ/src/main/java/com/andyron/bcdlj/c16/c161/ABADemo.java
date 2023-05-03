package com.andyron.bcdlj.c16.c161;


import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author andyron
 **/
public class ABADemo {

    public static void main(String[] args) {
        Pair pair = new Pair(100, 200);
        int stamp = 1;
        AtomicStampedReference<Pair> pairRef = new AtomicStampedReference<>(pair, stamp);
        int newStamp = 2;
        pairRef.compareAndSet(pair, new Pair(200, 200), stamp, newStamp);
    }
}
