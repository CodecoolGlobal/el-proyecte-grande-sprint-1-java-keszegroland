package com.codecool.backend.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private final String dbURL;
    private final String dbUser;
    private final String dbPassword;

    public DatabaseConnection(String dbURL, String dbUser, String dbPassword) {
        this.dbURL = dbURL;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public Connection getConnection() throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", dbUser);
        props.setProperty("password", dbPassword);
        return DriverManager.getConnection(dbURL, props);
    }
}
