package com.andyron.bcdlj.c04;

/**
 * 图形管理器
 * 负责管理画板上的所有图形对象并负责绘制，在绘制代码中，只需要将每个对象当作Shape并调用draw方法就可以了，系统会自动执行子类的draw方法
 */
public class ShapeManager {
    private static final int MAX_NUM = 100;
    private Shape[] shapes = new Shape[MAX_NUM];
    private int shapeNum = 0;

    public void addShape(Shape shape) {
        if (shapeNum < MAX_NUM) {
            shapes[shapeNum++] = shape;
        }
    }

    public void draw() {
        for (int i = 0; i < shapeNum; i++) {
            shapes[i].draw();
        }
    }

    public static void main(String[] args) {
        ShapeManager shapeManager = new ShapeManager();
        shapeManager.addShape(new Line(new Point(1, 2), new Point(3, 5), "red"));
        shapeManager.addShape(new Circle(new Point(5, 9), 2));
        shapeManager.addShape(new ArrowLine(new Point(6, 7), new Point(8, 11), "blue", true, false));

        shapeManager.draw();
    }
}


