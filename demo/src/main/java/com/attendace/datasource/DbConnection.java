package com.attendace.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static Connection connection = null;

    // If connection is null, method will try to create connection to the database when called

    public static Connection getConnection(){
        if (connection == null){
            try {
                //CHANGE TO UR OWN USER DB ACCOUNT.
                connection = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/courseDB?user=user&password=pass");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        } else {
            return connection;
        }
    }

    // This method close connection, when called

    public static void closeConnection(){
        if (connection != null){
            try {
                getConnection().close();
                connection = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
