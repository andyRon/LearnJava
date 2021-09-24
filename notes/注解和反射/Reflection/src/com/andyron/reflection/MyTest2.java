package com.andyron.reflection;

/**
 * @author Andy Ron
 */
public class MyTest2 {
    public static void main(String[] args) throws ClassNotFoundException {
        Person person = new Student();
        System.out.println("这个人是" + person.name);

        Class c1 = person.getClass();
        System.out.println(c1.hashCode());

        Class c2 = Class.forName("com.andyron.reflection.Student");
        System.out.println(c2.hashCode());

        Class c3 = Student.class;
        System.out.println(c3.hashCode());

        System.out.println(Integer.TYPE);

        System.out.println(c2.getSuperclass());
    }
}

class Person {
    public String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Student extends Person {
    public Student() {
        this.name = "我是学生";
    }
}

class Teacher extends Person {
    public Teacher() {
        this.name = "我是老师";
    }
}