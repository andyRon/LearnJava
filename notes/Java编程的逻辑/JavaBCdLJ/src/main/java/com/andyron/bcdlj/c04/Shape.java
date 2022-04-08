package com.andyron.bcdlj.c04;

public class Shape {
    private final static String DEFAULT_COLOR = "black";
    private String color;

    public Shape(String color) {
        this.color = color;
    }

    public Shape() {
        this(DEFAULT_COLOR);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void draw() {
        System.out.println("draw shape");
    }
}
