package com.andyron.bcdlj.c23.c235;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 异常切面类
 * @author andyron
 **/
@Aspect({ServiceB.class})
public class ExceptionAspect {
    public static void exception(Object object, Method method, Object[] args, Throwable e) {
        System.out.println("exception when calling: " + method.getName() + ", " + Arrays.toString(args));
    }
}
