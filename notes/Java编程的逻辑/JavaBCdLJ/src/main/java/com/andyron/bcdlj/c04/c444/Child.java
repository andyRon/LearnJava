package com.andyron.bcdlj.c04.c444;

import com.andyron.bcdlj.c04.c442.Base;

/**
 * @author andyron
 **/
public class Child {
    private Base base;
    private long sum;
    public Child() {
        base = new Base();
    }
    public void add(int number) {
        base.add(number);
        sum += number;
    }
    public void addAll(int[] numbers) {
        base.addAll(numbers);
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
    }

    public long getSum() {
        return sum;
    }
}
