package com.andyron.bcdlj.c23.c235;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 日志切面类
 * @author andyron
 **/
@Aspect({ServiceA.class, ServiceB.class})
public class ServiceLogAspect {
    public static  void before(Object object, Method method, Object[] args) {
        System.out.println("entering " + method.getDeclaringClass().getSimpleName() + "::" + method.getName()
        + ", args: " + Arrays.toString(args));
    }

    public static void after(Object object, Method method, Object[] args, Object result) {
        System.out.println("leaving " + method.getDeclaringClass().getSimpleName() + "::" + method.getName()
        + ", result: " + result);
    }
}
