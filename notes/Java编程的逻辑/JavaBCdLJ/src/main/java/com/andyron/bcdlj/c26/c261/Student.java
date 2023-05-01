package com.andyron.bcdlj.c26.c261;

public class Student {
    String name;
    Double score;

    public Student(String name, Double score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    public static String getCollegeName() {
        return "Chushui School";
    }

    public static String getCollegeName(String name) {
        return name + "School";
    }
}
