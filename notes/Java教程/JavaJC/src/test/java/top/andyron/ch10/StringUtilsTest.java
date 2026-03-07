package top.andyron.ch10;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtilsTest {

//    @ParameterizedTest
//    void testCapitalize(String input, String result) {
//        assertEquals(result, StringUtils.capitalize(input));
//    }

    @ParameterizedTest
    @MethodSource
    void testCapitalize(String input, String result) {
        assertEquals(result, StringUtils.capitalize(input));
    }

    static List<Arguments> testCapitalize() {
        return List.of( // arguments:
                Arguments.of("abc", "Abc"), //
                Arguments.of("APPLE", "Apple"), //
                Arguments.of("gooD", "Good"));
    }

    // 通过@MethodSource注解，它允许我们编写一个同名的静态方法来提供测试参数
    // 静态方法testCapitalize()返回了一组测试参数，每个参数都包含两个String，正好作为测试方法的两个参数传入。

}
