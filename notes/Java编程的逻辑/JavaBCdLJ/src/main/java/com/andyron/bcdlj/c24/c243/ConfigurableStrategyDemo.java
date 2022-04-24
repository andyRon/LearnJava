package com.andyron.bcdlj.c24.c243;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurableStrategyDemo {

    public static IService createService() {
        try {
            Properties prop = new Properties();
            String filename = "data/config.properties";
            prop.load(new FileInputStream(filename));
            String className = prop.getProperty("service");
            Class<?> cls = Class.forName(className);
            return (IService) cls.newInstance();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) {
        IService service = createService();
        service.action();
    }
}
