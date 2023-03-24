package com.andyron.bcdlj.c22.c225;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class SimpleFormatter {

    /**
     * 按照对应注解信息序列化对象
     * @param obj
     * @return
     */
    public static String format(Object obj) {
        try {
            Class<? > cls = obj.getClass();
            StringBuilder sb = new StringBuilder();
            for(Field f : cls.getDeclaredFields()) {
                if(!f.isAccessible()) {
                    f.setAccessible(true);
                }
                Label label = f.getAnnotation(Label.class);
                String name = label != null ? label.value() : f.getName();
                Object value = f.get(obj);
                if(value != null && f.getType() == Date.class) {
                    value = formatDate(f, value);
                }
                sb.append(name + ": " + value + "\n");
            }
            return sb.toString();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static Object formatDate(Field f, Object value) {
        Format format = f.getAnnotation(Format.class);
        if(format != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format.pattern());
            sdf.setTimeZone(TimeZone.getTimeZone(format.timezone()));
            return sdf.format(value);
        }
        return value;
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Student student = new Student("张三", sdf.parse("2002-5-31"), 89.2d);
        System.out.println(SimpleFormatter.format(student));
    }
}
