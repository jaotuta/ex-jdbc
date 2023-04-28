package com.example.MySql;

import com.example.Log;

import java.io.IOException;

public class Logger {
    static Log log;

    static {
        try {
            log = new Log("log_info");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Log getLog() {
        return log;
    }

    public Logger() throws IOException {
    }
}
