package com.andyron.ex03.connector.http;

import java.io.IOException;
import java.io.InputStream;

public class SocketInputStream extends InputStream {
    public SocketInputStream(InputStream inputStream, int i) {
    }

    @Override
    public int read() throws IOException {
        return 0;
    }
}
