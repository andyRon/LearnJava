package top.andyron.ch05.ch53;

import java.lang.reflect.Field;

public class Handle {

    public static void main(String[] args) {

    }

    void check(Person person) throws IllegalAccessException, ReflectiveOperationException {
        for (Field field : person.getClass().getFields()) {
            Range range = field.getAnnotation(Range.class);
            if (range != null) {
                Object value = field.get(person);
                if (value instanceof String s) {
                    if (s.length() < range.min() || s.length() > range.max()) {
                        throw new IllegalArgumentException("Invalid field: " + field.getName());
                    }
                    // ....
                }
            }
        }
    }
}
