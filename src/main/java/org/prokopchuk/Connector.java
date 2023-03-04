package org.prokopchuk;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class Connector {
    private static final String URL = "jdbc:postgresql://localhost/test";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";
    private final Connection connection;

    public Connector() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            log.info("Initialized the connection successfully");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialized connection");
        }
    }
    public Connection getConnection() {
        return connection;
    }
    public void closeConnection() {
        try {
            connection.close();
            log.info("Connection closed successfully");
        }
        catch (SQLException e) {
            throw  new RuntimeException("Failed to close connection");
        }
    }
}
