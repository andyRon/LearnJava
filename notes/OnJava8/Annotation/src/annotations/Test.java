package annotations;

import java.lang.annotation.*;

/**
 * @author andyron
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
}
