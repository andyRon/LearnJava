package com.andyron.bcdlj;

import com.andyron.bcdlj.c07.c76.Password;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.io.*;
import java.lang.reflect.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class MyTest {

    @Test
    public void test() throws IntrospectionException {
//        System.out.println(UUID.randomUUID().toString());

        HashMap<String, String> map = new HashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v1");
        System.out.println(map);
        System.out.println(map.keySet());
        System.out.println(map.values());
        map.keySet().clear();
        System.out.println(map);






    }

    @Test
    public void t() {
        System.out.println(new Random().nextInt(4));
    }



    @Test
    public void test21() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<Date> cls = Date.class;
        Class<?> aClass = Class.forName("java.util.Date");
//        System.out.println(aClass.getConstructors());

        Class<Integer> integerClass = int.class;
//        System.out.println(integerClass.getAnnotations());

        String[] strArr = new String[10];
        int[][] twoDimArr = new int[3][2];
        int[] oneDimArr = new int[10];
        Class<? extends String[]> strArrCls = strArr.getClass();
        Class<? extends int[][]> twoDimArrCls = twoDimArr.getClass();
        Class<? extends int[]> oneDimArrCls = oneDimArr.getClass();
        System.out.println(strArrCls);
        System.out.println(twoDimArr);
        System.out.println(oneDimArr);

        Class<Size> sCls = Size.class;
        System.out.println(sCls.getPackage());

        Class<String> stringClass = String.class;
        System.out.println(stringClass.getName());

        System.out.println(char[].class.getName());
        System.out.println(boolean[].class.getName());
        System.out.println(byte[].class.getName());
        System.out.println(long[].class.getName());
        System.out.println(double[].class.getName());
        System.out.println(float[].class.getName());
        System.out.println(short[].class.getName());

        String str = new String("abc");
        Class<? extends String> strClass = str.getClass();
        for (Field f : strClass.getDeclaredFields()) {
            System.out.println(f.getName());
        }
        System.out.println("-----------");

        List<String> obj = Arrays.asList(new String[]{"Andy", "编程"});
        Class<? > lcls = obj.getClass();
        System.out.println(lcls.getName());
        for(Field f : lcls.getDeclaredFields()){  // 返回的是Arrays静态内部类ArrayList中的两个字段
            f.setAccessible(true);
            System.out.println(f.getName() + " - " + f.get(obj) + " - " + Modifier.toString(f.getModifiers()));
        }
        for (Method m : lcls.getDeclaredMethods()) {
            m.setAccessible(true);
            System.out.println(m.getName());
        }
        System.out.println("-----------");

        Class<Integer> icls = Integer.class;
        try {
            Method method = icls.getMethod("parseInt", new Class[]{String.class});
            System.out.println(method.invoke(null, "123"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("-----------");

        Map<String, Integer> map = HashMap.class.newInstance();
        map.put("andy", 1587);


        // 通过指定参数类型，获取到特定构造方法
        Constructor<StringBuilder> constructor = StringBuilder.class.getConstructor(new Class[]{int.class});
        StringBuilder sb = constructor.newInstance(100);

        System.out.println("-----------");

        String[] arr = new String[]{};
        System.out.println(arr.getClass().getComponentType()); // class java.lang.String

        for (Size enumConstant : Size.class.getEnumConstants()) {
            System.out.println(enumConstant.toString());
        }
    }

    enum Size {
        SMALL, MEDIUM, BIG
    }

    @Test
    public void mytest() {
        String s1 = new String("xyz");
        String s2 = new String("xyz");
        Boolean e1 = s1.equals(s2);
        Boolean e2 = (s1 == s2);
        System.out.println(e1 + " " + e2);
        String s = "祝你考出好成绩！";
        System.out.println(s.length());
    }

    @Test
    public void test7_1() {
        int a = 0x12345678;
        System.out.println(Integer.toBinaryString(a));
        int r = Integer.reverse(a);
        System.out.println(Integer.toBinaryString(r));
        int rb = Integer.reverseBytes(a);
        System.out.println(Integer.toHexString(rb));
    }

    @Test
    public void test7_2() {
        String name = "Andy学编程,好学不好学呢";
        System.out.println(name.substring(2));
        System.out.println(name.lastIndexOf('学'));
        System.out.println(name.indexOf('学'));
        System.out.println(name.indexOf("好学"));
        System.out.println(name.contains("dy"));
        System.out.println(name.compareTo("Amdy"));
        System.out.println(name.replace("学", "血"));
        System.out.println(name.replace("好学", "号雪"));
        System.out.println(Arrays.toString(name.split("学")));
    }

    @Test
    public void test7_4() {
        int[] arr = {1, 3, 2, 6, 8, 12};
        System.out.println(Arrays.toString(Arrays.copyOf(arr, arr.length)));
        System.out.println(Arrays.hashCode(arr));
        Arrays.fill(arr, 4);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test7_5() {
        System.out.println(System.currentTimeMillis());
        Date date = new Date();
        System.out.println(date.getTime());
        TimeZone aDefault = TimeZone.getDefault();
        System.out.println(aDefault);

        System.out.println(System.getProperty("user.timezone"));
    }

    @Test
    public void test7_6() {
        Random rnd = new Random();
        System.out.println(rnd.nextInt());
        System.out.println(rnd.nextInt(100));

        System.out.println("------");
        Random rnd2 = new Random(20220101);
        for (int i = 0; i < 5; i++) {
            System.out.print(rnd2.nextInt(100) + " ");
        }
        System.out.println(System.currentTimeMillis());
        System.out.println(System.nanoTime());

        System.out.println(Password.randomPassword());
        System.out.println(Password.randomPassword2());
        System.out.println(Password.randomPassword3());

    }

    @Test
    public void test9() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.size();
    }

    /**
     *  使用HashMap统计随机数
     */
    @Test
    public void test10_1_2() {
        Random rnd = new Random();
        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            int num = rnd.nextInt(4);
            Integer count = countMap.get(num);
            if (count == null) {
                countMap.put(num, 1);
            } else {
                countMap.put(num, count + 1);
            }
        }
        for (Map.Entry<Integer, Integer> kv : countMap.entrySet()) {
            System.out.println(kv.getKey() + ", " + kv.getValue());
        }
    }
    @Test
    public void test10() {
        HashMap<Integer, Integer> map = new HashMap<>();
//        map

    }



    @Test
    public void test13_2() throws IOException {
        FileOutputStream output = new FileOutputStream("hello.txt");
        try {
            String data = "hello, world! I'm coming.";
            byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
            output.write(bytes);
        } finally {
            output.close();
        }
    }

    @Test
    public void test13_2_() throws IOException {
        FileInputStream input = new FileInputStream("hello.txt");
        try {
            byte[] buf = new byte[1024];
            int bytesRead = input.read(buf);
            String data = new String(buf, 0, bytesRead, StandardCharsets.UTF_8);
            System.out.println(data);
        } finally {
            input.close();
        }
    }

    @Test
    public void test13_3() throws IOException {
        DataOutputStream output = new DataOutputStream(new FileOutputStream("test.dat"));
        try {
            output.write(123);
        } finally {
            output.close();
        }
    }

    @Test
    public void test13_3_2() throws IOException {
        FileOutputStream output = new FileOutputStream("test.txt");
        try {
            String data = Integer.toString(123);
            output.write(data.getBytes(StandardCharsets.UTF_8));
        } finally {
            output.close();
        }

//        Pair<String, Integer> pair = new Pair<String, Integer>("key1", 123);
    }

}

class TestList extends AbstractList {

    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        super.removeRange(fromIndex, toIndex);
    }
}

class User {
    String id;
    String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}