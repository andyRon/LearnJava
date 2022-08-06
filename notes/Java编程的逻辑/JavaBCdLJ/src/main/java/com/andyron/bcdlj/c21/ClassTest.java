package com.andyron.bcdlj.c21;

import java.lang.reflect.*;

public class ClassTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        String[] strArr = new String[10];
        int[][] twoDimArr = new int[3][2];
        long[] oneDimArr = new long[10];
        Class<? extends String[]> strArrCls = strArr.getClass();
        Class<? extends int[][]> twoDimArrCls = twoDimArr.getClass();
        Class<? extends long[]> oneDimArrCls = oneDimArr.getClass();

        System.out.println(strArrCls);
        System.out.println(twoDimArrCls);
        System.out.println(oneDimArrCls);

        System.out.println("========");

        Programmer programmer = new Programmer("java", 10000);

        Class<Programmer> pClass = Programmer.class;
        for (Field field : pClass.getFields()) {
            System.out.println(field.getName());
        }
        System.out.println();
        for (Field declaredField : pClass.getDeclaredFields()) {
            System.out.println(declaredField.getName());
        }
        System.out.println();
        System.out.println(pClass.getField("name").getName());
        System.out.println();
        for (Field declaredField : pClass.getDeclaredFields()) {
            System.out.println(declaredField.get(programmer));
            System.out.println(Modifier.toString(declaredField.getModifiers()));
        }

        System.out.println("====方法====");
        for (Method method : pClass.getMethods()) {
            System.out.println(method.getName());
        }
        System.out.println("====自己的方法=====");
        for (Method declaredMethod : pClass.getDeclaredMethods()) {
            System.out.println(declaredMethod.getName());
            if (!declaredMethod.isAccessible()) {
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(programmer);
            }
        }
        System.out.println("===构造方法===");
        for (Constructor<?> constructor : pClass.getConstructors()) {
            System.out.println(constructor.getName());
        }

        Constructor<StringBuilder> stringBuilderConstructor = StringBuilder.class.getConstructor(new Class[]{int.class});
        StringBuilder sb = stringBuilderConstructor.newInstance(100);

        System.out.println(Object.class.isAssignableFrom(String.class));
        System.out.println(String.class.isAssignableFrom(String.class));

        System.out.println("==========");
        System.out.println(pClass.getSuperclass().getName());

        System.out.println("==========");
        String[] arr = new String[]{};
        System.out.println(arr.getClass().getComponentType().isLocalClass());

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


}

enum Sex {
    man, woman
}