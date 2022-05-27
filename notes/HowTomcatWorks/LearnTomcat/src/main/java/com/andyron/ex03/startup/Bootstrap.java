package com.andyron.ex03.startup;

import com.andyron.ex03.connector.http.HttpConnector;

public class Bootstrap {
    public static void main(String[] args) {
        HttpConnector connector = new HttpConnector();
        connector.start();
    }
}
