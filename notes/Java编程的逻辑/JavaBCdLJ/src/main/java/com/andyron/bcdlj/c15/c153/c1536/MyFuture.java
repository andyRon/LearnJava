package com.andyron.bcdlj.c15.c153.c1536;

/**
 * 表示异步调用的结果
 * 这个接口的get方法返回真正的结果，如果结果还没有计算完成，get方法会阻塞直到计算完成，如果调用过程发生异常，则get方法抛出调用过程中的异常。
 * @author andyron
 **/
public interface MyFuture <V> {
    V get() throws Exception;
}
