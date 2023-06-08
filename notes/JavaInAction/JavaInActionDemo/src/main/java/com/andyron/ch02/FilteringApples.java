package com.andyron.ch02;

import javax.lang.model.element.VariableElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author andyron
 **/
public class FilteringApples {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED));

        List<Apple> greens = filter(inventory, new AppleGreenPredicate());
        List<Apple> greens2 = filter(inventory, (Apple a) -> {
            return a.getColor() == Color.GREEN;
        });
        System.out.println(greens);
        List<Apple> weightApples = filter(inventory, new AppleWeightPredicate());
        System.out.println(weightApples);


        prettyPrintApple(inventory, new AppleFancyFormatter());
        prettyPrintApple(inventory, new AppleSimpleFormatter());

        List<Apple> redApples = filter2(inventory, (Apple a) -> Color.RED.equals(a.getColor()));
        List<Integer> evenNumbers = filter2(Arrays.asList(1, 10, 4, 3), (Integer i) -> i % 2 == 0);
        System.out.println(redApples);
        System.out.println(evenNumbers);
    }

    public static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
        List<Apple> apples = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                apples.add(apple);
            }
        }
        return apples;
    }

    /**
     * 以多种方式根据苹果生成一个String输出（有点儿像多个可定制的toString方法）
     * @param inventory
     * @param formatter
     */
    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
        for (Apple apple : inventory) {
            System.out.println(formatter.accept(apple));
        }
    }

    public static <T> List<T> filter2(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if (p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    enum Color {
        RED,
        GREEN
    }

    public static class Apple {

        private int weight = 0;
        private Color color;

        public Apple(int weight, Color color) {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        @SuppressWarnings("boxing")
        @Override
        public String toString() {
            return String.format("Apple{color=%s, weight=%d}", color, weight);
        }

    }

    interface ApplePredicate {
        boolean test(Apple a);
    }
    static class AppleWeightPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple a) {
            return a.getWeight() > 150;
        }
    }
    static class AppleGreenPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple a) {
            return a.getColor() == Color.GREEN;
        }
    }
    static class AppleRedAndHeavyPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return apple.getColor() == Color.RED && apple.getWeight() > 150;
        }

    }

    interface AppleFormatter {
        String accept(Apple a);
    }
    static class AppleFancyFormatter implements AppleFormatter {
        @Override
        public String accept(Apple a) {
            String characteristic = a.getWeight() > 150 ? "heavy" : "light";
            return "A " + characteristic + " " + a.getColor() + " apple";
        }
    }
    static class AppleSimpleFormatter implements AppleFormatter {
        @Override
        public String accept(Apple a) {
            return "An apple of " + a.getWeight() + "g";
        }
    }

    /**
     * 进一步处理的类型抽象化
     */
    interface Predicate<T> {
        boolean test(T t);
    }
}
