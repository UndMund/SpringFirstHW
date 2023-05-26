package org.example.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class ConnectionManager {
    private final String url;
    private final String username;
    private final String password;

    public ConnectionManager(@Value("${db.url}") String url,
                             @Value("${db.username}") String username,
                             @Value("${db.password}") String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection open() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
