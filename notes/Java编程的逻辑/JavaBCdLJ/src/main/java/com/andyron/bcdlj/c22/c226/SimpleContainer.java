package com.andyron.bcdlj.c22.c226;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DI容器
 */
public class SimpleContainer {
    /**
     * 缓存创建过的单例对象
     */
    private static Map<Class<? >, Object> instances = new ConcurrentHashMap<>();

    /**
     * 创建需要的对象，并配置依赖关系
     * @param cls
     * @return
     * @param <T>
     */
    public static <T> T getInstance(Class<T> cls) {
        try {
            // 如果非单列模式，直接常见实例
            boolean singleton = cls.isAnnotationPresent(SimpleSingleton.class);
            if (!singleton) {
                return createInstance(cls);
            }
            // 单列模式，从缓冲获取
            Object obj = instances.get(cls);
            if (obj != null) {
                return (T) obj;
            }
            // TODO
            synchronized (cls) {
                obj = instances.get(cls);
                if (obj == null) {
                    obj = createInstance(cls);
                    instances.put(cls, obj);
                }
            }
            return (T) obj;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> T createInstance(Class<T> cls) throws Exception {
        T obj = cls.newInstance();
        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            // 当前字段上如果有注解SimpleInject，就初始化
            if (f.isAnnotationPresent(SimpleInject.class)) {
                if (!f.isAccessible()) {
                    f.setAccessible(true);
                }
                Class<?> fieldCls = f.getType();
                f.set(obj, getInstance(fieldCls));
            }
        }
        return obj;
    }
    public static void main(String[] args) {
        // 使用getInstance方法获取对象实例，而不是自己new
        ServiceA a = SimpleContainer.getInstance(ServiceA.class);
        a.callB();
    }
}
