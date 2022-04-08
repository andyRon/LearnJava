package com.andyron.bcdlj.c04;

public class Circle extends Shape {
    private Point center;
    private double r;

    public Circle(Point center, double r) {
        this.center = center;
        this.r = r;
    }

    @Override
    public void draw() {
        System.out.println("draw a Circle, and center is " + center.toString() + "radius is " + r + ", useing color is " + getColor());
    }

    public double area() {
        return Math.PI * r * r;
    }

    public static void main(String[] args) {
        Point point = new Point(2, 3);
        Circle circle = new Circle(point, 2);
        circle.draw();
        System.out.println(circle.area());
    }
}
