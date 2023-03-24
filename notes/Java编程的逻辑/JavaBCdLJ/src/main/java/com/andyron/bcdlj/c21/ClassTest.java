package com.andyron.bcdlj.c21;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ClassTest {
    public static void main(String[] args) throws Exception {
//        t5();
//        t6();
        t7();
    }
    static void t1() throws ClassNotFoundException {
        System.out.println("=====不同类型的Class对象=======");
        Class<Date> dateClass = Date.class;
        Class<?> aClass = Class.forName("java.util.Date");
        Class<Comparable> comparableClass = Comparable.class;
        Class<Integer> integerClass = int.class;
        Class<Sex> eumClass = Sex.class;
        System.out.println(dateClass);
        System.out.println(aClass);
        System.out.println(comparableClass);
        System.out.println(integerClass);
        System.out.println(eumClass);
    }

    static void t2() {
        System.out.println("----数组类型的Class对象---");
        String[] strArr = new String[10];
        int[][] twoDimArr = new int[3][2];
        long[] oneDimArr = new long[10];
        Class<? extends String[]> strArrCls = strArr.getClass();
        Class<? extends int[][]> twoDimArrCls = twoDimArr.getClass();
        Class<? extends long[]> oneDimArrCls = oneDimArr.getClass();
        System.out.println(strArrCls);
        System.out.println(twoDimArrCls);
        System.out.println(oneDimArrCls);

        System.out.println("====名称信息====");
        Programmer programmer = new Programmer("java", 10000);
        Class<Programmer> pClass = Programmer.class;
        System.out.println(oneDimArrCls.getName());
        System.out.println(pClass.getName());
        System.out.println(pClass.getSimpleName());
        System.out.println(pClass.getCanonicalName());
        System.out.println(pClass.getPackage());
    }
    static void t3() throws NoSuchFieldException, IllegalAccessException {
        Programmer programmer = new Programmer("java", 10000);
        Class<Programmer> pClass = Programmer.class;
        System.out.println("====字段信息-所有public（包括从父类继承的）====");
        for (Field field : pClass.getFields()) {
            System.out.println(field.getName());
        }
        System.out.println("====字段信息-本类所有字段（不包括父类）=====");
        for (Field declaredField : pClass.getDeclaredFields()) {
            System.out.println(declaredField.getName());
        }
        System.out.println();
        System.out.println(pClass.getField("name").getName());
        System.out.println("=====指定对象中字段的值====");
        for (Field declaredField : pClass.getDeclaredFields()) {
            System.out.println(declaredField.get(programmer));
            System.out.println(Modifier.toString(declaredField.getModifiers()));
        }
    }
    static void t4() throws IllegalAccessException {
        System.out.println("====private字段访问");
        List<String> strings = Arrays.asList(new String[]{"Andy", "编程"});
        Class<? extends List> stringsClass = strings.getClass();
        System.out.println(stringsClass.getName());
        for (Field f : stringsClass.getDeclaredFields()) {
            f.setAccessible(true);
            System.out.println(f.getName() + " = " + f.get(strings) + "  " + Modifier.toString(f.getModifiers()));
        }
    }

    static void t5() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        System.out.println("====方法====");
        Programmer programmer = new Programmer("java", 10000);
        Class<Programmer> pClass = Programmer.class;
        for (Method method : pClass.getMethods()) {
            System.out.println(method.getName());
        }
        System.out.println("====自己的方法=====");
        for (Method declaredMethod : pClass.getDeclaredMethods()) {
            System.out.println(declaredMethod.getName());
        }
        // 第二个参数是方法的 参数的类型的Class对象数组
        Method m = pClass.getMethod("test", new Class[]{int.class});
        System.out.println(m.invoke(programmer, 9));

    }

    static void t6() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Programmer programmer = new Programmer("java", 10000);
        Class<Programmer> pClass = Programmer.class;

        System.out.println("===构造方法===");
        for (Constructor<?> constructor : pClass.getConstructors()) {
            System.out.println(constructor.getName());
        }

        Constructor<StringBuilder> stringBuilderConstructor = StringBuilder.class.getConstructor(new Class[]{int.class});
        StringBuilder sb = stringBuilderConstructor.newInstance(100);
    }

    static void t7() throws ClassNotFoundException {
        // 类型检查和转换
        Class<?> cls = Class.forName("java.util.ArrayList");

        System.out.println(Object.class.isAssignableFrom(String.class));
        System.out.println(String.class.isAssignableFrom(String.class));

        System.out.println("==========");
        System.out.println(cls.getSuperclass());
        for (Class<?> i : cls.getInterfaces()) {
            System.out.println(i.getName());
        }

        cls = Class.forName("java.util.function.DoubleBinaryOperator");
        System.out.println(cls.getAnnotations());
        for (Annotation a : cls.getAnnotations()) {
            System.out.println(a);
        }

        System.out.println("==========");
        String[] arr = new String[]{};
        System.out.println(arr.getClass().getComponentType().isLocalClass());

        Long[] larr = (Long[]) Array.newInstance(Long.class, 10);


        System.out.println("=========");
        for (Sex enumConstant : Sex.class.getEnumConstants()) {
            System.out.println(enumConstant);
        }

    }
}

class People {
    public String name;
    String ID;
    private String age;

    public String getNameId() {
        return name + ID;
    }

    public People() {

    }
}

class Programmer extends People {
    public String skill;
    int salary;
    public final static Sex sex = Sex.man;
    static final String INFO = "Hello world";

    public Programmer(String skill, int salary) {
        this.skill = skill;
        this.salary = salary;
    }

    public void show() {
        addSalary();
        System.out.println("I'm super Programmer");
    }

    private void addSalary() {
        salary += 1;
        System.out.println(salary);
    }

    public int test(int n) {
        return n * 2;
    }

}

enum Sex {
    man, woman
}