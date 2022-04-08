package com.andyron.bcdlj.c04;

/**
 * 带箭头直线（ArrowLine）继承自Line，但多了两个属性，分别表示两端是否有箭头，也重写了draw方法
 */
public class ArrowLine extends Line {
    private boolean startArrow;
    private boolean endArrow;

    public ArrowLine(Point start, Point end, String color, boolean startArrow, boolean endArrow) {
        super(start, end, color);
        this.startArrow = startArrow;
        this.endArrow = endArrow;
    }

    @Override
    public void draw() {
        super.draw();
        if (startArrow) {
            System.out.println("draw start arrow");
        }
        if (endArrow) {
            System.out.println("draw end arrow");
        }
    }
}
