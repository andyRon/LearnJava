package com.andyron.bcdlj.c07.c75;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Test {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date.getTime());

        TimeZone tz = TimeZone.getDefault();
        System.out.println(tz.getID());
        System.out.println(System.getProperty("user.timezone"));
        System.out.println(tz.getDisplayName());

        tz = TimeZone.getTimeZone("US/Eastern");
        System.out.println(tz.getDisplayName());

        tz = TimeZone.getTimeZone("America/Chicago");
        System.out.println(tz.getDisplayName());

        System.out.println("-------");

        Locale locale = Locale.getDefault();
        System.out.println(locale.toString());

        System.out.println("-------");

        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DAY_OF_MONTH, 1);
//        calendar.set(Calendar.HOUR_OF_DAY, 14);
//        calendar.set(Calendar.MINUTE, 15);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
        System.out.println("year: " + calendar.get(Calendar.YEAR));
        System.out.println("month: " + calendar.get(Calendar.MONTH));
        System.out.println("day: " + calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("hour: " + calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println("minute: " + calendar.get(Calendar.MINUTE));
        System.out.println("second: " + calendar.get(Calendar.SECOND));
        System.out.println("millisecond: " + calendar.get(Calendar.MILLISECOND));
        System.out.println("day_of_week: " + calendar.get(Calendar.DAY_OF_WEEK));

        System.out.println("-------");
        calendar.set(2021, 8, 15, 19, 25, 20);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 E HH时mm分ss秒");
        System.out.println(sdf.format(calendar.getTime()));

        // 获得月份的最大天数
        System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        test();
    }

    static void test() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str1 = "2021 09 08 14 38 05";

        //write your code here......
        String[] strarr = str1.split(" ");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, -12);
        System.out.println(sdf.format(cal.getTime()));


    }
}
