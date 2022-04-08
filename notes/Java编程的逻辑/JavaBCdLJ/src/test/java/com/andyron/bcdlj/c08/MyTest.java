package com.andyron.bcdlj.c08;

import com.andyron.bcdlj.c08.c81.DynamicArray;
import org.junit.Test;

import java.util.Random;

public class MyTest {

    @Test
    public void DynamicArrayTest() {
        DynamicArray<Double> arr = new DynamicArray<Double>();
        Random rnd = new Random();
        int size = 1+rnd.nextInt(100);
        for(int i=0; i<size; i++){
            arr.add(Math.random());
        }
        Double d = arr.get(rnd.nextInt(size));
        System.out.println(d);
    }
}
