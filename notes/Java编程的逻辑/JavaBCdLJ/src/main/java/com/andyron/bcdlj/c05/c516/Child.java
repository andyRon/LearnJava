package com.andyron.bcdlj.c05.c516;

import com.andyron.bcdlj.c04.c442.Base;

/**
 * @author andyron
 **/
public class Child implements IAdd {
    private Base base;
    private long sum;
    public Child() {
        base = new Base();
    }

    @Override
    public void add(int number) {
        base.add(number);
        sum += number;
    }

    @Override
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
