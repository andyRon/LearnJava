package com.andyorn.state;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
    模拟倒计时
 */
public class TestSleep2 {

    public static void main(String[] args) {
        try {
//            tenDown();
            printTime();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void tenDown() throws InterruptedException {
        int num = 10;
        while (true) {
            Thread.sleep(1000);
            if (num <= 0) {
                break;
            }
            System.out.println(num--);
        }
    }

    // 打印当前系统时间
    public static void printTime() throws InterruptedException {
        Date starttime = new Date(System.currentTimeMillis()); // 获取系统当前时间
        while (true) {
            Thread.sleep(1000);
            System.out.println(new SimpleDateFormat("HH:mm:ss").format(starttime));
            starttime = new Date(System.currentTimeMillis());
        }
    }
}
