package com.andyron.reflection;

import java.lang.annotation.*;
import java.lang.reflect.Field;

/**
 * @author Andy Ron
 */
// 反射操作注解
public class MyTest12 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        Class c = Class.forName("com.andyron.reflection.Student2");
        Annotation[] annotations = c.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }

        // 获得注解的value
        TableName tableName = (TableName)c.getAnnotation(TableName.class);
        String value = tableName.value();
        System.out.println(value);

        Field f = c.getDeclaredField("name");
        FieldAndy field = f.getAnnotation(FieldAndy.class);
        System.out.println(field.columnName());
        System.out.println(field.type());
        System.out.println(field.length());
    }
}

@TableName("db_student")
class Student2 {
    @FieldAndy(columnName = "db_id", type = "int", length = 10)
    private int id;
    @FieldAndy(columnName = "db_name", type = "varchar", length = 20)
    private String name;
    @FieldAndy(columnName = "db_tid", type = "int", length = 10)
    private int tid;

    public Student2(int id, String name, int tid) {
        this.id = id;
        this.name = name;
        this.tid = tid;
    }

    public Student2() {
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

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    @Override
    public String toString() {
        return "Student2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tid=" + tid +
                '}';
    }
}

// 类的注解
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface TableName {
    String value();
}

// 属性的注解
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface FieldAndy {
    String columnName();
    String type();
    int length();
}