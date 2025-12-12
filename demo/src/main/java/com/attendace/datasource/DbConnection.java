package com.attendace.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for managing the database connection to the MySQL database.
 * Provides static methods to obtain and close the connection.
 */
public class DbConnection {

    /**
     * The database connection instance. Shared across the application.
     */
    private static Connection connection = null;

    /**
     * The JDBC URL for the database connection.
     */

    // If connection is null, method will try to create connection to the database when called
    // jdbc:mysql://localhost:3306
    // jdbc:mysql://host.docker.internal:3306

    private static final String URL = "jdbc:mysql://localhost:3306/courseDB";

    /**
     * The username for the database connection.
     */
    private static final String USER = "user";

    /**
     * The password for the database connection.
     */
    private static final String PASSWORD = "pass";

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private DbConnection() {
        // Prevent instantiation
    }

    /**
     * Returns the database connection. If the connection does not exist,
     * it attempts to create a new one.
     *
     * @return the {@link Connection} to the database, or null if a connection could not be established
     */
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Attempt to establish a new database connection
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        } else {
            return connection;
        }
    }

    /**
     * Closes the database connection if it exists and sets the connection to null.
     * Any exceptions during closing are printed to the standard error.
     */
    public static void closeConnection() {
        Connection conn = getConnection();

        if (conn != null) {
            try {
                conn.close();
                connection = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}