package com.andyron.bcdlj.c05.c53.c534;

import com.andyron.bcdlj.c04.Point;

/**
 * @author andyron
 **/
public class Outer {
    public void test(final int x, final int y){
        Point point = new Point(2, 3) {
            @Override
            public double distance() {
                return distance(new Point(x, y));
            }
        };
        System.out.println(point.distance());
    }
}
