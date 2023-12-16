package com.andyron.bcdlj.c25;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author andyron
 **/
public class TemplateEngine {
    private static Pattern templatePattern = Pattern.compile("\\{(\\w+)\\}");
    public static String templateEngine(String template,
                                        Map<String, Object> params) {
        StringBuffer sb = new StringBuffer();
        Matcher matcher = templatePattern.matcher(template);
        while(matcher.find()) {
            String key = matcher.group(1);
            Object value = params.get(key);
            matcher.appendReplacement(sb, value != null ? Matcher.quoteReplacement(value.toString()) : "");
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static void main(String[] args) {
        String template = "Hi {name}, your code is {code}.";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", "老马");
        params.put("code", 6789);
        System.out.println(templateEngine(template, params));
    }
}
