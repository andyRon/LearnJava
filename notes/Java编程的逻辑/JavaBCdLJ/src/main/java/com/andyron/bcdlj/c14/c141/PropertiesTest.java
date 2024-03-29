package com.andyron.bcdlj.c14.c141;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesTest {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("data/config.properties"));
        String host = properties.getProperty("db.host");
        int port = Integer.valueOf(properties.getProperty("db.port", "3307"));
        System.out.println(host);
        System.out.println(port);
        System.out.println(properties.getProperty("myname"));
    }
}
