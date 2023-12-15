package com.andyron.bcdlj.c21.c213;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

public class GenericDemo {

    static class GenericTest<U extends Comparable<U>, V> {
        U u;
        V v;
        List<String> list;

        public U test(List<? extends Number> numbers) {
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        Class<GenericTest> cls = GenericTest.class;

        System.out.println("====类的类型参数=====");
        for (TypeVariable<Class<GenericTest>> t : cls.getTypeParameters()) {
            System.out.println(t.getName() + " extends " + Arrays.toString(t.getBounds()));
        }

        System.out.println("====字段：泛型类型=====");
        Field fu = cls.getDeclaredField("u");
        System.out.println(fu.getGenericType());

        System.out.println("====字段：参数化的类型=====");
        Field flist = cls.getDeclaredField("list");
        Type listType = flist.getGenericType();
        if (listType instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) listType;
            System.out.println("raw type: " + pType.getRawType() + ", type arguments:" + Arrays.toString(pType.getActualTypeArguments()));
        }

        System.out.println("====方法的泛型参数=====");
        Method m = cls.getMethod("test", new Class[]{List.class});
        for (Type t : m.getGenericParameterTypes()) {
            System.out.println(t);
        }

    }
}
