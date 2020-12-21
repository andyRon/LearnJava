package annotations;

import java.lang.annotation.*;

/**
 * 追踪项目中的用例
 * @author andyron
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCase {
    int id();
    String description() default "no description";
}
