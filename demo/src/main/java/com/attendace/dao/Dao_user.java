package com.attendace.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.attendace.datasource.DbConnection;

public class Dao_user {
    
    public ArrayList<String> getAllData(){

        // Fetch all users from Users-table.
        // Method return row id, username, student id, degree and timestamp.
        
        ArrayList<String> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT * FROM USERS ORDER BY id DESC LIMIT 1";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                data.add(Integer.toString(rs.getInt("id")));
                data.add(rs.getString("user_name"));
                data.add(Integer.toString(rs.getInt("user_student_id")));
                data.add(rs.getString("user_degree"));
                data.add(rs.getDate("pvm").toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    public ArrayList<String> getData(String userName){

        // Fetch specific user from Users-table
        // Method return id, username, student id, degree, password and timestamp

        ArrayList<String> data = new ArrayList<>();
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT * FROM USERS WHERE user_name = ?";


        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                data.add(Integer.toString(rs.getInt("id")));
                data.add(rs.getString("user_name"));
                data.add(Integer.toString(rs.getInt("user_student_id")));
                data.add(rs.getString("user_degree"));
                data.add(rs.getString("user_password"));
                data.add(rs.getDate("pvm").toString());
            }

            
        } catch (SQLException e){
            e.printStackTrace();;
        }
        
        return data;
    }

    public void setData(String name, int id, String degree, String passw){
        
        // Set users data to the table

        Connection connection = DbConnection.getConnection();
        String sql = "INSERT INTO USERS (user_name, user_student_id, user_degree, user_passw) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, id);
            ps.setString(3, degree);
            ps.setString(4, passw);

            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    

}
