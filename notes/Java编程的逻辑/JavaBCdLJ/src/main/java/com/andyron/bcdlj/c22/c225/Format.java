package com.andyron.bcdlj.c22.c225;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义日期类型的输出格式
 * @author andyron
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Format {
    String patten() default "yyyy-MM-dd HH:mm:ss";
    String timezone() default "GMT+8";
}
