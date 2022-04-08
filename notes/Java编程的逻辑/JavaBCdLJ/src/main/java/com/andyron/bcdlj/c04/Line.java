package com.andyron.bcdlj.c04;

public class Line extends Shape{
    private Point start;
    private Point end;

    public Line(Point start, Point end, String color) {
        super(color);
        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public double length() {
        return start.distance(end);
    }

    @Override
    public void draw() {
        System.out.println("draw line from " + start.toString() + " to " + end.toString() + ", using color " + getColor());
    }

    public static void main(String[] args) {
        Line blue = new Line(new Point(1, 2), new Point(3, 5), "blue");
        blue.draw();
    }
}
