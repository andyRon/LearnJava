package com.andyron.bcdlj.c03;

import java.util.Date;

public class Point {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point() {
        this(0, 0);
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 到原点的距离
     * @return
     */
    public double distance() {
        return Math.sqrt(x*x + y*y);
    }

    /**
     * 到某点的距离
     * @param p
     * @return
     */
    public double distance(Point p) {
        return Math.sqrt(Math.pow(x - p.getX(), 2) + Math.pow(y - p.getY(), 2));
    }

    public static void main(String[] args) {
//        Point point = new Point();
//        point.setX(2);
//        point.setY(5);
        Point point = new Point(2, 5);
        System.out.println(point.distance());


    }
}
