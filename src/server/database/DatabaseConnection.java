package server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    // Private constructor to prevent instantiation
    private DatabaseConnection() throws SQLException {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres?currentSchema=sep2_rms",

                    "postgres", "captos123");

        } catch (SQLException e) {
            throw new SQLException("Failed to create the database connection.", e);
        }
    }

    // Method to get the singleton instance of DatabaseConnection
    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // Method to get the database connection
    public Connection getConnection() {
        return connection;
    }
}
