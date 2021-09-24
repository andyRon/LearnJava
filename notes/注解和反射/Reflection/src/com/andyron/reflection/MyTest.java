package com.andyron.reflection;

/**
 * @author Andy Ron
 */
public class MyTest {
    public static void main(String[] args) throws ClassNotFoundException {
        Class c1 = Class.forName("com.andyron.reflection.User");
        System.out.println(c1);

        Class c2 = Class.forName("com.andyron.reflection.User");
        Class c3 = Class.forName("com.andyron.reflection.User");
        System.out.println(c1.hashCode());
        System.out.println(c2.hashCode());
        System.out.println(c3.hashCode());

    }
}

// 实体类 pojo或entity
class User {
    private int id;
    private String name;
    private int age;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public User() {
    }

    private void test() {

    }
}
