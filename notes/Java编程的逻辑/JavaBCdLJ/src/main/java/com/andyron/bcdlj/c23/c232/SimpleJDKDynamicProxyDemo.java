package com.andyron.bcdlj.c23.c232;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *  使用Java SDK实现动态代理示例
 */
public class SimpleJDKDynamicProxyDemo {
    static interface IService {
        public void sayHello();
    }
    static class RealService implements IService {
        @Override
        public void sayHello() {
            System.out.println("hello");
        }
    }
    static class SimpleInvocationHandler implements InvocationHandler {
        private Object realObj;

        public SimpleInvocationHandler(Object realObj) {
            this.realObj = realObj;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("entering " + method.getName());
            Object result = method.invoke(realObj, args);
            System.out.println("leaving " + method.getName());
            return result;
        }
    }
    public static void main(String[] args) {
        IService realService = new RealService();
        IService proxyService = (IService) Proxy.newProxyInstance(IService.class.getClassLoader(),
                new Class<? >[]{ IService.class },
                new SimpleInvocationHandler(realService));
        proxyService.sayHello();
    }
}

//final class $Proxy0 extends Proxy implements SimpleJDKDynamicProxyDemo.IService {
//    private static Method m1;
//    private static Method m2;
//    private static Method m3;
//    private static Method m0;
//
//    public $Proxy0(InvocationHandler paramInvocationHandler) {
//        super(paramInvocationHandler);
//    }
//    public final boolean equals(Object paramObject) {
//        return ((Boolean) this.h.invoke(this, m1, new Object[]{ paramObject })).booleanValue();
//    }
//
//    @Override
//    public final void sayHello() {
//        this.h.invoke(this, m3, null);
//    }
//    public final String toString() {
//        return (String) this.h.invoke(this, m2, null);
//    }
//    public final int hashCode() {
//        return ((Integer) this.h.invoke(this, m0, null)).intValue();
//    }
//
//    static {
//        m1 = Class.forName("com.andyron.bcdlj.c23.c232.SimpleJDKDynamicProxyDemo")
//                .getMethod("equals", new Class[]{ Class.forName("java.lang.Object") });
//        m3 = Class.forName("com.andyron.bcdlj.c23.c232.SimpleJDKDynamicProxyDemo$IService")
//                .getMethod("sayHello", new Class[0]);
//        m2 = Class.forName("java.lang.Object")
//                .getMethod("toString", new Class[0]);
//        m0 = Class.forName("java.lang.Object")
//                .getMethod("hashCode", new Class[0]);
//    }
//}