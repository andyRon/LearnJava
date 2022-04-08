package com.andyron.bcdlj.c05.c51;

public class Point implements MyComparable{
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

    public double distance() {
        return Math.sqrt(x*x + y*y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    @Override
    public int compareTo(Object other) {
        if (!(other instanceof Point)) {
            throw new IllegalArgumentException();
        }
        Point otherPoint = (Point) other;
        double delta = distance() - otherPoint.distance();

        if (delta < 0) {
            return -1;
        } else if (delta > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
