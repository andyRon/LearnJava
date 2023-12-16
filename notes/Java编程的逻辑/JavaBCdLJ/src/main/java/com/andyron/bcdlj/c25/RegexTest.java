package com.andyron.bcdlj.c25;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author andyron
 **/
public class RegexTest {
    @Test
    void t1() {
        String str = "abc   def        hello.\n    world";
        String[] fields = str.split("[\\s.]+");

        System.out.println(Arrays.toString(fields));
    }

    @Test
    void t2() {
        // 尾部的空白字符串不会包含在返回的结果数组中，但头部和中间的空白字符串会被包含在内
        String str = ", abc, , def, , ";
        String[] fields = str.split(", ");
        System.out.println("field num: " + fields.length);
        System.out.println(Arrays.toString(fields));
    }

    @Test
    void t3() {
        String regex = "\\d{8}";
        String str = "12345678";
        System.out.println(str.matches(regex));
    }
    @Test
    void find(){
        String regex = "\\d{4}-\\d{2}-\\d{2}";
        Pattern pattern = Pattern.compile(regex);
        String str = "today is 2017-06-02, yesterday is 2017-06-01";
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()){
            System.out.println("find " + matcher.group()
                    + " position: " + matcher.start() + "-" + matcher.end());
        }
    }
    @Test
    void findGroup() {
        String regex = "(\\d{4})-(\\d{2})-(\\d{2})";
        Pattern pattern = Pattern.compile(regex);
        String str = "today is 2017-06-02, yesterday is 2017-06-01";
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println("year:" + matcher.group(1)
                    + ", month:" + matcher.group(2) + ", day:" + matcher.group(3));
        }
    }

    @Test
    void t4() {
        String regex = "\\s+";
        String str = "hello     world         good";
        System.out.println(str.replaceAll(regex, " "));
    }

    @Test
    void t5() {
        // 在replaceAll和replaceFirst中，参数replacement也不是被看作普通的字符串，可以使用美元符号加数字的形式（比如$1）引用捕获分组
        String regex = "(\\d{4})-(\\d{2})-(\\d{2})";
        String str = "today is 2017-06-02.";
        System.out.println(str.replaceFirst(regex, "$1/$2/$3"));
    }
    @Test
    public void t6() {
        String regex = "#";
        String str = "#this is a test";
        System.out.println(str.replaceAll(regex, "\\$"));
    }

    @Test
    void replaceCat() {
        Pattern p = Pattern.compile("cat");
        Matcher m = p.matcher("one cat, two cat, three cat");
        StringBuffer sb = new StringBuffer();
        int foundNum = 0;
        while(m.find()) {
            m.appendReplacement(sb, "dog");
            foundNum++;
            if(foundNum == 2) {
                break;
            }
        }
        m.appendTail(sb);
        System.out.println(sb.toString());
    }
}
