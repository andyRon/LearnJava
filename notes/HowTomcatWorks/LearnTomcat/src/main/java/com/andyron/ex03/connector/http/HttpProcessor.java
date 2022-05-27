package com.andyron.ex03.connector.http;

import java.io.OutputStream;
import java.net.Socket;

public class HttpProcessor {
    public HttpProcessor(HttpConnector httpConnector) {
    }

    public void process(Socket socket) {
        SocketInputStream input = null;
        OutputStream output = null;
        try {
            input = new SocketInputStream(socket.getInputStream(), 2028);
            output = socket.getOutputStream();


        } catch (Exception e) {

        }
    }
}
